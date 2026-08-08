package com.behavioral.command;

public class Stereo {

    public void on() {
        System.out.println("Stereo is ON");
    }

    public void setVolume(int volume) {
        System.out.printf("Stereo volume set to %d%n", volume);
    }

    public void off() {
        System.out.println("Stereo is OFF");
    }
}
