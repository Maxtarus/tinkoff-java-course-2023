package edu.hw9.task3;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.solver.AbstractSolver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelDfsMazeSolver extends AbstractSolver {
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    public ParallelDfsMazeSolver(Maze maze) {
        super(maze);
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (isCoordinatesNotValid(maze, start, end)) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        boolean result = forkJoinPool.invoke(new ParallelSolverTask(start, end));
        if (!result) {
            return Collections.emptyList();
        }
        path.add(end);
        return path;
    }

    private class ParallelSolverTask extends RecursiveTask<Boolean> {
        private final Coordinate coordinate;
        private final Coordinate end;

        ParallelSolverTask(Coordinate coordinate, Coordinate end) {
            this.coordinate = coordinate;
            this.end = end;
        }

        @Override
        protected Boolean compute() {
            List<Coordinate> coordinatesForFork = new ArrayList<>();
            coordinatesForFork.add(coordinate);
            visited[coordinate.col()][coordinate.row()] = true;
            if (coordinate.equals(end)) {
                return true;
            }
            List<ParallelSolverTask> forks = new ArrayList<>();
            List<Coordinate> neighbours = getNeighbours(coordinate);
            for (Coordinate neighbour: neighbours) {
                if (!visited[neighbour.col()][neighbour.row()]) {
                    ParallelSolverTask task = new ParallelSolverTask(neighbour, end);
                    task.fork();
                    forks.add(task);
                }
            }
            for (ParallelSolverTask task : forks) {
                if (task.join()) {
                    path.addAll(coordinatesForFork);
                    return true;
                }
            }
            return false;
        }
    }
}
