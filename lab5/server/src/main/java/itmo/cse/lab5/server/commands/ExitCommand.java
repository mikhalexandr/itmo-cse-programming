package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;

/**
 * Команда {@code exit}: завершает программу без сохранения.
 */
public class ExitCommand extends Command {
    /**
     * Создает команду завершения программы.
     */
    public ExitCommand() {
        super("exit", "завершить программу (без сохранения!)");
    }

    /**
     * Завершает JVM-процесс.
     *
     * @param args аргументы команды (не используются)
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Программа завершена");
        System.exit(0);
    }
}
