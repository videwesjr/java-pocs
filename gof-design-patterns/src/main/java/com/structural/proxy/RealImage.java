package com.structural.proxy;

public class RealImage implements Image {

    private final String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.printf("Loading %s from disk...%n", fileName);
    }

    @Override
    public void display() {
        System.out.printf("Displaying %s%n", fileName);
    }
}
