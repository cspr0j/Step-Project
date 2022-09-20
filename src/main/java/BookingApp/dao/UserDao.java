package BookingApp.dao;

import BookingApp.model.User;

import java.io.File;
import java.util.Optional;

public class UserDao extends BaseDao<User> {

    public UserDao(File file) {
        super(file);
    }

    public Optional<User> getById(int id) {
        return readFromFile().stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }
}
