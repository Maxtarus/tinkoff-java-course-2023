package edu.project2;

import edu.project2.model.Cell;

public final class UtilsForTests {
    public static final String PASSAGE = "\033[0;100m";
    public static final String PATH = "\033[0;102m";
    public static final String WALL = "\033[0;107m";
    public static final String RESET = "\u001B[0m";
    public static final String MAZE_WITH_PATH_FOR_TEST =
        WALL + '\t' + RESET + WALL + '\t' + RESET + WALL + '\t' + RESET + WALL + '\t' + RESET + WALL + '\t' + RESET +
            '\n' +
            WALL + '\t' + RESET + PATH + '\t' + RESET + PASSAGE + '\t' + RESET + PASSAGE + '\t' + RESET + WALL + '\t' + RESET +
            '\n' +
            WALL + '\t' + RESET + PATH + '\t' + RESET + PASSAGE + '\t' + RESET + PASSAGE + '\t' + RESET + WALL + '\t' + RESET +
            '\n' +
            WALL + '\t' + RESET + PATH + '\t' + RESET + PASSAGE + '\t' + RESET + PASSAGE + '\t' + RESET + WALL + '\t' + RESET +
            '\n' +
            WALL + '\t' + RESET + WALL + '\t' + RESET + WALL + '\t' + RESET + WALL + '\t' + RESET + WALL + '\t' + RESET +
            '\n';

    private UtilsForTests() {
    }

    public static Cell[][] getGridFullOfWalls(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(j, i, Cell.Type.WALL);
            }
        }
        return grid;
    }

    public static Cell[][] getGridFullOfPassages(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(j, i, Cell.Type.PASSAGE);
            }
        }
        return grid;
    }

    public static Cell[][] getGrid(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || j == 0 || i == height - 1 || j == width - 1) {
                    grid[i][j] = new Cell(j, i, Cell.Type.WALL);
                } else {
                    grid[i][j] = new Cell(j, i, Cell.Type.PASSAGE);
                }
            }
        }
        return grid;
    }
}
