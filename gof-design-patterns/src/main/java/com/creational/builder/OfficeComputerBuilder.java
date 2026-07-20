package com.creational.builder;

public class OfficeComputerBuilder implements ComputerBuilder {

    private String cpu;
    private String ram;
    private String storage;
    private String gpu;

    @Override
    public ComputerBuilder buildCpu() {
        cpu = "Intel Core i5";
        return this;
    }

    @Override
    public ComputerBuilder buildRam() {
        ram = "16GB DDR4";
        return this;
    }

    @Override
    public ComputerBuilder buildStorage() {
        storage = "512GB SSD";
        return this;
    }

    @Override
    public ComputerBuilder buildGpu() {
        gpu = "Integrated Graphics";
        return this;
    }

    @Override
    public Computer getResult() {
        return new Computer(cpu, ram, storage, gpu);
    }
}
