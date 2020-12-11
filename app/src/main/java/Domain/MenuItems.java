package Domain;

import java.io.Serializable;

/**
 * MenuItems obj for food items
 */
public class MenuItems implements Serializable {
    private String item = "";
    private int image;
    public String price;
    private String notes = "";

    public MenuItems() {

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    /**
     * Overloaded constructor for menu list
     * @param image
     * @param item
     * @param notes
     * @param price
     */
    public MenuItems(int image, String item, String notes, String price) {
        this.image = image;
        this.item = item;
        this.notes = notes;
        this.price = price;
    }

    @Override
    public String toString() {
        return  item;
    }
    public String getCost(){
        return price;
    }

    /**
     * Set price method to add price to item
     * @param food
     * @return
     */
    public String getPrice(String food) {
        if(food == "Lasagna"){
            setPrice("13.00");
        }
        if(food == "Pizza"){
            setPrice("10.00");
        }
        if(food == "Burrito"){
            setPrice("10.00");
        }
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
