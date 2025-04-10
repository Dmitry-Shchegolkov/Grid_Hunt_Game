package com.shchegolkov.gameRules;

import com.shchegolkov.model.gameObject.GameObject;

import java.util.List;

/**
 * Класс для проверки правил в игре.
 */
public class GameRules {

    /**
     * Проверяет что координаты игрока и победной точки совпадают.
     * @param player Объект игрока
     * @param goal Объект победной точки.
     * @return true - Координаты совпадают, false - Координаты отличаются.
     */
    public static boolean checkForVictory(GameObject player, GameObject goal) {
        return player.getY() == goal.getY() && player.getX() == goal.getX();
    }

    /**
     * Проверяет что координаты врагов и игрока совпадают.
     * @param enemies Список врагов.
     * @param player Объект игрока
     * @return true - координаты совпадают, false - координаты не совпадают
     */
    public static boolean checkForLoss(List<GameObject> enemies, GameObject player) {
        for (GameObject enemy : enemies) {
            if (player.getY() == enemy.getY() && player.getX() == enemy.getX()) {
                return true;
            }
        }
        return false;
    }
}
