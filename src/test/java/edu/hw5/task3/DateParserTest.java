package edu.hw5.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class DateParserTest {
    private static Stream<Arguments> getDifferentDateFormats() {
        return Stream.of(
            Arguments.of("2020-08-08", LocalDate.of(2020, 8, 8)),
            Arguments.of("2020-1-1", LocalDate.of(2020, 1, 1)),
            Arguments.of("01/03/1976", LocalDate.of(1976, 3, 1)),
            Arguments.of("1/3/20", LocalDate.of(2020, 3, 1)),
            Arguments.of("tomorrow", LocalDate.now().plusDays(1)),
            Arguments.of("today", LocalDate.now()),
            Arguments.of("yesterday", LocalDate.now().minusDays(1)),
            Arguments.of("1 day ago", LocalDate.now().minusDays(1)),
            Arguments.of("2234 days ago", LocalDate.now().minusDays(2234))
        );
    }

    @ParameterizedTest
    @MethodSource("getDifferentDateFormats")
    @DisplayName("Различные верные форматы дат")
    public void parseDate_shouldReturnCorrectParsedDate(String givenString, LocalDate expectedDate) {
        Optional<LocalDate> actualDate = DateParser.parseDate(givenString);
        assertThat(Optional.of(actualDate)).hasValue(Optional.of(expectedDate));
    }

    @Test
    @DisplayName("Неправильный формат входной строки")
    public void parseDate_shouldReturnEmptyOptional_whenStringHasWrongFormat() {
        Optional<LocalDate> date = DateParser.parseDate("2022/10-10");
        assertThat(date.isEmpty()).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Входная строка null или пуста")
    public void parseDate_shouldThrowExceptionWhenStringIsNullOrEmpty(String givenString) {
        Optional<LocalDate> date = DateParser.parseDate(givenString);
        assertThat(date.isEmpty()).isTrue();
    }
}
