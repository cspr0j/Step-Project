package BookingApp.controller;

import BookingApp.entities.Booking;
import BookingApp.service.BookingService;
import BookingApp.utils.FilePath;


public class BookingController {
    private final BookingService bookingService = new BookingService(FilePath.bookingFile);

    public boolean doBooking(Booking booking){
        return bookingService.makeBooking(booking);
    }
}
