package sharwarko.truckdriver.domain;
import org.junit.Test;

import Domain.Login;

import static org.junit.Assert.*;


public class LoginTest {

    public LoginTest(){}

    @Test
    public void testLogin() {
        Login login = new Login();
        boolean result = login.Validate();
        assertFalse(result);
        result = login.Validate();
        assertFalse(result);
        login.setPassword("Loggins7!!");
        result = login.Validate();
        assertTrue(result);

    }

}

