package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.model.Ticket;
import org.example.server.manager.CollectionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Команда для вывода элементов коллекции Ticket в порядке убывания.
 */
public class PrintDescendingCommand extends AbstractCommand {

    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        LinkedHashSet<Ticket> tickets = collectionManager.getCollection();

        if (tickets.isEmpty()) {
            logger.warn("Attempted to print descending, but the collection is empty.");
            return new Response("The collection is empty.", null);
        }

        List<Ticket> sortedList = new ArrayList<>(tickets);
        sortedList.sort(Collections.reverseOrder());

        StringBuilder sb = new StringBuilder("Tickets in descending order:\n");
        for (Ticket ticket : sortedList) {
            sb.append(ticket).append("\n");
        }

        logger.info("Printed {} tickets in descending order.", sortedList.size());
        return new Response(sb.toString(), null);
    }
}
