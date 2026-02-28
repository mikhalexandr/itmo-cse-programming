package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.common.exceptions.CommandExecutionException;
import itmo.cse.lab5.server.input.InputHandler;
import itmo.cse.lab5.server.managers.CollectionManager;
import itmo.cse.lab5.server.models.SpaceMarine;

/**
 * Команда {@code update}: обновляет элемент коллекции по id.
 */
public class UpdateCommand extends Command {
    private final CollectionManager collectionManager;
    private final InputHandler inputHandler;

    /**
     * @param collectionManager менеджер коллекции
     * @param inputHandler обработчик интерактивного ввода
     */
    public UpdateCommand(CollectionManager collectionManager, InputHandler inputHandler) {
        super("update", "<id>", "обновить элемент коллекции по id");
        this.collectionManager = collectionManager;
        this.inputHandler = inputHandler;
    }

    /**
     * Обновляет элемент с переданным id новыми данными, считанными из консоли.
     *
     * @param args ожидается один аргумент: id
     * @throws CommandExecutionException если id не передан, некорректен или элемент не найден
     */
    @Override
    public void execute(String[] args) throws CommandExecutionException {
        if (args.length == 0) {
            throw new CommandExecutionException("Использование: update <id>");
        }
        try {
            String[] idArgs = args[0].trim().split("\\s+");
            if (idArgs.length != 1) {
                throw new CommandExecutionException("Формат: update <id>, затем ввод полей с новой строки");
            }

            int id = Integer.parseInt(idArgs[0]);
            SpaceMarine oldSpaceMarine = collectionManager.getById(id);
            if (oldSpaceMarine == null) {
                throw new CommandExecutionException(
                        String.format("Элемент с id=%d не найден", id)
                );
            }

            System.out.println("Введите новые значения полей:");
            SpaceMarine spaceMarine = inputHandler.readSpaceMarine(oldSpaceMarine);
            collectionManager.update(id, spaceMarine);
            System.out.println("Элемент успешно обновлён");
            System.out.println("Новое значение элемента:");
            System.out.println(collectionManager.getById(id));
        } catch (NumberFormatException e) {
            throw new CommandExecutionException("id должен быть числом");
        }
    }
}
