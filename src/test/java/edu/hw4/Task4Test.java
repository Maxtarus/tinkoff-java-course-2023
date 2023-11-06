package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Тест получения животного с самым длинным именем")
class Task4Test {
    @Mock
    Animal a1, a2, a3;

    @Test
    void testGetAnimalWithLongestName_shouldReturnAnimal() {
        MockitoAnnotations.openMocks(this);

        // given
        when(a1.name()).thenReturn("short name");
        when(a2.name()).thenReturn("usual name");
        when(a3.name()).thenReturn("the longest name");

        List<Animal> animals = List.of(a1, a2, a3);
        Animal expectedAnimal = a3;

        // when
        Animal actualAnimal = AnimalUtils.getAnimalWithLongestName(animals);

        // then
        assertThat(actualAnimal).isEqualTo(expectedAnimal);
    }
}
