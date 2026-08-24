/*
Создать класс Собака с наследованием от класса Животное.
У каждого животного есть ограничения на действия (бег: собака 500 м.; плавание: собака 10 м.).
Добавить подсчет созданных собак.
 */

package org.example.Task_1;

public class Dog extends Animal {
    static int dogCount = 0;
    public Dog (String name) {
        super(name);
        dogCount++;     // Подсчет созданных собак
    }

    // Ограничение на бег
    public void Run(int value) {
        if (value >= 0 & value <= 500) {
            super.Run(value);
        }
        else {
            System.out.println("\nСобака столько не может пробежать");
        }
    }

    // ограничение на плавание
    public void Swim (int value) {
        if (value >= 0 & value <= 10) {
            super.Swim(value);
        }
        else {
            System.out.println("\nСобака столько не может проплыть");
        }
    }
}
