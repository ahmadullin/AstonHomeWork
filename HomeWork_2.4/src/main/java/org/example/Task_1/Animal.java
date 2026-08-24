/*
+Создать суперкласс Животное
+Все животные могут бежать и плыть.
+В качестве параметра каждому методу передается длина препятствия.
+Результатом выполнения действия будет печать в консоль. (Например, dogBobik.run(150); -> 'Бобик пробежал 150 м.');
+ Добавить подсчет созданных животных.

 */
package org.example.Task_1;

public abstract class Animal {

    static int animalCount = 0;
    String name;
    public Animal(String name) {
        this.name = name;
        animalCount++;  // Подсчет созданных животных
    }
    // Животное может бежать. В качестве параметра каждому методу передается длина препятствия.
    public void Run(int value) {
        System.out.println(name + " пробежал/а " + value + " метров");
    }

    // Животное может плыть. В качестве параметра каждому методу передается длина препятствия.
    public void Swim(int value) {
        System.out.println(name + " проплыл/а " + value + " метров");
    }

    public void getAnimalCount() {
        System.out.println("\nВсего животных создано: " + animalCount);
    }
}
