package BookingApp.view.commands;

import BookingApp.console.Console;
import BookingApp.controller.FlightController;
import BookingApp.logger.CustomLogger;

public class FlightInfo {
    public static void run(Console console) {
        console.print("Please, enter Airline Designator (e.g AA325): ");
        String choice = console.scanNextLine();

        FlightController flightController = new FlightController();

        flightController.get(choice)
                .ifPresentOrElse(
                        flight1 -> console.println(
                                String.format("Date: %s\n" +
                                                "Time: %s \n" +
                                                "Destination: %s (%s)\n" +
                                                "Flight No: %s\n" +
                                                "Amount of free seats: %d",
                                        flight1.getDeparture().toLocalDate(),
                                        flight1.getDeparture().toLocalTime(),
                                        flight1.getAirportTo().toString(),
                                        flight1.getAirportTo().getCode(),
                                        flight1.getDesignator(),
                                        flight1.getSeats())),
                        () -> {
                            console.println("No such flight found.");
                            CustomLogger.error("No such flight found.");
                        });
    }
}
