package edu.hw3.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тест кластеризации скобок")
class BracketsClusteringUtilsTest {
    @Test
    @DisplayName("Одиночные скобки")
    void clusterize_shouldGetClusters_whenSingleBrackets() {
        //given
        String bracketString = "(){}[]";
        List<String> expectedClusters = List.of("()", "{}", "[]");

        //when
        List<String> actualClusters = BracketsClusteringUtils.clusterize(bracketString);

        //then
        assertThat(actualClusters).isEqualTo(expectedClusters);
    }

    @Test
    @DisplayName("Вложенные скобки")
    void clusterize_shouldGetClusters_whenNestedBrackets() {
        //given
        String bracketString = "({[]})([{}]){([])}{[()]}[({})][{()}]";
        List<String> expectedClusters = List.of("({[]})", "([{}])",
                                                "{([])}", "{[()]}",
                                                "[({})]", "[{()}]");

        //when
        List<String> actualClusters = BracketsClusteringUtils.clusterize(bracketString);

        //then
        assertThat(actualClusters).isEqualTo(expectedClusters);
    }

    @Test
    @DisplayName("Несколько скобок внутри вложенных скобок")
    void clusterize_shouldGetClusters_whenSeveralBracketsInNestedBrackets() {
        //given
        String bracketString = "({}[])([]{}()){()[]}{[](){}}[(){}][[]{}()]";
        List<String> expectedClusters = List.of("({}[])", "([]{}())",
                                                "{()[]}", "{[](){}}",
                                                "[(){}]", "[[]{}()]");

        //when
        List<String> actualClusters = BracketsClusteringUtils.clusterize(bracketString);

        //then
        assertThat(actualClusters).isEqualTo(expectedClusters);
    }

    @Test
    @DisplayName("Скобки с разными комбинациями")
    void clusterize_shouldGetClusters_whenBracketsInDifferentCombinations() {
        //given
        String bracketString = "[(){()[]{}}][]{[()]}{}([{[()[]]({}[])}[(){}]])[]";
        List<String> expectedClusters = List.of("[(){()[]{}}]", "[]",
                                                "{[()]}", "{}",
                                                "([{[()[]]({}[])}[(){}]])", "[]");

        //when
        List<String> actualClusters = BracketsClusteringUtils.clusterize(bracketString);

        //then
        assertThat(actualClusters).isEqualTo(expectedClusters);
    }
    @ParameterizedTest
    @ValueSource(strings = {"((((", "))))", "()))", ")((("})
    @DisplayName("Несбалансированные скобок")
    void clusterize_shouldThrowIllegalArgumentException_whenNotBalancedBrackets(String bracketString) {
        assertThrows(IllegalArgumentException.class, () -> BracketsClusteringUtils.clusterize(bracketString));
    }

    @ParameterizedTest
    @ValueSource(strings = {"(()", ")((", "1%Sа", "()[]=", " "})
    @DisplayName("Некорректная строка")
    void clusterize_shouldThrowIllegalArgumentException_whenNotValidString(String string) {
        assertThrows(IllegalArgumentException.class, () -> BracketsClusteringUtils.clusterize(string));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустая или null-строка")
    void clusterize_shouldThrowIllegalArgumentException_whenNullOrEmptyString(String string) {
        assertThrows(IllegalArgumentException.class, () -> BracketsClusteringUtils.clusterize(string));
    }
}
