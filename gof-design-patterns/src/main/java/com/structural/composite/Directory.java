package com.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemComponent {

    private final String name;
    private final List<FileSystemComponent> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return children.stream()
                .mapToLong(FileSystemComponent::getSize)
                .sum();
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "+ " + name + " (" + getSize() + " bytes)");
        for (FileSystemComponent child : children) {
            child.print(indent + "  ");
        }
    }
}
