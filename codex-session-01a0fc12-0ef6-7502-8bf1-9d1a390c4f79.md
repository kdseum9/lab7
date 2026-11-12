# Codex conversation

## User

Добавь в приложение lab7 команду `rename_ticket`, позволяющую авторизованному пользователю исправить название одного принадлежащего ему билета.

Требования:

1. В интерактивном режиме пользователь вводит `rename_ticket`, затем по запросам программы вводит ID билета и новое название. В скрипте команда, ID и название располагаются на трёх последовательных строках:
   rename_ticket
   12
   Концерт симфонического оркестра

## Assistant

Проверю устройство команд и проверку владельца билета, затем добавлю `rename_ticket` для интерактивного режима и скриптов.

## Activity

    $ pwd; rg --files -g AGENTS.md -g pom.xml -g '*.java' -g README*
    
    src\test\java\org\example\common\RequestTest.java
    pom.xml
    src\main\java\org\example\server\Server.java
    src\main\java\org\example\Main.java
    src\main\java\org\example\server\manager\VenueValidator.java
    src\main\java\org\example\server\manager\TicketValidator.java
    src\main\java\org\example\server\manager\DataBaseManager.java
    src\main\java\org\example\server\manager\CoordinatesValidator.java
    src\main\java\org\example\server\manager\CommandManager.java
    src\main\java\org\example\server\manager\CollectionManager.java
    src\main\java\org\example\server\handlers\HashHandler.java
    src\main\java\org\example\server\command\UpdateCommand.java
    src\main\java\org\example\client\TicketInput.java
    src\main\java\org\example\server\command\ShowCommand.java
    src\main\java\org\example\server\command\RemoveLowerCommand.java
    src\main\java\org\example\server\command\RemoveGreaterCommand.java
    src\main\java\org\example\client\ScriptExecutor.java
    src\main\java\org\example\server\command\RemoveByIdCommand.java
    src\main\java\org\example\server\command\PrintFieldDescendingDiscountCommand.java
    src\main\java\org\example\client\Client.java
    src\main\java\org\example\server\command\PrintDescendingCommand.java
    src\main\java\org\example\server\command\InfoCommand.java
    src\main\java\org\example\server\command\HelpCommand.java
    src\main\java\org\example\server\command\FilterCommand.java
    src\main\java\org\example\server\command\ExitCommand.java
    src\main\java\org\example\server\command\ClearCommand.java
    src\main\java\org\example\server\command\AddIfMaxCommand.java
    src\main\java\org\example\server\command\AddCommand.java
    src\main\java\org\example\server\command\AbstractCommand.java
    src\main\java\org\example\common\Response.java
    src\main\java\org\example\common\Request.java
    src\main\java\org\example\common\model\Venue.java
    src\main\java\org\example\common\model\Ticket.java
    src\main\java\org\example\common\model\generator\VenueGenerator.java
    src\main\java\org\example\common\model\generator\IdGenerator.java
    src\main\java\org\example\common\model\generator\CoordinatesGenerator.java
    src\main\java\org\example\common\model\enums\VenueType.java
    src\main\java\org\example\common\model\enums\TicketType.java
    src\main\java\org\example\common\model\Coordinates.java
    src\main\java\org\example\common\model\AbstractTicket.java
    src\main\java\org\example\common\Factory\TicketFactory.java
    src\main\java\org\example\common\Factory\ScriptTicketFactory.java
    src\main\java\org\example\common\Factory\Factory.java
    src\main\java\org\example\common\exceptions\WrongArgumentException.java
    src\main\java\org\example\common\exceptions\NoElementException.java
    Path
    ----
    C:\Users\perlm\lab7
    
    
    ✓ • 334ms

## User

2. ID  положительное целое число, представимое типом `long`. Отсутствующий ID, неверный формат, выход за пределы типа, ноль и отрицательные числа должны приводить к понятной ошибке без изменения данных. Новое название не должно отсутствовать, быть пустым или состоять только из пробельных символов. Удали пробельные символы по краям названия; пробелы внутри, кириллица, кавычки и другие символы должны сохраняться. Название считывается целой строкой.
3. Менять можно только свой билет. Для отсутствующего билета, чужого билета и пользователя без авторизации операция должна завершаться отказом без изменений.
4. При успехе изменяется только название. ID, владелец, цена, координаты, дата создания, скидка, тип и место проведения сохраняются. Повторное присвоение текущего названия допустимо и не должно создавать дубликаты.
5. Результат должен сохраняться в PostgreSQL и отражаться в основной коллекции: последующий просмотр показывает новое название, а после перезапуска сервера оно остаётся сохранённым. В коллекции не должны появляться дубликаты билета.
6. При ошибке сохранения в БД название в коллекции не должно изменяться.
7. Интерактивное выполнение и выполнение через скрипт должны давать одинаковый результат. В справке должна появиться новая команда с описанием ввода.
Добавь в приложение lab7 команду `rename_ticket`, позволяющую авторизованному пользователю исправить название одного принадлежащего ему билета.

Требования:

## User

1. В интерактивном режиме пользователь вводит `rename_ticket`, затем по запросам программы вводит ID билета и новое название. В скрипте команда, ID и название располагаются на трёх последовательных строках:
   rename_ticket
   12
   Концерт симфонического оркестра

