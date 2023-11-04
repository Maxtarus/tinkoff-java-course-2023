package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Тест нахождения количества животных каждого вида")
class Task3Test {
    @Mock
    Animal a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;

    @Test
    void testGetAnimalAmountByType() {
        MockitoAnnotations.openMocks(this);

        // given
        when(a1.type()).thenReturn(Animal.Type.DOG);
        when(a2.type()).thenReturn(Animal.Type.CAT);
        when(a3.type()).thenReturn(Animal.Type.FISH);
        when(a4.type()).thenReturn(Animal.Type.SPIDER);
        when(a5.type()).thenReturn(Animal.Type.CAT);
        when(a6.type()).thenReturn(Animal.Type.DOG);
        when(a7.type()).thenReturn(Animal.Type.DOG);
        when(a8.type()).thenReturn(Animal.Type.FISH);
        when(a9.type()).thenReturn(Animal.Type.DOG);
        when(a10.type()).thenReturn(Animal.Type.CAT);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);

        Map<Animal.Type, Integer> expectedAnimalAmountByType = Map.of(
            Animal.Type.CAT, 3,
            Animal.Type.DOG, 4,
            Animal.Type.BIRD, 0,
            Animal.Type.FISH, 2,
            Animal.Type.SPIDER, 1
        );

        // when
        var actualAnimalAmountByType = AnimalUtils.getAnimalAmountByType(animals);

        // then
        assertThat(actualAnimalAmountByType).isEqualTo(expectedAnimalAmountByType);
    }
}
