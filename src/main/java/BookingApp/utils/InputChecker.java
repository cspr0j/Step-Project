package BookingApp.utils;

import BookingApp.console.CustomConsole;
import BookingApp.logger.CustomLogger;

import java.util.Scanner;

public class InputChecker {

    /**
     * Check for correctness of the data
     */
    public static int correctIntegerInput(boolean isMenu) {
        Scanner scanner = new Scanner(System.in);
        CustomConsole console = new CustomConsole();
        int input;

        while (true) {
            String buffer = scanner.nextLine();
            if (buffer.equalsIgnoreCase("exit") && isMenu) {
                return 0; // exit code
            }
            try {
                input = Integer.parseInt(buffer);

                if (input > 0) {
                    return input;
                }

                throw new NumberFormatException();

            } catch (NumberFormatException e) {
                console.println("Wrong input. Please, try again");
                CustomLogger.error(e.getMessage());
            }
        }
    }
}
