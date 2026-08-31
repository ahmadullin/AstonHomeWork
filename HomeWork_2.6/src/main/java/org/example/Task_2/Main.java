package org.example.Task_2;

public class Main {
    public static void main(String[] args) {
        PhoneDirectory phoneDirectory = new PhoneDirectory();

        // Добавляем записи
        phoneDirectory.add("Иванов", "+7-495-123-45-67");
        phoneDirectory.add("Петров", "+7-495-234-56-78");
        phoneDirectory.add("Сидоров", "+7-495-345-67-89");
        phoneDirectory.add("Иванов", "+7-495-456-78-90"); // второй номер для Иванова
        phoneDirectory.add("Иванов", "+7-495-567-89-01"); // третий номер для Иванова
        phoneDirectory.add("Петров", "+7-495-678-90-12"); // второй номер для Петрова
        phoneDirectory.add("Смирнов", "+7-495-789-01-23");

        System.out.println("\nПоиск по фамилии");

        phoneDirectory.get("Иванов");
        phoneDirectory.get("Петров");
        phoneDirectory.get("Сидоров");
        phoneDirectory.get("Смирнов");
        phoneDirectory.get("Козлов");
    }
}

