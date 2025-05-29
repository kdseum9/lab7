package org.example.client;

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
                    login = scanner.nextLine();
                    password = scanner.nextLine();
                    request = new Request(command, new String[]{login, password}, null);
                    break;
                case "add":
                case "add_if_max":
                case "remove_greater":
                case "remove_lower":
                    request = new Request(command, new String[0], TicketInput.generateTicket());
                    break;
                case "update":
                    System.out.print("Enter ID of the ticket to update: ");
                    long id = Long.parseLong(scanner.nextLine());
                    request = new Request(command, new String[]{String.valueOf(id)}, TicketInput.generateTicket());
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
            bigBuffer.put(byteBuffer.array());
        }

        System.out.println("Данные прочитаны");

        return bigBuffer;
    }
}
