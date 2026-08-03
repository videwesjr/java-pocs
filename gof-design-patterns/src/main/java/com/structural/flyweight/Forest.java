package com.structural.flyweight;

import java.util.ArrayList;
import java.util.List;

public class Forest {

    private final List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, String color, String texture) {
        TreeType type = TreeFactory.getTreeType(name, color, texture);
        trees.add(new Tree(x, y, type));
    }

    public void render() {
        trees.forEach(Tree::render);
    }

    public int getTreeCount() {
        return trees.size();
    }
}
