package edu.hw4;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class Task18Test {
    @Mock
    Animal a1, a2, a3, a4, a5, a6;

    @Test
    void testGetHeaviestFishInLists_shouldReturnFish() {
        MockitoAnnotations.openMocks(this);

        // given
        when(a1.type()).thenReturn(Animal.Type.FISH);
        when(a2.type()).thenReturn(Animal.Type.DOG);
        when(a3.type()).thenReturn(Animal.Type.FISH);
        when(a4.type()).thenReturn(Animal.Type.FISH);
        when(a5.type()).thenReturn(Animal.Type.CAT);
        when(a6.type()).thenReturn(Animal.Type.FISH);

        when(a1.weight()).thenReturn(4);
        when(a2.weight()).thenReturn(6);
        when(a3.weight()).thenReturn(5);
        when(a4.weight()).thenReturn(1);
        when(a5.weight()).thenReturn(2);
        when(a6.weight()).thenReturn(3);

        List<Animal> animals1 = List.of(a1, a2, a3, a4);
        List<Animal> animals2 = List.of(a5, a6);
        Animal expectedFish = a3;

        // when
        Animal actualFish = AnimalUtils.getHeaviestFishInLists(animals1, animals2);

        // then
        assertThat(actualFish).isEqualTo(expectedFish);
    }

    @Test
    void testGetHeaviestFishInLists_shouldThrowNoSuchElementException() {
        MockitoAnnotations.openMocks(this);

        // given
        when(a1.type()).thenReturn(Animal.Type.BIRD);
        when(a2.type()).thenReturn(Animal.Type.DOG);
        when(a3.type()).thenReturn(Animal.Type.SPIDER);
        when(a4.type()).thenReturn(Animal.Type.CAT);
        when(a5.type()).thenReturn(Animal.Type.CAT);
        when(a6.type()).thenReturn(Animal.Type.BIRD);

        when(a1.weight()).thenReturn(4);
        when(a2.weight()).thenReturn(6);
        when(a3.weight()).thenReturn(4);
        when(a4.weight()).thenReturn(4);
        when(a5.weight()).thenReturn(2);
        when(a6.weight()).thenReturn(4);

        List<Animal> animals1 = List.of(a1, a2, a3, a4);
        List<Animal> animals2 = List.of(a5, a6);

        // then
        assertThrows(
            NoSuchElementException.class,
            () -> AnimalUtils.getHeaviestFishInLists(animals1, animals2));
    }
}
