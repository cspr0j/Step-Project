package BookingApp.controller;

import BookingApp.exceptions.BookingNotFoundException;
import BookingApp.logger.CustomLogger;
import BookingApp.model.Booking;
import BookingApp.model.User;
import BookingApp.service.BookingService;
import BookingApp.service.FlightService;
import BookingApp.service.UserService;

import java.util.List;
import java.util.Optional;

import static BookingApp.utils.FilePath.*;


public class UserController {
    private final UserService userService = new UserService(usersFile);
    private final BookingService bookingService = new BookingService(bookingFile);

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public boolean saveUser(User user) {
        getAllUsers().stream()
                .filter(myUser -> myUser.getId() == user.getId() && !myUser.equals(user))
                .forEach(myUser -> {
                    user.setId(getAllUsers().size() + 1);
                });
        return userService.save(user);
    }

    public Optional<User> getUser(int id) {
        return userService.get(id);
    }

    public boolean addBookingToTheUser(User user, Booking booking) {

        if (user == null) {
            return false;
        }
        List<Booking> bookings = user.getBookings();

        bookings.add(booking);
        user.setBookings(bookings);

        return saveUser(user);
    }


    public String removeBookingFromUser(int bookingId, User user) {
        try {
            if (user == null || bookingService.get(bookingId).isEmpty()) {
                throw new BookingNotFoundException("Booking not found");
            }

            List<Booking> bookings = user.getBookings();

            if (bookings.isEmpty())
                throw new BookingNotFoundException("Booking not found");


            Booking booking = bookings.get(bookingId - 1);

            bookings.remove(booking);
            if (!bookingService.deleteByObject(booking)) {
                throw new BookingNotFoundException("Booking not found");
            }

            bookings.stream()
                    .filter(booking1 -> booking1.getId() > 1 && bookingId == 1)
                    .forEach(myBooking -> {
                        myBooking.setId(myBooking.getId() - 1);
                    });
            user.setBookings(bookings);
            saveUser(user);

            return "Booking cancelled.";
        } catch (BookingNotFoundException e) {
            CustomLogger.error(e.getMessage());
            return e.getMessage();
        }
    }
}
