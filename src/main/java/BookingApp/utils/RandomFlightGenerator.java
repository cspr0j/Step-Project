package BookingApp.utils;

import BookingApp.model.Airline;
import BookingApp.model.Airport;
import BookingApp.model.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.time.LocalDateTime.of;

public class RandomFlightGenerator {
    private final Random random = new Random();

    private int randomNum(int min, int max) {
        int res = max - min;
        return (int) (Math.random() * res + min);
    }

    private LocalDateTime dateTimeGenerator(int range) {
        long minDay = LocalDate.now().toEpochDay();
        long maxDay = LocalDate.now().plusDays(range).toEpochDay();

        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        return of(LocalDate.ofEpochDay(randomDay),
                LocalTime.of(random.nextInt(24), random.nextInt(60)));
    }

    private Airline airlineGenerator() {
        return Airline.values()[random.nextInt(Airline.values().length)];
    }

    private String designatorGenerator(Airline airline) {
        return airline.getCode() + randomNum(100, 900);
    }

    private Airport airportGenerator() {
        return Airport.values()[random.nextInt(Airport.values().length)];
    }

    private int seatsCapacityGenerator() {
        return randomNum(120, 300) / 8;
    }

    public List<Flight> flightGenerator(int length) {
        List<Flight> list = new ArrayList<>();
        String designator;
        Airline airline;
        int seats;
        Airport to;
        Airport from;
        LocalDateTime departure;
        LocalDateTime arrival_time;

        for (int i = 1; i < length + 1; i++) {
            airline = airlineGenerator();
            designator = designatorGenerator(airline);
            seats = seatsCapacityGenerator();
            from = airportGenerator();
            do {
                to = airportGenerator();
            } while (from.equals(to));
            departure = dateTimeGenerator(30);
            arrival_time = departure
                    .plusHours(ThreadLocalRandom.current().nextInt(2, 16))
                    .plusMinutes(ThreadLocalRandom.current().nextInt(45));

            list.add(new Flight(i, designator, airline, seats, from, to, departure, arrival_time));
        }

        return list;
    }
}
