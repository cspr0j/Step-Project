package BookingApp.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -5077307027843314133L;

    public UserNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
