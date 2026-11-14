package org.example.server.command;

import java.sql.SQLException;

public class RemoveTicketCommand extends AbstractCommand {

    public RemoveTicketCommand() {
        super("remove_ticket", "удалить билет по id");
    }

    @Override
    public Response execute(Request request) {
        long ticketId = Long.parseLong(request.getArg());
        String ownerId = request.getOwnerId();
        try {
            dataBaseManager.deleteTicket(ticketId);
            collectionManager.removeById(ticketId);
            return new Response("Билет " + ticketId + " удалён пользователем " + ownerId);
        } catch (SQLException e) {
            return new Response("Ошибка БД: " + e.getMessage());
        }
    }
}
