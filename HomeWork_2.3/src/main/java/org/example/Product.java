/*
Создать класс "Товар" с полями:
название, дата производства, производитель, страна происхождения, цена, состояние бронирования покупателем.
Конструктор класса должен заполнять эти поля при создании объекта.
Внутри класса «Товар» написать метод, который выводит информацию об объекте в консоль.
 */

package org.example;

public class Product {
    private String name;
    private String date;
    private String manufacturer;
    private String country;
    private double price;
    private boolean reservation;

    public Product(String name, String date, String manufacturer, String country, double price, boolean reservation) {
        this.name = name;
        this.date = date;
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.reservation = reservation;
    }

    public void displayinfo() {
        System.out.println("Name: " + name);
        System.out.println("Date: " + date);
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Country: " + country);
        System.out.println("Price: " + price);
        System.out.println("Reservation: " + reservation);
    }
}
