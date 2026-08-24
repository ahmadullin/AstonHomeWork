package org.example.Task_2;

public class Triangle implements GeometricFigure {
    private double sideFirst;
    private double sideSecond;
    private double sideThird;
    private String fillColor;
    private String borderColor;

    public Triangle(double sideFirst, double sideSecond, double sideThird, String fillColor, String borderColor) {
        this.sideFirst = sideFirst;
        this.sideSecond = sideSecond;
        this.sideThird = sideThird;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getPerimeter() {
        return sideFirst + sideSecond + sideThird;
    }
    @Override
    public double getArea() {
        double p = getPerimeter() / 2;  // полупериметр
        return Math.sqrt(p * (p - sideFirst) * (p - sideSecond) * (p - sideThird));
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
