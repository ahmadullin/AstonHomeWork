/*
Создать массив из 5 товаров.
Пример:
// вначале объявляем массив объектов
Product[] productsArray = new Product[5];
// потом для каждой ячейки массива задаем объект
productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025",
               "Samsung Corp.", "Korea", 5599, true);
productsArray[1] = new Product(...);
...
productsArray[4] = new Product(...);
 */

package org.example;

public class Task_2 {
    public static void createArrays() {
        Product[] productsArray =  new Product[5];
        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025","Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Product("Samsung S25 Ultra", "01.02.2023","Samsung Corp.", "Korea", 5599, true);
        productsArray[2] = new Product("Samsung S25 Ultra", "01.02.2021","Samsung Corp.", "Korea", 5599, true);
        productsArray[3] = new Product("Samsung S25 Ultra", "01.02.2025","Samsung Corp.", "Korea", 5599, true);
        productsArray[4] = new Product("Samsung S25 Ultra", "01.02.2025","Samsung Corp.", "Korea", 5599, true);
    }
}
