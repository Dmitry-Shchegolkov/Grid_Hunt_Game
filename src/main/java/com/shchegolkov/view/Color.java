package com.shchegolkov.view;

import com.diogonunes.jcolor.Attribute;

/**
 * Перечисление, представляющее основные цвета.
 * Каждый цвет имеет свое значение цвета фона для текста
 */
public enum Color {
    WHITE(Attribute.WHITE_BACK()), // Белый фон
    BLACK(Attribute.BLACK_BACK()), // Черный фон
    CYAN(Attribute.CYAN_BACK()), // Голубой фон
    BLUE(Attribute.BLUE_BACK()), // Синий фон
    YELLOW(Attribute.YELLOW_BACK()), // Желтый фон
    RED(Attribute.RED_BACK()), // Красный фон
    GREEN(Attribute.GREEN_BACK()), // Зеленый фон
    MAGENTA(Attribute.MAGENTA_BACK()); // Фиолетовый фон

    private final Attribute color; // Цвет фона текста

    /**
     * Конструктор для создания элемента перечисления.
     * @param color Значение цвета текста.
     */
    Color(Attribute color) {
        this.color = color;
    }

    /**
     * Возвращает цвет фона для текста.
     * @return Цвет фона.
     */
    public Attribute getColor() {
        return color;
    }
}
