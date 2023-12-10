package edu.hw9.task2;

import java.io.File;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import org.jetbrains.annotations.NotNull;

public class FileSearchTask extends RecursiveTask<List<String>> {
    private final File file;
    private final long size;
    private final String extension;
    @Serial
    private static final long serialVersionUID = 4049673324822769377L;

    public FileSearchTask(File file, long size, String extension) {
        this.file = file;
        this.size = size;
        this.extension = extension;
    }

    @Override
    protected List<String> compute() {
        List<String> foundFiles = new ArrayList<>();
        searchFiles(file, foundFiles);

        return foundFiles;
    }

    private void searchFiles(@NotNull File currentFile, List<String> foundFiles) {
        File[] files = currentFile.listFiles();

        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    searchFiles(f, foundFiles);
                } else {
                    processFile(f, foundFiles);
                }
            }
        }
    }

    private void processFile(@NotNull File currentFile, List<String> foundFiles) {
        if (currentFile.length() > size && currentFile.getName().endsWith(extension)) {
            foundFiles.add(currentFile.getPath());
        }
    }
}
