package com.structural.facade;

public class Cpu {

    public void freeze() {
        System.out.println("CPU: freezing");
    }

    public void jump(long position) {
        System.out.println("CPU: jumping to 0x" + Long.toHexString(position));
    }

    public void execute() {
        System.out.println("CPU: executing");
    }
}
