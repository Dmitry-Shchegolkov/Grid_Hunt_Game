package com.shchegolkov.model.gameMap.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Класс для генерации уникальной позиции на игровом поле.
 */
public class Position {
    private Set<Integer> exclusion; // Список уже занятых позиций.
    private int position; // Текущая позиция.
    private final int size; // Размер игрового поля.
    private final int sizePositions; // Максимальное количество позиций.

    /**
     * Конструктор класса.
     * @param size Размер игрового поля.
     */
    public Position(int size) {
        this.size = size;
        this.sizePositions = (int) Math.pow(size, 2);
        this.exclusion = new HashSet<>();
        this.position = 0;
    }

    /**
     * Генерирует новую позицию.
     */
    public void generate() {
        Random random = new Random();
        do {
            position = random.nextInt(sizePositions);
        } while (exclusion.contains(position));
    }

    /**
     * Добавляет позицию в список исключений.
     */
    public void setPosition() {
        exclusion.add(this.position);
    }

    /**
     * Добавляет позицию в список исключений.
     * @param position Позиция для исключений.
     */
    public void setPosition(int position) {
        exclusion.add(position);
    }

    /**
     * Возвращает сгенерированную позицию.
     * @return Сгенерированная позиция.
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Возвращает Y координату из текущей позиции.
     * @return Координата по оси Y.
     */
    public int getY() {
        return position / size;
    }

    /**
     * Возвращает X координату из текущей позиции.
     * @return Координата по оси X.
     */
    public int getX() {
        return position % size;
    }

    /**
     * Возвращает размер списка исключений.
     * @return Размер списка исключений
     */
    public int getSize() {
        return exclusion.size();
    }
}
