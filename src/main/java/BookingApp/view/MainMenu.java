package BookingApp.view;

import BookingApp.console.Console;
import BookingApp.model.User;
import BookingApp.view.commands.*;

import static BookingApp.utils.InputChecker.correctIntegerInput;

public class MainMenu {

    public static void run(Console console, User user) {
        boolean notExitCommand = true;

        while (notExitCommand) {
            menu(console);

            int selection = correctIntegerInput(true);
            switch (selection) {
                case 1:
                    FlightsInfoTable.run(console);
                    break;
                case 2:
                    FlightInfo.run(console);
                    break;
                case 3:
                    SearchAndBook.run(console, user);
                    break;
                case 4:
                    BookingInfo.run(console, user);
                    break;
                case 5:
                    BookingCancel.run(console, user);
                    break;
                case 6:
                case 0: // exit code
                    notExitCommand = false;
                    break;
                default:
                    console.println("Please enter command from menu");
                    break;
            }
        }
    }

    private static void menu(Console console) {
        console.println("=".repeat(54));

        console.print(" ".repeat(14));
        console.println("FLIGHT BOOKING MANAGER");

        console.println("  Please choose one of the options below to continue.  ");
        console.println("=".repeat(54));

        console.print(" ".repeat(21));
        console.println("1. View Timetable");

        console.print(" ".repeat(21));
        console.println("2. View Flight");

        console.print(" ".repeat(21));
        console.println("3. Search and Bookings");

        console.print(" ".repeat(21));
        console.println("4. My Bookings");

        console.print(" ".repeat(21));
        console.println("5. Cancel Booking");

        console.print(" ".repeat(21));
        console.println("6. Logout");

        console.println("=".repeat(54));
        console.print(">>>  ");
    }
}
