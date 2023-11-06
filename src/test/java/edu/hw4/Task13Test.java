package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Тест нахождения списка животных, имена которых состоят из более чем двух слов")
class Task13Test {
    @Mock
    Animal a1, a2, a3, a4, a5;
    List<Animal> animals;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        when(a1.name()).thenReturn("usual animal name");
        when(a2.name()).thenReturn("usual name");
        when(a3.name()).thenReturn("usual animal name");
        when(a4.name()).thenReturn("name");
        when(a5.name()).thenReturn("typical animal name");

        animals = List.of(a1, a2, a3, a4, a5);
    }

    @Test
    void testGetAnimalsWithNamesMoreThanTwoWords_shouldReturnNotEmptyList() {
        //given
        int wordsNumber = 2;
        List<Animal> expectedAnimalsWithNamesMoreThanTwoWords= List.of(a1, a3, a5);

        // when
        List<Animal> actualAnimalsWithNamesMoreThanTwoWords = AnimalUtils
            .getAnimalsWithNamesMoreThanKWords(animals, wordsNumber);

        // then
        assertThat(actualAnimalsWithNamesMoreThanTwoWords)
            .isEqualTo(expectedAnimalsWithNamesMoreThanTwoWords);
    }

    @Test
    void testGetAnimalsWithNamesMoreThanTwoWords_shouldReturnEmptyList() {
        // when
        int wordsNumber = 3;
        List<Animal> actualAnimalsWithNamesMoreThanThreeWords = AnimalUtils
            .getAnimalsWithNamesMoreThanKWords(animals, wordsNumber);

        // then
        assertThat(actualAnimalsWithNamesMoreThanThreeWords).isEqualTo(List.of());
    }
}
