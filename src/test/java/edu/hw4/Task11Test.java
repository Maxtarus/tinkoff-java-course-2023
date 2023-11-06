package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayName("Тест получения списка животных, которые могут укусить и рост которых превышает k см")
class Task11Test {
    @Mock
    Animal a1, a2, a3, a4, a5;
    List<Animal> animals;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        when(a1.bites()).thenReturn(true);
        when(a2.bites()).thenReturn(true);
        when(a3.bites()).thenReturn(true);
        when(a4.bites()).thenReturn(false);
        when(a5.bites()).thenReturn(false);

        when(a1.height()).thenReturn(110);
        when(a2.height()).thenReturn(40);
        when(a3.height()).thenReturn(130);
        when(a4.height()).thenReturn(120);
        when(a5.height()).thenReturn(50);

        animals = List.of(a1, a2, a3, a4, a5);
    }

    @Test
    void testGetBitingAnimalsWithHeightOverHeight_shouldReturnNotEmptyList() {
        //given
        int height = 100;
        List<Animal> expectedBitingAnimalsWithHeightOverHeight = List.of(a1, a3);

        // when
        List<Animal> actualBitingAnimalsWithHeightOverHeight = AnimalUtils
            .getBitingAnimalsWithHeightOverHeight(animals, height);

        // then
        assertThat(actualBitingAnimalsWithHeightOverHeight)
            .isEqualTo(expectedBitingAnimalsWithHeightOverHeight);
    }

    @Test
    void testGetBitingAnimalsWithHeightOverHeight_shouldReturnEmptyList() {
        //given
        int height = 150;

        // when
        List<Animal> actualBitingAnimalsWithHeightOverHeight = AnimalUtils
            .getBitingAnimalsWithHeightOverHeight(animals, height);

        // then
        assertThat(actualBitingAnimalsWithHeightOverHeight).isEqualTo(List.of());
    }

    @ParameterizedTest
    @ValueSource(ints = {-50, 0})
    void testGetBitingAnimalsWithHeightOverHeight_shouldThrowIllegalArgumentException(int height) {
        assertThrows(
            IllegalArgumentException.class,
            () -> AnimalUtils.getBitingAnimalsWithHeightOverHeight(animals, height));
    }
}
