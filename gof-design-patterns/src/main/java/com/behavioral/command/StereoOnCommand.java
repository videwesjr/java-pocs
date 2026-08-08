package com.behavioral.command;

public class StereoOnCommand implements Command {

    private final Stereo stereo;
    private final int volume;

    public StereoOnCommand(Stereo stereo, int volume) {
        this.stereo = stereo;
        this.volume = volume;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setVolume(volume);
    }

    @Override
    public void undo() {
        stereo.off();
    }
}
