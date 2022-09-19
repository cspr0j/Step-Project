package BookingApp.console;

import BookingApp.controller.UserController;
import BookingApp.entities.User;
import BookingApp.exceptions.UserExistsException;
import BookingApp.logger.CustomLogger;
import BookingApp.utils.Validator;

public class Register {
    public static void run(Console console) {
        while (true) {
            UserController userController = new UserController();

            String username;
            do {
                console.print("Please, enter username (at least 4 character): ");
                username = console.scanNextLine();
            } while (username.length() < 4);

            String password;
            String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}$";
            do {
                console.print("Please, enter password (The password must consist of at least 8 characters," +
                        " and must include 1 digit, 1 uppercase and lowercase character): ");
                password = console.scanNextLine();
            } while (!Validator.passwordChecker(password, regex));

            try {
                User user = new User(username, password);

                if (userController.getAllUsers().contains(user)) {
                    throw new UserExistsException("User with this username already exists!" +
                            " Please try again with another username");
                }

                userController.saveUser(user);
                break;
            } catch (UserExistsException e) {
                console.println(e.getMessage());
                CustomLogger.error(e.getMessage());
            }
        }
    }
}
