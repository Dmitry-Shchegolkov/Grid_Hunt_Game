package com.shchegolkov;

import com.shchegolkov.controller.Controller;

/**
 * Основной класс для запуска приложения.
 * Создает все компоненты и запускает основной процесс.
 * @version 1.0
 * @author Dmitry
 */
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(args);
        controller.startApp();
    }
}
