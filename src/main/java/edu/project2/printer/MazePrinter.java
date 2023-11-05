package edu.project2.printer;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.List;

public class MazePrinter implements Renderer {
    @Override
    public String render(Maze maze) {
        StringBuilder renderedMaze = new StringBuilder();
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                if (maze.grid()[i][j].getType() == Cell.Type.WALL) {
                    renderedMaze.append(WHITE_BACKGROUND_BRIGHT + '\t' + RESET);
                } else {
                    renderedMaze.append(BLACK_BACKGROUND_BRIGHT + '\t' + RESET);
                }
            }
            renderedMaze.append('\n');
        }
        return renderedMaze.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder renderedMaze = new StringBuilder();
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                if (path.contains(new Coordinate(j, i))) {
                    renderedMaze.append(GREEN_BACKGROUND_BRIGHT + '\t' + RESET);
                } else if (maze.grid()[i][j].getType() == Cell.Type.WALL) {
                    renderedMaze.append(WHITE_BACKGROUND_BRIGHT + '\t' + RESET);
                } else {
                    renderedMaze.append(BLACK_BACKGROUND_BRIGHT + '\t' + RESET);
                }
            }
            renderedMaze.append('\n');
        }
        return renderedMaze.toString();
    }
}
