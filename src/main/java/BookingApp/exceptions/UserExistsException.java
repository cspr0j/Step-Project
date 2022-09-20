package BookingApp.exceptions;

public class UserExistsException extends RuntimeException {
    private static final long serialVersionUID = 927673495740139865L;

    public UserExistsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
