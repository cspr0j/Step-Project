import BookingApp.entities.*;
import BookingApp.service.BookingService;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingDAOTest {
    private final BookingService bookingService = new BookingService(FilePath.bookingFile);
    private final File f = new File(FilePath.bookingFile);

    private final Flight f1 = new Flight("J2302", Airline.AZAL, 89,
            Airport.BAKU, Airport.LONDON, LocalDateTime.now(), LocalDateTime.now().plusHours(8));

    private final Passenger p1 = new Passenger("Jalal", "Aliyev");

    private final Booking b1 = new Booking(1, f1, List.of(p1));


    @Test
    void makeBooking() {
        bookingService.makeBooking(b1);
        assertTrue(bookingService.makeBooking(b1));
    }

    @Test
    void get() {
        if (deleteFile() || !f.exists()) {
            bookingService.makeBooking(b1);
        }
        assertEquals(b1, bookingService.get(b1.getId()).orElse(null));
    }

    @Test
    public void deleteByObject() {
        if (deleteFile() || !f.exists()) {
            bookingService.makeBooking(b1);
        }
        bookingService.delete(b1);
        assertEquals(new ArrayList<>(), bookingService.getAll());
    }

    @Test
    public void deleteById() {
        if (deleteFile() || !f.exists()) {
            bookingService.makeBooking(b1);
        }
        assertTrue(bookingService.delete(b1.getId()));
    }

    @Test
    void equals() {
        Booking booking1 = new Booking(1, f1, List.of(p1));

        // reflexive: an object must equal itself
        boolean firstContract = booking1.equals(booking1); //true

        // symmetric: X.equals(Y) must return the same result as Y.equals(X)
        // here our X - booking1 ,   and    Y - b1
        boolean secondContract = booking1.equals(b1) && b1.equals(booking1); //true
        // System.out.println(family1.equals(family2));

        Booking booking2 = new Booking(1, f1, List.of(p1));
        // transitive: if X.equals(Y) and Y.equals(Z), then also X.equals(Z)
        // here our X - booking1 ,   and    Y - b1  and    Z - booking2
        boolean thirdContract = booking1.equals(b1) &&
                b1.equals(booking2) &&
                booking1.equals(booking2); //true

        // consistent:
        // for any given values of x and y,
        // a repeated call to x.equals(y)
        // will return the value of the previous call to this method,
        // provided that the fields used to compare these two objects have not changed between calls.;
        boolean check = booking1.equals(b1); //true
        boolean check2 = booking1.equals(b1); //true
        boolean check3 = booking1.equals(b1); //true

        boolean fourthContract = check && check2 && check3; //true

        // final check with all contracts
        boolean result = firstContract && secondContract && thirdContract && fourthContract;

        assertTrue(result);
    }


    @Test
    void hashCodeTests() {
        Booking booking1 = new Booking(1, f1, List.of(p1));

        // internal consistency:
        // calling the hashCode method one or more times over the same object
        // should return the same hash value,
        // provided that the fields of the object involved in calculating the value have not changed.
        int code1 = booking1.hashCode();
        int code2 = booking1.hashCode();
        int code3 = booking1.hashCode();
        boolean firstContract = booking1.hashCode() == code1 &&
                booking1.hashCode() == code2 &&
                booking1.hashCode() == code3;

        // calling the hashCode method on two objects
        // should always return the same number
        // if these objects are equal (calling the equals method on these objects returns true).
        boolean secondContract = (booking1.hashCode() == b1.hashCode());

        boolean result = firstContract && secondContract;
        assertTrue(result);
    }

    boolean deleteFile() {
        return f.delete();
    }
}
