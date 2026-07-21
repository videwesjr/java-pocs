package com.creational.prototype;

public class Rectangle implements Shape {

    private final int width;
    private final int height;
    private final String color;

    public Rectangle(int width, int height, String color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    private Rectangle(Rectangle source) {
        this(source.width, source.height, source.color);
    }

    @Override
    public Shape clone() {
        return new Rectangle(this);
    }

    @Override
    public String toString() {
        return "Rectangle{width=%d, height=%d, color=%s}".formatted(width, height, color);
    }
}
