package org.example.common;

import org.example.common.model.Ticket;

import java.io.Serializable;

/**
 * Класс {@code Request} представляет собой объект-запрос, отправляемый клиентом серверу.
 * <p>Содержит имя команды, аргументы, объект {@link Ticket} (опционально) и данные аутентификации.</p>
 * <p>Является сериализуемым, чтобы можно было передавать по сети.</p>
 */
public class Request implements Serializable {
    private static final long serialVersionUID = 5760575944040770153L;

    /**
     * Имя команды, которую необходимо выполнить на сервере.
     */
    private String commandName;

    /**
     * Аргументы команды, если они требуются.
     */
    private String[] args;

    /**
     * Объект {@link Ticket}, если команда предполагает передачу элемента коллекции.
     */
    private Ticket ticket;

    /**
     * Логин пользователя для аутентификации (может быть null).
     */
    private String login;

    /**
     * Пароль пользователя для аутентификации (может быть null).
     */
    private String password;

    /**
     * Создаёт новый объект запроса без данных аутентификации.
     *
     * @param commandName имя команды
     * @param args массив аргументов
     * @param ticket объект {@link Ticket}, связанный с командой (может быть {@code null})
     */
    public Request(String commandName, String[] args, Ticket ticket) {
        this.commandName = commandName;
        this.args = args;
        this.ticket = ticket;
    }

    /**
     * Создаёт новый объект запроса с данными аутентификации.
     *
     * @param commandName имя команды
     * @param args массив аргументов
     * @param ticket объект {@link Ticket}, связанный с командой (может быть {@code null})
     * @param login логин пользователя
     * @param password пароль пользователя
     */
    public Request(String commandName, String[] args, Ticket ticket, String login, String password) {
        this.commandName = commandName;
        this.args = args;
        this.ticket = ticket;
        this.login = login;
        this.password = password;
    }

    /**
     * Возвращает имя команды.
     *
     * @return имя команды
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * Возвращает массив аргументов команды.
     *
     * @return аргументы команды
     */
    public String[] getArgs() {
        return args;
    }

    /**
     * Возвращает объект {@link Ticket}, если он присутствует.
     *
     * @return объект {@link Ticket} или {@code null}
     */
    public Ticket getTicket() {
        return ticket;
    }

    /**
     * Устанавливает объект {@link Ticket}.
     *
     * @param ticket новый объект {@link Ticket}
     */
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    /**
     * Возвращает логин пользователя.
     *
     * @return логин или {@code null}, если не установлен
     */
    public String getLogin() {
        return login;
    }

    /**
     * Устанавливает логин пользователя.
     *
     * @param login новый логин
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Возвращает пароль пользователя.
     *
     * @return пароль или {@code null}, если не установлен
     */
    public String getPassword() {
        return password;
    }

    /**
     * Устанавливает пароль пользователя.
     *
     * @param password новый пароль
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Возвращает строковое представление объекта запроса (без пароля в целях безопасности).
     *
     * @return строковое представление запроса
     */
    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + commandName + '\'' +
                ", args=" + String.join(",", args) +
                ", ticket=" + ticket +
                ", login='" + login + '\'' +
                '}';
    }
}