package org.example.common.Factory;


import org.example.common.model.AbstractTicket;

public interface Factory {
    AbstractTicket createTicket() throws Exception;
}

