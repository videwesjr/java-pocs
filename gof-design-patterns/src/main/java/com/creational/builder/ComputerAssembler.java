package com.creational.builder;

public class ComputerAssembler {

    public Computer assemble(ComputerBuilder builder) {
        return builder.buildCpu()
                .buildRam()
                .buildStorage()
                .buildGpu()
                .getResult();
    }
}
