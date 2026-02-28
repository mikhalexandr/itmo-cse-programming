package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.server.managers.CollectionManager;

/**
 * Команда {@code show}: выводит все элементы коллекции.
 */
public class ShowCommand extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager менеджер коллекции
     */
    public ShowCommand(CollectionManager collectionManager) {
        super("show", "вывести все элементы коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Печатает все элементы коллекции в строковом представлении.
     *
     * @param args аргументы команды (не используются)
     */
    @Override
    public void execute(String[] args) {
        if (collectionManager.size() == 0) {
            System.out.println("Коллекция пуста");
            return;
        }
        collectionManager.getCollection().forEach(System.out::println);
    }
}
