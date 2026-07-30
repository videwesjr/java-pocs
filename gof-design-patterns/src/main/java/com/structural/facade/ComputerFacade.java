package com.structural.facade;

public class ComputerFacade {

    private static final long BOOT_ADDRESS = 0x00L;
    private static final long BOOT_SECTOR = 0x1FC0L;
    private static final int SECTOR_SIZE = 512;

    private final Cpu cpu;
    private final Memory memory;
    private final HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new Cpu();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void start() {
        cpu.freeze();
        String bootData = hardDrive.read(BOOT_SECTOR, SECTOR_SIZE);
        memory.load(BOOT_ADDRESS, bootData);
        cpu.jump(BOOT_ADDRESS);
        cpu.execute();
    }
}
