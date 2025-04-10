package com.shchegolkov.model.gameObject;

/**
 * Абстрактный класс, представляющий объект игрового поля.
 * Служит базовым классом для всех объектов игрового поля.
 */
public abstract class GameObject {
    protected int xAxis; // Координата по оси X.
    protected int yAxis; // Координата по оси Y.
    protected int priority; // Приоритет объекта.

    /**
     * Конструктор абстрактного класса.
     * @param yAxis Координата по оси X.
     * @param xAxis Координата по оси Y.
     */
    public GameObject(int yAxis, int xAxis) {
        this.yAxis = yAxis;
        this.xAxis = xAxis;
    }

    /**
     * Абстрактный метод для получения ключа на игровом поле.
     * Должен быть реализован в конкретных подклассах.
     * @return Ключ объекта на игровом поле.
     */
    public abstract int getObjectKey();

    /**
     * Возвращает координату по оси Y.
     * @return Координата по оси Y.
     */
    public int getY() {
        return this.yAxis;
    }

    /**
     * Возвращает координату по оси X.
     * @return Координата по оси X.
     */
    public int getX() {
        return this.xAxis;
    }

    /**
     * Переопределяет новые координаты для объекта.
     * @param yAxis Новая координата по оси У.
     * @param xAxis Новая координата по оси Х.
     */
    public void setNewCoordinates(int yAxis, int xAxis) {
        this.yAxis = yAxis;
        this.xAxis = xAxis;
    }

    /**
     * Возвращает приоритет объекта.
     * @return Приоритет объекта.
     */
    public int getPriority() {
        return this.priority;
    }
    /**
     * Задает приоритет для объекта.
     * @param priority Приоритет объекта.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
}
