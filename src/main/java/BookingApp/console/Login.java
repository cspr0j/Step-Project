package BookingApp.console;

import BookingApp.controller.UserController;
import BookingApp.entities.User;
import BookingApp.exceptions.UserNotFoundException;
import BookingApp.logger.CustomLogger;

import java.util.Optional;

public class Login {
    public static void run(Console console) {
        while (true) {
            UserController userController = new UserController();

            console.print("Please, enter your login: ");
            String login = console.scanNextLine();

            if (login.equalsIgnoreCase("exit")) break;

            console.print("Please, enter your password: ");
            String password = console.scanNextLine();

            if (password.equalsIgnoreCase("exit")) break;

            try {
                if (!userController.getAllUsers().contains(new User(login, password))) {
                    throw new UserNotFoundException("Login or password is incorrect.");
                }
                Optional<User> userOptional = userController.getAllUsers()
                        .stream()
                        .filter(user -> user.getUsername().equals(login.toLowerCase()) && user.getPassword().equals(password))
                        .findFirst();

                if (userOptional.isEmpty()) {
                    throw new UserNotFoundException("Login or password is incorrect.");
                }
                MainMenu.run(console, userOptional.get());
                break;
            } catch (UserNotFoundException e) {
                console.println(e.getMessage() + " If you don't have an account," +
                        " please, register. Type \"exit\" to go back.");
                CustomLogger.error(e.getMessage());
            }
        }
    }
}
