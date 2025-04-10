package com.shchegolkov.controller.exceptions;

/**
 * Класс исключений, которые могут быть вызваны во время работы,
 * если аргументы с консоли или данные из конфигурационного файла не соответствуют требованиям.
 */
public class IllegalParametersException extends RuntimeException {
    public IllegalParametersException(String message) {
        super(message);
    }
}
