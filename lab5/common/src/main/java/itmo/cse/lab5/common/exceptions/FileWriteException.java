package itmo.cse.lab5.common.exceptions;

public class FileWriteException extends  RuntimeException {
    public FileWriteException(String message,  Throwable cause) {
        super(message,  cause);
    }
}
