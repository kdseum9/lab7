package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.exceptions.NoElementException;
import org.example.common.exceptions.WrongArgumentException;
import org.example.server.manager.CollectionManager;
import org.example.server.manager.DataBaseManager;
import org.example.server.manager.TicketValidator;

public class RenameTicketCommand extends AbstractCommand {
    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        try {
            String[] args = request.getArgs();
            if (args == null || args.length != 2) {
                throw new WrongArgumentException("Ожидаются ID билета и новое название.");
            }
            long id = TicketValidator.positiveId(args[0]);
            String name = TicketValidator.normalizedName(args[1]);
            if (!DataBaseManager.checkUser(request.getLogin(), request.getPassword())) {
                return new Response("Ошибка: требуется авторизация.", null);
            }
            int ownerId = DataBaseManager.getUserId(request.getLogin());
            if (ownerId <= 0) return new Response("Ошибка: не удалось определить пользователя.", null);
            if (!collectionManager.renameTicket(id, ownerId, name)) {
                return new Response("Ошибка сохранения в БД: билет не изменён.", null);
            }
            return new Response("Название билета " + id + " изменено: " + name, null);
        } catch (WrongArgumentException | NoElementException | SecurityException e) {
            return new Response("Ошибка: " + e.getMessage(), null);
        }
    }
}
