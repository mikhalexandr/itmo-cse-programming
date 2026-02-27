package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.server.managers.CollectionManager;
import itmo.cse.lab5.server.models.SpaceMarine;

public class HeadCommand extends Command {
    private final CollectionManager collectionManager;

    public HeadCommand(CollectionManager collectionManager) {
        super("head", "вывести первый элемент коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        SpaceMarine head = collectionManager.head();
        if (head == null) {
            System.out.println("Коллекция пуста.");
        } else {
            System.out.println(head);
        }
    }
}
