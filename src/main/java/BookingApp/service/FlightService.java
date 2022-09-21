package BookingApp.service;

import BookingApp.controller.GeneratorController;
import BookingApp.dao.BookingDao;
import BookingApp.dao.FlightDao;
import BookingApp.logger.CustomLogger;
import BookingApp.model.Flight;
import BookingApp.utils.FilePath;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class FlightService {
    private final FlightDao flightDao;
    private final BookingDao bookingDao = new BookingDao(new File(FilePath.bookingFile));

    public FlightService(String pathname) {
        this.flightDao = new FlightDao(new File(pathname));
    }

    public void generateFlight() {
        if (getAllFlights().isEmpty()) {
            saveAll(GeneratorController.generator(300));
        }
        removeExpiredFlight();
    }

    public List<Flight> getAllFlights() {
        return flightDao.readFromFile();
    }

    public boolean save(Flight flight) {
        return flightDao.save(flight);
    }

    public boolean delete(Flight flight) {
        return flightDao.delete(flight);
    }

    public Optional<Flight> get(String airlineCode) {
        return getAllFlights()
                .stream()
                .filter(flight -> flight.getDesignator().equalsIgnoreCase(airlineCode))
                .findFirst();
    }

    public Optional<Flight> get(int id) {
        return flightDao.getById(id);
    }

    public void saveAll(List<Flight> data) {
        flightDao.saveToFile(data);
        CustomLogger.info("A list of flights saved to the database.");
    }

    public void decreaseSeats(String designator, int seats) {
        getAllFlights()
                .stream()
                .filter(flight -> flight.getDesignator().equalsIgnoreCase(designator))
                .forEach(flight -> {
                    flight.setSeats(flight.getSeats() - seats);
                    save(flight);
                    CustomLogger.info(String.format("The seats capacity decreased for %s flight.", designator.toUpperCase()));
                });
    }

    public void increaseSeats(int bookingId) {
        if (bookingDao.getById(bookingId).isEmpty()) {
            CustomLogger.error("Booking not found");
        }
        try {
            Flight flight = bookingDao.getById(bookingId).get().getFlight();
            int seats = bookingDao.getById(bookingId).get().getPassengers().size();
            flight.setSeats(flight.getSeats() + seats);
            CustomLogger.info(String.format("The seats capacity increased for %s flight.", flight.getDesignator().toUpperCase()));
        } catch (NoSuchElementException e) {
            CustomLogger.error(e.getMessage());
        }
    }

    private void removeExpiredFlight() {
        getAllFlights()
                .stream()
                .filter(flight -> LocalDateTime.now().isAfter(flight.getDeparture()))
                .forEach(this::delete);
    }
}
