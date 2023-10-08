package edu.hw1;

import java.util.Arrays;

public final class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] arr1, int[] arr2) {
        //Проверка на null
        if (arr1 == null || arr2 == null) {
            throw new IllegalArgumentException("Массивы не могут быть null!");
        }

        //Если в первом массиве нет элементов или во втором массиве меньше двух элементов,
        // первый массив не может вложен во второй
        if (arr1.length == 0 || arr2.length < 2) {
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
}
