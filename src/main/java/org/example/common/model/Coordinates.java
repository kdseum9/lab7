package org.example.common.model;

import java.io.Serializable;

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
}
