package com.behavioral.command;

public class RemoteControl {

    private static final int SLOT_COUNT = 3;

    private final Command[] onCommands = new Command[SLOT_COUNT];
    private Command lastCommand = new NoCommand();

    public RemoteControl() {
        Command noCommand = new NoCommand();
        for (int i = 0; i < SLOT_COUNT; i++) {
            onCommands[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command command) {
        onCommands[slot] = command;
    }

    public void pressButton(int slot) {
        Command command = onCommands[slot];
        command.execute();
        lastCommand = command;
    }

    public void pressUndo() {
        lastCommand.undo();
    }
}
