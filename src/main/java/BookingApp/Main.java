package BookingApp;

import BookingApp.console.CustomConsole;
import BookingApp.console.VisitorMenu;
import BookingApp.controller.FlightController;
import BookingApp.controller.GeneratorController;

public class Main {
    public static void main(String[] args) {
        FlightController flightController = new FlightController();
        if (flightController.getAllFlights().isEmpty()) {
            flightController.saveAllFlights(GeneratorController.generator(300));
        }
        flightController.removeExpiredFlight();

        VisitorMenu.run(new CustomConsole());
    }
}
