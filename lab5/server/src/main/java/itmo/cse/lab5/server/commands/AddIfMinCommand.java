package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.common.exceptions.CommandExecutionException;
import itmo.cse.lab5.server.input.InputHandler;
import itmo.cse.lab5.server.managers.CollectionManager;
import itmo.cse.lab5.server.models.SpaceMarine;

/**
 * Команда {@code add_if_min}: добавляет элемент, если он меньше минимального.
 */
public class AddIfMinCommand extends Command {
    private final CollectionManager collectionManager;
    private final InputHandler inputHandler;

    /**
     * @param collectionManager менеджер коллекции
     * @param inputHandler обработчик интерактивного ввода
     */
    public AddIfMinCommand(CollectionManager collectionManager, InputHandler inputHandler) {
        super("add_if_min", "<element>", "добавить элемент, если он меньше минимального");
        this.collectionManager = collectionManager;
        this.inputHandler = inputHandler;
    }

    /**
     * Считывает элемент и пытается добавить его по условию {@code add_if_min}.
     *
     * @param args аргументы команды (не используются)
     * @throws CommandExecutionException если не удалось дочитать поля элемента
     */
    @Override
    public void execute(String[] args) throws CommandExecutionException {
        SpaceMarine spaceMarine = inputHandler.readSpaceMarine();
        if (collectionManager.addIfMin(spaceMarine)) {
            System.out.println("Элемент добавлен");
        } else {
            System.err.println("Элемент НЕ добавлен, так как он НЕ меньше минимального");
        }
    }
}
