package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.model.Ticket;
import org.example.server.manager.CollectionManager;
import org.example.server.manager.DataBaseManager;

import java.sql.SQLException;
import java.util.ArrayList;

/** Renames an owned ticket only after its new name has been saved. */
public class RenameTicketCommand extends AbstractCommand {
    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        String[] args = request.getArgs();
        if (args == null || args.length == 0 || args[0] == null || args[0].isBlank()) {
            return new Response("ERROR: ID билета отсутствует", null);
        }
        long id;
        try {
            id = Long.parseLong(args[0].strip());
            if (id <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            return new Response("ERROR: ID должен быть положительным целым числом в пределах long", null);
        }
        if (args.length < 2 || args[1] == null) {
            return new Response("ERROR: Новое название отсутствует", null);
        }
        String name = stripWhitespace(args[1]);
        if (name.isEmpty()) {
            return new Response("ERROR: Название не должно быть пустым или состоять из пробельных символов", null);
        }
        if (!authenticate(request)) {
            return new Response("ERROR: Требуется авторизация", null);
        }
        int ownerId = userId(request.getLogin());
        if (ownerId <= 0) {
            return new Response("ERROR: Не удалось определить пользователя", null);
        }
        return collectionManager.withLockedCollection(collection -> {
            Ticket ticket = collection.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
            if (ticket == null) return new Response("ERROR: Билет не найден", null);
            if (ticket.getOwnerId() != ownerId) {
                return new Response("ERROR: Можно переименовать только свой билет", null);
            }
            try {
                if (!persist(id, ownerId, name)) {
                    return new Response("ERROR: Билет не найден или доступ запрещён", null);
                }
            } catch (SQLException e) {
                logger.error("Failed to rename ticket {}", id, e);
                return new Response("ERROR: Не удалось сохранить название в БД", null);
            }
            // Name participates in hashCode: rebuild the set without changing its order.
            ArrayList<Ticket> tickets = new ArrayList<>(collection);
            collection.clear();
            ticket.setName(name);
            collection.addAll(tickets);
            return new Response("Название билета успешно изменено", ticket);
        });
    }

    private static String stripWhitespace(String value) {
        int start = 0;
        int end = value.length();
        while (start < end && isWhitespace(value.codePointAt(start))) {
            start += Character.charCount(value.codePointAt(start));
        }
        while (start < end && isWhitespace(value.codePointBefore(end))) {
            end -= Character.charCount(value.codePointBefore(end));
        }
        return value.substring(start, end);
    }

    private static boolean isWhitespace(int codePoint) {
        return Character.isWhitespace(codePoint) || Character.isSpaceChar(codePoint);
    }

    protected boolean authenticate(Request request) {
        return DataBaseManager.checkUser(request.getLogin(), request.getPassword());
    }

    protected int userId(String login) {
        return DataBaseManager.getUserId(login);
    }

    protected boolean persist(long id, int ownerId, String name) throws SQLException {
        return DataBaseManager.renameTicket(id, ownerId, name);
    }
}
