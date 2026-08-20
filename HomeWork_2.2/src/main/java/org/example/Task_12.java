/*
Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом
и числа меньшие 6 умножить на 2
 */

package org.example;

public class Task_12 {
    public static void workInArr(){
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for(int i =0; i < arr.length; i++){
            if(arr[i] < 6){
                arr[i] = arr[i] * 2;
            }
        }
    }
}
