package org.example.server.command;

import java.sql.SQLException;
import java.util.List;

public class ShowMyTicketsCommand extends AbstractCommand {

    public ShowMyTicketsCommand() {
        super("show_my", "показать id моих билетов");
    }

    @Override
    public Response execute(Request request) {
        if (request.getUser() == null) {
            return new Response("Сначала выполните вход");
        }
        try {
            List<Long> ids = dataBaseManager.findTicketIdsByOwner(request.getUser());
            return new Response("Ваши билеты: " + ids);
        } catch (SQLException e) {
            return new Response("Ошибка БД: " + e.getMessage());
        }
    }
}
