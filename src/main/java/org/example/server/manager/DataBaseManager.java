package org.example.server.manager;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.example.common.model.Coordinates;
import org.example.common.model.Ticket;
import org.example.common.model.Venue;
import org.example.common.model.enums.TicketType;
import org.example.common.model.enums.VenueType;
import org.example.server.handlers.HashHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DataBaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String username = "postgres";
    private static final String password = "kdseum";

    private static Connection connection;
    private static DataBaseManager instance;
    private static final String GET_USERS = "SELECT * FROM lab7_users";
    private static final Logger logger = LoggerFactory.getLogger(DataBaseManager.class);

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
        // DriverManager(JDBC(библиотека
    }
    public static void connectToDataBase() {
        try {
            connection = DriverManager.getConnection(URL, username, password);
            System.out.println("Connection is ready");
        } catch (SQLException e) {
            System.out.println("Error while connecting to database");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    public static void getUsers() {
        try {
            PreparedStatement getStatement = connection.prepareStatement(GET_USERS);
            ResultSet rs = getStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("user_id") + " " + rs.getString("login"));
            }
        } catch (Exception e) {

        }
    }

    private static final String GET_USER_BY_USERNAME = "SELECT * FROM lab7_users WHERE login = ?";

    // получение id пользователя по логину
    public static int getUserId(String login) {
        try {
            PreparedStatement getStatement = connection.prepareStatement(GET_USER_BY_USERNAME);
            getStatement.setString(1, login);
            ResultSet rs = getStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            }
            return -1;
        } catch (Exception e) {
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

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password");
                    String inputHash = HashHandler.encryptString(password);
                    return storedHash.equals(inputHash);
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
        try (PreparedStatement statement = connection.prepareStatement(INSERT_TICKET)) {
            statement.setInt(11, 1);
            insertTicketDataIntoStatement(ticket, statement);
            // Выполняем запрос и проверяем результат
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    long newId = rs.getLong(1);
                    ticket.setId(newId); // Обновляем ID билета
                    logger.info("Successfully inserted ticket with ID: {}", newId);
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            logger.error("Database error while inserting ticket: {}", e.getMessage());
            // Проверяем конкретно нарушение FOREIGN KEY
            if (e.getSQLState().equals("23503")) { // Код ошибки FOREIGN KEY violation
                //logger.error("User with ID {} does not exist in lab7_users table", userId);
            }
            return false;
        }
    }

    private static void insertTicketDataIntoStatement(Ticket ticket, PreparedStatement statement) {
        try {
            statement.setString(1, ticket.getName());
            statement.setFloat(2,ticket.getCoordinates().getX());
            statement.setLong(3, ticket.getCoordinates().getY());
            statement.setTimestamp(4, Timestamp.from(ticket.getCreationDate().toInstant()));
            statement.setInt(5, ticket.getPrice());
            statement.setDouble(6, ticket.getDiscount());
            statement.setString(7, String.valueOf(ticket.getType()));
            statement.setString(8, ticket.getVenue().getVenueName() );
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
        ticket.setId(rs.getLong("venueid"));
        venue.setName(rs.getString("venuename"));
        venue.setCapacity(rs.getInt("capacity"));
        venue.setType(VenueType.valueOf(rs.getString("venuetype")));
        ticket.setVenue(venue);
        return ticket;
    }

    public static void getDataFromDatabase(CollectionManager collectionManager) {
        try (Statement statement = connection.createStatement()) {
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
        } {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_TICKET_BY_ID)) {
                insertTicketDataIntoStatement(ticket, statement);
                statement.setInt(11, userId);
                statement.setInt(12, id);
                statement.setInt(13, userId);
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
        int userId = getUserId(login); // получаем id пользователя, от которого пришел запрос
        if (userId == getOwnerId(key)) {
            try (PreparedStatement statement = connection.prepareStatement(REMOVE_TICKET)) {
                statement.setInt(1, Integer.parseInt(key)); // Используйте setInt для int значений
                statement.setInt(2, userId);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    return true; // Возвращает true, если удаление произошло
                } else {
                    System.out.println("Not found");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Couldn't remove ticket. Reason: " + e.getMessage());
                return false;
            }
        } else {
            return false; // Неправильный владелец
        }
    }

    public static int getOwnerId(String key) {
        try {
            PreparedStatement getStatement = connection.prepareStatement(GET_OWNER_BY_KEY);
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

        try (PreparedStatement statement = connection.prepareStatement(CLEAR_TICKET)) {
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



