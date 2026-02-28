package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.server.managers.CollectionManager;
import itmo.cse.lab5.server.models.SpaceMarine;

/**
 * Команда {@code max_by_chapter}: выводит элемент с максимальным chapter.
 */
public class MaxByChapterCommand extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager менеджер коллекции
     */
    public MaxByChapterCommand(CollectionManager collectionManager) {
        super("max_by_chapter", "вывести с максимальным значением chapter");
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит элемент с максимальным значением поля chapter.
     *
     * @param args аргументы команды (не используются)
     */
    @Override
    public void execute(String[] args) {
        SpaceMarine max = collectionManager.maxByChapter();
        if (max == null) {
            System.out.println("Нет элементов с chapter");
        } else {
            System.out.println(max);
        }
    }
}
