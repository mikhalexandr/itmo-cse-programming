package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.server.managers.CommandManager;

/**
 * Команда {@code history}: выводит историю выполненных команд.
 */
public class HistoryCommand extends Command {
    private final CommandManager commandManager;

    /**
     * @param commandManager менеджер команд
     */
    public HistoryCommand(CommandManager commandManager) {
        super("history", "вывести последние 5 команд");
        this.commandManager = commandManager;
    }

    /**
     * Печатает список последних выполненных команд.
     *
     * @param args аргументы команды (не используются)
     */
    @Override
    public void execute(String[] args) {
        var history = commandManager.getHistory();
        if (history.isEmpty()) {
            System.out.println("История команд пуста");
            return;
        }
        System.out.println("Последние команды:");
        history.forEach(command -> System.out.println("  " + command));
    }
}
