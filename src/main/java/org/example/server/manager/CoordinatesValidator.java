package org.example.server.manager;

import org.example.common.exceptions.WrongArgumentException;

/**
 * Валидатор координат X и Y.
 * <p>Проверяет корректность введённых значений координат по заданным условиям.</p>
 */
public class CoordinatesValidator {

    /**
     * Проверяет значение координаты X.
     *
     * @param arg строковое представление координаты X
     * @throws WrongArgumentException если значение некорректно: null, пустое, превышает предел или не число
     */
    public static void coordinateXIsOk(String arg) throws WrongArgumentException {
        if (arg == null) {
            throw new WrongArgumentException("Value of X must not be null.");
        }
        if (arg.trim().isEmpty()) {
            throw new WrongArgumentException("Value of X must not be empty.");
        }

        try {
            float x = Float.parseFloat(arg);
            if (x > 300) {
                throw new WrongArgumentException("Value of X must not exceed 300.");
            }
        } catch (NumberFormatException e) {
            throw new WrongArgumentException("Invalid format for X. Expected a float number.");
        }
    }

    /**
     * Проверяет значение координаты Y.
     *
     * @param arg строковое представление координаты Y
     * @throws WrongArgumentException если значение некорректно: null, пустое или не является целым числом
     */
    public static void coordinateYIsOk(String arg) throws WrongArgumentException {
        if (arg == null) {
            throw new WrongArgumentException("Value of Y must not be null.");
        }
        if (arg.trim().isEmpty()) {
            throw new WrongArgumentException("Value of Y must not be empty.");
        }

        try {
            Long.parseLong(arg);
        } catch (NumberFormatException e) {
            throw new WrongArgumentException("Invalid format for Y. Expected an integer.");
        }
    }
}
