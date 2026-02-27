package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.server.managers.CollectionManager;
import itmo.cse.lab5.server.models.SpaceMarine;

public class MaxByChapterCommand extends Command {
    private final CollectionManager collectionManager;

    public MaxByChapterCommand(CollectionManager collectionManager) {
        super("max_by_chapter", "вывести с максимальным значением chapter");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        SpaceMarine max = collectionManager.maxByChapter();
        if (max == null) {
            System.out.println("Нет элементов с chapter.");
        } else {
            System.out.println(max);
        }
    }
}
