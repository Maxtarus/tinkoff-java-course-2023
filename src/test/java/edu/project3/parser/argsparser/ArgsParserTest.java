package edu.project3.parser.argsparser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ArgsParserTest {
    private static Stream<Arguments> commands() {
        return Stream.of(
            Arguments.of(
                "--path src/main/resources/project3/* --from 2023-09-27 --to 2023-10-07 --format markdown",
                new ParseFormat(
                    "src/main/resources/project3/*",
                    "2023-09-27",
                    "2023-10-07",
                    "markdown"
                )
            ),
            Arguments.of(
                "--path src/main/resources/project3/* --to 2023-10-07 --format markdown",
                new ParseFormat(
                    "src/main/resources/project3/*",
                    "",
                    "2023-10-07",
                    "markdown"
                )
            ),
            Arguments.of(
                "--path src/main/resources/project3/* --from 2023-09-27 --format markdown",
                new ParseFormat(
                    "src/main/resources/project3/*",
                    "2023-09-27",
                    "",
                    "markdown"
                )
            ),
            Arguments.of(
                "--path src/main/resources/project3/* --format markdown",
                new ParseFormat(
                    "src/main/resources/project3/*",
                    "",
                    "",
                    "markdown"
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("commands")
    @DisplayName("Correct commands test")
    public void getParseFormat_shouldCorrectlyParses_andReturnParseFormat(String command, ParseFormat expectedFormat) {
        assertThat(ArgsParser.getParseFormat(command)).isEqualTo(expectedFormat);
    }

    @Test
    @DisplayName("Command without --pass test")
    public void getParseFormat_shouldThrowException_whenStringDoesntContainPath() {
        assertThatThrownBy(() -> ArgsParser.getParseFormat("--from 2023-09-27 --format markdown")).isInstanceOf(
            IllegalArgumentException.class);
    }
}
