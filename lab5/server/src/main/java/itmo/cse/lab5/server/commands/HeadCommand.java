package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.server.managers.CollectionManager;
import itmo.cse.lab5.server.models.SpaceMarine;

/**
 * Команда {@code head}: выводит первый элемент коллекции.
 */
public class HeadCommand extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager менеджер коллекции
     */
    public HeadCommand(CollectionManager collectionManager) {
        super("head", "вывести первый элемент коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Печатает первый элемент коллекции или сообщение о пустой коллекции.
     *
     * @param args аргументы команды (не используются)
     */
    @Override
    public void execute(String[] args) {
        SpaceMarine head = collectionManager.head();
        if (head == null) {
            System.out.println("Коллекция пуста");
        } else {
            System.out.println(head);
        }
    }
}
