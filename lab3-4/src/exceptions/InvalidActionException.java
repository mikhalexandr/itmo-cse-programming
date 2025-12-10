package exceptions;

public class InvalidActionException extends Exception {
    public InvalidActionException(String msg) {
        super(msg);
    }
    
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
