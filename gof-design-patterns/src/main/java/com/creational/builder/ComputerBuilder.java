package com.creational.builder;

public interface ComputerBuilder {

    ComputerBuilder buildCpu();

    ComputerBuilder buildRam();

    ComputerBuilder buildStorage();

    ComputerBuilder buildGpu();

    Computer getResult();
}
