package com.creational.prototype;

public class Circle implements Shape {

    private final int x;
    private final int y;
    private final int radius;
    private final String color;

    public Circle(int x, int y, int radius, String color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    private Circle(Circle source) {
        this(source.x, source.y, source.radius, source.color);
    }

    @Override
    public Shape clone() {
        return new Circle(this);
    }

    @Override
    public String toString() {
        return "Circle{x=%d, y=%d, radius=%d, color=%s}".formatted(x, y, radius, color);
    }
}
