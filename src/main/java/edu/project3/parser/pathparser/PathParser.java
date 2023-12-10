package edu.project3.parser.pathparser;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.stream.Stream;

public final class PathParser {
    private PathParser() {
    }

    public static List<Path> getPaths(String path) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + path);
        try (Stream<Path> pathStream = Files.find(
            Path.of(""),
            Integer.MAX_VALUE,
            (currentPath, fileAttributes) -> !fileAttributes.isDirectory() && pathMatcher.matches(currentPath)
        )) {
            return pathStream.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
