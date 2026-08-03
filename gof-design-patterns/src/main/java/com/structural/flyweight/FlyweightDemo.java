package com.structural.flyweight;

public final class FlyweightDemo {

    private FlyweightDemo() {
    }

    public static void run() {
        System.out.println("=== Flyweight ===");

        Forest forest = new Forest();
        forest.plantTree(1, 2, "Oak", "Green", "OakTexture");
        forest.plantTree(5, 6, "Oak", "Green", "OakTexture");
        forest.plantTree(3, 8, "Pine", "DarkGreen", "PineTexture");
        forest.plantTree(9, 1, "Oak", "Green", "OakTexture");

        forest.render();

        System.out.printf("Trees planted: %d, TreeType objects created: %d%n",
                forest.getTreeCount(), TreeFactory.getCreatedTypeCount());
    }
}
