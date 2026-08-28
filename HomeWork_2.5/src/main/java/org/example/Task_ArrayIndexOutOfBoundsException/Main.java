package org.example.Task_ArrayIndexOutOfBoundsException;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {
                {10, 20, 30, 40},
                {50, 60, 70, 80},
                {90, 100, 110, 120}
        };

        // Успешный доступ
        try {
            System.out.println(ArrayUtils.getElement(matrix, 1, 2));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        // Ошибка в строке
        try {
            System.out.println(ArrayUtils.getElement(matrix, 5, 1));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        // Ошибка в столбце
        try {
            System.out.println(ArrayUtils.getElement(matrix, 1, 10));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        // Ошибка в строке и столбце
        try {
            System.out.println(ArrayUtils.getElement(matrix, 5, 10));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        // Отрицательный индекс
        try {
            System.out.println(ArrayUtils.getElement(matrix, -1, 0));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        // Безопасный доступ
        System.out.println(ArrayUtils.safeGetElement(matrix, 1, 2));
        System.out.println(ArrayUtils.safeGetElement(matrix, 5, 1));
        System.out.println(ArrayUtils.safeGetElement(matrix, 1, 10));
        System.out.println(ArrayUtils.safeGetElement(matrix, -1, 0));
    }
}