package org.example.server.manager;

import org.example.common.model.Ticket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class CollectionManagerTest {

    @Test
    void updateReplacesExistingTicketById() {
        CollectionManager manager = new CollectionManager();
        Ticket original = new Ticket();
        original.setId(15L);
        original.setName("Original");
        manager.add(original);

        Ticket replacement = new Ticket();
        replacement.setId(15L);
        replacement.setName("Replacement");
        manager.update(replacement);

        assertEquals(1, manager.getCollection().size());
        assertSame(replacement, manager.getById(15L));
    }
}
