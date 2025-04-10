package com.shchegolkov.model.gameMap;

import com.shchegolkov.model.gameMap.search.PathFinder;
import com.shchegolkov.model.gameObject.GameObject;
import com.shchegolkov.model.gameObject.PlayerObject;

import java.util.Comparator;
import java.util.List;

/**
 * Класс для перемещения объекта на игровом поле.
 */
public class GameMapMovement {
    protected static final int EMPTY = 0; // Код игровой карты.
    protected static final int PLAYER = 1; // Код игрока на карте.
    private static final int ENEMY = 2; // Код врага на карте.
    private static final int OBSTACLE = 3; // Код препятствий на карте.

    /**
     * Перемещает объект игрока на игровой карте по направлению.
     * @param map Игровая карта.
     * @param obj Объект для перемещения.
     * @param yAxis Направление движения по оси У.
     * @param xAxis Направление движения по оси Х.
     */
    public static void movePlayer(int[][] map, PlayerObject obj, int yAxis, int xAxis) {
        int y = obj.getY() + yAxis;
        int x = obj.getX() + xAxis;
        if (y >= 0 && y < map.length && x >= 0 && x < map.length && map[y][x] != ENEMY && map[y][x] != OBSTACLE) {
            map[y][x] = obj.getObjectKey();
            map[obj.getY()][obj.getX()] = EMPTY;
            obj.setNewCoordinates(y, x);
        }
    }

    /**
     * Перемещает врагов в сторону игрока на игровой карте.
     * @param map Игровая карта.
     * @param enemies Список врагов.
     * @param player Объект игрока.
     */
    public static void moveEnemies(int[][] map, List<GameObject> enemies, PlayerObject player) {
        PathFinder pathFinder = new PathFinder(map);
        int yPlayer = player.getY();
        int xPlayer = player.getX();
        map[yPlayer][xPlayer] = EMPTY;
        for (GameObject enemy : enemies) {
            int yEnemy = enemy.getY();
            int xEnemy = enemy.getX();
            int[] nextStep = pathFinder.getNextStep(yEnemy, xEnemy, yPlayer, xPlayer);
            if (nextStep[0] >= 0 && nextStep[0] < map.length && nextStep[1] >= 0 && nextStep[1] < map.length && map[nextStep[0]][nextStep[1]] < ENEMY) {
                map[nextStep[0]][nextStep[1]] = enemy.getObjectKey();
                map[yEnemy][xEnemy] = EMPTY;
                enemy.setNewCoordinates(nextStep[0], nextStep[1]);
            }
            enemy.setPriority(pathFinder.getPathSize());
        }
        enemies.sort(Comparator.comparingInt(GameObject::getPriority));
        map[yPlayer][xPlayer] = PLAYER;
    }
}
