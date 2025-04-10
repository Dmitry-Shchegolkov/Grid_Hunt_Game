package com.shchegolkov.model.gameMap.search;

/**
 * Класс для хранения координат и цену ячейки по этим координатам.
 */
public class Point {
    private final int yAxis; // Координата по оси Y;
    private final int xAxis; // Координата по оси X;
    private final int price; // Цена ячейки.

    /**
     * Конструктор класса.
     * @param yAxis Координата по иси Y.
     * @param xAxis Координата по иси X.
     * @param price Цена ячейки.
     */
    public Point(int yAxis, int xAxis, int price) {
        this.yAxis = yAxis;
        this.xAxis = xAxis;
        this.price = price;
    }

    /**
     * Возвращает координату по оси У.
     * @return Координата по оси Y;
     */
    public int getY() {
        return this.yAxis;
    }

    /**
     * Возвращает координату по оси Х.
     * @return Координата по оси Х;
     */
    public int getX() {
        return this.xAxis;
    }

    /**
     * Возвращает цену ячейки.
     * @return Цена ячейки.
     */
    public int getPrice() {
        return this.price;
    }
}
