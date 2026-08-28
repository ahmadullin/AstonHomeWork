package org.example.Task_ArrayIndexOutOfBoundsException;

public class ArrayUtils {

    // Метод, который может выбросить ArrayIndexOutOfBoundsException для двумерного массива
    public static int getElement(int[][] array, int row, int col) throws ArrayIndexOutOfBoundsException {
        if (row < 0 || row >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
                    "Строка " + row + " вне диапазона [0, " + (array.length - 1) + "]"
            );
        }
        if (col < 0 || col >= array[row].length) {
            throw new ArrayIndexOutOfBoundsException(
                    "Столбец " + col + " вне диапазона [0, " + (array[row].length - 1) + "] в строке " + row
            );
        }
        return array[row][col];
    }

    // Метод для безопасного доступа к элементу двумерного массива
    public static int safeGetElement(int[][] array, int row, int col) {
        try {
            return array[row][col];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка доступа к элементу [" + row + "][" + col + "]: " + e.getMessage());
            return -1; // или другое значение по умолчанию
        }
    }

    // Метод для вывода матрицы
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}