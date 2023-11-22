package edu.hw6.task2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileCopyCreatorTest {
    final Path filePath = Path
        .of("src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret.txt");
    final Path firstCopyPath = Path
        .of("src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret — копия.txt");
    final Path secondCopyPath = Path
        .of("src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret — копия (2).txt");

    @BeforeEach
    void createSourceFile() throws IOException {
        Files.createFile(filePath);
    }

    @AfterEach
    void deleteCopiesAndSourceFile() throws IOException {
        Files.deleteIfExists(secondCopyPath);
        Files.deleteIfExists(firstCopyPath);
        Files.deleteIfExists(filePath);
    }

    @Test
    @DisplayName("Проверка существования первой копии")
    void testExistenceOfFirstCopy() throws FileNotFoundException {
        //Act
        FileCopyCreator.cloneFile(filePath);

        //Assert
        assertThat(Files.exists(firstCopyPath)).isTrue();
    }

    @Test
    @DisplayName("Проверка совпадения содержимого первой копии и исходного файла")
    void testIfContentsOfFirstCopyAndSourceFileMatch() throws IOException {
        //Act
        FileCopyCreator.cloneFile(filePath);

        //Assert
        List<String> lines = Files.readAllLines(filePath);
        List<String> copyLines = Files.readAllLines(firstCopyPath);
        assertThat(copyLines).isEqualTo(lines);
    }

    @Test
    @DisplayName("Проверка существования второй копии")
    void testExistenceOfSecondCopy() throws FileNotFoundException {
        //Act
        FileCopyCreator.cloneFile(filePath);
        FileCopyCreator.cloneFile(filePath);

        //Assert
        assertThat(Files.exists(secondCopyPath)).isTrue();
    }

    @Test
    @DisplayName("Проверка совпадения содержимого второй копии и исходного файла")
    void testIfContentsOfSecondCopyAndSourceFileMatch() throws IOException {
        //Act
        FileCopyCreator.cloneFile(filePath);
        FileCopyCreator.cloneFile(filePath);

        //Assert
        List<String> lines = Files.readAllLines(filePath);
        List<String> copyLines = Files.readAllLines(secondCopyPath);
        assertThat(copyLines).isEqualTo(lines);
    }

    @Test
    @DisplayName("Проверка копирования несуществующего файла")
    public void cloneFile_shouldThrowFileNotFoundException() {
        assertThrows(FileNotFoundException.class,
            () -> FileCopyCreator.cloneFile(Path.of("Tinkoff Bank Biggest Secret.txt")));
    }
}
