package org.example.common.Factory;


import org.example.client.TicketInput;
import org.example.common.model.AbstractTicket;

import java.io.BufferedReader;
import java.io.IOException;

public class ScriptTicketFactory implements Factory {
    private final BufferedReader reader;

    public ScriptTicketFactory(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public AbstractTicket createTicket() throws IOException {
        return TicketInput.generateTicketFromScript(reader);
    }
}

