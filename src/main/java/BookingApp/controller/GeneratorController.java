package BookingApp.controller;

import BookingApp.entities.Flight;
import BookingApp.utils.RandomFlightGenerator;

import java.util.List;

public class GeneratorController {
    public static List<Flight> generator(int count){
        RandomFlightGenerator generator = new RandomFlightGenerator();
        return generator.flightGenerator(count);
    }
}
