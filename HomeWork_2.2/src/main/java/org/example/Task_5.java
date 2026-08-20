/*
Напишите метод, принимающий на вход два целых числа и проверяющий,
что их сумма лежит в пределах от 10 до 20 (включительно),
если да – вернуть true, в противном случае – false.
 */

package org.example;

public class Task_5 {
    public static boolean checkSumInput(int a, int b) {
        return a + b > 10 && a + b <= 20;
    }
}
