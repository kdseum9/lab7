package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.model.Ticket;
import org.example.server.manager.CollectionManager;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Команда для вывода значений поля discount всех объектов Ticket в порядке убывания.
 */
public class PrintFieldDescendingDiscountCommand extends AbstractCommand {

    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        LinkedHashSet<Ticket> collection = collectionManager.getCollection();

        if (collection.isEmpty()) {
            logger.warn("Attempted to print discounts, but the collection is empty.");
            return new Response("The collection is empty.", null);
        }

        List<Double> discounts = collection.stream()
                .map(Ticket::getDiscount)
                .filter(discount -> discount != null && discount > 0)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        if (discounts.isEmpty()) {
            logger.info("No valid discounts found to display.");
            return new Response("No valid discount values found in the collection.", null);
        }

        StringBuilder result = new StringBuilder("Discount values (descending):\n");
        discounts.forEach(d -> result.append(String.format("- %.2f%%\n", d)));

        logger.info("Printed {} discount values in descending order.", discounts.size());
        return new Response(result.toString(), null);
    }
}
