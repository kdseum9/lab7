package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.model.Coordinates;
import org.example.common.model.Ticket;
import org.example.common.model.Venue;
import org.example.common.model.enums.TicketType;
import org.example.server.manager.CollectionManager;
import org.example.server.manager.CommandManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class RenameTicketCommandTest {
    private Ticket ticket() {
        Ticket ticket = new Ticket();
        ticket.setId(12);
        ticket.setOwnerId(7);
        ticket.setName("Old name");
        ticket.setCoordinates(new Coordinates(2f, 3L));
        ticket.setPrice(150);
        ticket.setDiscount(10.0);
        ticket.setType(TicketType.values()[0]);
        ticket.setVenue(new Venue());
        return ticket;
    }

    private Response execute(CollectionManager manager, String id, String name, String login) {
        return new CommandManager(manager).doCommand(
                new Request("rename_ticket", new String[]{id, name}, null, login, "password"), manager);
    }

    private void assertUnchangedFields(Ticket ticket, Ticket original) {
        assertEquals(original.getId(), ticket.getId());
        assertEquals(original.getOwnerId(), ticket.getOwnerId());
        assertEquals(original.getPrice(), ticket.getPrice());
        assertSame(original.getCoordinates(), ticket.getCoordinates());
        assertEquals(original.getCreationDate(), ticket.getCreationDate());
        assertEquals(original.getDiscount(), ticket.getDiscount());
        assertEquals(original.getType(), ticket.getType());
        assertSame(original.getVenue(), ticket.getVenue());
    }

    @Test
    void renamesOnlyNameAndRepeatedRenameDoesNotDuplicateOrBreakSet() {
        Ticket ticket = ticket();
        Ticket original = ticket();
        original.setCoordinates(ticket.getCoordinates());
        original.setCreationDate(ticket.getCreationDate());
        original.setVenue(ticket.getVenue());
        String[] storedName = {ticket.getName()};
        CollectionManager manager = new CollectionManager((id, name, login) -> {
            assertEquals(12, id);
            assertEquals("owner", login);
            assertEquals(storedName[0], ticket.getName(), "memory changes only after persistence");
            storedName[0] = name;
            return true;
        });
        Ticket other = ticket();
        other.setId(13);
        manager.add(ticket);
        manager.add(other);
        String name = "Концерт  симфонического оркестра \"Осень\" '2026'";
        for (int i = 0; i < 2; i++) {
            assertEquals("Ticket renamed successfully", execute(manager, "12", "\t\u00a0" + name + "\u2003 ", "owner").getMessage());
            assertEquals(name, ticket.getName());
            assertEquals(name, storedName[0]);
            assertUnchangedFields(ticket, original);
            LinkedHashSet<Ticket> collection = manager.getCollection();
            assertEquals(2, collection.size());
            assertEquals(1, collection.stream().filter(t -> t.getId() == 12).count());
            assertTrue(collection.contains(ticket));
            assertSame(ticket, collection.iterator().next());
            assertEquals("Old name", other.getName());
        }
        manager.delete(ticket);
        assertEquals(1, manager.getCollection().size());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t\n", "\u00a0\u2003"})
    void rejectsBlankOrMissingNameWithoutPersistence(String name) {
        assertInvalid("12", name);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "abc", "1.5", "12 extra", "0", "-1", "9223372036854775808", "-9223372036854775809"})
    void rejectsInvalidIdWithoutPersistence(String id) {
        assertInvalid(id, "New name");
    }

    private void assertInvalid(String id, String name) {
        AtomicInteger writes = new AtomicInteger();
        CollectionManager manager = new CollectionManager((key, value, login) -> {
            writes.incrementAndGet();
            return true;
        });
        Ticket ticket = ticket();
        manager.add(ticket);
        String before = ticket.toString();
        assertTrue(execute(manager, id, name, "owner").getMessage().startsWith("ERROR:"));
        assertEquals(0, writes.get());
        assertEquals("Old name", ticket.getName());
        assertEquals(before, ticket.toString());
        assertEquals(7, ticket.getOwnerId());
        assertEquals(1, manager.getCollection().size());
        assertTrue(manager.getCollection().contains(ticket));
    }

    @Test
    void preservesFullLongId() {
        CollectionManager manager = new CollectionManager((id, name, login) -> {
            assertEquals(Long.MAX_VALUE, id);
            return false;
        });
        assertTrue(execute(manager, Long.toString(Long.MAX_VALUE), "Name", "owner").getMessage().contains("not found"));
    }

    @Test
    void rejectsMissingAndForeignTicketsWithoutChanges() {
        CollectionManager manager = new CollectionManager((id, name, login) -> id == 12 && "owner".equals(login));
        Ticket ticket = ticket();
        manager.add(ticket);
        String before = ticket.toString();
        assertTrue(execute(manager, "99", "New", "owner").getMessage().contains("access denied"));
        assertTrue(execute(manager, "12", "New", "other").getMessage().contains("access denied"));
        assertEquals("Old name", ticket.getName());
        assertEquals(before, ticket.toString());
        assertEquals(7, ticket.getOwnerId());
        assertEquals(1, manager.getCollection().size());
        assertTrue(manager.getCollection().contains(ticket));
    }

    @Test
    void storageFailureDoesNotChangeMemory() {
        CollectionManager manager = new CollectionManager((id, name, login) -> { throw new SQLException("offline"); });
        Ticket ticket = ticket();
        manager.add(ticket);
        String before = ticket.toString();
        assertTrue(execute(manager, "12", "New", "owner").getMessage().startsWith("ERROR:"));
        assertEquals("Old name", ticket.getName());
        assertEquals(before, ticket.toString());
        assertEquals(7, ticket.getOwnerId());
        assertEquals(1, manager.getCollection().size());
        assertTrue(manager.getCollection().contains(ticket));
    }

    @Test
    void rejectsMissingLoginAndMalformedRequestsWithoutPersistence() {
        CollectionManager manager = new CollectionManager((id, name, login) -> { fail("Unexpected persistence"); return false; });
        assertEquals("Please, log in first.", execute(manager, "12", "New", null).getMessage());
        for (String[] args : new String[][]{null, {}, {"12"}, {"12", "Name", "extra"}}) {
            Response response = new RenameTicketCommand().execute(new Request("rename_ticket", args, null), manager);
            assertTrue(response.getMessage().startsWith("ERROR:"));
        }
    }
}
