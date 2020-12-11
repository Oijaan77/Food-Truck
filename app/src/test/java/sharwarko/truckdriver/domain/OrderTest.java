package sharwarko.truckdriver.domain;

import org.junit.Test;

import java.util.ArrayList;

import Domain.FoodTruck;
import Domain.Login;
import Domain.MenuItems;
import Domain.Order;
import Domain.User;

import static org.junit.Assert.*;


public class OrderTest {

    public OrderTest(){}

    @Test
    public void testOrder() {
        Order order = new Order();

        boolean result = order.Validate();
        order.setOrderTime("11/05/20/12/30");
        result = order.Validate();
        assertFalse(result);

        order.setPickUpTime("11/05/20/13/30");
        result = order.Validate();

        assertTrue(result);

    }

}
