package exception;

public class TravelAlreadyExistsException extends Exception {

    public TravelAlreadyExistsException(String message) {
        super(message);
    }

    public TravelAlreadyExistsException() {
        super("Travel already exists");
    }
}
