package com.behavioral.command;

public final class CommandDemo {

    private CommandDemo() {
    }

    public static void run() {
        System.out.println("=== Command ===");

        Light livingRoomLight = new Light("Living room");
        Stereo stereo = new Stereo();

        RemoteControl remote = new RemoteControl();
        remote.setCommand(0, new LightOnCommand(livingRoomLight));
        remote.setCommand(1, new LightOffCommand(livingRoomLight));
        remote.setCommand(2, new StereoOnCommand(stereo, 11));

        remote.pressButton(0);
        remote.pressButton(2);
        remote.pressUndo();

        remote.pressButton(1);
        remote.pressUndo();
    }
}
