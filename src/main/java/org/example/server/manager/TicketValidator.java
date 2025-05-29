package org.example.server.manager;

import org.example.common.exceptions.WrongArgumentException;
import org.example.common.model.Coordinates;
import org.example.common.model.Ticket;
import org.example.common.model.Venue;
import org.example.common.model.enums.TicketType;
import org.example.common.model.enums.VenueType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;

/**
 * Валидатор для полей модели Ticket и связанных объектов.
 * <p>Проверяет корректность ввода и целостность готовых объектов с логированием ошибок.</p>
 */
public class TicketValidator {

    private static final Logger logger = LoggerFactory.getLogger(TicketValidator.class);


    public static void idIsOK(String arg) throws WrongArgumentException {
        try {
            Long.parseLong(arg);
        } catch (Exception e) {
            logger.warn("Invalid format for ID: {}", arg);
            throw new WrongArgumentException("Invalid format for ID.");
        }
    }

    public static void inputIsNotEmpty(String arg, String model) throws WrongArgumentException {
        if (arg == null || arg.trim().isEmpty()) {
            logger.warn("{} must not be empty.", model);
            throw new WrongArgumentException(model + " must not be empty.");
        }
    }

    public static void priceIsOk(String arg) throws WrongArgumentException {
        try {
            int n = Integer.parseInt(arg);
            if (n <= 0) {
                logger.warn("Price must be greater than 0: {}", arg);
                throw new WrongArgumentException("Price must be greater than 0.");
            }
        } catch (Exception e) {
            logger.warn("Invalid format for price: {}", arg);
            throw new WrongArgumentException("Invalid format for price.");
        }
    }

    public static void discountIsOk(String arg) throws WrongArgumentException {
        try {
            double n = Double.parseDouble(arg);
            if (n <= 0) {
                logger.warn("Discount must be greater than 0: {}", arg);
                throw new WrongArgumentException("Discount must be greater than 0.");
            } else if (n >= 100) {
                logger.warn("Discount must be less than 100: {}", arg);
                throw new WrongArgumentException("Discount must be less than 100.");
            }
        } catch (Exception e) {
            logger.warn("Invalid format for discount: {}", arg);
            throw new WrongArgumentException("Invalid format for discount.");
        }
    }

    public static void typeIsOk(String arg) throws WrongArgumentException {
        try {
            TicketType.valueOf(arg);
        } catch (Exception e) {
            logger.warn("Invalid TicketType value: {}", arg);
            throw new WrongArgumentException("Invalid TicketType value.");
        }
    }



    public static boolean validateTicket(Ticket ticket) {
        if (ticket == null) {
            logger.warn("Ticket is null.");
            return false;
        }

        boolean result = validateId(ticket.getId())
                && validateName(ticket.getName())
                && validateCoordinates(ticket.getCoordinates())
                && validateCreationDate(ticket.getCreationDate())
                && validatePrice(ticket.getPrice())
                && validateDiscount(ticket.getDiscount())
                && validateTicketType(ticket.getType())
                && validateVenue(ticket.getVenue());

        if (!result) {
            logger.warn("Ticket validation failed: {}", ticket);
        }

        return result;
    }

    private static boolean validateId(Long id) {
        boolean valid = id != null && id > 0;
        if (!valid) logger.warn("Invalid Ticket ID: {}", id);
        return valid;
    }

    private static boolean validateName(String name) {
        boolean valid = name != null && !name.trim().isEmpty();
        if (!valid) logger.warn("Invalid Ticket name: '{}'", name);
        return valid;
    }

    private static boolean validateCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            logger.warn("Validation failed: Coordinates object is null.");
            return false;
        }

        if (coordinates.getY() == null) {
            logger.warn("Validation failed: Y value in Coordinates is null. Coordinates: {}", coordinates);
            return false;
        }

        if (coordinates.getX() > 300) {
            logger.warn("Validation failed: X value exceeds 300. Coordinates: {}", coordinates);
            return false;
        }

        return true;
    }


    private static boolean validateCreationDate(ZonedDateTime creationDate) {
        boolean valid = creationDate != null;
        if (!valid) logger.warn("Invalid creation date: null");
        return valid;
    }

    private static boolean validatePrice(Integer price) {
        boolean valid = price != null && price > 0;
        if (!valid) logger.warn("Invalid Ticket price: {}", price);
        return valid;
    }

    private static boolean validateDiscount(Double discount) {
        boolean valid = discount == null || (discount > 0 && discount <= 100);
        if (!valid) logger.warn("Invalid Ticket discount: {}", discount);
        return valid;
    }

    private static boolean validateTicketType(Enum<?> ticketType) {
        boolean valid = ticketType != null;
        if (!valid) logger.warn("Invalid Ticket type: null");
        return valid;
    }

    private static boolean validateVenue(Venue venue) {
        if (venue == null) {
            logger.warn("Venue is null.");
            return false;
        }

        boolean result = validateVenueId(venue.getId())
                && validateVenueName(venue.getName())
                && validateVenueCapacity(venue.getCapacity())
                && validateVenueType(String.valueOf(venue.getType()));

        if (!result) {
            logger.warn("Venue validation failed: {}", venue);
        }

        return result;
    }

    private static boolean validateVenueId(Long id) {
        boolean valid = id != null && id > 0;
        if (!valid) logger.warn("Invalid Venue ID: {}", id);
        return valid;
    }

    private static boolean validateVenueName(String name) {
        boolean valid = name != null && !name.trim().isEmpty();
        if (!valid) logger.warn("Invalid Venue name: '{}'", name);
        return valid;
    }

    private static boolean validateVenueCapacity(Integer capacity) {
        boolean valid = capacity != null && capacity > 0;
        if (!valid) logger.warn("Invalid Venue capacity: {}", capacity);
        return valid;
    }

    private static boolean validateVenueType(String venueTypeStr) {
        if (venueTypeStr == null || venueTypeStr.equalsIgnoreCase("null") || venueTypeStr.isBlank()) {
            return true; // null или пустая строка допустимы
        }

        try {
            VenueType.valueOf(venueTypeStr.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid VenueType: {}", venueTypeStr);
            return false;
        }
    }

}


