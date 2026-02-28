package itmo.cse.lab5.common.exceptions;

/**
 * Ошибка выполнения пользовательской команды.
 */
public class CommandExecutionException extends Exception {
    /**
     * @param message текст ошибки
     */
    public CommandExecutionException(String message) {
        super(message);
    }
}
