package com.structural.bridge;

public class Tv implements Device {

    private boolean enabled;
    private int volume = 30;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void enable() {
        enabled = true;
        System.out.println("TV: turned on");
    }

    @Override
    public void disable() {
        enabled = false;
        System.out.println("TV: turned off");
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int percent) {
        volume = Math.clamp(percent, 0, 100);
        System.out.println("TV: volume set to " + volume);
    }

    @Override
    public String getName() {
        return "TV";
    }
}
