package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.server.managers.CommandManager;

public class HelpCommand extends Command {
    private final CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        commandManager.getCommands()
                .forEach((name, command)
                        -> System.out.printf("  %-25s — %s%n",  name, command.getDescription()));
    }
}
