package exceptions;

public class CustomerNotEligibleException extends Exception {
    public CustomerNotEligibleException(String message) {
        super(message);
    }
}
