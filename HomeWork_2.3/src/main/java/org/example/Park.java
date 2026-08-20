/*
Создать класс Park с внутренним классом, с помощью объектов которого можно хранить информацию об аттракционах, времени их работы и стоимости.
 */

package org.example;

public class Park {
    private Attraction attraction;
    public class Attraction{
        private String time;
        private double price;
    }
}
