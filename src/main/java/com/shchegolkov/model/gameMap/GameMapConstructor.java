package com.shchegolkov.model.gameMap;

import com.shchegolkov.model.gameMap.utils.*;
import com.shchegolkov.model.gameObject.FinishObject;
import com.shchegolkov.model.gameObject.GameObject;
import com.shchegolkov.model.gameObject.PlayerObject;
import java.util.List;

/**
 * Класс для создания карты и наполнения ее объектами.
 */
public class GameMapConstructor {
    private List<GameObject> enemiesObjects; // Список противников.
    private final int enemiesCount; // Количество противников.
    private final int wallsCount; // Количество препятствий.
    private int[][] gameMap; // Игровое поле.
    private FinishObject finishObject; // Объект финиш.
    private PlayerObject playerObject; // Объект игрок.

    /**
     * Конструктор класса
     * @param size Размер игрового поля.
     * @param enemiesCount Количество врагов.
     * @param wallsCount Количество препятствий.
     */
    public GameMapConstructor(int size, int enemiesCount, int wallsCount) {
        this.gameMap = new int[size][size];
        this.enemiesCount = enemiesCount;
        this.wallsCount = wallsCount;
    }

    /**
     * Создает новую игровую карту и заполняет ее.
     */
    public void generate() {
        Position position = new Position(gameMap.length);

        Generator generator = new ObstacleGenerator(gameMap); // Генерируем препятствия
        gameMap = generator.generate(wallsCount, position);

        generator = new FinishGenerator(gameMap); // Генерация финиша
        gameMap = generator.generate(1, position);
        finishObject = (FinishObject) generator.getGameObjectList().getFirst();

        generator = new EnemiesGenerator(gameMap);
        gameMap = generator.generate(enemiesCount, position);
        enemiesObjects = generator.getGameObjectList();

        generator = new PlayerGenerator(gameMap,
                finishObject.getY(),
                finishObject.getX());
        gameMap = generator.generate(1, position);
        playerObject = (PlayerObject) generator.getGameObjectList().getFirst();
    }

    /**
     * Возвращает заполненное игровое поле.
     * @return Игровое поле.
     */
    public int[][] getGameMap() {
        return this.gameMap;
    }

    /**
     * Возвращает объект финиша.
     * @return Объект финиша.
     */
    public FinishObject getFinishObject() {
        return this.finishObject;
    }

    /**
     * Возвращает объект игрока.
     * @return Объект игрока.
     */
    public PlayerObject getPlayerObject() {
        return this.playerObject;
    }

    /**
     * Возвращает список противников.
     * @return Список противников.
     */
    public List<GameObject> getEnemiesObjects() {
        return this.enemiesObjects;
    }
}


