package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.model.Ticket;
import org.example.server.manager.CollectionManager;

import java.util.LinkedHashSet;
import java.util.List;

public class ShowCommand extends AbstractCommand {

    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        LinkedHashSet<Ticket> collection = collectionManager.getCollection();

        if (collection.isEmpty()) {
            logger.info("Show command executed: collection is empty.");
            return new Response("The collection is empty.", null);
        }

        logger.info("Show command executed: {} tickets displayed.", collection.size());
        return new Response("Displaying all elements in the collection:", List.copyOf(collection));
    }
}
