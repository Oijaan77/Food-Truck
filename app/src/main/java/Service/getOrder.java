package Service;

/**
 * Class to handle Get requests from Node to Retrofit. Data returns in this format
 */
public class getOrder {
    private String _id = "";
    private String id = "";
    private String email = "";
    private String items = "";
    private String truck = "";
    private String time = "";
    private String cost = "";
    private Boolean complete = false;

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public String  get_id() {
        return _id;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getItems() {
        return items;
    }

    public String getTruck() {
        return truck;
    }

    public String getTime() {
        return time;
    }

    public String getCost() {
        return cost;
    }
}
