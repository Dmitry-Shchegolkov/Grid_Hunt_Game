package com.shchegolkov.model.gameMap.utils;

import com.shchegolkov.model.gameObject.EnemyObject;
import com.shchegolkov.model.gameObject.GameObject;

import java.util.ArrayList;

/**
 * Класс для генерации противников.
 * Наследует абстрактный класс Generator.
 */
public class EnemiesGenerator extends Generator{

    /**
     * Конструктор класса
     * @param gameMap Игровая карта.
     */
    public EnemiesGenerator(int[][] gameMap) {
        super(gameMap);
    }

    /**
     * Реализация абстрактного метода generate.
     * Генерация противников на игровой карте.
     * @param countObject Количество объектов на добавление.
     * @param position Генератор уникальных позиций.
     */
    @Override
    public int[][] generate(int countObject, Position position) {
        gameObjectList = new ArrayList<>();
        for (int i = 0; i < countObject;) {
            position.generate();
            int yAxis = position.getY();
            int xAxis = position.getX();
            if (gameMap[yAxis][xAxis] == EMPTY) {
                gameMap[yAxis][xAxis] = ENEMY;
                i++;
                GameObject player = new EnemyObject(position.getY(), position.getX());
                gameObjectList.add(player);
            }
            position.setPosition();
        }
        return gameMap;
    }
}
