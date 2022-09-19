package BookingApp.entities;

import java.io.Serializable;
import java.util.Objects;

public class Passenger implements Serializable {

    private static final long serialVersionUID = -7919346364079107002L;
    private static int counter = 0;
    private final String name;
    private final String surname;
    private final int id;

    public Passenger(String name, String surname) {
        this.id = ++counter;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;

        return id == passenger.id &&
                name.equals(passenger.name) &&
                surname.equals(passenger.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Surname: %s", name, surname);
    }
}
