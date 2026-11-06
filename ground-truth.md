# Ground truth — тестовый PR для AI code-review бота

Ветка `test/ai-review-eval` → `master`. Эталон составлен **до** запуска бота.
Проблемы привязаны к пунктам `.github/review-instructions.md`. Матчинг — по файлу и сути проблемы (строка ±3).
Строки указаны для файлов из этого комплекта; для `DataBaseManager` — это номера строк внутри `DataBaseManager_additions.txt`.

## Реальные проблемы — всего 10

| ID | Файл / метод | Строка | Пункт инструкции | Ожидаемая severity | Что не так |
|----|--------------|--------|------------------|--------------------|------------|
| SEC-1 | DataBaseManager.findTicketsByName | 8 | 4.1.1 | error | SQL-инъекция: `name` склеивается в запрос через `Statement` |
| JDBC-1 | DataBaseManager.findTicketsByName | 6 | 4.2.4 | warning | `Connection`/`Statement`/`ResultSet` не закрываются, нет try-with-resources |
| SEC-2 | RemoveTicketCommand | 14 | 4.1.2 | error | `ownerId` берётся из клиентского `Request`, владелец билета в БД не проверяется — можно удалить чужой билет |
| DB-1 | RemoveTicketCommand | 16–17 | 4.2.2 / 4.2.3 | warning | Результат `deleteTicket` (boolean) игнорируется: билет удаляется из коллекции и возвращается «удалён», даже если в БД ничего не удалено |
| AUTH-1 | UpdatePriceCommand | 12–14 | 4.1.3 | error | Нет проверки, что пользователь вошёл (`request.getUser() == null` не проверяется) |
| VAL-1 | UpdatePriceCommand | 14 | 4.3.3 | warning | `newPrice` не валидируется (цена билета должна быть > 0) |
| INPUT-1 | RemoveTicketCommand (и UpdatePriceCommand) | 13 | 4.1.5 / 4.3.2 | warning | `Long.parseLong` без обработки `NumberFormatException` — некорректный id роняет обработку запроса. Засчитывается как одна проблема, даже если бот указал оба файла |
| SEC-3 | RegisterUserCommand | 10 | 4.1.4 | error | Захардкоженный токен `ADMIN_API_TOKEN` в исходниках |
| SEC-4 | RegisterUserCommand | 24 | 4.1.4 | error | Пароль пользователя пишется в лог |
| ARCH-1 | ServerRequestHandler | 12 | 4.5.1 / 4.5.6 | warning | Логика новой команды `count_by_venue` реализована в обработчике сервера, а не в отдельном классе-наследнике `AbstractCommand` |

## False-positive bait — корректный код, бот ругаться не должен — всего 4

| ID | Файл / метод | Строка | Чем похоже на ошибку | Почему корректно |
|----|--------------|--------|----------------------|------------------|
| FP-1 | DataBaseManager.findTicketIdsByOwner | 25–36 | SQL с пользовательским `login` | `PreparedStatement` с параметром `?`, try-with-resources |
| FP-2 | DataBaseManager.listTicketIdsSorted | 52 | SQL склеивается конкатенацией (`ORDER BY " + column`) | `column` выбирается из whitelist (`switch`), ввод пользователя в запрос не попадает |
| FP-3 | FindByIdCommand | 17 | `catch` на `NumberFormatException` — «проглоченное исключение» | Исключение обработано: возвращается осмысленный `Response` об ошибке, до этого есть проверка входа |
| FP-4 | RegisterUserCommand | 11 | Константа `PASSWORD_MIN_LENGTH = 6` рядом со словом password | Это не пароль, а ограничение длины; секретов нет |

Целиком корректный файл: `ShowMyTicketsCommand` (проверка входа, запрос через `DataBaseManager`, ошибка БД возвращается в `Response`). Любое замечание на него — FP.

## Серая зона (не входит ни в TP, ни в FP — отмечается как «valid extra», если бот нашёл)

- `UpdatePriceCommand` не проверяет владельца билета (обновление по id с `request.getUser()` — зависит от реализации `updatePrice`, которой в PR нет).
- Отсутствие тестов, стиль и именование — в инструкциях такого пункта нет, поэтому не заложено и не засчитывается.
- Замечания о методах, которых нет в PR (`updatePrice`, `userManager.register`): это недостаток тестового стенда, не заложенная проблема. Если бот пишет «метод не существует» — это **FP** по правилу «не сообщай о вымышленных ошибках компиляции» только если метод в реальном репозитории есть; проверь это и зафиксируй в REPORT.

## Правила подсчёта

- **TP** — замечание на ту же проблему в том же файле (строка ±3), формулировка любая. Одна проблема считается один раз, дубли не увеличивают TP.
- **FP** — замечание, не соответствующее ни одной реальной проблеме и не относящееся к серой зоне. Попадание в FP-1…FP-4 или `ShowMyTicketsCommand` фиксируется отдельно как **FP-bait hit**.
- **FN** — реальная проблема, которую бот не нашёл.
- Верная проблема с неверной severity — TP, но severity-расхождение фиксируется отдельно.

```
precision = TP / (TP + FP)
recall    = TP / 10
F1        = 2 * precision * recall / (precision + recall)
```
