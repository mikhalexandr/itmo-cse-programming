package exceptions;

public class PlantNotFoundException extends Exception {
    public PlantNotFoundException(String msg) {
        super(msg);
    }
    
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