## Activity

    $ Get-Content src/main/java/org/example/client/Client.java,src/main/java/org/example/client/ScriptExecutor.java,src/main/java/org/example/common/Request.java
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
            System.out.println("РџРѕР»СѓС‡РµРЅРѕ СЃРѕРѕР±С‰РµРЅРёРµ РѕС‚ СЃРµСЂРІРµСЂР°: " + response);
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
                bigBuffer.put(byteBuffer.array());
            }
    
            System.out.println("Р”Р°РЅРЅС‹Рµ РїСЂРѕС‡РёС‚Р°РЅС‹");
    
            return bigBuffer;
        }
    }
    package org.example.client;
    
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
     * РљР»Р°СЃСЃ {@code ScriptExecutor} РѕС‚РІРµС‡Р°РµС‚ Р·Р° РІС‹РїРѕР»РЅРµРЅРёРµ РєРѕРјР°РЅРґ РёР· СЃРєСЂРёРїС‚РѕРІ (С‚РµРєСЃС‚РѕРІС‹С… С„Р°Р№Р»РѕРІ).
     * <p>РџРѕРґРґРµСЂР¶РёРІР°РµС‚ РѕР±СЂР°Р±РѕС‚РєСѓ РІР»РѕР¶РµРЅРЅС‹С… СЃРєСЂРёРїС‚РѕРІ, РїСЂРµРґРѕС‚РІСЂР°С‰Р°РµС‚ СЂРµРєСѓСЂСЃРёРІРЅС‹Рµ РІС‹Р·РѕРІС‹ РѕРґРЅРѕРіРѕ Рё С‚РѕРіРѕ Р¶Рµ СЃРєСЂРёРїС‚Р°.</p>
     */
    public class ScriptExecutor {
    
        /**
         * РњРЅРѕР¶РµСЃС‚РІРѕ РїСѓС‚РµР№ Рє СѓР¶Рµ РІС‹РїРѕР»РЅРµРЅРЅС‹Рј СЃРєСЂРёРїС‚Р°Рј, С‡С‚РѕР±С‹ РїСЂРµРґРѕС‚РІСЂР°С‚РёС‚СЊ СЂРµРєСѓСЂСЃРёСЋ.
         */
        private final Stack<String> executedScripts = new Stack<>();
    
    
        /**
         * РљРѕРЅСЃС‚СЂСѓРєС‚РѕСЂ РїРѕ СѓРјРѕР»С‡Р°РЅРёСЋ.
         */
        public ScriptExecutor() {
        }
    
        /**
         * Р’С‹РїРѕР»РЅСЏРµС‚ РєРѕРјР°РЅРґС‹ РёР· СѓРєР°Р·Р°РЅРЅРѕРіРѕ С„Р°Р№Р»Р°-СЃРєСЂРёРїС‚Р°.
         * <p>РџРѕРґРґРµСЂР¶РёРІР°РµС‚ РєРѕРјР°РЅРґС‹, С‚СЂРµР±СѓСЋС‰РёРµ РґРѕРїРѕР»РЅРёС‚РµР»СЊРЅРѕРіРѕ РІРІРѕРґР° (РЅР°РїСЂРёРјРµСЂ, {@code add}, {@code update}), СЃС‡РёС‚С‹РІР°СЏ РґР°РЅРЅС‹Рµ РёР· С„Р°Р№Р»Р°.</p>
         *
         * @param filePath РїСѓС‚СЊ Рє СЃРєСЂРёРїС‚Сѓ
         * @param socket   Р°РєС‚РёРІРЅС‹Р№ {@link SocketChannel} РґР»СЏ РѕС‚РїСЂР°РІРєРё Р·Р°РїСЂРѕСЃРѕРІ РЅР° СЃРµСЂРІРµСЂ
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
                            executeScript(nestedPath, socket);  // СЂРµРєСѓСЂСЃРёРІРЅС‹Р№ РІС‹Р·РѕРІ
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
     * РљР»Р°СЃСЃ {@code Request} РїСЂРµРґСЃС‚Р°РІР»СЏРµС‚ СЃРѕР±РѕР№ РѕР±СЉРµРєС‚-Р·Р°РїСЂРѕСЃ, РѕС‚РїСЂР°РІР»СЏРµРјС‹Р№ РєР»РёРµРЅС‚РѕРј СЃРµСЂРІРµСЂСѓ.
     * <p>РЎРѕРґРµСЂР¶РёС‚ РёРјСЏ РєРѕРјР°РЅРґС‹, Р°СЂРіСѓРјРµРЅС‚С‹, РѕР±СЉРµРєС‚ {@link Ticket} (РѕРїС†РёРѕРЅР°Р»СЊРЅРѕ) Рё РґР°РЅРЅС‹Рµ Р°СѓС‚РµРЅС‚РёС„РёРєР°С†РёРё.</p>
     * <p>РЇРІР»СЏРµС‚СЃСЏ СЃРµСЂРёР°Р»РёР·СѓРµРјС‹Рј, С‡С‚РѕР±С‹ РјРѕР¶РЅРѕ Р±С‹Р»Рѕ РїРµСЂРµРґР°РІР°С‚СЊ РїРѕ СЃРµС‚Рё.</p>
     */
    public class Request implements Serializable {
        private static final long serialVersionUID = 5760575944040770153L;
    
        /**
         * РРјСЏ РєРѕРјР°РЅРґС‹, РєРѕС‚РѕСЂСѓСЋ РЅРµРѕР±С…РѕРґРёРјРѕ РІС‹РїРѕР»РЅРёС‚СЊ РЅР° СЃРµСЂРІРµСЂРµ.
         */
        private String commandName;
    
        /**
         * РђСЂРіСѓРјРµРЅС‚С‹ РєРѕРјР°РЅРґС‹, РµСЃР»Рё РѕРЅРё С‚СЂРµР±СѓСЋС‚СЃСЏ.
         */
        private String[] args;
    
        /**
         * РћР±СЉРµРєС‚ {@link Ticket}, РµСЃР»Рё РєРѕРјР°РЅРґР° РїСЂРµРґРїРѕР»Р°РіР°РµС‚ РїРµСЂРµРґР°С‡Сѓ СЌР»РµРјРµРЅС‚Р° РєРѕР»Р»РµРєС†РёРё.
         */
        private Ticket ticket;
    
        /**
         * Р›РѕРіРёРЅ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ РґР»СЏ Р°СѓС‚РµРЅС‚РёС„РёРєР°С†РёРё (РјРѕР¶РµС‚ Р±С‹С‚СЊ null).
         */
        private String login;
    
        /**
         * РџР°СЂРѕР»СЊ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ РґР»СЏ Р°СѓС‚РµРЅС‚РёС„РёРєР°С†РёРё (РјРѕР¶РµС‚ Р±С‹С‚СЊ null).
         */
        private String password;
    
        /**
         * РЎРѕР·РґР°С‘С‚ РЅРѕРІС‹Р№ РѕР±СЉРµРєС‚ Р·Р°РїСЂРѕСЃР° Р±РµР· РґР°РЅРЅС‹С… Р°СѓС‚РµРЅС‚РёС„РёРєР°С†РёРё.
         *
         * @param commandName РёРјСЏ РєРѕРјР°РЅРґС‹
         * @param args РјР°СЃСЃРёРІ Р°СЂРіСѓРјРµРЅС‚РѕРІ
         * @param ticket РѕР±СЉРµРєС‚ {@link Ticket}, СЃРІСЏР·Р°РЅРЅС‹Р№ СЃ РєРѕРјР°РЅРґРѕР№ (РјРѕР¶РµС‚ Р±С‹С‚СЊ {@code null})
         */
        public Request(String commandName, String[] args, AbstractTicket ticket) {
            this.commandName = commandName;
            this.args = args;
            this.ticket = (Ticket) ticket;
        }
    
        /**
         * РЎРѕР·РґР°С‘С‚ РЅРѕРІС‹Р№ РѕР±СЉРµРєС‚ Р·Р°РїСЂРѕСЃР° СЃ РґР°РЅРЅС‹РјРё Р°СѓС‚РµРЅС‚РёС„РёРєР°С†РёРё.
         *
         * @param commandName РёРјСЏ РєРѕРјР°РЅРґС‹
         * @param args РјР°СЃСЃРёРІ Р°СЂРіСѓРјРµРЅС‚РѕРІ
         * @param ticket РѕР±СЉРµРєС‚ {@link Ticket}, СЃРІСЏР·Р°РЅРЅС‹Р№ СЃ РєРѕРјР°РЅРґРѕР№ (РјРѕР¶РµС‚ Р±С‹С‚СЊ {@code null})
         * @param login Р»РѕРіРёРЅ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ
         * @param password РїР°СЂРѕР»СЊ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ
         */
        public Request(String commandName, String[] args, Ticket ticket, String login, String password) {
            this.commandName = commandName;
            this.args = args;
            this.ticket = (Ticket) ticket;
            this.login = login;
            this.password = password;
        }
    
        /**
         * Р’РѕР·РІСЂР°С‰Р°РµС‚ РёРјСЏ РєРѕРјР°РЅРґС‹.
         *
         * @return РёРјСЏ РєРѕРјР°РЅРґС‹
         */
        public String getCommandName() {
            return commandName;
        }
    
        /**
         * Р’РѕР·РІСЂР°С‰Р°РµС‚ РјР°СЃСЃРёРІ Р°СЂРіСѓРјРµРЅС‚РѕРІ РєРѕРјР°РЅРґС‹.
         *
         * @return Р°СЂРіСѓРјРµРЅС‚С‹ РєРѕРјР°РЅРґС‹
         */
        public String[] getArgs() {
            return args;
        }
    
        /**
         * Р’РѕР·РІСЂР°С‰Р°РµС‚ РѕР±СЉРµРєС‚ {@link Ticket}, РµСЃР»Рё РѕРЅ РїСЂРёСЃСѓС‚СЃС‚РІСѓРµС‚.
         *
         * @return РѕР±СЉРµРєС‚ {@link Ticket} РёР»Рё {@code null}
         */
        public Ticket getTicket() {
            return ticket;
        }
    
        /**
         * РЈСЃС‚Р°РЅР°РІР»РёРІР°РµС‚ РѕР±СЉРµРєС‚ {@link Ticket}.
         *
         * @param ticket РЅРѕРІС‹Р№ РѕР±СЉРµРєС‚ {@link Ticket}
         */
        public void setTicket(Ticket ticket) {
            this.ticket = (Ticket) ticket;
        }
    
        /**
         * Р’РѕР·РІСЂР°С‰Р°РµС‚ Р»РѕРіРёРЅ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ.
         *
         * @return Р»РѕРіРёРЅ РёР»Рё {@code null}, РµСЃР»Рё РЅРµ СѓСЃС‚Р°РЅРѕРІР»РµРЅ
         */
        public String getLogin() {
            return login;
        }
    
        /**
         * РЈСЃС‚Р°РЅР°РІР»РёРІР°РµС‚ Р»РѕРіРёРЅ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ.
         *
         * @param login РЅРѕРІС‹Р№ Р»РѕРіРёРЅ
         */
        public void setLogin(String login) {
            this.login = login;
        }
    
        /**
         * Р’РѕР·РІСЂР°С‰Р°РµС‚ РїР°СЂРѕР»СЊ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ.
         *
         * @return РїР°СЂРѕР»СЊ РёР»Рё {@code null}, РµСЃР»Рё РЅРµ СѓСЃС‚Р°РЅРѕРІР»РµРЅ
         */
        public String getPassword() {
            return password;
        }
    
        /**
         * РЈСЃС‚Р°РЅР°РІР»РёРІР°РµС‚ РїР°СЂРѕР»СЊ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ.
         *
         * @param password РЅРѕРІС‹Р№ РїР°СЂРѕР»СЊ
         */
        public void setPassword(String password) {
            this.password = password;
        }
    
        /**
         * Р’РѕР·РІСЂР°С‰Р°РµС‚ СЃС‚СЂРѕРєРѕРІРѕРµ РїСЂРµРґСЃС‚Р°РІР»РµРЅРёРµ РѕР±СЉРµРєС‚Р° Р·Р°РїСЂРѕСЃР° (Р±РµР· РїР°СЂРѕР»СЏ РІ С†РµР»СЏС… Р±РµР·РѕРїР°СЃРЅРѕСЃС‚Рё).
         *
         * @return СЃС‚СЂРѕРєРѕРІРѕРµ РїСЂРµРґСЃС‚Р°РІР»РµРЅРёРµ Р·Р°РїСЂРѕСЃР°
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
    ✓ • 319ms

## Activity

    $ Get-Content src/main/java/org/example/server/manager/CommandManager.java,src/main/java/org/example/server/manager/CollectionManager.java,src/main/java/org/example/server/manager/DataBaseManager.java
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
                        iterator.remove(); // РСЃРїРѕР»СЊР·СѓРµРј iterator.remove() РґР»СЏ Р±РµР·РѕРїР°СЃРЅРѕРіРѕ СѓРґР°Р»РµРЅРёСЏ РІРѕ РІСЂРµРјСЏ РёС‚РµСЂР°С†РёРё
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
                return new LinkedHashSet<>(collection); // Р’РѕР·РІСЂР°С‰Р°РµРј РєРѕРїРёСЋ РґР»СЏ Р±РµР·РѕРїР°СЃРЅРѕСЃС‚Рё
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
            synchronized (collection) { // РЎРёРЅС…СЂРѕРЅРёР·Р°С†РёСЏ РєРѕР»Р»РµРєС†РёРё
                int initialSize = collection.size();
                int userId = DataBaseManager.getUserId(userLogin); // РџРѕР»СѓС‡Р°РµРј ID РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ РїРѕ РµРіРѕ Р»РѕРіРёРЅСѓ
    
                if (userId == -1) {
                    logger.warn("Attempted to remove tickets for non-existent user login: {}", userLogin);
                    return 0; // РџРѕР»СЊР·РѕРІР°С‚РµР»СЊ РЅРµ РЅР°Р№РґРµРЅ, СѓРґР°Р»СЏС‚СЊ РЅРµС‡РµРіРѕ
                }
    
                // РСЃРїРѕР»СЊР·СѓРµРј Iterator РґР»СЏ Р±РµР·РѕРїР°СЃРЅРѕРіРѕ СѓРґР°Р»РµРЅРёСЏ СЌР»РµРјРµРЅС‚РѕРІ РІРѕ РІСЂРµРјСЏ РёС‚РµСЂР°С†РёРё
                Iterator<Ticket> iterator = collection.iterator();
                int removedCount = 0;
                while (iterator.hasNext()) {
                    Ticket ticket = iterator.next();
                    // РџСЂРѕРІРµСЂСЏРµРј, СЃРѕРІРїР°РґР°РµС‚ Р»Рё ownerId Р±РёР»РµС‚Р° СЃ ID С‚РµРєСѓС‰РµРіРѕ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ
                    if (ticket.getOwnerId() == userId) {
                        iterator.remove(); // Р‘РµР·РѕРїР°СЃРЅРѕРµ СѓРґР°Р»РµРЅРёРµ РёР· LinkedHashSet
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
    ✓ • 288ms

## Activity

    $ Get-Content -Encoding UTF8 src/main/java/org/example/server/manager/CommandManager.java,src/main/java/org/example/server/command/UpdateCommand.java,src/main/java/org/example/server/command/AbstractCommand.java,src/main/java/org/example/server/command/HelpCommand.java,pom.xml; Get-Content -Encoding UTF8 src/main/java/org/example/server/manager/CollectionManager.java | Select-Object -First 125; Get-Content -Encoding UTF8 src/main/java/org/example/common/model/Ticket.java | Select-Object -Last 100; Get-Content -Encoding UTF8 src/test/java/org/example/common/RequestTest.java | Select-Object -First 65; git status --short
    package org.example.server.manager;
    
    import org.example.common.Request;
    import org.example.common.Response;
    import org.example.server.command.*;
    
    import java.util.HashMap;
    
    /**
     * Менеджер команд, осуществляющий регистрацию и выполнение доступных команд.
     * <p>Хранит отображение названий команд на соответствующие классы и передаёт управление при выполнении.</p>
     */
    public class CommandManager {
    
        private final HashMap<String, AbstractCommand> commands = new HashMap<>();
    
    
        public CommandManager(CollectionManager collectionManager) {
    
            // Регистрация всех доступных команд
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
         * Выполняет команду, соответствующую переданному вводу.
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
     * <p>Команда для обновления элемента коллекции по заданному ID.</p>
     *
     * <p>Если элемент с указанным ID не найден, возвращает соответствующий объект {@link Response} с сообщением об ошибке.</p>
     *
     * <p><strong>Пример использования:</strong> <code>update 5 {element_data}</code></p>
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
    package org.example.server.command;
    
    import org.example.common.Request;
    import org.example.common.Response;
    import org.example.common.exceptions.NoElementException;
    import org.example.server.manager.CollectionManager;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    
    /**
     * Абстрактный класс для всех команд, реализующих определённое поведение в приложении.
     * Все команды должны реализовывать метод {@code execute}, принимающий аргументы
     * и объект менеджера коллекции, и возвращать результат выполнения команды.
     *
     * @author kdseum9
     * @version 1.0
     */
    
    public abstract class AbstractCommand {
    
        protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
        // Новый метод execute, который работает с Request
        public abstract Response execute(Request request, CollectionManager collectionManager) throws NoElementException;
    
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
    ✓ • 432ms

## Assistant

У билета название входит в хеш-код, поэтому простое изменение поля может нарушить работу `LinkedHashSet`. Добавлю отдельное обновление только названия в БД и после успешного сохранения перестрою набор, сохранив билеты и их порядок. Проверку ID и названия выполню на сервере для обоих способов ввода.

## Activity

    file changes: Completed · 6 changes
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\client\Client.java
    @@ -94,2 +94,10 @@
     
    +                case "rename_ticket":
    +                    System.out.print("Enter ID of the ticket to rename: ");
    +                    String renameId = scanner.hasNextLine() ? scanner.nextLine() : null;
    +                    System.out.print("Enter new ticket name: ");
    +                    String renameName = scanner.hasNextLine() ? scanner.nextLine() : null;
    +                    request = new Request("rename_ticket", new String[]{renameId, renameName}, null);
    +                    break;
    +
                     case "remove_by_id":
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\client\ScriptExecutor.java
    @@ -75,2 +75,7 @@
     
    +                    case "rename_ticket":
    +                        request = new Request(command,
    +                                new String[]{reader.readLine(), reader.readLine()}, null);
    +                        break;
    +
                         case "remove_by_id":
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\server\command\HelpCommand.java
    @@ -28,2 +28,3 @@
                             "update id {element} : обновить элемент по id\n" +
    +                        "rename_ticket : переименовать свой билет; затем ввести положительный ID и новое название целой строкой (в скрипте — три последовательные строки)\n" +
                             "remove_by_id id : удалить элемент по id\n" +
    Add: C:\Users\perlm\lab7\src\main\java\org\example\server\command\RenameTicketCommand.java
    package org.example.server.command;
    
    import org.example.common.Request;
    import org.example.common.Response;
    import org.example.common.model.Ticket;
    import org.example.server.manager.CollectionManager;
    import org.example.server.manager.DataBaseManager;
    
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.LinkedHashSet;
    
    /** Renames an owned ticket only after its new name has been saved. */
    public class RenameTicketCommand extends AbstractCommand {
        @Override
        public Response execute(Request request, CollectionManager collectionManager) {
            String[] args = request.getArgs();
            if (args == null || args.length == 0 || args[0] == null || args[0].isBlank()) {
                return new Response("ERROR: ID билета отсутствует", null);
            }
            long id;
            try {
                id = Long.parseLong(args[0].strip());
                if (id <= 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                return new Response("ERROR: ID должен быть положительным целым числом в пределах long", null);
            }
            if (args.length < 2 || args[1] == null) {
                return new Response("ERROR: Новое название отсутствует", null);
            }
            String name = stripWhitespace(args[1]);
            if (name.isEmpty()) {
                return new Response("ERROR: Название не должно быть пустым или состоять из пробельных символов", null);
            }
            if (!authenticate(request)) {
                return new Response("ERROR: Требуется авторизация", null);
            }
            int ownerId = userId(request.getLogin());
            if (ownerId <= 0) {
                return new Response("ERROR: Не удалось определить пользователя", null);
            }
            LinkedHashSet<Ticket> collection = collectionManager.getCollection();
            synchronized (collection) {
                Ticket ticket = collection.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
                if (ticket == null) return new Response("ERROR: Билет не найден", null);
                if (ticket.getOwnerId() != ownerId) {
                    return new Response("ERROR: Можно переименовать только свой билет", null);
                }
                try {
                    if (!persist(id, ownerId, name)) {
                        return new Response("ERROR: Билет не найден или доступ запрещён", null);
                    }
                } catch (SQLException e) {
                    logger.error("Failed to rename ticket {}", id, e);
                    return new Response("ERROR: Не удалось сохранить название в БД", null);
                }
                // Name participates in hashCode: rebuild the set without changing its order.
                ArrayList<Ticket> tickets = new ArrayList<>(collection);
                collection.clear();
                ticket.setName(name);
                collection.addAll(tickets);
                return new Response("Название билета успешно изменено", ticket);
            }
        }
    
        private static String stripWhitespace(String value) {
            int start = 0;
            int end = value.length();
            while (start < end && isWhitespace(value.codePointAt(start))) {
                start += Character.charCount(value.codePointAt(start));
            }
            while (start < end && isWhitespace(value.codePointBefore(end))) {
                end -= Character.charCount(value.codePointBefore(end));
            }
            return value.substring(start, end);
        }
    
        private static boolean isWhitespace(int codePoint) {
            return Character.isWhitespace(codePoint) || Character.isSpaceChar(codePoint);
        }
    
        protected boolean authenticate(Request request) {
            return DataBaseManager.checkUser(request.getLogin(), request.getPassword());
        }
    
        protected int userId(String login) {
            return DataBaseManager.getUserId(login);
        }
    
        protected boolean persist(long id, int ownerId, String name) throws SQLException {
            return DataBaseManager.renameTicket(id, ownerId, name);
        }
    }
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\server\manager\CommandManager.java
    @@ -27,2 +27,3 @@
             commands.put("update", new UpdateCommand());
    +        commands.put("rename_ticket", new RenameTicketCommand());
             commands.put("remove_by_id", new RemoveByIdCommand());
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\server\manager\DataBaseManager.java
    @@ -259,2 +259,13 @@
     
    +    public static boolean renameTicket(long id, int ownerId, String name) throws SQLException {
    +        try (Connection connection = createConnection();
    +             PreparedStatement statement = connection.prepareStatement(
    +                     "UPDATE ticket SET name = ? WHERE id = ? AND owner_id = ?")) {
    +            statement.setString(1, name);
    +            statement.setLong(2, id);
    +            statement.setInt(3, ownerId);
    +            return statement.executeUpdate() == 1;
    +        }
    +    }
    +
         public static boolean updateTicketById(int id, Ticket ticket, String login) {

## Activity

    $ rg -n 'getCollection|checkUser' src/main/java/org/example/server; mvn test
    src/main/java/org/example/server\command\PrintFieldDescendingDiscountCommand.java:20:        LinkedHashSet<Ticket> collection = collectionManager.getCollection();
    src/main/java/org/example/server\Server.java:179:            if (DataBaseManager.checkUser(login, password)) {
    src/main/java/org/example/server\command\PrintDescendingCommand.java:20:        LinkedHashSet<Ticket> tickets = collectionManager.getCollection();
    src/main/java/org/example/server\command\RemoveLowerCommand.java:22:        Iterator<Ticket> iterator = collectionManager.getCollection().iterator();
    src/main/java/org/example/server\command\FilterCommand.java:27:        List<Ticket> filtered = collectionManager.getCollection().stream()
    src/main/java/org/example/server\command\RenameTicketCommand.java:42:        LinkedHashSet<Ticket> collection = collectionManager.getCollection();
    src/main/java/org/example/server\command\RenameTicketCommand.java:83:        return DataBaseManager.checkUser(request.getLogin(), request.getPassword());
    src/main/java/org/example/server\manager\DataBaseManager.java:150:    public static boolean checkUser(String login, String password) {
    src/main/java/org/example/server\command\ShowCommand.java:15:        LinkedHashSet<Ticket> collection = collectionManager.getCollection();
    src/main/java/org/example/server\command\RemoveByIdCommand.java:25:        synchronized (collectionManager.getCollection()) {
    src/main/java/org/example/server\command\RemoveGreaterCommand.java:22:        Iterator<Ticket> iterator = collectionManager.getCollection().iterator();
    src/main/java/org/example/server\command\InfoCommand.java:25:        sb.append("- Collection type: ").append(manager.getCollection().getClass().getSimpleName()).append("\n");
    src/main/java/org/example/server\command\InfoCommand.java:26:        sb.append("- Collection size: ").append(manager.getCollection().size()).append("\n");
    src/main/java/org/example/server\manager\CollectionManager.java:129:    public LinkedHashSet<Ticket> getCollection() {
    src/main/java/org/example/server\manager\CollectionManager.java:170:        if (getCollection().isEmpty()) {
    src/main/java/org/example/server\manager\CollectionManager.java:177:        int maxPrice = getCollection().stream()
    ������� ������� ��������: 1251
    �������� � �������.
    [ERROR] Could not create local repository at C:\.m2\repository -> [Help 1]
    [ERROR]
    [ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
    [ERROR] Re-run Maven using the -X switch to enable full debug logging.
    [ERROR]
    [ERROR] For more information about the errors and possible solutions, please read the following articles:
    [ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/LocalRepositoryNotAccessibleException
    ✗ (1) • 2.71s

## Activity

    $ Get-Content -Encoding UTF8 src/main/java/org/example/server/Server.java | Select-Object -Skip 140 -First 90; Get-Content -Encoding UTF8 src/main/java/org/example/server/manager/CollectionManager.java | Select-Object -Skip 125 -First 12; Get-ChildItem C:/Users/perlm/.m2 -ErrorAction SilentlyContinue
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
    
        public LinkedHashSet<Ticket> getCollection() {
            synchronized (collection) {
                return new LinkedHashSet<>(collection); // Возвращаем копию для безопасности
            }
        }
    
        public Ticket getById(long id) {
    
            Ticket found = collection.stream()
    
    
        Каталог: C:\Users\perlm\.m2
    
    
    Mode                 LastWriteTime         Length Name
    ----                 -------------         ------ ----
    d-----        01.10.2026     11:02                repository
    
    
    ✓ • 384ms

## Activity

    file changes: Completed · 3 changes
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\server\Server.java
    @@ -202,4 +202,3 @@
             try {
    -            Object data = commandManager.doCommand(request, collectionManager);
    -            return new Response(null, data);
    +            return commandManager.doCommand(request, collectionManager);
             } catch (Exception e) {
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\server\command\RenameTicketCommand.java
    @@ -41,4 +41,3 @@
             }
    -        LinkedHashSet<Ticket> collection = collectionManager.getCollection();
    -        synchronized (collection) {
    +        return collectionManager.withLockedCollection(collection -> {
                 Ticket ticket = collection.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    @@ -62,3 +61,3 @@
                 return new Response("Название билета успешно изменено", ticket);
    -        }
    +        });
         }
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\server\manager\CollectionManager.java
    @@ -134,2 +134,9 @@
     
    +    /** Executes a collection mutation under the same lock as add/remove. */
    +    public <T> T withLockedCollection(java.util.function.Function<LinkedHashSet<Ticket>, T> action) {
    +        synchronized (collection) {
    +            return action.apply(collection);
    +        }
    +    }
    +
         public Ticket getById(long id) {

## Activity

    $ Get-Content .gitignore -ErrorAction SilentlyContinue; Get-Content -Encoding UTF8 src/main/java/org/example/common/model/Ticket.java | Select-Object -First 95
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
    ✓ • 362ms

## Activity

    $ mvn "-Dmaven.repo.local=C:/Users/perlm/lab7/.m2/repository" test
    ������� ������� ��������: 1251
    [INFO] Scanning for projects...
    [INFO]
    [INFO] --------------------------< org.example:lab7 >--------------------------
    [INFO] Building lab7 1.0-SNAPSHOT
    [INFO]   from pom.xml
    [INFO] --------------------------------[ jar ]---------------------------------
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-resources-plugin/3.4.0/maven-resources-plugin-3.4.0.pom
    Progress (1): 766 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-plugins/45/maven-plugins-45.pom
    Progress (1): 772 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/maven-parent/45/maven-parent-45.pom
    Progress (1): 708 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/apache/35/apache-35.pom
    Progress (1): 809 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/junit-bom/5.13.1/junit-bom-5.13.1.pom
    Progress (1): 908 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-resources-plugin/3.4.0/maven-resources-plugin-3.4.0.jar
    Progress (1): 0.9/31 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-compiler-plugin/3.15.0/maven-compiler-plugin-3.15.0.pom
    Progress (1): 758 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-plugins/47/maven-plugins-47.pom
    Progress (1): 771 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/maven-parent/47/maven-parent-47.pom
    Progress (1): 716 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/apache/37/apache-37.pom
    Progress (1): 809 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/junit-bom/5.14.2/junit-bom-5.14.2.pom
    Progress (1): 908 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-compiler-plugin/3.15.0/maven-compiler-plugin-3.15.0.jar
    Progress (1): 3.8/84 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-surefire-plugin/3.5.4/maven-surefire-plugin-3.5.4.pom
    Progress (1): 827 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/surefire/3.5.4/surefire-3.5.4.pom
    Progress (1): 791 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/maven-parent/44/maven-parent-44.pom
    Progress (1): 708 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/apache/34/apache-34.pom
    Progress (1): 737 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/junit-bom/5.12.1/junit-bom-5.12.1.pom
    Progress (1): 908 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-surefire-plugin/3.5.4/maven-surefire-plugin-3.5.4.jar
    Progress (1): 7.7/46 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/postgresql/postgresql/42.7.3/postgresql-42.7.3.pom
    Progress (1): 1.3 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/checkerframework/checker-qual/3.42.0/checker-qual-3.42.0.pom
    Progress (1): 940 B
    Downloading from central: https://repo.maven.apache.org/maven2/com/zaxxer/HikariCP/5.1.0/HikariCP-5.1.0.pom
    Progress (1): 1.1 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.pom
    Progress (1): 1.0 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/slf4j/slf4j-parent/1.7.36/slf4j-parent-1.7.36.pom
    Progress (1): 1.0 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/slf4j/slf4j-api/2.0.9/slf4j-api-2.0.9.pom
    Progress (1): 1.1 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/slf4j/slf4j-parent/2.0.9/slf4j-parent-2.0.9.pom
    Progress (1): 943 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/slf4j/slf4j-bom/2.0.9/slf4j-bom-2.0.9.pom
    Progress (1): 1.0 kB
    Downloading from central: https://repo.maven.apache.org/maven2/ch/qos/logback/logback-classic/1.5.13/logback-classic-1.5.13.pom
    Progress (1): 1.7 kB
    Downloading from central: https://repo.maven.apache.org/maven2/ch/qos/logback/logback-parent/1.5.13/logback-parent-1.5.13.pom
    Progress (1): 1.1 kB
    Downloading from central: https://repo.maven.apache.org/maven2/ch/qos/logback/logback-core/1.5.13/logback-core-1.5.13.pom
    Progress (1): 1.8 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/slf4j/slf4j-api/2.0.15/slf4j-api-2.0.15.pom
    Progress (1): 1.1 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/slf4j/slf4j-parent/2.0.15/slf4j-parent-2.0.15.pom
    Progress (1): 954 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/slf4j/slf4j-bom/2.0.15/slf4j-bom-2.0.15.pom
    Progress (1): 1.0 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter/5.10.2/junit-jupiter-5.10.2.pom
    Progress (1): 944 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/junit-bom/5.10.2/junit-bom-5.10.2.pom
    Progress (1): 908 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter-api/5.10.2/junit-jupiter-api-5.10.2.pom
    Progress (1): 954 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/opentest4j/opentest4j/1.3.0/opentest4j-1.3.0.pom
    Progress (1): 996 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-commons/1.10.2/junit-platform-commons-1.10.2.pom
    Progress (1): 966 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apiguardian/apiguardian-api/1.1.2/apiguardian-api-1.1.2.pom
    Progress (1): 1.0 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter-params/5.10.2/junit-jupiter-params-5.10.2.pom
    Progress (1): 964 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter-engine/5.10.2/junit-jupiter-engine-5.10.2.pom
    Progress (1): 963 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-engine/1.10.2/junit-platform-engine-1.10.2.pom
    Progress (1): 957 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/postgresql/postgresql/42.7.3/postgresql-42.7.3.jar
    Progress (1): 0/1.1 MB
    Downloading from central: https://repo.maven.apache.org/maven2/org/checkerframework/checker-qual/3.42.0/checker-qual-3.42.0.jar
    Downloading from central: https://repo.maven.apache.org/maven2/com/zaxxer/HikariCP/5.1.0/HikariCP-5.1.0.jar
    Downloading from central: https://repo.maven.apache.org/maven2/org/slf4j/slf4j-api/2.0.9/slf4j-api-2.0.9.jar
    Downloading from central: https://repo.maven.apache.org/maven2/ch/qos/logback/logback-classic/1.5.13/logback-classic-1.5.13.jar
    Downloading from central: https://repo.maven.apache.org/maven2/ch/qos/logback/logback-core/1.5.13/logback-core-1.5.13.jar
    Progress (1): 7.7/162 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter/5.10.2/junit-jupiter-5.10.2.jar
    Progress (1): 6.4 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter-api/5.10.2/junit-jupiter-api-5.10.2.jar
    Progress (1): 7.7/211 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/opentest4j/opentest4j/1.3.0/opentest4j-1.3.0.jar
    Progress (2): 20/304 kB | 0.9/231 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-commons/1.10.2/junit-platform-commons-1.10.2.jar
    Progress (3): 94/304 kB | 65/231 kB | 70/625 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/apiguardian/apiguardian-api/1.1.2/apiguardian-api-1.1.2.jar
    Progress (3): 304 kB | 384/625 kB | 106 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter-params/5.10.2/junit-jupiter-params-5.10.2.jar
    Progress (2): 304 kB | 401/625 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter-engine/5.10.2/junit-jupiter-engine-5.10.2.jar
    Progress (1): 528/625 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-engine/1.10.2/junit-platform-engine-1.10.2.jar
    Progress (1): 6.8 kB
    Progress (1): 77/586 kB
    Progress (3): 405/586 kB | 205 kB | 245 kB
    Progress (2): 421/586 kB | 245 kB
    Progress (1): 536/586 kB
    [INFO]
    [INFO] --- resources:3.4.0:resources (default-resources) @ lab7 ---
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-filtering/3.4.0/maven-filtering-3.4.0.pom
    Progress (1): 787 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-shared-components/43/maven-shared-components-43.pom
    Progress (1): 815 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/maven-parent/43/maven-parent-43.pom
    Progress (1): 720 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/apache/33/apache-33.pom
    Progress (1): 741 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/junit-bom/5.10.3/junit-bom-5.10.3.pom
    Progress (1): 908 B
    Downloading from central: https://repo.maven.apache.org/maven2/javax/inject/javax.inject/1/javax.inject-1.pom
    Progress (1): 612 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/sonatype/plexus/plexus-build-api/0.0.7/plexus-build-api-0.0.7.pom
    Progress (1): 910 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/sonatype/spice/spice-parent/15/spice-parent-15.pom
    Progress (1): 1.3 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/sonatype/forge/forge-parent/5/forge-parent-5.pom
    Progress (1): 1.3 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-utils/4.0.2/plexus-utils-4.0.2.pom
    Progress (1): 1.0 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-xml/3.0.1/plexus-xml-3.0.1.pom
    Progress (1): 814 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus/18/plexus-18.pom
    Progress (1): 692 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-interpolation/1.27/plexus-interpolation-1.27.pom
    Progress (1): 1.3 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus/16/plexus-16.pom
    Progress (1): 692 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/junit-bom/5.10.1/junit-bom-5.10.1.pom
    Progress (1): 908 B
    Downloading from central: https://repo.maven.apache.org/maven2/commons-io/commons-io/2.16.1/commons-io-2.16.1.pom
    Progress (1): 762 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/commons/commons-parent/69/commons-parent-69.pom
    Progress (1): 708 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/apache/31/apache-31.pom
    Progress (1): 739 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/commons/commons-lang3/3.16.0/commons-lang3-3.16.0.pom
    Progress (1): 744 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/commons/commons-parent/72/commons-parent-72.pom
    Progress (1): 705 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/junit-bom/5.11.0-M2/junit-bom-5.11.0-M2.pom
    Progress (1): 908 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/commons/commons-lang3/3.20.0/commons-lang3-3.20.0.pom
    Progress (1): 740 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/commons/commons-parent/92/commons-parent-92.pom
    Progress (1): 699 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/junit-bom/5.13.4/junit-bom-5.13.4.pom
    Progress (1): 908 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-filtering/3.4.0/maven-filtering-3.4.0.jar
    Progress (1): 7.7/56 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar
    Downloading from central: https://repo.maven.apache.org/maven2/org/sonatype/plexus/plexus-build-api/0.0.7/plexus-build-api-0.0.7.jar
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-utils/4.0.2/plexus-utils-4.0.2.jar
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-xml/3.0.1/plexus-xml-3.0.1.jar
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-interpolation/1.27/plexus-interpolation-1.27.jar
    Progress (1): 3.2/8.5 kB
    Downloading from central: https://repo.maven.apache.org/maven2/commons-io/commons-io/2.16.1/commons-io-2.16.1.jar
    Progress (1): 7.7/94 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/commons/commons-lang3/3.20.0/commons-lang3-3.20.0.jar
    Progress (2): 86 kB | 61/193 kB
    Downloading from central: https://repo.maven.apache.org/maven2/javax/inject/javax.inject/1/javax.inject-1.jar
    Progress (3): 193 kB | 48/509 kB | 16/41 kB
    Progress (2): 278/509 kB | 41 kB
    Progress (2): 360/509 kB | 16/714 kB
    Progress (2): 509 kB | 397/714 kB
    Progress (1): 557/714 kB
    [INFO] Copying 1 resource from src\main\resources to target\classes
    [INFO]
    [INFO] --- compiler:3.15.0:compile (default-compile) @ lab7 ---
    Downloading from central: https://repo.maven.apache.org/maven2/org/ow2/asm/asm/9.9.1/asm-9.9.1.pom
    Progress (1): 1.3 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/ow2/ow2/1.5.1/ow2-1.5.1.pom
    Progress (1): 709 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-shared-utils/3.4.2/maven-shared-utils-3.4.2.pom
    Progress (1): 793 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-shared-components/39/maven-shared-components-39.pom
    Progress (1): 812 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/maven-parent/39/maven-parent-39.pom
    Progress (1): 724 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/apache/29/apache-29.pom
    Progress (1): 741 B
    Downloading from central: https://repo.maven.apache.org/maven2/commons-io/commons-io/2.11.0/commons-io-2.11.0.pom
    Progress (1): 764 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/commons/commons-parent/52/commons-parent-52.pom
    Progress (1): 703 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/apache/23/apache-23.pom
    Progress (1): 810 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/junit-bom/5.7.2/junit-bom-5.7.2.pom
    Progress (1): 912 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-shared-incremental/1.1/maven-shared-incremental-1.1.pom
    Progress (1): 806 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-shared-components/19/maven-shared-components-19.pom
    Progress (1): 773 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/maven-parent/23/maven-parent-23.pom
    Progress (1): 726 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/apache/13/apache-13.pom
    Progress (1): 749 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-java/1.5.2/plexus-java-1.5.2.pom
    Progress (1): 1.9 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-languages/1.5.2/plexus-languages-1.5.2.pom
    Progress (1): 1.4 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus/24/plexus-24.pom
    Progress (1): 689 B
    Downloading from central: https://repo.maven.apache.org/maven2/com/thoughtworks/qdox/qdox/2.2.0/qdox-2.2.0.pom
    Progress (1): 872 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/sonatype/oss/oss-parent/9/oss-parent-9.pom
    Progress (1): 838 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-compiler-api/2.16.2/plexus-compiler-api-2.16.2.pom
    Progress (1): 1.4 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-compiler/2.16.2/plexus-compiler-2.16.2.pom
    Progress (1): 1.2 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus/25/plexus-25.pom
    Progress (1): 690 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/junit-bom/5.14.1/junit-bom-5.14.1.pom
    Progress (1): 908 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-compiler-manager/2.16.2/plexus-compiler-manager-2.16.2.pom
    Progress (1): 1.3 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-compiler-javac/2.16.2/plexus-compiler-javac-2.16.2.pom
    Progress (1): 1.3 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-compilers/2.16.2/plexus-compilers-2.16.2.pom
    Progress (1): 1.6 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/ow2/asm/asm/9.9.1/asm-9.9.1.jar
    Progress (1): 3.2/126 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-shared-utils/3.4.2/maven-shared-utils-3.4.2.jar
    Downloading from central: https://repo.maven.apache.org/maven2/commons-io/commons-io/2.11.0/commons-io-2.11.0.jar
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-shared-incremental/1.1/maven-shared-incremental-1.1.jar
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-java/1.5.2/plexus-java-1.5.2.jar
    Downloading from central: https://repo.maven.apache.org/maven2/com/thoughtworks/qdox/qdox/2.2.0/qdox-2.2.0.jar
    Progress (1): 7.7/327 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-compiler-api/2.16.2/plexus-compiler-api-2.16.2.jar
    Progress (2): 262/327 kB | 163/353 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-compiler-manager/2.16.2/plexus-compiler-manager-2.16.2.jar
    Progress (3): 311/327 kB | 196/353 kB | 12/57 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-compiler-javac/2.16.2/plexus-compiler-javac-2.16.2.jar
    Downloaded from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-java/1.5.2/plexus-java-1.5.2.jar (57 kB at 312 kB/s)
    Progress (2): 311/327 kB | 7.3/29 kB
    Progress (2): 311/327 kB | 5.2 kB
    Progress (2): 311/327 kB | 3.8/30 kB
    Progress (1): 327 kB
    [INFO] Recompiling the module because of changed dependency.
    [INFO] Compiling 44 source files with javac [debug target 17] to target\classes
    [INFO]
    [INFO] --- resources:3.4.0:testResources (default-testResources) @ lab7 ---
    [INFO] skip non existing resourceDirectory C:\Users\perlm\lab7\src\test\resources
    [INFO]
    [INFO] --- compiler:3.15.0:testCompile (default-testCompile) @ lab7 ---
    [INFO] Recompiling the module because of changed dependency.
    [INFO] Compiling 1 source file with javac [debug target 17] to target\test-classes
    [INFO]
    [INFO] --- surefire:3.5.4:test (default-test) @ lab7 ---
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/surefire-api/3.5.4/surefire-api-3.5.4.pom
    Progress (1): 832 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/surefire-logger-api/3.5.4/surefire-logger-api-3.5.4.pom
    Progress (1): 826 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/surefire-shared-utils/3.5.4/surefire-shared-utils-3.5.4.pom
    Progress (1): 1.1 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/surefire-extensions-api/3.5.4/surefire-extensions-api-3.5.4.pom
    Progress (1): 833 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/maven-surefire-common/3.5.4/maven-surefire-common-3.5.4.pom
    Progress (1): 823 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/surefire-booter/3.5.4/surefire-booter-3.5.4.pom
    Progress (1): 827 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/surefire-extensions-spi/3.5.4/surefire-extensions-spi-3.5.4.pom
    Progress (1): 873 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-util/1.4.1/maven-resolver-util-1.4.1.pom
    Progress (1): 825 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver/1.4.1/maven-resolver-1.4.1.pom
    Progress (1): 774 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/maven-parent/33/maven-parent-33.pom
    Progress (1): 717 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/apache/21/apache-21.pom
    Progress (1): 753 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-api/1.4.1/maven-resolver-api-1.4.1.pom
    Progress (1): 843 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-common-artifact-filters/3.4.0/maven-common-artifact-filters-3.4.0.pom
    Progress (1): 798 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-shared-components/42/maven-shared-components-42.pom
    Progress (1): 811 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/maven-parent/42/maven-parent-42.pom
    Progress (1): 724 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/apache/32/apache-32.pom
    Progress (1): 759 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-java/1.5.0/plexus-java-1.5.0.pom
    Progress (1): 1.9 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-languages/1.5.0/plexus-languages-1.5.0.pom
    Progress (1): 1.4 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus/20/plexus-20.pom
    Progress (1): 692 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/junit-bom/5.11.4/junit-bom-5.11.4.pom
    Progress (1): 908 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/ow2/asm/asm/9.8/asm-9.8.pom
    Progress (1): 1.3 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/surefire-api/3.5.4/surefire-api-3.5.4.jar
    Progress (1): 7.7/174 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/surefire-logger-api/3.5.4/surefire-logger-api-3.5.4.jar
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/surefire-shared-utils/3.5.4/surefire-shared-utils-3.5.4.jar
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/surefire-extensions-api/3.5.4/surefire-extensions-api-3.5.4.jar
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/maven-surefire-common/3.5.4/maven-surefire-common-3.5.4.jar
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/surefire-booter/3.5.4/surefire-booter-3.5.4.jar
    Progress (1): 0/2.9 MB
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/surefire-extensions-spi/3.5.4/surefire-extensions-spi-3.5.4.jar
    Progress (2): 0.3/2.9 MB | 14 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-util/1.4.1/maven-resolver-util-1.4.1.jar
    Progress (1): 0.4/2.9 MB
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-api/1.4.1/maven-resolver-api-1.4.1.jar
    Progress (4): 1.1/2.9 MB | 290/314 kB | 16/26 kB | 44/168 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-common-artifact-filters/3.4.0/maven-common-artifact-filters-3.4.0.jar
    Progress (3): 1.2/2.9 MB | 16/26 kB | 44/168 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-java/1.5.0/plexus-java-1.5.0.jar
    Progress (3): 1.4/2.9 MB | 26 kB | 77/168 kB
    Progress (3): 1.4/2.9 MB | 77/168 kB | 52/58 kB
    Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-common-artifact-filters/3.4.0/maven-common-artifact-filters-3.4.0.jar (58 kB at 173 kB/s)
    Progress (2): 1.4/2.9 MB | 77/168 kB
    Progress (3): 2.2/2.9 MB | 168 kB | 7.7/126 kB
    Progress (2): 2.3/2.9 MB | 110/126 kB
    Progress (1): 2.5/2.9 MB
    [INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/surefire-junit-platform/3.5.4/surefire-junit-platform-3.5.4.pom
    Progress (1): 832 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/surefire-providers/3.5.4/surefire-providers-3.5.4.pom
    Progress (1): 844 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/common-java5/3.5.4/common-java5-3.5.4.pom
    Progress (1): 839 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-engine/1.12.1/junit-platform-engine-1.12.1.pom
    Progress (1): 957 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-commons/1.12.1/junit-platform-commons-1.12.1.pom
    Progress (1): 966 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-launcher/1.12.1/junit-platform-launcher-1.12.1.pom
    Progress (1): 967 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/surefire-junit-platform/3.5.4/surefire-junit-platform-3.5.4.jar
    Progress (1): 3.2/35 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/surefire/common-java5/3.5.4/common-java5-3.5.4.jar
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-launcher/1.12.1/junit-platform-launcher-1.12.1.jar
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-commons/1.12.1/junit-platform-commons-1.12.1.jar
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-engine/1.12.1/junit-platform-engine-1.12.1.jar
    Progress (1): 7.3/152 kB
    Progress (2): 152 kB | 147/208 kB
    Downloaded from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-launcher/1.12.1/junit-platform-launcher-1.12.1.jar (208 kB at 1.0 MB/s)
    Progress (1): 7.7/256 kB
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-launcher/1.10.2/junit-platform-launcher-1.10.2.pom
    Progress (1): 967 B
    Downloading from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-launcher/1.10.2/junit-platform-launcher-1.10.2.jar
    Progress (1): 7.7/184 kB
    [INFO]
    [INFO] -------------------------------------------------------
    [INFO]  T E S T S
    [INFO] -------------------------------------------------------
    [INFO] Running org.example.common.RequestTest
    [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.100 s -- in org.example.common.RequestTest
    [INFO]
    [INFO] Results:
    [INFO]
    [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
    [INFO]
    [WARNING] Corrupted channel by directly writing to native stream in forked JVM 1. See FAQ web page and the dump file C:\Users\perlm\lab7\target\surefire-reports\2026-10-02T13-07-32_353-jvmRun1.dumpstream
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time:  51.353 s
    [INFO] Finished at: 2026-10-02T13:07:37+03:00
    [INFO] ------------------------------------------------------------------------
    ✓ • 54.79s

## Activity

    file changes: Completed · 1 changes
    Add: C:\Users\perlm\lab7\src\test\java\org\example\server\command\RenameTicketCommandTest.java
    package org.example.server.command;
    
    import org.example.common.Request;
    import org.example.common.model.Ticket;
    import org.example.server.manager.CollectionManager;
    import org.junit.jupiter.api.Test;
    
    import java.sql.SQLException;
    
    import static org.junit.jupiter.api.Assertions.*;
    
    class RenameTicketCommandTest {
        static class StubCommand extends RenameTicketCommand {
            boolean authorized = true;
            boolean saved = true;
            boolean fail;
            int writes;
            long savedId;
            String savedName;
            protected boolean authenticate(Request request) { return authorized; }
            protected int userId(String login) { return 7; }
            protected boolean persist(long id, int owner, String name) throws SQLException {
                writes++;
                savedId = id;
                savedName = name;
                if (fail) throw new SQLException("test failure");
                return saved;
            }
        }
    
        private Ticket ticket(CollectionManager manager) {
            Ticket ticket = new Ticket();
            ticket.setId(Long.MAX_VALUE);
            ticket.setOwnerId(7);
            ticket.setName("Old");
            ticket.setPrice(100);
            manager.add(ticket);
            return ticket;
        }
    
        private Request request(String... args) {
            return new Request("rename_ticket", args, null, "owner", "password");
        }
    
        @Test
        void renamesOnlyNameAndKeepsSetUsableOnRepeatedRename() {
            CollectionManager manager = new CollectionManager();
            Ticket ticket = ticket(manager);
            var created = ticket.getCreationDate();
            StubCommand command = new StubCommand();
            String name = "Концерт  \"оркестра\" !";
            for (int i = 0; i < 2; i++) {
                assertSame(ticket, command.execute(request("9223372036854775807", "\u00a0\t " + name + " \u2003"), manager).getData());
                assertEquals(name, ticket.getName());
                assertEquals(1, manager.getCollection().size());
                assertEquals(Long.MAX_VALUE, command.savedId);
                assertEquals(name, command.savedName);
                assertEquals(7, ticket.getOwnerId());
                assertEquals(100, ticket.getPrice());
                assertEquals(created, ticket.getCreationDate());
            }
            manager.add(ticket);
            assertEquals(1, manager.getCollection().size());
            manager.delete(ticket);
            assertTrue(manager.getCollection().isEmpty());
        }
    
        @Test
        void invalidInputDoesNotWrite() {
            CollectionManager manager = new CollectionManager();
            Ticket ticket = ticket(manager);
            StubCommand command = new StubCommand();
            for (String id : new String[]{null, "", "abc", "1.2", "0", "-1", "9223372036854775808"}) {
                assertTrue(command.execute(request(id, "New"), manager).getMessage().startsWith("ERROR:"));
            }
            for (String name : new String[]{null, "", " \t\u00a0\u2003"}) {
                assertTrue(command.execute(request("1", name), manager).getMessage().startsWith("ERROR:"));
            }
            command.execute(request(), manager);
            command.execute(request("1"), manager);
            assertEquals(0, command.writes);
            assertEquals("Old", ticket.getName());
        }
    
        @Test
        void rejectsUnauthorizedMissingAndForeignTickets() {
            CollectionManager manager = new CollectionManager();
            Ticket ticket = ticket(manager);
            StubCommand command = new StubCommand();
            command.authorized = false;
            command.execute(request(String.valueOf(ticket.getId()), "New"), manager);
            command.authorized = true;
            command.execute(request("1", "New"), manager);
            ticket.setOwnerId(8);
            command.execute(request(String.valueOf(ticket.getId()), "New"), manager);
            assertEquals(0, command.writes);
            assertEquals("Old", ticket.getName());
        }
    
        @Test
        void databaseFailureDoesNotChangeCollection() {
            CollectionManager manager = new CollectionManager();
            Ticket ticket = ticket(manager);
            StubCommand command = new StubCommand();
            command.saved = false;
            command.execute(request(String.valueOf(ticket.getId()), "New"), manager);
            command.fail = true;
            command.execute(request(String.valueOf(ticket.getId()), "New"), manager);
            assertEquals("Old", ticket.getName());
            manager.delete(ticket);
            assertTrue(manager.getCollection().isEmpty());
        }
    }

## Activity

    $ git diff --stat; git status --short
     src/main/java/org/example/client/Client.java                  |  8 ++++++++
     src/main/java/org/example/client/ScriptExecutor.java          |  5 +++++
     src/main/java/org/example/server/Server.java                  |  3 +--
     src/main/java/org/example/server/command/HelpCommand.java     |  1 +
     .../java/org/example/server/manager/CollectionManager.java    |  7 +++++++
     src/main/java/org/example/server/manager/CommandManager.java  |  1 +
     src/main/java/org/example/server/manager/DataBaseManager.java | 11 +++++++++++
     7 files changed, 34 insertions(+), 2 deletions(-)
     M src/main/java/org/example/client/Client.java
     M src/main/java/org/example/client/ScriptExecutor.java
     M src/main/java/org/example/server/Server.java
     M src/main/java/org/example/server/command/HelpCommand.java
     M src/main/java/org/example/server/manager/CollectionManager.java
     M src/main/java/org/example/server/manager/CommandManager.java
     M src/main/java/org/example/server/manager/DataBaseManager.java
    ?? .m2/
    ?? src/main/java/org/example/server/command/RenameTicketCommand.java
    ?? src/test/java/org/example/server/
    ✓ • 497ms

## Activity

    $ mvn -q "-Dmaven.repo.local=C:/Users/perlm/lab7/.m2/repository" test
    ������� ������� ��������: 1251
    13:08:59.298 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=9223372036854775807, name='Old', coordinates=null, creationDate=2026-10-02T13:08:59.293488+03:00[Europe/Moscow], price=100, discount=null, type=null, venue=null}
    13:08:59.338 [main] ERROR org.example.server.command.RenameTicketCommandTest$StubCommand -- Failed to rename ticket 9223372036854775807
    java.sql.SQLException: test failure
        at org.example.server.command.RenameTicketCommandTest$StubCommand.persist(RenameTicketCommandTest.java:26)
        at org.example.server.command.RenameTicketCommand.lambda$execute$1(RenameTicketCommand.java:49)
        at org.example.server.manager.CollectionManager.withLockedCollection(CollectionManager.java:138)
        at org.example.server.command.RenameTicketCommand.execute(RenameTicketCommand.java:42)
        at org.example.server.command.RenameTicketCommandTest.databaseFailureDoesNotChangeCollection(RenameTicketCommandTest.java:108)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:568)
        at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
        at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
        at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:147)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:86)
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:103)
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:93)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:92)
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:86)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$7(TestMethodTestDescriptor.java:218)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:214)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:139)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:69)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:151)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:198)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:169)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:93)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:58)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:141)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:57)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:103)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:85)
        at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
        at org.apache.maven.surefire.junitplatform.LauncherAdapter.executeWithoutCancellationToken(LauncherAdapter.java:60)
        at org.apache.maven.surefire.junitplatform.LauncherAdapter.execute(LauncherAdapter.java:52)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.execute(JUnitPlatformProvider.java:203)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invokeAllTests(JUnitPlatformProvider.java:168)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invoke(JUnitPlatformProvider.java:136)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:385)
        at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:162)
        at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:507)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:495)
    13:08:59.344 [main] INFO org.example.server.manager.CollectionManager -- Deleted ticket: Ticket{id=9223372036854775807, name='Old', coordinates=null, creationDate=2026-10-02T13:08:59.293488+03:00[Europe/Moscow], price=100, discount=null, type=null, venue=null}
    13:08:59.351 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=9223372036854775807, name='Old', coordinates=null, creationDate=2026-10-02T13:08:59.351556700+03:00[Europe/Moscow], price=100, discount=null, type=null, venue=null}
    13:08:59.353 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=9223372036854775807, name='�������  "��������" !', coordinates=null, creationDate=2026-10-02T13:08:59.351556700+03:00[Europe/Moscow], price=100, discount=null, type=null, venue=null}
    13:08:59.354 [main] INFO org.example.server.manager.CollectionManager -- Deleted ticket: Ticket{id=9223372036854775807, name='�������  "��������" !', coordinates=null, creationDate=2026-10-02T13:08:59.351556700+03:00[Europe/Moscow], price=100, discount=null, type=null, venue=null}
    13:08:59.360 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=9223372036854775807, name='Old', coordinates=null, creationDate=2026-10-02T13:08:59.360225100+03:00[Europe/Moscow], price=100, discount=null, type=null, venue=null}
    13:08:59.365 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=9223372036854775807, name='Old', coordinates=null, creationDate=2026-10-02T13:08:59.365215+03:00[Europe/Moscow], price=100, discount=null, type=null, venue=null}
    ✓ • 39.20s

