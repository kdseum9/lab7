package org.example.server.command;

public class FindByIdCommand extends AbstractCommand {

    public FindByIdCommand() {
        super("find_by_id", "найти билет по id");
    }

    @Override
    public Response execute(Request request) {
        if (request.getUser() == null) {
            return new Response("Сначала выполните вход");
        }
        long ticketId;
        try {
            ticketId = Long.parseLong(request.getArg());
        } catch (NumberFormatException e) {
            return new Response("Некорректный id: ожидается целое число");
        }
        return collectionManager.findById(ticketId)
                .map(ticket -> new Response(ticket.toString()))
                .orElseGet(() -> new Response("Билет с id " + ticketId + " не найден"));
    }
}
