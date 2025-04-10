package com.shchegolkov.model.gameObject;

/**
 * Класс, представляющий объект игрока в игровом поле.
 * Наследует абстрактный класс GameObject
 */
public class PlayerObject extends GameObject {
    private static final int OBJECT_KEY_ON_MAP = 1;
    public PlayerObject(int yAxis, int xAxis) {
        super(yAxis, xAxis);
    }

    /**
     * Реализация метода получения ключа на игровом поле.
     * @return Ключ объекта на игровом поле (Игрок).
     */
    @Override
    public int getObjectKey() {
        return OBJECT_KEY_ON_MAP;
    }
}
