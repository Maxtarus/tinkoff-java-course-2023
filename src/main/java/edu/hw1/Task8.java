package edu.hw1;

public final class Task8 {
    private static final int[] DX = {-2, -2, -1, -1, 1, 1, 2, 2};
    private static final int[] DY = {-1, 1, -2, 2, -2, 2, -1, 1};
    private static final int CHESSBOARD_LENGTH = 8;

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        if (!isBoardSizeValid(board)) {
            throw new IllegalArgumentException("Входной массив должен быть размера 8х8!");
        }

        if (!areBoardValuesValid(board)) {
            throw new IllegalArgumentException("Входной массив должен состоять только из нулей и единиц!");
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    if (isKnightUnderAttack(board, i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean isBoardSizeValid(int[][] board) {
        if (board.length != CHESSBOARD_LENGTH) {
            return false;
        }

        for (int[] x : board) {
            if (x.length != CHESSBOARD_LENGTH) {
                return false;
            }
        }

        return true;
    }

    private static boolean areBoardValuesValid(int[][] board) {
        for (int[] row : board) {
            for (int value : row) {
                if (value != 0 && value != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isKnightUnderAttack(int[][] board, int row, int col) {
        //Пара (DX[i];DY[i]), i = 0...7 - всевозможные смещения коня на шахматной доске
        for (int i = 0; i < DX.length; i++) {
            int newRow = row + DX[i];
            int newCol = col + DY[i];

            if (isValidMove(newRow, newCol) && board[newRow][newCol] == 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < CHESSBOARD_LENGTH && col >= 0 && col < CHESSBOARD_LENGTH;
    }
}
