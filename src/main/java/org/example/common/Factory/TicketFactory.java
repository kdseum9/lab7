package org.example.common.Factory;

import org.example.client.TicketInput;
import org.example.common.model.*;

/**
 * Создаёт билет с помощью ввода пользователя из консоли.
 */
public class TicketFactory implements Factory {
    @Override
    public AbstractTicket createTicket() {
        return TicketInput.generateTicket();
        }
    }




