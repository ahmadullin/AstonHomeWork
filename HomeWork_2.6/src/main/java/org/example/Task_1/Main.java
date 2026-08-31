package org.example.Task_1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.example.Task_1.StudentManagement.*;

public class Main {
    public static void main(String[] args) {
        // Создаем коллекцию студентов
        Set<Student> students = new HashSet<>();

        // Добавляем студентов
        students.add(new Student("Иван Петров", "Группа 101", 1, Arrays.asList(4, 5, 3, 4, 5)));
        students.add(new Student("Мария Сидорова", "Группа 102", 1, Arrays.asList(2, 3, 2, 3, 2)));
        students.add(new Student("Петр Иванов", "Группа 101", 2, Arrays.asList(5, 5, 5, 4, 5)));
        students.add(new Student("Анна Смирнова", "Группа 103", 2, Arrays.asList(3, 3, 4, 3, 3)));
        students.add(new Student("Сергей Козлов", "Группа 102", 1, Arrays.asList(2, 2, 3, 2, 2)));
        students.add(new Student("Елена Васильева", "Группа 101", 3, Arrays.asList(4, 4, 4, 5, 4)));

        // Удаляем студентов со средним баллом < 3
        removeLowPerformingStudents(students);

        // Переводим студентов на следующий курс
        promoteStudents(students);

        // Печатаем студентов на каждом курсе
        System.out.println("Студенты на 2 курсе:");
        printStudents(students, 2);

        System.out.println("\nСтуденты на 3 курсе:");
        printStudents(students, 3);

        System.out.println("\nСтуденты на 4 курсе:");
        printStudents(students, 4);
    }
}