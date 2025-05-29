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
        try {
            DataBaseManager.clear(request.getLogin());
            collectionManager.clear();
            logger.info("Cleared {} tickets for {}", request.getLogin());
            return new Response("Removed  tickets", null);
        } catch (Exception e) {
            logger.error("Clear failed for {}: {}", request.getLogin(), e.getMessage());
            return new Response("ERROR: Clear failed - " + e.getMessage(), null);
        }
    }
}