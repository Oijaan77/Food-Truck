package sharwarko.truckdriver.service;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import Domain.FoodTruck;
import Domain.Login;
import Domain.MenuItems;
import Domain.Order;
import Domain.User;
import Service.OrderCacheImplSvc;

import static org.junit.Assert.*;

public class OrderCacheImplSvcTest {

    @Test
    public void testCrud(){
        OrderCacheImplSvc svc = OrderCacheImplSvc.getInstance();

        Order order = new Order();
        User user = new User();
        MenuItems menu = new MenuItems();
        FoodTruck foodTruck = new FoodTruck();
        ArrayList list = new ArrayList();

        foodTruck.setName("trucky");
        foodTruck.setLocation("campbell");
        foodTruck.setDeparts("11/05/20/20/00");

        menu.setItem("pasta");
        menu.setAdds("extra sauce");
        menu.setNotes("spicy");
        menu.setSubs("pickles");

        list.add(menu);

        user.setId(0);
        user.setfName("Jimmy");
        user.setlName("Cricket");
        user.setZip(99999);
        Login login = new Login();
        login.setUser("bugs");
        login.setPassword("Bunny5!!");
        user.setLogin(login);

        order.setId(0);
        order.setOrderTime("11/05/20/12/30");
        order.setPickUpTime("11/05/20/13/30");
        order.setMenuItems(list);
        order.setUser(user);
        order.setFoodTruck(foodTruck);

        List<Order> list1 = svc.retrieveAll();
        assertNotNull(list1);
        int initialSize = list1.size();

        //test crud
        order = svc.create(order);
        assertNotNull(order);

        list1 = svc.retrieveAll();
        int size = list1.size();
        assertEquals(initialSize +1, size);

        order.setPickUpTime("11/05/20/14/00");
        order = svc.update(order);
        assertNotNull(order);
        assertEquals("11/05/20/14/00", order.getPickUpTime());

        order = svc.delete(order);
        assertNotNull(order);
        list1 = svc.retrieveAll();
        size = list1.size();
        assertEquals(initialSize, size);
    }
}
