package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Тест получения сообщений ошибок валидации")
class Task20Test {
    @Mock
    Animal a1, a2;
    @Test
    void testGetValidationErrorsMessages() {
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
        Map<String, String> expectedErrorMessages = Map.of(
            "Raven", "",
            "Cat", "Возраст животного не может быть меньше нуля! "
                + "Рост животного не может быть меньше нуля! "
                + "Вес животного не может быть меньше нуля!"
            );


        // when
        var actualErrorMessages = AnimalUtils.getValidationErrorMessages(animals);

        // then
        assertThat(actualErrorMessages.keySet()).isEqualTo(expectedErrorMessages.keySet());
    }
}
