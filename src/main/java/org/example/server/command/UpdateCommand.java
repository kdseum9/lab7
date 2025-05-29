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
