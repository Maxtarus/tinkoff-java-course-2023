package edu.hw3.task1;

import java.util.Map;
import static java.util.Map.entry;

public final class AtbashEncryptionUtils {
    private AtbashEncryptionUtils() {
    }

    private static final Map<Character, Character> LOOKUP_TABLE = Map.ofEntries(
        entry('A', 'Z'), entry('B', 'Y'), entry('C', 'X'), entry('D', 'W'),
        entry('E', 'V'), entry('F', 'U'), entry('G', 'T'), entry('H', 'S'),
        entry('I', 'R'), entry('J', 'Q'), entry('K', 'P'), entry('L', 'O'),
        entry('M', 'N'), entry('N', 'M'), entry('O', 'L'), entry('P', 'K'),
        entry('Q', 'J'), entry('R', 'I'), entry('S', 'H'), entry('T', 'G'),
        entry('U', 'F'), entry('V', 'E'), entry('W', 'D'), entry('X', 'C'),
        entry('Y', 'B'), entry('Z', 'A')
    );

    public static String atbash(String message) {
        if (message == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder encryptedMessage = new StringBuilder();

        for (char symbol : message.toCharArray()) {
            if (LOOKUP_TABLE.containsKey(symbol)) {
                encryptedMessage.append(LOOKUP_TABLE.get(symbol));
            } else if (LOOKUP_TABLE.containsKey(Character.toUpperCase(symbol))) {
                encryptedMessage.append(Character.toLowerCase(LOOKUP_TABLE.get(Character.toUpperCase(symbol))));
            } else {
                encryptedMessage.append(symbol);
            }
        }

        return encryptedMessage.toString();
    }
}
