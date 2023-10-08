package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Проверка вложенности одного массива в другой")
class Task3Test {
    @Test
    @DisplayName("Первый массив может быть вложен во второй")
    void isNestable_shouldReturnTrue_whenFirstArrayIsNestedInSecondArray() {
        // given
        int[] arr1 = new int[] {3, 1, 2};
        int[] arr2 = new int[] {4, 0, 2};

        // when
        boolean result = Task3.isNestable(arr1, arr2);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("В первом и втором массивах максимальные элементы совпадают или " +
        "в первом массиве максимальный элемент больше")
    void isNestable_shouldReturnFalse_whenMaxElementsAreTheSame() {
        // given
        int[] arr1 = new int[] {2, 6, 3, 9};
        int[] arr2 = new int[] {9, 1};
        int[] arr3 = new int[] {8, 1};

        // when
        boolean result1 = Task3.isNestable(arr1, arr2);
        boolean result2 = Task3.isNestable(arr1, arr3);

        // then
        assertThat(result1).isEqualTo(false);
        assertThat(result2).isEqualTo(false);
    }

    @Test
    @DisplayName("В первом и втором массивах минимальные элементы совпадают или " +
        "в первом массиве минимальный элемент меньше")
    void isNestable_shouldReturnFalse_whenMinElementsAreTheSame() {
        // given
        int[] arr1 = new int[] {1, 6, 3, 9};
        int[] arr2 = new int[] {15, 1};
        int[] arr3 = new int[] {15, 2};

        // when
        boolean result1 = Task3.isNestable(arr1, arr2);
        boolean result2 = Task3.isNestable(arr1, arr3);

        // then
        assertThat(result1).isEqualTo(false);
        assertThat(result2).isEqualTo(false);
    }

    @Test
    @DisplayName("В первом и втором массивах максимальные и минимальные элементы совпадают")
    void isNestable_shouldReturnFalse_whenMaxAndMinElementsAreTheSame() {
        // given
        int[] arr1 = new int[] {6, 7, 2, 10, 1};
        int[] arr2 = new int[] {10, 5, 1};

        // when
        boolean result = Task3.isNestable(arr1, arr2);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Второй массив вложен в первый")
    void isNestable_shouldReturnFalse_whenSecondArrayIsNestedInFirstArray() {
        // given
        int[] arr1 = new int[] {6, 7, 2, 10, 1};
        int[] arr2 = new int[] {9, 5, 2};

        // when
        boolean result = Task3.isNestable(arr1, arr2);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Во втором массиве меньше двух элементов")
    void isNestable_shouldReturnFalse_whenInSecondArrayLessThanTwoElements() {
        // given
        int[] arr1 = new int[] {6, 7, 2, 10, 1};
        int[] arr2 = new int[] {15};
        int[] arr3 = new int[] { };

        // when
        boolean result1 = Task3.isNestable(arr1, arr2);
        boolean result2 = Task3.isNestable(arr1, arr3);

        // then
        assertThat(result1).isEqualTo(false);
        assertThat(result2).isEqualTo(false);
    }

    @Test
    @DisplayName("В первом массиве нет элементов")
    void isNestable_shouldReturnFalse_whenInFirstArrayNoElements() {
        // given
        int[] arr1 = new int[] { };
        int[] arr2 = new int[] {15, 1};

        // when
        boolean result = Task3.isNestable(arr1, arr2);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Один из массивов null")
    void minutesToSeconds_shouldThrowIllegalArgumentException() {
        // given
        int[] arr = new int[] {1, 2, 3, 4};

        assertThrows(IllegalArgumentException.class, () -> Task3.isNestable(arr, null));
        assertThrows(IllegalArgumentException.class, () -> Task3.isNestable(null, arr));
    }
}
