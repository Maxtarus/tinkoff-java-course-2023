package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayName("Тест \"каких животных больше: самцов или самок?\"")
class Task5Test {
    @Mock
    Animal a1, a2, a3, a4;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMostCommonSex_shouldReturnResult() {
        // given
        when(a1.sex()).thenReturn(Animal.Sex.M);
        when(a2.sex()).thenReturn(Animal.Sex.M);
        when(a3.sex()).thenReturn(Animal.Sex.M);
        when(a4.sex()).thenReturn(Animal.Sex.F);

        List<Animal> animals = List.of(a1, a2, a3);
        Animal.Sex expectedSex = Animal.Sex.M;

        // when
        Animal.Sex actualSex = AnimalUtils.getMostCommonSex(animals);

        // then
        assertThat(actualSex).isEqualTo(expectedSex);
    }

    @Test
    void testGetMostCommonSex_shouldThrowNoSuchElementException() {
        // given
        when(a1.sex()).thenReturn(Animal.Sex.M);
        when(a2.sex()).thenReturn(Animal.Sex.M);
        when(a3.sex()).thenReturn(Animal.Sex.F);
        when(a4.sex()).thenReturn(Animal.Sex.F);

        List<Animal> animals = List.of(a1, a2, a3, a4);

        // then
        assertThrows(
            NoSuchElementException.class,
            () -> AnimalUtils.getMostCommonSex(animals));
    }
}
