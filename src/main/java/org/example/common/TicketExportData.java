package org.example.common;

import org.example.common.model.Ticket;

import java.io.Serializable;
import java.util.List;

/** Data prepared by the server for a client-side ticket export. */
public final class TicketExportData implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String login;
    private final List<Ticket> tickets;

    public TicketExportData(String login, List<Ticket> tickets) {
        this.login = login;
        this.tickets = List.copyOf(tickets);
    }

    public String getLogin() {
        return login;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
