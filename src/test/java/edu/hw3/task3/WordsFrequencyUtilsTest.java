package edu.hw3.task3;

import edu.hw3.task2.BracketsClusteringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тест частотного словаря")
class WordsFrequencyUtilsTest {
    @ParameterizedTest
    @ArgumentsSource(ObjectListProvider.class)
    @DisplayName("В списке все объекты одного типа")
    void freqDict_shouldReturnFrequencyDictionary_whenAllObjectsAreSameType(List<Object> objects) {
        //given
        Map<Object, Integer> expectedFrequencyDictionary = Map.of(
            objects.get(0), 1,
            objects.get(1), 3,
            objects.get(3), 2,
            objects.get(6), 1
        );

        //when
        Map<Object, Integer> actualFrequencyDictionary = WordsFrequencyUtils.freqDict(objects);

        //then
        assertThat(actualFrequencyDictionary).isEqualTo(expectedFrequencyDictionary);
    }

    @Test
    @DisplayName("В списке не все объекты одного типа")
    void freqDict_shouldThrowIllegalArgumentException_whenNotAllObjectsAreSameType() {
        //given
        List<Object> objects = List.of("abc", "bac", 1, "cab", "bac", "cab", "dba");

        //then
        assertThrows(IllegalArgumentException.class, () -> WordsFrequencyUtils.freqDict(objects));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустой или null-список")
    void freqDict_shouldThrowIllegalArgumentException_whenNullOrEmptyList(List<Object> objects) {
        assertThrows(IllegalArgumentException.class, () -> WordsFrequencyUtils.freqDict(objects));
    }

    static class ObjectListProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of(List.of("abc", "bac", "bac", "cab", "bac", "cab", "dba")),
                Arguments.of(List.of('a', 'b', 'b', 'c', 'b', 'c', 'd')),
                Arguments.of(List.of(10, 2, 2, 6, 2, 6, 4)),
                Arguments.of(List.of(10L, 2L, 2L, 6L, 2L, 6L, 4L)),
                Arguments.of(List.of(10.0, 2.0, 2.0, 6.0, 2.0, 6.0, 4.0))
            );
        }
    }


}
