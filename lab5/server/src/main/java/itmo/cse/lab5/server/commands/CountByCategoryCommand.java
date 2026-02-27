package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.common.exceptions.CommandExecutionException;
import itmo.cse.lab5.server.managers.CollectionManager;
import itmo.cse.lab5.server.models.AstartesCategory;

public class CountByCategoryCommand extends Command {
    private final CollectionManager collectionManager;

    public CountByCategoryCommand(CollectionManager collectionManager) {
        super("count_by_category", "вывести количество элементов с заданной категорией");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecutionException {
        if (args.length == 0) {
            throw new CommandExecutionException("Использование: count_by_category <category>");
        }
        try {
            AstartesCategory category = AstartesCategory.valueOf(args[0].toUpperCase());
            long count = collectionManager.countByCategory(category);
            System.out.printf("Количество элементов с категорией %s:%d%n",  category, count);
        } catch (IllegalArgumentException e) {
            throw new CommandExecutionException("Неизвестная категория: " + args[0]);
        }
    }
}
