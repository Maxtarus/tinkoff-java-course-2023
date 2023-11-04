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

@DisplayName("Тест нахождения K-го самого старого животного")
class Task7Test {
    @Mock
    Animal a1, a2, a3, a4, a5;

    List<Animal> animals;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        when(a1.age()).thenReturn(3);
        when(a2.age()).thenReturn(5);
        when(a3.age()).thenReturn(1);
        when(a4.age()).thenReturn(4);
        when(a5.age()).thenReturn(2);

        animals = List.of(a1, a2, a3, a4, a5);
    }

    @Test
    void testGetKthOldestAnimal_shouldReturnResult() {
        //given
        int k = 3;
        Animal expectedKthOldestAnimal = a1;

        // when
        Animal actualKthOldestAnimal = AnimalUtils.getKthOldestAnimal(animals, k);

        //then
        assertThat(actualKthOldestAnimal).isEqualTo(expectedKthOldestAnimal);
    }

    @ParameterizedTest
    @ValueSource(ints = {-2, 0, 6})
    void testGetKthOldestAnimal_shouldThrowIllegalArgumentException(int k) {
        assertThrows(IllegalArgumentException.class,
            () -> AnimalUtils.getKthOldestAnimal(animals, k));
    }
}