## Assistant

Команда добавлена в клиент, скрипты и справку. Также исправил передачу ответа сервера: скрипты теперь получают сообщение об успехе или отказе напрямую. Проверяю повторное переименование, неверный ввод, права владельца и сохранность коллекции при ошибке БД.

## Activity

    file changes: Completed · 3 changes
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\client\ScriptExecutor.java
    @@ -13,2 +13,3 @@
     import java.nio.channels.SocketChannel;
    +import java.nio.charset.StandardCharsets;
     import java.util.Stack;
    @@ -48,3 +49,3 @@
     
    -        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
    +        try (BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
                 Factory factory = new ScriptTicketFactory(reader);
    Update { move_path: None }: C:\Users\perlm\lab7\src\main\java\org\example\server\command\RenameTicketCommand.java
    @@ -10,3 +10,2 @@
     import java.util.ArrayList;
    -import java.util.LinkedHashSet;
     
    Update { move_path: None }: C:\Users\perlm\lab7\src\test\java\org\example\server\command\RenameTicketCommandTest.java
    @@ -4,2 +4,5 @@
     import org.example.common.model.Ticket;
    +import org.example.common.model.Coordinates;
    +import org.example.common.model.Venue;
    +import org.example.common.model.enums.TicketType;
     import org.example.server.manager.CollectionManager;
    @@ -36,2 +39,6 @@
             ticket.setPrice(100);
    +        ticket.setCoordinates(new Coordinates(1.0f, 2L));
    +        ticket.setDiscount(10.0);
    +        ticket.setType(TicketType.values()[0]);
    +        ticket.setVenue(new Venue());
             manager.add(ticket);
    @@ -49,2 +56,5 @@
             var created = ticket.getCreationDate();
    +        var coordinates = ticket.getCoordinates();
    +        var venue = ticket.getVenue();
    +        var type = ticket.getType();
             StubCommand command = new StubCommand();
    @@ -60,2 +70,6 @@
                 assertEquals(created, ticket.getCreationDate());
    +            assertSame(coordinates, ticket.getCoordinates());
    +            assertSame(venue, ticket.getVenue());
    +            assertEquals(type, ticket.getType());
    +            assertEquals(10.0, ticket.getDiscount());
             }

