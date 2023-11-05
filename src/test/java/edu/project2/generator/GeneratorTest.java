package edu.project2.generator;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.solver.BfsMazeSolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class GeneratorTest {
    @Test
    @DisplayName("Prim's Maze Generator creates perfect maze test")
    public void primsMazeGenerator_shouldReturnPerfectMaze() {
        Maze maze = new PrimsMazeGenerator(41, 41).generate(41, 41);
        List<Coordinate> path = new BfsMazeSolver(maze).solve(
            maze,
            new Coordinate(1, 1),
            new Coordinate(39, 39)
        );
        assertThat(path).isNotEmpty();
    }

    @Test
    @DisplayName("DFS Maze Generator creates perfect maze test")
    public void dfsMazeGenerator_shouldReturnPerfectMaze() {
        Maze maze = new DfsMazeGenerator(41, 41).generate(41, 41);
        List<Coordinate> path = new BfsMazeSolver(maze).solve(
            maze,
            new Coordinate(1, 1),
            new Coordinate(39, 39)
        );
        assertThat(path).isNotEmpty();
    }
}
