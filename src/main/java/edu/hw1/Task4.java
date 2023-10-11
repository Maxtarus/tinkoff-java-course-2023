package edu.hw1;

import java.util.Objects;

public final class Task4 {
    private Task4() {
    }

    public static String fixString(String wrongString) {
        Objects.requireNonNull(wrongString);
        StringBuilder rightString = new StringBuilder();
        int wrongStringLength = wrongString.length();

        for (int i = 0; i < wrongStringLength; i += 2) {
            if (i != wrongStringLength - 1) {
                rightString.append(wrongString.charAt(i + 1)).append(wrongString.charAt(i));
            } else {
                rightString.append(wrongString.charAt(i));
            }
        }

        return rightString.toString();
    }
}
