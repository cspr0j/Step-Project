package BookingApp.controller;

import BookingApp.entities.Booking;
import BookingApp.entities.User;
import BookingApp.service.BookingService;
import BookingApp.service.UserService;

import java.util.List;
import java.util.Optional;

import static BookingApp.utils.FilePath.bookingFile;
import static BookingApp.utils.FilePath.usersFile;


public class UserController {
    private final UserService userService = new UserService(usersFile);
    private final BookingService bookingService = new BookingService(bookingFile);

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public boolean saveUser(User user) {
        getAllUsers().stream()
                .filter(myUser -> myUser.getId() == user.getId())
                .forEach(myUser -> {
                    user.setId(user.getId() + 1);
                });
        return userService.save(user);
    }

    public Optional<User> getUser(int id) {
        return userService.get(id);
    }

    public boolean addBookingToTheUser(int userId, Booking booking) {

        if (getUser(userId).isEmpty()) {
            return false;
        }
        User user = getUser(userId).get();
        List<Booking> bookings = user.getBookings();

        bookings.add(booking);
        user.setBookings(bookings);

        return saveUser(user);
    }


    public String removeBookingFromUser(int bookingId, int userId) {
        if (getUser(userId).isEmpty() || bookingService.get(bookingId).isEmpty()) {
            return "No such item found.";
        }

        User user = getUser(userId).get();
        List<Booking> bookings = user.getBookings();

        bookings.remove(bookingService.get(bookingId).get());
        user.setBookings(bookings);

        saveUser(user);
        if (bookingService.delete(bookingId))
            return "Booking cancelled.";
        else
            return "Booking not found.";
    }
}
