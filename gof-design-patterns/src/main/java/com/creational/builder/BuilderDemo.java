package com.creational.builder;

public final class BuilderDemo {

    private BuilderDemo() {
    }

    public static void run() {
        System.out.println("=== Builder ===");

        ComputerAssembler assembler = new ComputerAssembler();

        Computer gamingComputer = assembler.assemble(new GamingComputerBuilder());
        Computer officeComputer = assembler.assemble(new OfficeComputerBuilder());

        System.out.println(gamingComputer);
        System.out.println(officeComputer);
    }
}
