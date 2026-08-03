package com.structural.flyweight;

public class TreeType {

    private final String name;
    private final String color;
    private final String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void render(int x, int y) {
        System.out.printf("Rendering %s tree (color=%s, texture=%s) at [%d, %d]%n", name, color, texture, x, y);
    }
}
