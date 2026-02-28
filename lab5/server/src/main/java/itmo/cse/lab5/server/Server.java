package itmo.cse.lab5.server;

import itmo.cse.lab5.common.exceptions.FileReadException;
import itmo.cse.lab5.server.commands.*;
import itmo.cse.lab5.server.input.InputHandler;
import itmo.cse.lab5.server.managers.CollectionManager;
import itmo.cse.lab5.server.managers.CommandManager;
import itmo.cse.lab5.server.managers.FileManager;

import java.util.Scanner;

/**
 * Точка входа серверного приложения.
 */
public final class Server {

    private Server() {
        throw new UnsupportedOperationException("Это утилитарный класс, его нельзя инстанцировать");
    }

    /**
     * Запускает интерактивный цикл командного интерпретатора.
     *
     * @param args первый аргумент должен содержать путь к JSON-файлу коллекции
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Использование: java -jar [SERVER_JAR] <путь к файлу>");
            System.exit(1);
        }
        String filePath = args[0];
        if (filePath == null || filePath.isEmpty()) {
            System.err.println("Путь к файлу неверен");
            System.exit(1);
        }
        Scanner scanner = new Scanner(System.in);
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager(filePath);
        InputHandler inputHandler = new InputHandler(scanner);
        CommandManager commandManager = new CommandManager();

        try {
            collectionManager.setCollection(fileManager.load());
            System.out.printf("Загружено элементов: %d%n", collectionManager.size());
        } catch (FileReadException e) {
            System.err.printf("Ошибка загрузки: %s%n", e.getMessage());
        }

        registerCommands(commandManager, collectionManager, inputHandler, fileManager);

        System.out.println("Введите help для списка команд.");
        while (true) {
            System.out.print("$ ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String input = scanner.nextLine();
            commandManager.execute(input);
        }
    }

    /**
     * Регистрирует все поддерживаемые команды.
     *
     * @param commandManager менеджер команд
     * @param collectionManager менеджер коллекции
     * @param inputHandler обработчик интерактивного ввода
     * @param fileManager менеджер чтения/записи файла
     */
    public static void registerCommands(CommandManager commandManager, CollectionManager collectionManager,
                                        InputHandler inputHandler, FileManager fileManager) {
        commandManager.register(new HelpCommand(commandManager));
        commandManager.register(new InfoCommand(collectionManager));
        commandManager.register(new ShowCommand(collectionManager));
        commandManager.register(new AddCommand(collectionManager, inputHandler));
        commandManager.register(new UpdateCommand(collectionManager, inputHandler));
        commandManager.register(new RemoveByIdCommand(collectionManager));
        commandManager.register(new ClearCommand(collectionManager));
        commandManager.register(new SaveCommand(collectionManager, fileManager));
        commandManager.register(new ExecuteScriptCommand(commandManager, inputHandler));
        commandManager.register(new ExitCommand());
        commandManager.register(new HeadCommand(collectionManager));
        commandManager.register(new AddIfMinCommand(collectionManager, inputHandler));
        commandManager.register(new HistoryCommand(commandManager));
        commandManager.register(new SumOfHealthCommand(collectionManager));
        commandManager.register(new MaxByChapterCommand(collectionManager));
        commandManager.register(new CountByCategoryCommand(collectionManager));
    }
}
