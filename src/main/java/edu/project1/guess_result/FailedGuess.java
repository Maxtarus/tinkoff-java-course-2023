package edu.project1.guess_result;

import java.util.ResourceBundle;
import org.jetbrains.annotations.NotNull;

public record FailedGuess(
    char[] state,
    int attempt,
    int maxAttempts
) implements GuessResult {

    @Override
    public @NotNull String message() {
        var rb = ResourceBundle.getBundle("hangman_messages_ru_Ru");
        return String.format(rb.getString("failed.guess"), attempt, maxAttempts);
    }
}
