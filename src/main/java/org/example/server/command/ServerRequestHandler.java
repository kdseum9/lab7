package org.example.server.command;

public class ServerRequestHandler {

    private final CollectionManager collectionManager;

    public ServerRequestHandler(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response handle(Request request) {
        if ("count_by_venue".equals(request.getCommandName())) {
            long count = collectionManager.getAll().stream()
                    .filter(t -> t.getVenue() != null && t.getVenue().getName().equals(request.getArg()))
                    .count();
            return new Response("Билетов с площадкой " + request.getArg() + ": " + count);
        }
        return new Response("Неизвестная команда");
    }
}
