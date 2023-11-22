package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

@FunctionalInterface
public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    default AbstractFilter and(AbstractFilter other) {
        return filter -> this.accept(filter) && other.accept(filter);
    }
}
