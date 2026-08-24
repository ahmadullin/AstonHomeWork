package org.example.Task_1;

public class Main {
    public static void main(String[] args) {
        Dog dogBobik = new Dog("Бобик");
        dogBobik.Run(150);

        Cat cat1 = new Cat("Кошка1");
        Cat cat2 = new Cat("Кошка2");
        Cat cat3 = new Cat("Кошка3");
        Cat cat4 = new Cat("Кошка4");
        Cat cat5 = new Cat("Кошка5");
        Cat cat6 = new Cat("Кошка6");
        Cat cat7 = new Cat("Кошка7");
        Cat cat8 = new Cat("Кошка8");
        Cat cat9 = new Cat("Кошка9");
        Cat cat10 = new Cat("Кошка10");
        Cat cat11 = new Cat("Кошка11");

        // Создаем массив котов. Просим всех котов покушать из миски и потом выводим информацию о сытости котов в консоль. Сытый true, голодный - false.
        Cat [] arrCat = new Cat[] {cat1, cat2, cat3, cat4, cat5,  cat6, cat7, cat8, cat9, cat10, cat11};
        for (int i = 0; i < arrCat.length; i++) {
            arrCat[i].eat();
        }

    }
}