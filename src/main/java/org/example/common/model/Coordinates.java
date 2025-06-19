package org.example.common.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс, представляющий координаты с ограничением на X и обязательным Y.
 */
public class Coordinates implements Serializable {
    private float x; // Максимальное значение: 300
    private Long y;  // Обязательное поле

    public Coordinates() {}

    /**
     * Конструктор координат.
     * @param x значение X (макс. 300)
     * @param y значение Y (не null)
     */
    public Coordinates(float x, long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return значение координаты X
     */
    public float getX() {
        return x;
    }

    /**
     * Устанавливает значение X.
     * @param x значение (рекомендуется: ≤ 300)
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return значение координаты Y
     */
    public Long getY() {
        return y;
    }

    /**
     * Устанавливает значение Y.
     * @param y значение (не может быть null)
     */
    public void setY(Long y) {
        this.y = y;
    }

    /**
     * Представление координат в XML-формате.
     * @return строка XML
     */
    public String toXML() {
        return "<coordinate><x>" + x + "</x><y>" + y + "</y></coordinate>";
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.x, x) == 0 && Objects.equals(y, that.y);
    }

    /**
     * Вычисляет хэш-код для объекта Coordinates.
     * Должен быть согласован с методом equals().
     * @return хэш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
