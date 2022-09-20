package BookingApp.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    boolean saveToFile(List<T> list);

    List<T> readFromFile();

    Optional<T> get(T t);

    boolean save(T t);

    boolean delete(int id);

    boolean delete(T t);
}
