package edu.hw5.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RussianLicensePlateValidatorTest {
    @DisplayName("Правильные номерные знаки")
    @ParameterizedTest
    @ValueSource(strings = {
        "А123ВЕ777",
        "О777ОО177"
    })
    void testIsRussianLicensePlateValid_shouldReturnTrue(String licensePlate) {
        // Act
        boolean result = RussianLicensePlateValidator.isRussianLicensePlateValid(licensePlate);

        // Assert
        assertThat(result).isTrue();
    }

    @DisplayName("Неправильные номерные знаки")
    @ParameterizedTest
    @ValueSource(strings = {
        "123АВЕ777",
        "А123ВГ77",
        "А123ВЕ7777"
    })
    void testIsRussianLicensePlateValid_shouldReturnFalse(String licensePlate) {
        // Act
        boolean result = RussianLicensePlateValidator.isRussianLicensePlateValid(licensePlate);

        // Assert
        assertThat(result).isFalse();
    }

    @DisplayName("Null-знак")
    @ParameterizedTest
    @NullSource
    public void testIsPasswordValid_shouldThrowNullPointerException(String password) {
        assertThrows(NullPointerException.class,
            () -> RussianLicensePlateValidator.isRussianLicensePlateValid(password));
    }
}
