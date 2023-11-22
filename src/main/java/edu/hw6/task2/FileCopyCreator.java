package edu.hw6.task2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FileCopyCreator {
    private static final String TXT = ".txt";
    private static final Logger LOGGER = LogManager.getLogger();

    private FileCopyCreator() {}

    public static void cloneFile(Path path) throws FileNotFoundException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException();
        }

        int copyNumber = 1;
        Path copiedPath = generateCopyPath(path, copyNumber);

        while (Files.exists(copiedPath)) {
            copyNumber++;
            copiedPath = generateCopyPath(path, copyNumber);
        }

        try {
            Files.copy(path, copiedPath, StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static Path generateCopyPath(Path path, int copyNumber) {
        String fileName = path.getFileName().toString().replace(TXT, "");
        String copySuffix = (copyNumber == 1) ? " — копия" : " — копия (" + copyNumber + ")";
        String newName = fileName + copySuffix + TXT;
        return path.resolveSibling(newName);
    }
}
