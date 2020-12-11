package sharwarko.truckdriver.service;
import org.junit.Test;

import java.util.List;

import Domain.Login;
import Domain.User;
import Service.UserCacheImplSvc;

import static org.junit.Assert.*;

public class UserCacheImplSvcTest {

    @Test
    public void testCrud(){
        UserCacheImplSvc svc = UserCacheImplSvc.getInstance();
        User user = new User();
        Login login = new Login();

        user.setfName("jim");
        user.setlName("Wagon");
        user.setZip(99999);

        login.setUser("jim23");
        login.setPassword("password99!");

        user.setLogin(login);

        List<User> list1 = svc.retrieveAll();
        assertNotNull(list1);
        int initialSize = list1.size();

        //test crud
        user = svc.create(user);
        assertNotNull(user);

        list1 = svc.retrieveAll();
        int size = list1.size();
        assertEquals(initialSize +1, size);

        user.setZip(99999);
        user = svc.update(user);
        assertNotNull(user);
        assertEquals(99999, user.getZip());

        user = svc.delete(user);
        assertNotNull(user);
        list1 = svc.retrieveAll();
        size = list1.size();
        assertEquals(initialSize, size);
    }
}