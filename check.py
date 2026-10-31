import argparse
from collections import Counter
from dataclasses import asdict, dataclass
import json
from pathlib import Path
import re
import subprocess
import sys

RULES = {
    'C1': 'Command classes', 'C2': 'Database access', 'C3': 'Validation',
    'C4': 'Error-handling', 'C5': 'Client-Server protocol',
    'C6': 'Collection access', 'C7': 'Layer separation',
}
VERSION = '1.0'


@dataclass
class Finding:
    rule: str
    file: str
    line: int
    message: str
    evidence: str
    kind: str = 'violation'

    def key(self):
        return self.rule, self.file, self.message, re.sub(r'\s+', '', self.evidence)


def java_views(text):
    chars = list(text)
    literals = []
    i = 0
    while i < len(text):
        start = i
        if text.startswith('//', i):
            end = text.find('\n', i)
            i = len(text) if end == -1 else end
        elif text.startswith('/*', i):
            end = text.find('*/', i + 2)
            if end == -1:
                raise ValueError('Незакрытый Java-комментарий')
            i = end + 2
        elif text.startswith('"""', i):
            i += 3
            while i < len(text):
                if text[i] == '\\':
                    i += 2
                elif text.startswith('"""', i):
                    break
                else:
                    i += 1
            if i >= len(text):
                raise ValueError('Незакрытый Java text block')
            literals.append((start, text[start + 3:i]))
            i += 3
        elif text[i] in '\"\'':
            quote = text[i]
            i += 1
            while i < len(text):
                if text[i] == '\\':
                    i += 2
                elif text[i] == quote:
                    break
                else:
                    i += 1
            if i >= len(text):
                raise ValueError('Незакрытый Java-литерал')
            if quote == '"':
                literals.append((start, text[start + 1:i]))
            i += 1
        else:
            i += 1
            continue
        for pos in range(start, min(i, len(text))):
            if chars[pos] not in '\r\n':
                chars[pos] = ' '
    return ''.join(chars), literals


def read_sources(root):
    source = root / 'src/main/java'
    if not source.is_dir():
        raise ValueError('Нет src/main/java. Передай корень lab7 через --root.')
    files = {}
    for path in sorted(source.rglob('*.java')):
        if path.is_symlink():
            raise ValueError('Не поддерживается символьная ссылка: ' + str(path))
        files[path.relative_to(root).as_posix()] = path.read_text(encoding='utf-8-sig')
    if not files:
        raise ValueError('Не найдено ни одного Java-файла; проверка не выполнена.')
    return files


