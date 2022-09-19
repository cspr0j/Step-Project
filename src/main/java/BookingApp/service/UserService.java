package BookingApp.service;

import BookingApp.dao.UserDao;
import BookingApp.entities.User;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDao userDao;

    public UserService(String fileName) {
        this.userDao = new UserDao(new File(fileName));
    }

    public List<User> getAllUsers(){
        return userDao.readFromFile();
    }

    public boolean save(User user){
        return userDao.save(user);
    }

    public Optional<User> get(int id){
        return userDao.getById(id);
    }
}
