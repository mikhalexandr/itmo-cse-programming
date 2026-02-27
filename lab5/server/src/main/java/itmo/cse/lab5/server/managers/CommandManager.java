package itmo.cse.lab5.server.managers;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.common.exceptions.CommandExecutionException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommandManager {
    private static final int HISTORY_SIZE = 7;
    private final Map<String, Command> commands = new LinkedHashMap<>();
    private final List<String> history = new ArrayList<>();

    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    public void execute(String input) {
        if (input == null || input.trim().isEmpty()) {
            return;
        }

        String[] parts = input.trim().split("\\s+", 2);
        String name = parts[0].toLowerCase();
        String[] args = parts.length > 1 ? new String[]{parts[1]} : new String[]{};

        Command command = commands.get(name);
        if (command == null) {
            System.out.printf("Неизвестная команда: %s. Введите help для отображения списка команд.\n", name);
            return;
        }

        try {
            command.execute(args);
            addToHistory(name);
        } catch (CommandExecutionException e) {
            System.out.printf("Ошибка выполнения команды: %s%n\n", e.getMessage());
        }
    }

    public void addToHistory(String name) {
        history.add(name);
        if (history.size() > HISTORY_SIZE) {
            history.remove(0);
        }
    }

    public List<String> getHistory() {
        return history;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
