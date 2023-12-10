package edu.hw9.task2;

import java.io.File;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * The {@code DirSearchTask} that recursively searches for directories with a specified number of files or more.
 * The search is performed in a parallel manner using the Fork-Join framework.
 */
public class DirSearchTask extends RecursiveTask<List<String>> {
    private final File directory;
    private final int numberOfFiles;

//    @Serial
//    private static final long serialVersionUID = 752150994361085757L;

    public DirSearchTask(File directory, int numberOfFiles) {
        this.directory = directory;
        this.numberOfFiles = numberOfFiles;
    }

    @Override
    protected List<String> compute() {
        int count = 0;
        List<String> directories = new ArrayList<>();
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    var counter = new DirSearchTask(file, numberOfFiles);
                    counter.fork();
                    directories.addAll(counter.join());
                } else {
                    count++;
                }
            }
        }

        if (count > numberOfFiles) {
            directories.add(directory.getPath());
        }

        return directories;
    }
}
