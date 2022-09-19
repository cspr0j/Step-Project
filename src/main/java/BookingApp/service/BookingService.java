package BookingApp.service;

import BookingApp.dao.BookingDao;
import BookingApp.entities.Booking;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class BookingService {
    private final BookingDao bookingDao;

    public BookingService(String pathname) {
        this.bookingDao = new BookingDao(new File(pathname));
    }

    public boolean makeBooking(Booking booking){
        return bookingDao.save(booking);
    }

    public List<Booking> getAll(){
        return bookingDao.readFromFile();
    }

    public Optional<Booking> get(int id){
        return bookingDao.getById(id);
    }

    public boolean delete(int id){
        return bookingDao.delete(id);
    }

    public boolean delete(Booking booking){
        return bookingDao.delete(booking);
    }
}
