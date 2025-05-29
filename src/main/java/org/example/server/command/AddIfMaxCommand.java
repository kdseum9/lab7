package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.model.Ticket;
import org.example.server.manager.CollectionManager;
import org.example.server.manager.DataBaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddIfMaxCommand extends AbstractCommand {
    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        Ticket ticket = request.getTicket();
        if (ticket == null) {
            return new Response("ERROR: Ticket data missing", null);
        }

        try {
            if (!collectionManager.add_if_max(ticket)) {
                return new Response("Ticket is not the maximum in collection", null);
            }

            DataBaseManager.insertTicket(ticket, request.getLogin());
            collectionManager.add(ticket);
            logger.info("Max ticket added by {}: {}", request.getLogin(), ticket.getId());
            return new Response("Max ticket added successfully", ticket);
        } catch (Exception e) {
            logger.error("AddIfMax failed: {}", e.getMessage());
            return new Response("ERROR: AddIfMax failed - " + e.getMessage(), null);
        }
    }
}