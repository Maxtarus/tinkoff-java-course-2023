package edu.project2.solver;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSolver implements Solver {
    private static final int[][] NEIGHBOURS_COORDINATES = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    protected final List<Coordinate> path = new ArrayList<>();
    protected boolean[][] visited;
    protected Maze maze;

    protected AbstractSolver(Maze maze) {
        this.maze = maze;
        this.visited = new boolean[maze.height()][maze.width()];
    }

    protected List<Coordinate> getNeighbours(Coordinate coordinate) {
        List<Coordinate> neighbours = new ArrayList<>();
        for (int[] neighbour : NEIGHBOURS_COORDINATES) {
            int newRow = coordinate.row() + neighbour[0];
            int newColumn = coordinate.col() + neighbour[1];
            if (inBounds(newRow, newColumn) && maze.grid()[newColumn][newRow].getType() == Cell.Type.PASSAGE) {
                neighbours.add(new Coordinate(newRow, newColumn));
            }
        }
        return neighbours;
    }

    protected boolean isCoordinatesNotValid(Maze maze, Coordinate start, Coordinate end) {
        return !inBounds(start.row(), start.col()) || !inBounds(end.row(), end.col())
            || maze.grid()[start.col()][start.row()].getType() == Cell.Type.WALL
            || maze.grid()[end.col()][end.row()].getType() == Cell.Type.WALL;
    }

    protected boolean inBounds(int xCoordinate, int yCoordinate) {
        return xCoordinate >= 0 && xCoordinate < maze.width() && yCoordinate >= 0 && yCoordinate < maze.height();
    }
}
