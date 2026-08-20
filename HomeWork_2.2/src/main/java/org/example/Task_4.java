/*
Создайте метод compareNumbers(), в теле которого объявите две int переменные a и b,
и инициализируйте их любыми значениями, которыми захотите.
Если a больше или равно b, то необходимо вывести в консоль сообщение “a >= b”,
в противном случае “a < b”;
 */

package org.example;

public class Task_4 {
    public static void compareNumbers() {
        int a = 20;
        int b = 10;
        if (a >= b) {
            System.out.println("a >= b");
        } else System.out.println("a < b");
    }
}
