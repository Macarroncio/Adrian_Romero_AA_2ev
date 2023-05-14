package exception;

public class CustomerAlreadyExistsException extends Exception {

    public CustomerAlreadyExistsException(String message) {
        super(message);
    }

    public CustomerAlreadyExistsException() {
        super("Customer already exists");
    }
}
