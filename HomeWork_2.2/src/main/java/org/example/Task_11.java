/*
Задать пустой целочисленный массив длиной 100.
С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 ... 100;
 */

package org.example;

public class Task_11 {
    public static void cycleArrPut () {
        int [] arr = new int [100];
        int i = 0;
        do {
            arr[i] = ++i;
        }
        while (i < arr.length);
    }
}