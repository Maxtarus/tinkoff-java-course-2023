package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Тест, проверяющий, что пауки кусаются чаще, чем собаки")
class Task17Test {
    @Mock
    Animal a1, a2, a3, a4, a5, a6;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoSpidersBiteMoreOftenThanDogs_shouldReturnTrue() {
        // given
        when(a1.type()).thenReturn(Animal.Type.DOG);
        when(a2.type()).thenReturn(Animal.Type.DOG);
        when(a3.type()).thenReturn(Animal.Type.DOG);
        when(a4.type()).thenReturn(Animal.Type.SPIDER);
        when(a5.type()).thenReturn(Animal.Type.SPIDER);
        when(a6.type()).thenReturn(Animal.Type.SPIDER);

        when(a1.bites()).thenReturn(true);
        when(a2.bites()).thenReturn(false);
        when(a3.bites()).thenReturn(false);
        when(a4.bites()).thenReturn(true);
        when(a5.bites()).thenReturn(true);
        when(a6.bites()).thenReturn(false);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5, a6);

        // when
        boolean doSpidersBiteMoreOftenThanDogs = AnimalUtils.doSpidersBiteMoreOftenThanDogs(animals);

        // then
        assertThat(doSpidersBiteMoreOftenThanDogs).isTrue();
    }

    @Test
    void testDoSpidersBiteMoreOftenThanDogs_shouldReturnFalse_whenEnoughData() {
        // given
        when(a1.type()).thenReturn(Animal.Type.DOG);
        when(a2.type()).thenReturn(Animal.Type.DOG);
        when(a3.type()).thenReturn(Animal.Type.DOG);
        when(a4.type()).thenReturn(Animal.Type.SPIDER);
        when(a5.type()).thenReturn(Animal.Type.SPIDER);
        when(a6.type()).thenReturn(Animal.Type.SPIDER);

        when(a1.bites()).thenReturn(true);
        when(a2.bites()).thenReturn(true);
        when(a3.bites()).thenReturn(false);
        when(a4.bites()).thenReturn(true);
        when(a5.bites()).thenReturn(true);
        when(a6.bites()).thenReturn(false);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5, a6);

        // when
        boolean doSpidersBiteMoreOftenThanDogs = AnimalUtils.doSpidersBiteMoreOftenThanDogs(animals);

        // then
        assertThat(doSpidersBiteMoreOftenThanDogs).isFalse();
    }

    @Test
    void testDoSpidersBiteMoreOftenThanDogs_shouldReturnFalse_whenNoDogs() {
        // given
        when(a1.type()).thenReturn(Animal.Type.SPIDER);
        when(a2.type()).thenReturn(Animal.Type.CAT);
        when(a3.type()).thenReturn(Animal.Type.FISH);
        when(a4.type()).thenReturn(Animal.Type.BIRD);
        when(a5.type()).thenReturn(Animal.Type.CAT);
        when(a6.type()).thenReturn(Animal.Type.SPIDER);

        when(a1.bites()).thenReturn(true);
        when(a2.bites()).thenReturn(true);
        when(a3.bites()).thenReturn(false);
        when(a4.bites()).thenReturn(true);
        when(a5.bites()).thenReturn(true);
        when(a6.bites()).thenReturn(false);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5, a6);

        // when
        boolean doSpidersBiteMoreOftenThanDogs = AnimalUtils.doSpidersBiteMoreOftenThanDogs(animals);

        // then
        assertThat(doSpidersBiteMoreOftenThanDogs).isFalse();
    }

    @Test
    void testDoSpidersBiteMoreOftenThanDogs_shouldReturnFalse_whenNoSpiders() {
        // given
        when(a1.type()).thenReturn(Animal.Type.DOG);
        when(a2.type()).thenReturn(Animal.Type.CAT);
        when(a3.type()).thenReturn(Animal.Type.FISH);
        when(a4.type()).thenReturn(Animal.Type.BIRD);
        when(a5.type()).thenReturn(Animal.Type.CAT);
        when(a6.type()).thenReturn(Animal.Type.DOG);

        when(a1.bites()).thenReturn(true);
        when(a2.bites()).thenReturn(true);
        when(a3.bites()).thenReturn(false);
        when(a4.bites()).thenReturn(true);
        when(a5.bites()).thenReturn(true);
        when(a6.bites()).thenReturn(false);

        List<Animal> animals = List.of(a1, a2, a3, a4, a5, a6);

        // when
        boolean doSpidersBiteMoreOftenThanDogs = AnimalUtils.doSpidersBiteMoreOftenThanDogs(animals);

        // then
        assertThat(doSpidersBiteMoreOftenThanDogs).isFalse();
    }
}
