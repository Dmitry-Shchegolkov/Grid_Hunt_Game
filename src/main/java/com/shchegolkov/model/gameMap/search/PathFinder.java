package com.shchegolkov.model.gameMap.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Класс для поиска путь между ячейками.
 * Реализует алгоритм поиска в ширину (BFS).
 */
public class PathFinder {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Координаты соседей
    private final int[][] grid; // Сетка областей.
    private final int rows; // Количество рядов в сетке.
    private final int columns; // Количество столбов в сетке.
    private Point[] pathRoad; // Путь с координатами между ячейками

    /**
     * Конструктор класса
     * @param grid Сетка областей.
     */
    public PathFinder(int[][] grid) {
        if (grid == null || grid.length < 1) {
            throw new IllegalArgumentException("The connection map cannot be null");
        }
        this.grid = grid;
        this.rows = grid.length;
        this.columns = grid[0].length;
    }

    /**
     * Возвращает координаты одного шага от стартовой ячейки до конечной.
     * @param yStart Стартовая координата по Y.
     * @param xStart Стартовая координата по X.
     * @param yFinish Конечная координата по Y.
     * @param xFinish Конечная координата по X.
     * @return Возвращает
     */
    public int[] getNextStep(int yStart, int xStart, int yFinish, int xFinish) {
        Queue<Point> queue = new LinkedList<>();
        Point[][] gridPoints = new Point[rows][columns];
        boolean[][] visited = new boolean[rows][columns];
        queue.add(new Point(yStart, xStart, 0));
        gridPoints[yStart][xStart] = new Point(yStart, xStart, 0);
        visited[yStart][xStart] = true;
        while(!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.getY() == yFinish && point.getX() == xFinish) {
                this.pathRoad = pathCalculator(gridPoints, point, visited);
                return new int[] { pathRoad[pathRoad.length - 1].getY(), pathRoad[pathRoad.length - 1].getX()};
            }
            for (int[] direction : DIRECTIONS) {
                int nY = point.getY() + direction[0];
                int nX = point.getX() + direction[1];
                if (nY >= 0 && nY < rows && nX >= 0 && nX < columns && grid[nY][nX] != 3 && grid[nY][nX] != 4 && !visited[nY][nX]) {
                    Point nextPoint = new Point(nY, nX, point.getPrice() + 1);
                    queue.add(nextPoint);
                    gridPoints[nY][nX] = nextPoint;
                    visited[nY][nX] = true;
                }
            }
        }
        return new int[] {yStart, xStart};
    }

    /**
     * Возвращает размер пути в сетке.
     * @return Размер пути.
     */
    public int getPathSize() {
        if (pathRoad == null) {
            return 0;
        }
        return pathRoad.length;
    }

    /**
     * Возвращает посчитанный путь.
     * @param gridPoints Сетка координат с ценами ячеек.
     * @param point Конечная ячейка.
     * @param visited Сетка посещенных ячеек.
     * @return Посчитанный путь.
     */
    private Point[] pathCalculator(Point[][] gridPoints, Point point, boolean[][] visited) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(point);
        while(point.getPrice() != 1) {
            for (int[] direction : DIRECTIONS) {
                int nY = point.getY() + direction[0];
                int nX = point.getX() + direction[1];
                if (nY >= 0 && nY < rows && nX >= 0 && nX < columns && visited[nY][nX] && grid[nY][nX] != 3 && grid[nY][nX] != 4) {
                    Point nextPoint = gridPoints[nY][nX];
                    if (nextPoint.getPrice() == point.getPrice() - 1) {
                        queue.add(nextPoint);
                        point = nextPoint;
                        break;
                    }
                }
            }
        }
        Point[] path = new Point[queue.size()];
        for (int i = 0; i < path.length; ++i) {
            path[i] = queue.poll();
        }
        return path;
    }
}