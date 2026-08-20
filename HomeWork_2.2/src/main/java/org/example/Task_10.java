/*
Задать целочисленный массив, состоящий из элементов 0 и 1.
Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
С помощью цикла и условия заменить 0 на 1, 1 на 0;
 */
package org.example;

public class Task_10 {
    public static void ArrPutInt (){
        int[] arr = {1,0,1,0,0,0,0,1,1,0,1,0,1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
            arr[i] = 0;
            } else  {
                arr[i] = 1;
            }
        }
    }
}
