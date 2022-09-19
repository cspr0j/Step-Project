package BookingApp.exceptions;

public class TicketsOverFlowException extends RuntimeException{

    private static final long serialVersionUID = -5297493864289073926L;

    public TicketsOverFlowException(String message) {
        super(message);
    }
}
