package BookingApp.view.commands;

import BookingApp.console.Console;
import BookingApp.controller.FlightController;
import BookingApp.controller.UserController;
import BookingApp.model.User;

import static BookingApp.utils.InputChecker.correctIntegerInput;

public class BookingCancel {
    public static void run(Console console, User user) {
        console.print("Please, enter the Booking ID: ");
        int id = correctIntegerInput(false);

        UserController userController = new UserController();
        FlightController flightController = new FlightController();

        flightController.increaseSeatsCapacity(id);
        console.println(userController.removeBookingFromUser(id, user));
    }
}
