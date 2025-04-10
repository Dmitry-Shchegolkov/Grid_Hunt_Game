package com.shchegolkov.model.gameMap.utils;

import com.shchegolkov.model.gameObject.GameObject;

import java.util.List;

/**
 * Абстрактный класс, представляющий генерацию объектов в игровом поле.
 * Служит базовым классом для всех объектов генерации.
 */
public abstract class Generator {
    protected static final int EMPTY = 0; // Код игровой карты.
    protected static final int PLAYER = 1; // Код игрока на карте.
    protected static final int ENEMY = 2; // Код врага на карте.
    protected static final int OBSTACLE = 3; // Код препятствий на карте.
    protected static final int FINISH = 4; // Код финиша на карте.
    protected int[][] gameMap; // Игровая карта.
    protected List<GameObject> gameObjectList; // Список объектов.

    /**
     * Конструктор абстрактного класса.
     * @param gameMap Игровая карта.
     */
    public Generator(int[][] gameMap) {
        this.gameMap = gameMap;
    }

    /**
     * Абстрактный метод для генерации объектов на игровой карте.
     * Должен быть реализован в конкретных подклассах.
     * @param countObject Количество объектов на добавление.
     * @param position Генератор уникальных позиций.
     */
    public abstract int[][] generate(int countObject, Position position);

    /**
     * Возвращает игровую карту.
     * @return Игровая карта.
     */
    public int[][] getGameMap() {
        return this.gameMap;
    }

    /**
     * Случайное расположение объектов.
     * @param rating Рейтинг объектов.
     * @param countObject Количество объектов.
     * @param position Генератор уникальных позиций.
     */
    protected void placeRandomWalls(int[][] gameMap, int rating, int countObject, Position position) {
        for (int i = 0; i < countObject;) {
            position.generate();
            int yAxis = position.getY();
            int xAxis = position.getX();
            if (gameMap[yAxis][xAxis] == EMPTY) {
                gameMap[yAxis][xAxis] = rating;
                i++;
            }
            position.setPosition();
        }
    }

    /**
     * Возвращает список игровых объектов на игровом поле.
     * @return Список игровых объектов.
     */
    public List<GameObject> getGameObjectList() {
        return this.gameObjectList;
    }
}