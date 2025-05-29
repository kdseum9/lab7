package org.example.common.model.generator;

import java.util.ArrayList;

/**
 * Класс-генератор уникальных идентификаторов.
 * <p>Хранит список уже сгенерированных ID и предоставляет методы
 * для генерации, добавления и удаления ID.</p>
 */
public class IdGenerator {
    /** Следующее значение ID для генерации */
    public static long nextId = 1;

    /** Список всех сгенерированных ID */
    public static ArrayList<Long> idList = new ArrayList<>();

    /**
     * Генерирует новый уникальный ID и добавляет его в список.
     * @return сгенерированный ID
     */
    public static long generateId() {
        long newId = nextId++;
        idList.add(newId);
        return newId;
    }

    /**
     * Добавляет заданный ID в список. Используется при загрузке из внешнего источника.
     * @param id ID, который нужно добавить
     */
    public static void add(long id) {
        idList.add(id);
    }

    /**
     * Удаляет заданный ID из списка.
     * @param id ID, который нужно удалить
     */
    public static void remove(long id) {
        idList.remove(id);
    }
}
