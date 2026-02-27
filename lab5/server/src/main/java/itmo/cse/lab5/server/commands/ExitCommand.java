package itmo.cse.lab5.server.commands;

import itmo.cse.lab5.common.commands.Command;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", "завершить программу (без сохранения!)");
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Заврешение программы...");
        System.exit(0);
    }
}
