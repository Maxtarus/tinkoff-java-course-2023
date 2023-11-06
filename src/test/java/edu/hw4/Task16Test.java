package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Тест сортировки списка животных по виду, затем по полу, затем по имени")
class Task16Test {
    @Mock
    Animal a1, a2, a3, a4, a5, a6, a7, a8, a9;
    @Test
    void testGetAnimalsSortedByTypeThenSexThenNameAscending() {
        MockitoAnnotations.openMocks(this);

        // given
        when(a1.type()).thenReturn(Animal.Type.DOG);
        when(a2.type()).thenReturn(Animal.Type.FISH);
        when(a3.type()).thenReturn(Animal.Type.CAT);
        when(a4.type()).thenReturn(Animal.Type.FISH);
        when(a5.type()).thenReturn(Animal.Type.CAT);
        when(a6.type()).thenReturn(Animal.Type.DOG);
        when(a7.type()).thenReturn(Animal.Type.FISH);
        when(a8.type()).thenReturn(Animal.Type.FISH);
        when(a9.type()).thenReturn(Animal.Type.SPIDER);

        when(a1.sex()).thenReturn(Animal.Sex.M);
        when(a4.sex()).thenReturn(Animal.Sex.F);
        when(a2.sex()).thenReturn(Animal.Sex.F);
        when(a6.sex()).thenReturn(Animal.Sex.F);
        when(a3.sex()).thenReturn(Animal.Sex.M);
        when(a8.sex()).thenReturn(Animal.Sex.F);
        when(a5.sex()).thenReturn(Animal.Sex.M);
        when(a7.sex()).thenReturn(Animal.Sex.F);
        when(a9.sex()).thenReturn(Animal.Sex.F);

        when(a1.name()).thenReturn("Retriever");
        when(a2.name()).thenReturn("Goldfish");
        when(a3.name()).thenReturn("Siamese");
        when(a4.name()).thenReturn("Clownfish");
        when(a5.name()).thenReturn("Persian");
        when(a6.name()).thenReturn("Shepherd");
        when(a7.name()).thenReturn("Catfish");
        when(a8.name()).thenReturn("Tuna");
        when(a9.name()).thenReturn("Tarantula");

        List<Animal> animals = List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9);
        List<Animal> expectedSortedAnimals = List.of(a5, a3, a1, a6, a7, a4, a2, a8, a9);

        // when
        List<Animal> actualSortedAnimals = AnimalUtils
            .getAnimalsSortedByTypeThenSexThenNameAscending(animals);

        // then
        assertThat(actualSortedAnimals).isEqualTo(expectedSortedAnimals);
    }
}
