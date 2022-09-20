package BookingApp.controller;

import BookingApp.model.Booking;
import BookingApp.model.User;
import BookingApp.service.BookingService;
import BookingApp.utils.FilePath;

import java.util.List;
import java.util.Optional;


public class BookingController {
    private final BookingService bookingService = new BookingService(FilePath.bookingFile);

    public boolean makeBooking(Booking booking, User user) {
        if (user.getBookings().isEmpty()){
            booking.setId(1);
        }else
            booking.setId(user.getBookings().size() + 1);

        return bookingService.makeBooking(booking);
    }

    public List<Booking> getAll() {
        return bookingService.getAll();
    }

    public Optional<Booking> get(int id) {
        return bookingService.get(id);
    }

    public boolean deleteById(int id) {
        return bookingService.deleteById(id);
    }

    public boolean deleteByObject(Booking booking) {
        return bookingService.deleteByObject(booking);
    }

}
