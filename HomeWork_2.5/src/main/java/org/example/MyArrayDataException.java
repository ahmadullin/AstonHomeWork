package org.example;

public class MyArrayDataException extends Exception {
    private int row;
    private int col;
    private String value;

    public MyArrayDataException(int row, int col, String value) {
        super("Не удалось преобразовать значение '" + value + "' в число в ячейке [" + row + "][" + col + "]");
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getValue() {
        return value;
    }
}
