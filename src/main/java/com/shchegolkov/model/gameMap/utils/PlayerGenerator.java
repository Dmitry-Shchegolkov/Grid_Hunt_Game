package com.shchegolkov.model.gameMap.utils;

import com.shchegolkov.model.gameMap.search.PathFinder;
import com.shchegolkov.model.gameObject.GameObject;
import com.shchegolkov.model.gameObject.PlayerObject;

import java.util.ArrayList;

/**
 * Класс для генерации игрока.
 * Наследует абстрактный класс Generator.
 */
public class PlayerGenerator extends Generator {

    private final int yFinish;
    private final int xFinish;
    /**
     * Конструктор класса
     * @param gameMap Игровая карта.
     * @param yFinish Координата объекта финиша по оси У.
     * @param xFinish Координата объекта финиша по оси X.
     */
    public PlayerGenerator(int[][] gameMap, int yFinish, int xFinish) {
        super(gameMap);
        this.yFinish = yFinish;
        this.xFinish = xFinish;
    }

    /**
     * Реализация абстрактного метода generate.
     * Генерация игрока на игровой карте.
     * @param countObject Количество объектов на добавление.
     * @param position Генератор уникальных позиций.
     */
    @Override
    public int[][] generate(int countObject, Position position) {
        this.gameObjectList = new ArrayList<>();
        int sizeMax = gameMap.length * gameMap.length;
        gameMap[yFinish][xFinish] = EMPTY;
        int y = 0, x = 0;
        PathFinder finder = new PathFinder(gameMap);
        for (int i = position.getSize(); i < sizeMax; i++) {
            placeRandomWalls(gameMap, PLAYER, countObject, position);
            y = position.getY();
            x = position.getX();
            int[] step = finder.getNextStep(y, x, yFinish, xFinish);
            if (step[0] != y && step[1] != x) {
                gameMap[yFinish][xFinish] = FINISH;
                GameObject player = new PlayerObject(position.getY(), position.getX());
                gameObjectList.add(player);
                return gameMap;
            }
            gameMap[y][x] = EMPTY;
        }
        gameMap[y][x] = PLAYER;
        gameMap[yFinish][xFinish] = FINISH;
        GameObject player = new PlayerObject(position.getY(), position.getX());
        gameObjectList.add(player);
        return gameMap;
    }
}
