package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.TicketExportData;
import org.example.common.model.Coordinates;
import org.example.common.model.Ticket;
import org.example.common.model.Venue;
import org.example.common.model.enums.TicketType;
import org.example.common.model.enums.VenueType;
import org.example.server.manager.CollectionManager;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExportMyTicketsCommandTest {

    @Test
    void exportsOnlyCurrentUsersTicketsSortedByIdWithoutChangingCollection() {
        CollectionManager manager = new CollectionManager();
        Ticket ownLater = ticket(30, 7, "later");
        Ticket foreign = ticket(10, 8, "foreign");
        Ticket ownEarlier = ticket(20, 7, "earlier");
        manager.add(ownLater);
        manager.add(foreign);
        manager.add(ownEarlier);
        LinkedHashSet<Ticket> before = manager.getCollection();

        Request request = new Request("export_my_tickets", new String[0], null);
        request.setLogin("alice");
        Response response = new ExportMyTicketsCommand(login -> 7).execute(request, manager);

        assertInstanceOf(TicketExportData.class, response.getData());
        TicketExportData data = (TicketExportData) response.getData();
        assertEquals("alice", data.getLogin());
        assertEquals(List.of(20L, 30L), data.getTickets().stream().map(Ticket::getId).toList());
        assertFalse(data.getTickets().contains(foreign));
        assertEquals(before, manager.getCollection());
        assertSame(ownLater, manager.getById(30));
        assertSame(ownEarlier, manager.getById(20));
    }

    @Test
    void emptyResultIsSuccessful() {
        CollectionManager manager = new CollectionManager();
        manager.add(ticket(1, 99, "someone else's"));
        Request request = new Request("export_my_tickets", new String[0], null);
        request.setLogin("alice");

        Response response = new ExportMyTicketsCommand(login -> 7).execute(request, manager);

        TicketExportData data = assertInstanceOf(TicketExportData.class, response.getData());
        assertTrue(data.getTickets().isEmpty());
    }

    @Test
    void rejectsOwnerOrLoginArguments() {
        Request request = new Request("export_my_tickets", new String[]{"other-user", "42"}, null);
        request.setLogin("alice");

        Response response = new ExportMyTicketsCommand(login -> 7)
                .execute(request, new CollectionManager());

        assertNull(response.getData());
        assertTrue(response.getMessage().contains("does not accept"));
    }

    private static Ticket ticket(long id, int ownerId, String name) {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setOwnerId(ownerId);
        ticket.setName(name);
        ticket.setCoordinates(new Coordinates(1.5f, 2L));
        ticket.setCreationDate(ZonedDateTime.parse("2025-01-02T03:04:05+03:00"));
        ticket.setPrice(100);
        ticket.setDiscount(10.0);
        ticket.setType(TicketType.VIP);
        ticket.setVenue(new Venue(5, "Hall", 200, VenueType.THEATRE));
        return ticket;
    }
}
