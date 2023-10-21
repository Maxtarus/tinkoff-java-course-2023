package edu.project1;

import edu.project1.dictionary.Dictionary;
import edu.project1.guess_result.Defeat;
import edu.project1.guess_result.FailedGuess;
import edu.project1.guess_result.GuessResult;
import edu.project1.guess_result.IncorrectGuess;
import edu.project1.guess_result.RepeatedGuess;
import edu.project1.guess_result.SuccessfulGuess;
import edu.project1.guess_result.Win;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HangmanGameTest {
    static final String ANSWER = "виселица";
    static final int MAX_ATTEMPTS = 4;
    static final ResourceBundle rb = ResourceBundle.getBundle("hangman_messages_ru_Ru", Locale.of("ru"));
    @Mock
    Dictionary dictionary;
    ConsoleHangman testGame;

    @BeforeEach
    void initNewSession() {
        MockitoAnnotations.openMocks(this);
        when(dictionary.getRandomWord()).thenReturn(ANSWER);
        testGame = new ConsoleHangman(dictionary, MAX_ATTEMPTS);
    }

    @ParameterizedTest
    @ValueSource(strings = { "ивласце", "фивласце", "эживласце", "ъхзивласце" })
    @DisplayName("Игрок победил")
    void testPlayerWon(@NotNull String input) {

        // Arrange
        List<String> inputs = new ArrayList<>();
        for (char guess : input.toCharArray()) {
            inputs.add(String.valueOf(guess));
        }

        for (int i = 0; i < inputs.size() - 1; i++) {
            // Act
            GuessResult result = testGame.tryGuess(inputs.get(i));

            // Assert
            if (ANSWER.contains(inputs.get(i))) {
                assertThat(result).isInstanceOf(SuccessfulGuess.class);
                assertThat(result.message()).isEqualTo(
                    String.format(rb.getString("successful.guess"),
                    String.valueOf(result.state())));
            } else {
                assertThat(result).isInstanceOf(FailedGuess.class);
                assertThat(result.message()).isEqualTo(
                    String.format(rb.getString("failed.guess"),
                        result.attempt(), result.maxAttempts()));
            }

            assertThat(testGame.getSession().isGameFinished()).isEqualTo(false);
        }

        // Act
        GuessResult result = testGame.tryGuess(inputs.get(inputs.size() - 1));

        // Assert
        assertThat(result).isInstanceOf(Win.class);
        assertThat(result.message()).isEqualTo(
            String.format(rb.getString("successful.guess") + rb.getString("win"),
            String.valueOf(result.state())));
        assertThat(testGame.getSession().isGameFinished()).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(strings = { "ъхэж", "иъхэж", "ивъхэж", "ивлъхэж",
        "ивлаъхэж", "ивласъхэж", "ивласцъхэж"})
    @DisplayName("Игрок проиграл")
    void testPlayerDefeated(@NotNull String input) {

        // Arrange
        List<String> inputs = new ArrayList<>();
        for (char guess : input.toCharArray()) {
            inputs.add(String.valueOf(guess));
        }

        for (int i = 0; i < inputs.size() - 1; i++) {
            GuessResult result = testGame.tryGuess(inputs.get(i));

            // Assert
            if (ANSWER.contains(inputs.get(i))) {
                assertThat(result).isInstanceOf(SuccessfulGuess.class);
            } else {
                assertThat(result).isInstanceOf(FailedGuess.class);
            }

            assertThat(testGame.getSession().isGameFinished()).isEqualTo(false);
        }

        // Act
        GuessResult result = testGame.tryGuess(inputs.get(inputs.size() - 1));

        // Assert
        assertThat(result).isInstanceOf(Defeat.class);
        assertThat(result.message()).isEqualTo(
            String.format(rb.getString("failed.guess") + rb.getString("defeat"),
                result.attempt(), result.maxAttempts()));
        assertThat(testGame.getSession().isGameFinished()).isEqualTo(true);
    }

    @Test
    @DisplayName("Игрок сдался")
    void testGiveUpCommand() {
        // Arrange
        String input = ResourceBundle
            .getBundle("hangman_messages_ru_Ru")
            .getString("giveup.command");

        // Act
        GuessResult result = testGame.tryGuess(input);

        // Assert
        assertThat(result).isInstanceOf(Defeat.class);
        assertThat(result.message()).isEqualTo(
            String.format(rb.getString("failed.guess") + rb.getString("defeat"),
                result.attempt(), result.maxAttempts()));
        assertThat(testGame.getSession().isGameFinished()).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(ints = { 2, 3, 4, 5})
    @DisplayName("Игрок совершил повторный ввод буквы")
    void testRepeatedInput(int argument) {
        // Arrange
        String input = String.valueOf(ANSWER.charAt(0));
        GuessResult result = testGame.tryGuess(input);

        // Assert
        assertThat(result).isInstanceOf(SuccessfulGuess.class);

        for (int i = 0; i < argument; i++) {
            // Act
            GuessResult resultRepeat = testGame.tryGuess(input);

            // Assert
            assertThat(resultRepeat).isInstanceOf(RepeatedGuess.class);
            assertThat(resultRepeat.message()).isEqualTo(
                String.format(rb.getString("repeated.guess"),
                    String.valueOf(result.state())));
            assertThat(testGame.getSession().isGameFinished()).isEqualTo(false);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "*", "1", "f", "бб"})
    @DisplayName("Игрок совершил некорректный ввод")
    void testUncorrectedInput(String input) {
        // Act
        GuessResult result = testGame.tryGuess(input);

        // Assert
        assertThat(result).isInstanceOf(IncorrectGuess.class);
        assertThat(result.message()).isEqualTo(
            String.format(rb.getString("incorrect.guess"),
                String.valueOf(result.state())));
        assertThat(testGame.getSession().isGameFinished()).isEqualTo(false);
    }
}
