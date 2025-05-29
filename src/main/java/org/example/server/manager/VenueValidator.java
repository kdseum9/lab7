package org.example.server.manager;

import org.example.common.exceptions.WrongArgumentException;
import org.example.common.model.enums.VenueType;

/**
 * Валидатор полей модели Venue.
 * <p>Проверяет корректность ввода данных для объекта места проведения.</p>
 */
public class VenueValidator {

    /**
     * Проверяет корректность ID места проведения.
     *
     * @param arg строка, представляющая ID
     * @throws WrongArgumentException если ID некорректен или меньше/равен 0
     */
    public static void idIsOk(String arg) throws WrongArgumentException {
        try {
            long id = Long.parseLong(arg);
            if (id <= 0) {
                throw new WrongArgumentException("Venue ID must be greater than 0.");
            }
        } catch (NumberFormatException e) {
            throw new WrongArgumentException("Invalid format for Venue ID.");
        }
    }

    /**
     * Проверяет корректность названия места проведения.
     *
     * @param venueName строка с названием
     * @throws WrongArgumentException если строка пустая или null
     */
    public static void nameIsOk(String venueName) throws WrongArgumentException {
        if (venueName == null || venueName.trim().isEmpty()) {
            throw new WrongArgumentException("Venue name must not be empty.");
        }
    }

    /**
     * Проверяет корректность вместимости.
     *
     * @param arg строка, представляющая вместимость
     * @throws WrongArgumentException если вместимость не положительна или не число
     */
    public static void capacityIsOk(String arg) throws WrongArgumentException {
        try {
            int capacity = Integer.parseInt(arg);
            if (capacity <= 0) {
                throw new WrongArgumentException("Capacity must be greater than 0.");
            }
        } catch (NumberFormatException e) {
            throw new WrongArgumentException("Invalid format for capacity.");
        }
    }

    /**
     * Проверяет корректность типа места проведения.
     *
     * @param arg строка с типом
     * @throws WrongArgumentException если значение не входит в список допустимых типов VenueType
     */
    public static void typeIsOk(String arg) throws WrongArgumentException {
        try {
            VenueType.valueOf(arg);
        } catch (Exception e) {
            throw new WrongArgumentException("Invalid format for VenueType.");
        }
    }
}
