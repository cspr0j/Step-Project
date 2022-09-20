package BookingApp.service;

import BookingApp.dao.BookingDao;
import BookingApp.model.Booking;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class BookingService {
    private final BookingDao bookingDao;

    public BookingService(String pathname) {
        this.bookingDao = new BookingDao(new File(pathname));
    }

    public boolean makeBooking(Booking booking) {
        return bookingDao.save(booking);
    }

    public List<Booking> getAll() {
        return bookingDao.readFromFile();
    }

    public Optional<Booking> get(int id) {
        return bookingDao.getById(id);
    }

    public boolean deleteById(int id) {
        return bookingDao.delete(id);
    }

    public boolean deleteByObject(Booking booking) {
        getAll().stream()
                .filter(booking1 -> booking1.getId() > 1 && booking.getId() == 1)
                .forEach(booking1 -> {
                    booking1.setId(getAll().size() - 1);
                    bookingDao.save(booking1);
                });

        return bookingDao.delete(booking);
    }
}
