package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LogParserApplicationTest {
    private final String[] args = {"--path", "src/main/resources/project3/log1.txt", "--format", "adoc"};

    @Test
    @DisplayName("Application start test")
    public void run_shouldNotThrowAnyException() {
        LogParserApplication logParserApplication = new LogParserApplication();
        assertDoesNotThrow(() -> logParserApplication.run(args));
    }
}
