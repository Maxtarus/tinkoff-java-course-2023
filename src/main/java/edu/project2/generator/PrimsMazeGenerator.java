package edu.project2.generator;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.List;

public class PrimsMazeGenerator extends AbstractMaze {
    private final List<Cell> cellsForMazePassage = new ArrayList<>();

    public PrimsMazeGenerator(int height, int width) {
        super(height, width);
    }

    @Override
    public Maze generate(int height, int width) {
        grid = getGridFullOfWalls(height, width);
        int xCoordinate = random.nextInt(width / 2) * 2 + 1;
        int yCoordinate = random.nextInt(height / 2) * 2 + 1;
        grid[yCoordinate][xCoordinate].setType(Cell.Type.PASSAGE);
        cellsForMazePassage.add(grid[yCoordinate][xCoordinate]);
        while (!cellsForMazePassage.isEmpty()) {
            int index = random.nextInt(cellsForMazePassage.size());
            Cell randomCell = cellsForMazePassage.get(index);
            xCoordinate = randomCell.getRow();
            yCoordinate = randomCell.getColumn();
            cellsForMazePassage.remove(index);
            var directions = new ArrayList<>(List.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
            while (!directions.isEmpty()) {
                int directionIndex = random.nextInt(directions.size());
                makePassage(directions.get(directionIndex), xCoordinate, yCoordinate);
                directions.remove(directionIndex);
            }
        }
        return new Maze(height, width, grid);
    }

    private Cell[][] getGridFullOfWalls(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(j, i, Cell.Type.WALL);
            }
        }
        return grid;
    }

    @SuppressWarnings("checkstyle:MissingSwitchDefault")
    private void makePassage(Direction direction, int xCoordinate, int yCoordinate) {
        switch (direction) {
            case NORTH -> {
                if (inBounds(xCoordinate, yCoordinate - 2)
                    && grid[yCoordinate - 2][xCoordinate].getType() == Cell.Type.WALL) {
                    grid[yCoordinate - 1][xCoordinate].setType(Cell.Type.PASSAGE);
                    grid[yCoordinate - 2][xCoordinate].setType(Cell.Type.PASSAGE);
                    cellsForMazePassage.add(grid[yCoordinate - 2][xCoordinate]);
                }
            }
            case SOUTH -> {
                if (inBounds(xCoordinate, yCoordinate + 2)
                    && grid[yCoordinate + 2][xCoordinate].getType() == Cell.Type.WALL) {
                    grid[yCoordinate + 1][xCoordinate].setType(Cell.Type.PASSAGE);
                    grid[yCoordinate + 2][xCoordinate].setType(Cell.Type.PASSAGE);
                    cellsForMazePassage.add(grid[yCoordinate + 2][xCoordinate]);
                }
            }
            case EAST -> {
                if (inBounds(xCoordinate + 2, yCoordinate)
                    && grid[yCoordinate][xCoordinate + 2].getType() == Cell.Type.WALL) {
                    grid[yCoordinate][xCoordinate + 1].setType(Cell.Type.PASSAGE);
                    grid[yCoordinate][xCoordinate + 2].setType(Cell.Type.PASSAGE);
                    cellsForMazePassage.add(grid[yCoordinate][xCoordinate + 2]);
                }
            }
            case WEST -> {
                if (inBounds(xCoordinate - 2, yCoordinate)
                    && grid[yCoordinate][xCoordinate - 2].getType() == Cell.Type.WALL) {
                    grid[yCoordinate][xCoordinate - 1].setType(Cell.Type.PASSAGE);
                    grid[yCoordinate][xCoordinate - 2].setType(Cell.Type.PASSAGE);
                    cellsForMazePassage.add(grid[yCoordinate][xCoordinate - 2]);
                }
            }
        }
    }
}
