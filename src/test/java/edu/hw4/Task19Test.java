package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Тест получения ошибок валидации")
class Task19Test {
    @Mock
    Animal a1, a2;
    @Test
    void tesGetValidationErrors() {
        MockitoAnnotations.openMocks(this);

        // given
        when(a1.name()).thenReturn("Raven");
        when(a1.age()).thenReturn(3);
        when(a1.height()).thenReturn(4);
        when(a1.weight()).thenReturn(56);

        when(a2.name()).thenReturn("Cat");
        when(a2.age()).thenReturn(-2);
        when(a2.height()).thenReturn(-3);
        when(a2.weight()).thenReturn(-1);

        List<Animal> animals = List.of(a1, a2);
        Map<String, Set<ValidationError>> expectedErrors = Map.of(
            "Raven", Set.of(),
            "Cat", Set.of(
                new ValidationError("Возраст животного не может быть меньше нуля!"),
                new ValidationError("Рост животного не может быть меньше нуля!"),
                new ValidationError("Вес животного не может быть меньше нуля!")
            )
        );

        // when
        var actualErrors = AnimalUtils.getValidationErrors(animals);

        // then
        assertThat(actualErrors)
            .usingRecursiveComparison()
            .ignoringCollectionOrder()
            .isEqualTo(expectedErrors);
    }
}
