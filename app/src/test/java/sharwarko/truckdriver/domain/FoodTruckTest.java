package sharwarko.truckdriver.domain;
import org.junit.Test;

import Domain.FoodTruck;

import static org.junit.Assert.*;


public class FoodTruckTest {

    public FoodTruckTest(){}

    @Test
    public void testFoodTruck() {
        FoodTruck foodTruck = new FoodTruck();
        boolean result = foodTruck.Validate();
        assertFalse(result);
        foodTruck.setName("trucker");
        result = foodTruck.Validate();
        assertFalse(result);
        foodTruck.setLocation("backwoods");
        result = foodTruck.Validate();
        assertFalse(result);
        foodTruck.setDeparts("11/06/20/20/30");
        result = foodTruck.Validate();
        assertTrue(result);

    }

}
