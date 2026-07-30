package com.structural.facade;

public class HardDrive {

    public String read(long lba, int size) {
        System.out.println("HardDrive: reading " + size + " bytes at sector 0x" + Long.toHexString(lba));
        return "boot data";
    }
}
