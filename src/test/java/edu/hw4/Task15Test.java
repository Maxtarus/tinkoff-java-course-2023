package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayName("Тест нахождения суммарного веса животных каждого вида, которым от k до l лет")
class Task15Test {
    @Mock
    Animal a1, a2, a3, a4, a5, a6, a7;
    List<Animal> animals;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        when(a1.type()).thenReturn(Animal.Type.CAT);
        when(a2.type()).thenReturn(Animal.Type.CAT);
        when(a3.type()).thenReturn(Animal.Type.DOG);
        when(a4.type()).thenReturn(Animal.Type.DOG);
        when(a5.type()).thenReturn(Animal.Type.FISH);
        when(a6.type()).thenReturn(Animal.Type.FISH);
        when(a7.type()).thenReturn(Animal.Type.BIRD);

        when(a1.weight()).thenReturn(10);
        when(a2.weight()).thenReturn(10);
        when(a3.weight()).thenReturn(20);
        when(a4.weight()).thenReturn(20);
        when(a5.weight()).thenReturn(30);
        when(a6.weight()).thenReturn(30);
        when(a7.weight()).thenReturn(5);

        when(a1.age()).thenReturn(1);
        when(a2.age()).thenReturn(5);
        when(a3.age()).thenReturn(1);
        when(a4.age()).thenReturn(2);
        when(a5.age()).thenReturn(3);
        when(a6.age()).thenReturn(4);
        when(a6.age()).thenReturn(7);

        animals = List.of(a1, a2, a3, a4, a5, a6, a7);
    }

    @Test
    void testGetTotalAnimalsWeightByTypeWithAgeRange_shouldReturnResult() {
        // given
        int minAge = 1;
        int maxAge = 3;

        List<Animal> animals = List.of(a1, a2, a3, a4, a5, a6, a7);
        Map<Animal.Type, Integer> expectedTotalAnimalsWeightByTypeWithAgeRange = Map.of(
            Animal.Type.CAT, 10,
            Animal.Type.FISH, 30,
            Animal.Type.BIRD, 0,
            Animal.Type.DOG, 40,
            Animal.Type.SPIDER, 0
        );

        // when
        Map<Animal.Type, Integer> actualTotalAnimalsWeightByTypeWithAgeRange = AnimalUtils
            .getTotalAnimalsWeightByTypeWithAgeRange(animals, minAge, maxAge);

        // then
        assertThat(actualTotalAnimalsWeightByTypeWithAgeRange)
            .isEqualTo(expectedTotalAnimalsWeightByTypeWithAgeRange);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "-1, 3",
        "3, 3",
        "5, 3"
    })
    void testGetTotalAnimalsWeightByTypeWithAgeRange_shouldThrowIllegalArgumentException(
        int minAge, int maxAge) {
        assertThrows(
            IllegalArgumentException.class,
            () -> AnimalUtils.getTotalAnimalsWeightByTypeWithAgeRange(animals, minAge, maxAge));
    }
}
