package BookingApp.model;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class Booking implements Serializable {

    private static final long serialVersionUID = -1262767543938157655L;

    private static int counter = 0;
    private final Flight flight;
    private int id;
    private List<Passenger> passengers;


    public Booking(Flight flight, List<Passenger> passengers) {
        this.id = ++counter;
        this.flight = flight;
        this.passengers = passengers;
    }

    public Booking(int id, Flight flight, List<Passenger> passengers) {
        this.id = id;
        this.flight = flight;
        this.passengers = passengers;
    }

    public Flight getFlight() {
        return flight;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(flight, booking.flight)
                && Objects.equals(passengers, booking.passengers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flight, passengers);
    }

    @Override
    public String toString() {
        return String.format(
                "Ticket ID: %d \n" +
                        "Airline Designator: %s \n" +
                        "To: %s \n" +
                        "Departure Time: %s \n" +
                        "Arrival Time: %s \n" +
                        "Passengers: %s \n",
                id,
                flight.getDesignator(),
                flight.getAirportTo(),
                flight.getDeparture().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")),
                flight.getArrival_time().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")),
                passengers);
    }
}
