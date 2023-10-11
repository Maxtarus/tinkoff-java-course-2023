package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Проверка, что кони расставлены на шахматной доске так, что ни один конь не может захватить другого коня")
class Task8Test {
    @Test
    @DisplayName("Конь не под атакой")
    void knightBoardCapture_shouldReturnTrue_whenKnightIsNotUnderAttack() {
        //given
        int[][] board = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        //when
        boolean result = Task8.knightBoardCapture(board);

        //then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Конь под атакой")
    void knightBoardCapture_shouldReturnFalse_whenKnightIsUnderAttack() {
        //given
        int[][] board = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        //when
        boolean result = Task8.knightBoardCapture(board);

        //then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Во входной матрице есть значение, отличное от нуля и единицы")
    void knightBoardCapture_shouldThrowIllegalArgumentException_whenBoardValueIsNotValid() {
        //given
        int[][] board = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 2, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        // then
        assertThrows(IllegalArgumentException.class, () -> Task8.knightBoardCapture(board));
    }

    @Test
    @DisplayName("Входная матрица имеет неверный размер")
    void knightBoardCapture_shouldThrowIllegalArgumentException_whenBoardSizeIsNotValid() {
        //given
        //7 строчек
        int[][] board1 = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 2, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
        };

        //9 столбцов
        int[][] board2 = {
            {0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0}
        };

        // then
        assertThrows(IllegalArgumentException.class, () -> Task8.knightBoardCapture(board1));
        assertThrows(IllegalArgumentException.class, () -> Task8.knightBoardCapture(board2));
    }
}
