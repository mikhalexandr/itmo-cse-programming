package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.server.managers.CollectionManager;

/**
 * Команда {@code info}: выводит информацию о текущей коллекции.
 */
public class InfoCommand extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager менеджер коллекции
     */
    public InfoCommand(CollectionManager collectionManager) {
        super("info", "вывести информацию о коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Печатает тип коллекции, дату инициализации и количество элементов.
     *
     * @param args аргументы команды (не используются)
     */
    @Override
    public void execute(String[] args) {
        System.out.printf("Тип коллекции: %s%n", collectionManager.getType());
        System.out.printf("Дата инициализации: %s%n", collectionManager.getInitializationDateFormatted());
        System.out.printf("Количество элементов: %d%n", collectionManager.size());
    }
}
