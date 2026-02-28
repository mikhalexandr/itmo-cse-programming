package itmo.cse.lab5.server.managers;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.common.exceptions.CommandExecutionException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Хранит зарегистрированные команды и исполняет их по строковому вводу.
 */
public class CommandManager {
    private static final int HISTORY_SIZE = 5;
    private final Map<String, Command> commands = new LinkedHashMap<>();
    private final List<String> history = new ArrayList<>();

    /**
     * Регистрирует новую команду.
     *
     * @param command экземпляр команды
     */
    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    /**
     * Парсит пользовательский ввод и вызывает соответствующую команду.
     *
     * @param input строка, введенная пользователем
     */
    public void execute(String input) {
        if (input == null || input.trim().isEmpty()) {
            return;
        }

        String[] parts = input.trim().split("\\s+", 2);
        String name = parts[0].toLowerCase();
        String[] args = parts.length > 1 ? new String[]{parts[1]} : new String[]{};

        Command command = commands.get(name);
        if (command == null) {
            System.err.printf("Неизвестная команда: %s. Введите help для отображения списка команд%n", name);
            return;
        }

        try {
            command.execute(args);
            addToHistory(name);
        } catch (CommandExecutionException e) {
            System.err.printf("Ошибка выполнения команды: %s%n", e.getMessage());
        }
    }

    /**
     * Добавляет название команды в историю с ограничением по размеру.
     *
     * @param name имя выполненной команды
     */
    public void addToHistory(String name) {
        history.add(name);
        if (history.size() > HISTORY_SIZE) {
            history.remove(0);
        }
    }

    /**
     * @return список недавно выполненных команд
     */
    public List<String> getHistory() {
        return history;
    }

    /**
     * @return карта зарегистрированных команд
     */
    public Map<String, Command> getCommands() {
        return commands;
    }
}
