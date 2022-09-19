import BookingApp.entities.User;
import BookingApp.service.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDAOTest {
    private final UserService userService = new UserService(FilePath.usersFile);
//    private final File f = new File(FilePath.usersFile);

    User user1 = new User("jalal", "Qwerty1");

    @Test
    void save() {
        assertTrue(userService.save(user1));
    }

    @Test
    void getFlight() {
        userService.save(user1);
        assertEquals(user1, userService.get(user1.getId()).orElse(null));
    }

    @Test
    void getAll() {
        userService.save(user1);
        assertTrue(userService.getAllUsers().contains(user1));
    }

    @Test
    void equals() {
        User user2 = new User("jalal", "Qwerty1");

        // reflexive: an object must equal itself
        boolean firstContract = user2.equals(user2); //true
        // symmetric: X.equals(Y) must return the same result as Y.equals(X)
        // here our X - user1 ,   and    Y - user2
        boolean secondContract = this.user1.equals(user2) && user2.equals(this.user1); //true

        User user3 = new User("jalal", "Qwerty1");

        // transitive: if X.equals(Y) and Y.equals(Z), then also X.equals(Z)
        // here our X - user1 ,   and    Y - user2  and    Z - user3
        boolean thirdContract = this.user1.equals(user2) &&
                user2.equals(user3) &&
                user1.equals(user3); //true

        // consistent:
        // for any given values of x and y,
        // a repeated call to x.equals(y)
        // will return the value of the previous call to this method,
        // provided that the fields used to compare these two objects have not changed between calls.;
        boolean check = user3.equals(this.user1); //true
        boolean check2 = user3.equals(this.user1); //true
        boolean check3 = user3.equals(this.user1); //true

        boolean fourthContract = check && check2 && check3; //true

        // final check with all contracts
        boolean result = firstContract && secondContract && thirdContract && fourthContract;

        assertTrue(result);
    }


    @Test
    void hashCodeTests() {
        User user2 = new User("jalal", "Qwerty1");
        // internal consistency:
        // calling the hashCode method one or more times over the same object
        // should return the same hash value,
        // provided that the fields of the object involved in calculating the value have not changed.
        int code1 = user2.hashCode();
        int code2 = user2.hashCode();
        int code3 = user2.hashCode();
        boolean firstContract = user2.hashCode() == code1 &&
                user2.hashCode() == code2 &&
                user2.hashCode() == code3;

        // calling the hashCode method on two objects
        // should always return the same number
        // if these objects are equal (calling the equals method on these objects returns true).
        boolean secondContract = (user1.hashCode() == user1.hashCode());

        boolean result = firstContract && secondContract;
        assertTrue(result);
    }
}
