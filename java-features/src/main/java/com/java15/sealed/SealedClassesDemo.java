package com.java15.sealed;

import java.util.Arrays;
import java.util.List;

/**
 * JEP 360: Sealed Classes (preview in Java 15, standard since Java 17).
 *
 * A sealed type names exactly which types may extend or implement it, so
 * the hierarchy is closed and fully known to the compiler. That is weaker
 * than final (subtypes are still allowed) and stronger than package-private
 * access (the permitted subtypes may be public and used by callers).
 *
 * Every permitted subtype must be in the same module (or the same package
 * for the unnamed module) and must pick a modifier of its own: final,
 * sealed, or non-sealed.
 */
public class SealedClassesDemo {

    sealed interface Shape permits Circle, Square, Rectangle {
        double area();
    }

    static final class Circle implements Shape {
        private final double radius;

        Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }

        @Override
        public String toString() {
            return "Circle(r=" + radius + ")";
        }
    }

    static final class Square implements Shape {
        private final double side;

        Square(double side) {
            this.side = side;
        }

        @Override
        public double area() {
            return side * side;
        }

        @Override
        public String toString() {
            return "Square(s=" + side + ")";
        }
    }

    // non-sealed reopens this branch: anyone may extend Rectangle, even
    // though Shape itself stays closed.
    static non-sealed class Rectangle implements Shape {
        final double width;
        final double height;

        Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public double area() {
            return width * height;
        }

        @Override
        public String toString() {
            return "Rectangle(" + width + "x" + height + ")";
        }
    }

    static final class Wall extends Rectangle {
        Wall(double width, double height) {
            super(width, height);
        }

        @Override
        public String toString() {
            return "Wall(" + width + "x" + height + ")";
        }
    }

    public static void main(String[] args) {
        List<Shape> shapes = List.of(
                new Circle(1),
                new Square(2),
                new Rectangle(2, 3),
                new Wall(10, 2.5));

        for (Shape shape : shapes) {
            System.out.printf("%-20s area=%7.3f  %s%n", shape, shape.area(), describe(shape));
        }

        System.out.println();
        System.out.println("Shape is sealed: " + Shape.class.isSealed());
        System.out.println("permitted subtypes: " + Arrays.stream(Shape.class.getPermittedSubclasses())
                .map(Class::getSimpleName)
                .toList());
        System.out.println("Rectangle is sealed: " + Rectangle.class.isSealed()
                + " (non-sealed, so it has no permits clause)");
    }

    // The compiler knows the hierarchy is closed, but exhaustiveness checking
    // for this kind of dispatch only arrives with pattern matching for switch
    // (JEP 441, Java 21), so the chain still needs a trailing branch.
    private static String describe(Shape shape) {
        if (shape instanceof Circle) {
            return "round";
        }
        if (shape instanceof Square) {
            return "equal sides";
        }
        if (shape instanceof Rectangle) {
            return "four corners";
        }
        throw new IllegalStateException("unreachable: Shape permits nothing else");
    }
}
