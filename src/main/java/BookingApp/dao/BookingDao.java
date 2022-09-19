package BookingApp.dao;

import BookingApp.entities.Booking;

import java.io.File;
import java.util.Optional;

public class BookingDao extends BaseDao<Booking>{

    public BookingDao(File file) {
        super(file);
    }

    public Optional<Booking> getById(int id) {
        return readFromFile().stream()
                .filter(booking -> booking.getId() == id)
                .findAny();
    }
}
