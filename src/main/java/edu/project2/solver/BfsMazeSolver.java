package edu.project2.solver;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class BfsMazeSolver extends AbstractSolver {
    public BfsMazeSolver(Maze maze) {
        super(maze);
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (isCoordinatesNotValid(maze, start, end)) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        Queue<Coordinate> queue = new ArrayDeque<>();
        Coordinate[][] parents = new Coordinate[maze.height()][maze.width()];
        queue.add(start);
        visited[start.col()][start.row()] = true;
        parents[start.col()][start.row()] = start;
        while (!queue.isEmpty()) {
            Coordinate coordinate = queue.poll();
            List<Coordinate> neighbours = getNeighbours(coordinate);
            for (Coordinate neighbour : neighbours) {
                if (!visited[neighbour.col()][neighbour.row()]) {
                    visited[neighbour.col()][neighbour.row()] = true;
                    queue.add(neighbour);
                    parents[neighbour.col()][neighbour.row()] = new Coordinate(coordinate.row(), coordinate.col());
                }
            }
        }
        if (!visited[end.col()][end.row()]) {
            return Collections.emptyList();
        }
        path.add(end);
        Coordinate coordinate = parents[end.col()][end.row()];
        while (!coordinate.equals(start)) {
            path.add(coordinate);
            coordinate = parents[coordinate.col()][coordinate.row()];
        }
        path.add(start);
        return path;
    }
}
