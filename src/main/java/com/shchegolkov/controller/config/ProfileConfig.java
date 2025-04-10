package com.shchegolkov.controller.config;

import java.io.InputStream;
import java.util.Properties;
import com.shchegolkov.controller.exceptions.IllegalParametersException;

/**
 * Класс для загрузки и работы с конфигурационными параметрами из properties-файла.
 */
public class ProfileConfig {

    // Объект Properties для хранения загруженных параметров
    private final Properties properties = new Properties();

    /**
     * Конструктор класса. Загружает конфигурацию из файла .properties.
     * @param profile Имя properties файла
     * @throws IllegalParametersException Файл не найден или ошибка загрузки конфига.
     */
    public ProfileConfig(String profile) {
        StringBuilder configFileName = new StringBuilder("application-");
        configFileName.append(profile).append(".properties");

        // Загрузка файла config.properties из ресурсов
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFileName.toString())) {
            if (input == null) {
                throw new IllegalParametersException("File " + configFileName + " not found");
            }
            properties.load(input); // Загрузка свойств из потока
        } catch (Exception e) {
            throw new IllegalParametersException("Error loading config");
        }
    }

    /**
     * Получает символ противника из конфигурации.
     * @return Значение свойства enemy.char или" ", если свойство не найдено или пустое.
     */
    public String getEnemySymbol() {
        String value = properties.getProperty("enemy.char");
        if (value == null || value.length() != 1) {
            return " ";
        }
        return value;
    }

    /**
     * Получает символ игрока из конфигурации.
     * @return Значение свойства player.char или" ", если свойство не найдено или пустое.
     */
    public String getPlayerSymbol() {
        String value = properties.getProperty("player.char");
        if (value == null || value.length() != 1) {
            return " ";
        }
        return value;
    }

    /**
     * Получает символ препятствий из конфигурации.
     * @return Значение свойства wall.char или" ", если свойство не найдено или пустое.
     */
    public String getWallSymbol() {
        String value = properties.getProperty("wall.char");
        if (value == null || value.length() != 1) {
            return " ";
        }
        return value;
    }

    /**
     * Получает символ финиша из конфигурации.
     * @return Значение свойства goal.char или" ", если свойство не найдено или пустое.
     */
    public String getGoalSymbol() {
        String value = properties.getProperty("goal.char");
        if (value == null || value.length() != 1) {
            return " ";
        }
        return value;
    }

    /**
     * Получает символ игрового поля из конфигурации.
     * @return Значение свойства empty.char или" ", если свойство не найдено или пустое.
     */
    public String getEmptySymbol() {
        String value = properties.getProperty("empty.char");
        if (value == null || value.length() != 1) {
            return " ";
        }
        return value;
    }

    /**
     * Получает цвет противника из конфигурации.
     * @return Значение свойства enemy.char или RED, если свойство не найдено или пустое.
     */
    public String getEnemyColor() {
        String value = properties.getProperty("enemy.color");
        if (value == null || value.isEmpty()) {
            return "RED";
        }
        return value;
    }

    /**
     * Получает цвет игрока из конфигурации.
     * @return Значение свойства player.char или GREEN, если свойство не найдено или пустое.
     */
    public String getPlayerColor() {
        String value = properties.getProperty("player.color");
        if (value == null || value.isEmpty()) {
            return "GREEN";
        }
        return value;
    }

    /**
     * Получает цвет препятствий из конфигурации.
     * @return Значение свойства wall.char или MAGENTA, если свойство не найдено или пустое.
     */
    public String getWallColor() {
        String value = properties.getProperty("wall.color");
        if (value == null || value.isEmpty()) {
            return "MAGENTA";
        }
        return value;
    }

    /**
     * Получает цвет финиша из конфигурации.
     * @return Значение свойства goal.char или BLUE, если свойство не найдено или пустое.
     */
    public String getGoalColor() {
        String value = properties.getProperty("goal.color");
        if (value == null || value.isEmpty()) {
            return "BLUE";
        }
        return value;
    }

    /**
     * Получает цвет игрового поля из конфигурации.
     * @return Значение свойства empty.char или YELLOW, если свойство не найдено или пустое.
     */
    public String getEmptyColor() {
        String value = properties.getProperty("empty.color");
        if (value == null || value.isEmpty()) {
            return "YELLOW";
        }
        return value;
    }
}
