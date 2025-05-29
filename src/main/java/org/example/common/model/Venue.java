package org.example.common.model;

import org.example.common.model.enums.VenueType;
import org.example.common.model.generator.IdGenerator;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс Venue представляет место проведения мероприятия.
 * Содержит уникальный идентификатор, название, вместимость и тип.
 */
public class Venue implements Serializable {
    private Long id; // Поле не может быть null, значение должно быть больше 0 и генерироваться автоматически
    private String venueName; // Поле не может быть null и не должно быть пустым
    private Integer capacity; // Поле не может быть null, значение должно быть больше 0
    private VenueType type; // Поле может быть null

    /**
     * Конструктор по умолчанию. Генерирует уникальный id.
     */
    public Venue() {
        this.id = IdGenerator.generateId();
        this.venueName = null;
        this.capacity = null;
        this.type = null;
    }

    /**
     * Конструктор с параметрами.
     *
     * @param id уникальный идентификатор (должен быть больше 0)
     * @param venueName название места (не может быть null или пустым)
     * @param capacity вместимость (должна быть больше 0)
     * @param type тип места проведения (может быть null)
     */
    public Venue(long id, String venueName, Integer capacity, VenueType type) {
        this.id = id;
        this.venueName = venueName;
        this.capacity = capacity;
        this.type = type;
    }

    /**
     * Конструктор с авто-генерацией ID.
     *
     * @param venueName название места (не может быть null или пустым)
     * @param capacity вместимость (должна быть больше 0)
     * @param type тип места проведения (может быть null)
     */
    public Venue(String venueName, Integer capacity, VenueType type) {
        this.id = IdGenerator.generateId();
        this.venueName = venueName;
        this.capacity = capacity;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public VenueType getType() {
        return type;
    }

    public void setType(VenueType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + id +
                ", venueName='" + venueName + '\'' +
                ", capacity=" + capacity +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venue)) return false;
        Venue venue = (Venue) o;
        return Objects.equals(id, venue.id)
                && Objects.equals(venueName, venue.venueName)
                && Objects.equals(capacity, venue.capacity)
                && type == venue.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, venueName, capacity, type);
    }

    public String getName() {
        return venueName;
    }

    public void setName(String venueName) {

    }
}
