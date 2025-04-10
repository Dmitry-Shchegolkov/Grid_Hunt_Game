package com.shchegolkov.model;

import com.shchegolkov.model.gameMap.GameMapConstructor;
import com.shchegolkov.model.gameMap.GameMapMovement;
import com.shchegolkov.model.gameObject.FinishObject;
import com.shchegolkov.model.gameObject.GameObject;
import com.shchegolkov.model.gameObject.PlayerObject;
import com.shchegolkov.model.gameRules.GameRules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Модель пользователя.
 * Содержит бизнес-логику и данные приложения.
 */

public class Model {
    private PlayerObject player; // Объект игрок.
    private FinishObject finish; // Объект финиш.
    private List<GameObject> enemies; // Список противников.
    private int[][] gameMap; // Игровое поле.
    private final Map<Integer, String[]> glossary; // Словарь символов и цветов фона символа.


    /**
     * Приватный конструктор, используется только Builder'ом.
     * @param builder экземпляр Builder'а с установленными параметрами.
     */
    private Model(Builder builder) {
        GameMapConstructor constructor = new GameMapConstructor(builder.size, builder.enemiesCount, builder.wallsCount);
        constructor.generate();
        this.gameMap = constructor.getGameMap();
        this.player = constructor.getPlayerObject();
        this.finish = constructor.getFinishObject();
        this.enemies = constructor.getEnemiesObjects();
        this.glossary = setNewGlossary(builder);
    }

    /**
     * Выполняет действие перемещение игрока на игровом поле по заданным координатам.
     * @param yAxis Направление движения по оси У.
     * @param xAxis Направление движения по оси Х.
     */
    public void movePlayer(int yAxis, int xAxis) {
        GameMapMovement.movePlayer(gameMap, player, yAxis, xAxis);
    }

    /**
     * Выполняет действие перемещение врагов к игроку на игровом поле.
     */
    public void moveEnemies() {
        GameMapMovement.moveEnemies(gameMap, enemies, player);
    }

    /**
     * Проверяет на победу в игре.
     * @return true - победа, false - не победа.
     */
    public boolean checkForVictory() {
        return GameRules.checkForVictory(player, finish);
    }

    /**
     * Проверяет на поражение в игре.
     * @return true - поражение, false - не поражение.
     */
    public boolean checkForLoss() {
        return GameRules.checkForLoss(enemies, player);
    }

    /**
     * Возвращает словарь символов и цветов фона символа.
     * @return Словарь символов и цветов фона символа.
     */
    public Map<Integer, String[]> getGlossary() {
        return this.glossary;
    }

    /**
     * Возвращает игровое поле.
     * @return Игровое поле.
     */
    public int[][] getGameMap() {
        return this.gameMap;
    }

    /**
     * Создает новый словарь символов и цветов фона символа
     * Ключ '0' - Игровое поле.
     * Ключ '1' - Игрок.
     * Ключ '2' - Противник.
     * Ключ '3' - Препятствия.
     * Ключ '4' - Финиш.
     * @param builder экземпляр Builder'а с установленными параметрами.
     */
    private Map<Integer, String[]> setNewGlossary(Builder builder) {
        Map<Integer, String[]> glossary = new HashMap<>(5); // Словарь из 5 элементов.
        glossary.put(0, setPairToArray(builder.emptySymbol, builder.emptyColor));
        glossary.put(1, setPairToArray(builder.playerSymbol, builder.playerColor));
        glossary.put(2, setPairToArray(builder.enemySymbol, builder.enemyColor));
        glossary.put(3, setPairToArray(builder.wallSymbol, builder.wallColor));
        glossary.put(4, setPairToArray(builder.goalSymbol, builder.goalColor));
        return glossary;
    }

    /**
     * Создает новую пару символ и цвет фона.
     * @param symbol Символ.
     * @param background Цвет фона.
     * @return Возвращает новую пару.
     */
    private String[] setPairToArray(String symbol, String background) {
        String[] pair = new String[2];
        pair[0] = symbol;
        pair[1] = background;
        return pair;
    }

