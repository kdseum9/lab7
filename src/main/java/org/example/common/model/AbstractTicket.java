package org.example.common.model;


import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Абстрактный билет, реализующий основные поля и методы для сравнения и сериализации.
 */
public abstract class AbstractTicket implements Comparable<AbstractTicket>, Serializable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected ZonedDateTime creationDate;
    protected int price;
    protected Double discount;
    protected org.example.common.model.enums.TicketType type;
    protected Venue venue;
    protected int ownerId;

    public abstract String toXML();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public org.example.common.model.enums.TicketType getType() {
        return type;
    }

    public void setType(org.example.common.model.enums.TicketType type) {
        this.type = type;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public int compareTo(AbstractTicket o) {
        return Integer.compare(this.price, o.price);
    }

    @Override
    public String toString() {
        return "AbstractTicket{" +
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
}

