package sharwarko.truckdriver.domain;
import org.junit.Test;

import Domain.User;

import static org.junit.Assert.*;


public class UserTest {

    public UserTest(){}

    @Test
    public void testFoodTruck() {
        User user = new User();
        boolean result = user.Validate();
        assertFalse(result);

        user.setfName("bucky");
        result = user.Validate();
        assertFalse(result);

        user.setlName("buckner");
        result = user.Validate();

        assertTrue(result);

    }

}

