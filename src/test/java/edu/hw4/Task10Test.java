package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Тест получения списка животных, возраст у которых не совпадает с количеством лап")
class Task10Test {
    @Mock
    Animal a1, a2, a3, a4, a5;

    @Test
    void testGetAnimalsWithMismatchedAgeAndPaws_shouldReturnNotEmptyList() {
        MockitoAnnotations.openMocks(this);

        // given
        when(a1.paws()).thenReturn(2);
        when(a2.paws()).thenReturn(4);
        when(a3.paws()).thenReturn(4);
        when(a4.paws()).thenReturn(0);
        when(a5.paws()).thenReturn(8);

        when(a1.age()).thenReturn(2);
        when(a2.age()).thenReturn(3);
        when(a3.age()).thenReturn(4);
        when(a4.age()).thenReturn(1);
        when(a5.age()).thenReturn(8);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5);
        List<Animal> expectedAnimalsWithMismatchedAgeAndPaws = List.of(a2, a4);

        // when
        List<Animal> actualAnimalsWithMismatchedAgeAndPaws = AnimalUtils.getAnimalsWithMismatchedAgeAndPaws(animals);

        // then
        assertThat(actualAnimalsWithMismatchedAgeAndPaws).isEqualTo(expectedAnimalsWithMismatchedAgeAndPaws);
    }

    @Test
    void testGetAnimalsWithMismatchedAgeAndPaws_shouldReturnEmptyList() {
        MockitoAnnotations.openMocks(this);

        // given
        when(a1.paws()).thenReturn(2);
        when(a2.paws()).thenReturn(4);
        when(a3.paws()).thenReturn(4);
        when(a4.paws()).thenReturn(0);
        when(a5.paws()).thenReturn(8);

        when(a1.age()).thenReturn(2);
        when(a2.age()).thenReturn(4);
        when(a3.age()).thenReturn(4);
        when(a4.age()).thenReturn(0);
        when(a5.age()).thenReturn(8);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5);

        // when
        List<Animal> actualAnimalsWithMismatchedAgeAndPaws = AnimalUtils.getAnimalsWithMismatchedAgeAndPaws(animals);

        // then
        assertThat(actualAnimalsWithMismatchedAgeAndPaws).isEqualTo(List.of());
    }
}
