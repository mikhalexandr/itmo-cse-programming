package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.common.exceptions.CommandExecutionException;
import itmo.cse.lab5.server.input.InputHandler;
import itmo.cse.lab5.server.managers.CommandManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Команда {@code execute_script}: выполняет команды из указанного файла.
 */
public class ExecuteScriptCommand extends Command {
    private static final Set<String> RUNNING_SCRIPTS = new HashSet<>();
    private final CommandManager commandManager;
    private final InputHandler inputHandler;

    /**
     * @param commandManager менеджер команд
     * @param inputHandler обработчик пользовательского ввода
     */
    public ExecuteScriptCommand(CommandManager commandManager, InputHandler inputHandler) {
        super("execute_script", "<file_name>", "выполнить скрипт из файла");
        this.commandManager = commandManager;
        this.inputHandler = inputHandler;
    }

    /**
     * Считывает файл скрипта и выполняет команды построчно.
     *
     * @param args ожидается один аргумент: имя файла скрипта
     * @throws CommandExecutionException если аргумент не передан или файл не найден
     */
    @Override
    public void execute(String[] args) throws CommandExecutionException {
        String fileName = parseFileName(args);
        String scriptKey = resolveScriptKey(fileName);

        if (RUNNING_SCRIPTS.contains(scriptKey)) {
            throw new CommandExecutionException(
                    String.format("Обнаружен рекурсивный вызов скрипта: %s", fileName)
            );
        }

        RUNNING_SCRIPTS.add(scriptKey);
        try {
            executeScriptLines(fileName);
        } finally {
            RUNNING_SCRIPTS.remove(scriptKey);
        }
    }

    private String parseFileName(String[] args) throws CommandExecutionException {
        if (args.length == 0) {
            throw new CommandExecutionException("Использование: execute_script <file_name>");
        }
        String[] fileNameArgs = args[0].trim().split("\\s+");
        if (fileNameArgs.length != 1) {
            throw new CommandExecutionException("Формат: execute_script <file_name>");
        }
        return fileNameArgs[0];
    }

    private String resolveScriptKey(String fileName) {
        try {
            return new File(fileName).getCanonicalPath();
        } catch (IOException e) {
            return new File(fileName).getAbsolutePath();
        }
    }

    private void executeScriptLines(String fileName) throws CommandExecutionException {
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            Scanner previousScanner = inputHandler.getScanner();
            try {
                inputHandler.setScanner(fileScanner);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    if (!line.isEmpty()) {
                        System.out.println("$ " + line);
                        commandManager.execute(line);
                    }
                }
            } finally {
                inputHandler.setScanner(previousScanner);
            }
        } catch (FileNotFoundException e) {
            throw new CommandExecutionException(String.format("файл [%s] не найден", fileName));
        }
    }
}
