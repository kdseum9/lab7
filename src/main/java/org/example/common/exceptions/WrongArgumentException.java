package org.example.common.exceptions;

/**
 * Исключение, выбрасываемое при некорректном аргументе.
 * <p>Обычно означает, что пользователь ввёл данные неподходящего типа или в неверном формате.</p>
 */
public class WrongArgumentException extends Exception {

    /**
     * Создаёт исключение с пояснением о некорректности введённого аргумента.
     *
     * @param argument аргумент, вызвавший исключение
     */
    public WrongArgumentException(String argument) {
        super("Invalid input detected. Please check the expected data type: " + argument);
    }
}
