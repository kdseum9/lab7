package org.example.client;

import org.example.common.model.Coordinates;
import org.example.common.model.Ticket;
import org.example.common.model.Venue;
import org.example.common.model.enums.TicketType;
import org.example.common.model.enums.VenueType;
import org.example.common.model.generator.CoordinatesGenerator;
import org.example.common.model.generator.IdGenerator;
import org.example.common.model.generator.VenueGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Класс для создания объекта {@link Ticket} с вводом данных с консоли.
 * Включает в себя генерацию всех необходимых полей билета.
 */
public class TicketInput {

    private static final Logger logger = LoggerFactory.getLogger(TicketInput.class);

    /**
     * Генерирует новый объект {@link Ticket}, запрашивая все данные у пользователя.
     * @return Сгенерированный объект Ticket
     */
    public static Ticket generateTicket() {
        logger.info("Generating new ticket...");
        Ticket ticket = new Ticket();
        ticket.setName(generateName());
        ticket.setCoordinates(CoordinatesGenerator.generateCoordinates());
        ticket.setPrice(generatePrice());
        ticket.setDiscount(generateDiscount());
        ticket.setType(generateTicketType());
        ticket.setVenue(VenueGenerator.generateVenue());
        return ticket;
    }

    /**
     * Запрашивает у пользователя имя билета.
     * @return Введённое имя
     */
    private static String generateName() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter name: ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                logger.info("Name set: {}", input);
                return input;
            }
            logger.warn("Empty name entered by user");
            System.out.println("Name cannot be empty!");
        }
    }

    /**
     * Запрашивает у пользователя цену билета.
     * @return Цена билета в виде целого числа
     */
    private static int generatePrice() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter price (number > 0): ");
                String input = scanner.nextLine().trim();
                double price = Double.parseDouble(input);
                if (price > 0) {
                    logger.info("Price set: {}", price);
                    return (int) price;
                }
                logger.warn("Price must be greater than 0, got: {}", price);
                System.out.println("Price must be greater than 0!");
            } catch (NumberFormatException e) {
                logger.warn("Invalid number format for price");
                System.out.println("Invalid number format!");
            }
        }
    }

    /**
     * Запрашивает у пользователя скидку на билет.
     * Может вернуть null, если скидка не указана.
     * @return Скидка как Double или null
     */
    private static Double generateDiscount() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter discount (0-100, or empty for null): ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    logger.info("No discount entered");
                    return null;
                }

                double discount = Double.parseDouble(input);
                if (discount > 0 && discount <= 100) {
                    logger.info("Discount set: {}", discount);
                    return discount;
                }

                logger.warn("Discount out of range: {}", discount);
                System.out.println("Discount must be between 0 and 100!");

            } catch (NumberFormatException e) {
                logger.warn("Invalid number format for discount");
                System.out.println("Invalid number format!");
            }
        }
    }

    private static TicketType generateTicketType() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter ticket type (VIP, USUAL, BUDGETARY, CHEAP)");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                logger.info("No ticket type provided, set as null");
                return null;
            }

            try {
                TicketType type = TicketType.valueOf(input.toUpperCase());
                logger.info("Ticket type set: {}", type);
                return type;
            } catch (IllegalArgumentException e) {
                logger.warn("Invalid ticket type entered: {}", input);
                System.out.println("Invalid ticket type! Please enter one of: VIP, USUAL, BUDGETARY, CHEAP.");
            }
        }
    }

    public static Ticket generateTicketFromScript(BufferedReader reader) throws IOException {
        String name = reader.readLine().trim();
        float x = Float.parseFloat(reader.readLine().trim());
        long y = Long.parseLong(reader.readLine().trim());
        int price = Integer.parseInt(reader.readLine().trim());
        double discount = Double.parseDouble(reader.readLine().trim());
        TicketType type = TicketType.valueOf(reader.readLine().trim());

        String venueName = reader.readLine().trim();
        int capacity = Integer.parseInt(reader.readLine().trim());
        VenueType venueType = VenueType.valueOf(reader.readLine().trim());

        Ticket ticket = new Ticket();
        ticket.setName(name);
        ticket.setCoordinates(new Coordinates(x, y));
        ticket.setPrice(price);
        ticket.setDiscount(discount);
        ticket.setType(type);
        ticket.setVenue(new Venue(venueName, capacity, venueType));

        return ticket;
    }


}
