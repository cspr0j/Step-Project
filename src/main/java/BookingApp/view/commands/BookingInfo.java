package BookingApp.view.commands;

import BookingApp.console.Console;
import BookingApp.controller.UserController;
import BookingApp.exceptions.BookingNotFoundException;
import BookingApp.logger.CustomLogger;
import BookingApp.model.User;

public class BookingInfo {
    public static void run(Console console, User user) {
        UserController userController = new UserController();

        try {
            if (userController.getUser(user.getId()).isEmpty() || user.getBookings().isEmpty()) {
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
