package com.creational.builder;

public final class Computer {

    private final String cpu;
    private final String ram;
    private final String storage;
    private final String gpu;

    Computer(String cpu, String ram, String storage, String gpu) {
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
        this.gpu = gpu;
    }

    @Override
    public String toString() {
        return "Computer{cpu=%s, ram=%s, storage=%s, gpu=%s}".formatted(cpu, ram, storage, gpu);
    }
}
