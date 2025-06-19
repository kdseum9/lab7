package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.server.manager.CollectionManager;
import org.example.server.manager.DataBaseManager;

/**
 * Команда для очистки коллекции.
 * <p>
 * Удаляет все элементы из коллекции, если она не пуста.
 * </p>
 *
 * @author kdseum9
 * @version 1.0
 */
public class ClearCommand extends AbstractCommand {
    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        String userLogin = request.getLogin();
        try {
            boolean dbCleared = DataBaseManager.clear(userLogin);

            if (dbCleared) {
                int ticketsRemovedFromMemory = collectionManager.clear(userLogin);

                logger.info("Successfully cleared {} tickets from database and {} from in-memory collection for user {}.",
                        ticketsRemovedFromMemory, ticketsRemovedFromMemory, userLogin);
                return new Response("Successfully cleared " + ticketsRemovedFromMemory + " tickets for user " + userLogin + ".", null);
            } else {
                logger.info("No tickets found to clear in the database for user {} or DB operation failed.", userLogin);
                return new Response("No tickets found to clear for user " + userLogin, null);
            }
        } catch (Exception e) {
            logger.error("Clear command failed for user {}: {}", userLogin, e.getMessage(), e); // Log the exception
            return new Response("ERROR: Clear failed - " + e.getMessage(), null);
        }
    }
    }
