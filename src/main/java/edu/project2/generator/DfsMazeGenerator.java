package edu.project2.generator;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DfsMazeGenerator extends AbstractMaze {
    private boolean[][] visited;

    public DfsMazeGenerator(int height, int width) {
        super(height, width);
    }

    @Override
    public Maze generate(int height, int width) {
        grid = makeGrid(height, width);
        Stack<Cell> cellStack = new Stack<>();
        Cell currentCell = grid[1][1];
        visited[1][1] = true;

        while (girdHasUnvisitedCells(visited)) {
            List<NeighbourCellWithDirection> neighbours =
                getUnvisitedNeighbours(currentCell.getRow(), currentCell.getColumn(), visited);
            if (!neighbours.isEmpty()) {
                cellStack.push(currentCell);
                int randomNeighbourIndex = random.nextInt(neighbours.size());
                Cell randomCell = neighbours.get(randomNeighbourIndex).cell();
                Direction randomCellDirection = neighbours.get(randomNeighbourIndex).direction();
                switch (randomCellDirection) {
                    case NORTH -> grid[randomCell.getColumn() + 1][randomCell.getRow()].setType(Cell.Type.PASSAGE);
                    case SOUTH -> grid[randomCell.getColumn() - 1][randomCell.getRow()].setType(Cell.Type.PASSAGE);
                    case EAST -> grid[randomCell.getColumn()][randomCell.getRow() - 1].setType(Cell.Type.PASSAGE);
                    case WEST -> grid[randomCell.getColumn()][randomCell.getRow() + 1].setType(Cell.Type.PASSAGE);
                    default -> {
                    }
                }
                currentCell = randomCell;
                visited[currentCell.getColumn()][currentCell.getRow()] = true;
            } else {
                currentCell = cellStack.pop();
            }
        }
        return new Maze(height, width, grid);
    }

    private Cell[][] makeGrid(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        visited = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i % 2 != 0 && j % 2 != 0) && (i < height - 1 && j < width - 1)) {
                    grid[i][j] = new Cell(j, i, Cell.Type.PASSAGE);
                } else {
                    grid[i][j] = new Cell(j, i, Cell.Type.WALL);
                    visited[i][j] = true;
                }
            }
        }
        return grid;
    }

    private boolean girdHasUnvisitedCells(boolean[][] visited) {
        for (boolean[] booleans : visited) {
            for (boolean aBoolean : booleans) {
                if (!aBoolean) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<NeighbourCellWithDirection> getUnvisitedNeighbours(
        int xCoordinate,
        int yCoordinate,
        boolean[][] visited
    ) {
        List<NeighbourCellWithDirection> unvisitedNeighbours = new ArrayList<>();
        if (inBounds(xCoordinate - 2, yCoordinate) && !visited[yCoordinate][xCoordinate - 2]) {
            unvisitedNeighbours.add(new NeighbourCellWithDirection(
                grid[yCoordinate][xCoordinate - 2],
                Direction.WEST
            ));
        }
        if (inBounds(xCoordinate + 2, yCoordinate) && !visited[yCoordinate][xCoordinate + 2]) {
            unvisitedNeighbours.add(new NeighbourCellWithDirection(
                grid[yCoordinate][xCoordinate + 2],
                Direction.EAST
            ));
        }
        if (inBounds(xCoordinate, yCoordinate - 2) && !visited[yCoordinate - 2][xCoordinate]) {
            unvisitedNeighbours.add(new NeighbourCellWithDirection(
                grid[yCoordinate - 2][xCoordinate],
                Direction.NORTH
            ));
        }
        if (inBounds(xCoordinate, yCoordinate + 2) && !visited[yCoordinate + 2][xCoordinate]) {
            unvisitedNeighbours.add(new NeighbourCellWithDirection(
                grid[yCoordinate + 2][xCoordinate],
                Direction.SOUTH
            ));
        }
        return unvisitedNeighbours;
    }

    private record NeighbourCellWithDirection(Cell cell, Direction direction) {
    }
}
