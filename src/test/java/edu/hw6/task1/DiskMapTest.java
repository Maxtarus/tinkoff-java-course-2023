package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class DiskMapTest {
    final String fileToLoadFromName = "src/test/java/edu/hw6/task1/fileToLoadFrom.txt";
    final String fileSaveToName = "src/test/java/edu/hw6/task1/fileSaveTo.txt";
    DiskMap diskMap;

    @BeforeEach
    void createDiskMap() {
        diskMap = new DiskMap();
    }

    @AfterEach
    void deleteFiles() throws IOException {
        Files.deleteIfExists(Path.of(fileSaveToName));
        Files.deleteIfExists(Path.of(fileToLoadFromName));
    }

    void predefineNotEmptyFileToLoadFrom() {
        Path fileToLoadFromPath = Path.of(fileToLoadFromName);

        try {
            Files.createFile(fileToLoadFromPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter writer = Files
            .newBufferedWriter(fileToLoadFromPath, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write("""
            key_1:value_1
            key_2:value_2
            key_3:value_3
            """);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    Set<String> readFromFileSaveTo() {
        Set<String> valuesFromFileSaveTo = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileSaveToName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                valuesFromFileSaveTo.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return valuesFromFileSaveTo;
    }

    void predefineEmptyFileToLoadFrom() {
        Path fileToLoadFromPath = Path.of(fileToLoadFromName);

        try (BufferedWriter writer = Files.newBufferedWriter(fileToLoadFromPath)) {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


    @Test
    @DisplayName("Загрузка из файла в DiskMap")
    void testLoadFromNotEmptyFile() {
        //Arrange
        predefineNotEmptyFileToLoadFrom();
        Set<Map.Entry<String, String>> expectedEntrySet = Set.of(
            Map.entry("key_1", "value_1"),
            Map.entry("key_2", "value_2"),
            Map.entry("key_3", "value_3")
        );

        //Act
        diskMap.loadFromFile(fileToLoadFromName);

        //Assert
        Set<Map.Entry<String, String>> actualEntrySet = diskMap.entrySet();
        assertThat(actualEntrySet).containsExactlyInAnyOrderElementsOf(expectedEntrySet);
    }


    @Test
    @DisplayName("Сохранение в файл содержимого DiskMap")
    void testSaveToFile() {
        //Arrange
        diskMap.putAll(
            Map.of(
            "key_1", "value_1",
            "key_2", "value_2",
            "key_3", "value_3"
            )
        );

        Set<String> expectedValuesFromFileSaveTo = Set.of(
            "key_1:value_1",
            "key_2:value_2",
            "key_3:value_3"
        );

        //Act
        diskMap.saveToFile(fileSaveToName);

        //Assert
        Set<String> actualValuesFromFileSaveTo = readFromFileSaveTo();
        assertThat(actualValuesFromFileSaveTo)
            .containsExactlyInAnyOrderElementsOf(expectedValuesFromFileSaveTo);
    }

    @Test
    @DisplayName("Загрузка из пустого файла в DiskMap")
    void testLoadFromEmptyFile() {
        //Arrange
        predefineEmptyFileToLoadFrom();

        //Act
        diskMap.loadFromFile(fileToLoadFromName);

        //Assert
        Set<Map.Entry<String, String>> actualEntrySet = diskMap.entrySet();
        assertThat(actualEntrySet).isEmpty();
    }
}
