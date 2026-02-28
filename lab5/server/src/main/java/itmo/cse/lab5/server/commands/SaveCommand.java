package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.common.exceptions.FileWriteException;
import itmo.cse.lab5.server.managers.CollectionManager;
import itmo.cse.lab5.server.managers.FileManager;

/**
 * Команда {@code save}: сохраняет коллекцию в файл.
 */
public class SaveCommand extends Command {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    /**
     * @param collectionManager менеджер коллекции
     * @param fileManager менеджер файловых операций
     */
    public SaveCommand(CollectionManager collectionManager, FileManager fileManager) {
        super("save", "сохранит коллекцию в файл");
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    /**
     * Сохраняет текущую коллекцию в файл.
     *
     * @param args аргументы команды (не используются)
     */
    @Override
    public void execute(String[] args) {
        try {
            fileManager.save(collectionManager.getCollection());
            System.out.println("Коллекция сохранена");
        } catch (FileWriteException e) {
            System.out.printf("Ошибка сохранения: %s", e.getMessage());
        }
    }
}
