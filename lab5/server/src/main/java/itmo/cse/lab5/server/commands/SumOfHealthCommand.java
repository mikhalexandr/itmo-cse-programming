package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.server.managers.CollectionManager;

public class SumOfHealthCommand extends Command {
    private final CollectionManager collectionManager;

    public SumOfHealthCommand(CollectionManager collectionManager) {
        super("sum_of_health", "вывести сумму здоровья всех элементов");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        System.out.printf("Сумма здоровья: %f", collectionManager.sumOfHealth());
    }
}
