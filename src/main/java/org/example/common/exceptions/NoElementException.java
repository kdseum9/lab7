package org.example.common.exceptions;

/**
 * <p>Исключение, выбрасываемое при попытке получить доступ к элементу коллекции по ключу или ID, которого не существует.</p>
 *
 * <p>Используется, например, в командах <code>update</code> или <code>remove_by_id</code>, если элемент не найден.</p>
 */
public class NoElementException extends Exception {

    /**
     * Создает исключение для отсутствующего элемента с указанным ключом.
     *
     * @param key ключ элемента, который не найден
     */
    public NoElementException(String key) {
        super("No element in collection with key: " + key);
    }

    /**
     * Создает исключение для отсутствующего элемента с указанным ID.
     *
     * @param id ID элемента, который не найден
     */
    public NoElementException(Long id) {
        super("No element in collection with id: " + id);
    }
}
