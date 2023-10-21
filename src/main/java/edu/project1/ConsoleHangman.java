package edu.project1;

import edu.project1.dictionary.Dictionary;
import edu.project1.guess_result.Defeat;
import edu.project1.guess_result.GuessResult;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"RegexpSinglelineJava", "MultipleStringLiterals"})
public class ConsoleHangman {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final ResourceBundle RB = ResourceBundle.getBundle(
        "edu.project1.resources.hangman_messages",
        Locale.of("ru", "RU"));
    private final Session session;

    public Session getSession() {
        return session;
    }

    public ConsoleHangman(Dictionary wordDictionary, int maxAttempts) {
        if (maxAttempts < 1) {
            throw new IllegalArgumentException(RB.getString("maxAttempts.condition"));
        }
        this.session = new Session(wordDictionary.getRandomWord(), maxAttempts);
    }

    public void run() {
        printInitialState();
        while (!session.isGameFinished()) {
            System.out.println(RB.getString("input.letter"));
            String playerInput = INPUT.next();
            GuessResult guessResult = tryGuess(playerInput);
            printState(guessResult);
        }
    }

    public GuessResult tryGuess(String input) {

        if (isGiveUpCommand(input)) {
            return session.giveUp();
        }

        if (isInvalidInput(input)) {
            return session.getIncorrectGuess();
        }

        return session.guess(input.toLowerCase().charAt(0));
    }

    private boolean isInvalidInput(String input) {
        return input.length() != 1;
    }

    private boolean isGiveUpCommand(@NotNull String input) {
        return input.equalsIgnoreCase(RB.getString("giveup.command"));
    }

    private void printState(@NotNull GuessResult guess) {
        StringBuilder sb = new StringBuilder().append(guess.message());

        if (guess instanceof Defeat) {
            sb.append(String.format(RB.getString("hidden.word"), session.getAnswer()));
        }

        System.out.println(sb);
    }

    private void printInitialState() {
        String sb = String.format(
            RB.getString("word"),
            session.getUserAnswer().length(), session.getUserAnswer());
        System.out.println(sb);
    }
}
