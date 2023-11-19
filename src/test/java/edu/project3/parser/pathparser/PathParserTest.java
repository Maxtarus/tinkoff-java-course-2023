package edu.project3.parser.pathparser;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import static edu.project3.parser.pathparser.PathParser.getPaths;
import static org.assertj.core.api.Assertions.assertThat;

public class PathParserTest {
    private static Stream<Arguments> paths() {
        Path firstLog = Path.of("src/main/resources/project3/log1.txt");
        Path secondLog = Path.of("src/main/resources/project3/log2.txt");
        return Stream.of(
            Arguments.of(
                "src/main/resources/project3/**",
                List.of(
                    firstLog,
                    secondLog,
                    Path.of("src/main/resources/project3/dir/log3.txt")
                )
            ),
            Arguments.of(
                "src/main/resources/project3/*",
                List.of(
                    firstLog,
                    secondLog
                )
            ),
            Arguments.of(
                "src/main/resources/project3/log1.txt",
                List.of(
                    firstLog
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paths")
    @DisplayName("Parsing path input test")
    public void getPaths_shouldReturnListOfPaths(String path, List<Path> expectedPaths) {
        assertThat(getPaths(path)).containsAll(expectedPaths);
    }
}
