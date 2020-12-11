package Lists;

import java.util.ArrayList;

import Domain.FoodTruck;
import Domain.MenuItems;
import sharwarko.truckdriver.R;

/**
 * list for FoodTruck obj for display list. Add new truck here.
 */
public class FoodTruckList {

    public ArrayList<FoodTruck> getTruckList(){
        ArrayList<FoodTruck> foodTrucks = new ArrayList<>();
        FoodTruck foodTruck = new FoodTruck("SanFran1","CVS Parking lot on Market Street");
        FoodTruck foodTruck2 = new FoodTruck("SanFran2","Van Ness and Sacramento St");
        FoodTruck foodTruck3 = new FoodTruck("SanFran3","Academy of Science Parking Lot");

        foodTrucks.add(foodTruck);
        foodTrucks.add(foodTruck2);
        foodTrucks.add(foodTruck3);

        return foodTrucks;
    }


}
