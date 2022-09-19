import BookingApp.entities.Airline;
import BookingApp.entities.Airport;
import BookingApp.entities.Flight;
import BookingApp.service.FlightService;
import BookingApp.utils.RandomFlightGenerator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightDAOTest {
    private final FlightService flightService = new FlightService(FilePath.flightFile);
    private final File f = new File(FilePath.flightFile);
    private final RandomFlightGenerator generator = new RandomFlightGenerator();
    private final List<Flight> flights = generator.flightGenerator(10);

    private final LocalDateTime dateTime = LocalDateTime.now();

    private final Flight flight1 = new Flight("J2302", Airline.AZAL, 89,
            Airport.BAKU, Airport.LONDON, dateTime, dateTime.plusHours(8));


    @Test
    void saveFlight() {
        assertTrue(flightService.save(flight1));
    }

    @Test
    void getFlight() {
        if (deleteFile() || !f.exists()) {
            flightService.save(flight1);
        }
        assertEquals(flight1, flightService.get(flight1.getId()).orElse(null));
    }

    @Test
    void saveAll() {
        flightService.saveAll(flights);
        assertEquals(flights.size(), flightService.getAllFlights().size());
    }

    @Test
    void getAll() {
        flightService.saveAll(flights);
        assertEquals(flights, flightService.getAllFlights());
    }

    @Test
    public void deleteByObject() {
        if (deleteFile() || !f.exists()) {
            flightService.save(flight1);
        }
        flightService.delete(flight1);
        assertEquals(flightService.getAllFlights(), new ArrayList<>());
    }

    @Test
    void equals() {
        Flight flight2 = new Flight("J2302", Airline.AZAL, 89,
                Airport.BAKU, Airport.LONDON, dateTime, dateTime.plusHours(8));

        // reflexive: an object must equal itself
        boolean firstContract = flight2.equals(flight2); //true
        // symmetric: X.equals(Y) must return the same result as Y.equals(X)
        // here our X - flight1 ,   and    Y - flight2
        boolean secondContract = flight2.equals(this.flight1) && this.flight1.equals(flight2); //true

        Flight flight3 = new Flight("J2302", Airline.AZAL, 89,
                Airport.BAKU, Airport.LONDON, dateTime, dateTime.plusHours(8));

        // transitive: if X.equals(Y) and Y.equals(Z), then also X.equals(Z)
        // here our X - flight2 ,   and    Y - flight1  and    Z - flight3
        boolean thirdContract = flight2.equals(this.flight1) &&
                this.flight1.equals(flight3) &&
                flight2.equals(flight3); //true

        // consistent:
        // for any given values of x and y,
        // a repeated call to x.equals(y)
        // will return the value of the previous call to this method,
        // provided that the fields used to compare these two objects have not changed between calls.;
        boolean check = flight3.equals(this.flight1); //true
        boolean check2 = flight3.equals(this.flight1); //true
        boolean check3 = flight3.equals(this.flight1); //true

        boolean fourthContract = check && check2 && check3; //true

        // final check with all contracts
        boolean result = firstContract && secondContract && thirdContract && fourthContract;

        assertTrue(result);
    }


    @Test
    void hashCodeTests() {
        Flight flight2 = new Flight("J2302", Airline.AZAL, 89,
                Airport.BAKU, Airport.LONDON, dateTime, dateTime.plusHours(8));

        // internal consistency:
        // calling the hashCode method one or more times over the same object
        // should return the same hash value,
        // provided that the fields of the object involved in calculating the value have not changed.
        int code1 = flight2.hashCode();
        int code2 = flight2.hashCode();
        int code3 = flight2.hashCode();
        boolean firstContract = flight2.hashCode() == code1 &&
                flight2.hashCode() == code2 &&
                flight2.hashCode() == code3;

        // calling the hashCode method on two objects
        // should always return the same number
        // if these objects are equal (calling the equals method on these objects returns true).
        boolean secondContract = (flight1.hashCode() == flight2.hashCode());

        boolean result = firstContract && secondContract;
        assertTrue(result);
    }


    boolean deleteFile(){
        return f.delete();
    }
}
