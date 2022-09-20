package BookingApp.dao;

import BookingApp.model.Flight;

import java.io.File;
import java.util.Optional;

public class FlightDao extends BaseDao<Flight> {

    public FlightDao(File file) {
        super(file);
    }

    public Optional<Flight> getById(int id) {
        return readFromFile().stream()
                .filter(flight -> flight.getId() == id)
                .findAny();
    }
}
