package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Тест сортировки животных по росту от самого маленького к самому большому")
class Task1Test {
    @Mock
    Animal a1, a2, a3, a4, a5;

    @Test
    void testGetAnimalsByHeightAscending() {
        MockitoAnnotations.openMocks(this);

        // given
        when(a1.height()).thenReturn(3);
        when(a2.height()).thenReturn(5);
        when(a3.height()).thenReturn(1);
        when(a4.height()).thenReturn(4);
        when(a5.height()).thenReturn(2);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5);
        List<Animal> expectedList = List.of(a3, a5, a1, a4, a2);

        // when
        List<Animal> actualList = AnimalUtils.getAnimalsByHeightAscending(animals);

        // then
        assertThat(actualList).isEqualTo(expectedList);
    }
}
