package com.java14.records;

import java.util.HashSet;
import java.util.Set;

/**
 * JEP 359: Records (preview in Java 14, standard since Java 16).
 *
 * A record is a transparent carrier for immutable data. The compiler
 * generates the canonical constructor, accessors, equals, hashCode and
 * toString from the component list.
 */
public class RecordsDemo {

    record Point(int x, int y) {

        static Point origin() {
            return new Point(0, 0);
        }

        double distanceTo(Point other) {
            return Math.hypot(x - other.x, y - other.y);
        }
    }

    public static void main(String[] args) {
        Point a = new Point(3, 4);
        Point b = new Point(3, 4);

        System.out.println("toString: " + a);
        System.out.println("accessors: x=" + a.x() + ", y=" + a.y());
        System.out.println("equals: " + a.equals(b) + ", same hashCode: " + (a.hashCode() == b.hashCode()));
        System.out.println("distance to origin: " + a.distanceTo(Point.origin()));

        Set<Point> points = new HashSet<>();
        points.add(a);
        points.add(b);
        System.out.println("set size after adding equal points: " + points.size());
    }
}
