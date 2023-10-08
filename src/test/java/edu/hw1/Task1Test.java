package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Нахождение общей длины видео в секундах")
class Task1Test {
    @Test
    @DisplayName("Корректная строка без нулей c пробелами и без")
    void minutesToSeconds_shouldGetTotalSeconds_whenCorrectStringWithoutZeros() {
        // given
        String videoLength1 = "13:56";
        String videoLength2 = " 13:56  ";

        // when
        int videoLengthInSeconds1 = Task1.minutesToSeconds(videoLength1);
        int videoLengthInSeconds2 = Task1.minutesToSeconds(videoLength2);

        // then
        assertThat(videoLengthInSeconds1).isEqualTo(836);
        assertThat(videoLengthInSeconds2).isEqualTo(836);
    }

    @Test
    @DisplayName("Пустая строка с пробелами и без")
    void minutesToSeconds_shouldGetMinusOne_whenEmptyString() {
        // given
        String videoLength1 = "";
        String videoLength2 = "   ";

        // when
        int videoLengthInSeconds1 = Task1.minutesToSeconds(videoLength1);
        int videoLengthInSeconds2 = Task1.minutesToSeconds(videoLength2);

        // then
        assertThat(videoLengthInSeconds1).isEqualTo(-1);
        assertThat(videoLengthInSeconds2).isEqualTo(-1);
    }

    @Test
    @DisplayName("Строка с одной цифрой после нуля до двоеточия и после двоеточия")
    void minutesToSeconds_shouldGetTotalSeconds_whenOneDigitAfterZeroToColon() {
        // given
        String videoLength1 = "01:10";
        String videoLength2 = "01:01";

        // when
        int videoLengthInSeconds1 = Task1.minutesToSeconds(videoLength1);
        int videoLengthInSeconds2 = Task1.minutesToSeconds(videoLength2);

        // then
        assertThat(videoLengthInSeconds1).isEqualTo(70);
        assertThat(videoLengthInSeconds2).isEqualTo(61);
    }

    @Test
    @DisplayName("Строка с несколькими цифрами после нуля до двоеточия и после двоеточия")
    void minutesToSeconds_shouldGetMinusOne_whenSeveralDigitsAfterZeroToColon() {
        // given
        String videoLength1 = "012:10";
        String videoLength2 = "12:010";

        // when
        int videoLengthInSeconds1 = Task1.minutesToSeconds(videoLength1);
        int videoLengthInSeconds2 = Task1.minutesToSeconds(videoLength2);

        // then
        assertThat(videoLengthInSeconds1).isEqualTo(-1);
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
    @DisplayName("Строка со всеми нулями")
    void minutesToSeconds_shouldGetZeroSeconds_whenOnlyZerosInString() {
        // given
        String videoLength1 = "0:00";
        String videoLength2 = "00:00";

        // when
        int videoLengthInSeconds1 = Task1.minutesToSeconds(videoLength1);
        int videoLengthInSeconds2 = Task1.minutesToSeconds(videoLength2);

        // then
        assertThat(videoLengthInSeconds1).isEqualTo(0);
        assertThat(videoLengthInSeconds2).isEqualTo(0);
    }

    @Test
    @DisplayName("Строка с количеством секунд больше или равно 60")
    void minutesToSeconds_shouldGetMinusOne_whenSecondsMoreThanOrEqualTo60() {
        // given
        String videoLength1 = "15:60";
        String videoLength2 = "15:123";

        // when
        int videoLengthInSeconds1 = Task1.minutesToSeconds(videoLength1);
        int videoLengthInSeconds2 = Task1.minutesToSeconds(videoLength2);

        // then
        assertThat(videoLengthInSeconds1).isEqualTo(-1);
        assertThat(videoLengthInSeconds2).isEqualTo(-1);
    }

    @Test
    @DisplayName("Null-строка")
    void minutesToSeconds_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Task1.minutesToSeconds(null));
    }
}
