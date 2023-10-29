package edu.hw3.task7;

import java.util.Comparator;

public class NullComparator<T extends Comparable<T>> implements Comparator<T> {
    @Override
    public int compare(T key1, T key2) {
        if (key1 == null) {
            if (key2 == null) {
                return 0;
            } else {
                return 1;
            }
        } else if (key2 == null) {
            return -1;
        }

        return key1.compareTo(key2);
    }
}
