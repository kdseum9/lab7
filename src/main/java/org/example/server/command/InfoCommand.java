package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.server.manager.CollectionManager;

/**
 * Команда для отображения информации о коллекции.
 */
public class InfoCommand extends AbstractCommand {

    /**
     * Выполняет команду info.
     *
     * @param request объект запроса
     * @param manager менеджер коллекции
     * @return объект ответа с информацией о коллекции
     */
    @Override
    public Response execute(Request request, CollectionManager manager) {
        logger.info("Executing 'info' command");

        StringBuilder sb = new StringBuilder();
        sb.append("Application Info:\n");
        sb.append("- Collection type: ").append(manager.getCollection().getClass().getSimpleName()).append("\n");
        sb.append("- Collection size: ").append(manager.getCollection().size()).append("\n");
        sb.append("- Initialization time: ").append(manager.getTimeOfInitial()).append("\n");

        return new Response(sb.toString(), null);
    }
}
