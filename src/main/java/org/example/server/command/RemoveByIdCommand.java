package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.model.Ticket;
import org.example.server.manager.CollectionManager;
import org.example.server.manager.DataBaseManager;

import java.util.Optional;

public class RemoveByIdCommand extends AbstractCommand {

    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        String[] args = request.getArgs();
        String userLogin = request.getLogin();
        long ticketId;
        try {
            ticketId = Long.parseLong(args[0]);
        } catch (NumberFormatException e) {
            logger.error("Invalid ID format for removal: {}", args[0], e);
            return new Response("Invalid ID format. Please enter a numeric ID.", null);
        }

        synchronized (collectionManager.getCollection()) {
            boolean removedFromDb;
            try {
                // Вызываем метод DataBaseManager, который уже проверяет владельца по userLogin
                removedFromDb = DataBaseManager.removeTicketByName(userLogin, String.valueOf(ticketId));
            } catch (Exception e) {
                logger.error("Database error during remove_by_id for ID {} by user {}: {}", ticketId, userLogin, e.getMessage(), e);
                return new Response("ERROR: Database operation failed during removal: " + e.getMessage(), null);
            }

            if (removedFromDb) {
                logger.info("Ticket with ID {} successfully removed from database by user {}.", ticketId, userLogin);

                // Если удалено из БД, удаляем из коллекции в памяти
                boolean removedFromMemory = collectionManager.removeById(ticketId);

                if (removedFromMemory) {
                    logger.info("Ticket with ID {} successfully removed from in-memory collection.", ticketId);
                    return new Response("Ticket with ID " + ticketId + " was successfully removed.", null);
                } else {
                    // Очень редкий сценарий рассинхронизации
                    logger.warn("Ticket with ID {} was removed from DB by user {}, but could not be removed from in-memory collection. Data inconsistency detected.", ticketId, userLogin);
                    return new Response("Ticket was removed from database, but an error occurred updating the local collection. Please refresh.", null);
                }
            } else {
                // Билет не найден в БД или пользователь не является его владельцем
                logger.info("Failed to remove ticket with ID {} for user {}: Ticket not found or user is not the owner.", ticketId, userLogin);
                return new Response("Failed to remove ticket with ID " + ticketId + ". It might not exist or you are not the owner.", null);
            }
        }
    }
}
