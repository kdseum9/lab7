package org.example.server.manager;

import org.example.common.Request;
import org.example.common.exceptions.NoElementException;
import org.example.common.exceptions.WrongArgumentException;
import org.example.common.model.Ticket;
import org.example.server.command.RenameTicketCommand;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class RenameTicketTest {
    @Test
    void validatesIdAndPreservesNameContents() throws Exception {
        for (String id : new String[]{null, "", "abc", "1.2", "0", "-12", "9223372036854775808"}) {
            assertThrows(WrongArgumentException.class, () -> TicketValidator.positiveId(id));
        }
        assertEquals(Long.MAX_VALUE, TicketValidator.positiveId("9223372036854775807"));
        for (String name : new String[]{null, "", " \t\n", "\u2003\u00a0"}) {
            assertThrows(WrongArgumentException.class, () -> TicketValidator.normalizedName(name));
        }
        assertEquals("Концерт  'оркестра' \"!\"", TicketValidator.normalizedName("\u2003 Концерт  'оркестра' \"!\" \t"));
    }

    @Test
    void rejectsMissingArgumentsAndUnauthenticatedRequests() {
        CollectionManager manager = new CollectionManager();
        RenameTicketCommand command = new RenameTicketCommand();
        for (String[] args : new String[][]{null, {}, {"12"}, {"12", ""}, {"12", "name", "extra"}}) {
            assertTrue(command.execute(new Request("rename_ticket", args, null), manager).getMessage().startsWith("Ошибка"));
        }
        assertTrue(command.execute(new Request("rename_ticket", new String[]{"12", "name"}, null), manager)
                .getMessage().contains("авторизация"));
    }

    @Test
    void savesBeforeChangingCollectionAndMaintainsSetIntegrity() throws Exception {
        // Substitute JDBC at the driver boundary; never connect to or modify a real database.
        Driver driver = new Driver() {
            public Connection connect(String url, Properties info) {
                if (!acceptsURL(url)) return null;
                return proxy(Connection.class, (method, args) -> {
                    if (method.equals("prepareStatement")) {
                        assertEquals("UPDATE ticket SET name = ? WHERE id = ? AND owner_id = ?", args[0]);
                        return proxy(PreparedStatement.class, (m, a) -> {
                            if (m.equals("executeUpdate")) return rows;
                            return null;
                        });
                    }
                    return null;
                });
            }
            public boolean acceptsURL(String url) { return url.startsWith("jdbc:postgresql:"); }
            public DriverPropertyInfo[] getPropertyInfo(String u, Properties p) { return new DriverPropertyInfo[0]; }
            public int getMajorVersion() { return 1; }
            public int getMinorVersion() { return 0; }
            public boolean jdbcCompliant() { return false; }
            public Logger getParentLogger() { return Logger.getGlobal(); }
        };
        // Ensure our driver is registered before PostgreSQL's service-loaded driver.
        DriverManager.registerDriver(driver);
        try {
            CollectionManager manager = new CollectionManager();
            Ticket ticket = new Ticket();
            ticket.setId(12); ticket.setOwnerId(7); ticket.setName("old"); ticket.setPrice(42);
            var date = ticket.getCreationDate();
            manager.add(ticket);
            assertThrows(NoElementException.class, () -> manager.renameTicket(13, 7, "new"));
            assertThrows(SecurityException.class, () -> manager.renameTicket(12, 8, "new"));
            rows = 0;
            assertFalse(manager.renameTicket(12, 7, "new"));
            assertEquals("old", ticket.getName());
            rows = 1;
            assertTrue(manager.renameTicket(12, 7, "new"));
            assertTrue(manager.renameTicket(12, 7, "new"));
            assertEquals("new", manager.getById(12).getName());
            assertEquals(7, ticket.getOwnerId());
            assertEquals(42, ticket.getPrice());
            assertEquals(date, ticket.getCreationDate());
            assertEquals(1, manager.getCollection().size());
            assertTrue(manager.removeById(12));
            assertTrue(manager.getCollection().isEmpty());
        } finally {
            DriverManager.deregisterDriver(driver);
        }
    }

    private static int rows;
    private interface Call { Object invoke(String method, Object[] args) throws Exception; }
    private static <T> T proxy(Class<T> type, Call call) {
        return type.cast(Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[]{type},
                (p, method, args) -> call.invoke(method.getName(), args)));
    }
}
