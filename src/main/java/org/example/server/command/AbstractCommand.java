package org.example.server.command;

import org.example.common.Request;
import org.example.common.Response;
import org.example.common.exceptions.NoElementException;
import org.example.server.manager.CollectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Абстрактный класс для всех команд, реализующих определённое поведение в приложении.
 * Все команды должны реализовывать метод {@code execute}, принимающий аргументы
 * и объект менеджера коллекции, и возвращать результат выполнения команды.
 *
 * @author kdseum9
 * @version 1.0
 */

public abstract class AbstractCommand {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Новый метод execute, который работает с Request
    public abstract Response execute(Request request, CollectionManager collectionManager) throws NoElementException;

}
