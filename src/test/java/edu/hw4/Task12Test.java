package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Тест нахождения количества животных, вес которых превышает рост")
class Task12Test {
    @Mock
    Animal a1, a2, a3, a4, a5;
    @Test
    void testGetAnimalsAmountWithWeightOverHeight() {
        MockitoAnnotations.openMocks(this);

        // given
        when(a1.height()).thenReturn(1);
        when(a2.height()).thenReturn(2);
        when(a3.height()).thenReturn(3);
        when(a4.height()).thenReturn(4);
        when(a4.height()).thenReturn(5);

        when(a1.weight()).thenReturn(5);
        when(a2.weight()).thenReturn(1);
        when(a3.weight()).thenReturn(3);
        when(a4.weight()).thenReturn(6);
        when(a5.weight()).thenReturn(7);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5);
        int expectedAmountWithWeightOverHeight = 3;

        // when
        int actualAmountWithWeightOverHeight = AnimalUtils.getAnimalsAmountWithWeightOverHeight(animals);

        // then
        assertThat(actualAmountWithWeightOverHeight).isEqualTo(expectedAmountWithWeightOverHeight);
    }
}
