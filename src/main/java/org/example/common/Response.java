package org.example.common;

import java.io.Serializable;

/**
 * Класс {@code Response} представляет собой объект-ответ, отправляемый сервером клиенту.
 * <p>Содержит сообщение о результате выполнения команды и (опционально) дополнительные данные.</p>
 * <p>Является сериализуемым для передачи по сети.</p>
 */
public class Response implements Serializable {

    /**
     * Сообщение, описывающее результат выполнения команды.
     */
    private String message;

    /**
     * Дополнительные данные, возвращаемые сервером (может быть {@code null}).
     * Например, это может быть коллекция объектов или результат фильтрации.
     */
    private Object data;

    /**
     * Создаёт новый объект ответа.
     *
     * @param message текст сообщения
     * @param data дополнительные данные (может быть {@code null})
     */
    public Response(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    /**
     * Возвращает сообщение результата.
     *
     * @return сообщение
     */
    public String getMessage() {
        return message;
    }

    /**
     * Возвращает дополнительные данные ответа.
     *
     * @return данные (или {@code null}, если отсутствуют)
     */
    public Object getData() {
        return data;
    }

    /**
     * Возвращает строковое представление объекта ответа.
     *
     * @return строковое представление
     */
    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
