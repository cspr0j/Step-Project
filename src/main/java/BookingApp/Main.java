package BookingApp;

import BookingApp.console.CustomConsole;
import BookingApp.controller.FlightController;
import BookingApp.controller.GeneratorController;
import BookingApp.view.VisitorMenu;

public class Main {
    public static void main(String[] args) {
        FlightController flightController = new FlightController();
        flightController.generateFlights();

        VisitorMenu.run(new CustomConsole());
    }
}
