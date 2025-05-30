
package org.example.server;


import org.example.client.TicketInput;
import org.example.server.manager.CollectionManager;
import org.example.server.manager.CommandManager;
import org.example.common.Request;
import org.example.common.Response;
import org.example.server.manager.DataBaseManager;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;


public class Server {

    private final CollectionManager collectionManager;
    private final CommandManager commandManager;
    private HashMap<String, SocketChannel> users = new HashMap<>();

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
        Response response;
        String login = request.getLogin();
        String password = request.getPassword();

        if (users.containsKey(login)) {
            if (users.get(login).equals(clientChannel)) {
                try {
                    response = new Response(null, commandManager.doCommand(request, collectionManager));
                } catch (Exception e) {
                    response = new Response("Command execution error: " + e.getMessage(), null);
                }
            } else {
                response = new Response("Something went wrong", null);
            }
        } else if (request.getCommandName().equals("login")) {
            if (DataBaseManager.checkUser(login, password)){
                response = new Response("You are log in!", null);
                users.put(login, clientChannel);
            } else {
                response = new Response("You are not log in.", null);
            }
        } else if (request.getCommandName().equals("register")){
            if (DataBaseManager.insertUser(login, password)){
                response = new Response("You are log in", null);
                users.put(login, clientChannel);
            } else {
                response = new Response("Something went wrong", null);
            }
        } else {
            response = new Response("Please, log in", null);
        }

        return response;
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
