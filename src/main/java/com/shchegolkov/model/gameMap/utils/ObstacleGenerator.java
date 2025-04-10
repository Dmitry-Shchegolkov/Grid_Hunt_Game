package com.shchegolkov.model.gameMap.utils;

import com.shchegolkov.model.gameMap.search.MapTraversed;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для генерации препятствий.
 * Наследует абстрактный класс Generator.
 */

public class ObstacleGenerator extends Generator {
    private final int ITERATION = 100; // Количество итераций.

    /**
     * Конструктор класса
     * @param gameMap Игровая карта.
     */
    public ObstacleGenerator(int[][] gameMap) {
        super(gameMap);
    }

    /**
     * Реализация абстрактного метода generate.
     * Генерация препятствий на игровой карте.
     * @param countObject Количество объектов на добавление.
     * @param position Генератор уникальных позиций.
     */
    @Override
    public int[][] generate(int countObject, Position position) {
        List<Integer> exclusion = new ArrayList<>();
        for (int i = 0; i < ITERATION; i++) {
            exclusion.clear();
            int[][] nextGameMap = new int[gameMap.length][gameMap[0].length];
            for (int j = 0; j < countObject;) {
                position.generate();
                int yAxis = position.getY();
                int xAxis = position.getX();
                if (nextGameMap[yAxis][xAxis] == EMPTY) {
                    nextGameMap[yAxis][xAxis] = OBSTACLE;
                    j++;
                    exclusion.add(position.getPosition());
                }
            }
            MapTraversed mapTraversed = new MapTraversed(nextGameMap);
            if (mapTraversed.isAllMapReachable()) {
                addExclusionPositions(exclusion, position);
                return nextGameMap;
            }
            gameMap = nextGameMap;
        }
        addExclusionPositions(exclusion, position);
        return gameMap;
    }

    /**
     * Добовляет позиции в список эксклюзивных позиций
     * @param exclusion Список сгенерированных позиций.
     * @param position Генератор уникальных позиций.
     */
    private void addExclusionPositions(List<Integer> exclusion, Position position) {
        for (int value : exclusion) {
            position.setPosition(value);
        }
    }

}
