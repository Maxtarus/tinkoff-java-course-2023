package edu.project2.printer;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.List;

public interface Renderer {
    String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";
    String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";
    String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";
    String RESET = "\u001B[0m";

    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path);
}
