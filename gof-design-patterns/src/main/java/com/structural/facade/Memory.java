package com.structural.facade;

public class Memory {

    public void load(long position, String data) {
        System.out.println("Memory: loading \"" + data + "\" at 0x" + Long.toHexString(position));
    }
}
