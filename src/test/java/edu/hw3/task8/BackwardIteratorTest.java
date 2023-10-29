package edu.hw3.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест обратного итератора")
class BackwardIteratorTest {
    @Test
    void testBackwardIterator() {
        //given
        List<Integer> iterableList = List.of(1, 2, 3);
        BackwardIterator<Integer> iterator = new BackwardIterator<>(iterableList);

        //when
        List<Integer> iterations = new ArrayList<>();
        while (iterator.hasNext()) {
            iterations.add(iterator.next());
        }

        //then
        assertThat(iterations).isEqualTo(List.of(3, 2, 1));
    }
}
