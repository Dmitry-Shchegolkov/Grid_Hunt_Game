package com.shchegolkov.view;

import java.util.Map;
import java.util.Scanner;
import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * Класс представления.
 * Отвечает за отображение информации и сбор пользовательского ввода.
 */
public class View {
    private Scanner scanner; // Для чтения ввода пользователя

    /**
     * Конструктор представления.
     */
    public View() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Запрашивает и возвращает команду от пользователя.
     * @return Введенное пользователем команда.
     */
    public String getUserCommand() {
        return scanner.nextLine();
    }

    /**
     * Отображает игровую карту.
     * @param gameMap Игровая карта для отображения.
     * @param glossary Словарь для отображения цвета фона символа.
     */
    public void showGameMap(int[][] gameMap, Map<Integer, String[]> glossary) {
        try {
            for (int[] gameLine : gameMap) {
                for (int element : gameLine) {
                    String[] itemData = glossary.get(element); // Получаем информацию из словаря.
                    String item = itemData[0]; // Получаем символ из словаря по элементу.
                    Color background = Color.valueOf(itemData[1]); // Получает цвет фона из словаря по элементу.
                    System.out.print(colorize(item, Attribute.BLACK_TEXT(), background.getColor()));
                }
                System.out.println();
            }
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }

    /**
     * Отображает сообщение об ошибке.
     * @param message Сообщение для отображения.
     */
    public void showError(String message) {
        System.err.println(message);
        System.exit(-1);
    }

    /**
     * Отображает сообщение о результате игры.
     * @param message Сообщение для отображения.
     */
    public void showResultGameMessage(String message) {
        System.out.println(message);
        System.exit(0);
    }

    /**
     * Очищает игровое поле.
     */
    public void clearScene() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.out.println("Не удалось очистить консоль: " + e.getMessage());
        }
    }
}
