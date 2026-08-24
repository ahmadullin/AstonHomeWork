package org.example.Task_2;

public class Main {
    public static void main(String[] args) {
        //Характеристики круга
        Circle circle = new Circle(15,"Желтый", "Красный");
        circle.displayInfo();

        //Характеристики прямоугольника
        Rectangle rectangle = new Rectangle(4,5, "голубой", "черный");
        rectangle.displayInfo();

        //Характеристики треугольника
        Triangle triangle = new Triangle(5,6,7,"Красный","Зеленый");
        triangle.displayInfo();
    }
}
