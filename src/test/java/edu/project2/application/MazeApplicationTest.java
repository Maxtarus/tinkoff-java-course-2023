package edu.project2.application;

import edu.project2.MazeApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MazeApplicationTest {
    private List<LogRecord> logs;
    private ByteArrayInputStream in;
    private InputStream originalInputStream;

    @BeforeEach
    public void setUp() {
        logs = new ArrayList<>();
        originalInputStream = System.in;
    }

    @AfterEach
    public void restoreSystemInput() {
        System.setIn(originalInputStream);
    }

    @Test
    @DisplayName("Starting program without incorrect inputs test")
    public void shouldNotThrowAnyExceptionAndLogAnything() {
        Logger logger = Logger.getLogger(MazeApplication.class.getName());
        in = new ByteArrayInputStream("5\n5\n1\n1 1\n3 3\n1".getBytes());
        System.setIn(in);
        MazeApplication application = new MazeApplication();
        logger.addHandler(getNewHandler());
        assertAll(
            ()-> assertDoesNotThrow(application::run),
            () -> assertEquals(0, logs.size())
        );
    }

    @Test
    @DisplayName("Starting program with incorrect integer algorithm numbers inputs test")
    public void shouldLogWrongAlgorithmNumberInputButDoNotThrowException() {
        logs = new ArrayList<>();
        Logger logger = Logger.getLogger(MazeApplication.class.getName());
        logger.addHandler(getNewHandler());
        in = new ByteArrayInputStream("5\n5\n3\n1\n1 1\n3 3\n0\n1".getBytes());
        System.setIn(in);
        MazeApplication application = new MazeApplication();
        assertAll(
            ()-> assertDoesNotThrow(application::run),
            () -> assertEquals(2, logs.size()),
            () -> assertEquals("Wrong algorithm number", logs.get(0).getMessage())
        );
    }

    @Test
    @DisplayName("Starting program with incorrect integer maze size inputs test")
    public void shouldLogWrongMazeSizeInputButDoNotThrowException() {
        logs = new ArrayList<>();
        Logger logger = Logger.getLogger(MazeApplication.class.getName());
        logger.addHandler(getNewHandler());
        in = new ByteArrayInputStream("6\n5\n43\n5\n2\n1 1\n3 3\n2".getBytes());
        System.setIn(in);
        MazeApplication application = new MazeApplication();
        assertAll(
            ()-> assertDoesNotThrow(application::run),
            () -> assertEquals(2, logs.size()),
            () -> assertEquals("Wrong maze width", logs.get(0).getMessage()),
            () -> assertEquals("Wrong maze height", logs.get(1).getMessage())
        );
    }

    private Handler getNewHandler(){
        return new Handler() {
            @Override
            public void publish(LogRecord record) {
                logs.add(record);
            }

            @Override
            public void flush() {
            }

            @Override
            public void close() throws SecurityException {
            }
        };
    }
}
