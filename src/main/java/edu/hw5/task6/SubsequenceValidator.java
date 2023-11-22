package edu.hw5.task6;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SubsequenceValidator {
    private SubsequenceValidator() {}

    public static boolean isSubsequence(String subsequence, String string) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(subsequence);
        Pattern subsequencePattern = Pattern.compile(subsequence);
        Matcher subsequencePatternMatcher = subsequencePattern.matcher(string);
        return subsequencePatternMatcher.find();
    }
}
