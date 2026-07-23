package com.structural.bridge;

public final class BridgeDemo {

    private BridgeDemo() {
    }

    public static void run() {
        System.out.println("=== Bridge ===");

        RemoteControl tvRemote = new RemoteControl(new Tv());
        tvRemote.togglePower();
        tvRemote.volumeUp();
        tvRemote.togglePower();

        AdvancedRemoteControl radioRemote = new AdvancedRemoteControl(new Radio());
        radioRemote.togglePower();
        radioRemote.volumeUp();
        radioRemote.mute();
        radioRemote.togglePower();
    }
}
