package org.example.server.command;

import java.sql.SQLException;

public class UpdatePriceCommand extends AbstractCommand {

    public UpdatePriceCommand() {
        super("update_price", "изменить цену билета");
    }

    @Override
    public Response execute(Request request) {
        long ticketId = Long.parseLong(request.getArg());
        long newPrice = request.getPrice();
        try {
            dataBaseManager.updatePrice(ticketId, newPrice, request.getUser());
            collectionManager.updatePrice(ticketId, newPrice);
            return new Response("Цена обновлена");
        } catch (SQLException e) {
            return new Response("Ошибка БД: " + e.getMessage());
        }
    }
}
