package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.model.Ticket;
import org.example.server.manager.CollectionManager;
import org.example.server.manager.DataBaseManager;
import org.example.server.manager.DataBaseManager.RenameTicketResult;

/**
 * Renames a ticket owned by the authenticated user.
 */
public class RenameTicketCommand extends AbstractCommand {

    @FunctionalInterface
    interface RenameOperation {
        RenameTicketResult rename(long id, String newName, String login);
    }

    private final RenameOperation renameOperation;

    public RenameTicketCommand() {
        this(DataBaseManager::renameTicket);
    }

    RenameTicketCommand(RenameOperation renameOperation) {
        this.renameOperation = renameOperation;
    }

    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        if (request.getLogin() == null || request.getLogin().isBlank()) {
            return new Response("ERROR: Please, log in first.", null);
        }

        String[] args = request.getArgs();
        if (args == null || args.length < 2) {
            return new Response("ERROR: rename_ticket requires a ticket ID and a new name.", null);
        }

        long ticketId;
        try {
            ticketId = Long.parseLong(args[0].trim());
            if (ticketId <= 0) {
                throw new NumberFormatException("ID must be positive");
            }
        } catch (NumberFormatException e) {
            return new Response("ERROR: Invalid ticket ID. Please enter a positive numeric ID.", null);
        }

        String newName = args[1] == null ? "" : args[1].trim();
        if (newName.isEmpty()) {
            return new Response("ERROR: Ticket name cannot be empty or contain only whitespace.", null);
        }

        RenameTicketResult result = renameOperation.rename(ticketId, newName, request.getLogin());
        return switch (result) {
            case NOT_FOUND -> new Response("ERROR: Ticket with ID " + ticketId + " was not found.", null);
            case ACCESS_DENIED -> new Response("ERROR: You can rename only your own tickets.", null);
            case DATABASE_ERROR -> new Response("ERROR: Could not save the new ticket name due to a database error.", null);
            case SUCCESS -> renameInCollection(collectionManager, ticketId, newName, request.getLogin());
        };
    }

    private Response renameInCollection(CollectionManager collectionManager, long ticketId,
                                        String newName, String login) {
        Ticket renamedTicket = collectionManager.renameTicket(ticketId, newName);
        if (renamedTicket == null) {
            logger.error("Ticket {} was renamed in the database but is absent from the collection", ticketId);
            return new Response(
                    "ERROR: Ticket was renamed in the database, but the local collection is out of sync.",
                    null
            );
        }

        logger.info("Ticket {} renamed by {}", ticketId, login);
        return new Response("Ticket renamed successfully.", renamedTicket);
    }
}
