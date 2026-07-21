package com.creational.prototype;

public final class PrototypeDemo {

    private PrototypeDemo() {
    }

    public static void run() {
        System.out.println("=== Prototype ===");

        ShapeRegistry registry = new ShapeRegistry();
        registry.register("circle", new Circle(0, 0, 10, "red"));
        registry.register("rectangle", new Rectangle(20, 10, "blue"));

        Shape circleClone = registry.create("circle");
        Shape rectangleClone = registry.create("rectangle");

        System.out.println(circleClone);
        System.out.println(rectangleClone);
    }
}
