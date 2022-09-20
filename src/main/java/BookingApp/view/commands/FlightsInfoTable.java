package BookingApp.view.commands;

import BookingApp.console.Console;
import BookingApp.controller.FlightController;

public class FlightsInfoTable {
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";

    public static void run(Console console) {
        FlightController flightController = new FlightController();
        console.println(TEXT_RED + "|  ID  | CODE  |    DEPARTURE     " +
                "|    FROM\t\t  ---->\t\t\t TO   |      ARRIVAL     | AIRPLANE" + TEXT_RESET);
        flightController.getAllFlights()
                .forEach(flight -> console.println(flight.toString()));
    }
}
