package edu.project1;

import edu.project1.dictionary.RussianDictionary;
import edu.project1.guess_result.Defeat;
import edu.project1.guess_result.FailedGuess;
import edu.project1.guess_result.GuessResult;
import edu.project1.guess_result.IncorrectGuess;
import edu.project1.guess_result.RepeatedGuess;
import edu.project1.guess_result.SuccessfulGuess;
import edu.project1.guess_result.Win;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class Session {
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private final Set<Character> usedLetters = new HashSet<>();
    private int numberOfHiddenLetters;
    private int attempts;
    private boolean isGameFinished;

    public Session(String answer, int maxAttempts) {
        this.answer = answer;
        this.maxAttempts = maxAttempts;
        userAnswer = new char[answer.length()];
        Arrays.fill(this.userAnswer, '_');
        numberOfHiddenLetters = answer.length();
        attempts = 0;
        isGameFinished = false;
    }

    public String getAnswer() {
        return answer;
    }

    public String getUserAnswer() {
        return String.valueOf(userAnswer);
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }

    private boolean isAnswerGuessed() {
        return numberOfHiddenLetters == 0;
    }

    private boolean isLetterUsed(char guess) {
        return usedLetters.contains(guess);
    }

    private boolean areMaxAttemptsExceeded() {
        return attempts == maxAttempts;
    }

    private boolean isInvalidGuess(char guess) {
        char lowerCaseGuess = String.valueOf(guess).toLowerCase().charAt(0);
        return !(RussianDictionary.getAvailableLetters().contains(lowerCaseGuess));
    }

    private boolean isSuccessfulGuess(char guess) {
        usedLetters.add(guess);
        boolean isSuccessfulGuess = false;

        for (int i = 0; i < answer.length(); i++) {
            if (guess == answer.charAt(i)) {
                userAnswer[i] = guess;
                numberOfHiddenLetters--;
                isSuccessfulGuess = true;
            }
        }

        return isSuccessfulGuess;
    }

    public @NotNull GuessResult guess(char guess) {
        if (isInvalidGuess(guess)) {
            return new IncorrectGuess(userAnswer, attempts, maxAttempts);
        }

        if (isLetterUsed(guess)) {
            return new RepeatedGuess(userAnswer, attempts, maxAttempts);
        }

        if (isSuccessfulGuess(guess)) {
            return getSuccessfulGuess();
        } else {
            attempts++;
            return getFailedGuess();
        }
    }

    public @NotNull GuessResult getSuccessfulGuess() {
        if (isAnswerGuessed()) {
            isGameFinished = true;
            return new Win(userAnswer, attempts, maxAttempts);
        }

        return new SuccessfulGuess(userAnswer, attempts, maxAttempts);
    }

    public @NotNull GuessResult getFailedGuess() {
        if (areMaxAttemptsExceeded()) {
            isGameFinished = true;
            return new Defeat(userAnswer, attempts, maxAttempts);
        }

        return new FailedGuess(userAnswer, attempts, maxAttempts);
    }

    public @NotNull GuessResult giveUp() {
        isGameFinished = true;
        return new Defeat(userAnswer, attempts, maxAttempts);
    }

    public @NotNull GuessResult getIncorrectGuess() {
        return new IncorrectGuess(userAnswer, attempts, maxAttempts);
    }
}
