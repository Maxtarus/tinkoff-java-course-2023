package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Исправление строки, в которой каждая пара символов поменялась местами")
class Task4Test {
    @Test
    @DisplayName("Строка с чётным количеством символов")
    void fixString_shouldReturnRightString_whenEvenCharactersNumberInString() {
        // given
        String wrongString = "hTsii  s aimex dpus rtni.g";

        // when
        String rightString = Task4.fixString(wrongString);

        // then
        assertThat(rightString).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("Строка с нечётным количеством символов")
    void fixString_shouldReturnRightString_whenOddCharactersNumberInString() {
        // given
        String wrongString = "badce";

        // when
        String rightString = Task4.fixString(wrongString);

        // then
        assertThat(rightString).isEqualTo("abcde");
    }

    @Test
    @DisplayName("Null-строка")
    void fixString_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> Task4.fixString(null));
    }
}
