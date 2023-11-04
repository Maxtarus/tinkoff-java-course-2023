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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DisplayName("Тест проверки, есть ли в списке собака ростом более k см")
class Task14Test {
    @Mock
    Animal a1, a2, a3;
    List<Animal> animals;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        when(a1.type()).thenReturn(Animal.Type.BIRD);
        when(a2.type()).thenReturn(Animal.Type.SPIDER);
        when(a3.type()).thenReturn(Animal.Type.DOG);

        when(a1.height()).thenReturn(120);
        when(a2.height()).thenReturn(10);
        when(a3.height()).thenReturn(140);

        animals = List.of(a1, a2, a3);
    }

    @Test
    void testHasDogTallerThanHeight_shouldReturnTrue() {
        // given
        int height = 100;

        // when
        boolean hasDogTallerThanHeight = AnimalUtils.hasDogTallerThanHeight(animals, height);

        // then
        assertThat(hasDogTallerThanHeight).isTrue();
    }

    @Test
    void testHasDogTallerThanHeight_shouldReturnFalse() {
        // given
        int height = 145;

        // when
        boolean hasDogTallerThanHeight = AnimalUtils.hasDogTallerThanHeight(animals, height);

        // then
        assertThat(hasDogTallerThanHeight).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {-50, 0})
    void testHasDogTallerThanHeight_shouldThrowIllegalArgumentException(int height) {
        assertThrows(
            IllegalArgumentException.class,
            () -> AnimalUtils.hasDogTallerThanHeight(animals, height));
    }
}
