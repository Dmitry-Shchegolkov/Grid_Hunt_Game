package com.shchegolkov.model.gameObject;

/**
 * Класс, представляющий объект финиша в игровом поле.
 * Наследует абстрактный класс GameObject
 */
public class FinishObject extends GameObject {
    private static final int OBJECT_KEY_ON_MAP = 4;

    public FinishObject(int yAxis, int xAxis) {
        super(yAxis, xAxis);
    }

    /**
     * Реализация метода получения ключа на игровом поле.
     * @return Ключ объекта на игровом поле (Финиш).
     */
    @Override
    public int getObjectKey() {
        return OBJECT_KEY_ON_MAP;
    }
}
