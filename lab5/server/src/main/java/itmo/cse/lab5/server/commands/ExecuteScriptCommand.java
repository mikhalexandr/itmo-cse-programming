package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;
import itmo.cse.lab5.common.exceptions.CommandExecutionException;
import itmo.cse.lab5.common.exceptions.ScriptRecursionException;
import itmo.cse.lab5.server.managers.CommandManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ExecuteScriptCommand extends Command {
    private static final Set<String> RUNNING_SCRIPTS = new HashSet<>();
    private final CommandManager commandManager;

    public ExecuteScriptCommand(CommandManager commandManager) {
        super("execute_script", "выполнить скрипт из файла");
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecutionException {
        if (args.length == 0) {
            throw new CommandExecutionException("Использование: execute_script <file_name>");
        }

        String fileName = args[0];

        if (RUNNING_SCRIPTS.contains(fileName)) {
            throw new ScriptRecursionException(fileName);
        }

        RUNNING_SCRIPTS.add(fileName);
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (!line.isEmpty()) {
                    System.out.println(">" + line);
                    commandManager.execute(line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new CommandExecutionException("Файл не найден: " +  fileName);
        } catch (ScriptRecursionException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            RUNNING_SCRIPTS.remove(fileName);
        }
    }
}
