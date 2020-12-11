package Domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Order obj contains order information
 */
public class Order implements Serializable {
    private int id = -1;
    private String cost;
    private String pickUpTime = "";
    private Login login;
    private FoodTruck foodTruck;
    ArrayList<MenuItems> menuItems;
    private  ArrayList<Integer> ids;
    private Boolean complete = false;

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    /**
     * Method to get Mongo id in case of later implementation *Unused
     * @return list of Mongo ids
     */
    public ArrayList<Integer> getIds() {
        return ids;
    }
    /**
     * Method to get Mongo id in case of later implementation *Unused
     * @return Mongo ids
     */
    public void setIds(ArrayList<Integer> ids) {
        this.ids = ids;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setMenuItems(ArrayList<MenuItems> menuItems) {
        this.menuItems = menuItems;
    }

    public FoodTruck getFoodTruck() {
        return foodTruck;
    }

    public void setFoodTruck(FoodTruck foodTruck) {
        this.foodTruck = foodTruck;
    }

    public List<MenuItems> getMenuItems() {
        return menuItems;
    }


    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
