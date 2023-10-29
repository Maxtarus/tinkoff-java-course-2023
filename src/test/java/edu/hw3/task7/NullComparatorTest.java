package edu.hw3.task7;

import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест комапаратора, который обрабатывает null")
class NullComparatorTest {
    @Test
    void testNullComparator() {
        // given
        Map<String, String> tree = new TreeMap<>(new NullComparator<>());

        //when
        tree.put(null, "test");

        //then
        assertThat(tree.containsKey(null)).isTrue();
    }
}
