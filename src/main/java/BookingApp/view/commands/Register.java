package BookingApp.view.commands;

import BookingApp.console.Console;
import BookingApp.controller.UserController;
import BookingApp.exceptions.UserExistsException;
import BookingApp.logger.CustomLogger;
import BookingApp.model.User;
import BookingApp.utils.Validator;

public class Register {
    public static void run(Console console) {
        while (true) {
            UserController userController = new UserController();

            String username;
            do {
                console.print("Please, enter username (at least 4 character): ");
                username = console.scanNextLine();

                if (username.equalsIgnoreCase("exit")) return;
            } while (username.length() < 4);


            String password;
            String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}$";
            do {
                console.print("Please, enter password (The password must consist of at least 8 characters," +
                        " and must include 1 digit, 1 uppercase and lowercase character): ");
                password = console.scanNextLine();

                if (password.equalsIgnoreCase("exit")) return;
            } while (!Validator.passwordChecker(password, regex));
//            if (password.equalsIgnoreCase("exit")) break;

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
