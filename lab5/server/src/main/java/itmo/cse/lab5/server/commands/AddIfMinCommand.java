package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.server.input.InputHandler;
import itmo.cse.lab5.server.managers.CollectionManager;
import itmo.cse.lab5.server.models.SpaceMarine;

public class AddIfMinCommand extends Command {
    private final CollectionManager collectionManager;
    private final InputHandler inputHandler;

    public AddIfMinCommand(CollectionManager collectionManager, InputHandler inputHandler) {
        super("add_if_min", "добавить элемент, если он меньше минимального");
        this.collectionManager = collectionManager;
        this.inputHandler = inputHandler;
    }

    @Override
    public void execute(String[] args) {
        SpaceMarine spaceMarine = inputHandler.readSpaceMarine();
        if (collectionManager.addIfMin(spaceMarine)) {
            System.out.println("Элемент добавлен");
        } else {
            System.out.println("Элемент НЕ добавлен, так как он НЕ меньше минимального.");
        }
    }
}
