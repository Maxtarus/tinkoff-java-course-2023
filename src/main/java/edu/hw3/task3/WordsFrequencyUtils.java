package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class WordsFrequencyUtils {
    private WordsFrequencyUtils() {
    }

    public static Map<Object, Integer> freqDict(List<Object> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (!areAllObjectsOfSameType(list)) {
            throw new IllegalArgumentException("Объекты в списке должны быть одного типа!");
        }

        Map<Object, Integer> freqDict = new HashMap<>();

        for (Object item : list) {
            freqDict.put(item, freqDict.getOrDefault(item, 0) + 1);
        }

        return freqDict;
    }

    private static boolean areAllObjectsOfSameType(List<Object> list) {
        for (int i = 1; i < list.size(); i++) {
            if (!list.get(0).getClass().equals(list.get(i).getClass())) {
                return false;
            }
        }
        return true;
    }
}
