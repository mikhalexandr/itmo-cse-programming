package exceptions;

public class DisgustingTasteException extends Exception {
    public DisgustingTasteException(String msg) {
        super(msg);
    }
    
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
