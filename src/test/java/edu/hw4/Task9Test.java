package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Тест нахождения суммарного количества лап у животных в списке")
class Task9Test {
    @Mock
    Animal a1, a2, a3, a4, a5;

    @Test
    void testGetTotalAnimalsPaws() {
        MockitoAnnotations.openMocks(this);

        //given
        when(a1.paws()).thenReturn(2); // BIRD
        when(a2.paws()).thenReturn(4); // CAT
        when(a3.paws()).thenReturn(4); // DOG
        when(a4.paws()).thenReturn(0); // FISH
        when(a5.paws()).thenReturn(8); // SPIDER

        List<Animal> animals = List.of(a1, a2, a3, a4, a5);
        int expectedTotalAnimalsPaws = 18;

        // when
        int actualTotalAnimalsPaws = AnimalUtils.getTotalAnimalsPaws(animals);

        // then
        assertThat(actualTotalAnimalsPaws).isEqualTo(expectedTotalAnimalsPaws);
    }
}
