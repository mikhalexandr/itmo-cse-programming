package itmo.cse.lab5.common.commands;

import itmo.cse.lab5.common.exceptions.CommandExecutionException;

/**
 * Базовый абстрактный тип команды.
 */
public abstract class Command {
    private final String name;
    private final String arguments;
    private final String description;

    /**
     * Создает команду с явным описанием аргументов.
     *
     * @param name имя команды
     * @param arguments ожидаемые аргументы в текстовом виде
     * @param description описание назначения команды
     */
    public Command(String name, String arguments, String description) {
        this.name = name;
        this.arguments = arguments;
        this.description = description;
    }

    /**
     * Создает команду без обязательных аргументов.
     *
     * @param name имя команды
     * @param description описание назначения команды
     */
    public Command(String name, String description) {
        this(name, "", description);
    }

    /**
     * @return имя команды
     */
    public String getName() {
        return name;
    }

    /**
     * @return строка с описанием аргументов команды
     */
    public String getArgs() {
        return arguments;
    }

    /**
     * @return описание команды
     */
    public String getDescription() {
        return description;
    }

    /**
     * Выполняет команду.
     *
     * @param args аргументы команды
     * @throws CommandExecutionException если выполнить команду не удалось
     */
    public abstract void execute(String[] args) throws CommandExecutionException;
}
