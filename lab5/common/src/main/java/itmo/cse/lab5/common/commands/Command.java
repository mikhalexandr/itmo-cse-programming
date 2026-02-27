package itmo.cse.lab5.common.commands;

import itmo.cse.lab5.common.exceptions.CommandExecutionException;

public abstract class Command {
    private final String name;
    private final String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void execute(String[] args) throws CommandExecutionException;
}
