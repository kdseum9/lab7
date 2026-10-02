package org.example.server;

import org.example.common.Request;
import org.example.common.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerAuthorizationTest {

    @Test
    void exportIsRejectedWithoutAuthorization() {
        Server server = new Server();
        Request request = new Request("export_my_tickets", new String[0], null);

        Response response = server.processRequest(request, null);

        assertEquals("Please, log in first.", response.getMessage());
        assertNull(response.getData());
    }
}
