package edu.hw5.task4;

import java.util.Objects;
import java.util.regex.Pattern;

public final class PasswordValidator {
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[~!@#$%^&*|]");

    private PasswordValidator() {}

    public static boolean isPasswordValid(String password) {
        Objects.requireNonNull(password);
        return PASSWORD_PATTERN.matcher(password).find();
    }
}
