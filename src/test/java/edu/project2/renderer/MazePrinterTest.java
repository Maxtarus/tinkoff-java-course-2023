package edu.project2.renderer;

import edu.project2.UtilsForTests;
import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.printer.MazePrinter;
import edu.project2.printer.Renderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static edu.project2.UtilsForTests.PASSAGE;
import static edu.project2.UtilsForTests.RESET;
import static edu.project2.UtilsForTests.WALL;
import static org.assertj.core.api.Assertions.assertThat;

public class MazePrinterTest {
    private final Renderer renderer = new MazePrinter();

    private String expectedMaze;

    private void prepareStrings(String cellType) {
        String mazeWall = cellType + '\t' + RESET;
        String firstMazeLine = mazeWall.repeat(5) + '\n';
        expectedMaze = firstMazeLine.repeat(5);
    }

    @Test
    @DisplayName("Maze full of walls test")
    public void shouldReturnStringWithMazeFullOfWalls() {
        prepareStrings(WALL);
        Cell[][] grid = UtilsForTests.getGridFullOfWalls(5, 5);
        Maze maze = new Maze(5, 5, grid);
        Renderer renderer = new MazePrinter();
        assertThat(renderer.render(maze)).isEqualTo(expectedMaze);
    }

    @Test
    @DisplayName("Render maze full of passages test")
    public void shouldReturnStringWithMazeFullOfPassages() {
        prepareStrings(PASSAGE);
        Cell[][] grid = UtilsForTests.getGridFullOfPassages(5, 5);
        Maze maze = new Maze(5, 5, grid);
        assertThat(renderer.render(maze)).isEqualTo(expectedMaze);
    }

    @Test
    @DisplayName("Render path in maze test")
    public void shouldReturnShortestCorrectPathInMaze() {
        Cell[][] grid = UtilsForTests.getGrid(5, 5);
        Maze maze = new Maze(5, 5, grid);
        List<Coordinate> path = new ArrayList<>(List.of(
            new Coordinate(1, 1),
            new Coordinate(1, 2),
            new Coordinate(1, 3)
        ));
        assertThat(renderer.render(maze, path)).isEqualTo(UtilsForTests.MAZE_WITH_PATH_FOR_TEST);
    }
}
