package BookingApp.controller;

import BookingApp.entities.Flight;
import BookingApp.logger.CustomLogger;
import BookingApp.service.BookingService;
import BookingApp.service.FlightService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static BookingApp.utils.FilePath.bookingFile;
import static BookingApp.utils.FilePath.flightFile;


public class FlightController {
    private final FlightService flightService = new FlightService(flightFile);
    private final BookingService bookingService = new BookingService(bookingFile);

    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    public void saveAllFlights(List<Flight> data) {
        flightService.saveAll(data);
    }

    public Optional<Flight> get(String airlineCode) {
        return flightService.getAllFlights()
                .stream()
                .filter(flight -> flight.getDesignator().equalsIgnoreCase(airlineCode))
                .findFirst();
    }

    public void decreaseSeatsCapacity(String designator, int seats) {
        flightService.getAllFlights()
                .stream()
                .filter(flight -> flight.getDesignator().equalsIgnoreCase(designator))
                .forEach(flight -> {
                    flight.setSeats(flight.getSeats() - seats);
                    flightService.save(flight);
                    CustomLogger.info(String.format("The seats capacity decreased for %s flight.", designator.toUpperCase()));
                });
    }

    public void increaseSeatsCapacity(int bookingId) {
        if (bookingService.get(bookingId).isEmpty()) {
            CustomLogger.error("Booking not found");
        }
        try {
            Flight flight = bookingService.get(bookingId).get().getFlight();
            int seats = bookingService.get(bookingId).get().getPassengers().size();
            flight.setSeats(flight.getSeats() + seats);
            CustomLogger.info(String.format("The seats capacity increased for %s flight.", flight.getDesignator().toUpperCase()));
        } catch (NoSuchElementException e){
            CustomLogger.error(e.getMessage());
        }
    }

    public void removeExpiredFlight() {
        flightService.getAllFlights()
                .stream()
                .filter(flight -> LocalDateTime.now().isAfter(flight.getDeparture()))
                .forEach(flightService::delete);
    }
}
