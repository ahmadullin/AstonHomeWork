package org.example;

public class Main {
    public static void main(String[] args) {

        // ТЕСТ 1: ПРАВИЛЬНЫЙ МАССИВ
        System.out.println();
        String[][] correctArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int sum = Task.arrayString(correctArray);
            System.out.println("Массив успешно обработан!");
            System.out.println("Сумма всех элементов: " + sum);
        } catch (MyArraySizeException e) {
            System.out.println("ОШИБКА РАЗМЕРА: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("ОШИБКА ДАННЫХ: " + e.getMessage());
            System.out.println("Ячейка: [" + e.getRow() + "][" + e.getCol() + "]");
            System.out.println("Некорректное значение: '" + e.getValue() + "'");
        }

        // ТЕСТ 2: МАССИВ С ОШИБКОЙ В ДАННЫХ
        System.out.println();
        String[][] arrayWithDataError = {
                {"1", "2", "3", "4"},
                {"5", "шесть", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int sum = Task.arrayString(arrayWithDataError);
            System.out.println("Массив успешно обработан!");
            System.out.println("Сумма всех элементов: " + sum);
        } catch (MyArraySizeException e) {
            System.out.println("ОШИБКА РАЗМЕРА: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("ОШИБКА ДАННЫХ: " + e.getMessage());
            System.out.println("Ячейка: [" + e.getRow() + "][" + e.getCol() + "]");
            System.out.println("Некорректное значение: '" + e.getValue() + "'");
        }

        // ТЕСТ 3: НЕПРАВИЛЬНЫЙ РАЗМЕР
        System.out.println();
        String[][] wrongSizeArray = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };

        try {
            int sum = Task.arrayString(wrongSizeArray);
            System.out.println("Массив успешно обработан!");
            System.out.println("Сумма всех элементов: " + sum);
        } catch (MyArraySizeException e) {
            System.out.println("ОШИБКА РАЗМЕРА: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("ОШИБКА ДАННЫХ: " + e.getMessage());
            System.out.println("Ячейка: [" + e.getRow() + "][" + e.getCol() + "]");
            System.out.println("Некорректное значение: '" + e.getValue() + "'");
        }

        // ТЕСТ 4: МАССИВ С РАЗНЫМИ ЧИСЛАМИ
        System.out.println();

        String[][] anotherCorrectArray = {
                {"10", "20", "30", "40"},
                {"50", "60", "70", "80"},
                {"90", "100", "110", "120"},
                {"130", "140", "150", "160"}
        };

        try {
            int sum = Task.arrayString(anotherCorrectArray);
            System.out.println("Массив успешно обработан!");
            System.out.println("Сумма всех элементов: " + sum);
        } catch (MyArraySizeException e) {
            System.out.println("ОШИБКА РАЗМЕРА: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("ОШИБКА ДАННЫХ: " + e.getMessage());
            System.out.println("Ячейка: [" + e.getRow() + "][" + e.getCol() + "]");
            System.out.println("Некорректное значение: '" + e.getValue() + "'");
        }
    }
}