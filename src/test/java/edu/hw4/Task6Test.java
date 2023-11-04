package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Тест нахождения самого тяжелого животного каждого вида")
class Task6Test {
    @Mock
    Animal a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;

    @Test
    void testGetHeaviestAnimalsByType() {
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

        when(a1.weight()).thenReturn(30);
        when(a2.weight()).thenReturn(20);
        when(a3.weight()).thenReturn(5);
        when(a4.weight()).thenReturn(1);
        when(a5.weight()).thenReturn(21);
        when(a6.weight()).thenReturn(37);
        when(a7.weight()).thenReturn(32);
        when(a8.weight()).thenReturn(10);
        when(a9.weight()).thenReturn(33);
        when(a10.weight()).thenReturn(22);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);

        Map<Animal.Type, Animal> expectedHeaviestAnimalsByType = new HashMap<>(
            Map.of(
                Animal.Type.CAT, a10,
                Animal.Type.DOG, a6,
                Animal.Type.FISH, a8,
                Animal.Type.SPIDER, a4
            )
        );

        expectedHeaviestAnimalsByType.put(Animal.Type.BIRD, null);

        // when
        var actualHeaviestAnimalsByType = AnimalUtils.getHeaviestAnimalsByType(animals);

        // Assert
        assertThat(actualHeaviestAnimalsByType).isEqualTo(expectedHeaviestAnimalsByType);
    }
}
