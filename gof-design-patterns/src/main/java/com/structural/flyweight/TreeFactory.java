package com.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public final class TreeFactory {

    private static final Map<String, TreeType> TREE_TYPES = new HashMap<>();

    private TreeFactory() {
    }

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + "-" + color + "-" + texture;
        return TREE_TYPES.computeIfAbsent(key, k -> {
            System.out.printf("Creating new TreeType: %s%n", key);
            return new TreeType(name, color, texture);
        });
    }

    public static int getCreatedTypeCount() {
        return TREE_TYPES.size();
    }
}
