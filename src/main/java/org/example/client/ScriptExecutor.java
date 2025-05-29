package org.example.client;

import org.example.common.Request;
import org.example.common.Response;
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
                        Ticket ticket = TicketInput.generateTicketFromScript(reader);
                        request = new Request(command, new String[0], ticket);
                        break;

                    case "update":
                        String idLine = reader.readLine();
                        long id = Long.parseLong(idLine.trim());
                        Ticket updatedTicket = TicketInput.generateTicketFromScript(reader);
                        request = new Request(command, new String[]{String.valueOf(id)}, updatedTicket);
                        break;

                    case "remove_by_id":
                        String removeId = reader.readLine().trim();
                        request = new Request(command, new String[]{removeId}, null);
                        break;

                    case "remove_greater":
                    case "remove_lower":
                        Ticket compareTicket = TicketInput.generateTicketFromScript(reader);
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
        } finally {
            if (!executedScripts.isEmpty() && executedScripts.peek().equals(filePath)) {
                executedScripts.pop();
            } else {
                executedScripts.remove(filePath);
            }
        }
    }
}
