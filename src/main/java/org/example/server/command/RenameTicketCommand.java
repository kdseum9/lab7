package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.RenameTicketArguments;
import org.example.server.manager.CollectionManager;
import org.example.server.manager.DataBaseManager;

public class RenameTicketCommand extends AbstractCommand {
    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        String[] args = request.getArgs();
        RenameTicketArguments rename;
        try {
            if (args == null || args.length != 2) {
                return new Response("ERROR: rename_ticket requires a ticket ID and a new name", null);
            }
            rename = RenameTicketArguments.parse(args[0], args[1]);
        } catch (IllegalArgumentException e) {
            return new Response(e.getMessage(), null);
        }
        if (request.getLogin() == null || request.getLogin().isBlank()) {
            return new Response("Please, log in first.", null);
        }
        try {
            if (!collectionManager.renameTicket(rename.id(), rename.name(), request.getLogin())) {
                return new Response("ERROR: Ticket not found or access denied", null);
            }
            return new Response("Ticket renamed successfully", null);
        } catch (java.sql.SQLException e) {
            logger.error("Rename failed for ticket {}: {}", rename.id(), e.getMessage());
            return new Response("ERROR: Could not rename ticket in the database", null);
        }
    }
}
