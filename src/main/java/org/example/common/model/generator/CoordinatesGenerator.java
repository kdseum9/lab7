package org.example.common.model.generator;

import org.example.common.model.Coordinates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Класс для генерации координат {@link Coordinates} через пользовательский ввод.
 * <p>Проверяет вводимые значения на корректность:
 * X не должен превышать 300, Y — любое целое число.</p>
 */
public class CoordinatesGenerator {
    private static final Logger logger = LoggerFactory.getLogger(CoordinatesGenerator.class);

    /**
     * Генерирует координаты, запрашивая у пользователя значения X и Y.
     * @return объект {@link Coordinates} с заданными пользователем координатами
     */
    public static Coordinates generateCoordinates() {
        logger.info("Generating coordinates...");
        Coordinates coordinates = new Coordinates();

        Float x = generateX();
        if (x != null) {
            coordinates.setX(x);
            logger.info("X coordinate set: {}", x);
        } else {
            logger.info("X coordinate not provided (null)");
        }

        Long y = generateY();
        coordinates.setY(y);
        logger.info("Y coordinate set: {}", y);

        return coordinates;
    }

    /**
     * Запрашивает координату X у пользователя. Значение должно быть числом не больше 300.
     * @return значение X или null, если пользователь оставил поле пустым
     */
    private static Float generateX() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter X coordinate: ");
            try {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    logger.info("Empty input)");
                    return null;
                }
                float x = Float.parseFloat(line);
                if (x <= 300) {
                    return x;
                }
                logger.warn("X coordinate must be <= 300, got: {}", x);
                System.out.println("X must be less than or equal to 300");
            } catch (Exception e) {
                logger.warn("Invalid input for X coordinate", e);
                System.out.println("Invalid coordinates entered");
            }
        }
    }

    /**
     * Запрашивает координату Y у пользователя. Ожидается целое число.
     * @return значение Y
     */
    private static Long generateY() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter Y coordinate: ");
            try {
                String line = scanner.nextLine();
                return Long.parseLong(line);
            } catch (Exception e) {
                logger.warn("Invalid input for Y coordinate", e);
                System.out.println("Invalid coordinates entered");
            }
        }
    }
}
