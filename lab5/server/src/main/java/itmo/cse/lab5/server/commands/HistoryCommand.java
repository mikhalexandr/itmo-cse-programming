package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.server.managers.CommandManager;

public class HistoryCommand extends Command {
    private final CommandManager commandManager;

    public HistoryCommand(CommandManager commandManager) {
        super("history", "вывести последние 7 команд");
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        var history = commandManager.getHistory();
        if (history.isEmpty()) {
            System.out.println("История команд пуста.");
            return;
        }
        System.out.println("Последние команды:");
        history.forEach(command -> System.out.println("  " + command));
    }
}