    /**
     * Вложенный класс Builder для пошагового создания объекта Model.
     * Реализует паттерн Builder.
     */
    public static class Builder {
        private int enemiesCount;
        private int wallsCount;
        private int size;
        private String enemySymbol;
        private String emptySymbol;
        private String wallSymbol;
        private String playerSymbol;
        private String goalSymbol;
        private String enemyColor;
        private String emptyColor;
        private String wallColor;
        private String playerColor;
        private String goalColor;

        /**
         * Устанавливает количество врагов.
         * @param enemiesCount Количество врагов.
         * @return Текущий экземпляр Builder'а для цепочки вызовов.
         */
        public Builder enemiesCount(int enemiesCount) {
            this.enemiesCount = enemiesCount;
            return this;
        }

        /**
         * Устанавливает количество стен.
         * @param wallsCount Количество стен.
         * @return Текущий экземпляр Builder'а для цепочки вызовов.
         */
        public Builder wallsCount(int wallsCount) {
            this.wallsCount = wallsCount;
            return this;
        }

        /**
         * Устанавливает размер игрового поля.
         * @param size Размер игрового поля.
         * @return Текущий экземпляр Builder'а для цепочки вызовов.
         */
        public Builder sizeGameMap(int size) {
            this.size = size;
            return this;
        }

        /**
         * Устанавливает символ противника.
         * @param enemySymbol Символ противника.
         * @return Текущий экземпляр Builder'а для цепочки вызовов.
         */
        public Builder enemySymbol(String enemySymbol) {
            this.enemySymbol = enemySymbol;
            return this;
        }

        /**
         * Устанавливает цвет фона противника.
         * @param enemyColor Цвет фона противника.
         * @return Текущий экземпляр Builder'а для цепочки вызовов.
         */
        public Builder enemyColor(String enemyColor) {
            this.enemyColor = enemyColor;
            return this;
        }

        /**
         * Устанавливает символ игрового поля.
         * @param emptySymbol Символ игрового поля.
         * @return Текущий экземпляр Builder'а для цепочки вызовов.
         */
        public Builder emptySymbol(String emptySymbol) {
            this.emptySymbol = emptySymbol;
            return this;
        }

        /**
         * Устанавливает цвет фона игрового поля.
         * @param emptyColor Цвет фона игрового поля.
         * @return Текущий экземпляр Builder'а для цепочки вызовов.
         */
        public Builder emptyColor(String emptyColor) {
            this.emptyColor = emptyColor;
            return this;
        }

        /**
         * Устанавливает символ препятствия.
         * @param wallSymbol Символ препятствия.
         * @return Текущий экземпляр Builder'а для цепочки вызовов.
         */
        public Builder wallSymbol(String wallSymbol) {
            this.wallSymbol = wallSymbol;
            return this;
        }

        /**
         * Устанавливает цвет фона препятствия.
         * @param wallColor Цвет фона препятствия.
         * @return Текущий экземпляр Builder'а для цепочки вызовов.
         */
        public Builder wallColor(String wallColor) {
            this.wallColor = wallColor;
            return this;
        }

        /**
         * Устанавливает символ игрока.
         * @param playerSymbol Символ игрока.
         * @return Текущий экземпляр Builder'а для цепочки вызовов.
         */
        public Builder playerSymbol(String playerSymbol) {
            this.playerSymbol = playerSymbol;
            return this;
        }

        /**
         * Устанавливает цвет фона игрока.
         * @param playerColor Цвет фона игрока.
         * @return Текущий экземпляр Builder'а для цепочки вызовов.
         */
        public Builder playerColor(String playerColor) {
            this.playerColor = playerColor;
            return this;
        }

        /**
         * Устанавливает символ финиша.
         * @param goalSymbol Символ финиша.
         * @return Текущий экземпляр Builder'а для цепочки вызовов.
         */
        public Builder goalSymbol(String goalSymbol) {
            this.goalSymbol = goalSymbol;
            return this;
        }

        /**
         * Устанавливает цвет фона финиша.
         * @param goalColor Цвет фона финиша.
         * @return Текущий экземпляр Builder'а для цепочки вызовов.
         */
        public Builder goalColor(String goalColor) {
            this.goalColor = goalColor;
            return this;
        }

        /**
         * Создает объект Model с заданными параметрами.
         * @return новый экземпляр класса Model
         */
        public Model build() {
            return new Model(this);
        }
    }
}

