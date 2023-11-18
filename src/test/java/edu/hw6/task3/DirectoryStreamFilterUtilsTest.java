package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.task3.DirectoryStreamFilterUtils.globMatches;
import static edu.hw6.task3.DirectoryStreamFilterUtils.largerThan;
import static edu.hw6.task3.DirectoryStreamFilterUtils.magicNumber;
import static edu.hw6.task3.DirectoryStreamFilterUtils.readable;
import static edu.hw6.task3.DirectoryStreamFilterUtils.regexContains;
import static edu.hw6.task3.DirectoryStreamFilterUtils.regularFile;
import static org.assertj.core.api.Assertions.assertThat;

class DirectoryStreamFilterUtilsTest {
    final String dirName = "src/test/java/edu/hw6/task3/resources";

    @Test
    @DisplayName("Проверка цепочки фильтров")
    void testDirectoryStreamFilterUtils() {
        //Arrange
        Path dirPath = Path.of(dirName);
        DirectoryStream.Filter<Path> filter = regularFile()
            .and(readable())
            .and(largerThan(245_000))
            .and(globMatches("*.png"))
            .and(regexContains("_"))
            .and(magicNumber( 0x89, 'P', 'N', 'G'));
        String expectedFile = "_nature.png";

        //Act
        List<String> filePaths = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dirPath, filter)) {
            entries.forEach(filePath -> filePaths.add(filePath.getFileName().toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Assert
        assertThat(filePaths).containsExactly(expectedFile);
    }
}
