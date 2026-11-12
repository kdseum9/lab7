package org.example.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RequestTest {

    @Test
    void requestCanBeCreatedWithoutTicket() {
        Request request = new Request("help", new String[0], null);
        assertNotNull(request);
    }
}