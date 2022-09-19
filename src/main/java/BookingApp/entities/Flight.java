package BookingApp.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Flight implements Serializable {

    private static final long serialVersionUID = 6088204406177476281L;
    private static int counter = 0;
    private int id;
    private final String designator;
    private final Airline airline;
    private int seats;
    private final Airport airportFrom;
    private final Airport airportTo;
    private final LocalDateTime departure;

    private final LocalDateTime arrival_time;

    public Flight(String designator,
                  Airline airline,
                  int seats,
                  Airport airportFrom,
                  Airport airportTo,
                  LocalDateTime departure,
                  LocalDateTime arrival_time) {
        this.id = ++counter;
        this.designator = designator;
        this.airline = airline;
        this.seats = seats;
        this.airportFrom = airportFrom;
        this.airportTo = airportTo;
        this.departure = departure;
        this.arrival_time = arrival_time;
    }

    public Flight(int id,
                  String designator,
                  Airline airline,
                  int seats,
                  Airport airportFrom,
                  Airport airportTo,
                  LocalDateTime departure,
                  LocalDateTime arrival_time) {
        this.id = id;
        this.designator = designator;
        this.airline = airline;
        this.seats = seats;
        this.airportFrom = airportFrom;
        this.airportTo = airportTo;
        this.departure = departure;
        this.arrival_time = arrival_time;
    }

    public int getId() {
        return id;
    }

    public String getDesignator() {
        return designator;
    }

    public Airline getAirline() {
        return airline;
    }

    public int getSeats() {
        return seats;
    }

    public Airport getAirportFrom() {
        return airportFrom;
    }

    public Airport getAirportTo() {
        return airportTo;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public LocalDateTime getArrival_time() {
        return arrival_time;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(designator, flight.designator) &&
                airline == flight.airline &&
                airportFrom == flight.airportFrom &&
                airportTo == flight.airportTo &&
                Objects.equals(departure, flight.departure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(designator, airline, airportFrom, airportTo, departure);
    }

    @Override
    public String toString() {
        String result = prettyFormat();
        return String
                .format(result,
                        id,
                        designator,
                        departure.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                        airportFrom,
                        airportTo,
                        arrival_time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                        airline
                );
    }

    private String prettyFormat() {
        StringBuilder result = new StringBuilder();
        if (id < 10) {
            result.append("| %d    ");
        }
        if (id >= 10 && id < 100) {
            result.append("| %d   ");
        } else if (id >= 100) {
            result.append("| %d  ");
        }
        result.append("| %s | %s | ");

        for (int i = 3; i < 13; i++) {
            if (airportFrom.name().length() == i) {
                result.append("%s").append(" ".repeat(14 - i));
            }
        }
        result.append("---->");

        for (int i = 3; i < 13; i++) {
            if (airportTo.name().length() == i) {
                result.append(" ".repeat(14 - i)).append("%s ");
            }
        }

        result.append("| %s | %s");


        return result.toString();
    }
}
