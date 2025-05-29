package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.model.Ticket;
import org.example.server.manager.CollectionManager;

import java.util.Optional;

public class RemoveByIdCommand extends AbstractCommand {

    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        String[] args = request.getArgs();

        if (args.length < 2) {
            logger.warn("ID was not provided for remove_by_id command.");
            return new Response("Please provide an ID.", null);
        }

        try {
            long id = Long.parseLong(args[1]);

            Optional<Ticket> ticketToRemove = collectionManager.getCollection()
                    .stream()
                    .filter(ticket -> ticket.getId() == id)
                    .findFirst();

            if (ticketToRemove.isPresent()) {
                collectionManager.getCollection().remove(ticketToRemove.get());
                logger.info("Removed ticket with ID: {}", id);
                return new Response("Ticket with ID " + id + " was successfully removed.", null);
            } else {
                logger.info("No ticket found with ID: {}", id);
                return new Response("No ticket with ID " + id + " found.", null);
            }

        } catch (NumberFormatException e) {
            logger.error("Invalid ID format: {}", args[1], e);
            return new Response("Invalid ID format. Please enter a numeric ID.", null);
        }
    }
}
