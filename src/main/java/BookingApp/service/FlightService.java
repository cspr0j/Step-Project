package BookingApp.service;

import BookingApp.dao.FlightDao;
import BookingApp.entities.Flight;
import BookingApp.logger.CustomLogger;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class FlightService {
    private final FlightDao flightDao;

    public FlightService(String pathname) {
        this.flightDao = new FlightDao(new File(pathname));
    }

    public List<Flight> getAllFlights(){
        return flightDao.readFromFile();
    }

    public boolean save(Flight flight){
        return flightDao.save(flight);
    }

    public boolean delete(Flight flight){
        return flightDao.delete(flight);
    }

    public Optional<Flight> get(int id){
        return flightDao.getById(id);
    }

    public void saveAll(List<Flight> data){
        flightDao.saveToFile(data);
        CustomLogger.info("A list of flights saved to the database.");
    }
}