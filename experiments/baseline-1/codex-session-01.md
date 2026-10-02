# Codex conversation

## User

Добавь в приложение lab7 команду `rename_ticket`, позволяющую авторизованному пользователю исправить название одного принадлежащего ему билета.

Требования:

## Assistant

Посмотрю структуру lab7 и существующие команды для работы с билетами, чтобы добавить `rename_ticket` в том же стиле.

## Assistant

Сообщение обрывается на «Требования:». Пришлите продолжение требований к `rename_ticket`, если оно есть.

## User

1. В интерактивном режиме пользователь вводит `rename_ticket`, затем по запросам программы вводит ID билета и новое название. В скрипте команда, ID и название располагаются на трёх последовательных строках:
   rename_ticket
   12
   Концерт симфонического оркестра

2. ID  положительное целое число, представимое типом `long`. Отсутствующий ID, неверный формат, выход за пределы типа, ноль и отрицательные числа должны приводить к понятной ошибке без изменения данных. Новое название не должно отсутствовать, быть пустым или состоять только из пробельных символов. Удали пробельные символы по краям названия; пробелы внутри, кириллица, кавычки и другие символы должны сохраняться. Название считывается целой строкой.

## Activity

    $ Get-Location; rg --files -g AGENTS.md -g '*Ticket*' -g '*Command*' -g 'pom.xml' -g 'build.gradle*' -g 'README*'
    
    pom.xml
    src\main\java\org\example\client\TicketInput.java
    src\main\java\org\example\server\manager\TicketValidator.java
    src\main\java\org\example\server\manager\CommandManager.java
    src\main\java\org\example\server\command\UpdateCommand.java
    src\main\java\org\example\server\command\ShowCommand.java
    src\main\java\org\example\common\model\Ticket.java
    src\main\java\org\example\server\command\RemoveLowerCommand.java
    src\main\java\org\example\server\command\RemoveGreaterCommand.java
    src\main\java\org\example\server\command\RemoveByIdCommand.java
    src\main\java\org\example\server\command\PrintFieldDescendingDiscountCommand.java
    src\main\java\org\example\server\command\PrintDescendingCommand.java
    src\main\java\org\example\server\command\InfoCommand.java
    src\main\java\org\example\server\command\HelpCommand.java
    src\main\java\org\example\server\command\FilterCommand.java
    src\main\java\org\example\server\command\ExitCommand.java
    src\main\java\org\example\server\command\ClearCommand.java
    src\main\java\org\example\server\command\AddIfMaxCommand.java
    src\main\java\org\example\server\command\AddCommand.java
    src\main\java\org\example\server\command\AbstractCommand.java
    src\main\java\org\example\common\Factory\TicketFactory.java
    src\main\java\org\example\common\Factory\ScriptTicketFactory.java
    src\main\java\org\example\common\model\AbstractTicket.java
    src\main\java\org\example\common\model\enums\TicketType.java
    Path
    ----
    C:\Users\perlm\lab7
    
    
    ✓ • 504ms

## Activity

    $ rg --files; Get-Content pom.xml; Get-Content src/main/java/org/example/server/command/UpdateCommand.java; Get-Content src/main/java/org/example/server/manager/CommandManager.java
    pom.xml
    out\artifacts\lab7_jar2\lab7.jar
    out\artifacts\lab7_jar\lab7.jar
    src\main\resources\META-INF\MANIFEST.MF
    src\test\java\org\example\common\RequestTest.java
    src\main\java\org\example\server\Server.java
    src\main\java\org\example\client\TicketInput.java
    src\main\java\org\example\client\ScriptExecutor.java
    src\main\java\org\example\client\Client.java
    src\main\java\org\example\Main.java
    src\main\java\org\example\server\manager\VenueValidator.java
    src\main\java\org\example\server\manager\TicketValidator.java
    src\main\java\org\example\server\manager\DataBaseManager.java
    src\main\java\org\example\server\manager\CoordinatesValidator.java
    src\main\java\org\example\server\manager\CommandManager.java
    src\main\java\org\example\server\manager\CollectionManager.java
    src\main\java\org\example\common\Response.java
    src\main\java\org\example\common\Request.java
    src\main\java\org\example\common\model\Venue.java
    src\main\java\org\example\common\model\Ticket.java
    src\main\java\org\example\server\command\UpdateCommand.java
    src\main\java\org\example\server\command\ShowCommand.java
    src\main\java\org\example\server\command\RemoveLowerCommand.java
    src\main\java\org\example\server\command\RemoveGreaterCommand.java
    src\main\java\org\example\server\command\RemoveByIdCommand.java
    src\main\java\org\example\server\command\PrintFieldDescendingDiscountCommand.java
    src\main\java\org\example\server\command\PrintDescendingCommand.java
    src\main\java\org\example\server\command\InfoCommand.java
    src\main\java\org\example\server\command\HelpCommand.java
    src\main\java\org\example\server\command\FilterCommand.java
    src\main\java\org\example\server\command\ExitCommand.java
    src\main\java\org\example\server\command\ClearCommand.java
    src\main\java\org\example\server\command\AddIfMaxCommand.java
    src\main\java\org\example\server\command\AddCommand.java
    src\main\java\org\example\server\command\AbstractCommand.java
    src\main\java\org\example\common\model\AbstractTicket.java
    src\main\java\org\example\common\exceptions\WrongArgumentException.java
    src\main\java\org\example\common\exceptions\NoElementException.java
    src\main\java\org\example\common\model\Coordinates.java
    src\main\java\org\example\common\model\generator\VenueGenerator.java
    src\main\java\org\example\common\model\generator\IdGenerator.java
    src\main\java\org\example\common\model\generator\CoordinatesGenerator.java
    src\main\java\org\example\common\Factory\TicketFactory.java
    src\main\java\org\example\common\Factory\ScriptTicketFactory.java
    src\main\java\org\example\common\Factory\Factory.java
    src\main\java\org\example\server\handlers\HashHandler.java
    src\main\java\org\example\common\model\enums\VenueType.java
    src\main\java\org\example\common\model\enums\TicketType.java
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
    
        <groupId>org.example</groupId>
        <artifactId>lab7</artifactId>
        <version>1.0-SNAPSHOT</version>
    
        <properties>
            <maven.compiler.source>17</maven.compiler.source>
            <maven.compiler.target>17</maven.compiler.target>
            <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        </properties>
    
        <dependencies>
            <dependency>
                <groupId>org.postgresql</groupId>
                <artifactId>postgresql</artifactId>
                <version>42.7.3</version>
            </dependency>
    
            <dependency>
                <groupId>com.zaxxer</groupId>
                <artifactId>HikariCP</artifactId>
                <version>5.1.0</version>
            </dependency>
    
            <dependency>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-api</artifactId>
                <version>2.0.9</version>
            </dependency>
    
            <dependency>
                <groupId>ch.qos.logback</groupId>
                <artifactId>logback-classic</artifactId>
                <version>1.5.13</version>
            </dependency>
    
            <dependency>
                <groupId>org.junit.jupiter</groupId>
                <artifactId>junit-jupiter</artifactId>
                <version>5.10.2</version>
                <scope>test</scope>
            </dependency>
        </dependencies>
    </project>
    package org.example.server.command;
    
    import org.example.common.Request;
    import org.example.common.Response;
    import org.example.common.exceptions.NoElementException;
    import org.example.common.model.Ticket;
    import org.example.server.manager.CollectionManager;
    import org.example.server.manager.DataBaseManager;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    
    /**
     * <p>РљРѕРјР°РЅРґР° РґР»СЏ РѕР±РЅРѕРІР»РµРЅРёСЏ СЌР»РµРјРµРЅС‚Р° РєРѕР»Р»РµРєС†РёРё РїРѕ Р·Р°РґР°РЅРЅРѕРјСѓ ID.</p>
     *
     * <p>Р•СЃР»Рё СЌР»РµРјРµРЅС‚ СЃ СѓРєР°Р·Р°РЅРЅС‹Рј ID РЅРµ РЅР°Р№РґРµРЅ, РІРѕР·РІСЂР°С‰Р°РµС‚ СЃРѕРѕС‚РІРµС‚СЃС‚РІСѓСЋС‰РёР№ РѕР±СЉРµРєС‚ {@link Response} СЃ СЃРѕРѕР±С‰РµРЅРёРµРј РѕР± РѕС€РёР±РєРµ.</p>
     *
     * <p><strong>РџСЂРёРјРµСЂ РёСЃРїРѕР»СЊР·РѕРІР°РЅРёСЏ:</strong> <code>update 5 {element_data}</code></p>
     *
     * @version 1.3
     */
    public class UpdateCommand extends AbstractCommand {
        @Override
        public Response execute(Request request, CollectionManager collectionManager) {
            Ticket ticket = request.getTicket();
            if (ticket == null) {
                return new Response("ERROR: Ticket data missing", null);
            }
    
            try {
                if (!DataBaseManager.updateTicketById((int) ticket.getId(), ticket, request.getLogin())) {
                    return new Response("ERROR: Ticket not found or access denied", null);
                }
                collectionManager.update(ticket);
                logger.info("Ticket updated by {}: {}", request.getLogin(), ticket.getId());
                return new Response("Ticket updated successfully", ticket);
            } catch (Exception e) {
                logger.error("Update failed for ticket {}: {}", ticket.getId(), e.getMessage());
                return new Response("ERROR: Update failed - " + e.getMessage(), null);
            }
        }
    }
    package org.example.server.manager;
    
    import org.example.common.Request;
    import org.example.common.Response;
    import org.example.server.command.*;
    
    import java.util.HashMap;
    
    /**
     * РњРµРЅРµРґР¶РµСЂ РєРѕРјР°РЅРґ, РѕСЃСѓС‰РµСЃС‚РІР»СЏСЋС‰РёР№ СЂРµРіРёСЃС‚СЂР°С†РёСЋ Рё РІС‹РїРѕР»РЅРµРЅРёРµ РґРѕСЃС‚СѓРїРЅС‹С… РєРѕРјР°РЅРґ.
     * <p>РҐСЂР°РЅРёС‚ РѕС‚РѕР±СЂР°Р¶РµРЅРёРµ РЅР°Р·РІР°РЅРёР№ РєРѕРјР°РЅРґ РЅР° СЃРѕРѕС‚РІРµС‚СЃС‚РІСѓСЋС‰РёРµ РєР»Р°СЃСЃС‹ Рё РїРµСЂРµРґР°С‘С‚ СѓРїСЂР°РІР»РµРЅРёРµ РїСЂРё РІС‹РїРѕР»РЅРµРЅРёРё.</p>
     */
    public class CommandManager {
    
        private final HashMap<String, AbstractCommand> commands = new HashMap<>();
    
    
        public CommandManager(CollectionManager collectionManager) {
    
            // Р РµРіРёСЃС‚СЂР°С†РёСЏ РІСЃРµС… РґРѕСЃС‚СѓРїРЅС‹С… РєРѕРјР°РЅРґ
            commands.put("info", new InfoCommand());
            commands.put("add", new AddCommand());
            commands.put("show", new ShowCommand());
            commands.put("remove_greater", new RemoveGreaterCommand());
            commands.put("print_descending", new PrintDescendingCommand());
            commands.put("help", new HelpCommand());
            commands.put("update", new UpdateCommand());
            commands.put("remove_by_id", new RemoveByIdCommand());
            commands.put("clear", new ClearCommand());
            commands.put("add_if_max", new AddIfMaxCommand());
            commands.put("remove_lower", new RemoveLowerCommand());
            commands.put("filter_starts_with", new FilterCommand());
            commands.put("print_field_descending_discount", new PrintFieldDescendingDiscountCommand());
            commands.put("exit", new ExitCommand());
    
    
        }
    
        /**
         * Р’С‹РїРѕР»РЅСЏРµС‚ РєРѕРјР°РЅРґСѓ, СЃРѕРѕС‚РІРµС‚СЃС‚РІСѓСЋС‰СѓСЋ РїРµСЂРµРґР°РЅРЅРѕРјСѓ РІРІРѕРґСѓ.
         *
         * @param request
         */
        public Response doCommand(Request request, CollectionManager collectionManager) {
            AbstractCommand command = commands.get(request.getCommandName());
            if (command != null) {
                try {
                    return command.execute(request, collectionManager);
                } catch (Exception e) {
                    return new Response("Error executing command: " + e.getMessage(), null);
                }
            }
            return new Response("Unknown command: " + request.getCommandName(), null);
        }
    }
    ✓ • 303ms

## Activity

    $ Get-Content -Encoding utf8 src/main/java/org/example/client/Client.java; Get-Content -Encoding utf8 src/main/java/org/example/client/ScriptExecutor.java; Get-Content -Encoding utf8 src/main/java/org/example/common/Request.java
    package org.example.client;
    
    import org.example.common.Factory.TicketFactory;
    import org.example.common.Request;
    import org.example.common.Response;
    
    import java.io.*;
    import java.net.InetSocketAddress;
    import java.nio.ByteBuffer;
    import java.nio.channels.SocketChannel;
    import java.util.ArrayList;
    import java.util.Scanner;
    
    public class Client {
    
        private static final String SERVER_ADDRESS = "localhost";
        private static int SERVER_PORT;
        private static String login = "";
        private static String password = "";
        private static TicketFactory ticketFactory = new TicketFactory();
    
    
        public static void main(String[] args) throws InterruptedException {
            Scanner scanner = new Scanner(System.in);
            ScriptExecutor scriptExecutor = new ScriptExecutor();
            SERVER_PORT = Integer.parseInt(args[0]);
    
            while (true) {
                try (SocketChannel socket = SocketChannel.open()) {
                    socket.connect(new InetSocketAddress(SERVER_ADDRESS, SERVER_PORT));
                    System.out.println("Connected to server at " + SERVER_ADDRESS + ":" + SERVER_PORT);
    
                    while (true) {
                        try {
                            System.out.print("Enter command: ");
                            String command = scanner.nextLine().trim();
    
                            if (command.equalsIgnoreCase("exit")) {
                                System.out.println("Exiting...");
                                return;
                            }
    
                            boolean handled = handleCommand(command, scanner, socket, scriptExecutor);
                            if (!handled) {
                                System.out.println("Failed to handle command.");
                            }
    
                        } catch (Exception e) {
                            System.out.println("Error during command processing: " + e.getMessage());
                        }
                    }
    
                } catch (IOException e) {
                    System.err.println("Connection error: " + e.getMessage());
                    Thread.sleep(1000);
                }
            }
        }
    
        private static boolean handleCommand(String command, Scanner scanner, SocketChannel socket, ScriptExecutor scriptExecutor) {
            Request request;
    
            try {
                switch (command.toLowerCase()) {
                    case "login":
                    case "register":
                        System.out.print("Enter login: ");
                        login = scanner.nextLine().trim();
                        System.out.print("Enter password: ");
                        password = scanner.nextLine().trim();
    
                        request = new Request(command, new String[]{login, password}, null);
                        break;
    
                    case "logout":
                        login = "";
                        password = "";
                        System.out.println("Logged out successfully.");
                        return true;
    
                    case "add":
                    case "add_if_max":
                    case "remove_greater":
                    case "remove_lower":
                        request = new Request(command, new String[0], ticketFactory.createTicket()
                        );
                        break;
                    case "update":
                        System.out.print("Enter ID of the ticket to update: ");
                        long id = Long.parseLong(scanner.nextLine());
                        request = new Request(command, new String[]{String.valueOf(id)}, ticketFactory.createTicket()
                        );
                        break;
    
                    case "remove_by_id":
                        System.out.print("Enter ID of the ticket to remove: ");
                        String removeIdStr = scanner.nextLine();
                        request = new Request(command, new String[]{removeIdStr}, null);
                        break;
    
                    case "filter_starts_with":
                        System.out.print("Enter name prefix to filter: ");
                        String prefix = scanner.nextLine();
                        request = new Request(command, new String[]{prefix}, null);
                        break;
    
                    case "execute_script":
                        System.out.print("Enter script file path: ");
                        String filePath = scanner.nextLine();
                        scriptExecutor.executeScript(filePath, socket);
                        return true;
    
                    default:
                        request = new Request(command, new String[0], null);
                        break;
                }
    
                sendRequest(request, socket);
                Response response = receiveResponse(socket);
                System.out.println("Server response: " + response.getMessage());
                if (response.getData() != null) {
                    System.out.println("Server data: " + response.getData());
                }
    
                return true;
    
            } catch (Exception e) {
                System.err.println("Failed to process command: " + e.getMessage());
                return false;
            }
        }
    
        protected static void sendRequest(Request request, SocketChannel socket) throws IOException {
            request.setLogin(login);
            request.setPassword(password);
            try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                 ObjectOutputStream objectStream = new ObjectOutputStream(byteStream)) {
    
                objectStream.writeObject(request);
                objectStream.flush();
                byte[] data = byteStream.toByteArray();
    
                ByteBuffer buffer = ByteBuffer.wrap(data);
                int chunkSize = 8192;
    
                while (buffer.hasRemaining()) {
                    int length = Math.min(chunkSize, buffer.remaining());
                    ByteBuffer chunk = ByteBuffer.wrap(data, buffer.position(), length);
                    int bytesWritten = socket.write(chunk);
                    buffer.position(buffer.position() + bytesWritten);
                }
            }
        }
    
        public static Response receiveResponse(SocketChannel socket) throws IOException, InterruptedException, ClassNotFoundException {
            ByteBuffer buffer1 = dynamicBuffer(socket);
    
    
            ByteArrayInputStream bi = new ByteArrayInputStream(buffer1.array());
            ObjectInputStream oi = new ObjectInputStream(bi);
            Response response = (Response) oi.readObject();
            System.out.println("Получено сообщение от сервера: " + response);
            return response;
        }
    
        public static ByteBuffer dynamicBuffer(SocketChannel server) throws IOException, InterruptedException {
            Thread.sleep(200);
            ArrayList<ByteBuffer> bufferList = new ArrayList<>();
            for (int i = 0; i < 10000000; i++) {
                ByteBuffer buffer = ByteBuffer.allocate(8192);
    
    
                int bytesRead = server.read(buffer);
                buffer.flip();
                if (bytesRead > 0) {
                    bufferList.add(buffer);
                }
    
                if (bytesRead < buffer.capacity()) {
                    break;
                }
            }
            ByteBuffer bigBuffer = ByteBuffer.allocate(bufferList.size() * 8192);
            for (ByteBuffer byteBuffer : bufferList) {
    import org.example.common.Factory.Factory;
    import org.example.common.Factory.ScriptTicketFactory;
    import org.example.common.Request;
    import org.example.common.Response;
    import org.example.common.model.AbstractTicket;
    import org.example.common.model.Ticket;
    
    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import java.nio.channels.SocketChannel;
    import java.util.Stack;
    
    /**
     * Класс {@code ScriptExecutor} отвечает за выполнение команд из скриптов (текстовых файлов).
     * <p>Поддерживает обработку вложенных скриптов, предотвращает рекурсивные вызовы одного и того же скрипта.</p>
     */
    public class ScriptExecutor {
    
        /**
         * Множество путей к уже выполненным скриптам, чтобы предотвратить рекурсию.
         * Выполняет команды из указанного файла-скрипта.
         * <p>Поддерживает команды, требующие дополнительного ввода (например, {@code add}, {@code update}), считывая данные из файла.</p>
         *
         * @param filePath путь к скрипту
         * @param socket   активный {@link SocketChannel} для отправки запросов на сервер
         */
        public void executeScript(String filePath, SocketChannel socket) {
            if (executedScripts.contains(filePath)) {
                System.out.println("Recursion detected. Skipping script: " + filePath);
                return;
            }
    
            executedScripts.push(filePath);
    
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                Factory factory = new ScriptTicketFactory(reader);
                String line;
    
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;
    
                    String command = line.split(" ")[0];
                    System.out.println("Executing command from script: " + command);
    
                    Request request = null;
    
                    switch (command) {
                        case "add":
                        case "add_if_max":
                            AbstractTicket ticket = factory.createTicket();
                            request = new Request(command, new String[0], ticket);
                            break;
    
                        case "update":
                            String idLine = reader.readLine();
                            long id = Long.parseLong(idLine.trim());
                            Ticket updatedTicket = (Ticket) factory.createTicket();
                            request = new Request(command, new String[]{String.valueOf(id)}, updatedTicket);
                            break;
    
                        case "remove_by_id":
                            String removeId = reader.readLine().trim();
                            request = new Request(command, new String[]{removeId}, null);
                            break;
    
                        case "remove_greater":
                        case "remove_lower":
                            Ticket compareTicket = (Ticket) factory.createTicket();
                            request = new Request(command, new String[0], compareTicket);
                            break;
    
                        case "execute_script":
                            String nestedPath = reader.readLine().trim();
                            executeScript(nestedPath, socket);  // рекурсивный вызов
                            continue;
    
                        default:
                            request = new Request(command, new String[0], null);
                    }
    
                    if (request != null) {
                        Client.sendRequest(request, socket);
                        Response response = Client.receiveResponse(socket);
                        System.out.println("Server response: " + response.getMessage());
                    }
                }
    
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error executing script: " + e.getMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                if (!executedScripts.isEmpty() && executedScripts.peek().equals(filePath)) {
                    executedScripts.pop();
                } else {
                    executedScripts.remove(filePath);
                }
            }
        }
    }
    package org.example.common;
    
    import org.example.common.model.AbstractTicket;
    import org.example.common.model.Ticket;
    
    import java.io.Serializable;
    
    /**
     * Класс {@code Request} представляет собой объект-запрос, отправляемый клиентом серверу.
     * <p>Содержит имя команды, аргументы, объект {@link Ticket} (опционально) и данные аутентификации.</p>
     * <p>Является сериализуемым, чтобы можно было передавать по сети.</p>
     */
    public class Request implements Serializable {
        private static final long serialVersionUID = 5760575944040770153L;
    
        /**
         * Имя команды, которую необходимо выполнить на сервере.
         */
        private String commandName;
    
        /**
         * Аргументы команды, если они требуются.
         */
        private String[] args;
    
        /**
         * Объект {@link Ticket}, если команда предполагает передачу элемента коллекции.
         */
        private Ticket ticket;
    
        /**
         * Логин пользователя для аутентификации (может быть null).
         */
        private String login;
    
        /**
         * Пароль пользователя для аутентификации (может быть null).
         */
        private String password;
    
        /**
         * Создаёт новый объект запроса без данных аутентификации.
         *
         * @param commandName имя команды
         * @param args массив аргументов
         * @param ticket объект {@link Ticket}, связанный с командой (может быть {@code null})
         */
        public Request(String commandName, String[] args, AbstractTicket ticket) {
            this.commandName = commandName;
            this.args = args;
            this.ticket = (Ticket) ticket;
        }
    
        /**
         * Создаёт новый объект запроса с данными аутентификации.
         *
         * @param commandName имя команды
         * @param args массив аргументов
         * @param ticket объект {@link Ticket}, связанный с командой (может быть {@code null})
         * @param login логин пользователя
         * @param password пароль пользователя
         */
        public Request(String commandName, String[] args, Ticket ticket, String login, String password) {
            this.commandName = commandName;
            this.args = args;
            this.ticket = (Ticket) ticket;
            this.login = login;
            this.password = password;
        }
    
        /**
         * Возвращает имя команды.
         *
         * @return имя команды
         */
        public String getCommandName() {
            return commandName;
        }
    
        /**
         * Возвращает массив аргументов команды.
         *
         * @return аргументы команды
         */
        public String[] getArgs() {
            return args;
        }
    
        /**
         * Возвращает объект {@link Ticket}, если он присутствует.
         *
         * @return объект {@link Ticket} или {@code null}
         */
        public Ticket getTicket() {
            return ticket;
        }
    
        /**
         * Устанавливает объект {@link Ticket}.
         *
         * @param ticket новый объект {@link Ticket}
         */
        public void setTicket(Ticket ticket) {
            this.ticket = (Ticket) ticket;
        }
    
        /**
         * Возвращает логин пользователя.
         *
         * @return логин или {@code null}, если не установлен
         */
        public String getLogin() {
            return login;
        }
    
        /**
         * Устанавливает логин пользователя.
         *
         * @param login новый логин
         */
        public void setLogin(String login) {
            this.login = login;
        }
    
        /**
         * Возвращает пароль пользователя.
         *
         * @return пароль или {@code null}, если не установлен
         */
        public String getPassword() {
            return password;
        }
    
        /**
         * Устанавливает пароль пользователя.
         *
         * @param password новый пароль
         */
        public void setPassword(String password) {
            this.password = password;
        }
    
        /**
         * Возвращает строковое представление объекта запроса (без пароля в целях безопасности).
         *
         * @return строковое представление запроса
         */
        @Override
        public String toString() {
            return "Request{" +
                    "commandName='" + commandName + '\'' +
                    ", args=" + String.join(",", args) +
                    ", ticket=" + ticket +
                    ", login='" + login + '\'' +
                    '}';
        }
    }
    ✓ • 452ms

