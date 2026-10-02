package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.TicketExportData;
import org.example.common.exceptions.WrongArgumentException;
import org.example.common.model.Ticket;
import org.example.server.manager.CollectionManager;
import org.example.server.manager.DataBaseManager;

import java.util.List;
import java.util.function.ToIntFunction;

/** Prepares the authenticated user's tickets for writing on the client. */
public class ExportMyTicketsCommand extends AbstractCommand {
    private final ToIntFunction<String> userIdResolver;

    public ExportMyTicketsCommand() {
        this(DataBaseManager::getUserId);
    }

    ExportMyTicketsCommand(ToIntFunction<String> userIdResolver) {
        this.userIdResolver = userIdResolver;
    }

    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        try {
            validateRequest(request);
        } catch (WrongArgumentException e) {
            return new Response(e.getMessage(), null);
        }

        String login = request.getLogin();
        int ownerId = userIdResolver.applyAsInt(login);
        if (ownerId < 0) {
            return new Response("Unable to identify the authenticated user.", null);
        }

        List<Ticket> tickets = collectionManager.getTicketsByOwnerId(ownerId);
        logger.info("Prepared {} tickets for export by user {}.", tickets.size(), login);
        return new Response("Tickets are ready for client-side export.",
                new TicketExportData(login, tickets));
    }

    private void validateRequest(Request request) throws WrongArgumentException {
        if (request == null || request.getLogin() == null || request.getLogin().isBlank()) {
            throw new WrongArgumentException("authorization is required");
        }
        if (request.getArgs() == null || request.getArgs().length != 0) {
            throw new WrongArgumentException("export_my_tickets does not accept a login, owner ID, or other arguments");
        }
    }
}
