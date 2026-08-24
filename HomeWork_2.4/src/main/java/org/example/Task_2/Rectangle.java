package org.example.Task_2;

public class Rectangle implements GeometricFigure {
    private double length;
    private double width;
    private String fillColor;
    private String borderColor;
    public Rectangle (double length, double width, String fillColor, String borderColor) {
        this.length = length;
        this.width = width;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getPerimeter() {
        return (length + width) * 2;
    }
    @Override
    public double getArea() {
        return length * width;
    }
    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}