## Activity

    $ Get-Content -Encoding utf8 src/main/java/org/example/server/manager/CollectionManager.java; Get-Content -Encoding utf8 src/main/java/org/example/server/manager/DataBaseManager.java; Get-Content -Encoding utf8 src/main/java/org/example/server/Server.java; Get-Content -Encoding utf8 src/main/java/org/example/server/command/HelpCommand.java; git status --short
    package org.example.server.manager;
    
    
    import org.example.client.TicketInput;
    import org.example.common.model.Ticket;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    
    import java.time.LocalDateTime;
    import java.util.Iterator;
    import java.util.LinkedHashSet;
    
    
    public class CollectionManager {
    
        private final LocalDateTime timeOfInitial = LocalDateTime.now();
    
        private LinkedHashSet<Ticket> collection = new LinkedHashSet<>();
    
        private static final Logger logger = LoggerFactory.getLogger(CollectionManager.class);
    
    
    
        public CollectionManager() {
        }
    
        public void add(Ticket ticket) {
    
            synchronized (collection) {
                collection.add(ticket);
                logger.info("Added ticket: {}", ticket);
            }
        }
    
        public void update(Ticket ticket) {
    
            synchronized (collection) {
                collection.remove(ticket);
                collection.add(ticket);
                logger.info("Updated ticket: {}", ticket);
            }
        }
    
        public void delete(Ticket ticket) {
            synchronized (collection) {
                boolean removed = collection.remove(ticket);
                if (removed) {
                    logger.info("Deleted ticket: {}", ticket);
                } else {
                    logger.warn("Attempted to delete non-existent ticket: {}", ticket);
                }
            }
        }
    
        public boolean removeById(long id) {
            synchronized (collection) {
                Iterator<Ticket> iterator = collection.iterator();
                while (iterator.hasNext()) {
                    Ticket ticket = iterator.next();
                    if (ticket.getId() == id) {
                        iterator.remove(); // Используем iterator.remove() для безопасного удаления во время итерации
                        logger.info("Removed ticket with ID {} from collection.", id);
                        return true;
                    }
                }
                logger.warn("Attempted to remove non-existent ticket with ID {}.", id);
                return false;
            }
        }
        public void remove_lower(Ticket ticket) {
            synchronized (collection) {
                Ticket referenceTicket = TicketInput.generateTicket();
                logger.info("Reference ticket for comparison: {}", referenceTicket);
    
                int removedCount = 0;
                Iterator<Ticket> iterator = collection.iterator();
                while (iterator.hasNext()) {
                    Ticket currentTicket = iterator.next();
                    if (referenceTicket.compareTo(currentTicket) > 0) {
                        iterator.remove();
                        removedCount++;
                        logger.info("Removed ticket: {}", currentTicket);
                        System.out.println("Deleted ticket: " + currentTicket);
                    }
                }
    
                if (removedCount == 0) {
                    System.out.println("No tickets were lower than the reference ticket.");
                    logger.info("No tickets were removed.");
                } else {
                    System.out.println("Total tickets removed: " + removedCount);
                    logger.info("Total tickets removed: {}", removedCount);
                }
            }
        }
    
        public void remove_greater(Ticket ticket) {
            synchronized (collection) {
                Ticket referenceTicket = TicketInput.generateTicket();
                int removedCount = 0;
    
                Iterator<Ticket> iterator = collection.iterator();
                while (iterator.hasNext()) {
                    Ticket currentTicket = iterator.next();
                    if (referenceTicket.compareTo(currentTicket) < 0) {
                        iterator.remove();
                        removedCount++;
                        logger.info("Removed ticket: {}", currentTicket);
                        System.out.println("Ticket deleted: " + currentTicket);
                    }
                }
    
                if (removedCount == 0) {
                    System.out.println("No tickets were greater than the reference ticket.");
                    logger.info("No tickets were removed. All are less than the reference.");
                } else {
                    System.out.println("Total tickets removed: " + removedCount);
                    logger.info("Total tickets removed: {}", removedCount);
                }
            }
        }
    
        public LocalDateTime getTimeOfInitial() {
    
            return timeOfInitial;
    
        }
    
        public LinkedHashSet<Ticket> getCollection() {
            synchronized (collection) {
                return new LinkedHashSet<>(collection); // Возвращаем копию для безопасности
            }
        }
    
        public Ticket getById(long id) {
    
            Ticket found = collection.stream()
    
                    .filter(t -> t.getId() == id)
    
                    .findFirst()
    
                    .orElse(null);
    
            if (found != null) {
    
                logger.info("Found ticket by ID {}: {}", id, found);
    
            } else {
    
                logger.warn("No ticket found with ID: {}", id);
    
            }
    
            return found;
    
        }
    
        public void setCollection(LinkedHashSet<Ticket> newCollection) {
            synchronized (collection) {
                collection.clear();
                collection.addAll(newCollection);
                logger.info("Collection replaced with new set (size: {})", newCollection.size());
            }
        }
    
    
        public boolean add_if_max(Ticket ticket) {
    
            if (getCollection().isEmpty()) {
    
                add(ticket);
    
                logger.info("Added ticket with price: {}", ticket.getPrice());
    
            }
            int maxPrice = getCollection().stream()
                    .mapToInt(Ticket::getPrice)
                    .max()
                    .orElse(Integer.MIN_VALUE);
    
            if (ticket.getPrice() > maxPrice) {
    
                add(ticket);
    
                logger.info("Added ticket with price: {}", ticket.getPrice());
    
            } else {
    
                logger.info("Not added ticket with price: {} <= {}", ticket.getPrice(), maxPrice);
    
            }
    
            return false;
        }
    
        public int clear(String userLogin) {
            synchronized (collection) { // Синхронизация коллекции
                int initialSize = collection.size();
                int userId = DataBaseManager.getUserId(userLogin); // Получаем ID пользователя по его логину
    
                if (userId == -1) {
                    logger.warn("Attempted to remove tickets for non-existent user login: {}", userLogin);
                    return 0; // Пользователь не найден, удалять нечего
                }
    
                // Используем Iterator для безопасного удаления элементов во время итерации
                Iterator<Ticket> iterator = collection.iterator();
                int removedCount = 0;
                while (iterator.hasNext()) {
                    Ticket ticket = iterator.next();
                    // Проверяем, совпадает ли ownerId билета с ID текущего пользователя
                    if (ticket.getOwnerId() == userId) {
                        iterator.remove(); // Безопасное удаление из LinkedHashSet
                        removedCount++;
                        logger.debug("Removed in-memory ticket with ID {} belonging to user ID {}", ticket.getId(), userId);
                    }
                }
                logger.info("Removed {} tickets from in-memory collection for user {}.", removedCount, userLogin);
                return removedCount;
            }
        }
        }
    package org.example.server.manager;
    
    import java.sql.*;
    import java.time.ZoneId;
    
    import org.example.common.model.Coordinates;
    import org.example.common.model.Ticket;
    import org.example.common.model.Venue;
    import org.example.common.model.enums.TicketType;
    import org.example.common.model.enums.VenueType;
    import org.example.server.handlers.HashHandler;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    
    
    public class DataBaseManager {
        private static DataBaseManager instance;
        private static final String GET_USERS = "SELECT * FROM lab7_users";
        private static final Logger logger = LoggerFactory.getLogger(DataBaseManager.class);
    
        //private static final String DB_URL = "jdbc:postgresql://pg:5432/studs";
        //private static final String DB_USER = "s465729";
        //private static final String DB_PASSWORD = "TMnULcCn63BZLOCt";
    
        private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        private static final String DB_USER = "postgres";
        private static final String DB_PASSWORD = "kdseum";
    
        private static final String INSERT_TICKET = "INSERT INTO ticket (name, coordinates_x, coordinates_y, creation_date, price, discount, tickettype, venuename, capacity, venuetype, owner_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
    
        private static final String GET_OWNER_BY_KEY = "SELECT owner_id FROM ticket WHERE id = ?";
        private static final String REMOVE_TICKET = "DELETE FROM ticket WHERE id = ? AND owner_id=?";
        private static final String CLEAR_TICKET = "DELETE FROM ticket WHERE owner_id=?";
        private static final String UPDATE_TICKET_BY_ID = "UPDATE ticket SET " +
                "name = ?, coordinates_x = ?, coordinates_y = ?, creation_date = ?, price = ?," +
                " discount = ?, tickettype = ?, venuename = ?, capacity = ?, venuetype = ?, owner_id = ? WHERE id = ? AND owner_id=?";
    
        public DataBaseManager() {
        }
    
        public static DataBaseManager getInstance() {
            if (instance == null) {
                instance = new DataBaseManager();
            }
            return instance;
        }
    
        private static Connection createConnection() throws SQLException {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }
    
        public static void connectToDataBase() {
            try (Connection connection = createConnection()) {
                checkAndCreateTables(connection);
                System.out.println("Database connection established. Tables checked/created.");
            } catch (SQLException e) {
                System.out.println("Error while connecting to database");
                System.out.println(e.getMessage());
                System.exit(-1);
            }
        }
    
        private static void checkAndCreateTables(Connection connection) throws SQLException {
            try (Statement stmt = connection.createStatement()) {
    
                stmt.executeUpdate("""
                            CREATE TABLE IF NOT EXISTS lab7_users (
                                user_id SERIAL PRIMARY KEY,
                                login VARCHAR(50) UNIQUE NOT NULL,
                                password VARCHAR(64) NOT NULL
                            );
                        """);
    
                stmt.executeUpdate("""
                            CREATE TABLE IF NOT EXISTS ticket (
                                id SERIAL PRIMARY KEY,
                                name VARCHAR(100) NOT NULL,
                                coordinates_x FLOAT NOT NULL,
                                coordinates_y BIGINT NOT NULL,
                                creation_date TIMESTAMP NOT NULL,
                                price INTEGER NOT NULL CHECK (price > 0),
                                discount DOUBLE PRECISION,
                                tickettype VARCHAR(20) NOT NULL,
                                venuename VARCHAR(100) NOT NULL,
                                capacity INTEGER NOT NULL CHECK (capacity > 0),
                                venuetype VARCHAR(20) NOT NULL,
                                owner_id INTEGER NOT NULL,
                                venueid SERIAL NOT NULL,
                                FOREIGN KEY (owner_id) REFERENCES lab7_users(user_id) ON DELETE CASCADE
                            );
                        """);
    
                System.out.println("Tables checked/created.");
            }
        }
    
        public static void getUsers() {
            try (Connection connection = createConnection();
                 PreparedStatement getStatement = connection.prepareStatement(GET_USERS)) {
                ResultSet rs = getStatement.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getInt("user_id") + " " + rs.getString("login"));
                }
            } catch (Exception e) {
                logger.error("Error getting users: {}", e.getMessage());
            }
        }
    
        private static final String GET_USER_BY_USERNAME = "SELECT * FROM lab7_users WHERE login = ?";
    
        public static int getUserId(String login) {
            try (Connection connection = createConnection();
                 PreparedStatement getStatement = connection.prepareStatement(GET_USER_BY_USERNAME)) {
                getStatement.setString(1, login);
                ResultSet rs = getStatement.executeQuery();
                if (rs.next()) {
                    return rs.getInt("user_id");
                }
                return -1;
            } catch (Exception e) {
                logger.error("Error getting user ID for login {}: {}", login, e.getMessage());
                return -1;
            }
        }
    
        public static final String INSERT_USER_REQUEST = "INSERT INTO lab7_users (login, password) VALUES (?,?)";
    
        public static boolean insertUser(String login, String password) {
            if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
                logger.error("Invalid login or password");
                return false;
            }
    
            String hashedPassword = HashHandler.encryptString(password);
            String sql = "INSERT INTO lab7_users (login, password) VALUES (?, ?)";
    
            try (Connection connection = createConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, login);
                stmt.setString(2, hashedPassword);
                return stmt.executeUpdate() > 0;
            } catch (SQLException e) {
                logger.error("Error creating user: {}", e.getMessage());
                return false;
            }
        }
    
    
        public static boolean checkUser(String login, String password) {
            if (login == null || password == null) return false;
    
            String sql = "SELECT password FROM lab7_users WHERE login = ?";
            try (Connection connection = createConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, login);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String storedHash = rs.getString("password");
                        String inputHash = HashHandler.encryptString(password);
                        boolean result = storedHash.equals(inputHash);
                        if (result) logger.info("User '{}' logged in successfully", login);
                        else logger.warn("Incorrect password for user '{}'", login);
                        return result;
                    }
                }
            } catch (SQLException e) {
                logger.error("Error checking user: {}", e.getMessage());
            }
            return false;
        }
    
        public static boolean insertTicket(Ticket ticket, String login) {
            if (ticket == null || login == null) {
                logger.error("Ticket or login is null");
                return false;
            }
            int userId = getUserId(login);
            if (userId == -1) {
                logger.error("User not found: {}", login);
                return false;
            }
            try (Connection connection = createConnection();
                 PreparedStatement statement = connection.prepareStatement(INSERT_TICKET)) {
                statement.setInt(11, userId);
                insertTicketDataIntoStatement(ticket, statement);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        long newId = rs.getLong(1);
                        ticket.setId(newId);
                        ticket.setOwnerId(userId);
                        logger.info("Successfully inserted ticket with ID: {}", newId);
                        return true;
                    }
                    return false;
                }
            } catch (SQLException e) {
                logger.error("Database error while inserting ticket: {}", e.getMessage());
                if (e.getSQLState().equals("23503")) {
                    logger.error("User with ID {} does not exist or ticket data is invalid for user {}", userId, login);
                }
                return false;
            }
        }
    
        private static void insertTicketDataIntoStatement(Ticket ticket, PreparedStatement statement) {
            try {
                statement.setString(1, ticket.getName());
                statement.setFloat(2, ticket.getCoordinates().getX());
                statement.setLong(3, ticket.getCoordinates().getY());
                statement.setTimestamp(4, Timestamp.from(ticket.getCreationDate().toInstant()));
                statement.setInt(5, ticket.getPrice());
                statement.setDouble(6, ticket.getDiscount());
                statement.setString(7, String.valueOf(ticket.getType()));
                statement.setString(8, ticket.getVenue().getVenueName());
                statement.setInt(9, ticket.getVenue().getCapacity());
                statement.setString(10, String.valueOf(ticket.getVenue().getType()));
            } catch (SQLException e) {
                logger.error("Couldn't insert data into statement. Reason: {}", e.getMessage());
            }
        }
    
        private static Ticket extractTicketFromEntry(ResultSet rs) throws SQLException {
            Ticket ticket = new Ticket();
            ticket.setId(rs.getLong("id"));
            ticket.setName(rs.getString("name"));
            ticket.setCoordinates(new Coordinates(rs.getFloat("coordinates_x"), rs.getLong("coordinates_y")));
            ticket.setCreationDate(rs.getTimestamp("creation_date").toInstant().atZone(ZoneId.systemDefault()));
            ticket.setPrice(rs.getInt("price"));
            ticket.setDiscount(rs.getDouble("discount"));
            ticket.setType(TicketType.valueOf(rs.getString("tickettype")));
            Venue venue = new Venue();
            venue.setName(rs.getString("venuename"));
            venue.setCapacity(rs.getInt("capacity"));
            venue.setType(VenueType.valueOf(rs.getString("venuetype")));
            ticket.setVenue(venue);
            ticket.setOwnerId(rs.getInt("owner_id"));
            return ticket;
        }
    
        public static void getDataFromDatabase(CollectionManager collectionManager) {
            try (Connection connection = createConnection();
                 Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery("SELECT * FROM ticket");
                while (rs.next()) {
                    try {
                        Ticket ticket = extractTicketFromEntry(rs);
                        collectionManager.add(ticket);
    
                    } catch (Exception e) {
                        System.out.println("Invalid entry in DB. Reason: " + e.getMessage());
                    }
                }
            } catch (SQLException e) {
                System.out.println("Couldn't load data from DB. Reason: " + e.getMessage());
                System.exit(-1);
            }
        }
    
        public static boolean updateTicketById(int id, Ticket ticket, String login) {
            int userId = getUserId(login);
            int ownerId = getOwnerId(String.valueOf(id));
            if (ownerId == -1) {
                logger.error("Ticket {} not found", id);
                return false;
            }
            if (userId != ownerId) {
                logger.error("User {} is not the owner of ticket {}", login, id);
                return false;
            } else {
                try (Connection connection = createConnection();
                     PreparedStatement statement = connection.prepareStatement(UPDATE_TICKET_BY_ID)) {
                    insertTicketDataIntoStatement(ticket, statement);
                    statement.setInt(11, userId);
                    statement.setInt(12, id);
                    statement.setInt(13, userId); // This parameter was set previously, left for old code consistency
                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        logger.info("Ticket {} updated successfully by user {}", id, login);
                        return true;
                    } else {
                        logger.error("Ticket {} not found or not updated", id);
                        return false;
                    }
                } catch (SQLException e) {
                    logger.error("Database error while updating ticket {}: {}", id, e.getMessage());
                    return false;
                }
            }
        }
    
        public static boolean removeTicketByName(String login, String key) {
            int userId = getUserId(login);
            int ownerId = getOwnerId(key);
    
            logger.info("DEBUG: removeTicketByName called. User login: {}, Ticket ID (key): {}", login, key);
            logger.info("DEBUG: Current User ID (from login '{}'): {}", login, userId);
            logger.info("DEBUG: Ticket Owner ID (for ticket ID '{}'): {}", key, ownerId);
            logger.info("DEBUG: Is User ID ({}) == Ticket Owner ID ({})? -> {}", userId, ownerId, (userId == ownerId));
            if (userId == getOwnerId(key)) {
                try (Connection connection = createConnection();
                     PreparedStatement statement = connection.prepareStatement(REMOVE_TICKET)) {
                    statement.setInt(1, Integer.parseInt(key));
                    statement.setInt(2, userId);
    
                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        return true;
                    } else {
                        System.out.println("Not found");
                        return false;
                    }
                } catch (SQLException e) {
                    System.out.println("Couldn't remove ticket. Reason: " + e.getMessage());
                    return false;
                }
            } else {
                return false;
            }
        }
    
        public static int getOwnerId(String key) {
            try (Connection connection = createConnection();
                 PreparedStatement getStatement = connection.prepareStatement(GET_OWNER_BY_KEY)) {
                getStatement.setLong(1, Long.parseLong(key));
                ResultSet rs = getStatement.executeQuery();
                while (rs.next()) {
                    return rs.getInt("owner_id");
                }
                return -1;
            } catch (Exception e) {
                return -2;
            }
        }
    
        public static boolean clear(String login) {
            int userId = getUserId(login);
    
            try (Connection connection = createConnection();
                 PreparedStatement statement = connection.prepareStatement(CLEAR_TICKET)) {
                statement.setInt(1, userId);
                int rowsDeleted = statement.executeUpdate();
                logger.info("Deleted {} tickets for user {}", rowsDeleted, login);
                return rowsDeleted > 0;
            } catch (SQLException e) {
                logger.error("Couldn't clear tickets for user {}. Reason: {}", login, e.getMessage());
                return false;
            }
        }
    }
    
    package org.example.server;
    
    
    import org.example.server.manager.CollectionManager;
    import org.example.server.manager.CommandManager;
    import org.example.common.Request;
    import org.example.common.Response;
    import org.example.server.manager.DataBaseManager;
    
    import java.io.*;
    import java.net.InetAddress;
    import java.net.InetSocketAddress;
    import java.net.SocketException;
    import java.nio.ByteBuffer;
    import java.nio.channels.*;
    import java.util.*;
    import java.util.concurrent.*;
    
    
    public class Server {
    
        private final CollectionManager collectionManager;
        private final CommandManager commandManager;
        private final Set<String> authorizedUsers = Collections.synchronizedSet(new HashSet<>());
    
    
        // Пул потоков для чтения запросов
        private final ExecutorService readingPool = Executors.newCachedThreadPool();
        // Пул потоков для отправки ответов
        private final ExecutorService sendingPool = Executors.newFixedThreadPool(10);
    
        public Server() {
            this.collectionManager = new CollectionManager();
            this.commandManager = new CommandManager(collectionManager);
        }
    
        public void start(String[] args) throws IOException {
            DataBaseManager.connectToDataBase();
            DataBaseManager.getDataFromDatabase(collectionManager);
    
            int port = Integer.parseInt(args[0]);
    
    
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.bind(new InetSocketAddress(InetAddress.getByName("localhost"), port));
    
            Selector selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    
            System.out.println("TCP server is running on port " + port);
    
            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
    
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isAcceptable()) {
                        handleAccept(key, selector);
                    } else if (key.isReadable()) {
                        handleRead(key, selector);
                    }
                    keyIterator.remove();
                }
            }
        }
    
        private void handleAccept(SelectionKey key, Selector selector) throws IOException {
            ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
            SocketChannel clientChannel = serverChannel.accept();
            clientChannel.configureBlocking(false);
            clientChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("New connection from " + clientChannel.getRemoteAddress());
        }
    
        private void handleRead(SelectionKey key, Selector selector) throws IOException {
            SocketChannel clientChannel = (SocketChannel) key.channel();
    
            // Передаем обработку чтения в cached thread pool
            readingPool.submit(() -> {
                ByteArrayOutputStream baos = (ByteArrayOutputStream) key.attachment();
    
                if (baos == null) {
                    baos = new ByteArrayOutputStream();
                    key.attach(baos);
                }
    
                ByteBuffer buffer = ByteBuffer.allocate(8192);
    
                try {
                    int bytesRead = clientChannel.read(buffer);
                    if (bytesRead == -1) {
                        System.err.println("Client " + clientChannel.getRemoteAddress() + " disconnected");
                        clientChannel.close();
                        key.cancel();
                        return null;
                    }
    
                    if (bytesRead > 0) {
                        buffer.flip();
                        baos.write(buffer.array(), 0, buffer.limit());
                        buffer.clear();
    
                        byte[] data = baos.toByteArray();
                        if (data.length > 0) {
                            try (ByteArrayInputStream bi = new ByteArrayInputStream(data);
                                 ObjectInputStream oi = new ObjectInputStream(bi)) {
    
                                Request request = (Request) oi.readObject();
                                System.out.println("Received request from client: " + clientChannel.getRemoteAddress());
    
                                baos.reset();
                                key.attach(null);
    
                                // Обработку запроса выполняем в новом потоке
                                Thread processingThread = new Thread(() -> {
                                    Response response = processRequest(request, clientChannel);
    
                                    // Отправку ответа выполняем в fixed thread pool
                                    sendingPool.submit(() -> {
                                        try {
                                            sendResponse(clientChannel, response);
                                            clientChannel.register(selector, SelectionKey.OP_READ);
                                        } catch (IOException e) {
                                            System.err.println("Error sending response: " + e.getMessage());
                                        }
                                    });
                                });
    
                                processingThread.start();
    
                            } catch (StreamCorruptedException | EOFException e) {
                                // Waiting for more data
                            } catch (ClassNotFoundException e) {
                                System.err.println("Unknown class received: " + e.getMessage());
                                sendingPool.submit(() -> {
                                    try {
                                        sendResponse(clientChannel, new Response("Deserialization error: " + e.getMessage(), null));
                                    } catch (IOException ex) {
                                        System.err.println("Error sending error response: " + ex.getMessage());
                                    }
                                });
                            }
    
                        }
                    }
                } catch (SocketException e) {
                    System.err.println("Connection reset: " + e.getMessage());
                    try {
                        clientChannel.close();
                        key.cancel();
                    } catch (IOException ex) {
                        System.err.println("Error closing channel: " + ex.getMessage());
                    }
                } catch (IOException e) {
                    System.err.println("IO error during reading: " + e.getMessage());
                }
                return null;
            });
        }
    
        private Response processRequest(Request request, SocketChannel clientChannel) {
            String login = request.getLogin();
            String password = request.getPassword();
            String command = request.getCommandName();
    
            if ("logout".equalsIgnoreCase(command)) {
                if (authorizedUsers.remove(login)) {
                    return new Response("Logged out successfully.", null);
                } else {
                    return new Response("You are not logged in.", null);
                }
            }
    
            if ("login".equalsIgnoreCase(command)) {
                if (DataBaseManager.checkUser(login, password)) {
                    authorizedUsers.add(login);
                    return new Response("You are logged in!", null);
                } else {
                    return new Response("Invalid login or password.", null);
                }
            }
    
            if ("register".equalsIgnoreCase(command)) {
                if (DataBaseManager.insertUser(login, password)) {
                    authorizedUsers.add(login);
                    return new Response("Registration successful! You are logged in.", null);
                } else {
                    return new Response("Registration failed.", null);
                }
            }
    
            // Проверка авторизации для других команд
            if (!authorizedUsers.contains(login)) {
                return new Response("Please, log in first.", null);
            }
    
            // Выполнение команды
            try {
                Object data = commandManager.doCommand(request, collectionManager);
                return new Response(null, data);
            } catch (Exception e) {
                return new Response("Command execution error: " + e.getMessage(), null);
            }
        }
    
        private void sendResponse(SocketChannel clientChannel, Response response) throws IOException {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 ObjectOutputStream oos = new ObjectOutputStream(baos)) {
    
                oos.writeObject(response);
                oos.flush();
    
                byte[] data = baos.toByteArray();
                ByteBuffer buffer = ByteBuffer.wrap(data);
    
                int chunkSize = 8192;
    
                while (buffer.hasRemaining()) {
                    int length = Math.min(chunkSize, buffer.remaining());
                    ByteBuffer chunkBuffer = ByteBuffer.wrap(data, buffer.position(), length);
    
                    int bytesWritten = clientChannel.write(chunkBuffer);
                    buffer.position(buffer.position() + bytesWritten);
                }
    
                System.out.println("Response sent to client.");
            }
        }
    
        public static void main(String[] args) throws IOException {
    
            Server server = new Server();
            server.start(args);
        }
    
    }
    package org.example.server.command;
    
    import org.example.common.Request;
    import org.example.common.Response;
    import org.example.server.manager.CollectionManager;
    
    /**
     * <p>Команда для отображения справки по всем доступным командам.</p>
     * <p>Выводит список всех команд с кратким описанием.</p>
     */
    public class HelpCommand extends AbstractCommand {
    
        /**
         * Выполняет команду help.
         *
         * @param request объект запроса, содержащий аргументы команды
         * @param collectionManager менеджер коллекции
         * @return объект ответа с описанием доступных команд
         */
        @Override
        public Response execute(Request request, CollectionManager collectionManager) {
            String helpText = (
                    "Доступные команды:\n" +
                            "help : вывести справку по командам\n" +
                            "info : вывести информацию о коллекции\n" +
                            "show : вывести все элементы коллекции\n" +
                            "add {element} : добавить новый элемент\n" +
                            "update id {element} : обновить элемент по id\n" +
                            "remove_by_id id : удалить элемент по id\n" +
                            "clear : очистить коллекцию\n" +
                            "save : сохранить коллекцию в файл\n" +
                            "execute_script file_name : выполнить скрипт из файла\n" +
                            "exit : завершить программу\n" +
                            "add_if_max : добавить элемент в коллекцию, если его значение превышает значение наибольшего элемента коллекции\n" +
                            "remove_greater {element} : удалить элемент, превышающий заданный\n" +
                            "remove_lower {element} : удалить элемент, меньше заданного\n" +
                            "filter_starts_with_name name : вывести элементы, у которых name начинается с заданной подстроки\n" +
                            "print_descending : вывести элементы в порядке убывания\n" +
                            "print_field_descending_discount : вывести значения поля discount в порядке убывания"
            );
    
            logger.info("Help command executed.");
            return new Response(helpText, null);
        }
    }
    ✓ • 423ms

## Assistant

Для переименования добавлю отдельный SQL-запрос, который меняет только название и проверяет владельца билета. Ввод буду проверять и на клиенте, и на сервере. Также обнаружил две проблемы, мешающие проверке: незавершённый метод в `Client` и отсутствующее поле в `ScriptExecutor`; исправлю их.

## Activity

    $ Get-Content -Encoding utf8 src/main/java/org/example/common/model/Ticket.java; Get-Content -Encoding utf8 src/test/java/org/example/common/RequestTest.java; Get-Content -Encoding utf8 src/main/java/org/example/client/Client.java -Tail 25; Get-Command mvn,java,javac -ErrorAction SilentlyContinue
    package org.example.common.model;
    
    import org.example.common.model.enums.TicketType;
    import org.example.common.model.enums.VenueType;
    import org.example.common.model.generator.IdGenerator;
    import org.example.server.manager.CoordinatesValidator;
    import org.example.server.manager.TicketValidator;
    import org.example.server.manager.VenueValidator;
    
    import java.io.Serializable;
    import java.time.ZonedDateTime;
    import java.util.Objects;
    
    /**
     * Представляет билет с уникальным идентификатором, названием, координатами, датой создания,
     * ценой, скидкой, типом и местом проведения.
     * <p>
     * Используется для хранения информации о билетах и генерации XML-представления.
     */
    public class Ticket extends AbstractTicket {
    
        /**
         * Уникальный идентификатор билета. Генерируется автоматически, должен быть > 0.
         */
        private long id;
    
        /**
         * Название билета. Не может быть null или пустым.
         */
        private String name;
    
        /**
         * Координаты, связанные с билетом. Не могут быть null.
         */
        private Coordinates coordinates;
    
        /**
         * Дата и время создания билета. Генерируется автоматически.
         */
        private ZonedDateTime creationDate;
    
        /**
         * Цена билета. Должна быть больше 0.
         */
        private int price;
    
        /**
         * Скидка на билет. Может быть null, значение должно быть больше 0 и не превышать 100.
         */
        private Double discount;
    
        /**
         * Тип билета. Не может быть null.
         */
        private TicketType type;
    
        /**
         * Место проведения мероприятия. Может быть null.
         */
        private Venue venue;
    
        private int ownerId;
    
        /**
         * Конструктор по умолчанию. Создаёт билет с автоматически сгенерированными значениями id и creationDate.
         */
        public Ticket() {
            this.id = IdGenerator.generateId();
            this.name = null;
            this.coordinates = null;
            this.creationDate = ZonedDateTime.now();
            this.price = -1;
            this.discount = null;
            this.type = null;
            this.venue = null;
    
    
        }
    
        /**
         * Полный конструктор на основе строковых параметров. Валидирует значения перед созданием объекта.
         *
         * @param id           строковое представление идентификатора
         * @param name         название билета
         * @param X            координата X
         * @param Y            координата Y
         * @param creationDate дата создания (в формате ISO-8601)
         * @param price        цена
         * @param discount     скидка
         * @param type         тип билета
         * @param venueId      идентификатор места проведения
         * @param venueName    название места проведения
         * @param capacity     вместимость места
         * @param venueType    тип места
         * @throws Exception если одно из значений не прошло валидацию
         */
        public Ticket(String id, String name, String X, String Y, String creationDate, String price, String discount, String type, String venueId, String venueName, String capacity, String venueType) throws Exception {
            TicketValidator.idIsOK(id);
            TicketValidator.inputIsNotEmpty(name, "NAME");
            CoordinatesValidator.coordinateXIsOk(X);
            CoordinatesValidator.coordinateYIsOk(Y);
            TicketValidator.inputIsNotEmpty(creationDate, "DATE");
            TicketValidator.priceIsOk(price);
            TicketValidator.discountIsOk(discount);
            TicketValidator.typeIsOk(type);
            VenueValidator.idIsOk(venueId);
            VenueValidator.nameIsOk(venueName);
            VenueValidator.capacityIsOk(capacity);
            VenueValidator.typeIsOk(venueType);
    
            this.id = Long.parseLong(id);
            this.name = name;
            this.coordinates = new Coordinates(Float.parseFloat(X), Long.parseLong(Y));
            this.creationDate = ZonedDateTime.parse(creationDate);
            this.price = Integer.parseInt(price);
            this.discount = Double.parseDouble(discount);
            this.type = TicketType.valueOf(type);
            this.venue = new Venue(Long.parseLong(id), venueName, Integer.parseInt(capacity), VenueType.valueOf(type));
        }
    
        /**
         * Преобразует объект билета в XML-представление.
         *
         * @return XML-строка, представляющая билет.
         */
        public String toXML() {
            String xmlFormat = "<ticket>" +
                    "<id>" + id + "</id>" +
                    "<name>" + name + "</name>" +
                    "<creationDate>" + creationDate + "</creationDate>" +
                    "<price>" + price + "</price>" +
                    "<discount>" + discount + "</discount>" +
                    coordinates.toXML() +
                    "<ticketType>" + type + "</ticketType>" ;
            if (venue != null) {
                xmlFormat += "<venueId>" + venue.getId() + "</venueId>" +
                        "<venueName>" + venue.getName() + "</venueName>" +
                        "<capacity>" + venue.getCapacity() + "</capacity>" +
                        "<venueType>" + venue.getType() + "</venueType>";
            }
            xmlFormat += "</ticket>";
            return xmlFormat;
        }
    
        // Геттеры и сеттеры с описанием
    
        /**
         * @return идентификатор билета
         */
        public long getId() {
            return id;
        }
    
        /**
         * @param id устанавливает идентификатор билета
         */
        public void setId(long id) {
            this.id = id;
        }
    
        /**
         * @return название билета
         */
        public String getName() {
            return name;
        }
    
        /**
         * @param name устанавливает название билета
         */
        public void setName(String name) {
            this.name = name;
        }
    
        /**
         * @return координаты билета
         */
        public Coordinates getCoordinates() {
            return coordinates;
        }
    
        /**
         * @param coordinates устанавливает координаты
         */
        public void setCoordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
        }
    
        /**
         * @return дата создания билета
         */
        public ZonedDateTime getCreationDate() {
            return creationDate;
        }
    
        /**
         * @param creationDate устанавливает дату создания
         */
        public void setCreationDate(ZonedDateTime creationDate) {
            this.creationDate = creationDate;
        }
    
        /**
         * @return цена билета
         */
        public int getPrice() {
            return price;
        }
    
        /**
         * @param price устанавливает цену билета
         */
        public void setPrice(int price) {
            this.price = price;
        }
    
        /**
         * @return скидка на билет
         */
        public Double getDiscount() {
    
            return discount;
        }
    
        /**
         * @param discount устанавливает скидку
         */
        public void setDiscount(Double discount) {
            this.discount = discount;
        }
    
        /**
         * @return тип билета
         */
        public TicketType getType() {
            return type;
        }
    
        /**
         * @param type устанавливает тип билета
         */
        public void setType(TicketType type) {
            this.type = type;
        }
    
        /**
         * @return место проведения
         */
        public Venue getVenue() {
            return venue;
        }
    
        /**
         * @param venue устанавливает место проведения
         */
        public void setVenue(Venue venue) {
            this.venue = venue;
        }
    
        /**
         * Сравнивает билеты по цене. Null считается меньшим.
         *
         * @param o другой билет
         * @return результат сравнения
         */
        @Override
        public int compareTo(AbstractTicket o) {
            if (!(o instanceof Ticket)) return 0;
            Ticket other = (Ticket) o;
    
            if (this.price < 0 && other.price < 0) return 0;
            if (this.price < 0) return -1;
            if (other.price < 0) return 1;
    
            return Integer.compare(this.price, other.price);
        }
    
        /**
         * Возвращает строковое представление билета.
         *
         * @return строка с информацией о билете
         */
        @Override
        public String toString() {
            return "Ticket{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", coordinates=" + coordinates +
                    ", creationDate=" + creationDate +
                    ", price=" + price +
                    ", discount=" + discount +
                    ", type=" + type +
                    ", venue=" + venue +
                    '}';
        }
    
        /**
         * Проверяет равенство двух объектов Ticket.
         *
         * @param o объект для сравнения
         * @return true, если объекты равны
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Ticket ticket = (Ticket) o;
            return id == ticket.id &&
                    Objects.equals(name, ticket.name) &&
                    Objects.equals(coordinates, ticket.coordinates) &&
                    Objects.equals(creationDate, ticket.creationDate) &&
                    Objects.equals(price, ticket.price) &&
                    Objects.equals(discount, ticket.discount) &&
                    type == ticket.type &&
                    Objects.equals(venue, ticket.venue);
        }
    
        /**
         * Вычисляет хэш-код для объекта Ticket.
         *
         * @return хэш-код
         */
        @Override
        public int hashCode() {
            return Objects.hash(id, name, coordinates, creationDate, price, discount, type, venue);
        }
    
        public int getOwnerId() {
            return ownerId;
        }
    
        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }
    
    
    }
    package org.example.common;
    
    import org.junit.jupiter.api.Test;
    
    import static org.junit.jupiter.api.Assertions.assertNotNull;
    
    class RequestTest {
    
        @Test
        void requestCanBeCreatedWithoutTicket() {
            Request request = new Request("help", new String[0], null);
            assertNotNull(request);
        }
    }
            ArrayList<ByteBuffer> bufferList = new ArrayList<>();
            for (int i = 0; i < 10000000; i++) {
                ByteBuffer buffer = ByteBuffer.allocate(8192);
    
    
                int bytesRead = server.read(buffer);
                buffer.flip();
                if (bytesRead > 0) {
                    bufferList.add(buffer);
                }
    
                if (bytesRead < buffer.capacity()) {
                    break;
                }
            }
            ByteBuffer bigBuffer = ByteBuffer.allocate(bufferList.size() * 8192);
            for (ByteBuffer byteBuffer : bufferList) {
                bigBuffer.put(byteBuffer.array());
            }
    
            System.out.println("Данные прочитаны");
    
            return bigBuffer;
        }
    }
    
    CommandType     Name                                               Version    Source
    -----------     ----                                               -------    ------
    Application     mvn.cmd                                            0.0.0.0    C:\Program Files\Apache\maven\apache-m...
    Application     java.exe                                           17.0.12.0  C:\Program Files\Java\jdk-17\bin\java.exe
    Application     javac.exe                                          17.0.12.0  C:\Program Files\Java\jdk-17\bin\javac...
    
    
    ✓ • 217ms

