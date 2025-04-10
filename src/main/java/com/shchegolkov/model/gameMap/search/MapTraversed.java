package com.shchegolkov.model.gameMap.search;

/**
 * Класс для проверки изолированных областей в сетке.
 * Реализует алгоритм поиска в глубину (DFS).
 */
public final class MapTraversed {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Координаты соседей
    private final int[][] grid; // Сетка.
    private final int rows; // Количество рядов в сетке.
    private final int columns; // Количество столбов в сетке.

    /**
     * Конструктор класса
     * @param grid Сетка.
     */
    public MapTraversed(int[][] grid) {
        if (grid == null || grid.length < 1) {
            throw new IllegalArgumentException("The connection map cannot be null");
        }
        this.grid = grid;
        this.rows = grid.length;
        this.columns = grid[0].length;
    }

    /**
     * Проверяет связь всех областей в сетке.
     * @return {@code true}, если все области сетки достижимы,
     *         {@code false} если есть изолированные области
     */
    public boolean isAllMapReachable() {
        boolean[][] visited = new boolean[rows][columns];
        int[] startPositions = addStartPositions();
        if (startPositions == null) {
            return false;
        }
        dfs(startPositions[0], startPositions[1], visited);
        return isVisited(visited);
    }

    /**
     * Проверяет, связана ли указанная ячейка с другими ячейками в сетке.
     * @param yStart Координата по оси Y.
     * @param xStart Координата по оси X.
     * @return {@code true}, если ячейка связана с другими ячейками в сетке,
     *         {@code false} если изолирована.
     */
    public boolean isConnected(int yStart, int xStart) {
        boolean[][] visited = new boolean[rows][columns];
        dfs(yStart, xStart, visited);
        return isVisited(visited);
    }

    /**
     * Aлгоритм поиска в глубину (DFS)/
     * @param y Координата по оси Y.
     * @param x Координата по оси X.
     * @param visited Сетка посещенных областей.
     */
    public void dfs(int y, int x, boolean[][] visited) {
        visited[y][x] = true;
        for (int[] direction : DIRECTIONS) {
            int nY = y + direction[0];
            int nX = x + direction[1];
            if (nY >= 0 && nY < rows && nX >= 0 && nX < columns && !visited[nY][nX] && grid[nY][nX] == 0) {
                dfs(nY, nX, visited);
            }
        }
    }
    /**
     * Проверяет что все области сетки были посещены.
     * @param visited Сетка посещенных областей.
     * @return {@code true}, если все области сетки посещены,
     *         {@code false} если есть изолированные области.
     */
    private boolean isVisited(boolean[][] visited) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Возвращает стартовую позицию.
     * @return Стартовая позиция.
     */
    private int[] addStartPositions() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 0) {
                    return new int[] {i , j};
                }
            }
        }
        return null;
    }
}
