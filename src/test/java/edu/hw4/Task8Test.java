package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Тест нахождения самого тяжелого животного среди животных ниже k см")
class Task8Test {
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

        when(a1.height()).thenReturn(30);
        when(a2.height()).thenReturn(50);
        when(a3.height()).thenReturn(10);
        when(a4.height()).thenReturn(40);
        when(a5.height()).thenReturn(20);

        animals = List.of(a1, a2, a3, a4, a5);
    }

    @Test
    void testGetHeaviestAnimalBelowHeight_whenAnimalPresent() {
        // given
        int height = 35;
        Animal expectedHeaviestAnimalBelowHeight = a1;

        // when
        Optional<Animal> actualHeaviestAnimalBelowHeight = AnimalUtils.getHeaviestAnimalBelowHeight(animals, height);

        // then
        assertThat(Optional.of(actualHeaviestAnimalBelowHeight))
            .hasValue(Optional.of(expectedHeaviestAnimalBelowHeight));
    }

    @Test
    void testGetHeaviestAnimalBelowHeight_whenNoAnimalPresent() {
        // given
        int height = 10;

        // when
        Optional<Animal> actualHeaviestAnimalBelowHeight = AnimalUtils.getHeaviestAnimalBelowHeight(animals, height);

        // then
        assertThat(actualHeaviestAnimalBelowHeight).isEmpty();
    }
}
