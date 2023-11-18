package edu.hw6.task3;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.PathMatcher;
import java.util.regex.Pattern;

public final class DirectoryStreamFilterUtils {
    private DirectoryStreamFilterUtils() {}

    public static AbstractFilter regularFile() {
        return Files::isRegularFile;
    }

    public static AbstractFilter readable() {
        return Files::isReadable;
    }

    public static AbstractFilter largerThan(long fileSize) {
        return filePath -> Files.size(filePath) > fileSize;
    }

    public static AbstractFilter magicNumber(int... magicNumbers) {
        return path -> {
            byte[] bytes = Files.readAllBytes(path);

            if (bytes.length < magicNumbers.length) {
                return false;
            }

            for (int i = 0; i < magicNumbers.length; i++) {
                if (bytes[i] != (byte) magicNumbers[i]) {
                    return false;
                }
            }
            return true;
        };
    }

    public static AbstractFilter globMatches(String pattern) {
        PathMatcher matcher = FileSystems.getDefault()
            .getPathMatcher("glob:" + pattern);
        return filePath -> matcher.matches(filePath.getFileName());
    }

    public static AbstractFilter regexContains(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return filePath -> pattern.matcher(filePath.toString()).find();
    }
}
