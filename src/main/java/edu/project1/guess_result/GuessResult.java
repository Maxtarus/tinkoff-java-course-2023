package edu.project1.guess_result;

import org.jetbrains.annotations.NotNull;

public sealed interface GuessResult permits
    Defeat, FailedGuess, IncorrectGuess, RepeatedGuess, SuccessfulGuess, Win {
    char[] state();

    int attempt();

    int maxAttempts();

    @NotNull String message();
}
