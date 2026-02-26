package itmo.cse.lab5.common.exceptions;

public class ScriptRecursionException extends RuntimeException {
    public ScriptRecursionException(String fileName) {
        super(
                String.format("Обнаружен рекурсивный вызов скрипта: %s", fileName)
        );
    }
}
