package edu.hw1;

import java.util.Arrays;
import java.util.Objects;

public final class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] arr1, int[] arr2) {
        Objects.requireNonNull(arr1);
        Objects.requireNonNull(arr2);

        if (!areArraysLengthsValid(arr1, arr2)) {
            return false;
        }

        //Сортируем массивы по возрастанию
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        //Нахождение максимального и минимального элементов в массивах
        int minArr1 = arr1[0];
        int minArr2 = arr2[0];
        int maxArr1 = arr1[arr1.length - 1];
        int maxArr2 = arr2[arr2.length - 1];

        return minArr1 > minArr2 && maxArr1 < maxArr2;
    }

    private static boolean areArraysLengthsValid(int[] arr1, int[] arr2) {
        return arr1.length != 0 && arr2.length >= 2;
    }
}
