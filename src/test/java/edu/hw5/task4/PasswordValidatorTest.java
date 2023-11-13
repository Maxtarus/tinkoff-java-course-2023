package edu.hw5.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordValidatorTest {
    private static Stream<Arguments> correctPasswords() {
        return Stream.of(
            Arguments.of("~aboba"),
            Arguments.of("aboba!"),
            Arguments.of("@@aboba"),
            Arguments.of("###aboba"),
            Arguments.of("zxc$"),
            Arguments.of("qw%e"),
            Arguments.of("kan&eki"),
            Arguments.of("pos1only^"),
            Arguments.of("mmm*"),
            Arguments.of("gho|ul")
        );
    }

    private static Stream<Arguments> wrongPasswords() {
        return Stream.of(
            Arguments.of("aboba"),
            Arguments.of(""),
            Arguments.of("12adsa__")
        );
    }

    @ParameterizedTest
    @MethodSource("correctPasswords")
    @DisplayName("Корректные пароли")
    public void isPasswordValid_shouldReturnTrue_whenStringContainsRequiredSign(String password) {
        assertThat(PasswordValidator.isPasswordValid(password)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("wrongPasswords")
    @DisplayName("Некорректные пароли")
    public void isPasswordValid_shouldReturnFalse_whenStringDoesNotContainsRequiredSign(String password) {
        assertThat(PasswordValidator.isPasswordValid(password)).isFalse();
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null-пароль")
    public void isPasswordValid_shouldThrowNullPointerException(String password) {
       assertThrows(NullPointerException.class, () -> PasswordValidator.isPasswordValid(password));
    }
}
