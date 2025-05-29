package org.example.server.command;

import org.example.client.TicketInput;
import org.example.common.Request;
import org.example.common.Response;
import org.example.common.model.Ticket;
import org.example.server.manager.CollectionManager;
import org.example.server.manager.DataBaseManager;

/**
 * Команда для добавления нового объекта {@link Ticket} в коллекцию.
 * Использует ввод данных через {@link TicketInput}.
 *
 * @author kdseum9
 * @version 1.0
 */
public class AddCommand extends AbstractCommand {

    /**
     * Выполняет команду с заданными аргументами и менеджером коллекции.
     * Возвращает результат выполнения команды в виде объекта Response.
     *
     * @param request запрос, содержащий команду и параметры
     * @param collectionManager объект, управляющий коллекцией элементов
     * @return результат выполнения команды в виде объекта Response
     */
    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        Ticket ticket = request.getTicket();  // Генерация нового билета через TicketInput

        if (ticket == null) {
            return new Response("ERROR: Failed to generate ticket.", null);
        }

        try {
            DataBaseManager.insertTicket(ticket, request.getLogin());
            collectionManager.add(ticket);
        } catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
        logger.info("Ticket added: {}", ticket);

        return new Response("Ticket added successfully.", ticket);  // Возвращаем успешный ответ
    }
}
