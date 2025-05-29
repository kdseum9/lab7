package org.example.common.model;

import org.example.common.model.enums.TicketType;
import org.example.common.model.enums.VenueType;
import org.example.common.model.generator.IdGenerator;
import org.example.server.manager.CoordinatesValidator;
import org.example.server.manager.TicketValidator;
import org.example.server.manager.VenueValidator;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Представляет билет с уникальным идентификатором, названием, координатами, датой создания,
 * ценой, скидкой, типом и местом проведения.
 * <p>
 * Используется для хранения информации о билетах и генерации XML-представления.
 */
public class Ticket implements Comparable<Ticket>, Serializable {

    /**
     * Уникальный идентификатор билета. Генерируется автоматически, должен быть > 0.
     */
    private long id;

    /**
     * Название билета. Не может быть null или пустым.
     */
    private String name;

    /**
     * Координаты, связанные с билетом. Не могут быть null.
     */
    private Coordinates coordinates;

    /**
     * Дата и время создания билета. Генерируется автоматически.
     */
    private ZonedDateTime creationDate;

    /**
     * Цена билета. Должна быть больше 0.
     */
    private int price;

    /**
     * Скидка на билет. Может быть null, значение должно быть больше 0 и не превышать 100.
     */
    private Double discount;

    /**
     * Тип билета. Не может быть null.
     */
    private TicketType type;

    /**
     * Место проведения мероприятия. Может быть null.
     */
    private Venue venue;

    /**
     * Конструктор по умолчанию. Создаёт билет с автоматически сгенерированными значениями id и creationDate.
     */
    public Ticket() {
        this.id = IdGenerator.generateId();
        this.name = null;
        this.coordinates = null;
        this.creationDate = ZonedDateTime.now();
        this.price = -1;
        this.discount = null;
        this.type = null;
        this.venue = null;


    }

    /**
     * Полный конструктор на основе строковых параметров. Валидирует значения перед созданием объекта.
     *
     * @param id           строковое представление идентификатора
     * @param name         название билета
     * @param X            координата X
     * @param Y            координата Y
     * @param creationDate дата создания (в формате ISO-8601)
     * @param price        цена
     * @param discount     скидка
     * @param type         тип билета
     * @param venueId      идентификатор места проведения
     * @param venueName    название места проведения
     * @param capacity     вместимость места
     * @param venueType    тип места
     * @throws Exception если одно из значений не прошло валидацию
     */
    public Ticket(String id, String name, String X, String Y, String creationDate, String price, String discount, String type, String venueId, String venueName, String capacity, String venueType) throws Exception {
        TicketValidator.idIsOK(id);
        TicketValidator.inputIsNotEmpty(name, "NAME");
        CoordinatesValidator.coordinateXIsOk(X);
        CoordinatesValidator.coordinateYIsOk(Y);
        TicketValidator.inputIsNotEmpty(creationDate, "DATE");
        TicketValidator.priceIsOk(price);
        TicketValidator.discountIsOk(discount);
        TicketValidator.typeIsOk(type);
        VenueValidator.idIsOk(venueId);
        VenueValidator.nameIsOk(venueName);
        VenueValidator.capacityIsOk(capacity);
        VenueValidator.typeIsOk(venueType);

        this.id = Long.parseLong(id);
        this.name = name;
        this.coordinates = new Coordinates(Float.parseFloat(X), Long.parseLong(Y));
        this.creationDate = ZonedDateTime.parse(creationDate);
        this.price = Integer.parseInt(price);
        this.discount = Double.parseDouble(discount);
        this.type = TicketType.valueOf(type);
        this.venue = new Venue(Long.parseLong(id), venueName, Integer.parseInt(capacity), VenueType.valueOf(type));
    }

    /**
     * Преобразует объект билета в XML-представление.
     *
     * @return XML-строка, представляющая билет.
     */
    public String toXML() {
        String xmlFormat = "<ticket>" +
                "<id>" + id + "</id>" +
                "<name>" + name + "</name>" +
                "<creationDate>" + creationDate + "</creationDate>" +
                "<price>" + price + "</price>" +
                "<discount>" + discount + "</discount>" +
                coordinates.toXML() +
                "<ticketType>" + type + "</ticketType>" ;
        if (venue != null) {
            xmlFormat += "<venueId>" + venue.getId() + "</venueId>" +
                    "<venueName>" + venue.getName() + "</venueName>" +
                    "<capacity>" + venue.getCapacity() + "</capacity>" +
                    "<venueType>" + venue.getType() + "</venueType>";
        }
        xmlFormat += "</ticket>";
        return xmlFormat;
    }

    // Геттеры и сеттеры с описанием

    /**
     * @return идентификатор билета
     */
    public long getId() {
        return id;
    }

    /**
     * @param id устанавливает идентификатор билета
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return название билета
     */
    public String getName() {
        return name;
    }

    /**
     * @param name устанавливает название билета
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return координаты билета
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates устанавливает координаты
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return дата создания билета
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate устанавливает дату создания
     */
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return цена билета
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price устанавливает цену билета
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return скидка на билет
     */
    public Double getDiscount() {

        return discount;
    }

    /**
     * @param discount устанавливает скидку
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * @return тип билета
     */
    public TicketType getType() {
        return type;
    }

    /**
     * @param type устанавливает тип билета
     */
    public void setType(TicketType type) {
        this.type = type;
    }

    /**
     * @return место проведения
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * @param venue устанавливает место проведения
     */
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    /**
     * Сравнивает билеты по цене. Null считается меньшим.
     *
     * @param other другой билет
     * @return результат сравнения
     */
    @Override
    public int compareTo(Ticket other) {
        if (this.price < 0 && other.price < 0) return 0;
        if (this.price < 0) return -1;
        if (other.price < 0) return 1;

        return Integer.compare(this.price, other.price);
    }

    /**
     * Возвращает строковое представление билета.
     *
     * @return строка с информацией о билете
     */
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", discount=" + discount +
                ", type=" + type +
                ", venue=" + venue +
                '}';
    }

    /**
     * Проверяет равенство двух объектов Ticket.
     *
     * @param o объект для сравнения
     * @return true, если объекты равны
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                Objects.equals(name, ticket.name) &&
                Objects.equals(coordinates, ticket.coordinates) &&
                Objects.equals(creationDate, ticket.creationDate) &&
                Objects.equals(price, ticket.price) &&
                Objects.equals(discount, ticket.discount) &&
                type == ticket.type &&
                Objects.equals(venue, ticket.venue);
    }

    /**
     * Вычисляет хэш-код для объекта Ticket.
     *
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, price, discount, type, venue);
    }

}
