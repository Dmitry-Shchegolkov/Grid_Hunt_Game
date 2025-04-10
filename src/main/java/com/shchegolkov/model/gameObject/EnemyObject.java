package com.shchegolkov.model.gameObject;

/**
 * Класс, представляющий объект врага в игровом поле.
 * Наследует абстрактный класс GameObject
 */

public class EnemyObject extends GameObject {
    private static final int OBJECT_KEY_ON_MAP = 2;
    public EnemyObject(int yAxis, int xAxis) {
        super(yAxis, xAxis);
    }

    /**
     * Реализация метода получения ключа на игровом поле.
     * @return Ключ объекта на игровом поле (Враг).
     */
    @Override
    public int getObjectKey() {
        return OBJECT_KEY_ON_MAP;
    }
}
