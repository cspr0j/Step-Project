package BookingApp.controller;

import BookingApp.model.Flight;
import BookingApp.service.FlightService;

import java.util.List;
import java.util.Optional;

import static BookingApp.utils.FilePath.flightFile;


public class FlightController {
    private final FlightService flightService = new FlightService(flightFile);

    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    public void saveAllFlights(List<Flight> data) {
        flightService.saveAll(data);
    }

    public void generateFlights() {
        flightService.generateFlight();
    }

    public Optional<Flight> get(String airlineCode) {
        return flightService.get(airlineCode);
    }

    public void decreaseSeatsCapacity(String designator, int seats) {
        flightService.decreaseSeats(designator, seats);
    }

    public void increaseSeatsCapacity(int bookingId) {
        flightService.increaseSeats(bookingId);
    }
}
