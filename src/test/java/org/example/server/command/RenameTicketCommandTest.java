package org.example.server.command;

import org.example.common.Request;
import org.example.common.model.Ticket;
import org.example.common.model.Coordinates;
import org.example.common.model.Venue;
import org.example.common.model.enums.TicketType;
import org.example.common.model.enums.VenueType;
import org.example.server.manager.CollectionManager;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RenameTicketCommandTest {
    static class StubCommand extends RenameTicketCommand {
        boolean authorized = true;
        boolean saved = true;
        boolean fail;
        int writes;
        long savedId;
        String savedName;
        protected boolean authenticate(Request request) { return authorized; }
        protected int userId(String login) { return 7; }
        protected boolean persist(long id, int owner, String name) throws SQLException {
            writes++;
            savedId = id;
            savedName = name;
            if (fail) throw new SQLException("test failure");
            return saved;
        }
    }

    private Ticket ticket(CollectionManager manager) {
        Ticket ticket = new Ticket();
        ticket.setId(Long.MAX_VALUE);
        ticket.setOwnerId(7);
        ticket.setName("Old");
        ticket.setPrice(100);
        ticket.setCoordinates(new Coordinates(1.0f, 2L));
        ticket.setDiscount(10.0);
        ticket.setType(TicketType.values()[0]);
        ticket.setVenue(new Venue(42, "Большой зал", 500, VenueType.values()[0]));
        manager.add(ticket);
        return ticket;
    }

    private Request request(String... args) {
        return new Request("rename_ticket", args, null, "owner", "password");
    }

    private void assertUnchanged(CollectionManager manager, Ticket ticket, String before, int owner) {
        assertEquals(before, ticket.toString());
        assertEquals(owner, ticket.getOwnerId());
        assertEquals(1, manager.getCollection().size());
        assertSame(ticket, manager.getById(ticket.getId()));
        assertTrue(manager.getCollection().contains(ticket));
    }

    @Test
    void renamesOnlyNameAndKeepsSetUsableOnRepeatedRename() {
        CollectionManager manager = new CollectionManager();
        Ticket ticket = ticket(manager);
        var created = ticket.getCreationDate();
        var coordinates = ticket.getCoordinates();
        var venue = ticket.getVenue();
        var type = ticket.getType();
        String venueBefore = venue.toString();
        StubCommand command = new StubCommand();
        String name = "Концерт  \"оркестра\" !";
        for (int i = 0; i < 2; i++) {
            assertSame(ticket, command.execute(request("9223372036854775807", "\u00a0\t " + name + " \u2003"), manager).getData());
            assertEquals(name, ticket.getName());
            assertEquals(1, manager.getCollection().size());
            assertEquals(Long.MAX_VALUE, command.savedId);
            assertEquals(Long.MAX_VALUE, ticket.getId());
            assertEquals(name, command.savedName);
            assertEquals(7, ticket.getOwnerId());
            assertEquals(100, ticket.getPrice());
            assertEquals(created, ticket.getCreationDate());
            assertSame(coordinates, ticket.getCoordinates());
            assertEquals(1.0f, ticket.getCoordinates().getX());
            assertEquals(2L, ticket.getCoordinates().getY());
            assertSame(venue, ticket.getVenue());
            assertEquals(venueBefore, ticket.getVenue().toString());
            assertEquals(type, ticket.getType());
            assertEquals(10.0, ticket.getDiscount());
        }
        manager.add(ticket);
        assertEquals(1, manager.getCollection().size());
        manager.delete(ticket);
        assertTrue(manager.getCollection().isEmpty());
    }

    @Test
    void invalidInputDoesNotWrite() {
        CollectionManager manager = new CollectionManager();
        Ticket ticket = ticket(manager);
        StubCommand command = new StubCommand();
        String before = ticket.toString();
        for (String id : new String[]{null, "", "  ", "abc", "1.2", "0", "-1", "9223372036854775808", "-9223372036854775809"}) {
            assertTrue(command.execute(request(id, "New"), manager).getMessage().startsWith("ERROR:"));
            assertUnchanged(manager, ticket, before, 7);
        }
        for (String name : new String[]{null, "", " \t\u00a0\u2003"}) {
            assertTrue(command.execute(request(String.valueOf(ticket.getId()), name), manager).getMessage().startsWith("ERROR:"));
            assertUnchanged(manager, ticket, before, 7);
        }
        assertTrue(command.execute(request(), manager).getMessage().startsWith("ERROR:"));
        assertTrue(command.execute(request(String.valueOf(ticket.getId())), manager).getMessage().startsWith("ERROR:"));
        assertTrue(command.execute(request((String[]) null), manager).getMessage().startsWith("ERROR:"));
        assertUnchanged(manager, ticket, before, 7);
        assertEquals(0, command.writes);
        assertEquals("Old", ticket.getName());
    }

    @Test
    void rejectsUnauthorizedMissingAndForeignTickets() {
        CollectionManager manager = new CollectionManager();
        Ticket ticket = ticket(manager);
        StubCommand command = new StubCommand();
        String before = ticket.toString();
        command.authorized = false;
        assertTrue(command.execute(request(String.valueOf(ticket.getId()), "New"), manager).getMessage().startsWith("ERROR:"));
        assertUnchanged(manager, ticket, before, 7);
        command.authorized = true;
        assertTrue(command.execute(request("1", "New"), manager).getMessage().startsWith("ERROR:"));
        assertUnchanged(manager, ticket, before, 7);
        ticket.setOwnerId(8);
        assertTrue(command.execute(request(String.valueOf(ticket.getId()), "New"), manager).getMessage().startsWith("ERROR:"));
        assertUnchanged(manager, ticket, before, 8);
        assertEquals(0, command.writes);
        assertEquals("Old", ticket.getName());
    }

    @Test
    void databaseFailureDoesNotChangeCollection() {
        CollectionManager manager = new CollectionManager();
        Ticket ticket = ticket(manager);
        StubCommand command = new StubCommand();
        String before = ticket.toString();
        command.saved = false;
        assertTrue(command.execute(request(String.valueOf(ticket.getId()), "New"), manager).getMessage().startsWith("ERROR:"));
        assertUnchanged(manager, ticket, before, 7);
        command.fail = true;
        assertTrue(command.execute(request(String.valueOf(ticket.getId()), "New"), manager).getMessage().startsWith("ERROR:"));
        assertUnchanged(manager, ticket, before, 7);
        assertEquals("Old", ticket.getName());
        manager.delete(ticket);
        assertTrue(manager.getCollection().isEmpty());
    }
}
