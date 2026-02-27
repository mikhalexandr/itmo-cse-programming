package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.server.managers.CollectionManager;

public class InfoCommand extends Command {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info", "вывести информацию о коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        System.out.printf("Тип коллекции: %s%n", collectionManager.getType());
        System.out.printf("Дата инициализации: %s%n", collectionManager.getIntializationDate());
        System.out.printf("Количество элементов: %d%n", collectionManager.size());
    }
}
