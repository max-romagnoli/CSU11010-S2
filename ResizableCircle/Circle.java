package com.company;

public class Circle implements GeometricObject {

    double radius;
    double perimeter;
    double area;

    Circle() {
        radius=1.0;
    }

    Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getPerimeter() {
        return 2*Math.PI*getRadius();
    }

    @Override
    public double getArea() {
        double r = getRadius();
        return Math.PI*(r*r);
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public void setArea(double area) {
        this.area = area;
    }

}
