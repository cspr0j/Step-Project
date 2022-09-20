package BookingApp.exceptions;

public class BookingNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -7884606685002395405L;

    public BookingNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
