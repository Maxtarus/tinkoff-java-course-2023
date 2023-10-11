package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Нахождение общей длины видео в секундах")
class Task1Test {
    @Test
    @DisplayName("Корректная строка без нулей c пробелами")
    void minutesToSeconds_shouldGetTotalSeconds_whenCorrectStringWithoutZeros() {
        // given
        String videoLength = " 13:56  ";

        // when
        int videoLengthInSeconds = Task1.minutesToSeconds(videoLength);

        // then
        assertThat(videoLengthInSeconds).isEqualTo(836);
    }

    @Test
    @DisplayName("Пустая строка с пробелами")
    void minutesToSeconds_shouldGetMinusOne_whenEmptyString() {
        // given
        String videoLength = "   ";

        // when
        int videoLengthInSeconds = Task1.minutesToSeconds(videoLength);

        // then
        assertThat(videoLengthInSeconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Строка с одной цифрой после нуля до двоеточия и после двоеточия")
    void minutesToSeconds_shouldGetTotalSeconds_whenOneDigitAfterZeroToColon() {
        // given
        String videoLength2 = "01:01";

        // when
        int videoLengthInSeconds2 = Task1.minutesToSeconds(videoLength2);

        // then
        assertThat(videoLengthInSeconds2).isEqualTo(61);
    }

    @Test
    @DisplayName("Строка с несколькими цифрами после нуля до двоеточия и после двоеточия")
    void minutesToSeconds_shouldGetMinusOne_whenSeveralDigitsAfterZeroToColon() {
        // given
        String videoLength2 = "12:010";

        // when
        int videoLengthInSeconds2 = Task1.minutesToSeconds(videoLength2);

        // then
        assertThat(videoLengthInSeconds2).isEqualTo(-1);
    }

    @Test
    @DisplayName("Строка с одной цифрой до двоеточия")
    void minutesToSeconds_shouldGetTotalSeconds_whenOneDigitToColon() {
        // given
        String videoLength = "1:10";

        // when
        int videoLengthInSeconds = Task1.minutesToSeconds(videoLength);

        // then
        assertThat(videoLengthInSeconds).isEqualTo(70);
    }

    @Test
    @DisplayName("Строка с одной цифрой после двоеточия")
    void minutesToSeconds_shouldGetMinusOne_whenOneDigitAfterColon() {
        // given
        String videoLength = "15:1";

        // when
        int videoLengthInSeconds = Task1.minutesToSeconds(videoLength);

        // then
        assertThat(videoLengthInSeconds).isEqualTo(-1);
    }

    @Test
    @DisplayName("Строка с количеством секунд, равным 60")
    void minutesToSeconds_shouldGetMinusOne_whenSecondsMoreThanOrEqualTo60() {
        // given
        String videoLength = "15:60";

        // when
        int videoLengthInSeconds = Task1.minutesToSeconds(videoLength);

        // then
        assertThat(videoLengthInSeconds).isEqualTo(-1);
    }
}
