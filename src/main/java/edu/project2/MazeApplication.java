package edu.project2;


import edu.project2.generator.DfsMazeGenerator;
import edu.project2.generator.Generator;
import edu.project2.generator.PrimsMazeGenerator;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.printer.MazePrinter;
import edu.project2.printer.Renderer;
import edu.project2.solver.BfsMazeSolver;
import edu.project2.solver.DfsMazeSolver;
import edu.project2.solver.Solver;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import static java.lang.System.out;

public class MazeApplication {
    private static final int MIN_MAZE_SIZE = 5;
    private static final int MAX_MAZE_SIZE = 41;
    private static final String ERROR_INPUT_MESSAGE = "Wrong algorithm number";
    private static final Logger LOGGER = Logger.getLogger(MazeApplication.class.getName());

    private final Scanner scanner;
    private final Generator mazeGenerator;
    private final Renderer mazeRenderer;
    private final int height;
    private final int width;

    public MazeApplication() {
        out.println("Hello and welcome to Maze Application!");
        scanner = new Scanner(System.in);
        width = setMazeWidth();
        height = setMazeHeight();
        mazeGenerator = setMazeGenerator(height, width);
        mazeRenderer = new MazePrinter();
    }

    public void run() {
        Maze maze = mazeGenerator.generate(height, width);
        out.println("Generated maze:\n" + mazeRenderer.render(maze));
        out.println("Input start of path coordinates");
        Coordinate start = setCoordinate();
        out.println("Input end of path coordinates");
        Coordinate end = setCoordinate();
        Solver mazeSolver = setMazeSolver(maze);
        List<Coordinate> path = mazeSolver.solve(maze, start, end);
        out.println("Shortest path from start to end:\n" + mazeRenderer.render(maze, path));
    }

    private Solver setMazeSolver(Maze maze) {
        out.println("Choose algorithm for finding path in maze: 1) BFS or 2) DFS");
        int choose = scanner.nextInt();
        while (choose < 1 || choose > 2) {
            LOGGER.info(ERROR_INPUT_MESSAGE);
            choose = scanner.nextInt();
        }
        if (choose == 1) {
            return new BfsMazeSolver(maze);
        } else {
            return new DfsMazeSolver(maze);
        }
    }

    private Coordinate setCoordinate() {
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        return new Coordinate(x, y);
    }

    private Generator setMazeGenerator(int height, int width) {
        out.println("Choose algorithm for maze generation: 1) Prim's or 2) DFS");
        int choose = scanner.nextInt();
        while (choose < 1 || choose > 2) {
            LOGGER.info(ERROR_INPUT_MESSAGE);
            choose = scanner.nextInt();
        }
        if (choose == 1) {
            return new PrimsMazeGenerator(height, width);
        } else {
            return new DfsMazeGenerator(height, width);
        }
    }

    private int setMazeWidth() {
        out.println("Choose odd width of maze from 5 to 41");
        int mazeWidth = scanner.nextInt();
        while (isNotValidMazeSize(mazeWidth)) {
            LOGGER.info("Wrong maze width");
            mazeWidth = scanner.nextInt();
        }
        return mazeWidth;
    }

    private int setMazeHeight() {
        out.println("Choose odd height of maze from 5 to 41");
        int mazeHeight = scanner.nextInt();
        while (isNotValidMazeSize(mazeHeight)) {
            LOGGER.info("Wrong maze height");
            mazeHeight = scanner.nextInt();
        }
        return mazeHeight;
    }

    private boolean isNotValidMazeSize(int size) {
        return size % 2 == 0 || size < MIN_MAZE_SIZE || size > MAX_MAZE_SIZE;
    }
}
