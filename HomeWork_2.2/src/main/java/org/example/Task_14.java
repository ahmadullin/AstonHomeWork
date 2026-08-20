/*
Написать метод, принимающий на вход два аргумента: len и initialValue,
и возвращающий одномерный массив типа int длиной len,
каждая ячейка которого равна initialValue.
 */

package org.example;

public class Task_14 {
    public static int[] putInArr(int len, int initialValue) {
        // Запись массива
        int [] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }
}
