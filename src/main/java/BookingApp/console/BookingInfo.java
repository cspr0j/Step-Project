package BookingApp.console;

import BookingApp.controller.UserController;
import BookingApp.entities.User;
import BookingApp.exceptions.BookingNotFoundException;
import BookingApp.logger.CustomLogger;

public class BookingInfo {
    public static void run(Console console, User user) {
        UserController userController = new UserController();

        try {
            if (userController.getUser(user.getId()).isEmpty()) {
                throw new BookingNotFoundException("Booking not found");
            }

            userController.getUser(user.getId()).get().getBookings()
                    .forEach(booking -> console.println(booking.toString()));
        } catch (BookingNotFoundException e) {
            console.println(e.getMessage());
            CustomLogger.error(e.getMessage());
        }
    }
}