## Activity

    $ Get-Content target/surefire-reports/*.txt | Select-String 'Tests run:'; git diff --check
    
    Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.106 s -- in org.example.common.RequestTest
    Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.222 s -- in org.example.server.command.RenameTicketCo
    mmandTest
    
    
    ✓ • 528ms

## Activity

    $ mvn -q "-Dmaven.repo.local=C:/Users/perlm/lab7/.m2/repository" test
    ������� ������� ��������: 1251
    13:15:17.291 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:15:17.285750400+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=2, venueName='null', capacity=null, type=null}}
    13:15:17.336 [main] ERROR org.example.server.command.RenameTicketCommandTest$StubCommand -- Failed to rename ticket 9223372036854775807
    java.sql.SQLException: test failure
        at org.example.server.command.RenameTicketCommandTest$StubCommand.persist(RenameTicketCommandTest.java:29)
        at org.example.server.command.RenameTicketCommand.lambda$execute$1(RenameTicketCommand.java:48)
        at org.example.server.manager.CollectionManager.withLockedCollection(CollectionManager.java:138)
        at org.example.server.command.RenameTicketCommand.execute(RenameTicketCommand.java:41)
        at org.example.server.command.RenameTicketCommandTest.databaseFailureDoesNotChangeCollection(RenameTicketCommandTest.java:122)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:568)
        at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
        at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
        at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:147)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:86)
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:103)
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:93)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:92)
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:86)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$7(TestMethodTestDescriptor.java:218)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:214)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:139)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:69)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:151)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:198)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:169)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:93)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:58)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:141)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:57)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:103)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:85)
        at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
        at org.apache.maven.surefire.junitplatform.LauncherAdapter.executeWithoutCancellationToken(LauncherAdapter.java:60)
        at org.apache.maven.surefire.junitplatform.LauncherAdapter.execute(LauncherAdapter.java:52)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.execute(JUnitPlatformProvider.java:203)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invokeAllTests(JUnitPlatformProvider.java:168)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invoke(JUnitPlatformProvider.java:136)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:385)
        at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:162)
        at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:507)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:495)
    13:15:17.346 [main] INFO org.example.server.manager.CollectionManager -- Deleted ticket: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:15:17.285750400+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=2, venueName='null', capacity=null, type=null}}
    13:15:17.368 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:15:17.368166200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=4, venueName='null', capacity=null, type=null}}
    13:15:17.370 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=9223372036854775807, name='�������  "��������" !', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:15:17.368166200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=4, venueName='null', capacity=null, type=null}}
    13:15:17.372 [main] INFO org.example.server.manager.CollectionManager -- Deleted ticket: Ticket{id=9223372036854775807, name='�������  "��������" !', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:15:17.368166200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=4, venueName='null', capacity=null, type=null}}
    13:15:17.375 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:15:17.375817100+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=6, venueName='null', capacity=null, type=null}}
    13:15:17.378 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:15:17.378815800+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=8, venueName='null', capacity=null, type=null}}
    ✓ • 42.75s

## Activity

    $ Get-Item target/surefire-reports/org.example.server.command.RenameTicketCommandTest.txt | Select-Object LastWriteTime; Get-Date
    
    LastWriteTime
    -------------
    02.10.2026 13:15:17
    
    
    
    ✓ • 229ms

## Activity

    $ $cacheSource = (Resolve-Path -LiteralPath 'C:\Users\perlm\lab7\.m2').Path
        $cacheTarget = Join-Path 'C:\Users\perlm\AppData\Local\Temp' ('lab7-maven-' + [guid]::NewGuid().ToString())
        if ($cacheSource -ne 'C:\Users\perlm\lab7\.m2' -or -not $cacheTarget.StartsWith('C:\Users\perlm\AppData\Local\Temp\lab7-maven-')) { throw 'Unexpected cache path' }
        Move-Item -LiteralPath $cacheSource -Destination $cacheTarget
        Get-Content target/surefire-reports/*.txt | Select-String 'Tests run:'
        git diff --check
        git status --short
    
    Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.086 s -- in org.example.common.RequestTest
    Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.214 s -- in org.example.server.command.RenameTicketCo
    mmandTest
     M src/main/java/org/example/client/Client.java
     M src/main/java/org/example/client/ScriptExecutor.java
     M src/main/java/org/example/server/Server.java
     M src/main/java/org/example/server/command/HelpCommand.java
     M src/main/java/org/example/server/manager/CollectionManager.java
     M src/main/java/org/example/server/manager/CommandManager.java
     M src/main/java/org/example/server/manager/DataBaseManager.java
    ?? src/main/java/org/example/server/command/RenameTicketCommand.java
    ?? src/test/java/org/example/server/
    
    
    ✓ • 464ms

## Assistant

Добавлена `rename_ticket`: ввод ID и названия отдельными строками, проверка данных, авторизации и владельца, обновление только названия в PostgreSQL и коллекции без дубликатов. При ошибке БД коллекция не изменяется. Справка обновлена; скрипты читаются в UTF-8.

Все 5 тестов проходят. Проверка с реальным PostgreSQL и перезапуском сервера не проводилась.

## User

2. ID  положительное целое число, представимое типом `long`. Отсутствующий ID, неверный формат, выход за пределы типа, ноль и отрицательные числа должны приводить к понятной ошибке без изменения данных. Новое название не должно отсутствовать, быть пустым или состоять только из пробельных символов. Удали пробельные символы по краям названия; пробелы внутри, кириллица, кавычки и другие символы должны сохраняться. Название считывается целой строкой.
3. Менять можно только свой билет. Для отсутствующего билета, чужого билета и пользователя без авторизации операция должна завершаться отказом без изменений.
4. При успехе изменяется только название. ID, владелец, цена, координаты, дата создания, скидка, тип и место проведения сохраняются. Повторное присвоение текущего названия допустимо и не должно создавать дубликаты.
5. Результат должен сохраняться в PostgreSQL и отражаться в основной коллекции: последующий просмотр показывает новое название, а после перезапуска сервера оно остаётся сохранённым. В коллекции не должны появляться дубликаты билета.
6. При ошибке сохранения в БД название в коллекции не должно изменяться.
7. Интерактивное выполнение и выполнение через скрипт должны давать одинаковый результат. В справке должна появиться новая команда с описанием ввода.
8. Добавь автоматические тесты успешного переименования, названия с пробелами и кириллицей, пустого/пробельного названия, неверного ID, отсутствующего/чужого билета и отказа сохранения. В тестах проверь сохранение остальных полей, отсутствие дубликатов и отсутствие частичного изменения при отказе.

## Assistant

Проверю добавленные тесты по полному списку требований, особенно сохранение остальных полей и отсутствие изменений при отказе.

## Activity

    $ Get-Content -Encoding UTF8 src/test/java/org/example/server/command/RenameTicketCommandTest.java; Get-Content -Encoding UTF8 src/main/java/org/example/server/command/RenameTicketCommand.java; Get-Content -Encoding UTF8 src/main/java/org/example/common/model/Venue.java | Select-Object -First 100; Get-ChildItem C:/Users/perlm/AppData/Local/Temp -Directory -Filter lab7-maven-* | Select-Object -ExpandProperty FullName
    package org.example.server.command;
    
    import org.example.common.Request;
    import org.example.common.model.Ticket;
    import org.example.common.model.Coordinates;
    import org.example.common.model.Venue;
    import org.example.common.model.enums.TicketType;
    import org.example.server.manager.CollectionManager;
    import org.junit.jupiter.api.Test;
    
    import java.sql.SQLException;
    
    import static org.junit.jupiter.api.Assertions.*;
    
    class RenameTicketCommandTest {
        static class StubCommand extends RenameTicketCommand {
            boolean authorized = true;
            boolean saved = true;
            boolean fail;
            int writes;
            long savedId;
            String savedName;
            protected boolean authenticate(Request request) { return authorized; }
            protected int userId(String login) { return 7; }
            protected boolean persist(long id, int owner, String name) throws SQLException {
                writes++;
                savedId = id;
                savedName = name;
                if (fail) throw new SQLException("test failure");
                return saved;
            }
        }
    
        private Ticket ticket(CollectionManager manager) {
            Ticket ticket = new Ticket();
            ticket.setId(Long.MAX_VALUE);
            ticket.setOwnerId(7);
            ticket.setName("Old");
            ticket.setPrice(100);
            ticket.setCoordinates(new Coordinates(1.0f, 2L));
            ticket.setDiscount(10.0);
            ticket.setType(TicketType.values()[0]);
            ticket.setVenue(new Venue());
            manager.add(ticket);
            return ticket;
        }
    
        private Request request(String... args) {
            return new Request("rename_ticket", args, null, "owner", "password");
        }
    
        @Test
        void renamesOnlyNameAndKeepsSetUsableOnRepeatedRename() {
            CollectionManager manager = new CollectionManager();
            Ticket ticket = ticket(manager);
            var created = ticket.getCreationDate();
            var coordinates = ticket.getCoordinates();
            var venue = ticket.getVenue();
            var type = ticket.getType();
            StubCommand command = new StubCommand();
            String name = "Концерт  \"оркестра\" !";
            for (int i = 0; i < 2; i++) {
                assertSame(ticket, command.execute(request("9223372036854775807", "\u00a0\t " + name + " \u2003"), manager).getData());
                assertEquals(name, ticket.getName());
                assertEquals(1, manager.getCollection().size());
                assertEquals(Long.MAX_VALUE, command.savedId);
                assertEquals(name, command.savedName);
                assertEquals(7, ticket.getOwnerId());
                assertEquals(100, ticket.getPrice());
                assertEquals(created, ticket.getCreationDate());
                assertSame(coordinates, ticket.getCoordinates());
                assertSame(venue, ticket.getVenue());
                assertEquals(type, ticket.getType());
                assertEquals(10.0, ticket.getDiscount());
            }
            manager.add(ticket);
            assertEquals(1, manager.getCollection().size());
            manager.delete(ticket);
            assertTrue(manager.getCollection().isEmpty());
        }
    
        @Test
        void invalidInputDoesNotWrite() {
            CollectionManager manager = new CollectionManager();
            Ticket ticket = ticket(manager);
            StubCommand command = new StubCommand();
            for (String id : new String[]{null, "", "abc", "1.2", "0", "-1", "9223372036854775808"}) {
                assertTrue(command.execute(request(id, "New"), manager).getMessage().startsWith("ERROR:"));
            }
            for (String name : new String[]{null, "", " \t\u00a0\u2003"}) {
                assertTrue(command.execute(request("1", name), manager).getMessage().startsWith("ERROR:"));
            }
            command.execute(request(), manager);
            command.execute(request("1"), manager);
            assertEquals(0, command.writes);
            assertEquals("Old", ticket.getName());
        }
    
        @Test
        void rejectsUnauthorizedMissingAndForeignTickets() {
            CollectionManager manager = new CollectionManager();
            Ticket ticket = ticket(manager);
            StubCommand command = new StubCommand();
            command.authorized = false;
            command.execute(request(String.valueOf(ticket.getId()), "New"), manager);
            command.authorized = true;
            command.execute(request("1", "New"), manager);
            ticket.setOwnerId(8);
            command.execute(request(String.valueOf(ticket.getId()), "New"), manager);
            assertEquals(0, command.writes);
            assertEquals("Old", ticket.getName());
        }
    
        @Test
        void databaseFailureDoesNotChangeCollection() {
            CollectionManager manager = new CollectionManager();
            Ticket ticket = ticket(manager);
            StubCommand command = new StubCommand();
            command.saved = false;
            command.execute(request(String.valueOf(ticket.getId()), "New"), manager);
            command.fail = true;
            command.execute(request(String.valueOf(ticket.getId()), "New"), manager);
            assertEquals("Old", ticket.getName());
            manager.delete(ticket);
            assertTrue(manager.getCollection().isEmpty());
        }
    }
    package org.example.server.command;
    
    import org.example.common.Request;
    import org.example.common.Response;
    import org.example.common.model.Ticket;
    import org.example.server.manager.CollectionManager;
    import org.example.server.manager.DataBaseManager;
    
    import java.sql.SQLException;
    import java.util.ArrayList;
    
    /** Renames an owned ticket only after its new name has been saved. */
    public class RenameTicketCommand extends AbstractCommand {
        @Override
        public Response execute(Request request, CollectionManager collectionManager) {
            String[] args = request.getArgs();
            if (args == null || args.length == 0 || args[0] == null || args[0].isBlank()) {
                return new Response("ERROR: ID билета отсутствует", null);
            }
            long id;
            try {
                id = Long.parseLong(args[0].strip());
                if (id <= 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                return new Response("ERROR: ID должен быть положительным целым числом в пределах long", null);
            }
            if (args.length < 2 || args[1] == null) {
                return new Response("ERROR: Новое название отсутствует", null);
            }
            String name = stripWhitespace(args[1]);
            if (name.isEmpty()) {
                return new Response("ERROR: Название не должно быть пустым или состоять из пробельных символов", null);
            }
            if (!authenticate(request)) {
                return new Response("ERROR: Требуется авторизация", null);
            }
            int ownerId = userId(request.getLogin());
            if (ownerId <= 0) {
                return new Response("ERROR: Не удалось определить пользователя", null);
            }
            return collectionManager.withLockedCollection(collection -> {
                Ticket ticket = collection.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
                if (ticket == null) return new Response("ERROR: Билет не найден", null);
                if (ticket.getOwnerId() != ownerId) {
                    return new Response("ERROR: Можно переименовать только свой билет", null);
                }
                try {
                    if (!persist(id, ownerId, name)) {
                        return new Response("ERROR: Билет не найден или доступ запрещён", null);
                    }
                } catch (SQLException e) {
                    logger.error("Failed to rename ticket {}", id, e);
                    return new Response("ERROR: Не удалось сохранить название в БД", null);
                }
                // Name participates in hashCode: rebuild the set without changing its order.
                ArrayList<Ticket> tickets = new ArrayList<>(collection);
                collection.clear();
                ticket.setName(name);
                collection.addAll(tickets);
                return new Response("Название билета успешно изменено", ticket);
            });
        }
    
        private static String stripWhitespace(String value) {
            int start = 0;
            int end = value.length();
            while (start < end && isWhitespace(value.codePointAt(start))) {
                start += Character.charCount(value.codePointAt(start));
            }
            while (start < end && isWhitespace(value.codePointBefore(end))) {
                end -= Character.charCount(value.codePointBefore(end));
            }
            return value.substring(start, end);
        }
    
        private static boolean isWhitespace(int codePoint) {
            return Character.isWhitespace(codePoint) || Character.isSpaceChar(codePoint);
        }
    
        protected boolean authenticate(Request request) {
            return DataBaseManager.checkUser(request.getLogin(), request.getPassword());
        }
    
        protected int userId(String login) {
            return DataBaseManager.getUserId(login);
        }
    
        protected boolean persist(long id, int ownerId, String name) throws SQLException {
            return DataBaseManager.renameTicket(id, ownerId, name);
        }
    }
    package org.example.common.model;
    
    import org.example.common.model.enums.VenueType;
    import org.example.common.model.generator.IdGenerator;
    
    import java.io.Serializable;
    import java.util.Objects;
    
    /**
     * Класс Venue представляет место проведения мероприятия.
     * Содержит уникальный идентификатор, название, вместимость и тип.
     */
    public class Venue implements Serializable {
        private Long id; // Поле не может быть null, значение должно быть больше 0 и генерироваться автоматически
        private String venueName; // Поле не может быть null и не должно быть пустым
        private Integer capacity; // Поле не может быть null, значение должно быть больше 0
        private VenueType type; // Поле может быть null
    
        /**
         * Конструктор по умолчанию. Генерирует уникальный id.
         */
        public Venue() {
            this.id = IdGenerator.generateId();
            this.venueName = null;
            this.capacity = null;
            this.type = null;
        }
    
        /**
         * Конструктор с параметрами.
         *
         * @param id уникальный идентификатор (должен быть больше 0)
         * @param venueName название места (не может быть null или пустым)
         * @param capacity вместимость (должна быть больше 0)
         * @param type тип места проведения (может быть null)
         */
        public Venue(long id, String venueName, Integer capacity, VenueType type) {
            this.id = id;
            this.venueName = venueName;
            this.capacity = capacity;
            this.type = type;
        }
    
        /**
         * Конструктор с авто-генерацией ID.
         *
         * @param venueName название места (не может быть null или пустым)
         * @param capacity вместимость (должна быть больше 0)
         * @param type тип места проведения (может быть null)
         */
        public Venue(String venueName, Integer capacity, VenueType type) {
            this.id = IdGenerator.generateId();
            this.venueName = venueName;
            this.capacity = capacity;
            this.type = type;
        }
    
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
    
        public String getVenueName() {
            return venueName;
        }
    
        public void setVenueName(String venueName) {
            this.venueName = venueName;
        }
    
        public Integer getCapacity() {
            return capacity;
        }
    
        public void setCapacity(Integer capacity) {
            this.capacity = capacity;
        }
    
        public VenueType getType() {
            return type;
        }
    
        public void setType(VenueType type) {
            this.type = type;
        }
    
        @Override
        public String toString() {
            return "Venue{" +
                    "id=" + id +
                    ", venueName='" + venueName + '\'' +
                    ", capacity=" + capacity +
                    ", type=" + type +
                    '}';
        }
    
        @Override
    C:\Users\perlm\AppData\Local\Temp\lab7-maven-c7cde7fc-5bbd-49bc-9301-5ecee49783f9
    ✓ • 370ms

## Activity

    file changes: Completed · 1 changes
    Update { move_path: None }: C:\Users\perlm\lab7\src\test\java\org\example\server\command\RenameTicketCommandTest.java
    @@ -7,2 +7,3 @@
     import org.example.common.model.enums.TicketType;
    +import org.example.common.model.enums.VenueType;
     import org.example.server.manager.CollectionManager;
    @@ -42,3 +43,3 @@
             ticket.setType(TicketType.values()[0]);
    -        ticket.setVenue(new Venue());
    +        ticket.setVenue(new Venue(42, "Большой зал", 500, VenueType.values()[0]));
             manager.add(ticket);
    @@ -51,2 +52,10 @@
     
    +    private void assertUnchanged(CollectionManager manager, Ticket ticket, String before, int owner) {
    +        assertEquals(before, ticket.toString());
    +        assertEquals(owner, ticket.getOwnerId());
    +        assertEquals(1, manager.getCollection().size());
    +        assertSame(ticket, manager.getById(ticket.getId()));
    +        assertTrue(manager.getCollection().contains(ticket));
    +    }
    +
         @Test
    @@ -59,2 +68,3 @@
             var type = ticket.getType();
    +        String venueBefore = venue.toString();
             StubCommand command = new StubCommand();
    @@ -66,2 +76,3 @@
                 assertEquals(Long.MAX_VALUE, command.savedId);
    +            assertEquals(Long.MAX_VALUE, ticket.getId());
                 assertEquals(name, command.savedName);
    @@ -71,3 +82,6 @@
                 assertSame(coordinates, ticket.getCoordinates());
    +            assertEquals(1.0f, ticket.getCoordinates().getX());
    +            assertEquals(2L, ticket.getCoordinates().getY());
                 assertSame(venue, ticket.getVenue());
    +            assertEquals(venueBefore, ticket.getVenue().toString());
                 assertEquals(type, ticket.getType());
    @@ -86,10 +100,15 @@
             StubCommand command = new StubCommand();
    -        for (String id : new String[]{null, "", "abc", "1.2", "0", "-1", "9223372036854775808"}) {
    +        String before = ticket.toString();
    +        for (String id : new String[]{null, "", "  ", "abc", "1.2", "0", "-1", "9223372036854775808", "-9223372036854775809"}) {
                 assertTrue(command.execute(request(id, "New"), manager).getMessage().startsWith("ERROR:"));
    +            assertUnchanged(manager, ticket, before, 7);
             }
             for (String name : new String[]{null, "", " \t\u00a0\u2003"}) {
    -            assertTrue(command.execute(request("1", name), manager).getMessage().startsWith("ERROR:"));
    +            assertTrue(command.execute(request(String.valueOf(ticket.getId()), name), manager).getMessage().startsWith("ERROR:"));
    +            assertUnchanged(manager, ticket, before, 7);
             }
    -        command.execute(request(), manager);
    -        command.execute(request("1"), manager);
    +        assertTrue(command.execute(request(), manager).getMessage().startsWith("ERROR:"));
    +        assertTrue(command.execute(request(String.valueOf(ticket.getId())), manager).getMessage().startsWith("ERROR:"));
    +        assertTrue(command.execute(request((String[]) null), manager).getMessage().startsWith("ERROR:"));
    +        assertUnchanged(manager, ticket, before, 7);
             assertEquals(0, command.writes);
    @@ -103,8 +122,12 @@
             StubCommand command = new StubCommand();
    +        String before = ticket.toString();
             command.authorized = false;
    -        command.execute(request(String.valueOf(ticket.getId()), "New"), manager);
    +        assertTrue(command.execute(request(String.valueOf(ticket.getId()), "New"), manager).getMessage().startsWith("ERROR:"));
    +        assertUnchanged(manager, ticket, before, 7);
             command.authorized = true;
    -        command.execute(request("1", "New"), manager);
    +        assertTrue(command.execute(request("1", "New"), manager).getMessage().startsWith("ERROR:"));
    +        assertUnchanged(manager, ticket, before, 7);
             ticket.setOwnerId(8);
    -        command.execute(request(String.valueOf(ticket.getId()), "New"), manager);
    +        assertTrue(command.execute(request(String.valueOf(ticket.getId()), "New"), manager).getMessage().startsWith("ERROR:"));
    +        assertUnchanged(manager, ticket, before, 8);
             assertEquals(0, command.writes);
    @@ -118,6 +141,9 @@
             StubCommand command = new StubCommand();
    +        String before = ticket.toString();
             command.saved = false;
    -        command.execute(request(String.valueOf(ticket.getId()), "New"), manager);
    +        assertTrue(command.execute(request(String.valueOf(ticket.getId()), "New"), manager).getMessage().startsWith("ERROR:"));
    +        assertUnchanged(manager, ticket, before, 7);
             command.fail = true;
    -        command.execute(request(String.valueOf(ticket.getId()), "New"), manager);
    +        assertTrue(command.execute(request(String.valueOf(ticket.getId()), "New"), manager).getMessage().startsWith("ERROR:"));
    +        assertUnchanged(manager, ticket, before, 7);
             assertEquals("Old", ticket.getName());

## Activity

    $ mvn -q "-Dmaven.repo.local=C:/Users/perlm/AppData/Local/Temp/lab7-maven-c7cde7fc-5bbd-49bc-9301-5ecee49783f9/repository" test
    ������� ������� ��������: 1251
    13:17:15.177 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.171490200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.234 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.171490200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.239 [main] ERROR org.example.server.command.RenameTicketCommandTest$StubCommand -- Failed to rename ticket 9223372036854775807
    java.sql.SQLException: test failure
        at org.example.server.command.RenameTicketCommandTest$StubCommand.persist(RenameTicketCommandTest.java:30)
        at org.example.server.command.RenameTicketCommand.lambda$execute$1(RenameTicketCommand.java:48)
        at org.example.server.manager.CollectionManager.withLockedCollection(CollectionManager.java:138)
        at org.example.server.command.RenameTicketCommand.execute(RenameTicketCommand.java:41)
        at org.example.server.command.RenameTicketCommandTest.databaseFailureDoesNotChangeCollection(RenameTicketCommandTest.java:147)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:568)
        at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
        at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
        at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:147)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:86)
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:103)
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:93)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:92)
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:86)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$7(TestMethodTestDescriptor.java:218)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:214)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:139)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:69)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:151)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:198)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:169)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:93)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:58)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:141)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:57)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:103)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:85)
        at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
        at org.apache.maven.surefire.junitplatform.LauncherAdapter.executeWithoutCancellationToken(LauncherAdapter.java:60)
        at org.apache.maven.surefire.junitplatform.LauncherAdapter.execute(LauncherAdapter.java:52)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.execute(JUnitPlatformProvider.java:203)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invokeAllTests(JUnitPlatformProvider.java:168)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invoke(JUnitPlatformProvider.java:136)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:385)
        at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:162)
        at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:507)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:495)
    13:17:15.245 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.171490200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.246 [main] INFO org.example.server.manager.CollectionManager -- Deleted ticket: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.171490200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.251 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.251155100+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.252 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=9223372036854775807, name='�������  "��������" !', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.251155100+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.253 [main] INFO org.example.server.manager.CollectionManager -- Deleted ticket: Ticket{id=9223372036854775807, name='�������  "��������" !', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.251155100+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.256 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.256873200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.256 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.256873200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.257 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.256873200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.257 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.256873200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.261 [main] INFO org.example.server.manager.CollectionManager -- Added ticket: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.261541200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.262 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.261541200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.262 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.261541200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.262 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.261541200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.263 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.261541200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.263 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.261541200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.264 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.261541200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.264 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.261541200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.265 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.261541200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.265 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.261541200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.266 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.261541200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.266 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.261541200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.266 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.261541200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    13:17:15.267 [main] INFO org.example.server.manager.CollectionManager -- Found ticket by ID 9223372036854775807: Ticket{id=9223372036854775807, name='Old', coordinates=Coordinates{x=1.0, y=2}, creationDate=2026-10-02T13:17:15.261541200+03:00[Europe/Moscow], price=100, discount=10.0, type=VIP, venue=Venue{id=42, venueName='������� ���', capacity=500, type=OPEN_AREA}}
    ✓ • 17.99s

## Activity

    $ git diff --check; Get-Content target/surefire-reports/*.txt | Select-String 'Tests run:'
    
    Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.116 s -- in org.example.common.RequestTest
    Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.228 s -- in org.example.server.command.RenameTicketCo
    mmandTest
    
    
    ✓ • 351ms

## Assistant

Расширены автоматические тесты: проверяются кириллица, кавычки, внутренние пробелы, обрезка пробелов по краям, некорректные и отсутствующие аргументы, права доступа и отказ БД.

После успеха проверяются остальные поля и отсутствие дубликатов; после каждого отказа — неизменность билета и коллекции.

Все 5 тестов проходят. Сохранение и перезапуск с реальным PostgreSQL пока не проверены; в тестах БД заменена заглушкой.
