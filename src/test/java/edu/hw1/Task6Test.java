package edu.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Нахождение количества шагов, которые нужно сделать, чтобы получить из данного числа постоянную Капрекара")
class Task6Test {
    @BeforeEach
    void initStepsNumber() {
        Task6.setZeroStepsNumber();
    }

    @Test
    @DisplayName("Получение постоянной Капрекара за 5 шагов")
    void countK_shouldReturnStepsNumber_whenCorrectNumberInFiveSteps() {
        // given
        int number = 6621;

        // when
        int stepsNumber = Task6.countK(number);

        // then
        assertThat(stepsNumber).isEqualTo(5);
    }

    @Test
    @DisplayName("Получение постоянной Капрекара за 4 шагов")
    void countK_shouldReturnStepsNumber_whenCorrectNumberInFourSteps() {
        // given
        int number = 6554;

        // when
        int stepsNumber = Task6.countK(number);

        // then
        assertThat(stepsNumber).isEqualTo(4);
    }

    @Test
    @DisplayName("Получение постоянной Капрекара за 3 шага")
    void countK_shouldReturnStepsNumber_whenCorrectNumberInThreeSteps() {
        // given
        int number = 1234;

        // when
        int stepsNumber = Task6.countK(number);

        // then
        assertThat(stepsNumber).isEqualTo(3);
    }

    @Test
    @DisplayName("Число является постоянной Капрекара")
    void countK_shouldReturnStepsNumber_whenCorrectNumberInZeroSteps() {
        // given
        int number = 6174;

        // when
        int stepsNumber = Task6.countK(number);

        // then
        assertThat(stepsNumber).isEqualTo(0);
    }

    @Test
    @DisplayName("Число не является четырёхзначным")
    void countK_shouldThrowIllegalArgumentException_whenNotFourDigitsInNumber() {
        // given
        int number = 12345;

        // then
        assertThrows(IllegalArgumentException.class, () -> Task6.countK(number));
    }

    @Test
    @DisplayName("Число является четырёхзначным, но все цифры в нём сопадают")
    void countK_shouldThrowIllegalArgumentException_whenAllDigitsInNumberAreSame() {
        // given
        int number = 1111;

        // then
        assertThrows(IllegalArgumentException.class, () -> Task6.countK(number));
    }

}
