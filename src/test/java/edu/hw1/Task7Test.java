package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Проверка работы функций, реализующих циклический сдвиг битов")
class Task7Test {
    @Test
    @DisplayName("Циклический сдвиг вправо c корректными аргументами")
    void rotateRight_shouldGetNewNumber_whenCorrectArguments() {
        // given
        int number = 8;
        int shift = 1;

        // when
        int newNumber = Task7.rotateRight(number, shift);

        // then
        assertThat(newNumber).isEqualTo(4);
    }

    @Test
    @DisplayName("Циклический сдвиг влево c корректными аргументами")
    void rotateLeft_shouldGetNewNumber_whenCorrectArguments() {
        // given
        int number = 17;
        int shift = 2;

        // when
        int newNumber = Task7.rotateLeft(number, shift);

        // then
        assertThat(newNumber).isEqualTo(6);
    }

    @Test
    @DisplayName("Циклический сдвиг влево c некорректными аргументами")
    void rotateRightOrLeft_shouldThrowIllegalArgumentException_whenIncorrectArguments() {
        // given
        int number1 = -1;
        int shift = -2;

        // then
        assertThrows(IllegalArgumentException.class, () -> Task7.rotateLeft(number1, shift));
    }
}
