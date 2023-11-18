package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class DiskMap implements Map<String, String> {
    private final Map<String, String> diskMap = new HashMap<>();

    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] keyValue = line.split(":");
                diskMap.put(keyValue[0], keyValue[1]);
            }
         } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void saveToFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (var entry : diskMap.entrySet()) {
                sb.append(entry.getKey())
                    .append(":")
                    .append(entry.getValue())
                    .append("\n");
            }
            writer.write(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    // Default methods
    @Override
    public int size() {
        return diskMap.size();
    }

    @Override
    public boolean isEmpty() {
        return diskMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return diskMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return diskMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return diskMap.get(key);
    }

    @Override
    public String put(String key, String value) {
        return diskMap.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return diskMap.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        diskMap.putAll(m);
    }

    @Override
    public void clear() {
        diskMap.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return diskMap.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return diskMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return diskMap.entrySet();
    }
}



