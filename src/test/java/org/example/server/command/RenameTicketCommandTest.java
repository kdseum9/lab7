package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.model.Coordinates;
import org.example.common.model.Ticket;
import org.example.common.model.Venue;
import org.example.common.model.enums.TicketType;
import org.example.common.model.enums.VenueType;
import org.example.server.manager.CollectionManager;
import org.example.server.manager.DataBaseManager.RenameTicketResult;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RenameTicketCommandTest {

    @Test
    void renamesOwnedTicketAndPreservesEveryOtherField() {
        CollectionManager collectionManager = new CollectionManager();
        Ticket ticket = ticket(42L, "Old name", 7);
        collectionManager.add(ticket);

        Coordinates coordinates = ticket.getCoordinates();
        ZonedDateTime creationDate = ticket.getCreationDate();
        Venue venue = ticket.getVenue();
        int price = ticket.getPrice();
        Double discount = ticket.getDiscount();
        TicketType type = ticket.getType();
        int ownerId = ticket.getOwnerId();

        RenameTicketCommand command = new RenameTicketCommand((id, newName, login) -> {
            assertEquals(42L, id);
            assertEquals("New name", newName);
            assertEquals("alice", login);
            return RenameTicketResult.SUCCESS;
        });

        Response response = command.execute(request("42", "  New name  ", "alice"), collectionManager);

        assertEquals("Ticket renamed successfully.", response.getMessage());
        assertSame(ticket, response.getData());
        assertEquals("New name", ticket.getName());
        assertSame(coordinates, ticket.getCoordinates());
        assertEquals(creationDate, ticket.getCreationDate());
        assertEquals(price, ticket.getPrice());
        assertEquals(discount, ticket.getDiscount());
        assertEquals(type, ticket.getType());
        assertSame(venue, ticket.getVenue());
        assertEquals(ownerId, ticket.getOwnerId());
        assertEquals(1, collectionManager.getCollection().size());
        assertSame(ticket, collectionManager.getById(42L));
    }

    @Test
    void reportsMissingTicketWithoutChangingCollection() {
        CollectionManager collectionManager = new CollectionManager();
        Ticket ticket = ticket(42L, "Original", 7);
        collectionManager.add(ticket);
        RenameTicketCommand command = new RenameTicketCommand(
                (id, newName, login) -> RenameTicketResult.NOT_FOUND
        );

        Response response = command.execute(request("999", "New name", "alice"), collectionManager);

        assertTrue(response.getMessage().contains("was not found"));
        assertEquals("Original", ticket.getName());
    }

    @Test
    void rejectsRenamingAnotherUsersTicket() {
        CollectionManager collectionManager = new CollectionManager();
        Ticket ticket = ticket(42L, "Original", 7);
        collectionManager.add(ticket);
        RenameTicketCommand command = new RenameTicketCommand(
                (id, newName, login) -> RenameTicketResult.ACCESS_DENIED
        );

        Response response = command.execute(request("42", "Stolen name", "bob"), collectionManager);

        assertTrue(response.getMessage().contains("only your own tickets"));
        assertEquals("Original", ticket.getName());
    }

    @Test
    void rejectsBlankNameBeforeDatabaseCall() {
        AtomicBoolean called = new AtomicBoolean(false);
        RenameTicketCommand command = new RenameTicketCommand((id, newName, login) -> {
            called.set(true);
            return RenameTicketResult.SUCCESS;
        });

        Response response = command.execute(request("42", " \t ", "alice"), new CollectionManager());

        assertTrue(response.getMessage().contains("cannot be empty"));
        assertFalse(called.get());
    }

    @Test
    void rejectsInvalidIdBeforeDatabaseCall() {
        AtomicBoolean called = new AtomicBoolean(false);
        RenameTicketCommand command = new RenameTicketCommand((id, newName, login) -> {
            called.set(true);
            return RenameTicketResult.SUCCESS;
        });

        Response response = command.execute(request("not-a-number", "New name", "alice"), new CollectionManager());

        assertTrue(response.getMessage().contains("Invalid ticket ID"));
        assertFalse(called.get());
    }

    @Test
    void requiresAuthenticationBeforeDatabaseCall() {
        AtomicBoolean called = new AtomicBoolean(false);
        RenameTicketCommand command = new RenameTicketCommand((id, newName, login) -> {
            called.set(true);
            return RenameTicketResult.SUCCESS;
        });

        Response response = command.execute(request("42", "New name", ""), new CollectionManager());

        assertTrue(response.getMessage().contains("log in first"));
        assertFalse(called.get());
    }

    private static Request request(String id, String newName, String login) {
        Request request = new Request("rename_ticket", new String[]{id, newName}, null);
        request.setLogin(login);
        return request;
    }

    private static Ticket ticket(long id, String name, int ownerId) {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setName(name);
        ticket.setCoordinates(new Coordinates(12.5f, 15L));
        ticket.setCreationDate(ZonedDateTime.parse("2025-01-02T03:04:05Z"));
        ticket.setPrice(1500);
        ticket.setDiscount(25.0);
        ticket.setType(TicketType.VIP);
        ticket.setVenue(new Venue(9L, "Main hall", 500, VenueType.THEATRE));
        ticket.setOwnerId(ownerId);
        return ticket;
    }
}
