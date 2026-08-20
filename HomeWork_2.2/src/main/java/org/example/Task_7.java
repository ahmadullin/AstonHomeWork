/*
Напишите метод, которому в качестве параметра передается целое число.
Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.
Замечание: ноль считаем положительным числом.
 */

package org.example;
import java.util.Scanner;

public class Task_7 {
    public static boolean inputBool(int a) {
        return a < 0;
    }
}