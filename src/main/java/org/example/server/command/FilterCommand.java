package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.model.Ticket;
import org.example.server.manager.CollectionManager;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Команда {@code filter_starts_with_name} фильтрует элементы коллекции по префиксу имени.
 */
public class FilterCommand extends AbstractCommand {

    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        String[] args = request.getArgs();

        if (args.length < 2) {
            logger.warn("Filter argument not provided.");
            return new Response("ERROR: You must provide a prefix to filter by.", null);
        }

        String filter = args[1];

        List<Ticket> filtered = collectionManager.getCollection().stream()
                .filter(ticket -> ticket.getName() != null && ticket.getName().startsWith(filter))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            return new Response("No tickets found starting with \"" + filter + "\".", null);
        }

        return new Response("Tickets found: " + filtered.size(), filtered); // Второй аргумент — сами билеты
    }

}
