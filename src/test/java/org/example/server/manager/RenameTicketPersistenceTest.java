package org.example.server.manager;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class RenameTicketPersistenceTest {
    /** JDBC double checks the actual persistence method without a running database. */
    @Test
    void usesParameterizedNameOnlyUpdateAndLongIdWithOwnerCondition() throws Exception {
        withDriver(1, false, (sql, bindings) -> {
            assertTrue(DataBaseManager.renameTicketById(Long.MAX_VALUE, "Концерт \"Осень\"", "owner"));
            assertEquals("UPDATE ticket SET name = ? WHERE id = ? AND owner_id = "
                    + "(SELECT user_id FROM lab7_users WHERE login = ?)", sql[0]);
            assertEquals(Map.of(1, "Концерт \"Осень\"", 2, Long.MAX_VALUE, 3, "owner"), bindings);
        });
    }

    @Test
    void noMatchingOwnerOrTicketReturnsFalse() throws Exception {
        withDriver(0, false, (sql, bindings) ->
                assertFalse(DataBaseManager.renameTicketById(12, "Name", "other")));
    }

    @Test
    void sqlFailurePropagatesSoCollectionCanRemainUnchanged() throws Exception {
        withDriver(0, true, (sql, bindings) ->
                assertThrows(SQLException.class, () -> DataBaseManager.renameTicketById(12, "Name", "owner")));
    }

    @FunctionalInterface
    private interface Check {
        void run(String[] sql, Map<Integer, Object> bindings) throws Exception;
    }

    private void withDriver(int rows, boolean fail, Check check) throws Exception {
        String[] sql = {null};
        Map<Integer, Object> bindings = new HashMap<>();
        PreparedStatement statement = (PreparedStatement) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{PreparedStatement.class}, (proxy, method, args) -> {
                    switch (method.getName()) {
                        case "setString", "setLong": bindings.put((Integer) args[0], args[1]); return null;
                        case "executeUpdate": if (fail) throw new SQLException("Write failed"); return rows;
                        case "close": return null;
                        default: throw new UnsupportedOperationException(method.getName());
                    }
                });
        Connection connection = (Connection) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{Connection.class}, (proxy, method, args) -> {
                    switch (method.getName()) {
                        case "prepareStatement": sql[0] = (String) args[0]; return statement;
                        case "close": return null;
                        default: throw new UnsupportedOperationException(method.getName());
                    }
                });
        // Register before loading the PostgreSQL driver; no real connection is made.
        List<Driver> previous = Collections.list(DriverManager.getDrivers());
        for (Driver driver : previous) DriverManager.deregisterDriver(driver);
        Driver driver = new Driver() {
            public Connection connect(String url, Properties properties) { return connection; }
            public boolean acceptsURL(String url) { return true; }
            public DriverPropertyInfo[] getPropertyInfo(String url, Properties properties) { return new DriverPropertyInfo[0]; }
            public int getMajorVersion() { return 1; }
            public int getMinorVersion() { return 0; }
            public boolean jdbcCompliant() { return false; }
            public Logger getParentLogger() { return Logger.getGlobal(); }
        };
        DriverManager.registerDriver(driver);
        try {
            check.run(sql, bindings);
        } finally {
            DriverManager.deregisterDriver(driver);
            for (Driver saved : previous) DriverManager.registerDriver(saved);
        }
    }
}
