package edu.project2.solver;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.Collections;
import java.util.List;

public class DfsMazeSolver extends AbstractSolver {
    public DfsMazeSolver(Maze maze) {
        super(maze);
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (isCoordinatesNotValid(maze, start, end)) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        if (!dfs(start, end)) {
            return Collections.emptyList();
        }
        return path;
    }

    private boolean dfs(Coordinate coordinate, Coordinate end) {
        visited[coordinate.col()][coordinate.row()] = true;
        path.add(coordinate);
        if (coordinate.equals(end)) {
            return true;
        }
        List<Coordinate> neighbours = getNeighbours(coordinate);
        for (Coordinate neighbour: neighbours) {
            if (!visited[neighbour.col()][neighbour.row()]) {
                if (dfs(neighbour, end)) {
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        return false;
    }
}
