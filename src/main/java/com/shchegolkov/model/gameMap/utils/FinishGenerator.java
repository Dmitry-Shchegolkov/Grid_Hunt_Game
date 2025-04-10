package com.shchegolkov.model.gameMap.utils;

import com.shchegolkov.model.gameObject.FinishObject;
import com.shchegolkov.model.gameObject.GameObject;

import java.util.ArrayList;

/**
 * Класс для генерации финиша.
 * Наследует абстрактный класс Generator.
 */
public class FinishGenerator extends Generator {

    /**
     * Конструктор класса
     * @param gameMap Игровая карта.
     */
    public FinishGenerator(int[][] gameMap) {
        super(gameMap);
    }

    /**
     * Реализация абстрактного метода generate.
     * Генерация финиша на игровой карте.
     * @param countObject Количество объектов на добавление.
     * @param position Генератор уникальных позиций.
     */
    @Override
    public int[][] generate(int countObject, Position position) {
        this.gameObjectList = new ArrayList<>();
        placeRandomWalls(this.gameMap, FINISH, countObject, position);
        GameObject finish = new FinishObject(position.getY(), position.getX());
        gameObjectList.add(finish);
        return this.gameMap;
    }

    /**
     * Возвращает объект финиша.
     * @return Объект финиш.
     */
    public GameObject getFinishObject() {
        return gameObjectList.getFirst();
    }
}
