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
