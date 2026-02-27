package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.server.input.InputHandler;
import itmo.cse.lab5.server.managers.CollectionManager;
import itmo.cse.lab5.server.models.SpaceMarine;

public class AddCommand extends Command {
    private final CollectionManager collectionManager;
    private final InputHandler inputHandler;

    public AddCommand(CollectionManager collectionManager, InputHandler inputHandler) {
        super("add", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.inputHandler = inputHandler;
    }

    @Override
    public void execute(String[] args) {
        SpaceMarine spaceMarine = inputHandler.readSpaceMarine();
        collectionManager.add(spaceMarine);
        System.out.println("Элемент успешно добавлен.");
    }
}
