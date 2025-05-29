package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.model.Ticket;
import org.example.server.manager.CollectionManager;

import java.util.Iterator;

public class RemoveLowerCommand extends AbstractCommand {

    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        Ticket referenceTicket = request.getTicket();

        if (referenceTicket == null) {
            logger.warn("Reference ticket was not provided in the request.");
            return new Response("ERROR: Reference ticket was not provided.", null);
        }

        int removedCount = 0;
        Iterator<Ticket> iterator = collectionManager.getCollection().iterator();

        while (iterator.hasNext()) {
            Ticket currentTicket = iterator.next();
            if (referenceTicket.compareTo(currentTicket) > 0) {
                iterator.remove();
                removedCount++;
                logger.info("Removed ticket: {}", currentTicket);
            }
        }

        String resultMessage;
        if (removedCount == 0) {
            resultMessage = "No tickets were lower than the reference ticket.";
            logger.info("No tickets removed.");
        } else {
            resultMessage = "Total tickets removed: " + removedCount;
            logger.info("Removed {} ticket(s).", removedCount);
        }

        return new Response(resultMessage, null);
    }
}
