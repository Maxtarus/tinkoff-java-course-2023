package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
@DisplayName("Тест сортировки животных по весу от самого тяжелого к самому легкому c выбором k первых")
class Task2Test {
    @Mock
    Animal a1, a2, a3, a4, a5;

    List<Animal> animals;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        when(a1.weight()).thenReturn(3);
        when(a2.weight()).thenReturn(5);
        when(a3.weight()).thenReturn(1);
        when(a4.weight()).thenReturn(4);
        when(a5.weight()).thenReturn(2);

        animals = List.of(a1, a2, a3, a4, a5);
    }

    @Test
    void testGetTopKHeaviestAnimals_shouldReturnResult() {
        // given
        int k = 3;
        List<Animal> animals = List.of(a1, a2, a3, a4, a5);
        List<Animal> expectedList = List.of(a2, a4, a1);

        // when
        List<Animal> actualList = AnimalUtils.getTopKHeaviestAnimals(animals, k);

        // then
        assertThat(actualList).isEqualTo(expectedList);
    }

    @ParameterizedTest
    @ValueSource(ints = {-2, 0, 6})
    void testGetTopKHeaviestAnimals_shouldThrowIllegalArgumentException(int k) {
        assertThrows(IllegalArgumentException.class,
            () -> AnimalUtils.getKthOldestAnimal(animals, k));
    }
}

