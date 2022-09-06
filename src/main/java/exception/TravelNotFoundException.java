package exception;

public class TravelNotFoundException extends Exception {
    public TravelNotFoundException(String message) {
        super(message);
    }

    public TravelNotFoundException() {
        super("domain.Travel Not Found");
    }
}
