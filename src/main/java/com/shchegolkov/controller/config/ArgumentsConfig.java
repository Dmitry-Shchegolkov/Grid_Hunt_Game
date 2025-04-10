package com.shchegolkov.controller.config;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.shchegolkov.controller.exceptions.IllegalParametersException;

/**
 * Класс для обработки аргументов с консоли.
 */

@Parameters(separators = "=") // Указываем, что разделитель — это "=".
public class ArgumentsConfig {

    @Parameter(names = {"--enemiesCount"}, description = "Количество врагов", required = true)
    private String enemiesCount; // Количество врагов.

    @Parameter(names = {"--wallsCount"}, description = "Количество препятствий", required = true)
    private String wallsCount; // Количество препятствий.

    @Parameter(names = {"--size"}, description = "Размер поля", required = true)
    private String size; // Размер игрового поля.

    @Parameter(names = {"--profile"}, description = "Режим приложения", required = true)
    private String profile; // Режим приложения.

    /**
     * Конструктор создания конфигурации для параметров.
     * @param args Аргументы с консоли.
     * @throws IllegalParametersException Неверный ввод аргументов.
     */
    public ArgumentsConfig(String[] args) throws IllegalParametersException {
        try {
            JCommander jCommander = JCommander.newBuilder()
                    .addObject(this)
                    .build();
            jCommander.parse(args);
        } catch (ParameterException e) {
            throw new IllegalParametersException(e.getMessage());
        }
    }

    /**
     * Возвращает количество врагов.
     * @return Количество врагов.
     * @throws IllegalParametersException Неверное значение.
     */
    public int getEnemiesCount() {
        try {
            return Integer.parseInt(this.enemiesCount);
        } catch (NumberFormatException e) {
            throw new IllegalParametersException("Incorrect input enemies count");
        }
    }

    /**
     * Возвращает количество препятствий.
     * @return Количество препятствий.
     * @throws IllegalParametersException Неверное значение.
     */
    public int getWallsCount() {
        try {
            return Integer.parseInt(this.wallsCount);
        } catch (NumberFormatException e) {
            throw new IllegalParametersException("Incorrect input walls count");
        }
    }

    /**
     * Возвращает размер игрового поля.
     * @return Размер игрового поля.
     * @throws IllegalParametersException Неверное значение.
     */
    public int getSize() {
        try {
            return Integer.parseInt(this.size);
        } catch (NumberFormatException e) {
            throw new IllegalParametersException("Incorrect input size");
        }
    }

    /**
     * Возвращает режим приложения.
     * @return Режим приложения.
     */
    public String getProfile() {
        return this.profile;
    }
}