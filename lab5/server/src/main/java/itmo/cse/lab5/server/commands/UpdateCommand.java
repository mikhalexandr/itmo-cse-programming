package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.common.exceptions.CommandExecutionException;
import itmo.cse.lab5.server.input.InputHandler;
import itmo.cse.lab5.server.managers.CollectionManager;
import itmo.cse.lab5.server.models.SpaceMarine;

public class UpdateCommand extends Command {
    private final CollectionManager collectionManager;
    private final InputHandler inputHandler;

    public UpdateCommand(CollectionManager collectionManager, InputHandler inputHandler) {
        super("update", "обновить элемент коллекции по id");
        this.collectionManager = collectionManager;
        this.inputHandler = inputHandler;
    }

    @Override
    public void execute(String[] args) throws CommandExecutionException {
        if (args.length == 0) {
            throw new CommandExecutionException("Использование: update <id>");
        }
        try {
            int id = Integer.parseInt(args[0]);
            if (collectionManager.getById(id) == null) {
                throw new CommandExecutionException(
                        String.format("Элемент с id=%d не найден", id)
                );
            }
            SpaceMarine spaceMarine = inputHandler.readSpaceMarine();
            collectionManager.update(id, spaceMarine);
            System.out.println("Элемент успешно обновлён.");
        } catch (NumberFormatException e) {
            throw new CommandExecutionException("id должен быть числом.");
        }
    }
}
