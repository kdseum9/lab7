package org.example.client;

import org.example.common.model.*;
import org.example.common.model.enums.TicketType;
import org.example.common.model.enums.VenueType;

import java.time.ZonedDateTime;

public class TicketFactory {

    public static Ticket createTicket(String name, Coordinates coordinates, int price, Double discount, TicketType type, Venue venue) {
        Ticket ticket = new Ticket();
        ticket.setName(name);
        ticket.setCoordinates(coordinates);
        ticket.setPrice(price);
        ticket.setDiscount(discount);
        ticket.setType(type);
        ticket.setVenue(venue);
        ticket.setCreationDate(ZonedDateTime.now());
        return ticket;
    }
}

