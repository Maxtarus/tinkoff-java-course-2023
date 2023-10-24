package edu.project1.guess_result;

import java.util.ResourceBundle;
import org.jetbrains.annotations.NotNull;

public record RepeatedGuess(
    char[] state,
    int attempt,
    int maxAttempts
) implements GuessResult {
    @Override
    public @NotNull String message() {
        var rb = ResourceBundle.getBundle("hangman");
        return String.format(rb.getString("repeated.guess"), String.valueOf(state));
    }
}
