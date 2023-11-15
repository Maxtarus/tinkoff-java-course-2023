package edu.project2.solver;

import edu.project2.UtilsForTests;
import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SolverTest {
    private Maze maze;

    private static Stream<Arguments> wrongCoordinates() {
        return Stream.of(
            Arguments.of(
                new Coordinate(0, 0), new Coordinate(1, 1)
            ),
            Arguments.of(
                new Coordinate(1, 1), new Coordinate(4, 4)
            ),
            Arguments.of(
                new Coordinate(-1, -1), new Coordinate(6, 6)
            )
        );
    }

    @BeforeEach
    public void prepareMaze() {
        Cell[][] grid = UtilsForTests.getGrid(5, 5);
        maze = new Maze(5, 5, grid);
    }

    @Test
    @DisplayName("BFS solver test")
    public void bfsSolver_shouldReturnCorrectShortestPath() {
        Solver bfsSolver = new BfsMazeSolver(maze);
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(1, 3);
        assertThat(bfsSolver.solve(maze, start, end)).containsExactly(
            new Coordinate(1, 3),
            new Coordinate(1, 2),
            new Coordinate(1, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("wrongCoordinates")
    @DisplayName("BFS wrong coordinates test")
    public void bfsSolver_shouldThrowException_whenStartOrEndAreWrong(Coordinate start, Coordinate end) {
        Solver bfsSolver = new BfsMazeSolver(maze);
        assertThatThrownBy(() -> bfsSolver.solve(maze, start, end)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("DFS solver test")
    public void dfsSolver_shouldReturnCorrectPath() {
        Cell[][] grid = UtilsForTests.getGrid(5, 5);
        grid[1][2].setType(Cell.Type.WALL);
        grid[2][2].setType(Cell.Type.WALL);
        Maze maze = new Maze(5, 5, grid);
        Solver dfsSolver = new DfsMazeSolver(maze);
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(3, 3);
        assertThat(dfsSolver.solve(maze, start, end)).containsExactly(
            new Coordinate(1, 1),
            new Coordinate(1, 2),
            new Coordinate(1, 3),
            new Coordinate(2, 3),
            new Coordinate(3, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("wrongCoordinates")
    @DisplayName("DFS wrong coordinates test")
    public void dfsSolver_shouldThrowException_whenStartOrEndAreWrong(Coordinate start, Coordinate end) {
        Solver dfsSolver = new DfsMazeSolver(maze);
        assertThatThrownBy(() -> dfsSolver.solve(maze, start, end)).isInstanceOf(IllegalArgumentException.class);
    }
}
