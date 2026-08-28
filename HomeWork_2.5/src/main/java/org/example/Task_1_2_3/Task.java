package org.example.Task_1_2_3;

public class Task {
    public static int arrayString(String[][] arrays) throws MyArraySizeException, MyArrayDataException {
        // Проверка размера
        if (arrays == null || arrays.length != 4) {
            throw new MyArraySizeException("Массив должен быть размером 4x4");
        }

        for (String[] row : arrays) {
            if (row == null || row.length != 4) {
                throw new MyArraySizeException("Массив должен быть размером 4x4");
            }
        }

        // Преобразуем и суммируем
        int sum = 0;
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                try {
                    int number = Integer.parseInt(arrays[i][j]);
                    sum += number;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, arrays[i][j]);
                }
            }
        }
        return sum;
    }
}