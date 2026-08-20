/*
Напишите метод, который определяет, является ли год високосным,
и возвращает boolean (високосный - true, не високосный - false).
Каждый 4-й год является високосным, кроме каждого 100-го,
при этом каждый 400-й – високосный.
 */

package org.example;

public class Task_9 {
    public static boolean checkYear() {

        int year = 2008;
        return year % 4 ==0 && year % 1000 != 100;
    }
}