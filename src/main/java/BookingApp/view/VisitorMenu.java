package BookingApp.view;

import BookingApp.console.Console;
import BookingApp.view.commands.FlightInfo;
import BookingApp.view.commands.FlightsInfoTable;
import BookingApp.view.commands.Login;
import BookingApp.view.commands.Register;

import static BookingApp.utils.InputChecker.correctIntegerInput;

public class VisitorMenu {
    public static void run(Console console) {
        boolean notExitCommand = true;

        while (notExitCommand) {
            menu(console);

            int selection = correctIntegerInput(true);

            switch (selection) {
                case 1:
                    Login.run(console);
                    break;
                case 2:
                    Register.run(console);
                    break;
                case 3:
                    FlightsInfoTable.run(console);
                    break;
                case 4:
                    FlightInfo.run(console);
                    break;
                case 5:
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
        console.println("1. Log In.");

        console.print(" ".repeat(21));
        console.println("2. Register.");

        console.print(" ".repeat(21));
        console.println("3. View Timetable.");

        console.print(" ".repeat(21));
        console.println("4. View Flight.");

        console.print(" ".repeat(21));
        console.println("5. Exit.");

        console.println("=".repeat(54));
        console.print(">>>  ");
    }
}
