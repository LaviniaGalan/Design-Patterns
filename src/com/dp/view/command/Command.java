package com.dp.view.command;

public abstract class Command {
    private final String key;
    private final String text;

    public Command(String key, String text) {
        this.key = key;
        this.text = text;
    }

    public String getKey() {
        return key;
    }

    public String getText() {
        return text;
    }

    public abstract void execute();

}