def git_bytes(root, *args):
    result = subprocess.run(['git', '-C', str(root), *args],
                            stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    if result.returncode:
        raise ValueError(result.stderr.decode('utf-8', errors='replace').strip())
    return result.stdout


def read_base(root, ref):
    sha = git_bytes(root, 'rev-parse', '--verify', '--end-of-options',
                    ref + '^{commit}').decode().strip()
    top = Path(git_bytes(root, 'rev-parse', '--show-toplevel').decode().strip()).resolve()
    prefix = root.relative_to(top).as_posix()
    prefix = '' if prefix == '.' else prefix + '/'
    source = prefix + 'src/main/java/'
    names = git_bytes(top, 'ls-tree', '-r', '--name-only', '-z', sha,
                      '--', source).decode('utf-8').split('\0')
    files = {name[len(prefix):]: git_bytes(top, 'show', sha + ':' + name).decode('utf-8-sig')
             for name in names if name.endswith('.java')}
    if not files:
        raise ValueError('В исходном коммите нет Java-файлов проекта.')
    return sha, files


def scan(files):
    found = []
    views = {}
    classes = {}
    for path, text in files.items():
        code, literals = java_views(text)
        views[path] = code, literals
        match = re.search(r'\bclass\s+(\w+)(?:\s+extends\s+([\w.]+))?', code)
        if match:
            name, parent = match.group(1), match.group(2)
            if name in classes:
                raise ValueError('Неоднозначное имя класса: ' + name)
            classes[name] = (path, parent.rsplit('.', 1)[-1] if parent else None)

    registered = set()
    for path, (code, _) in views.items():
        if Path(path).name == 'CommandManager.java':
            registered.update(re.findall(r'\bcommands\s*\.\s*put\s*\([^;]*?\bnew\s+([\w.]+)\s*\(', code))
    registered = {name.rsplit('.', 1)[-1] for name in registered}

    def inherits(name):
        seen = set()
        while name and name not in seen:
            if name == 'AbstractCommand':
                return True
            seen.add(name)
            name = classes.get(name, ('', None))[1]
        return False

    def add(rule, path, offset, message, evidence, kind='violation'):
        line = files[path].count('\n', 0, offset) + 1
        found.append(Finding(rule, path, line, message, evidence.strip(), kind))

    for path, text in files.items():
        code, literals = views[path]
        basename = Path(path).name
        cls = re.search(r'\bclass\s+(\w+)', code)
        name = cls.group(1) if cls else ''
        package = re.search(r'\bpackage\s+([\w.]+)\s*;', code)
        package_name = package.group(1) if package else ''
        is_command = name != 'AbstractCommand' and bool(name) and (
                name in registered or name.endswith('Command') or inherits(name))
        is_network = basename in {'Client.java', 'Server.java'}
        if is_command:
            if not package_name.endswith('.command') or '/command/' not in path:
                add('C1', path, cls.start(), 'Класс команды вне пакета/каталога command.', name)
            if not inherits(name):
                add('C1', path, cls.start(), 'Класс команды не наследуется от AbstractCommand.', name)
            signature = re.search(r'\bexecute\s*\(([^)]*)\)', code)
            if signature and not re.search(r'\bRequest\b', signature.group(1)):
                add('C5', path, signature.start(), 'execute принимает параметры без Request.', signature.group())
            elif not signature:
                add('C5', path, cls.start(), 'Проверь унаследованный execute и передачу параметров через Request.', name, 'review')
            has_validator = bool(re.search(r'\b(?:Ticket|Coordinates|Venue)Validator\s*\.', code))
            add('C3', path, cls.start(),
                'Сверь проверки полей моделей; ' + ('вызов валидатора найден.' if has_validator else 'прямой вызов валидатора не найден, возможен вызов через менеджер.'), name, 'review')
            add('C4', path, cls.start(), 'Сверь ошибочные случаи и использование подходящих существующих исключений.', name, 'review')
            add('C5', path, cls.start(), 'Сверь состав Request, Client и ScriptExecutor для этой команды.', name, 'review')
            add('C7', path, cls.start(), 'Сверь, что команда координирует менеджеры, без переноса их ответственности.', name, 'review')

        if basename != 'DataBaseManager.java':
            patterns = [
                (r'\bimport\s+(?:static\s+)?(?:java\.sql|javax\.sql|org\.postgresql|com\.zaxxer\.hikari)\.[\w.*]+\s*;', 'Импорт JDBC/драйвера/пула вне DataBaseManager.'),
                (r'\bDriverManager\s*\.\s*getConnection\s*\(', 'Создание JDBC-соединения вне DataBaseManager.'),
                (r'\b\w+\s*\.\s*(?:prepareStatement|prepareCall|createStatement)\s*\(', 'Вызов подготовки SQL вне DataBaseManager.'),
            ]
            for pattern, message in patterns:
                for match in re.finditer(pattern, code):
                    add('C2', path, match.start(), message, match.group())
            for pos, value in literals:
                if re.match(r'\s*(?:SELECT\b.+?\bFROM\b|INSERT\s+INTO\b|UPDATE\b.+?\bSET\b|DELETE\s+FROM\b|CREATE\s+TABLE\b|ALTER\s+TABLE\b|DROP\s+TABLE\b)', value, re.I | re.S):
                    add('C2', path, pos, 'Строка похожа на SQL: проверь назначение вне DataBaseManager.', value, 'review')

        if is_command:
            mutation = r'(?:add|addAll|remove|removeAll|removeIf|retainAll|clear)'
            direct = r'\b\w+\s*\.\s*getCollection\s*\(\s*\)\s*\.\s*' + mutation + r'\s*\('
            for match in re.finditer(direct, code):
                add('C6', path, match.start(), 'Команда изменяет результат getCollection() в обход метода менеджера.', match.group())
            alias_pattern = r'\b(\w+)\s*=\s*\w+\s*\.\s*getCollection\s*\(\s*\)(?:\s*\.\s*iterator\s*\(\s*\))?\s*;'
            for alias in re.finditer(alias_pattern, code):
                variable = alias.group(1)
                pattern = r'\b' + re.escape(variable) + r'\s*\.\s*' + mutation + r'\s*\('
                for match in re.finditer(pattern, code[alias.end():]):
                    offset = alias.end() + match.start()
                    add('C6', path, offset, 'Возможная мутация копии коллекции/её итератора: проверь алиас.', match.group(), 'review')
            if re.search(r'\bgetCollection\s*\(|\bgetById\s*\(', code):
                add('C6', path, cls.start(), 'Проверь операции и изменения объектов, полученных из CollectionManager.', name, 'review')

        if is_network:
            for match in re.finditer(r'\b(?:CollectionManager|collectionManager)\s*\.\s*(?:add|update|delete|removeById|clear|remove_lower|remove_greater|add_if_max)\s*\(', code):
                add('C7', path, match.start(), 'Сетевой класс непосредственно изменяет основную коллекцию.', match.group())
            add('C7', path, 0, 'Просмотри новые ветки/методы: бизнес-логику по одному dispatch обнаружить нельзя.', basename, 'review')

        if name == 'Request' and not re.search(r'\bimplements\b[^\{]*\bSerializable\b', code):
            add('C5', path, cls.start(), 'Проверь Serializable у Request, включая наследование.', name, 'review')
        if basename in {'Client.java', 'ScriptExecutor.java'}:
            for match in re.finditer(r'\b(?:Ticket|Coordinates|Venue)Validator\s*\.', code):
                add('C3', path, match.start(), 'Серверный валидатор вызван на клиенте: проверь наличие серверной проверки.', match.group(), 'review')
    unique = {(f.rule, f.file, f.line, f.message, f.evidence, f.kind): f for f in found}
    return sorted(unique.values(), key=lambda f: (f.file, f.line, f.rule, f.kind, f.message))


def compare(current, previous):
    counts = Counter(f.key() for f in previous if f.kind == 'violation')
    added, existing = [], []
    for finding in current:
        if finding.kind != 'violation':
            continue
        if counts[finding.key()]:
            existing.append(finding)
            counts[finding.key()] -= 1
        else:
            added.append(finding)
    return added, existing, sum(counts.values())


def make_report(current_files, base_files=None, sha=None):
    current = scan(current_files)
    previous = scan(base_files) if base_files is not None else []
    added, existing, removed = compare(current, previous)
    changed = set(current_files) if base_files is None else {
        p for p, t in current_files.items() if t != base_files.get(p)}
    reviews = [f for f in current if f.kind == 'review' and f.file in changed]
    return {
        'checker_version': VERSION, 'base_commit': sha,
        'scope': 'whole_project' if base_files is None else 'compared_with_base',
        'java_files': len(current_files), 'changed_java_files': len(changed),
        'rules': RULES, 'violations': [asdict(f) for f in added],
        'existing_violations': [asdict(f) for f in existing],
        'reviews': [asdict(f) for f in reviews],
        'counts': {'violations': len(added), 'existing': len(existing),
                   'removed': removed, 'manual_review': len(reviews)},
        'limitations': 'Текстовые признаки; нет AST, анализа потоков данных, запуска Java или тестов. Ноль находок не доказывает соблюдение всех правил.',
    }


def main(argv=None):
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument('--root', type=Path, default=Path('.'), help='Корень Java-проекта, по умолчанию текущая папка.')
    parser.add_argument('--base', help='Исходный Git-коммит, общий для всех прогонов.')
    parser.add_argument('--json', type=Path, help='Сохранить подробный JSON-отчёт.')
    args = parser.parse_args(argv)
    try:
        root = args.root.resolve()
        current = read_sources(root)
        sha, base = read_base(root, args.base) if args.base else (None, None)
        report = make_report(current, base, sha)
        if args.json:
            args.json.parent.mkdir(parents=True, exist_ok=True)
            args.json.write_text(json.dumps(report, ensure_ascii=False, indent=2) + '\n', encoding='utf-8')
        for key, label in [('violations', 'НАРУШЕНИЕ'), ('reviews', 'СВЕРИТЬ')]:
            for f in report[key]:
                print(f"[{label} {f['rule']}] {f['file']}:{f['line']} — {f['message']}")
        count = report['counts']
        print(f"\nJava-файлов: {report['java_files']}; нарушений: {count['violations']}; ручных проверок: {count['manual_review']}.")
        if sha:
            print(f"Исходный коммит: {sha}; старых находок осталось: {count['existing']}; устранено: {count['removed']}.")
        else:
            print('Без --base проверяется весь проект, включая старые нарушения.')
        print(report['limitations'])
        return 1 if count['violations'] else 0
    except (OSError, UnicodeError, ValueError) as exc:
        print('Ошибка проверки: ' + str(exc), file=sys.stderr)
        return 2


if __name__ == '__main__':
    sys.exit(main())
