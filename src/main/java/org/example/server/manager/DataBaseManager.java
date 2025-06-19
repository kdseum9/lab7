package org.example.server.manager;

import java.sql.*;
import java.time.ZoneId;

import org.example.common.model.Coordinates;
import org.example.common.model.Ticket;
import org.example.common.model.Venue;
import org.example.common.model.enums.TicketType;
import org.example.common.model.enums.VenueType;
import org.example.server.handlers.HashHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DataBaseManager {
    private static DataBaseManager instance;
    private static final String GET_USERS = "SELECT * FROM lab7_users";
    private static final Logger logger = LoggerFactory.getLogger(DataBaseManager.class);

    private static final String DB_URL = "jdbc:postgresql://pg:5432/studs";
    private static final String DB_USER = "s465729";
    private static final String DB_PASSWORD = "TMnULcCn63BZLOCt";

    //private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    //private static final String DB_USER = "postgres";
    //private static final String DB_PASSWORD = "kdseum";

    private static final String INSERT_TICKET = "INSERT INTO ticket (name, coordinates_x, coordinates_y, creation_date, price, discount, tickettype, venuename, capacity, venuetype, owner_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

    private static final String GET_OWNER_BY_KEY = "SELECT owner_id FROM ticket WHERE id = ?";
    private static final String REMOVE_TICKET = "DELETE FROM ticket WHERE id = ? AND owner_id=?";
    private static final String CLEAR_TICKET = "DELETE FROM ticket WHERE owner_id=?";
    private static final String UPDATE_TICKET_BY_ID = "UPDATE ticket SET " +
            "name = ?, coordinates_x = ?, coordinates_y = ?, creation_date = ?, price = ?," +
            " discount = ?, tickettype = ?, venuename = ?, capacity = ?, venuetype = ?, owner_id = ? WHERE id = ? AND owner_id=?";

    public DataBaseManager() {
    }

    public static DataBaseManager getInstance() {
        if (instance == null) {
            instance = new DataBaseManager();
        }
        return instance;
    }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void connectToDataBase() {
        try (Connection connection = createConnection()) {
            checkAndCreateTables(connection);
            System.out.println("Database connection established. Tables checked/created.");
        } catch (SQLException e) {
            System.out.println("Error while connecting to database");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    private static void checkAndCreateTables(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {

            stmt.executeUpdate("""
                        CREATE TABLE IF NOT EXISTS lab7_users (
                            user_id SERIAL PRIMARY KEY,
                            login VARCHAR(50) UNIQUE NOT NULL,
                            password VARCHAR(64) NOT NULL
                        );
                    """);

            stmt.executeUpdate("""
                        CREATE TABLE IF NOT EXISTS ticket (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            coordinates_x FLOAT NOT NULL,
                            coordinates_y BIGINT NOT NULL,
                            creation_date TIMESTAMP NOT NULL,
                            price INTEGER NOT NULL CHECK (price > 0),
                            discount DOUBLE PRECISION,
                            tickettype VARCHAR(20) NOT NULL,
                            venuename VARCHAR(100) NOT NULL,
                            capacity INTEGER NOT NULL CHECK (capacity > 0),
                            venuetype VARCHAR(20) NOT NULL,
                            owner_id INTEGER NOT NULL,
                            venueid SERIAL NOT NULL,
                            FOREIGN KEY (owner_id) REFERENCES lab7_users(user_id) ON DELETE CASCADE
                        );
                    """);

            System.out.println("Tables checked/created.");
        }
    }

    public static void getUsers() {
        try (Connection connection = createConnection();
             PreparedStatement getStatement = connection.prepareStatement(GET_USERS)) {
            ResultSet rs = getStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("user_id") + " " + rs.getString("login"));
            }
        } catch (Exception e) {
            logger.error("Error getting users: {}", e.getMessage());
        }
    }

    private static final String GET_USER_BY_USERNAME = "SELECT * FROM lab7_users WHERE login = ?";

    public static int getUserId(String login) {
        try (Connection connection = createConnection();
             PreparedStatement getStatement = connection.prepareStatement(GET_USER_BY_USERNAME)) {
            getStatement.setString(1, login);
            ResultSet rs = getStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            }
            return -1;
        } catch (Exception e) {
            logger.error("Error getting user ID for login {}: {}", login, e.getMessage());
            return -1;
        }
    }

    public static final String INSERT_USER_REQUEST = "INSERT INTO lab7_users (login, password) VALUES (?,?)";

    public static boolean insertUser(String login, String password) {
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            logger.error("Invalid login or password");
            return false;
        }

        String hashedPassword = HashHandler.encryptString(password);
        String sql = "INSERT INTO lab7_users (login, password) VALUES (?, ?)";

        try (Connection connection = createConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, hashedPassword);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error creating user: {}", e.getMessage());
            return false;
        }
    }


    public static boolean checkUser(String login, String password) {
        if (login == null || password == null) return false;

        String sql = "SELECT password FROM lab7_users WHERE login = ?";
        try (Connection connection = createConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password");
                    String inputHash = HashHandler.encryptString(password);
                    boolean result = storedHash.equals(inputHash);
                    if (result) logger.info("User '{}' logged in successfully", login);
                    else logger.warn("Incorrect password for user '{}'", login);
                    return result;
                }
            }
        } catch (SQLException e) {
            logger.error("Error checking user: {}", e.getMessage());
        }
        return false;
    }

    public static boolean insertTicket(Ticket ticket, String login) {
        if (ticket == null || login == null) {
            logger.error("Ticket or login is null");
            return false;
        }
        int userId = getUserId(login);
        if (userId == -1) {
            logger.error("User not found: {}", login);
            return false;
        }
        try (Connection connection = createConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_TICKET)) {
            statement.setInt(11, userId);
            insertTicketDataIntoStatement(ticket, statement);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    long newId = rs.getLong(1);
                    ticket.setId(newId);
                    ticket.setOwnerId(userId);
                    logger.info("Successfully inserted ticket with ID: {}", newId);
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            logger.error("Database error while inserting ticket: {}", e.getMessage());
            if (e.getSQLState().equals("23503")) {
                logger.error("User with ID {} does not exist or ticket data is invalid for user {}", userId, login);
            }
            return false;
        }
    }

    private static void insertTicketDataIntoStatement(Ticket ticket, PreparedStatement statement) {
        try {
            statement.setString(1, ticket.getName());
            statement.setFloat(2, ticket.getCoordinates().getX());
            statement.setLong(3, ticket.getCoordinates().getY());
            statement.setTimestamp(4, Timestamp.from(ticket.getCreationDate().toInstant()));
            statement.setInt(5, ticket.getPrice());
            statement.setDouble(6, ticket.getDiscount());
            statement.setString(7, String.valueOf(ticket.getType()));
            statement.setString(8, ticket.getVenue().getVenueName());
            statement.setInt(9, ticket.getVenue().getCapacity());
            statement.setString(10, String.valueOf(ticket.getVenue().getType()));
        } catch (SQLException e) {
            logger.error("Couldn't insert data into statement. Reason: {}", e.getMessage());
        }
    }

    private static Ticket extractTicketFromEntry(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getLong("id"));
        ticket.setName(rs.getString("name"));
        ticket.setCoordinates(new Coordinates(rs.getFloat("coordinates_x"), rs.getLong("coordinates_y")));
        ticket.setCreationDate(rs.getTimestamp("creation_date").toInstant().atZone(ZoneId.systemDefault()));
        ticket.setPrice(rs.getInt("price"));
        ticket.setDiscount(rs.getDouble("discount"));
        ticket.setType(TicketType.valueOf(rs.getString("tickettype")));
        Venue venue = new Venue();
        venue.setName(rs.getString("venuename"));
        venue.setCapacity(rs.getInt("capacity"));
        venue.setType(VenueType.valueOf(rs.getString("venuetype")));
        ticket.setVenue(venue);
        ticket.setOwnerId(rs.getInt("owner_id"));
        return ticket;
    }

    public static void getDataFromDatabase(CollectionManager collectionManager) {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM ticket");
            while (rs.next()) {
                try {
                    Ticket ticket = extractTicketFromEntry(rs);
                    collectionManager.add(ticket);

                } catch (Exception e) {
                    System.out.println("Invalid entry in DB. Reason: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Couldn't load data from DB. Reason: " + e.getMessage());
            System.exit(-1);
        }
    }

    public static boolean updateTicketById(int id, Ticket ticket, String login) {
        int userId = getUserId(login);
        int ownerId = getOwnerId(String.valueOf(id));
        if (ownerId == -1) {
            logger.error("Ticket {} not found", id);
            return false;
        }
        if (userId != ownerId) {
            logger.error("User {} is not the owner of ticket {}", login, id);
            return false;
        } else {
            try (Connection connection = createConnection();
                 PreparedStatement statement = connection.prepareStatement(UPDATE_TICKET_BY_ID)) {
                insertTicketDataIntoStatement(ticket, statement);
                statement.setInt(11, userId);
                statement.setInt(12, id);
                statement.setInt(13, userId); // This parameter was set previously, left for old code consistency
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    logger.info("Ticket {} updated successfully by user {}", id, login);
                    return true;
                } else {
                    logger.error("Ticket {} not found or not updated", id);
                    return false;
                }
            } catch (SQLException e) {
                logger.error("Database error while updating ticket {}: {}", id, e.getMessage());
                return false;
            }
        }
    }

    public static boolean removeTicketByName(String login, String key) {
        int userId = getUserId(login);
        int ownerId = getOwnerId(key);

        logger.info("DEBUG: removeTicketByName called. User login: {}, Ticket ID (key): {}", login, key);
        logger.info("DEBUG: Current User ID (from login '{}'): {}", login, userId);
        logger.info("DEBUG: Ticket Owner ID (for ticket ID '{}'): {}", key, ownerId);
        logger.info("DEBUG: Is User ID ({}) == Ticket Owner ID ({})? -> {}", userId, ownerId, (userId == ownerId));
        if (userId == getOwnerId(key)) {
            try (Connection connection = createConnection();
                 PreparedStatement statement = connection.prepareStatement(REMOVE_TICKET)) {
                statement.setInt(1, Integer.parseInt(key));
                statement.setInt(2, userId);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    return true;
                } else {
                    System.out.println("Not found");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Couldn't remove ticket. Reason: " + e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }

    public static int getOwnerId(String key) {
        try (Connection connection = createConnection();
             PreparedStatement getStatement = connection.prepareStatement(GET_OWNER_BY_KEY)) {
            getStatement.setLong(1, Long.parseLong(key));
            ResultSet rs = getStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt("owner_id");
            }
            return -1;
        } catch (Exception e) {
            return -2;
        }
    }

    public static boolean clear(String login) {
        int userId = getUserId(login);

        try (Connection connection = createConnection();
             PreparedStatement statement = connection.prepareStatement(CLEAR_TICKET)) {
            statement.setInt(1, userId);
            int rowsDeleted = statement.executeUpdate();
            logger.info("Deleted {} tickets for user {}", rowsDeleted, login);
            return rowsDeleted > 0;
        } catch (SQLException e) {
            logger.error("Couldn't clear tickets for user {}. Reason: {}", login, e.getMessage());
            return false;
        }
    }
}
