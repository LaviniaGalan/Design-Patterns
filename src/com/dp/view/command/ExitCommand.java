package com.dp.view.command;

public class ExitCommand extends Command{

    public ExitCommand(String key, String text) {
        super(key, text);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
