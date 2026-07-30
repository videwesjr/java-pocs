package com.structural.facade;

public final class FacadeDemo {

    private FacadeDemo() {
    }

    public static void run() {
        System.out.println("=== Facade ===");

        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}
