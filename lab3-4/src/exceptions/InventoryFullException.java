package exceptions;

public class InventoryFullException extends Exception {
    public InventoryFullException(String msg) {
        super(msg);
    }
    
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