## Activity

    $ rg executedScripts src/main/java/org/example/client/ScriptExecutor.java; mvn test -q
        private final Stack<String> executedScripts = new Stack<>();
            if (executedScripts.contains(filePath)) {
            executedScripts.push(filePath);
                if (!executedScripts.isEmpty() && executedScripts.peek().equals(filePath)) {
                    executedScripts.pop();
                    executedScripts.remove(filePath);
    ������� ������� ��������: 1251
    �������� � �������.
    [ERROR] Could not create local repository at C:\.m2\repository -> [Help 1]
    [ERROR]
    [ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
    [ERROR] Re-run Maven using the -X switch to enable full debug logging.
    [ERROR]
    [ERROR] For more information about the errors and possible solutions, please read the following articles:
    [ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/LocalRepositoryNotAccessibleException
    ✗ (1) • 2.44s

## Activity

    file changes: Completed · 9 changes
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\client\Client.java
    @@ -4,3 +4,4 @@
     import org.example.common.Request;
    -import org.example.common.Response;
    +import org.example.common.Response;
    +import org.example.common.RenameTicketArguments;
     
    @@ -94,3 +95,13 @@
     
    -                case "remove_by_id":
    +                case "rename_ticket":
    +                    System.out.print("Enter ID of the ticket to rename: ");
    +                    String renameId = scanner.hasNextLine() ? scanner.nextLine() : null;
    +                    System.out.print("Enter new ticket name: ");
    +                    String renameName = scanner.hasNextLine() ? scanner.nextLine() : null;
    +                    RenameTicketArguments rename = RenameTicketArguments.parse(renameId, renameName);
    +                    request = new Request("rename_ticket",
    +                            new String[]{Long.toString(rename.id()), rename.name()}, null);
    +                    break;
    +
    +                case "remove_by_id":
                         System.out.print("Enter ID of the ticket to remove: ");
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\client\ScriptExecutor.java
    @@ -5,3 +5,4 @@
     import org.example.common.Request;
    -import org.example.common.Response;
    +import org.example.common.Response;
    +import org.example.common.RenameTicketArguments;
     import org.example.common.model.AbstractTicket;
    @@ -75,3 +76,16 @@
     
    -                    case "remove_by_id":
    +                    case "rename_ticket":
    +                        String renameId = reader.readLine();
    +                        String renameName = reader.readLine();
    +                        try {
    +                            RenameTicketArguments rename = RenameTicketArguments.parse(renameId, renameName);
    +                            request = new Request(command,
    +                                    new String[]{Long.toString(rename.id()), rename.name()}, null);
    +                        } catch (IllegalArgumentException e) {
    +                            System.err.println(e.getMessage());
    +                            continue;
    +                        }
    +                        break;
    +
    +                    case "remove_by_id":
                             String removeId = reader.readLine().trim();
    Add: C:\Users\perlm\lab7\src\main\java\org\example\common\RenameTicketArguments.java
    package org.example.common;
    
    /** Shared validation for interactive, script and server requests. */
    public record RenameTicketArguments(long id, String name) {
        public static RenameTicketArguments parse(String idLine, String nameLine) {
            if (idLine == null || idLine.isBlank()) {
                throw new IllegalArgumentException("ERROR: Ticket ID is required");
            }
            long id;
            try {
                id = Long.parseLong(idLine.strip());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("ERROR: Ticket ID must be a positive integer within the long range");
            }
            if (id <= 0) {
                throw new IllegalArgumentException("ERROR: Ticket ID must be positive");
            }
            if (nameLine == null) {
                throw new IllegalArgumentException("ERROR: Ticket name is required");
            }
            String name = stripWhitespace(nameLine);
            if (name.isEmpty()) {
                throw new IllegalArgumentException("ERROR: Ticket name must not be blank");
            }
            return new RenameTicketArguments(id, name);
        }
    
        private static String stripWhitespace(String value) {
            int start = 0;
            int end = value.length();
            while (start < end && isWhitespace(value.codePointAt(start))) {
                start += Character.charCount(value.codePointAt(start));
            }
            while (end > start && isWhitespace(value.codePointBefore(end))) {
                end -= Character.charCount(value.codePointBefore(end));
            }
            return value.substring(start, end);
        }
    
        private static boolean isWhitespace(int codePoint) {
            return Character.isWhitespace(codePoint) || Character.isSpaceChar(codePoint);
        }
    }
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\server\Server.java
    @@ -196,5 +196,9 @@
             // Проверка авторизации для других команд
    -        if (!authorizedUsers.contains(login)) {
    +        if (!authorizedUsers.contains(login)) {
                 return new Response("Please, log in first.", null);
    -        }
    +        }
    +
    +        if ("rename_ticket".equals(command) && !DataBaseManager.checkUser(login, password)) {
    +            return new Response("Invalid login or password.", null);
    +        }
     
    @@ -202,4 +206,3 @@
             try {
    -            Object data = commandManager.doCommand(request, collectionManager);
    -            return new Response(null, data);
    +            return commandManager.doCommand(request, collectionManager);
             } catch (Exception e) {
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\server\command\HelpCommand.java
    @@ -27,3 +27,4 @@
                             "add {element} : добавить новый элемент\n" +
    -                        "update id {element} : обновить элемент по id\n" +
    +                        "update id {element} : обновить элемент по id\n" +
    +                        "rename_ticket : изменить название своего билета (ID и название на следующих строках)\n" +
                             "remove_by_id id : удалить элемент по id\n" +
    Add: C:\Users\perlm\lab7\src\main\java\org\example\server\command\RenameTicketCommand.java
    package org.example.server.command;
    
    import org.example.common.Request;
    import org.example.common.Response;
    import org.example.common.RenameTicketArguments;
    import org.example.server.manager.CollectionManager;
    import org.example.server.manager.DataBaseManager;
    
    public class RenameTicketCommand extends AbstractCommand {
        @Override
        public Response execute(Request request, CollectionManager collectionManager) {
            String[] args = request.getArgs();
            RenameTicketArguments rename;
            try {
                if (args == null || args.length != 2) {
                    return new Response("ERROR: rename_ticket requires a ticket ID and a new name", null);
                }
                rename = RenameTicketArguments.parse(args[0], args[1]);
            } catch (IllegalArgumentException e) {
                return new Response(e.getMessage(), null);
            }
            if (request.getLogin() == null || request.getLogin().isBlank()) {
                return new Response("Please, log in first.", null);
            }
            try {
                if (!collectionManager.renameTicket(rename.id(), rename.name(), request.getLogin())) {
                    return new Response("ERROR: Ticket not found or access denied", null);
                }
                return new Response("Ticket renamed successfully", null);
            } catch (java.sql.SQLException e) {
                logger.error("Rename failed for ticket {}: {}", rename.id(), e.getMessage());
                return new Response("ERROR: Could not rename ticket in the database", null);
            }
        }
    }
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\server\manager\CollectionManager.java
    @@ -34,3 +34,3 @@
     
    -    public void update(Ticket ticket) {
    +    public void update(Ticket ticket) {
     
    @@ -41,3 +41,25 @@
             }
    -    }
    +    }
    +
    +    public boolean renameTicket(long id, String name, String login) throws java.sql.SQLException {
    +        synchronized (collection) {
    +            if (!DataBaseManager.renameTicketById(id, name, login)) {
    +                return false;
    +            }
    +            // Name participates in hashCode: remove before changing it, then rebuild
    +            // the set in the same order so existing entries remain searchable.
    +            LinkedHashSet<Ticket> renamed = new LinkedHashSet<>();
    +            Iterator<Ticket> iterator = collection.iterator();
    +            while (iterator.hasNext()) {
    +                Ticket ticket = iterator.next();
    +                iterator.remove();
    +                if (ticket.getId() == id) {
    +                    ticket.setName(name);
    +                }
    +                renamed.add(ticket);
    +            }
    +            collection.addAll(renamed);
    +            return true;
    +        }
    +    }
     
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\server\manager\CommandManager.java
    @@ -26,3 +26,4 @@
             commands.put("help", new HelpCommand());
    -        commands.put("update", new UpdateCommand());
    +        commands.put("update", new UpdateCommand());
    +        commands.put("rename_ticket", new RenameTicketCommand());
             commands.put("remove_by_id", new RemoveByIdCommand());
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\server\manager\DataBaseManager.java
    @@ -259,3 +259,3 @@
     
    -    public static boolean updateTicketById(int id, Ticket ticket, String login) {
    +    public static boolean updateTicketById(int id, Ticket ticket, String login) {
             int userId = getUserId(login);
    @@ -289,3 +289,15 @@
             }
    -    }
    +    }
    +
    +    public static boolean renameTicketById(long id, String name, String login) throws SQLException {
    +        String sql = "UPDATE ticket SET name = ? WHERE id = ? AND owner_id = "
    +                + "(SELECT user_id FROM lab7_users WHERE login = ?)";
    +        try (Connection connection = createConnection();
    +             PreparedStatement statement = connection.prepareStatement(sql)) {
    +            statement.setString(1, name);
    +            statement.setLong(2, id);
    +            statement.setString(3, login);
    +            return statement.executeUpdate() == 1;
    +        }
    +    }
     

## User

3. Менять можно только свой билет. Для отсутствующего билета, чужого билета и пользователя без авторизации операция должна завершаться отказом без изменений.
4. При успехе изменяется только название. ID, владелец, цена, координаты, дата создания, скидка, тип и место проведения сохраняются. Повторное присвоение текущего названия допустимо и не должно создавать дубликаты.
5. Результат должен сохраняться в PostgreSQL и отражаться в основной коллекции: последующий просмотр показывает новое название, а после перезапуска сервера оно остаётся сохранённым. В коллекции не должны появляться дубликаты билета.
6. При ошибке сохранения в БД название в коллекции не должно изменяться.
7. Интерактивное выполнение и выполнение через скрипт должны давать одинаковый результат. В справке должна появиться новая команда с описанием ввода.
8. Добавь автоматические тесты успешного переименования, названия с пробелами и кириллицей, пустого/пробельного названия, неверного ID, отсутствующего/чужого билета и отказа сохранения. В тестах проверь сохранение остальных полей, отсутствие дубликатов и отсутствие частичного изменения при отказе.

## Activity

    $ git status --short; Get-Content -Encoding utf8 src/main/java/org/example/client/ScriptExecutor.java -First 35; Get-ChildItem C:/Users/perlm/.m2/repository -ErrorAction SilentlyContinue | Select-Object -First 5; mvn '-Dmaven.repo.local=C:/Users/perlm/.m2/repository' test -q
     M src/main/java/org/example/client/Client.java
     M src/main/java/org/example/client/ScriptExecutor.java
     M src/main/java/org/example/server/Server.java
     M src/main/java/org/example/server/command/HelpCommand.java
     M src/main/java/org/example/server/manager/CollectionManager.java
     M src/main/java/org/example/server/manager/CommandManager.java
     M src/main/java/org/example/server/manager/DataBaseManager.java
    ?? src/main/java/org/example/common/RenameTicketArguments.java
    ?? src/main/java/org/example/server/command/RenameTicketCommand.java
    package org.example.client;
    
    import org.example.common.Factory.Factory;
    import org.example.common.Factory.ScriptTicketFactory;
    import org.example.common.Request;
    import org.example.common.Response;
    import org.example.common.RenameTicketArguments;
    import org.example.common.model.AbstractTicket;
    import org.example.common.model.Ticket;
    
    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import java.nio.channels.SocketChannel;
    import java.util.Stack;
    
    /**
     * Класс {@code ScriptExecutor} отвечает за выполнение команд из скриптов (текстовых файлов).
     * <p>Поддерживает обработку вложенных скриптов, предотвращает рекурсивные вызовы одного и того же скрипта.</p>
     */
    public class ScriptExecutor {
    
        /**
         * Множество путей к уже выполненным скриптам, чтобы предотвратить рекурсию.
         */
        private final Stack<String> executedScripts = new Stack<>();
    
    
        /**
         * Конструктор по умолчанию.
         */
        public ScriptExecutor() {
        }
    
        /**
    
    
        Каталог: C:\Users\perlm\.m2\repository
    
    
    Mode                 LastWriteTime         Length Name
    ----                 -------------         ------ ----
    d-----        02.11.2025     20:41                aopalliance
    d-----        02.11.2025     20:41                avalon-framework
    d-----        01.10.2026     11:02                ch
    d-----        02.11.2025     20:41                classworlds
    d-----        01.10.2026     11:02                com
    ������� ������� ��������: 1251
    �������� � �������.
    [ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.15.0:compile (default-compile) on project lab7: Fatal error compiling: C:\Users\perlm\.m2\repository\com\zaxxer\HikariCP\5.1.0\HikariCP-5.1.0.jar -> [Help 1]
    [ERROR]
    [ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
    [ERROR] Re-run Maven using the -X switch to enable full debug logging.
    [ERROR]
    [ERROR] For more information about the errors and possible solutions, please read the following articles:
    [ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionException
    
    
    ✗ (1) • 17.14s

## Assistant

Учту все пункты, включая проверку остальных полей и отсутствие частичных изменений. Коллекция будет обновляться только после успешного SQL-запроса. Название входит в `hashCode` билета, поэтому при его изменении нужно корректно перестроить `LinkedHashSet`, сохранив порядок и исключив дубликаты.

Уточнение к предыдущему сообщению: повторная проверка показала, что метод `Client` и поле `ScriptExecutor` присутствуют; исправлять их не требуется.

## Activity

    file changes: Completed · 1 changes
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\server\manager\CollectionManager.java
    @@ -13,3 +13,10 @@
     
    -public class CollectionManager {
    +public class CollectionManager {
    +
    +    @FunctionalInterface
    +    public interface TicketNameStore {
    +        boolean rename(long id, String name, String login) throws java.sql.SQLException;
    +    }
    +
    +    private final TicketNameStore ticketNameStore;
     
    @@ -23,3 +30,8 @@
     
    -    public CollectionManager() {
    +    public CollectionManager() {
    +        this(DataBaseManager::renameTicketById);
    +    }
    +
    +    public CollectionManager(TicketNameStore ticketNameStore) {
    +        this.ticketNameStore = java.util.Objects.requireNonNull(ticketNameStore);
         }
    @@ -45,3 +57,3 @@
             synchronized (collection) {
    -            if (!DataBaseManager.renameTicketById(id, name, login)) {
    +            if (!ticketNameStore.rename(id, name, login)) {
                     return false;

## Activity

    file changes: Completed · 1 changes
    Add: C:\Users\perlm\lab7\src\test\java\org\example\server\command\RenameTicketCommandTest.java
    package org.example.server.command;
    
    import org.example.common.Request;
    import org.example.common.Response;
    import org.example.common.model.Coordinates;
    import org.example.common.model.Ticket;
    import org.example.common.model.Venue;
    import org.example.common.model.enums.TicketType;
    import org.example.server.manager.CollectionManager;
    import org.example.server.manager.CommandManager;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.params.ParameterizedTest;
    import org.junit.jupiter.params.provider.NullAndEmptySource;
    import org.junit.jupiter.params.provider.ValueSource;
    
    import java.sql.SQLException;
    import java.util.LinkedHashSet;
    import java.util.concurrent.atomic.AtomicInteger;
    
    import static org.junit.jupiter.api.Assertions.*;
    
    class RenameTicketCommandTest {
        private Ticket ticket() {
            Ticket ticket = new Ticket();
            ticket.setId(12);
            ticket.setOwnerId(7);
            ticket.setName("Old name");
            ticket.setCoordinates(new Coordinates(2f, 3L));
            ticket.setPrice(150);
            ticket.setDiscount(10.0);
            ticket.setType(TicketType.values()[0]);
            ticket.setVenue(new Venue());
            return ticket;
        }
    
        private Response execute(CollectionManager manager, String id, String name, String login) {
            return new CommandManager(manager).doCommand(
                    new Request("rename_ticket", new String[]{id, name}, null, login, "password"), manager);
        }
    
        private void assertUnchangedFields(Ticket ticket, Ticket original) {
            assertEquals(original.getId(), ticket.getId());
            assertEquals(original.getOwnerId(), ticket.getOwnerId());
            assertEquals(original.getPrice(), ticket.getPrice());
            assertSame(original.getCoordinates(), ticket.getCoordinates());
            assertEquals(original.getCreationDate(), ticket.getCreationDate());
            assertEquals(original.getDiscount(), ticket.getDiscount());
            assertEquals(original.getType(), ticket.getType());
            assertSame(original.getVenue(), ticket.getVenue());
        }
    
        @Test
        void renamesOnlyNameAndRepeatedRenameDoesNotDuplicateOrBreakSet() {
            Ticket ticket = ticket();
            Ticket original = ticket();
            original.setCoordinates(ticket.getCoordinates());
            original.setCreationDate(ticket.getCreationDate());
            original.setVenue(ticket.getVenue());
            String[] storedName = {ticket.getName()};
            CollectionManager manager = new CollectionManager((id, name, login) -> {
                assertEquals(12, id);
                assertEquals("owner", login);
                assertEquals(storedName[0], ticket.getName(), "memory changes only after persistence");
                storedName[0] = name;
                return true;
            });
            Ticket other = ticket();
            other.setId(13);
            manager.add(ticket);
            manager.add(other);
            String name = "Концерт  симфонического оркестра \"Осень\" '2026'";
            for (int i = 0; i < 2; i++) {
                assertEquals("Ticket renamed successfully", execute(manager, "12", "\t\u00a0" + name + "\u2003 ", "owner").getMessage());
                assertEquals(name, ticket.getName());
                assertEquals(name, storedName[0]);
                assertUnchangedFields(ticket, original);
                LinkedHashSet<Ticket> collection = manager.getCollection();
                assertEquals(2, collection.size());
                assertEquals(1, collection.stream().filter(t -> t.getId() == 12).count());
                assertTrue(collection.contains(ticket));
                assertSame(ticket, collection.iterator().next());
                assertEquals("Old name", other.getName());
            }
            manager.delete(ticket);
            assertEquals(1, manager.getCollection().size());
        }
    
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t\n", "\u00a0\u2003"})
        void rejectsBlankOrMissingNameWithoutPersistence(String name) {
            assertInvalid("12", name);
        }
    
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "abc", "1.5", "12 extra", "0", "-1", "9223372036854775808", "-9223372036854775809"})
        void rejectsInvalidIdWithoutPersistence(String id) {
            assertInvalid(id, "New name");
        }
    
        private void assertInvalid(String id, String name) {
            AtomicInteger writes = new AtomicInteger();
            CollectionManager manager = new CollectionManager((key, value, login) -> {
                writes.incrementAndGet();
                return true;
            });
            Ticket ticket = ticket();
            manager.add(ticket);
            assertTrue(execute(manager, id, name, "owner").getMessage().startsWith("ERROR:"));
            assertEquals(0, writes.get());
            assertEquals("Old name", ticket.getName());
            assertEquals(1, manager.getCollection().size());
            assertTrue(manager.getCollection().contains(ticket));
        }
    
        @Test
        void preservesFullLongId() {
            CollectionManager manager = new CollectionManager((id, name, login) -> {
                assertEquals(Long.MAX_VALUE, id);
                return false;
            });
            assertTrue(execute(manager, Long.toString(Long.MAX_VALUE), "Name", "owner").getMessage().contains("not found"));
        }
    
        @Test
        void rejectsMissingAndForeignTicketsWithoutChanges() {
            CollectionManager manager = new CollectionManager((id, name, login) -> id == 12 && "owner".equals(login));
            Ticket ticket = ticket();
            manager.add(ticket);
            assertTrue(execute(manager, "99", "New", "owner").getMessage().contains("access denied"));
            assertTrue(execute(manager, "12", "New", "other").getMessage().contains("access denied"));
            assertEquals("Old name", ticket.getName());
            assertEquals(1, manager.getCollection().size());
            assertTrue(manager.getCollection().contains(ticket));
        }
    
        @Test
        void storageFailureDoesNotChangeMemory() {
            CollectionManager manager = new CollectionManager((id, name, login) -> { throw new SQLException("offline"); });
            Ticket ticket = ticket();
            manager.add(ticket);
            assertTrue(execute(manager, "12", "New", "owner").getMessage().startsWith("ERROR:"));
            assertEquals("Old name", ticket.getName());
            assertEquals(1, manager.getCollection().size());
            assertTrue(manager.getCollection().contains(ticket));
        }
    
        @Test
        void rejectsMissingLoginAndMalformedRequestsWithoutPersistence() {
            CollectionManager manager = new CollectionManager((id, name, login) -> { fail("Unexpected persistence"); return false; });
            assertEquals("Please, log in first.", execute(manager, "12", "New", null).getMessage());
            for (String[] args : new String[][]{null, {}, {"12"}, {"12", "Name", "extra"}}) {
                Response response = new RenameTicketCommand().execute(new Request("rename_ticket", args, null), manager);
                assertTrue(response.getMessage().startsWith("ERROR:"));
            }
        }
    }

## Activity

    $ mvn '-Dmaven.repo.local=C:/Users/perlm/lab7/.m2-test' test -q
    ������� ������� ��������: 1251
    �������� � �������.
    [ERROR] Plugin org.apache.maven.plugins:maven-resources-plugin:3.4.0 or one of its dependencies could not be resolved:
    [ERROR]     The following artifacts could not be resolved: org.apache.maven.plugins:maven-resources-plugin:pom:3.4.0 (absent): Could not transfer artifact org.apache.maven.plugins:maven-resources-plugin:pom:3.4.0 from/to central (https://repo.maven.apache.org/maven2): Permission denied: no further information
    [ERROR] -> [Help 1]
    [ERROR]
    [ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
    [ERROR] Re-run Maven using the -X switch to enable full debug logging.
    [ERROR]
    [ERROR] For more information about the errors and possible solutions, please read the following articles:
    [ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/PluginResolutionException
    ✗ (1) • 23.09s

## Activity

    $ git diff --stat; Get-Content -Encoding utf8 src/main/java/org/example/common/Response.java; Get-Content .gitignore -ErrorAction SilentlyContinue
     src/main/java/org/example/client/Client.java       | 387 ++++++------
     .../java/org/example/client/ScriptExecutor.java    | 240 +++----
     src/main/java/org/example/server/Server.java       | 475 +++++++-------
     .../org/example/server/command/HelpCommand.java    |  89 +--
     .../example/server/manager/CollectionManager.java  | 442 +++++++------
     .../org/example/server/manager/CommandManager.java | 109 ++--
     .../example/server/manager/DataBaseManager.java    | 700 +++++++++++----------
     7 files changed, 1259 insertions(+), 1183 deletions(-)
    package org.example.common;
    
    import java.io.Serializable;
    
    /**
     * Класс {@code Response} представляет собой объект-ответ, отправляемый сервером клиенту.
     * <p>Содержит сообщение о результате выполнения команды и (опционально) дополнительные данные.</p>
     * <p>Является сериализуемым для передачи по сети.</p>
     */
    public class Response implements Serializable {
    
        /**
         * Сообщение, описывающее результат выполнения команды.
         */
        private String message;
    
        /**
         * Дополнительные данные, возвращаемые сервером (может быть {@code null}).
         * Например, это может быть коллекция объектов или результат фильтрации.
         */
        private Object data;
    
        /**
         * Создаёт новый объект ответа.
         *
         * @param message текст сообщения
         * @param data дополнительные данные (может быть {@code null})
         */
        public Response(String message, Object data) {
            this.message = message;
            this.data = data;
        }
    
        /**
         * Возвращает сообщение результата.
         *
         * @return сообщение
         */
        public String getMessage() {
            return message;
        }
    
        /**
         * Возвращает дополнительные данные ответа.
         *
         * @return данные (или {@code null}, если отсутствуют)
         */
        public Object getData() {
            return data;
        }
    
        /**
         * Возвращает строковое представление объекта ответа.
         *
         * @return строковое представление
         */
        @Override
        public String toString() {
            return "Response{" +
                    "message='" + message + '\'' +
                    ", data=" + data +
                    '}';
        }
    }
    target/
    !.mvn/wrapper/maven-wrapper.jar
    !**/src/main/**/target/
    !**/src/test/**/target/
    
    ### IntelliJ IDEA ###
    .idea/modules.xml
    .idea/jarRepositories.xml
    .idea/compiler.xml
    .idea/libraries/
    *.iws
    *.iml
    *.ipr
    
    ### Eclipse ###
    .apt_generated
    .classpath
    .factorypath
    .project
    .settings
    .springBeans
    .sts4-cache
    
    ### NetBeans ###
    /nbproject/private/
    /nbbuild/
    /dist/
    /nbdist/
    /.nb-gradle/
    build/
    !**/src/main/**/build/
    !**/src/test/**/build/
    
    ### VS Code ###
    .vscode/
    
    ### Mac OS ###
    .DS_Store
    ✓ • 370ms

## Activity

    $ mvn '-Dmaven.repo.local=C:/Users/perlm/lab7/.m2-test' test -q
    ������� ������� ��������: 1251
    12:00:52.591 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.502840100+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=2, venueName='null', capacity=null, type=null}}
    12:00:52.613 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=13, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.589692200+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=6, venueName='null', capacity=null, type=null}}
    12:00:52.633 [main] INFO org.example.server.manager.CollectionManager -- Deleted ticket: Ticket{id=12, name='�������  �������������� �������� "�����" '2026'', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.502840100+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=2, venueName='null', capacity=null, type=null}}
    12:00:52.649 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.649154900+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=8, venueName='null', capacity=null, type=null}}
    12:00:52.653 [main] ERROR org.example.server.command.RenameTicketCommand -- Rename failed for ticket 12: offline
    12:00:52.661 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.661907+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=10, venueName='null', capacity=null, type=null}}
    12:00:52.762 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.762122800+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=12, venueName='null', capacity=null, type=null}}
    12:00:52.775 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.775209400+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=14, venueName='null', capacity=null, type=null}}
    12:00:52.796 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.796742400+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=16, venueName='null', capacity=null, type=null}}
    12:00:52.800 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.800837300+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=18, venueName='null', capacity=null, type=null}}
    12:00:52.808 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.808365700+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=20, venueName='null', capacity=null, type=null}}
    12:00:52.815 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.815793800+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=22, venueName='null', capacity=null, type=null}}
    12:00:52.820 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.820324900+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=24, venueName='null', capacity=null, type=null}}
    12:00:52.825 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.825333600+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=26, venueName='null', capacity=null, type=null}}
    12:00:52.830 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.829170300+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=28, venueName='null', capacity=null, type=null}}
    12:00:52.835 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.835335400+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=30, venueName='null', capacity=null, type=null}}
    12:00:52.842 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.842833+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=32, venueName='null', capacity=null, type=null}}
    12:00:52.848 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.848210+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=34, venueName='null', capacity=null, type=null}}
    12:00:52.854 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.854430100+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=36, venueName='null', capacity=null, type=null}}
    12:00:52.857 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.856795200+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=38, venueName='null', capacity=null, type=null}}
    12:00:52.861 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:00:52.861614600+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=40, venueName='null', capacity=null, type=null}}
    ✓ • 46.60s

## Activity

    file changes: Completed · 1 changes
    Add: C:\Users\perlm\lab7\src\test\java\org\example\client\RenameTicketInputTest.java
    package org.example.client;
    
    import org.example.common.Request;
    import org.example.common.Response;
    import org.example.server.Server;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.io.TempDir;
    
    import java.io.*;
    import java.lang.reflect.Method;
    import java.net.InetSocketAddress;
    import java.nio.channels.ServerSocketChannel;
    import java.nio.channels.SocketChannel;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.util.Scanner;
    import java.util.concurrent.*;
    
    import static org.junit.jupiter.api.Assertions.*;
    
    class RenameTicketInputTest {
        @TempDir Path temp;
    
        private Request capture(boolean script, String input) throws Exception {
            try (ServerSocketChannel listener = ServerSocketChannel.open()) {
                listener.bind(new InetSocketAddress("localhost", 0));
                ExecutorService worker = Executors.newSingleThreadExecutor();
                try {
                    Future<Request> received = worker.submit(() -> {
                        try (SocketChannel server = listener.accept()) {
                            ObjectInputStream stream = new ObjectInputStream(server.socket().getInputStream());
                            Request request = (Request) stream.readObject();
                            ObjectOutputStream response = new ObjectOutputStream(server.socket().getOutputStream());
                            response.writeObject(new Response("OK", null));
                            response.flush();
                            return request;
                        }
                    });
                    try (SocketChannel client = SocketChannel.open(listener.getLocalAddress())) {
                        if (script) {
                            Path path = temp.resolve("rename.txt");
                            Files.writeString(path, "rename_ticket\n" + input);
                            new ScriptExecutor().executeScript(path.toString(), client);
                        } else {
                            Method handle = Client.class.getDeclaredMethod("handleCommand", String.class,
                                    Scanner.class, SocketChannel.class, ScriptExecutor.class);
                            handle.setAccessible(true);
                            assertEquals(true, handle.invoke(null, "rename_ticket", new Scanner(input), client, new ScriptExecutor()));
                        }
                        return received.get(5, TimeUnit.SECONDS);
                    }
                } finally {
                    worker.shutdownNow();
                }
            }
        }
    
        @Test
        void interactiveAndScriptSendSameWholeLineName() throws Exception {
            String input = "12\n  Концерт  оркестра \"Осень\"  \n";
            Request interactive = capture(false, input);
            Request script = capture(true, input);
            assertEquals("rename_ticket", interactive.getCommandName());
            assertEquals(interactive.getCommandName(), script.getCommandName());
            assertArrayEquals(new String[]{"12", "Концерт  оркестра \"Осень\""}, interactive.getArgs());
            assertArrayEquals(interactive.getArgs(), script.getArgs());
            assertNull(interactive.getTicket());
            assertNull(script.getTicket());
        }
    
        @Test
        void truncatedOrInvalidScriptsDoNotSendRequests() throws Exception {
            for (String input : new String[]{"", "12\n", "invalid\nNew name\n", "12\n \t \n"}) {
                Path path = temp.resolve("invalid.txt");
                Files.writeString(path, "rename_ticket\n" + input);
                // A null socket would fail if the executor attempted to send a request.
                assertDoesNotThrow(() -> new ScriptExecutor().executeScript(path.toString(), null));
            }
        }
    
        @Test
        void serverRejectsUnauthorizedUserBeforeExecutingCommand() throws Exception {
            Method process = Server.class.getDeclaredMethod("processRequest", Request.class, SocketChannel.class);
            process.setAccessible(true);
            Response response = (Response) process.invoke(new Server(),
                    new Request("rename_ticket", new String[]{"12", "New"}, null, "stranger", "password"), null);
            assertEquals("Please, log in first.", response.getMessage());
            assertNull(response.getData());
        }
    }

