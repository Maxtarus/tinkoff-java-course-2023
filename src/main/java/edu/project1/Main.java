package edu.project1;

import edu.project1.dictionary.RussianDictionary;

public final class Main {
    private static final int DEFAULT_MAX_ATTEMPTS = 7;

    private Main() {
    }

    public static void main(String[] args) {
        var game = new ConsoleHangman(RussianDictionary.getInstance(), DEFAULT_MAX_ATTEMPTS);
        game.run();
    }
}
