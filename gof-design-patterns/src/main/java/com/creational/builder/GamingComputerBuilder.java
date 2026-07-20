package com.creational.builder;

public class GamingComputerBuilder implements ComputerBuilder {

    private String cpu;
    private String ram;
    private String storage;
    private String gpu;

    @Override
    public ComputerBuilder buildCpu() {
        cpu = "Intel Core i9";
        return this;
    }

    @Override
    public ComputerBuilder buildRam() {
        ram = "32GB DDR5";
        return this;
    }

    @Override
    public ComputerBuilder buildStorage() {
        storage = "2TB NVMe SSD";
        return this;
    }

    @Override
    public ComputerBuilder buildGpu() {
        gpu = "NVIDIA RTX 4090";
        return this;
    }

    @Override
    public Computer getResult() {
        return new Computer(cpu, ram, storage, gpu);
    }
}