## Assistant

Первые тесты прошли: проверены успешное переименование, повторное присвоение имени, границы `long`, неверный ввод и отказ сохранения. Добавил проверки одинаковых запросов из интерактивного режима и скрипта. Для чтения скриптов явно задам UTF-8, чтобы кириллица сохранялась и на Windows.

## Activity

    file changes: Completed · 2 changes
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\client\ScriptExecutor.java
    @@ -12,3 +12,4 @@
     import java.io.FileReader;
    -import java.io.IOException;
    +import java.io.IOException;
    +import java.nio.charset.StandardCharsets;
     import java.nio.channels.SocketChannel;
    @@ -49,3 +50,3 @@
     
    -        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
    +        try (BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
                 Factory factory = new ScriptTicketFactory(reader);
    Add: C:\Users\perlm\lab7\src\test\java\org\example\server\manager\RenameTicketPersistenceTest.java
    package org.example.server.manager;
    
    import org.junit.jupiter.api.Test;
    
    import java.lang.reflect.Proxy;
    import java.sql.*;
    import java.util.*;
    import java.util.logging.Logger;
    
    import static org.junit.jupiter.api.Assertions.*;
    
    class RenameTicketPersistenceTest {
        /** JDBC double checks the actual persistence method without a running database. */
        @Test
        void usesParameterizedNameOnlyUpdateAndLongIdWithOwnerCondition() throws Exception {
            withDriver(1, false, (sql, bindings) -> {
                assertTrue(DataBaseManager.renameTicketById(Long.MAX_VALUE, "Концерт \"Осень\"", "owner"));
                assertEquals("UPDATE ticket SET name = ? WHERE id = ? AND owner_id = "
                        + "(SELECT user_id FROM lab7_users WHERE login = ?)", sql[0]);
                assertEquals(Map.of(1, "Концерт \"Осень\"", 2, Long.MAX_VALUE, 3, "owner"), bindings);
            });
        }
    
        @Test
        void noMatchingOwnerOrTicketReturnsFalse() throws Exception {
            withDriver(0, false, (sql, bindings) ->
                    assertFalse(DataBaseManager.renameTicketById(12, "Name", "other")));
        }
    
        @Test
        void sqlFailurePropagatesSoCollectionCanRemainUnchanged() throws Exception {
            withDriver(0, true, (sql, bindings) ->
                    assertThrows(SQLException.class, () -> DataBaseManager.renameTicketById(12, "Name", "owner")));
        }
    
        @FunctionalInterface
        private interface Check {
            void run(String[] sql, Map<Integer, Object> bindings) throws Exception;
        }
    
        private void withDriver(int rows, boolean fail, Check check) throws Exception {
            String[] sql = {null};
            Map<Integer, Object> bindings = new HashMap<>();
            PreparedStatement statement = (PreparedStatement) Proxy.newProxyInstance(getClass().getClassLoader(),
                    new Class[]{PreparedStatement.class}, (proxy, method, args) -> {
                        switch (method.getName()) {
                            case "setString", "setLong": bindings.put((Integer) args[0], args[1]); return null;
                            case "executeUpdate": if (fail) throw new SQLException("Write failed"); return rows;
                            case "close": return null;
                            default: throw new UnsupportedOperationException(method.getName());
                        }
                    });
            Connection connection = (Connection) Proxy.newProxyInstance(getClass().getClassLoader(),
                    new Class[]{Connection.class}, (proxy, method, args) -> {
                        switch (method.getName()) {
                            case "prepareStatement": sql[0] = (String) args[0]; return statement;
                            case "close": return null;
                            default: throw new UnsupportedOperationException(method.getName());
                        }
                    });
            // Register before loading the PostgreSQL driver; no real connection is made.
            List<Driver> previous = Collections.list(DriverManager.getDrivers());
            for (Driver driver : previous) DriverManager.deregisterDriver(driver);
            Driver driver = new Driver() {
                public Connection connect(String url, Properties properties) { return connection; }
                public boolean acceptsURL(String url) { return true; }
                public DriverPropertyInfo[] getPropertyInfo(String url, Properties properties) { return new DriverPropertyInfo[0]; }
                public int getMajorVersion() { return 1; }
                public int getMinorVersion() { return 0; }
                public boolean jdbcCompliant() { return false; }
                public Logger getParentLogger() { return Logger.getGlobal(); }
            };
            DriverManager.registerDriver(driver);
            try {
                check.run(sql, bindings);
            } finally {
                DriverManager.deregisterDriver(driver);
                for (Driver saved : previous) DriverManager.registerDriver(saved);
            }
        }
    }

## Activity

    $ $utf8 = New-Object System.Text.UTF8Encoding($false)
        $files = git diff --name-only -- '*.java'
        foreach ($file in $files) { $path = Join-Path (Get-Location) $file; $content = [IO.File]::ReadAllText($path); $content = $content.Replace("`r`n", "`n").Replace("`n", "`r`n"); [IO.File]::WriteAllText($path, $content, $utf8) }
        mvn '-Dmaven.repo.local=C:/Users/perlm/lab7/.m2-test' test -q
    ������� ������� ��������: 1251
    �������� � �������.
    [ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.15.0:compile (default-compile) on project lab7: Fatal error compiling: C:\Users\perlm\lab7\.m2-test\ch\qos\logback\logback-classic\1.5.13\logback-classic-1.5.13.jar -> [Help 1]
    [ERROR]
    [ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
    [ERROR] Re-run Maven using the -X switch to enable full debug logging.
    [ERROR]
    [ERROR] For more information about the errors and possible solutions, please read the following articles:
    [ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionException
    ✗ (1) • 19.02s

## Activity

    $ git diff --stat; git diff --check; Get-ChildItem target/surefire-reports/TEST-*.xml | ForEach-Object { [xml]$report = Get-Content $_.FullName; $report.testsuite | Select-Object name,tests,failures,errors,skipped }
     src/main/java/org/example/client/Client.java       | 395 ++++++------
     .../java/org/example/client/ScriptExecutor.java    | 249 +++----
     src/main/java/org/example/server/Server.java       | 483 +++++++-------
     .../org/example/server/command/HelpCommand.java    |  91 +--
     .../example/server/manager/CollectionManager.java  | 480 +++++++-------
     .../org/example/server/manager/CommandManager.java | 111 ++--
     .../example/server/manager/DataBaseManager.java    | 712 +++++++++++----------
     7 files changed, 1299 insertions(+), 1222 deletions(-)
    src/main/java/org/example/client/Client.java:1: trailing whitespace.
    +package org.example.client;
    src/main/java/org/example/client/Client.java:2: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:3: trailing whitespace.
    +import org.example.common.Factory.TicketFactory;
    src/main/java/org/example/client/Client.java:4: trailing whitespace.
    +import org.example.common.Request;
    src/main/java/org/example/client/Client.java:5: trailing whitespace.
    +import org.example.common.Response;
    src/main/java/org/example/client/Client.java:6: trailing whitespace.
    +import org.example.common.RenameTicketArguments;
    src/main/java/org/example/client/Client.java:7: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:8: trailing whitespace.
    +import java.io.*;
    src/main/java/org/example/client/Client.java:9: trailing whitespace.
    +import java.net.InetSocketAddress;
    src/main/java/org/example/client/Client.java:10: trailing whitespace.
    +import java.nio.ByteBuffer;
    src/main/java/org/example/client/Client.java:11: trailing whitespace.
    +import java.nio.channels.SocketChannel;
    src/main/java/org/example/client/Client.java:12: trailing whitespace.
    +import java.util.ArrayList;
    src/main/java/org/example/client/Client.java:13: trailing whitespace.
    +import java.util.Scanner;
    src/main/java/org/example/client/Client.java:14: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:15: trailing whitespace.
    +public class Client {
    src/main/java/org/example/client/Client.java:16: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:17: trailing whitespace.
    +    private static final String SERVER_ADDRESS = "localhost";
    src/main/java/org/example/client/Client.java:18: trailing whitespace.
    +    private static int SERVER_PORT;
    src/main/java/org/example/client/Client.java:19: trailing whitespace.
    +    private static String login = "";
    src/main/java/org/example/client/Client.java:20: trailing whitespace.
    +    private static String password = "";
    src/main/java/org/example/client/Client.java:21: trailing whitespace.
    +    private static TicketFactory ticketFactory = new TicketFactory();
    src/main/java/org/example/client/Client.java:22: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:23: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:24: trailing whitespace.
    +    public static void main(String[] args) throws InterruptedException {
    src/main/java/org/example/client/Client.java:25: trailing whitespace.
    +        Scanner scanner = new Scanner(System.in);
    src/main/java/org/example/client/Client.java:26: trailing whitespace.
    +        ScriptExecutor scriptExecutor = new ScriptExecutor();
    src/main/java/org/example/client/Client.java:27: trailing whitespace.
    +        SERVER_PORT = Integer.parseInt(args[0]);
    src/main/java/org/example/client/Client.java:28: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:29: trailing whitespace.
    +        while (true) {
    src/main/java/org/example/client/Client.java:30: trailing whitespace.
    +            try (SocketChannel socket = SocketChannel.open()) {
    src/main/java/org/example/client/Client.java:31: trailing whitespace.
    +                socket.connect(new InetSocketAddress(SERVER_ADDRESS, SERVER_PORT));
    src/main/java/org/example/client/Client.java:32: trailing whitespace.
    +                System.out.println("Connected to server at " + SERVER_ADDRESS + ":" + SERVER_PORT);
    src/main/java/org/example/client/Client.java:33: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:34: trailing whitespace.
    +                while (true) {
    src/main/java/org/example/client/Client.java:35: trailing whitespace.
    +                    try {
    src/main/java/org/example/client/Client.java:36: trailing whitespace.
    +                        System.out.print("Enter command: ");
    src/main/java/org/example/client/Client.java:37: trailing whitespace.
    +                        String command = scanner.nextLine().trim();
    src/main/java/org/example/client/Client.java:38: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:39: trailing whitespace.
    +                        if (command.equalsIgnoreCase("exit")) {
    src/main/java/org/example/client/Client.java:40: trailing whitespace.
    +                            System.out.println("Exiting...");
    src/main/java/org/example/client/Client.java:41: trailing whitespace.
    +                            return;
    src/main/java/org/example/client/Client.java:42: trailing whitespace.
    +                        }
    src/main/java/org/example/client/Client.java:43: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:44: trailing whitespace.
    +                        boolean handled = handleCommand(command, scanner, socket, scriptExecutor);
    src/main/java/org/example/client/Client.java:45: trailing whitespace.
    +                        if (!handled) {
    src/main/java/org/example/client/Client.java:46: trailing whitespace.
    +                            System.out.println("Failed to handle command.");
    src/main/java/org/example/client/Client.java:47: trailing whitespace.
    +                        }
    src/main/java/org/example/client/Client.java:48: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:49: trailing whitespace.
    +                    } catch (Exception e) {
    src/main/java/org/example/client/Client.java:50: trailing whitespace.
    +                        System.out.println("Error during command processing: " + e.getMessage());
    src/main/java/org/example/client/Client.java:51: trailing whitespace.
    +                    }
    src/main/java/org/example/client/Client.java:52: trailing whitespace.
    +                }
    src/main/java/org/example/client/Client.java:53: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:54: trailing whitespace.
    +            } catch (IOException e) {
    src/main/java/org/example/client/Client.java:55: trailing whitespace.
    +                System.err.println("Connection error: " + e.getMessage());
    src/main/java/org/example/client/Client.java:56: trailing whitespace.
    +                Thread.sleep(1000);
    src/main/java/org/example/client/Client.java:57: trailing whitespace.
    +            }
    src/main/java/org/example/client/Client.java:58: trailing whitespace.
    +        }
    src/main/java/org/example/client/Client.java:59: trailing whitespace.
    +    }
    src/main/java/org/example/client/Client.java:60: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:61: trailing whitespace.
    +    private static boolean handleCommand(String command, Scanner scanner, SocketChannel socket, ScriptExecutor scriptExecutor) {
    src/main/java/org/example/client/Client.java:62: trailing whitespace.
    +        Request request;
    src/main/java/org/example/client/Client.java:63: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:64: trailing whitespace.
    +        try {
    src/main/java/org/example/client/Client.java:65: trailing whitespace.
    +            switch (command.toLowerCase()) {
    src/main/java/org/example/client/Client.java:66: trailing whitespace.
    +                case "login":
    src/main/java/org/example/client/Client.java:67: trailing whitespace.
    +                case "register":
    src/main/java/org/example/client/Client.java:68: trailing whitespace.
    +                    System.out.print("Enter login: ");
    src/main/java/org/example/client/Client.java:69: trailing whitespace.
    +                    login = scanner.nextLine().trim();
    src/main/java/org/example/client/Client.java:70: trailing whitespace.
    +                    System.out.print("Enter password: ");
    src/main/java/org/example/client/Client.java:71: trailing whitespace.
    +                    password = scanner.nextLine().trim();
    src/main/java/org/example/client/Client.java:72: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:73: trailing whitespace.
    +                    request = new Request(command, new String[]{login, password}, null);
    src/main/java/org/example/client/Client.java:74: trailing whitespace.
    +                    break;
    src/main/java/org/example/client/Client.java:75: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:76: trailing whitespace.
    +                case "logout":
    src/main/java/org/example/client/Client.java:77: trailing whitespace.
    +                    login = "";
    src/main/java/org/example/client/Client.java:78: trailing whitespace.
    +                    password = "";
    src/main/java/org/example/client/Client.java:79: trailing whitespace.
    +                    System.out.println("Logged out successfully.");
    src/main/java/org/example/client/Client.java:80: trailing whitespace.
    +                    return true;
    src/main/java/org/example/client/Client.java:81: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:82: trailing whitespace.
    +                case "add":
    src/main/java/org/example/client/Client.java:83: trailing whitespace.
    +                case "add_if_max":
    src/main/java/org/example/client/Client.java:84: trailing whitespace.
    +                case "remove_greater":
    src/main/java/org/example/client/Client.java:85: trailing whitespace.
    +                case "remove_lower":
    src/main/java/org/example/client/Client.java:86: trailing whitespace.
    +                    request = new Request(command, new String[0], ticketFactory.createTicket()
    src/main/java/org/example/client/Client.java:87: trailing whitespace.
    +                    );
    src/main/java/org/example/client/Client.java:88: trailing whitespace.
    +                    break;
    src/main/java/org/example/client/Client.java:89: trailing whitespace.
    +                case "update":
    src/main/java/org/example/client/Client.java:90: trailing whitespace.
    +                    System.out.print("Enter ID of the ticket to update: ");
    src/main/java/org/example/client/Client.java:91: trailing whitespace.
    +                    long id = Long.parseLong(scanner.nextLine());
    src/main/java/org/example/client/Client.java:92: trailing whitespace.
    +                    request = new Request(command, new String[]{String.valueOf(id)}, ticketFactory.createTicket()
    src/main/java/org/example/client/Client.java:93: trailing whitespace.
    +                    );
    src/main/java/org/example/client/Client.java:94: trailing whitespace.
    +                    break;
    src/main/java/org/example/client/Client.java:95: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:96: trailing whitespace.
    +                case "rename_ticket":
    src/main/java/org/example/client/Client.java:97: trailing whitespace.
    +                    System.out.print("Enter ID of the ticket to rename: ");
    src/main/java/org/example/client/Client.java:98: trailing whitespace.
    +                    String renameId = scanner.hasNextLine() ? scanner.nextLine() : null;
    src/main/java/org/example/client/Client.java:99: trailing whitespace.
    +                    System.out.print("Enter new ticket name: ");
    src/main/java/org/example/client/Client.java:100: trailing whitespace.
    +                    String renameName = scanner.hasNextLine() ? scanner.nextLine() : null;
    src/main/java/org/example/client/Client.java:101: trailing whitespace.
    +                    RenameTicketArguments rename = RenameTicketArguments.parse(renameId, renameName);
    src/main/java/org/example/client/Client.java:102: trailing whitespace.
    +                    request = new Request("rename_ticket",
    src/main/java/org/example/client/Client.java:103: trailing whitespace.
    +                            new String[]{Long.toString(rename.id()), rename.name()}, null);
    src/main/java/org/example/client/Client.java:104: trailing whitespace.
    +                    break;
    src/main/java/org/example/client/Client.java:105: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:106: trailing whitespace.
    +                case "remove_by_id":
    src/main/java/org/example/client/Client.java:107: trailing whitespace.
    +                    System.out.print("Enter ID of the ticket to remove: ");
    src/main/java/org/example/client/Client.java:108: trailing whitespace.
    +                    String removeIdStr = scanner.nextLine();
    src/main/java/org/example/client/Client.java:109: trailing whitespace.
    +                    request = new Request(command, new String[]{removeIdStr}, null);
    src/main/java/org/example/client/Client.java:110: trailing whitespace.
    +                    break;
    src/main/java/org/example/client/Client.java:111: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:112: trailing whitespace.
    +                case "filter_starts_with":
    src/main/java/org/example/client/Client.java:113: trailing whitespace.
    +                    System.out.print("Enter name prefix to filter: ");
    src/main/java/org/example/client/Client.java:114: trailing whitespace.
    +                    String prefix = scanner.nextLine();
    src/main/java/org/example/client/Client.java:115: trailing whitespace.
    +                    request = new Request(command, new String[]{prefix}, null);
    src/main/java/org/example/client/Client.java:116: trailing whitespace.
    +                    break;
    src/main/java/org/example/client/Client.java:117: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:118: trailing whitespace.
    +                case "execute_script":
    src/main/java/org/example/client/Client.java:119: trailing whitespace.
    +                    System.out.print("Enter script file path: ");
    src/main/java/org/example/client/Client.java:120: trailing whitespace.
    +                    String filePath = scanner.nextLine();
    src/main/java/org/example/client/Client.java:121: trailing whitespace.
    +                    scriptExecutor.executeScript(filePath, socket);
    src/main/java/org/example/client/Client.java:122: trailing whitespace.
    +                    return true;
    src/main/java/org/example/client/Client.java:123: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:124: trailing whitespace.
    +                default:
    src/main/java/org/example/client/Client.java:125: trailing whitespace.
    +                    request = new Request(command, new String[0], null);
    src/main/java/org/example/client/Client.java:126: trailing whitespace.
    +                    break;
    src/main/java/org/example/client/Client.java:127: trailing whitespace.
    +            }
    src/main/java/org/example/client/Client.java:128: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:129: trailing whitespace.
    +            sendRequest(request, socket);
    src/main/java/org/example/client/Client.java:130: trailing whitespace.
    +            Response response = receiveResponse(socket);
    src/main/java/org/example/client/Client.java:131: trailing whitespace.
    +            System.out.println("Server response: " + response.getMessage());
    src/main/java/org/example/client/Client.java:132: trailing whitespace.
    +            if (response.getData() != null) {
    src/main/java/org/example/client/Client.java:133: trailing whitespace.
    +                System.out.println("Server data: " + response.getData());
    src/main/java/org/example/client/Client.java:134: trailing whitespace.
    +            }
    src/main/java/org/example/client/Client.java:135: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:136: trailing whitespace.
    +            return true;
    src/main/java/org/example/client/Client.java:137: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:138: trailing whitespace.
    +        } catch (Exception e) {
    src/main/java/org/example/client/Client.java:139: trailing whitespace.
    +            System.err.println("Failed to process command: " + e.getMessage());
    src/main/java/org/example/client/Client.java:140: trailing whitespace.
    +            return false;
    src/main/java/org/example/client/Client.java:141: trailing whitespace.
    +        }
    src/main/java/org/example/client/Client.java:142: trailing whitespace.
    +    }
    src/main/java/org/example/client/Client.java:143: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:144: trailing whitespace.
    +    protected static void sendRequest(Request request, SocketChannel socket) throws IOException {
    src/main/java/org/example/client/Client.java:145: trailing whitespace.
    +        request.setLogin(login);
    src/main/java/org/example/client/Client.java:146: trailing whitespace.
    +        request.setPassword(password);
    src/main/java/org/example/client/Client.java:147: trailing whitespace.
    +        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    src/main/java/org/example/client/Client.java:148: trailing whitespace.
    +             ObjectOutputStream objectStream = new ObjectOutputStream(byteStream)) {
    src/main/java/org/example/client/Client.java:149: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:150: trailing whitespace.
    +            objectStream.writeObject(request);
    src/main/java/org/example/client/Client.java:151: trailing whitespace.
    +            objectStream.flush();
    src/main/java/org/example/client/Client.java:152: trailing whitespace.
    +            byte[] data = byteStream.toByteArray();
    src/main/java/org/example/client/Client.java:153: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:154: trailing whitespace.
    +            ByteBuffer buffer = ByteBuffer.wrap(data);
    src/main/java/org/example/client/Client.java:155: trailing whitespace.
    +            int chunkSize = 8192;
    src/main/java/org/example/client/Client.java:156: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:157: trailing whitespace.
    +            while (buffer.hasRemaining()) {
    src/main/java/org/example/client/Client.java:158: trailing whitespace.
    +                int length = Math.min(chunkSize, buffer.remaining());
    src/main/java/org/example/client/Client.java:159: trailing whitespace.
    +                ByteBuffer chunk = ByteBuffer.wrap(data, buffer.position(), length);
    src/main/java/org/example/client/Client.java:160: trailing whitespace.
    +                int bytesWritten = socket.write(chunk);
    src/main/java/org/example/client/Client.java:161: trailing whitespace.
    +                buffer.position(buffer.position() + bytesWritten);
    src/main/java/org/example/client/Client.java:162: trailing whitespace.
    +            }
    src/main/java/org/example/client/Client.java:163: trailing whitespace.
    +        }
    src/main/java/org/example/client/Client.java:164: trailing whitespace.
    +    }
    src/main/java/org/example/client/Client.java:165: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:166: trailing whitespace.
    +    public static Response receiveResponse(SocketChannel socket) throws IOException, InterruptedException, ClassNotFoundException {
    src/main/java/org/example/client/Client.java:167: trailing whitespace.
    +        ByteBuffer buffer1 = dynamicBuffer(socket);
    src/main/java/org/example/client/Client.java:168: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:169: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:170: trailing whitespace.
    +        ByteArrayInputStream bi = new ByteArrayInputStream(buffer1.array());
    src/main/java/org/example/client/Client.java:171: trailing whitespace.
    +        ObjectInputStream oi = new ObjectInputStream(bi);
    src/main/java/org/example/client/Client.java:172: trailing whitespace.
    +        Response response = (Response) oi.readObject();
    src/main/java/org/example/client/Client.java:173: trailing whitespace.
    +        System.out.println("Получено сообщение от сервера: " + response);
    src/main/java/org/example/client/Client.java:174: trailing whitespace.
    +        return response;
    src/main/java/org/example/client/Client.java:175: trailing whitespace.
    +    }
    src/main/java/org/example/client/Client.java:176: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:177: trailing whitespace.
    +    public static ByteBuffer dynamicBuffer(SocketChannel server) throws IOException, InterruptedException {
    src/main/java/org/example/client/Client.java:178: trailing whitespace.
    +        Thread.sleep(200);
    src/main/java/org/example/client/Client.java:179: trailing whitespace.
    +        ArrayList<ByteBuffer> bufferList = new ArrayList<>();
    src/main/java/org/example/client/Client.java:180: trailing whitespace.
    +        for (int i = 0; i < 10000000; i++) {
    src/main/java/org/example/client/Client.java:181: trailing whitespace.
    +            ByteBuffer buffer = ByteBuffer.allocate(8192);
    src/main/java/org/example/client/Client.java:182: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:183: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:184: trailing whitespace.
    +            int bytesRead = server.read(buffer);
    src/main/java/org/example/client/Client.java:185: trailing whitespace.
    +            buffer.flip();
    src/main/java/org/example/client/Client.java:186: trailing whitespace.
    +            if (bytesRead > 0) {
    src/main/java/org/example/client/Client.java:187: trailing whitespace.
    +                bufferList.add(buffer);
    src/main/java/org/example/client/Client.java:188: trailing whitespace.
    +            }
    src/main/java/org/example/client/Client.java:189: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:190: trailing whitespace.
    +            if (bytesRead < buffer.capacity()) {
    src/main/java/org/example/client/Client.java:191: trailing whitespace.
    +                break;
    src/main/java/org/example/client/Client.java:192: trailing whitespace.
    +            }
    src/main/java/org/example/client/Client.java:193: trailing whitespace.
    +        }
    src/main/java/org/example/client/Client.java:194: trailing whitespace.
    +        ByteBuffer bigBuffer = ByteBuffer.allocate(bufferList.size() * 8192);
    src/main/java/org/example/client/Client.java:195: trailing whitespace.
    +        for (ByteBuffer byteBuffer : bufferList) {
    src/main/java/org/example/client/Client.java:196: trailing whitespace.
    +            bigBuffer.put(byteBuffer.array());
    src/main/java/org/example/client/Client.java:197: trailing whitespace.
    +        }
    src/main/java/org/example/client/Client.java:198: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:199: trailing whitespace.
    +        System.out.println("Данные прочитаны");
    src/main/java/org/example/client/Client.java:200: trailing whitespace.
    +
    src/main/java/org/example/client/Client.java:201: trailing whitespace.
    +        return bigBuffer;
    src/main/java/org/example/client/Client.java:202: trailing whitespace.
    +    }
    src/main/java/org/example/client/Client.java:203: trailing whitespace.
    +}
    src/main/java/org/example/client/ScriptExecutor.java:1: trailing whitespace.
    +package org.example.client;
    src/main/java/org/example/client/ScriptExecutor.java:2: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:3: trailing whitespace.
    +import org.example.common.Factory.Factory;
    src/main/java/org/example/client/ScriptExecutor.java:4: trailing whitespace.
    +import org.example.common.Factory.ScriptTicketFactory;
    src/main/java/org/example/client/ScriptExecutor.java:5: trailing whitespace.
    +import org.example.common.Request;
    src/main/java/org/example/client/ScriptExecutor.java:6: trailing whitespace.
    +import org.example.common.Response;
    src/main/java/org/example/client/ScriptExecutor.java:7: trailing whitespace.
    +import org.example.common.RenameTicketArguments;
    src/main/java/org/example/client/ScriptExecutor.java:8: trailing whitespace.
    +import org.example.common.model.AbstractTicket;
    src/main/java/org/example/client/ScriptExecutor.java:9: trailing whitespace.
    +import org.example.common.model.Ticket;
    src/main/java/org/example/client/ScriptExecutor.java:10: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:11: trailing whitespace.
    +import java.io.BufferedReader;
    src/main/java/org/example/client/ScriptExecutor.java:12: trailing whitespace.
    +import java.io.FileReader;
    src/main/java/org/example/client/ScriptExecutor.java:13: trailing whitespace.
    +import java.io.IOException;
    src/main/java/org/example/client/ScriptExecutor.java:14: trailing whitespace.
    +import java.nio.charset.StandardCharsets;
    src/main/java/org/example/client/ScriptExecutor.java:15: trailing whitespace.
    +import java.nio.channels.SocketChannel;
    src/main/java/org/example/client/ScriptExecutor.java:16: trailing whitespace.
    +import java.util.Stack;
    src/main/java/org/example/client/ScriptExecutor.java:17: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:18: trailing whitespace.
    +/**
    src/main/java/org/example/client/ScriptExecutor.java:19: trailing whitespace.
    + * Класс {@code ScriptExecutor} отвечает за выполнение команд из скриптов (текстовых файлов).
    src/main/java/org/example/client/ScriptExecutor.java:20: trailing whitespace.
    + * <p>Поддерживает обработку вложенных скриптов, предотвращает рекурсивные вызовы одного и того же скрипта.</p>
    src/main/java/org/example/client/ScriptExecutor.java:21: trailing whitespace.
    + */
    src/main/java/org/example/client/ScriptExecutor.java:22: trailing whitespace.
    +public class ScriptExecutor {
    src/main/java/org/example/client/ScriptExecutor.java:23: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:24: trailing whitespace.
    +    /**
    src/main/java/org/example/client/ScriptExecutor.java:25: trailing whitespace.
    +     * Множество путей к уже выполненным скриптам, чтобы предотвратить рекурсию.
    src/main/java/org/example/client/ScriptExecutor.java:26: trailing whitespace.
    +     */
    src/main/java/org/example/client/ScriptExecutor.java:27: trailing whitespace.
    +    private final Stack<String> executedScripts = new Stack<>();
    src/main/java/org/example/client/ScriptExecutor.java:28: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:29: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:30: trailing whitespace.
    +    /**
    src/main/java/org/example/client/ScriptExecutor.java:31: trailing whitespace.
    +     * Конструктор по умолчанию.
    src/main/java/org/example/client/ScriptExecutor.java:32: trailing whitespace.
    +     */
    src/main/java/org/example/client/ScriptExecutor.java:33: trailing whitespace.
    +    public ScriptExecutor() {
    src/main/java/org/example/client/ScriptExecutor.java:34: trailing whitespace.
    +    }
    src/main/java/org/example/client/ScriptExecutor.java:35: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:36: trailing whitespace.
    +    /**
    src/main/java/org/example/client/ScriptExecutor.java:37: trailing whitespace.
    +     * Выполняет команды из указанного файла-скрипта.
    src/main/java/org/example/client/ScriptExecutor.java:38: trailing whitespace.
    +     * <p>Поддерживает команды, требующие дополнительного ввода (например, {@code add}, {@code update}), считывая данные из файла.</p>
    src/main/java/org/example/client/ScriptExecutor.java:39: trailing whitespace.
    +     *
    src/main/java/org/example/client/ScriptExecutor.java:40: trailing whitespace.
    +     * @param filePath путь к скрипту
    src/main/java/org/example/client/ScriptExecutor.java:41: trailing whitespace.
    +     * @param socket   активный {@link SocketChannel} для отправки запросов на сервер
    src/main/java/org/example/client/ScriptExecutor.java:42: trailing whitespace.
    +     */
    src/main/java/org/example/client/ScriptExecutor.java:43: trailing whitespace.
    +    public void executeScript(String filePath, SocketChannel socket) {
    src/main/java/org/example/client/ScriptExecutor.java:44: trailing whitespace.
    +        if (executedScripts.contains(filePath)) {
    src/main/java/org/example/client/ScriptExecutor.java:45: trailing whitespace.
    +            System.out.println("Recursion detected. Skipping script: " + filePath);
    src/main/java/org/example/client/ScriptExecutor.java:46: trailing whitespace.
    +            return;
    src/main/java/org/example/client/ScriptExecutor.java:47: trailing whitespace.
    +        }
    src/main/java/org/example/client/ScriptExecutor.java:48: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:49: trailing whitespace.
    +        executedScripts.push(filePath);
    src/main/java/org/example/client/ScriptExecutor.java:50: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:51: trailing whitespace.
    +        try (BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
    src/main/java/org/example/client/ScriptExecutor.java:52: trailing whitespace.
    +            Factory factory = new ScriptTicketFactory(reader);
    src/main/java/org/example/client/ScriptExecutor.java:53: trailing whitespace.
    +            String line;
    src/main/java/org/example/client/ScriptExecutor.java:54: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:55: trailing whitespace.
    +            while ((line = reader.readLine()) != null) {
    src/main/java/org/example/client/ScriptExecutor.java:56: trailing whitespace.
    +                line = line.trim();
    src/main/java/org/example/client/ScriptExecutor.java:57: trailing whitespace.
    +                if (line.isEmpty()) continue;
    src/main/java/org/example/client/ScriptExecutor.java:58: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:59: trailing whitespace.
    +                String command = line.split(" ")[0];
    src/main/java/org/example/client/ScriptExecutor.java:60: trailing whitespace.
    +                System.out.println("Executing command from script: " + command);
    src/main/java/org/example/client/ScriptExecutor.java:61: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:62: trailing whitespace.
    +                Request request = null;
    src/main/java/org/example/client/ScriptExecutor.java:63: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:64: trailing whitespace.
    +                switch (command) {
    src/main/java/org/example/client/ScriptExecutor.java:65: trailing whitespace.
    +                    case "add":
    src/main/java/org/example/client/ScriptExecutor.java:66: trailing whitespace.
    +                    case "add_if_max":
    src/main/java/org/example/client/ScriptExecutor.java:67: trailing whitespace.
    +                        AbstractTicket ticket = factory.createTicket();
    src/main/java/org/example/client/ScriptExecutor.java:68: trailing whitespace.
    +                        request = new Request(command, new String[0], ticket);
    src/main/java/org/example/client/ScriptExecutor.java:69: trailing whitespace.
    +                        break;
    src/main/java/org/example/client/ScriptExecutor.java:70: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:71: trailing whitespace.
    +                    case "update":
    src/main/java/org/example/client/ScriptExecutor.java:72: trailing whitespace.
    +                        String idLine = reader.readLine();
    src/main/java/org/example/client/ScriptExecutor.java:73: trailing whitespace.
    +                        long id = Long.parseLong(idLine.trim());
    src/main/java/org/example/client/ScriptExecutor.java:74: trailing whitespace.
    +                        Ticket updatedTicket = (Ticket) factory.createTicket();
    src/main/java/org/example/client/ScriptExecutor.java:75: trailing whitespace.
    +                        request = new Request(command, new String[]{String.valueOf(id)}, updatedTicket);
    src/main/java/org/example/client/ScriptExecutor.java:76: trailing whitespace.
    +                        break;
    src/main/java/org/example/client/ScriptExecutor.java:77: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:78: trailing whitespace.
    +                    case "rename_ticket":
    src/main/java/org/example/client/ScriptExecutor.java:79: trailing whitespace.
    +                        String renameId = reader.readLine();
    src/main/java/org/example/client/ScriptExecutor.java:80: trailing whitespace.
    +                        String renameName = reader.readLine();
    src/main/java/org/example/client/ScriptExecutor.java:81: trailing whitespace.
    +                        try {
    src/main/java/org/example/client/ScriptExecutor.java:82: trailing whitespace.
    +                            RenameTicketArguments rename = RenameTicketArguments.parse(renameId, renameName);
    src/main/java/org/example/client/ScriptExecutor.java:83: trailing whitespace.
    +                            request = new Request(command,
    src/main/java/org/example/client/ScriptExecutor.java:84: trailing whitespace.
    +                                    new String[]{Long.toString(rename.id()), rename.name()}, null);
    src/main/java/org/example/client/ScriptExecutor.java:85: trailing whitespace.
    +                        } catch (IllegalArgumentException e) {
    src/main/java/org/example/client/ScriptExecutor.java:86: trailing whitespace.
    +                            System.err.println(e.getMessage());
    src/main/java/org/example/client/ScriptExecutor.java:87: trailing whitespace.
    +                            continue;
    src/main/java/org/example/client/ScriptExecutor.java:88: trailing whitespace.
    +                        }
    src/main/java/org/example/client/ScriptExecutor.java:89: trailing whitespace.
    +                        break;
    src/main/java/org/example/client/ScriptExecutor.java:90: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:91: trailing whitespace.
    +                    case "remove_by_id":
    src/main/java/org/example/client/ScriptExecutor.java:92: trailing whitespace.
    +                        String removeId = reader.readLine().trim();
    src/main/java/org/example/client/ScriptExecutor.java:93: trailing whitespace.
    +                        request = new Request(command, new String[]{removeId}, null);
    src/main/java/org/example/client/ScriptExecutor.java:94: trailing whitespace.
    +                        break;
    src/main/java/org/example/client/ScriptExecutor.java:95: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:96: trailing whitespace.
    +                    case "remove_greater":
    src/main/java/org/example/client/ScriptExecutor.java:97: trailing whitespace.
    +                    case "remove_lower":
    src/main/java/org/example/client/ScriptExecutor.java:98: trailing whitespace.
    +                        Ticket compareTicket = (Ticket) factory.createTicket();
    src/main/java/org/example/client/ScriptExecutor.java:99: trailing whitespace.
    +                        request = new Request(command, new String[0], compareTicket);
    src/main/java/org/example/client/ScriptExecutor.java:100: trailing whitespace.
    +                        break;
    src/main/java/org/example/client/ScriptExecutor.java:101: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:102: trailing whitespace.
    +                    case "execute_script":
    src/main/java/org/example/client/ScriptExecutor.java:103: trailing whitespace.
    +                        String nestedPath = reader.readLine().trim();
    src/main/java/org/example/client/ScriptExecutor.java:104: trailing whitespace.
    +                        executeScript(nestedPath, socket);  // рекурсивный вызов
    src/main/java/org/example/client/ScriptExecutor.java:105: trailing whitespace.
    +                        continue;
    src/main/java/org/example/client/ScriptExecutor.java:106: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:107: trailing whitespace.
    +                    default:
    src/main/java/org/example/client/ScriptExecutor.java:108: trailing whitespace.
    +                        request = new Request(command, new String[0], null);
    src/main/java/org/example/client/ScriptExecutor.java:109: trailing whitespace.
    +                }
    src/main/java/org/example/client/ScriptExecutor.java:110: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:111: trailing whitespace.
    +                if (request != null) {
    src/main/java/org/example/client/ScriptExecutor.java:112: trailing whitespace.
    +                    Client.sendRequest(request, socket);
    src/main/java/org/example/client/ScriptExecutor.java:113: trailing whitespace.
    +                    Response response = Client.receiveResponse(socket);
    src/main/java/org/example/client/ScriptExecutor.java:114: trailing whitespace.
    +                    System.out.println("Server response: " + response.getMessage());
    src/main/java/org/example/client/ScriptExecutor.java:115: trailing whitespace.
    +                }
    src/main/java/org/example/client/ScriptExecutor.java:116: trailing whitespace.
    +            }
    src/main/java/org/example/client/ScriptExecutor.java:117: trailing whitespace.
    +
    src/main/java/org/example/client/ScriptExecutor.java:118: trailing whitespace.
    +        } catch (IOException | ClassNotFoundException e) {
    src/main/java/org/example/client/ScriptExecutor.java:119: trailing whitespace.
    +            System.err.println("Error executing script: " + e.getMessage());
    src/main/java/org/example/client/ScriptExecutor.java:120: trailing whitespace.
    +        } catch (InterruptedException e) {
    src/main/java/org/example/client/ScriptExecutor.java:121: trailing whitespace.
    +            throw new RuntimeException(e);
    src/main/java/org/example/client/ScriptExecutor.java:122: trailing whitespace.
    +        } catch (Exception e) {
    src/main/java/org/example/client/ScriptExecutor.java:123: trailing whitespace.
    +            throw new RuntimeException(e);
    src/main/java/org/example/client/ScriptExecutor.java:124: trailing whitespace.
    +        } finally {
    src/main/java/org/example/client/ScriptExecutor.java:125: trailing whitespace.
    +            if (!executedScripts.isEmpty() && executedScripts.peek().equals(filePath)) {
    src/main/java/org/example/client/ScriptExecutor.java:126: trailing whitespace.
    +                executedScripts.pop();
    src/main/java/org/example/client/ScriptExecutor.java:127: trailing whitespace.
    +            } else {
    src/main/java/org/example/client/ScriptExecutor.java:128: trailing whitespace.
    +                executedScripts.remove(filePath);
    src/main/java/org/example/client/ScriptExecutor.java:129: trailing whitespace.
    +            }
    src/main/java/org/example/client/ScriptExecutor.java:130: trailing whitespace.
    +        }
    src/main/java/org/example/client/ScriptExecutor.java:131: trailing whitespace.
    +    }
    src/main/java/org/example/client/ScriptExecutor.java:132: trailing whitespace.
    +}
    src/main/java/org/example/server/Server.java:1: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:2: trailing whitespace.
    +package org.example.server;
    src/main/java/org/example/server/Server.java:3: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:4: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:5: trailing whitespace.
    +import org.example.server.manager.CollectionManager;
    src/main/java/org/example/server/Server.java:6: trailing whitespace.
    +import org.example.server.manager.CommandManager;
    src/main/java/org/example/server/Server.java:7: trailing whitespace.
    +import org.example.common.Request;
    src/main/java/org/example/server/Server.java:8: trailing whitespace.
    +import org.example.common.Response;
    src/main/java/org/example/server/Server.java:9: trailing whitespace.
    +import org.example.server.manager.DataBaseManager;
    src/main/java/org/example/server/Server.java:10: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:11: trailing whitespace.
    +import java.io.*;
    src/main/java/org/example/server/Server.java:12: trailing whitespace.
    +import java.net.InetAddress;
    src/main/java/org/example/server/Server.java:13: trailing whitespace.
    +import java.net.InetSocketAddress;
    src/main/java/org/example/server/Server.java:14: trailing whitespace.
    +import java.net.SocketException;
    src/main/java/org/example/server/Server.java:15: trailing whitespace.
    +import java.nio.ByteBuffer;
    src/main/java/org/example/server/Server.java:16: trailing whitespace.
    +import java.nio.channels.*;
    src/main/java/org/example/server/Server.java:17: trailing whitespace.
    +import java.util.*;
    src/main/java/org/example/server/Server.java:18: trailing whitespace.
    +import java.util.concurrent.*;
    src/main/java/org/example/server/Server.java:19: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:20: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:21: trailing whitespace.
    +public class Server {
    src/main/java/org/example/server/Server.java:22: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:23: trailing whitespace.
    +    private final CollectionManager collectionManager;
    src/main/java/org/example/server/Server.java:24: trailing whitespace.
    +    private final CommandManager commandManager;
    src/main/java/org/example/server/Server.java:25: trailing whitespace.
    +    private final Set<String> authorizedUsers = Collections.synchronizedSet(new HashSet<>());
    src/main/java/org/example/server/Server.java:26: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:27: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:28: trailing whitespace.
    +    // Пул потоков для чтения запросов
    src/main/java/org/example/server/Server.java:29: trailing whitespace.
    +    private final ExecutorService readingPool = Executors.newCachedThreadPool();
    src/main/java/org/example/server/Server.java:30: trailing whitespace.
    +    // Пул потоков для отправки ответов
    src/main/java/org/example/server/Server.java:31: trailing whitespace.
    +    private final ExecutorService sendingPool = Executors.newFixedThreadPool(10);
    src/main/java/org/example/server/Server.java:32: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:33: trailing whitespace.
    +    public Server() {
    src/main/java/org/example/server/Server.java:34: trailing whitespace.
    +        this.collectionManager = new CollectionManager();
    src/main/java/org/example/server/Server.java:35: trailing whitespace.
    +        this.commandManager = new CommandManager(collectionManager);
    src/main/java/org/example/server/Server.java:36: trailing whitespace.
    +    }
    src/main/java/org/example/server/Server.java:37: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:38: trailing whitespace.
    +    public void start(String[] args) throws IOException {
    src/main/java/org/example/server/Server.java:39: trailing whitespace.
    +        DataBaseManager.connectToDataBase();
    src/main/java/org/example/server/Server.java:40: trailing whitespace.
    +        DataBaseManager.getDataFromDatabase(collectionManager);
    src/main/java/org/example/server/Server.java:41: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:42: trailing whitespace.
    +        int port = Integer.parseInt(args[0]);
    src/main/java/org/example/server/Server.java:43: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:44: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:45: trailing whitespace.
    +        ServerSocketChannel serverChannel = ServerSocketChannel.open();
    src/main/java/org/example/server/Server.java:46: trailing whitespace.
    +        serverChannel.configureBlocking(false);
    src/main/java/org/example/server/Server.java:47: trailing whitespace.
    +        serverChannel.bind(new InetSocketAddress(InetAddress.getByName("localhost"), port));
    src/main/java/org/example/server/Server.java:48: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:49: trailing whitespace.
    +        Selector selector = Selector.open();
    src/main/java/org/example/server/Server.java:50: trailing whitespace.
    +        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    src/main/java/org/example/server/Server.java:51: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:52: trailing whitespace.
    +        System.out.println("TCP server is running on port " + port);
    src/main/java/org/example/server/Server.java:53: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:54: trailing whitespace.
    +        while (true) {
    src/main/java/org/example/server/Server.java:55: trailing whitespace.
    +            selector.select();
    src/main/java/org/example/server/Server.java:56: trailing whitespace.
    +            Set<SelectionKey> selectedKeys = selector.selectedKeys();
    src/main/java/org/example/server/Server.java:57: trailing whitespace.
    +            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
    src/main/java/org/example/server/Server.java:58: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:59: trailing whitespace.
    +            while (keyIterator.hasNext()) {
    src/main/java/org/example/server/Server.java:60: trailing whitespace.
    +                SelectionKey key = keyIterator.next();
    src/main/java/org/example/server/Server.java:61: trailing whitespace.
    +                if (key.isAcceptable()) {
    src/main/java/org/example/server/Server.java:62: trailing whitespace.
    +                    handleAccept(key, selector);
    src/main/java/org/example/server/Server.java:63: trailing whitespace.
    +                } else if (key.isReadable()) {
    src/main/java/org/example/server/Server.java:64: trailing whitespace.
    +                    handleRead(key, selector);
    src/main/java/org/example/server/Server.java:65: trailing whitespace.
    +                }
    src/main/java/org/example/server/Server.java:66: trailing whitespace.
    +                keyIterator.remove();
    src/main/java/org/example/server/Server.java:67: trailing whitespace.
    +            }
    src/main/java/org/example/server/Server.java:68: trailing whitespace.
    +        }
    src/main/java/org/example/server/Server.java:69: trailing whitespace.
    +    }
    src/main/java/org/example/server/Server.java:70: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:71: trailing whitespace.
    +    private void handleAccept(SelectionKey key, Selector selector) throws IOException {
    src/main/java/org/example/server/Server.java:72: trailing whitespace.
    +        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
    src/main/java/org/example/server/Server.java:73: trailing whitespace.
    +        SocketChannel clientChannel = serverChannel.accept();
    src/main/java/org/example/server/Server.java:74: trailing whitespace.
    +        clientChannel.configureBlocking(false);
    src/main/java/org/example/server/Server.java:75: trailing whitespace.
    +        clientChannel.register(selector, SelectionKey.OP_READ);
    src/main/java/org/example/server/Server.java:76: trailing whitespace.
    +        System.out.println("New connection from " + clientChannel.getRemoteAddress());
    src/main/java/org/example/server/Server.java:77: trailing whitespace.
    +    }
    src/main/java/org/example/server/Server.java:78: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:79: trailing whitespace.
    +    private void handleRead(SelectionKey key, Selector selector) throws IOException {
    src/main/java/org/example/server/Server.java:80: trailing whitespace.
    +        SocketChannel clientChannel = (SocketChannel) key.channel();
    src/main/java/org/example/server/Server.java:81: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:82: trailing whitespace.
    +        // Передаем обработку чтения в cached thread pool
    src/main/java/org/example/server/Server.java:83: trailing whitespace.
    +        readingPool.submit(() -> {
    src/main/java/org/example/server/Server.java:84: trailing whitespace.
    +            ByteArrayOutputStream baos = (ByteArrayOutputStream) key.attachment();
    src/main/java/org/example/server/Server.java:85: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:86: trailing whitespace.
    +            if (baos == null) {
    src/main/java/org/example/server/Server.java:87: trailing whitespace.
    +                baos = new ByteArrayOutputStream();
    src/main/java/org/example/server/Server.java:88: trailing whitespace.
    +                key.attach(baos);
    src/main/java/org/example/server/Server.java:89: trailing whitespace.
    +            }
    src/main/java/org/example/server/Server.java:90: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:91: trailing whitespace.
    +            ByteBuffer buffer = ByteBuffer.allocate(8192);
    src/main/java/org/example/server/Server.java:92: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:93: trailing whitespace.
    +            try {
    src/main/java/org/example/server/Server.java:94: trailing whitespace.
    +                int bytesRead = clientChannel.read(buffer);
    src/main/java/org/example/server/Server.java:95: trailing whitespace.
    +                if (bytesRead == -1) {
    src/main/java/org/example/server/Server.java:96: trailing whitespace.
    +                    System.err.println("Client " + clientChannel.getRemoteAddress() + " disconnected");
    src/main/java/org/example/server/Server.java:97: trailing whitespace.
    +                    clientChannel.close();
    src/main/java/org/example/server/Server.java:98: trailing whitespace.
    +                    key.cancel();
    src/main/java/org/example/server/Server.java:99: trailing whitespace.
    +                    return null;
    src/main/java/org/example/server/Server.java:100: trailing whitespace.
    +                }
    src/main/java/org/example/server/Server.java:101: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:102: trailing whitespace.
    +                if (bytesRead > 0) {
    src/main/java/org/example/server/Server.java:103: trailing whitespace.
    +                    buffer.flip();
    src/main/java/org/example/server/Server.java:104: trailing whitespace.
    +                    baos.write(buffer.array(), 0, buffer.limit());
    src/main/java/org/example/server/Server.java:105: trailing whitespace.
    +                    buffer.clear();
    src/main/java/org/example/server/Server.java:106: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:107: trailing whitespace.
    +                    byte[] data = baos.toByteArray();
    src/main/java/org/example/server/Server.java:108: trailing whitespace.
    +                    if (data.length > 0) {
    src/main/java/org/example/server/Server.java:109: trailing whitespace.
    +                        try (ByteArrayInputStream bi = new ByteArrayInputStream(data);
    src/main/java/org/example/server/Server.java:110: trailing whitespace.
    +                             ObjectInputStream oi = new ObjectInputStream(bi)) {
    src/main/java/org/example/server/Server.java:111: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:112: trailing whitespace.
    +                            Request request = (Request) oi.readObject();
    src/main/java/org/example/server/Server.java:113: trailing whitespace.
    +                            System.out.println("Received request from client: " + clientChannel.getRemoteAddress());
    src/main/java/org/example/server/Server.java:114: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:115: trailing whitespace.
    +                            baos.reset();
    src/main/java/org/example/server/Server.java:116: trailing whitespace.
    +                            key.attach(null);
    src/main/java/org/example/server/Server.java:117: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:118: trailing whitespace.
    +                            // Обработку запроса выполняем в новом потоке
    src/main/java/org/example/server/Server.java:119: trailing whitespace.
    +                            Thread processingThread = new Thread(() -> {
    src/main/java/org/example/server/Server.java:120: trailing whitespace.
    +                                Response response = processRequest(request, clientChannel);
    src/main/java/org/example/server/Server.java:121: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:122: trailing whitespace.
    +                                // Отправку ответа выполняем в fixed thread pool
    src/main/java/org/example/server/Server.java:123: trailing whitespace.
    +                                sendingPool.submit(() -> {
    src/main/java/org/example/server/Server.java:124: trailing whitespace.
    +                                    try {
    src/main/java/org/example/server/Server.java:125: trailing whitespace.
    +                                        sendResponse(clientChannel, response);
    src/main/java/org/example/server/Server.java:126: trailing whitespace.
    +                                        clientChannel.register(selector, SelectionKey.OP_READ);
    src/main/java/org/example/server/Server.java:127: trailing whitespace.
    +                                    } catch (IOException e) {
    src/main/java/org/example/server/Server.java:128: trailing whitespace.
    +                                        System.err.println("Error sending response: " + e.getMessage());
    src/main/java/org/example/server/Server.java:129: trailing whitespace.
    +                                    }
    src/main/java/org/example/server/Server.java:130: trailing whitespace.
    +                                });
    src/main/java/org/example/server/Server.java:131: trailing whitespace.
    +                            });
    src/main/java/org/example/server/Server.java:132: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:133: trailing whitespace.
    +                            processingThread.start();
    src/main/java/org/example/server/Server.java:134: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:135: trailing whitespace.
    +                        } catch (StreamCorruptedException | EOFException e) {
    src/main/java/org/example/server/Server.java:136: trailing whitespace.
    +                            // Waiting for more data
    src/main/java/org/example/server/Server.java:137: trailing whitespace.
    +                        } catch (ClassNotFoundException e) {
    src/main/java/org/example/server/Server.java:138: trailing whitespace.
    +                            System.err.println("Unknown class received: " + e.getMessage());
    src/main/java/org/example/server/Server.java:139: trailing whitespace.
    +                            sendingPool.submit(() -> {
    src/main/java/org/example/server/Server.java:140: trailing whitespace.
    +                                try {
    src/main/java/org/example/server/Server.java:141: trailing whitespace.
    +                                    sendResponse(clientChannel, new Response("Deserialization error: " + e.getMessage(), null));
    src/main/java/org/example/server/Server.java:142: trailing whitespace.
    +                                } catch (IOException ex) {
    src/main/java/org/example/server/Server.java:143: trailing whitespace.
    +                                    System.err.println("Error sending error response: " + ex.getMessage());
    src/main/java/org/example/server/Server.java:144: trailing whitespace.
    +                                }
    src/main/java/org/example/server/Server.java:145: trailing whitespace.
    +                            });
    src/main/java/org/example/server/Server.java:146: trailing whitespace.
    +                        }
    src/main/java/org/example/server/Server.java:147: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:148: trailing whitespace.
    +                    }
    src/main/java/org/example/server/Server.java:149: trailing whitespace.
    +                }
    src/main/java/org/example/server/Server.java:150: trailing whitespace.
    +            } catch (SocketException e) {
    src/main/java/org/example/server/Server.java:151: trailing whitespace.
    +                System.err.println("Connection reset: " + e.getMessage());
    src/main/java/org/example/server/Server.java:152: trailing whitespace.
    +                try {
    src/main/java/org/example/server/Server.java:153: trailing whitespace.
    +                    clientChannel.close();
    src/main/java/org/example/server/Server.java:154: trailing whitespace.
    +                    key.cancel();
    src/main/java/org/example/server/Server.java:155: trailing whitespace.
    +                } catch (IOException ex) {
    src/main/java/org/example/server/Server.java:156: trailing whitespace.
    +                    System.err.println("Error closing channel: " + ex.getMessage());
    src/main/java/org/example/server/Server.java:157: trailing whitespace.
    +                }
    src/main/java/org/example/server/Server.java:158: trailing whitespace.
    +            } catch (IOException e) {
    src/main/java/org/example/server/Server.java:159: trailing whitespace.
    +                System.err.println("IO error during reading: " + e.getMessage());
    src/main/java/org/example/server/Server.java:160: trailing whitespace.
    +            }
    src/main/java/org/example/server/Server.java:161: trailing whitespace.
    +            return null;
    src/main/java/org/example/server/Server.java:162: trailing whitespace.
    +        });
    src/main/java/org/example/server/Server.java:163: trailing whitespace.
    +    }
    src/main/java/org/example/server/Server.java:164: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:165: trailing whitespace.
    +    private Response processRequest(Request request, SocketChannel clientChannel) {
    src/main/java/org/example/server/Server.java:166: trailing whitespace.
    +        String login = request.getLogin();
    src/main/java/org/example/server/Server.java:167: trailing whitespace.
    +        String password = request.getPassword();
    src/main/java/org/example/server/Server.java:168: trailing whitespace.
    +        String command = request.getCommandName();
    src/main/java/org/example/server/Server.java:169: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:170: trailing whitespace.
    +        if ("logout".equalsIgnoreCase(command)) {
    src/main/java/org/example/server/Server.java:171: trailing whitespace.
    +            if (authorizedUsers.remove(login)) {
    src/main/java/org/example/server/Server.java:172: trailing whitespace.
    +                return new Response("Logged out successfully.", null);
    src/main/java/org/example/server/Server.java:173: trailing whitespace.
    +            } else {
    src/main/java/org/example/server/Server.java:174: trailing whitespace.
    +                return new Response("You are not logged in.", null);
    src/main/java/org/example/server/Server.java:175: trailing whitespace.
    +            }
    src/main/java/org/example/server/Server.java:176: trailing whitespace.
    +        }
    src/main/java/org/example/server/Server.java:177: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:178: trailing whitespace.
    +        if ("login".equalsIgnoreCase(command)) {
    src/main/java/org/example/server/Server.java:179: trailing whitespace.
    +            if (DataBaseManager.checkUser(login, password)) {
    src/main/java/org/example/server/Server.java:180: trailing whitespace.
    +                authorizedUsers.add(login);
    src/main/java/org/example/server/Server.java:181: trailing whitespace.
    +                return new Response("You are logged in!", null);
    src/main/java/org/example/server/Server.java:182: trailing whitespace.
    +            } else {
    src/main/java/org/example/server/Server.java:183: trailing whitespace.
    +                return new Response("Invalid login or password.", null);
    src/main/java/org/example/server/Server.java:184: trailing whitespace.
    +            }
    src/main/java/org/example/server/Server.java:185: trailing whitespace.
    +        }
    src/main/java/org/example/server/Server.java:186: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:187: trailing whitespace.
    +        if ("register".equalsIgnoreCase(command)) {
    src/main/java/org/example/server/Server.java:188: trailing whitespace.
    +            if (DataBaseManager.insertUser(login, password)) {
    src/main/java/org/example/server/Server.java:189: trailing whitespace.
    +                authorizedUsers.add(login);
    src/main/java/org/example/server/Server.java:190: trailing whitespace.
    +                return new Response("Registration successful! You are logged in.", null);
    src/main/java/org/example/server/Server.java:191: trailing whitespace.
    +            } else {
    src/main/java/org/example/server/Server.java:192: trailing whitespace.
    +                return new Response("Registration failed.", null);
    src/main/java/org/example/server/Server.java:193: trailing whitespace.
    +            }
    src/main/java/org/example/server/Server.java:194: trailing whitespace.
    +        }
    src/main/java/org/example/server/Server.java:195: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:196: trailing whitespace.
    +        // Проверка авторизации для других команд
    src/main/java/org/example/server/Server.java:197: trailing whitespace.
    +        if (!authorizedUsers.contains(login)) {
    src/main/java/org/example/server/Server.java:198: trailing whitespace.
    +            return new Response("Please, log in first.", null);
    src/main/java/org/example/server/Server.java:199: trailing whitespace.
    +        }
    src/main/java/org/example/server/Server.java:200: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:201: trailing whitespace.
    +        if ("rename_ticket".equals(command) && !DataBaseManager.checkUser(login, password)) {
    src/main/java/org/example/server/Server.java:202: trailing whitespace.
    +            return new Response("Invalid login or password.", null);
    src/main/java/org/example/server/Server.java:203: trailing whitespace.
    +        }
    src/main/java/org/example/server/Server.java:204: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:205: trailing whitespace.
    +        // Выполнение команды
    src/main/java/org/example/server/Server.java:206: trailing whitespace.
    +        try {
    src/main/java/org/example/server/Server.java:207: trailing whitespace.
    +            return commandManager.doCommand(request, collectionManager);
    src/main/java/org/example/server/Server.java:208: trailing whitespace.
    +        } catch (Exception e) {
    src/main/java/org/example/server/Server.java:209: trailing whitespace.
    +            return new Response("Command execution error: " + e.getMessage(), null);
    src/main/java/org/example/server/Server.java:210: trailing whitespace.
    +        }
    src/main/java/org/example/server/Server.java:211: trailing whitespace.
    +    }
    src/main/java/org/example/server/Server.java:212: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:213: trailing whitespace.
    +    private void sendResponse(SocketChannel clientChannel, Response response) throws IOException {
    src/main/java/org/example/server/Server.java:214: trailing whitespace.
    +        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
    src/main/java/org/example/server/Server.java:215: trailing whitespace.
    +             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
    src/main/java/org/example/server/Server.java:216: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:217: trailing whitespace.
    +            oos.writeObject(response);
    src/main/java/org/example/server/Server.java:218: trailing whitespace.
    +            oos.flush();
    src/main/java/org/example/server/Server.java:219: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:220: trailing whitespace.
    +            byte[] data = baos.toByteArray();
    src/main/java/org/example/server/Server.java:221: trailing whitespace.
    +            ByteBuffer buffer = ByteBuffer.wrap(data);
    src/main/java/org/example/server/Server.java:222: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:223: trailing whitespace.
    +            int chunkSize = 8192;
    src/main/java/org/example/server/Server.java:224: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:225: trailing whitespace.
    +            while (buffer.hasRemaining()) {
    src/main/java/org/example/server/Server.java:226: trailing whitespace.
    +                int length = Math.min(chunkSize, buffer.remaining());
    src/main/java/org/example/server/Server.java:227: trailing whitespace.
    +                ByteBuffer chunkBuffer = ByteBuffer.wrap(data, buffer.position(), length);
    src/main/java/org/example/server/Server.java:228: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:229: trailing whitespace.
    +                int bytesWritten = clientChannel.write(chunkBuffer);
    src/main/java/org/example/server/Server.java:230: trailing whitespace.
    +                buffer.position(buffer.position() + bytesWritten);
    src/main/java/org/example/server/Server.java:231: trailing whitespace.
    +            }
    src/main/java/org/example/server/Server.java:232: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:233: trailing whitespace.
    +            System.out.println("Response sent to client.");
    src/main/java/org/example/server/Server.java:234: trailing whitespace.
    +        }
    src/main/java/org/example/server/Server.java:235: trailing whitespace.
    +    }
    src/main/java/org/example/server/Server.java:236: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:237: trailing whitespace.
    +    public static void main(String[] args) throws IOException {
    src/main/java/org/example/server/Server.java:238: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:239: trailing whitespace.
    +        Server server = new Server();
    src/main/java/org/example/server/Server.java:240: trailing whitespace.
    +        server.start(args);
    src/main/java/org/example/server/Server.java:241: trailing whitespace.
    +    }
    src/main/java/org/example/server/Server.java:242: trailing whitespace.
    +
    src/main/java/org/example/server/Server.java:243: trailing whitespace.
    +}
    src/main/java/org/example/server/command/HelpCommand.java:1: trailing whitespace.
    +package org.example.server.command;
    src/main/java/org/example/server/command/HelpCommand.java:2: trailing whitespace.
    +
    src/main/java/org/example/server/command/HelpCommand.java:3: trailing whitespace.
    +import org.example.common.Request;
    src/main/java/org/example/server/command/HelpCommand.java:4: trailing whitespace.
    +import org.example.common.Response;
    src/main/java/org/example/server/command/HelpCommand.java:5: trailing whitespace.
    +import org.example.server.manager.CollectionManager;
    src/main/java/org/example/server/command/HelpCommand.java:6: trailing whitespace.
    +
    src/main/java/org/example/server/command/HelpCommand.java:7: trailing whitespace.
    +/**
    src/main/java/org/example/server/command/HelpCommand.java:8: trailing whitespace.
    + * <p>Команда для отображения справки по всем доступным командам.</p>
    src/main/java/org/example/server/command/HelpCommand.java:9: trailing whitespace.
    + * <p>Выводит список всех команд с кратким описанием.</p>
    src/main/java/org/example/server/command/HelpCommand.java:10: trailing whitespace.
    + */
    src/main/java/org/example/server/command/HelpCommand.java:11: trailing whitespace.
    +public class HelpCommand extends AbstractCommand {
    src/main/java/org/example/server/command/HelpCommand.java:12: trailing whitespace.
    +
    src/main/java/org/example/server/command/HelpCommand.java:13: trailing whitespace.
    +    /**
    src/main/java/org/example/server/command/HelpCommand.java:14: trailing whitespace.
    +     * Выполняет команду help.
    src/main/java/org/example/server/command/HelpCommand.java:15: trailing whitespace.
    +     *
    src/main/java/org/example/server/command/HelpCommand.java:16: trailing whitespace.
    +     * @param request объект запроса, содержащий аргументы команды
    src/main/java/org/example/server/command/HelpCommand.java:17: trailing whitespace.
    +     * @param collectionManager менеджер коллекции
    src/main/java/org/example/server/command/HelpCommand.java:18: trailing whitespace.
    +     * @return объект ответа с описанием доступных команд
    src/main/java/org/example/server/command/HelpCommand.java:19: trailing whitespace.
    +     */
    src/main/java/org/example/server/command/HelpCommand.java:20: trailing whitespace.
    +    @Override
    src/main/java/org/example/server/command/HelpCommand.java:21: trailing whitespace.
    +    public Response execute(Request request, CollectionManager collectionManager) {
    src/main/java/org/example/server/command/HelpCommand.java:22: trailing whitespace.
    +        String helpText = (
    src/main/java/org/example/server/command/HelpCommand.java:23: trailing whitespace.
    +                "Доступные команды:\n" +
    src/main/java/org/example/server/command/HelpCommand.java:24: trailing whitespace.
    +                        "help : вывести справку по командам\n" +
    src/main/java/org/example/server/command/HelpCommand.java:25: trailing whitespace.
    +                        "info : вывести информацию о коллекции\n" +
    src/main/java/org/example/server/command/HelpCommand.java:26: trailing whitespace.
    +                        "show : вывести все элементы коллекции\n" +
    src/main/java/org/example/server/command/HelpCommand.java:27: trailing whitespace.
    +                        "add {element} : добавить новый элемент\n" +
    src/main/java/org/example/server/command/HelpCommand.java:28: trailing whitespace.
    +                        "update id {element} : обновить элемент по id\n" +
    src/main/java/org/example/server/command/HelpCommand.java:29: trailing whitespace.
    +                        "rename_ticket : изменить название своего билета (ID и название на следующих строках)\n" +
    src/main/java/org/example/server/command/HelpCommand.java:30: trailing whitespace.
    +                        "remove_by_id id : удалить элемент по id\n" +
    src/main/java/org/example/server/command/HelpCommand.java:31: trailing whitespace.
    +                        "clear : очистить коллекцию\n" +
    src/main/java/org/example/server/command/HelpCommand.java:32: trailing whitespace.
    +                        "save : сохранить коллекцию в файл\n" +
    src/main/java/org/example/server/command/HelpCommand.java:33: trailing whitespace.
    +                        "execute_script file_name : выполнить скрипт из файла\n" +
    src/main/java/org/example/server/command/HelpCommand.java:34: trailing whitespace.
    +                        "exit : завершить программу\n" +
    src/main/java/org/example/server/command/HelpCommand.java:35: trailing whitespace.
    +                        "add_if_max : добавить элемент в коллекцию, если его значение превышает значение наибольшего элемента коллекции\n" +
    src/main/java/org/example/server/command/HelpCommand.java:36: trailing whitespace.
    +                        "remove_greater {element} : удалить элемент, превышающий заданный\n" +
    src/main/java/org/example/server/command/HelpCommand.java:37: trailing whitespace.
    +                        "remove_lower {element} : удалить элемент, меньше заданного\n" +
    src/main/java/org/example/server/command/HelpCommand.java:38: trailing whitespace.
    +                        "filter_starts_with_name name : вывести элементы, у которых name начинается с заданной подстроки\n" +
    src/main/java/org/example/server/command/HelpCommand.java:39: trailing whitespace.
    +                        "print_descending : вывести элементы в порядке убывания\n" +
    src/main/java/org/example/server/command/HelpCommand.java:40: trailing whitespace.
    +                        "print_field_descending_discount : вывести значения поля discount в порядке убывания"
    src/main/java/org/example/server/command/HelpCommand.java:41: trailing whitespace.
    +        );
    src/main/java/org/example/server/command/HelpCommand.java:42: trailing whitespace.
    +
    src/main/java/org/example/server/command/HelpCommand.java:43: trailing whitespace.
    +        logger.info("Help command executed.");
    src/main/java/org/example/server/command/HelpCommand.java:44: trailing whitespace.
    +        return new Response(helpText, null);
    src/main/java/org/example/server/command/HelpCommand.java:45: trailing whitespace.
    +    }
    src/main/java/org/example/server/command/HelpCommand.java:46: trailing whitespace.
    +}
    src/main/java/org/example/server/manager/CollectionManager.java:1: trailing whitespace.
    +package org.example.server.manager;
    src/main/java/org/example/server/manager/CollectionManager.java:2: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:3: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:4: trailing whitespace.
    +import org.example.client.TicketInput;
    src/main/java/org/example/server/manager/CollectionManager.java:5: trailing whitespace.
    +import org.example.common.model.Ticket;
    src/main/java/org/example/server/manager/CollectionManager.java:6: trailing whitespace.
    +import org.slf4j.Logger;
    src/main/java/org/example/server/manager/CollectionManager.java:7: trailing whitespace.
    +import org.slf4j.LoggerFactory;
    src/main/java/org/example/server/manager/CollectionManager.java:8: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:9: trailing whitespace.
    +import java.time.LocalDateTime;
    src/main/java/org/example/server/manager/CollectionManager.java:10: trailing whitespace.
    +import java.util.Iterator;
    src/main/java/org/example/server/manager/CollectionManager.java:11: trailing whitespace.
    +import java.util.LinkedHashSet;
    src/main/java/org/example/server/manager/CollectionManager.java:12: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:13: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:14: trailing whitespace.
    +public class CollectionManager {
    src/main/java/org/example/server/manager/CollectionManager.java:15: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:16: trailing whitespace.
    +    @FunctionalInterface
    src/main/java/org/example/server/manager/CollectionManager.java:17: trailing whitespace.
    +    public interface TicketNameStore {
    src/main/java/org/example/server/manager/CollectionManager.java:18: trailing whitespace.
    +        boolean rename(long id, String name, String login) throws java.sql.SQLException;
    src/main/java/org/example/server/manager/CollectionManager.java:19: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CollectionManager.java:20: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:21: trailing whitespace.
    +    private final TicketNameStore ticketNameStore;
    src/main/java/org/example/server/manager/CollectionManager.java:22: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:23: trailing whitespace.
    +    private final LocalDateTime timeOfInitial = LocalDateTime.now();
    src/main/java/org/example/server/manager/CollectionManager.java:24: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:25: trailing whitespace.
    +    private LinkedHashSet<Ticket> collection = new LinkedHashSet<>();
    src/main/java/org/example/server/manager/CollectionManager.java:26: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:27: trailing whitespace.
    +    private static final Logger logger = LoggerFactory.getLogger(CollectionManager.class);
    src/main/java/org/example/server/manager/CollectionManager.java:28: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:29: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:30: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:31: trailing whitespace.
    +    public CollectionManager() {
    src/main/java/org/example/server/manager/CollectionManager.java:32: trailing whitespace.
    +        this(DataBaseManager::renameTicketById);
    src/main/java/org/example/server/manager/CollectionManager.java:33: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CollectionManager.java:34: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:35: trailing whitespace.
    +    public CollectionManager(TicketNameStore ticketNameStore) {
    src/main/java/org/example/server/manager/CollectionManager.java:36: trailing whitespace.
    +        this.ticketNameStore = java.util.Objects.requireNonNull(ticketNameStore);
    src/main/java/org/example/server/manager/CollectionManager.java:37: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CollectionManager.java:38: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:39: trailing whitespace.
    +    public void add(Ticket ticket) {
    src/main/java/org/example/server/manager/CollectionManager.java:40: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:41: trailing whitespace.
    +        synchronized (collection) {
    src/main/java/org/example/server/manager/CollectionManager.java:42: trailing whitespace.
    +            collection.add(ticket);
    src/main/java/org/example/server/manager/CollectionManager.java:43: trailing whitespace.
    +            logger.info("Added ticket: {}", ticket);
    src/main/java/org/example/server/manager/CollectionManager.java:44: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/CollectionManager.java:45: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CollectionManager.java:46: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:47: trailing whitespace.
    +    public void update(Ticket ticket) {
    src/main/java/org/example/server/manager/CollectionManager.java:48: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:49: trailing whitespace.
    +        synchronized (collection) {
    src/main/java/org/example/server/manager/CollectionManager.java:50: trailing whitespace.
    +            collection.remove(ticket);
    src/main/java/org/example/server/manager/CollectionManager.java:51: trailing whitespace.
    +            collection.add(ticket);
    src/main/java/org/example/server/manager/CollectionManager.java:52: trailing whitespace.
    +            logger.info("Updated ticket: {}", ticket);
    src/main/java/org/example/server/manager/CollectionManager.java:53: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/CollectionManager.java:54: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CollectionManager.java:55: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:56: trailing whitespace.
    +    public boolean renameTicket(long id, String name, String login) throws java.sql.SQLException {
    src/main/java/org/example/server/manager/CollectionManager.java:57: trailing whitespace.
    +        synchronized (collection) {
    src/main/java/org/example/server/manager/CollectionManager.java:58: trailing whitespace.
    +            if (!ticketNameStore.rename(id, name, login)) {
    src/main/java/org/example/server/manager/CollectionManager.java:59: trailing whitespace.
    +                return false;
    src/main/java/org/example/server/manager/CollectionManager.java:60: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/CollectionManager.java:61: trailing whitespace.
    +            // Name participates in hashCode: remove before changing it, then rebuild
    src/main/java/org/example/server/manager/CollectionManager.java:62: trailing whitespace.
    +            // the set in the same order so existing entries remain searchable.
    src/main/java/org/example/server/manager/CollectionManager.java:63: trailing whitespace.
    +            LinkedHashSet<Ticket> renamed = new LinkedHashSet<>();
    src/main/java/org/example/server/manager/CollectionManager.java:64: trailing whitespace.
    +            Iterator<Ticket> iterator = collection.iterator();
    src/main/java/org/example/server/manager/CollectionManager.java:65: trailing whitespace.
    +            while (iterator.hasNext()) {
    src/main/java/org/example/server/manager/CollectionManager.java:66: trailing whitespace.
    +                Ticket ticket = iterator.next();
    src/main/java/org/example/server/manager/CollectionManager.java:67: trailing whitespace.
    +                iterator.remove();
    src/main/java/org/example/server/manager/CollectionManager.java:68: trailing whitespace.
    +                if (ticket.getId() == id) {
    src/main/java/org/example/server/manager/CollectionManager.java:69: trailing whitespace.
    +                    ticket.setName(name);
    src/main/java/org/example/server/manager/CollectionManager.java:70: trailing whitespace.
    +                }
    src/main/java/org/example/server/manager/CollectionManager.java:71: trailing whitespace.
    +                renamed.add(ticket);
    src/main/java/org/example/server/manager/CollectionManager.java:72: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/CollectionManager.java:73: trailing whitespace.
    +            collection.addAll(renamed);
    src/main/java/org/example/server/manager/CollectionManager.java:74: trailing whitespace.
    +            return true;
    src/main/java/org/example/server/manager/CollectionManager.java:75: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/CollectionManager.java:76: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CollectionManager.java:77: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:78: trailing whitespace.
    +    public void delete(Ticket ticket) {
    src/main/java/org/example/server/manager/CollectionManager.java:79: trailing whitespace.
    +        synchronized (collection) {
    src/main/java/org/example/server/manager/CollectionManager.java:80: trailing whitespace.
    +            boolean removed = collection.remove(ticket);
    src/main/java/org/example/server/manager/CollectionManager.java:81: trailing whitespace.
    +            if (removed) {
    src/main/java/org/example/server/manager/CollectionManager.java:82: trailing whitespace.
    +                logger.info("Deleted ticket: {}", ticket);
    src/main/java/org/example/server/manager/CollectionManager.java:83: trailing whitespace.
    +            } else {
    src/main/java/org/example/server/manager/CollectionManager.java:84: trailing whitespace.
    +                logger.warn("Attempted to delete non-existent ticket: {}", ticket);
    src/main/java/org/example/server/manager/CollectionManager.java:85: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/CollectionManager.java:86: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/CollectionManager.java:87: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CollectionManager.java:88: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:89: trailing whitespace.
    +    public boolean removeById(long id) {
    src/main/java/org/example/server/manager/CollectionManager.java:90: trailing whitespace.
    +        synchronized (collection) {
    src/main/java/org/example/server/manager/CollectionManager.java:91: trailing whitespace.
    +            Iterator<Ticket> iterator = collection.iterator();
    src/main/java/org/example/server/manager/CollectionManager.java:92: trailing whitespace.
    +            while (iterator.hasNext()) {
    src/main/java/org/example/server/manager/CollectionManager.java:93: trailing whitespace.
    +                Ticket ticket = iterator.next();
    src/main/java/org/example/server/manager/CollectionManager.java:94: trailing whitespace.
    +                if (ticket.getId() == id) {
    src/main/java/org/example/server/manager/CollectionManager.java:95: trailing whitespace.
    +                    iterator.remove(); // Используем iterator.remove() для безопасного удаления во время итерации
    src/main/java/org/example/server/manager/CollectionManager.java:96: trailing whitespace.
    +                    logger.info("Removed ticket with ID {} from collection.", id);
    src/main/java/org/example/server/manager/CollectionManager.java:97: trailing whitespace.
    +                    return true;
    src/main/java/org/example/server/manager/CollectionManager.java:98: trailing whitespace.
    +                }
    src/main/java/org/example/server/manager/CollectionManager.java:99: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/CollectionManager.java:100: trailing whitespace.
    +            logger.warn("Attempted to remove non-existent ticket with ID {}.", id);
    src/main/java/org/example/server/manager/CollectionManager.java:101: trailing whitespace.
    +            return false;
    src/main/java/org/example/server/manager/CollectionManager.java:102: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/CollectionManager.java:103: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CollectionManager.java:104: trailing whitespace.
    +    public void remove_lower(Ticket ticket) {
    src/main/java/org/example/server/manager/CollectionManager.java:105: trailing whitespace.
    +        synchronized (collection) {
    src/main/java/org/example/server/manager/CollectionManager.java:106: trailing whitespace.
    +            Ticket referenceTicket = TicketInput.generateTicket();
    src/main/java/org/example/server/manager/CollectionManager.java:107: trailing whitespace.
    +            logger.info("Reference ticket for comparison: {}", referenceTicket);
    src/main/java/org/example/server/manager/CollectionManager.java:108: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:109: trailing whitespace.
    +            int removedCount = 0;
    src/main/java/org/example/server/manager/CollectionManager.java:110: trailing whitespace.
    +            Iterator<Ticket> iterator = collection.iterator();
    src/main/java/org/example/server/manager/CollectionManager.java:111: trailing whitespace.
    +            while (iterator.hasNext()) {
    src/main/java/org/example/server/manager/CollectionManager.java:112: trailing whitespace.
    +                Ticket currentTicket = iterator.next();
    src/main/java/org/example/server/manager/CollectionManager.java:113: trailing whitespace.
    +                if (referenceTicket.compareTo(currentTicket) > 0) {
    src/main/java/org/example/server/manager/CollectionManager.java:114: trailing whitespace.
    +                    iterator.remove();
    src/main/java/org/example/server/manager/CollectionManager.java:115: trailing whitespace.
    +                    removedCount++;
    src/main/java/org/example/server/manager/CollectionManager.java:116: trailing whitespace.
    +                    logger.info("Removed ticket: {}", currentTicket);
    src/main/java/org/example/server/manager/CollectionManager.java:117: trailing whitespace.
    +                    System.out.println("Deleted ticket: " + currentTicket);
    src/main/java/org/example/server/manager/CollectionManager.java:118: trailing whitespace.
    +                }
    src/main/java/org/example/server/manager/CollectionManager.java:119: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/CollectionManager.java:120: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:121: trailing whitespace.
    +            if (removedCount == 0) {
    src/main/java/org/example/server/manager/CollectionManager.java:122: trailing whitespace.
    +                System.out.println("No tickets were lower than the reference ticket.");
    src/main/java/org/example/server/manager/CollectionManager.java:123: trailing whitespace.
    +                logger.info("No tickets were removed.");
    src/main/java/org/example/server/manager/CollectionManager.java:124: trailing whitespace.
    +            } else {
    src/main/java/org/example/server/manager/CollectionManager.java:125: trailing whitespace.
    +                System.out.println("Total tickets removed: " + removedCount);
    src/main/java/org/example/server/manager/CollectionManager.java:126: trailing whitespace.
    +                logger.info("Total tickets removed: {}", removedCount);
    src/main/java/org/example/server/manager/CollectionManager.java:127: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/CollectionManager.java:128: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/CollectionManager.java:129: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CollectionManager.java:130: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:131: trailing whitespace.
    +    public void remove_greater(Ticket ticket) {
    src/main/java/org/example/server/manager/CollectionManager.java:132: trailing whitespace.
    +        synchronized (collection) {
    src/main/java/org/example/server/manager/CollectionManager.java:133: trailing whitespace.
    +            Ticket referenceTicket = TicketInput.generateTicket();
    src/main/java/org/example/server/manager/CollectionManager.java:134: trailing whitespace.
    +            int removedCount = 0;
    src/main/java/org/example/server/manager/CollectionManager.java:135: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:136: trailing whitespace.
    +            Iterator<Ticket> iterator = collection.iterator();
    src/main/java/org/example/server/manager/CollectionManager.java:137: trailing whitespace.
    +            while (iterator.hasNext()) {
    src/main/java/org/example/server/manager/CollectionManager.java:138: trailing whitespace.
    +                Ticket currentTicket = iterator.next();
    src/main/java/org/example/server/manager/CollectionManager.java:139: trailing whitespace.
    +                if (referenceTicket.compareTo(currentTicket) < 0) {
    src/main/java/org/example/server/manager/CollectionManager.java:140: trailing whitespace.
    +                    iterator.remove();
    src/main/java/org/example/server/manager/CollectionManager.java:141: trailing whitespace.
    +                    removedCount++;
    src/main/java/org/example/server/manager/CollectionManager.java:142: trailing whitespace.
    +                    logger.info("Removed ticket: {}", currentTicket);
    src/main/java/org/example/server/manager/CollectionManager.java:143: trailing whitespace.
    +                    System.out.println("Ticket deleted: " + currentTicket);
    src/main/java/org/example/server/manager/CollectionManager.java:144: trailing whitespace.
    +                }
    src/main/java/org/example/server/manager/CollectionManager.java:145: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/CollectionManager.java:146: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:147: trailing whitespace.
    +            if (removedCount == 0) {
    src/main/java/org/example/server/manager/CollectionManager.java:148: trailing whitespace.
    +                System.out.println("No tickets were greater than the reference ticket.");
    src/main/java/org/example/server/manager/CollectionManager.java:149: trailing whitespace.
    +                logger.info("No tickets were removed. All are less than the reference.");
    src/main/java/org/example/server/manager/CollectionManager.java:150: trailing whitespace.
    +            } else {
    src/main/java/org/example/server/manager/CollectionManager.java:151: trailing whitespace.
    +                System.out.println("Total tickets removed: " + removedCount);
    src/main/java/org/example/server/manager/CollectionManager.java:152: trailing whitespace.
    +                logger.info("Total tickets removed: {}", removedCount);
    src/main/java/org/example/server/manager/CollectionManager.java:153: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/CollectionManager.java:154: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/CollectionManager.java:155: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CollectionManager.java:156: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:157: trailing whitespace.
    +    public LocalDateTime getTimeOfInitial() {
    src/main/java/org/example/server/manager/CollectionManager.java:158: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:159: trailing whitespace.
    +        return timeOfInitial;
    src/main/java/org/example/server/manager/CollectionManager.java:160: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:161: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CollectionManager.java:162: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:163: trailing whitespace.
    +    public LinkedHashSet<Ticket> getCollection() {
    src/main/java/org/example/server/manager/CollectionManager.java:164: trailing whitespace.
    +        synchronized (collection) {
    src/main/java/org/example/server/manager/CollectionManager.java:165: trailing whitespace.
    +            return new LinkedHashSet<>(collection); // Возвращаем копию для безопасности
    src/main/java/org/example/server/manager/CollectionManager.java:166: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/CollectionManager.java:167: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CollectionManager.java:168: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:169: trailing whitespace.
    +    public Ticket getById(long id) {
    src/main/java/org/example/server/manager/CollectionManager.java:170: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:171: trailing whitespace.
    +        Ticket found = collection.stream()
    src/main/java/org/example/server/manager/CollectionManager.java:172: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:173: trailing whitespace.
    +                .filter(t -> t.getId() == id)
    src/main/java/org/example/server/manager/CollectionManager.java:174: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:175: trailing whitespace.
    +                .findFirst()
    src/main/java/org/example/server/manager/CollectionManager.java:176: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:177: trailing whitespace.
    +                .orElse(null);
    src/main/java/org/example/server/manager/CollectionManager.java:178: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:179: trailing whitespace.
    +        if (found != null) {
    src/main/java/org/example/server/manager/CollectionManager.java:180: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:181: trailing whitespace.
    +            logger.info("Found ticket by ID {}: {}", id, found);
    src/main/java/org/example/server/manager/CollectionManager.java:182: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:183: trailing whitespace.
    +        } else {
    src/main/java/org/example/server/manager/CollectionManager.java:184: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:185: trailing whitespace.
    +            logger.warn("No ticket found with ID: {}", id);
    src/main/java/org/example/server/manager/CollectionManager.java:186: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:187: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/CollectionManager.java:188: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:189: trailing whitespace.
    +        return found;
    src/main/java/org/example/server/manager/CollectionManager.java:190: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:191: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CollectionManager.java:192: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:193: trailing whitespace.
    +    public void setCollection(LinkedHashSet<Ticket> newCollection) {
    src/main/java/org/example/server/manager/CollectionManager.java:194: trailing whitespace.
    +        synchronized (collection) {
    src/main/java/org/example/server/manager/CollectionManager.java:195: trailing whitespace.
    +            collection.clear();
    src/main/java/org/example/server/manager/CollectionManager.java:196: trailing whitespace.
    +            collection.addAll(newCollection);
    src/main/java/org/example/server/manager/CollectionManager.java:197: trailing whitespace.
    +            logger.info("Collection replaced with new set (size: {})", newCollection.size());
    src/main/java/org/example/server/manager/CollectionManager.java:198: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/CollectionManager.java:199: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CollectionManager.java:200: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:201: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:202: trailing whitespace.
    +    public boolean add_if_max(Ticket ticket) {
    src/main/java/org/example/server/manager/CollectionManager.java:203: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:204: trailing whitespace.
    +        if (getCollection().isEmpty()) {
    src/main/java/org/example/server/manager/CollectionManager.java:205: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:206: trailing whitespace.
    +            add(ticket);
    src/main/java/org/example/server/manager/CollectionManager.java:207: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:208: trailing whitespace.
    +            logger.info("Added ticket with price: {}", ticket.getPrice());
    src/main/java/org/example/server/manager/CollectionManager.java:209: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:210: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/CollectionManager.java:211: trailing whitespace.
    +        int maxPrice = getCollection().stream()
    src/main/java/org/example/server/manager/CollectionManager.java:212: trailing whitespace.
    +                .mapToInt(Ticket::getPrice)
    src/main/java/org/example/server/manager/CollectionManager.java:213: trailing whitespace.
    +                .max()
    src/main/java/org/example/server/manager/CollectionManager.java:214: trailing whitespace.
    +                .orElse(Integer.MIN_VALUE);
    src/main/java/org/example/server/manager/CollectionManager.java:215: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:216: trailing whitespace.
    +        if (ticket.getPrice() > maxPrice) {
    src/main/java/org/example/server/manager/CollectionManager.java:217: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:218: trailing whitespace.
    +            add(ticket);
    src/main/java/org/example/server/manager/CollectionManager.java:219: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:220: trailing whitespace.
    +            logger.info("Added ticket with price: {}", ticket.getPrice());
    src/main/java/org/example/server/manager/CollectionManager.java:221: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:222: trailing whitespace.
    +        } else {
    src/main/java/org/example/server/manager/CollectionManager.java:223: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:224: trailing whitespace.
    +            logger.info("Not added ticket with price: {} <= {}", ticket.getPrice(), maxPrice);
    src/main/java/org/example/server/manager/CollectionManager.java:225: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:226: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/CollectionManager.java:227: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:228: trailing whitespace.
    +        return false;
    src/main/java/org/example/server/manager/CollectionManager.java:229: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CollectionManager.java:230: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:231: trailing whitespace.
    +    public int clear(String userLogin) {
    src/main/java/org/example/server/manager/CollectionManager.java:232: trailing whitespace.
    +        synchronized (collection) { // Синхронизация коллекции
    src/main/java/org/example/server/manager/CollectionManager.java:233: trailing whitespace.
    +            int initialSize = collection.size();
    src/main/java/org/example/server/manager/CollectionManager.java:234: trailing whitespace.
    +            int userId = DataBaseManager.getUserId(userLogin); // Получаем ID пользователя по его логину
    src/main/java/org/example/server/manager/CollectionManager.java:235: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:236: trailing whitespace.
    +            if (userId == -1) {
    src/main/java/org/example/server/manager/CollectionManager.java:237: trailing whitespace.
    +                logger.warn("Attempted to remove tickets for non-existent user login: {}", userLogin);
    src/main/java/org/example/server/manager/CollectionManager.java:238: trailing whitespace.
    +                return 0; // Пользователь не найден, удалять нечего
    src/main/java/org/example/server/manager/CollectionManager.java:239: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/CollectionManager.java:240: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CollectionManager.java:241: trailing whitespace.
    +            // Используем Iterator для безопасного удаления элементов во время итерации
    src/main/java/org/example/server/manager/CollectionManager.java:242: trailing whitespace.
    +            Iterator<Ticket> iterator = collection.iterator();
    src/main/java/org/example/server/manager/CollectionManager.java:243: trailing whitespace.
    +            int removedCount = 0;
    src/main/java/org/example/server/manager/CollectionManager.java:244: trailing whitespace.
    +            while (iterator.hasNext()) {
    src/main/java/org/example/server/manager/CollectionManager.java:245: trailing whitespace.
    +                Ticket ticket = iterator.next();
    src/main/java/org/example/server/manager/CollectionManager.java:246: trailing whitespace.
    +                // Проверяем, совпадает ли ownerId билета с ID текущего пользователя
    src/main/java/org/example/server/manager/CollectionManager.java:247: trailing whitespace.
    +                if (ticket.getOwnerId() == userId) {
    src/main/java/org/example/server/manager/CollectionManager.java:248: trailing whitespace.
    +                    iterator.remove(); // Безопасное удаление из LinkedHashSet
    src/main/java/org/example/server/manager/CollectionManager.java:249: trailing whitespace.
    +                    removedCount++;
    src/main/java/org/example/server/manager/CollectionManager.java:250: trailing whitespace.
    +                    logger.debug("Removed in-memory ticket with ID {} belonging to user ID {}", ticket.getId(), userId);
    src/main/java/org/example/server/manager/CollectionManager.java:251: trailing whitespace.
    +                }
    src/main/java/org/example/server/manager/CollectionManager.java:252: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/CollectionManager.java:253: trailing whitespace.
    +            logger.info("Removed {} tickets from in-memory collection for user {}.", removedCount, userLogin);
    src/main/java/org/example/server/manager/CollectionManager.java:254: trailing whitespace.
    +            return removedCount;
    src/main/java/org/example/server/manager/CollectionManager.java:255: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/CollectionManager.java:256: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CollectionManager.java:257: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CommandManager.java:1: trailing whitespace.
    +package org.example.server.manager;
    src/main/java/org/example/server/manager/CommandManager.java:2: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CommandManager.java:3: trailing whitespace.
    +import org.example.common.Request;
    src/main/java/org/example/server/manager/CommandManager.java:4: trailing whitespace.
    +import org.example.common.Response;
    src/main/java/org/example/server/manager/CommandManager.java:5: trailing whitespace.
    +import org.example.server.command.*;
    src/main/java/org/example/server/manager/CommandManager.java:6: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CommandManager.java:7: trailing whitespace.
    +import java.util.HashMap;
    src/main/java/org/example/server/manager/CommandManager.java:8: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CommandManager.java:9: trailing whitespace.
    +/**
    src/main/java/org/example/server/manager/CommandManager.java:10: trailing whitespace.
    + * Менеджер команд, осуществляющий регистрацию и выполнение доступных команд.
    src/main/java/org/example/server/manager/CommandManager.java:11: trailing whitespace.
    + * <p>Хранит отображение названий команд на соответствующие классы и передаёт управление при выполнении.</p>
    src/main/java/org/example/server/manager/CommandManager.java:12: trailing whitespace.
    + */
    src/main/java/org/example/server/manager/CommandManager.java:13: trailing whitespace.
    +public class CommandManager {
    src/main/java/org/example/server/manager/CommandManager.java:14: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CommandManager.java:15: trailing whitespace.
    +    private final HashMap<String, AbstractCommand> commands = new HashMap<>();
    src/main/java/org/example/server/manager/CommandManager.java:16: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CommandManager.java:17: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CommandManager.java:18: trailing whitespace.
    +    public CommandManager(CollectionManager collectionManager) {
    src/main/java/org/example/server/manager/CommandManager.java:19: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CommandManager.java:20: trailing whitespace.
    +        // Регистрация всех доступных команд
    src/main/java/org/example/server/manager/CommandManager.java:21: trailing whitespace.
    +        commands.put("info", new InfoCommand());
    src/main/java/org/example/server/manager/CommandManager.java:22: trailing whitespace.
    +        commands.put("add", new AddCommand());
    src/main/java/org/example/server/manager/CommandManager.java:23: trailing whitespace.
    +        commands.put("show", new ShowCommand());
    src/main/java/org/example/server/manager/CommandManager.java:24: trailing whitespace.
    +        commands.put("remove_greater", new RemoveGreaterCommand());
    src/main/java/org/example/server/manager/CommandManager.java:25: trailing whitespace.
    +        commands.put("print_descending", new PrintDescendingCommand());
    src/main/java/org/example/server/manager/CommandManager.java:26: trailing whitespace.
    +        commands.put("help", new HelpCommand());
    src/main/java/org/example/server/manager/CommandManager.java:27: trailing whitespace.
    +        commands.put("update", new UpdateCommand());
    src/main/java/org/example/server/manager/CommandManager.java:28: trailing whitespace.
    +        commands.put("rename_ticket", new RenameTicketCommand());
    src/main/java/org/example/server/manager/CommandManager.java:29: trailing whitespace.
    +        commands.put("remove_by_id", new RemoveByIdCommand());
    src/main/java/org/example/server/manager/CommandManager.java:30: trailing whitespace.
    +        commands.put("clear", new ClearCommand());
    src/main/java/org/example/server/manager/CommandManager.java:31: trailing whitespace.
    +        commands.put("add_if_max", new AddIfMaxCommand());
    src/main/java/org/example/server/manager/CommandManager.java:32: trailing whitespace.
    +        commands.put("remove_lower", new RemoveLowerCommand());
    src/main/java/org/example/server/manager/CommandManager.java:33: trailing whitespace.
    +        commands.put("filter_starts_with", new FilterCommand());
    src/main/java/org/example/server/manager/CommandManager.java:34: trailing whitespace.
    +        commands.put("print_field_descending_discount", new PrintFieldDescendingDiscountCommand());
    src/main/java/org/example/server/manager/CommandManager.java:35: trailing whitespace.
    +        commands.put("exit", new ExitCommand());
    src/main/java/org/example/server/manager/CommandManager.java:36: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CommandManager.java:37: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CommandManager.java:38: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CommandManager.java:39: trailing whitespace.
    +
    src/main/java/org/example/server/manager/CommandManager.java:40: trailing whitespace.
    +    /**
    src/main/java/org/example/server/manager/CommandManager.java:41: trailing whitespace.
    +     * Выполняет команду, соответствующую переданному вводу.
    src/main/java/org/example/server/manager/CommandManager.java:42: trailing whitespace.
    +     *
    src/main/java/org/example/server/manager/CommandManager.java:43: trailing whitespace.
    +     * @param request
    src/main/java/org/example/server/manager/CommandManager.java:44: trailing whitespace.
    +     */
    src/main/java/org/example/server/manager/CommandManager.java:45: trailing whitespace.
    +    public Response doCommand(Request request, CollectionManager collectionManager) {
    src/main/java/org/example/server/manager/CommandManager.java:46: trailing whitespace.
    +        AbstractCommand command = commands.get(request.getCommandName());
    src/main/java/org/example/server/manager/CommandManager.java:47: trailing whitespace.
    +        if (command != null) {
    src/main/java/org/example/server/manager/CommandManager.java:48: trailing whitespace.
    +            try {
    src/main/java/org/example/server/manager/CommandManager.java:49: trailing whitespace.
    +                return command.execute(request, collectionManager);
    src/main/java/org/example/server/manager/CommandManager.java:50: trailing whitespace.
    +            } catch (Exception e) {
    src/main/java/org/example/server/manager/CommandManager.java:51: trailing whitespace.
    +                return new Response("Error executing command: " + e.getMessage(), null);
    src/main/java/org/example/server/manager/CommandManager.java:52: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/CommandManager.java:53: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/CommandManager.java:54: trailing whitespace.
    +        return new Response("Unknown command: " + request.getCommandName(), null);
    src/main/java/org/example/server/manager/CommandManager.java:55: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/CommandManager.java:56: trailing whitespace.
    +}
    src/main/java/org/example/server/manager/DataBaseManager.java:1: trailing whitespace.
    +package org.example.server.manager;
    src/main/java/org/example/server/manager/DataBaseManager.java:2: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:3: trailing whitespace.
    +import java.sql.*;
    src/main/java/org/example/server/manager/DataBaseManager.java:4: trailing whitespace.
    +import java.time.ZoneId;
    src/main/java/org/example/server/manager/DataBaseManager.java:5: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:6: trailing whitespace.
    +import org.example.common.model.Coordinates;
    src/main/java/org/example/server/manager/DataBaseManager.java:7: trailing whitespace.
    +import org.example.common.model.Ticket;
    src/main/java/org/example/server/manager/DataBaseManager.java:8: trailing whitespace.
    +import org.example.common.model.Venue;
    src/main/java/org/example/server/manager/DataBaseManager.java:9: trailing whitespace.
    +import org.example.common.model.enums.TicketType;
    src/main/java/org/example/server/manager/DataBaseManager.java:10: trailing whitespace.
    +import org.example.common.model.enums.VenueType;
    src/main/java/org/example/server/manager/DataBaseManager.java:11: trailing whitespace.
    +import org.example.server.handlers.HashHandler;
    src/main/java/org/example/server/manager/DataBaseManager.java:12: trailing whitespace.
    +import org.slf4j.Logger;
    src/main/java/org/example/server/manager/DataBaseManager.java:13: trailing whitespace.
    +import org.slf4j.LoggerFactory;
    src/main/java/org/example/server/manager/DataBaseManager.java:14: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:15: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:16: trailing whitespace.
    +public class DataBaseManager {
    src/main/java/org/example/server/manager/DataBaseManager.java:17: trailing whitespace.
    +    private static DataBaseManager instance;
    src/main/java/org/example/server/manager/DataBaseManager.java:18: trailing whitespace.
    +    private static final String GET_USERS = "SELECT * FROM lab7_users";
    src/main/java/org/example/server/manager/DataBaseManager.java:19: trailing whitespace.
    +    private static final Logger logger = LoggerFactory.getLogger(DataBaseManager.class);
    src/main/java/org/example/server/manager/DataBaseManager.java:20: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:21: trailing whitespace.
    +    //private static final String DB_URL = "jdbc:postgresql://pg:5432/studs";
    src/main/java/org/example/server/manager/DataBaseManager.java:22: trailing whitespace.
    +    //private static final String DB_USER = "s465729";
    src/main/java/org/example/server/manager/DataBaseManager.java:23: trailing whitespace.
    +    //private static final String DB_PASSWORD = "TMnULcCn63BZLOCt";
    src/main/java/org/example/server/manager/DataBaseManager.java:24: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:25: trailing whitespace.
    +    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    src/main/java/org/example/server/manager/DataBaseManager.java:26: trailing whitespace.
    +    private static final String DB_USER = "postgres";
    src/main/java/org/example/server/manager/DataBaseManager.java:27: trailing whitespace.
    +    private static final String DB_PASSWORD = "kdseum";
    src/main/java/org/example/server/manager/DataBaseManager.java:28: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:29: trailing whitespace.
    +    private static final String INSERT_TICKET = "INSERT INTO ticket (name, coordinates_x, coordinates_y, creation_date, price, discount, tickettype, venuename, capacity, venuetype, owner_id) " +
    src/main/java/org/example/server/manager/DataBaseManager.java:30: trailing whitespace.
    +            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
    src/main/java/org/example/server/manager/DataBaseManager.java:31: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:32: trailing whitespace.
    +    private static final String GET_OWNER_BY_KEY = "SELECT owner_id FROM ticket WHERE id = ?";
    src/main/java/org/example/server/manager/DataBaseManager.java:33: trailing whitespace.
    +    private static final String REMOVE_TICKET = "DELETE FROM ticket WHERE id = ? AND owner_id=?";
    src/main/java/org/example/server/manager/DataBaseManager.java:34: trailing whitespace.
    +    private static final String CLEAR_TICKET = "DELETE FROM ticket WHERE owner_id=?";
    src/main/java/org/example/server/manager/DataBaseManager.java:35: trailing whitespace.
    +    private static final String UPDATE_TICKET_BY_ID = "UPDATE ticket SET " +
    src/main/java/org/example/server/manager/DataBaseManager.java:36: trailing whitespace.
    +            "name = ?, coordinates_x = ?, coordinates_y = ?, creation_date = ?, price = ?," +
    src/main/java/org/example/server/manager/DataBaseManager.java:37: trailing whitespace.
    +            " discount = ?, tickettype = ?, venuename = ?, capacity = ?, venuetype = ?, owner_id = ? WHERE id = ? AND owner_id=?";
    src/main/java/org/example/server/manager/DataBaseManager.java:38: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:39: trailing whitespace.
    +    public DataBaseManager() {
    src/main/java/org/example/server/manager/DataBaseManager.java:40: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:41: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:42: trailing whitespace.
    +    public static DataBaseManager getInstance() {
    src/main/java/org/example/server/manager/DataBaseManager.java:43: trailing whitespace.
    +        if (instance == null) {
    src/main/java/org/example/server/manager/DataBaseManager.java:44: trailing whitespace.
    +            instance = new DataBaseManager();
    src/main/java/org/example/server/manager/DataBaseManager.java:45: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:46: trailing whitespace.
    +        return instance;
    src/main/java/org/example/server/manager/DataBaseManager.java:47: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:48: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:49: trailing whitespace.
    +    private static Connection createConnection() throws SQLException {
    src/main/java/org/example/server/manager/DataBaseManager.java:50: trailing whitespace.
    +        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    src/main/java/org/example/server/manager/DataBaseManager.java:51: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:52: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:53: trailing whitespace.
    +    public static void connectToDataBase() {
    src/main/java/org/example/server/manager/DataBaseManager.java:54: trailing whitespace.
    +        try (Connection connection = createConnection()) {
    src/main/java/org/example/server/manager/DataBaseManager.java:55: trailing whitespace.
    +            checkAndCreateTables(connection);
    src/main/java/org/example/server/manager/DataBaseManager.java:56: trailing whitespace.
    +            System.out.println("Database connection established. Tables checked/created.");
    src/main/java/org/example/server/manager/DataBaseManager.java:57: trailing whitespace.
    +        } catch (SQLException e) {
    src/main/java/org/example/server/manager/DataBaseManager.java:58: trailing whitespace.
    +            System.out.println("Error while connecting to database");
    src/main/java/org/example/server/manager/DataBaseManager.java:59: trailing whitespace.
    +            System.out.println(e.getMessage());
    src/main/java/org/example/server/manager/DataBaseManager.java:60: trailing whitespace.
    +            System.exit(-1);
    src/main/java/org/example/server/manager/DataBaseManager.java:61: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:62: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:63: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:64: trailing whitespace.
    +    private static void checkAndCreateTables(Connection connection) throws SQLException {
    src/main/java/org/example/server/manager/DataBaseManager.java:65: trailing whitespace.
    +        try (Statement stmt = connection.createStatement()) {
    src/main/java/org/example/server/manager/DataBaseManager.java:66: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:67: trailing whitespace.
    +            stmt.executeUpdate("""
    src/main/java/org/example/server/manager/DataBaseManager.java:68: trailing whitespace.
    +                        CREATE TABLE IF NOT EXISTS lab7_users (
    src/main/java/org/example/server/manager/DataBaseManager.java:69: trailing whitespace.
    +                            user_id SERIAL PRIMARY KEY,
    src/main/java/org/example/server/manager/DataBaseManager.java:70: trailing whitespace.
    +                            login VARCHAR(50) UNIQUE NOT NULL,
    src/main/java/org/example/server/manager/DataBaseManager.java:71: trailing whitespace.
    +                            password VARCHAR(64) NOT NULL
    src/main/java/org/example/server/manager/DataBaseManager.java:72: trailing whitespace.
    +                        );
    src/main/java/org/example/server/manager/DataBaseManager.java:73: trailing whitespace.
    +                    """);
    src/main/java/org/example/server/manager/DataBaseManager.java:74: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:75: trailing whitespace.
    +            stmt.executeUpdate("""
    src/main/java/org/example/server/manager/DataBaseManager.java:76: trailing whitespace.
    +                        CREATE TABLE IF NOT EXISTS ticket (
    src/main/java/org/example/server/manager/DataBaseManager.java:77: trailing whitespace.
    +                            id SERIAL PRIMARY KEY,
    src/main/java/org/example/server/manager/DataBaseManager.java:78: trailing whitespace.
    +                            name VARCHAR(100) NOT NULL,
    src/main/java/org/example/server/manager/DataBaseManager.java:79: trailing whitespace.
    +                            coordinates_x FLOAT NOT NULL,
    src/main/java/org/example/server/manager/DataBaseManager.java:80: trailing whitespace.
    +                            coordinates_y BIGINT NOT NULL,
    src/main/java/org/example/server/manager/DataBaseManager.java:81: trailing whitespace.
    +                            creation_date TIMESTAMP NOT NULL,
    src/main/java/org/example/server/manager/DataBaseManager.java:82: trailing whitespace.
    +                            price INTEGER NOT NULL CHECK (price > 0),
    src/main/java/org/example/server/manager/DataBaseManager.java:83: trailing whitespace.
    +                            discount DOUBLE PRECISION,
    src/main/java/org/example/server/manager/DataBaseManager.java:84: trailing whitespace.
    +                            tickettype VARCHAR(20) NOT NULL,
    src/main/java/org/example/server/manager/DataBaseManager.java:85: trailing whitespace.
    +                            venuename VARCHAR(100) NOT NULL,
    src/main/java/org/example/server/manager/DataBaseManager.java:86: trailing whitespace.
    +                            capacity INTEGER NOT NULL CHECK (capacity > 0),
    src/main/java/org/example/server/manager/DataBaseManager.java:87: trailing whitespace.
    +                            venuetype VARCHAR(20) NOT NULL,
    src/main/java/org/example/server/manager/DataBaseManager.java:88: trailing whitespace.
    +                            owner_id INTEGER NOT NULL,
    src/main/java/org/example/server/manager/DataBaseManager.java:89: trailing whitespace.
    +                            venueid SERIAL NOT NULL,
    src/main/java/org/example/server/manager/DataBaseManager.java:90: trailing whitespace.
    +                            FOREIGN KEY (owner_id) REFERENCES lab7_users(user_id) ON DELETE CASCADE
    src/main/java/org/example/server/manager/DataBaseManager.java:91: trailing whitespace.
    +                        );
    src/main/java/org/example/server/manager/DataBaseManager.java:92: trailing whitespace.
    +                    """);
    src/main/java/org/example/server/manager/DataBaseManager.java:93: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:94: trailing whitespace.
    +            System.out.println("Tables checked/created.");
    src/main/java/org/example/server/manager/DataBaseManager.java:95: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:96: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:97: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:98: trailing whitespace.
    +    public static void getUsers() {
    src/main/java/org/example/server/manager/DataBaseManager.java:99: trailing whitespace.
    +        try (Connection connection = createConnection();
    src/main/java/org/example/server/manager/DataBaseManager.java:100: trailing whitespace.
    +             PreparedStatement getStatement = connection.prepareStatement(GET_USERS)) {
    src/main/java/org/example/server/manager/DataBaseManager.java:101: trailing whitespace.
    +            ResultSet rs = getStatement.executeQuery();
    src/main/java/org/example/server/manager/DataBaseManager.java:102: trailing whitespace.
    +            while (rs.next()) {
    src/main/java/org/example/server/manager/DataBaseManager.java:103: trailing whitespace.
    +                System.out.println(rs.getInt("user_id") + " " + rs.getString("login"));
    src/main/java/org/example/server/manager/DataBaseManager.java:104: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/DataBaseManager.java:105: trailing whitespace.
    +        } catch (Exception e) {
    src/main/java/org/example/server/manager/DataBaseManager.java:106: trailing whitespace.
    +            logger.error("Error getting users: {}", e.getMessage());
    src/main/java/org/example/server/manager/DataBaseManager.java:107: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:108: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:109: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:110: trailing whitespace.
    +    private static final String GET_USER_BY_USERNAME = "SELECT * FROM lab7_users WHERE login = ?";
    src/main/java/org/example/server/manager/DataBaseManager.java:111: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:112: trailing whitespace.
    +    public static int getUserId(String login) {
    src/main/java/org/example/server/manager/DataBaseManager.java:113: trailing whitespace.
    +        try (Connection connection = createConnection();
    src/main/java/org/example/server/manager/DataBaseManager.java:114: trailing whitespace.
    +             PreparedStatement getStatement = connection.prepareStatement(GET_USER_BY_USERNAME)) {
    src/main/java/org/example/server/manager/DataBaseManager.java:115: trailing whitespace.
    +            getStatement.setString(1, login);
    src/main/java/org/example/server/manager/DataBaseManager.java:116: trailing whitespace.
    +            ResultSet rs = getStatement.executeQuery();
    src/main/java/org/example/server/manager/DataBaseManager.java:117: trailing whitespace.
    +            if (rs.next()) {
    src/main/java/org/example/server/manager/DataBaseManager.java:118: trailing whitespace.
    +                return rs.getInt("user_id");
    src/main/java/org/example/server/manager/DataBaseManager.java:119: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/DataBaseManager.java:120: trailing whitespace.
    +            return -1;
    src/main/java/org/example/server/manager/DataBaseManager.java:121: trailing whitespace.
    +        } catch (Exception e) {
    src/main/java/org/example/server/manager/DataBaseManager.java:122: trailing whitespace.
    +            logger.error("Error getting user ID for login {}: {}", login, e.getMessage());
    src/main/java/org/example/server/manager/DataBaseManager.java:123: trailing whitespace.
    +            return -1;
    src/main/java/org/example/server/manager/DataBaseManager.java:124: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:125: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:126: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:127: trailing whitespace.
    +    public static final String INSERT_USER_REQUEST = "INSERT INTO lab7_users (login, password) VALUES (?,?)";
    src/main/java/org/example/server/manager/DataBaseManager.java:128: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:129: trailing whitespace.
    +    public static boolean insertUser(String login, String password) {
    src/main/java/org/example/server/manager/DataBaseManager.java:130: trailing whitespace.
    +        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
    src/main/java/org/example/server/manager/DataBaseManager.java:131: trailing whitespace.
    +            logger.error("Invalid login or password");
    src/main/java/org/example/server/manager/DataBaseManager.java:132: trailing whitespace.
    +            return false;
    src/main/java/org/example/server/manager/DataBaseManager.java:133: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:134: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:135: trailing whitespace.
    +        String hashedPassword = HashHandler.encryptString(password);
    src/main/java/org/example/server/manager/DataBaseManager.java:136: trailing whitespace.
    +        String sql = "INSERT INTO lab7_users (login, password) VALUES (?, ?)";
    src/main/java/org/example/server/manager/DataBaseManager.java:137: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:138: trailing whitespace.
    +        try (Connection connection = createConnection();
    src/main/java/org/example/server/manager/DataBaseManager.java:139: trailing whitespace.
    +             PreparedStatement stmt = connection.prepareStatement(sql)) {
    src/main/java/org/example/server/manager/DataBaseManager.java:140: trailing whitespace.
    +            stmt.setString(1, login);
    src/main/java/org/example/server/manager/DataBaseManager.java:141: trailing whitespace.
    +            stmt.setString(2, hashedPassword);
    src/main/java/org/example/server/manager/DataBaseManager.java:142: trailing whitespace.
    +            return stmt.executeUpdate() > 0;
    src/main/java/org/example/server/manager/DataBaseManager.java:143: trailing whitespace.
    +        } catch (SQLException e) {
    src/main/java/org/example/server/manager/DataBaseManager.java:144: trailing whitespace.
    +            logger.error("Error creating user: {}", e.getMessage());
    src/main/java/org/example/server/manager/DataBaseManager.java:145: trailing whitespace.
    +            return false;
    src/main/java/org/example/server/manager/DataBaseManager.java:146: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:147: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:148: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:149: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:150: trailing whitespace.
    +    public static boolean checkUser(String login, String password) {
    src/main/java/org/example/server/manager/DataBaseManager.java:151: trailing whitespace.
    +        if (login == null || password == null) return false;
    src/main/java/org/example/server/manager/DataBaseManager.java:152: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:153: trailing whitespace.
    +        String sql = "SELECT password FROM lab7_users WHERE login = ?";
    src/main/java/org/example/server/manager/DataBaseManager.java:154: trailing whitespace.
    +        try (Connection connection = createConnection();
    src/main/java/org/example/server/manager/DataBaseManager.java:155: trailing whitespace.
    +             PreparedStatement stmt = connection.prepareStatement(sql)) {
    src/main/java/org/example/server/manager/DataBaseManager.java:156: trailing whitespace.
    +            stmt.setString(1, login);
    src/main/java/org/example/server/manager/DataBaseManager.java:157: trailing whitespace.
    +            try (ResultSet rs = stmt.executeQuery()) {
    src/main/java/org/example/server/manager/DataBaseManager.java:158: trailing whitespace.
    +                if (rs.next()) {
    src/main/java/org/example/server/manager/DataBaseManager.java:159: trailing whitespace.
    +                    String storedHash = rs.getString("password");
    src/main/java/org/example/server/manager/DataBaseManager.java:160: trailing whitespace.
    +                    String inputHash = HashHandler.encryptString(password);
    src/main/java/org/example/server/manager/DataBaseManager.java:161: trailing whitespace.
    +                    boolean result = storedHash.equals(inputHash);
    src/main/java/org/example/server/manager/DataBaseManager.java:162: trailing whitespace.
    +                    if (result) logger.info("User '{}' logged in successfully", login);
    src/main/java/org/example/server/manager/DataBaseManager.java:163: trailing whitespace.
    +                    else logger.warn("Incorrect password for user '{}'", login);
    src/main/java/org/example/server/manager/DataBaseManager.java:164: trailing whitespace.
    +                    return result;
    src/main/java/org/example/server/manager/DataBaseManager.java:165: trailing whitespace.
    +                }
    src/main/java/org/example/server/manager/DataBaseManager.java:166: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/DataBaseManager.java:167: trailing whitespace.
    +        } catch (SQLException e) {
    src/main/java/org/example/server/manager/DataBaseManager.java:168: trailing whitespace.
    +            logger.error("Error checking user: {}", e.getMessage());
    src/main/java/org/example/server/manager/DataBaseManager.java:169: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:170: trailing whitespace.
    +        return false;
    src/main/java/org/example/server/manager/DataBaseManager.java:171: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:172: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:173: trailing whitespace.
    +    public static boolean insertTicket(Ticket ticket, String login) {
    src/main/java/org/example/server/manager/DataBaseManager.java:174: trailing whitespace.
    +        if (ticket == null || login == null) {
    src/main/java/org/example/server/manager/DataBaseManager.java:175: trailing whitespace.
    +            logger.error("Ticket or login is null");
    src/main/java/org/example/server/manager/DataBaseManager.java:176: trailing whitespace.
    +            return false;
    src/main/java/org/example/server/manager/DataBaseManager.java:177: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:178: trailing whitespace.
    +        int userId = getUserId(login);
    src/main/java/org/example/server/manager/DataBaseManager.java:179: trailing whitespace.
    +        if (userId == -1) {
    src/main/java/org/example/server/manager/DataBaseManager.java:180: trailing whitespace.
    +            logger.error("User not found: {}", login);
    src/main/java/org/example/server/manager/DataBaseManager.java:181: trailing whitespace.
    +            return false;
    src/main/java/org/example/server/manager/DataBaseManager.java:182: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:183: trailing whitespace.
    +        try (Connection connection = createConnection();
    src/main/java/org/example/server/manager/DataBaseManager.java:184: trailing whitespace.
    +             PreparedStatement statement = connection.prepareStatement(INSERT_TICKET)) {
    src/main/java/org/example/server/manager/DataBaseManager.java:185: trailing whitespace.
    +            statement.setInt(11, userId);
    src/main/java/org/example/server/manager/DataBaseManager.java:186: trailing whitespace.
    +            insertTicketDataIntoStatement(ticket, statement);
    src/main/java/org/example/server/manager/DataBaseManager.java:187: trailing whitespace.
    +            try (ResultSet rs = statement.executeQuery()) {
    src/main/java/org/example/server/manager/DataBaseManager.java:188: trailing whitespace.
    +                if (rs.next()) {
    src/main/java/org/example/server/manager/DataBaseManager.java:189: trailing whitespace.
    +                    long newId = rs.getLong(1);
    src/main/java/org/example/server/manager/DataBaseManager.java:190: trailing whitespace.
    +                    ticket.setId(newId);
    src/main/java/org/example/server/manager/DataBaseManager.java:191: trailing whitespace.
    +                    ticket.setOwnerId(userId);
    src/main/java/org/example/server/manager/DataBaseManager.java:192: trailing whitespace.
    +                    logger.info("Successfully inserted ticket with ID: {}", newId);
    src/main/java/org/example/server/manager/DataBaseManager.java:193: trailing whitespace.
    +                    return true;
    src/main/java/org/example/server/manager/DataBaseManager.java:194: trailing whitespace.
    +                }
    src/main/java/org/example/server/manager/DataBaseManager.java:195: trailing whitespace.
    +                return false;
    src/main/java/org/example/server/manager/DataBaseManager.java:196: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/DataBaseManager.java:197: trailing whitespace.
    +        } catch (SQLException e) {
    src/main/java/org/example/server/manager/DataBaseManager.java:198: trailing whitespace.
    +            logger.error("Database error while inserting ticket: {}", e.getMessage());
    src/main/java/org/example/server/manager/DataBaseManager.java:199: trailing whitespace.
    +            if (e.getSQLState().equals("23503")) {
    src/main/java/org/example/server/manager/DataBaseManager.java:200: trailing whitespace.
    +                logger.error("User with ID {} does not exist or ticket data is invalid for user {}", userId, login);
    src/main/java/org/example/server/manager/DataBaseManager.java:201: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/DataBaseManager.java:202: trailing whitespace.
    +            return false;
    src/main/java/org/example/server/manager/DataBaseManager.java:203: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:204: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:205: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:206: trailing whitespace.
    +    private static void insertTicketDataIntoStatement(Ticket ticket, PreparedStatement statement) {
    src/main/java/org/example/server/manager/DataBaseManager.java:207: trailing whitespace.
    +        try {
    src/main/java/org/example/server/manager/DataBaseManager.java:208: trailing whitespace.
    +            statement.setString(1, ticket.getName());
    src/main/java/org/example/server/manager/DataBaseManager.java:209: trailing whitespace.
    +            statement.setFloat(2, ticket.getCoordinates().getX());
    src/main/java/org/example/server/manager/DataBaseManager.java:210: trailing whitespace.
    +            statement.setLong(3, ticket.getCoordinates().getY());
    src/main/java/org/example/server/manager/DataBaseManager.java:211: trailing whitespace.
    +            statement.setTimestamp(4, Timestamp.from(ticket.getCreationDate().toInstant()));
    src/main/java/org/example/server/manager/DataBaseManager.java:212: trailing whitespace.
    +            statement.setInt(5, ticket.getPrice());
    src/main/java/org/example/server/manager/DataBaseManager.java:213: trailing whitespace.
    +            statement.setDouble(6, ticket.getDiscount());
    src/main/java/org/example/server/manager/DataBaseManager.java:214: trailing whitespace.
    +            statement.setString(7, String.valueOf(ticket.getType()));
    src/main/java/org/example/server/manager/DataBaseManager.java:215: trailing whitespace.
    +            statement.setString(8, ticket.getVenue().getVenueName());
    src/main/java/org/example/server/manager/DataBaseManager.java:216: trailing whitespace.
    +            statement.setInt(9, ticket.getVenue().getCapacity());
    src/main/java/org/example/server/manager/DataBaseManager.java:217: trailing whitespace.
    +            statement.setString(10, String.valueOf(ticket.getVenue().getType()));
    src/main/java/org/example/server/manager/DataBaseManager.java:218: trailing whitespace.
    +        } catch (SQLException e) {
    src/main/java/org/example/server/manager/DataBaseManager.java:219: trailing whitespace.
    +            logger.error("Couldn't insert data into statement. Reason: {}", e.getMessage());
    src/main/java/org/example/server/manager/DataBaseManager.java:220: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:221: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:222: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:223: trailing whitespace.
    +    private static Ticket extractTicketFromEntry(ResultSet rs) throws SQLException {
    src/main/java/org/example/server/manager/DataBaseManager.java:224: trailing whitespace.
    +        Ticket ticket = new Ticket();
    src/main/java/org/example/server/manager/DataBaseManager.java:225: trailing whitespace.
    +        ticket.setId(rs.getLong("id"));
    src/main/java/org/example/server/manager/DataBaseManager.java:226: trailing whitespace.
    +        ticket.setName(rs.getString("name"));
    src/main/java/org/example/server/manager/DataBaseManager.java:227: trailing whitespace.
    +        ticket.setCoordinates(new Coordinates(rs.getFloat("coordinates_x"), rs.getLong("coordinates_y")));
    src/main/java/org/example/server/manager/DataBaseManager.java:228: trailing whitespace.
    +        ticket.setCreationDate(rs.getTimestamp("creation_date").toInstant().atZone(ZoneId.systemDefault()));
    src/main/java/org/example/server/manager/DataBaseManager.java:229: trailing whitespace.
    +        ticket.setPrice(rs.getInt("price"));
    src/main/java/org/example/server/manager/DataBaseManager.java:230: trailing whitespace.
    +        ticket.setDiscount(rs.getDouble("discount"));
    src/main/java/org/example/server/manager/DataBaseManager.java:231: trailing whitespace.
    +        ticket.setType(TicketType.valueOf(rs.getString("tickettype")));
    src/main/java/org/example/server/manager/DataBaseManager.java:232: trailing whitespace.
    +        Venue venue = new Venue();
    src/main/java/org/example/server/manager/DataBaseManager.java:233: trailing whitespace.
    +        venue.setName(rs.getString("venuename"));
    src/main/java/org/example/server/manager/DataBaseManager.java:234: trailing whitespace.
    +        venue.setCapacity(rs.getInt("capacity"));
    src/main/java/org/example/server/manager/DataBaseManager.java:235: trailing whitespace.
    +        venue.setType(VenueType.valueOf(rs.getString("venuetype")));
    src/main/java/org/example/server/manager/DataBaseManager.java:236: trailing whitespace.
    +        ticket.setVenue(venue);
    src/main/java/org/example/server/manager/DataBaseManager.java:237: trailing whitespace.
    +        ticket.setOwnerId(rs.getInt("owner_id"));
    src/main/java/org/example/server/manager/DataBaseManager.java:238: trailing whitespace.
    +        return ticket;
    src/main/java/org/example/server/manager/DataBaseManager.java:239: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:240: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:241: trailing whitespace.
    +    public static void getDataFromDatabase(CollectionManager collectionManager) {
    src/main/java/org/example/server/manager/DataBaseManager.java:242: trailing whitespace.
    +        try (Connection connection = createConnection();
    src/main/java/org/example/server/manager/DataBaseManager.java:243: trailing whitespace.
    +             Statement statement = connection.createStatement()) {
    src/main/java/org/example/server/manager/DataBaseManager.java:244: trailing whitespace.
    +            ResultSet rs = statement.executeQuery("SELECT * FROM ticket");
    src/main/java/org/example/server/manager/DataBaseManager.java:245: trailing whitespace.
    +            while (rs.next()) {
    src/main/java/org/example/server/manager/DataBaseManager.java:246: trailing whitespace.
    +                try {
    src/main/java/org/example/server/manager/DataBaseManager.java:247: trailing whitespace.
    +                    Ticket ticket = extractTicketFromEntry(rs);
    src/main/java/org/example/server/manager/DataBaseManager.java:248: trailing whitespace.
    +                    collectionManager.add(ticket);
    src/main/java/org/example/server/manager/DataBaseManager.java:249: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:250: trailing whitespace.
    +                } catch (Exception e) {
    src/main/java/org/example/server/manager/DataBaseManager.java:251: trailing whitespace.
    +                    System.out.println("Invalid entry in DB. Reason: " + e.getMessage());
    src/main/java/org/example/server/manager/DataBaseManager.java:252: trailing whitespace.
    +                }
    src/main/java/org/example/server/manager/DataBaseManager.java:253: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/DataBaseManager.java:254: trailing whitespace.
    +        } catch (SQLException e) {
    src/main/java/org/example/server/manager/DataBaseManager.java:255: trailing whitespace.
    +            System.out.println("Couldn't load data from DB. Reason: " + e.getMessage());
    src/main/java/org/example/server/manager/DataBaseManager.java:256: trailing whitespace.
    +            System.exit(-1);
    src/main/java/org/example/server/manager/DataBaseManager.java:257: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:258: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:259: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:260: trailing whitespace.
    +    public static boolean updateTicketById(int id, Ticket ticket, String login) {
    src/main/java/org/example/server/manager/DataBaseManager.java:261: trailing whitespace.
    +        int userId = getUserId(login);
    src/main/java/org/example/server/manager/DataBaseManager.java:262: trailing whitespace.
    +        int ownerId = getOwnerId(String.valueOf(id));
    src/main/java/org/example/server/manager/DataBaseManager.java:263: trailing whitespace.
    +        if (ownerId == -1) {
    src/main/java/org/example/server/manager/DataBaseManager.java:264: trailing whitespace.
    +            logger.error("Ticket {} not found", id);
    src/main/java/org/example/server/manager/DataBaseManager.java:265: trailing whitespace.
    +            return false;
    src/main/java/org/example/server/manager/DataBaseManager.java:266: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:267: trailing whitespace.
    +        if (userId != ownerId) {
    src/main/java/org/example/server/manager/DataBaseManager.java:268: trailing whitespace.
    +            logger.error("User {} is not the owner of ticket {}", login, id);
    src/main/java/org/example/server/manager/DataBaseManager.java:269: trailing whitespace.
    +            return false;
    src/main/java/org/example/server/manager/DataBaseManager.java:270: trailing whitespace.
    +        } else {
    src/main/java/org/example/server/manager/DataBaseManager.java:271: trailing whitespace.
    +            try (Connection connection = createConnection();
    src/main/java/org/example/server/manager/DataBaseManager.java:272: trailing whitespace.
    +                 PreparedStatement statement = connection.prepareStatement(UPDATE_TICKET_BY_ID)) {
    src/main/java/org/example/server/manager/DataBaseManager.java:273: trailing whitespace.
    +                insertTicketDataIntoStatement(ticket, statement);
    src/main/java/org/example/server/manager/DataBaseManager.java:274: trailing whitespace.
    +                statement.setInt(11, userId);
    src/main/java/org/example/server/manager/DataBaseManager.java:275: trailing whitespace.
    +                statement.setInt(12, id);
    src/main/java/org/example/server/manager/DataBaseManager.java:276: trailing whitespace.
    +                statement.setInt(13, userId); // This parameter was set previously, left for old code consistency
    src/main/java/org/example/server/manager/DataBaseManager.java:277: trailing whitespace.
    +                int rowsUpdated = statement.executeUpdate();
    src/main/java/org/example/server/manager/DataBaseManager.java:278: trailing whitespace.
    +                if (rowsUpdated > 0) {
    src/main/java/org/example/server/manager/DataBaseManager.java:279: trailing whitespace.
    +                    logger.info("Ticket {} updated successfully by user {}", id, login);
    src/main/java/org/example/server/manager/DataBaseManager.java:280: trailing whitespace.
    +                    return true;
    src/main/java/org/example/server/manager/DataBaseManager.java:281: trailing whitespace.
    +                } else {
    src/main/java/org/example/server/manager/DataBaseManager.java:282: trailing whitespace.
    +                    logger.error("Ticket {} not found or not updated", id);
    src/main/java/org/example/server/manager/DataBaseManager.java:283: trailing whitespace.
    +                    return false;
    src/main/java/org/example/server/manager/DataBaseManager.java:284: trailing whitespace.
    +                }
    src/main/java/org/example/server/manager/DataBaseManager.java:285: trailing whitespace.
    +            } catch (SQLException e) {
    src/main/java/org/example/server/manager/DataBaseManager.java:286: trailing whitespace.
    +                logger.error("Database error while updating ticket {}: {}", id, e.getMessage());
    src/main/java/org/example/server/manager/DataBaseManager.java:287: trailing whitespace.
    +                return false;
    src/main/java/org/example/server/manager/DataBaseManager.java:288: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/DataBaseManager.java:289: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:290: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:291: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:292: trailing whitespace.
    +    public static boolean renameTicketById(long id, String name, String login) throws SQLException {
    src/main/java/org/example/server/manager/DataBaseManager.java:293: trailing whitespace.
    +        String sql = "UPDATE ticket SET name = ? WHERE id = ? AND owner_id = "
    src/main/java/org/example/server/manager/DataBaseManager.java:294: trailing whitespace.
    +                + "(SELECT user_id FROM lab7_users WHERE login = ?)";
    src/main/java/org/example/server/manager/DataBaseManager.java:295: trailing whitespace.
    +        try (Connection connection = createConnection();
    src/main/java/org/example/server/manager/DataBaseManager.java:296: trailing whitespace.
    +             PreparedStatement statement = connection.prepareStatement(sql)) {
    src/main/java/org/example/server/manager/DataBaseManager.java:297: trailing whitespace.
    +            statement.setString(1, name);
    src/main/java/org/example/server/manager/DataBaseManager.java:298: trailing whitespace.
    +            statement.setLong(2, id);
    src/main/java/org/example/server/manager/DataBaseManager.java:299: trailing whitespace.
    +            statement.setString(3, login);
    src/main/java/org/example/server/manager/DataBaseManager.java:300: trailing whitespace.
    +            return statement.executeUpdate() == 1;
    src/main/java/org/example/server/manager/DataBaseManager.java:301: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:302: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:303: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:304: trailing whitespace.
    +    public static boolean removeTicketByName(String login, String key) {
    src/main/java/org/example/server/manager/DataBaseManager.java:305: trailing whitespace.
    +        int userId = getUserId(login);
    src/main/java/org/example/server/manager/DataBaseManager.java:306: trailing whitespace.
    +        int ownerId = getOwnerId(key);
    src/main/java/org/example/server/manager/DataBaseManager.java:307: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:308: trailing whitespace.
    +        logger.info("DEBUG: removeTicketByName called. User login: {}, Ticket ID (key): {}", login, key);
    src/main/java/org/example/server/manager/DataBaseManager.java:309: trailing whitespace.
    +        logger.info("DEBUG: Current User ID (from login '{}'): {}", login, userId);
    src/main/java/org/example/server/manager/DataBaseManager.java:310: trailing whitespace.
    +        logger.info("DEBUG: Ticket Owner ID (for ticket ID '{}'): {}", key, ownerId);
    src/main/java/org/example/server/manager/DataBaseManager.java:311: trailing whitespace.
    +        logger.info("DEBUG: Is User ID ({}) == Ticket Owner ID ({})? -> {}", userId, ownerId, (userId == ownerId));
    src/main/java/org/example/server/manager/DataBaseManager.java:312: trailing whitespace.
    +        if (userId == getOwnerId(key)) {
    src/main/java/org/example/server/manager/DataBaseManager.java:313: trailing whitespace.
    +            try (Connection connection = createConnection();
    src/main/java/org/example/server/manager/DataBaseManager.java:314: trailing whitespace.
    +                 PreparedStatement statement = connection.prepareStatement(REMOVE_TICKET)) {
    src/main/java/org/example/server/manager/DataBaseManager.java:315: trailing whitespace.
    +                statement.setInt(1, Integer.parseInt(key));
    src/main/java/org/example/server/manager/DataBaseManager.java:316: trailing whitespace.
    +                statement.setInt(2, userId);
    src/main/java/org/example/server/manager/DataBaseManager.java:317: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:318: trailing whitespace.
    +                int rowsAffected = statement.executeUpdate();
    src/main/java/org/example/server/manager/DataBaseManager.java:319: trailing whitespace.
    +                if (rowsAffected > 0) {
    src/main/java/org/example/server/manager/DataBaseManager.java:320: trailing whitespace.
    +                    return true;
    src/main/java/org/example/server/manager/DataBaseManager.java:321: trailing whitespace.
    +                } else {
    src/main/java/org/example/server/manager/DataBaseManager.java:322: trailing whitespace.
    +                    System.out.println("Not found");
    src/main/java/org/example/server/manager/DataBaseManager.java:323: trailing whitespace.
    +                    return false;
    src/main/java/org/example/server/manager/DataBaseManager.java:324: trailing whitespace.
    +                }
    src/main/java/org/example/server/manager/DataBaseManager.java:325: trailing whitespace.
    +            } catch (SQLException e) {
    src/main/java/org/example/server/manager/DataBaseManager.java:326: trailing whitespace.
    +                System.out.println("Couldn't remove ticket. Reason: " + e.getMessage());
    src/main/java/org/example/server/manager/DataBaseManager.java:327: trailing whitespace.
    +                return false;
    src/main/java/org/example/server/manager/DataBaseManager.java:328: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/DataBaseManager.java:329: trailing whitespace.
    +        } else {
    src/main/java/org/example/server/manager/DataBaseManager.java:330: trailing whitespace.
    +            return false;
    src/main/java/org/example/server/manager/DataBaseManager.java:331: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:332: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:333: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:334: trailing whitespace.
    +    public static int getOwnerId(String key) {
    src/main/java/org/example/server/manager/DataBaseManager.java:335: trailing whitespace.
    +        try (Connection connection = createConnection();
    src/main/java/org/example/server/manager/DataBaseManager.java:336: trailing whitespace.
    +             PreparedStatement getStatement = connection.prepareStatement(GET_OWNER_BY_KEY)) {
    src/main/java/org/example/server/manager/DataBaseManager.java:337: trailing whitespace.
    +            getStatement.setLong(1, Long.parseLong(key));
    src/main/java/org/example/server/manager/DataBaseManager.java:338: trailing whitespace.
    +            ResultSet rs = getStatement.executeQuery();
    src/main/java/org/example/server/manager/DataBaseManager.java:339: trailing whitespace.
    +            while (rs.next()) {
    src/main/java/org/example/server/manager/DataBaseManager.java:340: trailing whitespace.
    +                return rs.getInt("owner_id");
    src/main/java/org/example/server/manager/DataBaseManager.java:341: trailing whitespace.
    +            }
    src/main/java/org/example/server/manager/DataBaseManager.java:342: trailing whitespace.
    +            return -1;
    src/main/java/org/example/server/manager/DataBaseManager.java:343: trailing whitespace.
    +        } catch (Exception e) {
    src/main/java/org/example/server/manager/DataBaseManager.java:344: trailing whitespace.
    +            return -2;
    src/main/java/org/example/server/manager/DataBaseManager.java:345: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:346: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:347: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:348: trailing whitespace.
    +    public static boolean clear(String login) {
    src/main/java/org/example/server/manager/DataBaseManager.java:349: trailing whitespace.
    +        int userId = getUserId(login);
    src/main/java/org/example/server/manager/DataBaseManager.java:350: trailing whitespace.
    +
    src/main/java/org/example/server/manager/DataBaseManager.java:351: trailing whitespace.
    +        try (Connection connection = createConnection();
    src/main/java/org/example/server/manager/DataBaseManager.java:352: trailing whitespace.
    +             PreparedStatement statement = connection.prepareStatement(CLEAR_TICKET)) {
    src/main/java/org/example/server/manager/DataBaseManager.java:353: trailing whitespace.
    +            statement.setInt(1, userId);
    src/main/java/org/example/server/manager/DataBaseManager.java:354: trailing whitespace.
    +            int rowsDeleted = statement.executeUpdate();
    src/main/java/org/example/server/manager/DataBaseManager.java:355: trailing whitespace.
    +            logger.info("Deleted {} tickets for user {}", rowsDeleted, login);
    src/main/java/org/example/server/manager/DataBaseManager.java:356: trailing whitespace.
    +            return rowsDeleted > 0;
    src/main/java/org/example/server/manager/DataBaseManager.java:357: trailing whitespace.
    +        } catch (SQLException e) {
    src/main/java/org/example/server/manager/DataBaseManager.java:358: trailing whitespace.
    +            logger.error("Couldn't clear tickets for user {}. Reason: {}", login, e.getMessage());
    src/main/java/org/example/server/manager/DataBaseManager.java:359: trailing whitespace.
    +            return false;
    src/main/java/org/example/server/manager/DataBaseManager.java:360: trailing whitespace.
    +        }
    src/main/java/org/example/server/manager/DataBaseManager.java:361: trailing whitespace.
    +    }
    src/main/java/org/example/server/manager/DataBaseManager.java:362: trailing whitespace.
    +}
    
    
    name     : org.example.common.RequestTest
    tests    : 1
    failures : 0
    errors   : 0
    skipped  : 0
    
    name     : org.example.server.command.RenameTicketCommandTest
    tests    : 20
    failures : 0
    errors   : 0
    skipped  : 0
    
    
    
    ✓ • 425ms

## Activity

    $ $utf8 = New-Object System.Text.UTF8Encoding($false)
        $files = git diff --name-only -- '*.java'
        foreach ($file in $files) { $path = Join-Path (Get-Location) $file; $content = [IO.File]::ReadAllText($path).Replace("`r`n", "`n"); [IO.File]::WriteAllText($path, $content, $utf8) }
        git diff --stat
        git diff --check
     src/main/java/org/example/client/Client.java       | 11 +++++++
     .../java/org/example/client/ScriptExecutor.java    | 17 ++++++++++-
     src/main/java/org/example/server/Server.java       |  7 +++--
     .../org/example/server/command/HelpCommand.java    |  1 +
     .../example/server/manager/CollectionManager.java  | 34 ++++++++++++++++++++++
     .../org/example/server/manager/CommandManager.java |  1 +
     .../example/server/manager/DataBaseManager.java    | 12 ++++++++
     7 files changed, 80 insertions(+), 3 deletions(-)
    ✓ • 420ms

## Activity

    $ mvn '-Dmaven.repo.local=C:/Users/perlm/lab7/.m2-test' test -q
    ������� ������� ��������: 1251
    Enter ID of the ticket to rename: Enter new ticket name: ������ ���������
    �������� ��������� �� �������: Response{message='OK', data=null}
    Server response: OK
    Executing command from script: rename_ticket
    ������ ���������
    �������� ��������� �� �������: Response{message='OK', data=null}
    Server response: OK
    Executing command from script: rename_ticket
    ERROR: Ticket ID is requiredExecuting command from script: rename_ticket
    
    ERROR: Ticket name is required
    Executing command from script: rename_ticket
    ERROR: Ticket ID must be a positive integer within the long rangeExecuting command from script: rename_ticket
    
    ERROR: Ticket name must not be blank
    12:03:14.782 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.777253200+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=2, venueName='null', capacity=null, type=null}}
    12:03:14.818 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=13, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.780280+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=6, venueName='null', capacity=null, type=null}}
    12:03:14.824 [main] INFO org.example.server.manager.CollectionManager -- Deleted ticket: Ticket{id=12, name='�������  �������������� �������� "�����" '2026'', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.777253200+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=2, venueName='null', capacity=null, type=null}}
    12:03:14.835 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.834466900+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=8, venueName='null', capacity=null, type=null}}
    12:03:14.840 [main] ERROR org.example.server.command.RenameTicketCommand -- Rename failed for ticket 12: offline
    12:03:14.851 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.851478500+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=10, venueName='null', capacity=null, type=null}}
    12:03:14.921 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.921398200+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=12, venueName='null', capacity=null, type=null}}
    12:03:14.933 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.933399500+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=14, venueName='null', capacity=null, type=null}}
    12:03:14.947 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.947386100+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=16, venueName='null', capacity=null, type=null}}
    12:03:14.952 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.952386200+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=18, venueName='null', capacity=null, type=null}}
    12:03:14.955 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.955383300+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=20, venueName='null', capacity=null, type=null}}
    12:03:14.958 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.958896700+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=22, venueName='null', capacity=null, type=null}}
    12:03:14.962 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.962909+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=24, venueName='null', capacity=null, type=null}}
    12:03:14.969 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.969413100+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=26, venueName='null', capacity=null, type=null}}
    12:03:14.973 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.973422200+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=28, venueName='null', capacity=null, type=null}}
    12:03:14.977 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.977420100+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=30, venueName='null', capacity=null, type=null}}
    12:03:14.984 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.984178800+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=32, venueName='null', capacity=null, type=null}}
    12:03:14.989 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.989178+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=34, venueName='null', capacity=null, type=null}}
    12:03:14.999 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:14.999183300+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=36, venueName='null', capacity=null, type=null}}
    12:03:15.006 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:15.006179300+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=38, venueName='null', capacity=null, type=null}}
    12:03:15.012 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:15.012180100+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=40, venueName='null', capacity=null, type=null}}
    ✓ • 24.52s

## Activity

    $ Get-ChildItem target/surefire-reports/TEST-*.xml | ForEach-Object { [xml]$report = Get-Content $_.FullName; $report.testsuite | Select-Object name,tests,failures,errors,skipped }; git status --short
    
    
    name     : org.example.client.RenameTicketInputTest
    tests    : 3
    failures : 0
    errors   : 0
    skipped  : 0
    
    name     : org.example.common.RequestTest
    tests    : 1
    failures : 0
    errors   : 0
    skipped  : 0
    
    name     : org.example.server.command.RenameTicketCommandTest
    tests    : 20
    failures : 0
    errors   : 0
    skipped  : 0
    
    name     : org.example.server.manager.RenameTicketPersistenceTest
    tests    : 3
    failures : 0
    errors   : 0
    skipped  : 0
    
     M src/main/java/org/example/client/Client.java
     M src/main/java/org/example/client/ScriptExecutor.java
     M src/main/java/org/example/server/Server.java
     M src/main/java/org/example/server/command/HelpCommand.java
     M src/main/java/org/example/server/manager/CollectionManager.java
     M src/main/java/org/example/server/manager/CommandManager.java
     M src/main/java/org/example/server/manager/DataBaseManager.java
    ?? .m2-test/
    ?? src/main/java/org/example/common/RenameTicketArguments.java
    ?? src/main/java/org/example/server/command/RenameTicketCommand.java
    ?? src/test/java/org/example/client/
    ?? src/test/java/org/example/server/
    
    
    ✓ • 331ms

## Activity

    file changes: Completed · 1 changes
    Update { move_path: None }: C:\Users\perlm\lab7\src\test\java\org\example\server\command\RenameTicketCommandTest.java
    @@ -109,2 +109,3 @@
             manager.add(ticket);
    +        String before = ticket.toString();
             assertTrue(execute(manager, id, name, "owner").getMessage().startsWith("ERROR:"));
    @@ -112,2 +113,4 @@
             assertEquals("Old name", ticket.getName());
    +        assertEquals(before, ticket.toString());
    +        assertEquals(7, ticket.getOwnerId());
             assertEquals(1, manager.getCollection().size());
    @@ -130,2 +133,3 @@
             manager.add(ticket);
    +        String before = ticket.toString();
             assertTrue(execute(manager, "99", "New", "owner").getMessage().contains("access denied"));
    @@ -133,2 +137,4 @@
             assertEquals("Old name", ticket.getName());
    +        assertEquals(before, ticket.toString());
    +        assertEquals(7, ticket.getOwnerId());
             assertEquals(1, manager.getCollection().size());
    @@ -142,4 +148,7 @@
             manager.add(ticket);
    +        String before = ticket.toString();
             assertTrue(execute(manager, "12", "New", "owner").getMessage().startsWith("ERROR:"));
             assertEquals("Old name", ticket.getName());
    +        assertEquals(before, ticket.toString());
    +        assertEquals(7, ticket.getOwnerId());
             assertEquals(1, manager.getCollection().size());

## Activity

    $ mvn '-Dmaven.repo.local=C:/Users/perlm/lab7/.m2-test' test -q
    ������� ������� ��������: 1251
    Enter ID of the ticket to rename: Enter new ticket name: ������ ���������
    �������� ��������� �� �������: Response{message='OK', data=null}
    Server response: OK
    Executing command from script: rename_ticket
    ������ ���������
    �������� ��������� �� �������: Response{message='OK', data=null}
    Server response: OK
    Executing command from script: rename_ticket
    ERROR: Ticket ID is required
    ERROR: Ticket name is requiredExecuting command from script: rename_ticket
    
    Executing command from script: rename_ticket
    ERROR: Ticket ID must be a positive integer within the long range
    Executing command from script: rename_ticket
    ERROR: Ticket name must not be blank
    12:03:57.728 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.724706500+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=2, venueName='null', capacity=null, type=null}}
    12:03:57.766 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=13, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.726720200+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=6, venueName='null', capacity=null, type=null}}
    12:03:57.771 [main] INFO org.example.server.manager.CollectionManager -- Deleted ticket: Ticket{id=12, name='�������  �������������� �������� "�����" '2026'', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.724706500+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=2, venueName='null', capacity=null, type=null}}
    12:03:57.791 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.791831800+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=8, venueName='null', capacity=null, type=null}}
    12:03:57.795 [main] ERROR org.example.server.command.RenameTicketCommand -- Rename failed for ticket 12: offline
    12:03:57.801 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.801194500+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=10, venueName='null', capacity=null, type=null}}
    12:03:57.853 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.853474700+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=12, venueName='null', capacity=null, type=null}}
    12:03:57.865 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.865012600+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=14, venueName='null', capacity=null, type=null}}
    12:03:57.882 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.882012100+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=16, venueName='null', capacity=null, type=null}}
    12:03:57.885 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.885012300+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=18, venueName='null', capacity=null, type=null}}
    12:03:57.893 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.893252100+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=20, venueName='null', capacity=null, type=null}}
    12:03:57.898 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.898266300+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=22, venueName='null', capacity=null, type=null}}
    12:03:57.903 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.903267300+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=24, venueName='null', capacity=null, type=null}}
    12:03:57.910 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.910080600+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=26, venueName='null', capacity=null, type=null}}
    12:03:57.914 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.914097200+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=28, venueName='null', capacity=null, type=null}}
    12:03:57.919 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.919184800+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=30, venueName='null', capacity=null, type=null}}
    12:03:57.927 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.927617+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=32, venueName='null', capacity=null, type=null}}
    12:03:57.932 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.932355700+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=34, venueName='null', capacity=null, type=null}}
    12:03:57.937 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.937738200+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=36, venueName='null', capacity=null, type=null}}
    12:03:57.942 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.942739400+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=38, venueName='null', capacity=null, type=null}}
    12:03:57.947 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=12, name='Old name', coordinates=Coordinates{x=2.0, y=3}, creationDate=2026-10-02T12:03:57.946737700+03:00[Europe/Moscow], price=150, discount=10.0, type=VIP, venue=Venue{id=40, venueName='null', capacity=null, type=null}}
    ✓ • 15.60s

## Assistant

Все 27 тестов прошли, включая передачу запросов по сокету и проверку SQL-параметров. Дополнительно усилил проверки неизменности всех полей при отказе и запускаю их повторно. Работа с БД проверяется через тестовый JDBC-драйвер; перезапуск с настоящим PostgreSQL пока не проверен.

## Activity

    $ git diff --check; git diff -- src/main/java/org/example/server/Server.java src/main/java/org/example/server/manager/CollectionManager.java src/main/java/org/example/server/manager/DataBaseManager.java; Get-ChildItem target/surefire-reports/TEST-*.xml | ForEach-Object { [xml]$report = Get-Content $_.FullName; '{0}: tests={1}, failures={2}, errors={3}' -f $report.testsuite.name,$report.testsuite.tests,$report.testsuite.failures,$report.testsuite.errors }
    diff --git a/src/main/java/org/example/server/Server.java b/src/main/java/org/example/server/Server.java
    index f307e94..941a139 100644
    --- a/src/main/java/org/example/server/Server.java
    +++ b/src/main/java/org/example/server/Server.java
    @@ -198,10 +198,13 @@ public class Server {
                 return new Response("Please, log in first.", null);
             }
    
    +        if ("rename_ticket".equals(command) && !DataBaseManager.checkUser(login, password)) {
    +            return new Response("Invalid login or password.", null);
    +        }
    +
             // Выполнение команды
             try {
    -            Object data = commandManager.doCommand(request, collectionManager);
    -            return new Response(null, data);
    +            return commandManager.doCommand(request, collectionManager);
             } catch (Exception e) {
                 return new Response("Command execution error: " + e.getMessage(), null);
             }
    diff --git a/src/main/java/org/example/server/manager/CollectionManager.java b/src/main/java/org/example/server/manager/CollectionManager.java
    index fa17cd8..07b8e18 100644
    --- a/src/main/java/org/example/server/manager/CollectionManager.java
    +++ b/src/main/java/org/example/server/manager/CollectionManager.java
    @@ -13,6 +13,13 @@ import java.util.LinkedHashSet;
    
     public class CollectionManager {
    
    +    @FunctionalInterface
    +    public interface TicketNameStore {
    +        boolean rename(long id, String name, String login) throws java.sql.SQLException;
    +    }
    +
    +    private final TicketNameStore ticketNameStore;
    +
         private final LocalDateTime timeOfInitial = LocalDateTime.now();
    
         private LinkedHashSet<Ticket> collection = new LinkedHashSet<>();
    @@ -22,6 +29,11 @@ public class CollectionManager {
    
    
         public CollectionManager() {
    +        this(DataBaseManager::renameTicketById);
    +    }
    +
    +    public CollectionManager(TicketNameStore ticketNameStore) {
    +        this.ticketNameStore = java.util.Objects.requireNonNull(ticketNameStore);
         }
    
         public void add(Ticket ticket) {
    @@ -41,6 +53,28 @@ public class CollectionManager {
             }
         }
    
    +    public boolean renameTicket(long id, String name, String login) throws java.sql.SQLException {
    +        synchronized (collection) {
    +            if (!ticketNameStore.rename(id, name, login)) {
    +                return false;
    +            }
    +            // Name participates in hashCode: remove before changing it, then rebuild
    +            // the set in the same order so existing entries remain searchable.
    +            LinkedHashSet<Ticket> renamed = new LinkedHashSet<>();
    +            Iterator<Ticket> iterator = collection.iterator();
    +            while (iterator.hasNext()) {
    +                Ticket ticket = iterator.next();
    +                iterator.remove();
    +                if (ticket.getId() == id) {
    +                    ticket.setName(name);
    +                }
    +                renamed.add(ticket);
    +            }
    +            collection.addAll(renamed);
    +            return true;
    +        }
    +    }
    +
         public void delete(Ticket ticket) {
             synchronized (collection) {
                 boolean removed = collection.remove(ticket);
    diff --git a/src/main/java/org/example/server/manager/DataBaseManager.java b/src/main/java/org/example/server/manager/DataBaseManager.java
    index 49a4b95..a374159 100644
    --- a/src/main/java/org/example/server/manager/DataBaseManager.java
    +++ b/src/main/java/org/example/server/manager/DataBaseManager.java
    @@ -289,6 +289,18 @@ public class DataBaseManager {
             }
         }
    
    +    public static boolean renameTicketById(long id, String name, String login) throws SQLException {
    +        String sql = "UPDATE ticket SET name = ? WHERE id = ? AND owner_id = "
    +                + "(SELECT user_id FROM lab7_users WHERE login = ?)";
    +        try (Connection connection = createConnection();
    +             PreparedStatement statement = connection.prepareStatement(sql)) {
    +            statement.setString(1, name);
    +            statement.setLong(2, id);
    +            statement.setString(3, login);
    +            return statement.executeUpdate() == 1;
    +        }
    +    }
    +
         public static boolean removeTicketByName(String login, String key) {
             int userId = getUserId(login);
             int ownerId = getOwnerId(key);
    org.example.client.RenameTicketInputTest: tests=3, failures=0, errors=0
    org.example.common.RequestTest: tests=1, failures=0, errors=0
    org.example.server.command.RenameTicketCommandTest: tests=20, failures=0, errors=0
    org.example.server.manager.RenameTicketPersistenceTest: tests=3, failures=0, errors=0
    ✓ • 426ms

## Activity

    $ $cacheSource = [IO.Path]::GetFullPath('C:/Users/perlm/lab7/.m2-test')
        $cacheDestination = [IO.Path]::GetFullPath('C:/Users/perlm/AppData/Local/Temp/lab7-rename-ticket-maven-cache')
        if ($cacheSource -ne 'C:\Users\perlm\lab7\.m2-test' -or -not $cacheDestination.StartsWith('C:\Users\perlm\AppData\Local\Temp\')) { throw 'Unexpected cache paths' }
        if (Test-Path -LiteralPath $cacheDestination) { throw 'Cache destination already exists' }
        Move-Item -LiteralPath $cacheSource -Destination $cacheDestination
        git status --short
     M src/main/java/org/example/client/Client.java
     M src/main/java/org/example/client/ScriptExecutor.java
     M src/main/java/org/example/server/Server.java
     M src/main/java/org/example/server/command/HelpCommand.java
     M src/main/java/org/example/server/manager/CollectionManager.java
     M src/main/java/org/example/server/manager/CommandManager.java
     M src/main/java/org/example/server/manager/DataBaseManager.java
    ?? src/main/java/org/example/common/RenameTicketArguments.java
    ?? src/main/java/org/example/server/command/RenameTicketCommand.java
    ?? src/test/java/org/example/client/
    ?? src/test/java/org/example/server/
    ✓ • 521ms

## Assistant

Добавлена `rename_ticket` для интерактивного режима и UTF-8-скриптов, включая справку, проверку ID и названия, авторизации и владельца.

Изменяется только название. Коллекция обновляется после успешного сохранения в PostgreSQL, без дубликатов; при отказе данные сохраняются прежними.

Все 27 автоматических тестов прошли. Проверка БД выполнена через тестовый JDBC-драйвер; перезапуск с настоящим PostgreSQL не проверялся.
