package Domain;

import java.io.Serializable;

/**
 * Food truck class object to hols food truck information.
 */
public class FoodTruck implements Serializable {
    private String name = "";
    private String location = "";

    public FoodTruck() {
    }

    @Override
    public String toString() {
        return "FoodTruck: " +
                 name;
    }

    /**
     * Overloaded constructor for list of food trucks
     * @param name
     * @param location
     */
    public FoodTruck(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
