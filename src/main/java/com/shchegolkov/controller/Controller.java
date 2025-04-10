package com.shchegolkov.controller;

import com.shchegolkov.controller.config.ArgumentsConfig;
import com.shchegolkov.controller.config.ProfileConfig;
import com.shchegolkov.controller.exceptions.IllegalParametersException;
import com.shchegolkov.model.Model;
import com.shchegolkov.view.View;

/**
 * Класс контроллера
 * Обрабатывает пользовательский ввод и управляет моделью и представлением.
 */

public class Controller {
    private View view; // Ссылка на представление
    private Model model; // Ссылка на модель
    private String gameMode; // Игровой режим.

    /**
     * Конструктор контроллера
     * @param args Аргументы с консоли
     */
    public Controller(String[] args) {
        try {
            this.view = new View();
            ArgumentsConfig arguments = new ArgumentsConfig(args); // Обработка аргументов консоли.
            this.gameMode = arguments.getProfile();
            ProfileConfig profile = new ProfileConfig(this.gameMode); // Обработка параметров из файла .properties.
            parseArguments(arguments);
            this.model = new Model.Builder()
                    .enemiesCount(arguments.getEnemiesCount())
                    .wallsCount(arguments.getWallsCount())
                    .sizeGameMap(arguments.getSize())
                    .enemySymbol(profile.getEnemySymbol())
                    .enemyColor(profile.getEnemyColor())
                    .emptySymbol(profile.getEmptySymbol())
                    .emptyColor(profile.getEmptyColor())
                    .wallSymbol(profile.getWallSymbol())
                    .wallColor(profile.getWallColor())
                    .playerSymbol(profile.getPlayerSymbol())
                    .playerColor(profile.getPlayerColor())
                    .goalSymbol(profile.getGoalSymbol())
                    .goalColor(profile.getGoalColor())
                    .build();
        } catch (IllegalParametersException e) {
            this.view.showError(e.getMessage());
        }
    }

    /**
     * Основной метод запуска приложения.
     */
    public void startApp() {
        switch (gameMode) {
            case "develop" :
                gameModeDevelop();
                break;
            case "production" :
                gameModeProduction();
                break;
        }
    }

    /**
     * Запускает режим игры develop.
     */
    private void gameModeDevelop() {
        String command = "start";
        while(!command.equals("9")) {
            view.showGameMap(model.getGameMap(), model.getGlossary());
            command = view.getUserCommand().toLowerCase();
            switch (command) {
                case "w" :
                    model.movePlayer(-1, 0);
                    break;
                case "s" :
                    model.movePlayer(1, 0);
                    break;
                case "d" :
                    model.movePlayer(0, 1);
                    break;
                case "a" :
                    model.movePlayer(0, -1);
                    break;
                case "8" :
                    model.moveEnemies();
                    break;
            }
            if (model.checkForVictory()) {
                view.showResultGameMessage("Game win");
                break;
            }
            if (model.checkForLoss()) {
                view.showResultGameMessage("Game over");
                break;
            }
        }
    }

    /**
     * Запускает режим игры production.
     */
    private void gameModeProduction() {
        String command = "start";
        while(!command.equals("9")) {
            view.showGameMap(model.getGameMap(), model.getGlossary());
            command = view.getUserCommand().toLowerCase();
            switch (command) {
                case "w" :
                    model.movePlayer(-1, 0);
                    model.moveEnemies();
                    break;
                case "s" :
                    model.movePlayer(1, 0);
                    model.moveEnemies();
                    break;
                case "d" :
                    model.movePlayer(0, 1);
                    model.moveEnemies();
                    break;
                case "a" :
                    model.movePlayer(0, -1);
                    model.moveEnemies();
                    break;
            }
            if (model.checkForVictory()) {
                view.showResultGameMessage("Game win");
                break;
            }
            if (model.checkForLoss()) {
                view.showResultGameMessage("Game over");
                break;
            }
            view.clearScene();
        }
    }

    /**
     * Проверяет, что значения больше нуля и игровое поле не меньше чем количество объектов на нем.
     * @param arguments Аргументы с консоли.
     * @throws IllegalParametersException Количество объектов больше чем игровое поле или значения равны нулю, или отрицательные.
     */
    private void parseArguments(ArgumentsConfig arguments) {
        int enemiesCount = arguments.getEnemiesCount();
        int wallsCount = arguments.getWallsCount();
        int sizeMap = arguments.getSize();

        int sizeObjects = enemiesCount + wallsCount + 2;
        if (enemiesCount <= 0 || wallsCount <= 0 || sizeMap <= 0) {
            throw new IllegalParametersException("Error: incorrect arguments");
        }
        if (sizeObjects >= Math.pow(sizeMap, 2)) {
            throw new IllegalParametersException("Error: the number of objects is greater than the playing field");
        }
    }
}
