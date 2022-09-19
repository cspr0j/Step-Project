package BookingApp.console;

import BookingApp.controller.BookingController;
import BookingApp.controller.FlightController;
import BookingApp.controller.UserController;
import BookingApp.entities.Booking;
import BookingApp.entities.Flight;
import BookingApp.entities.Passenger;
import BookingApp.entities.User;
import BookingApp.exceptions.TicketsOverFlowException;
import BookingApp.logger.CustomLogger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static BookingApp.utils.InputChecker.correctIntegerInput;

public class SearchAndBook {
    public static void run(Console console, User user){
        while(true) {
            try {
                UserController userController = new UserController();
                FlightController flightController = new FlightController();
                BookingController bookingController = new BookingController();

                console.print("Please, enter the arrival city (e.g London): ");
                String arrival = console.scanNextLine();

                console.print("Please, enter the departure city (e.g Baku): ");
                String departure = console.scanNextLine();

                console.print("Please, enter flight date in a format \"dd/MM/yyyy\": ");
                String date = console.scanNextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(date, formatter);

                console.print("Please, enter number of tickets: ");
                int tickets = correctIntegerInput(false);

                List<Flight> flights = flightController.getAllFlights()
                        .stream()
                        .filter(flight -> flight.getAirportTo().toString().equalsIgnoreCase(arrival)
                                && flight.getAirportFrom().toString().equalsIgnoreCase(departure)
                                && flight.getDeparture().toLocalDate().isEqual(localDate)
                                && flight.getSeats() > tickets)
                        .collect(Collectors.toList());

                if (flights.isEmpty()) {
                    console.println("No such flights found.");
                    break;
                } else
                    flights.forEach(flight -> console.println(String.valueOf(flight)));

                console.print("Please, enter Airline designator to book a flight (e.g AA325)" +
                        " or write \"exit\" to go back: ");
                String designator = console.scanNextLine();
                if (designator.equals("exit")) break;

                int i = 1;
                List<Passenger> passengers = new ArrayList<>();
                while (i <= tickets) {
                    console.print("Please, enter passenger's name " + i + ": ");
                    String name = console.scanNextLine();

                    console.print("Please, enter passenger's surname " + i + ": ");
                    String surname = console.scanNextLine();

                    Passenger passenger = new Passenger(name, surname);
                    passengers.add(passenger);
                    i++;
                }

                if(flightController.get(designator).isPresent() &&
                        flightController.get(designator).get().getSeats() < tickets)
                    throw new TicketsOverFlowException("There is not enough seats for this flight");

                flightController.decreaseSeatsCapacity(designator, tickets);
                Booking booking = new Booking(flightController.get(designator).get(), passengers);

                bookingController.doBooking(booking);
                userController.addBookingToTheUser(user.getId(), booking);

                console.println("Successfully booked.");
                break;
            } catch (DateTimeParseException e) {
                console.println("Please, provide date in a format of \"dd/MM/yyyy\".");
                CustomLogger.error(e.getMessage());
            } catch (TicketsOverFlowException e){
                console.println(e.getMessage());
                CustomLogger.error(e.getMessage());
            }
        }
    }
}
