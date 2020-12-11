package Service;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Domain.FoodTruck;
import Domain.Login;
import Domain.MenuItems;
import Domain.Order;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *Admin cache for CRUD operations for get and update data from Mongo
 */
public class AdminCacheImplSvc implements IAdminSvc{
    ArrayList<Order> orders = new ArrayList<>();
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private final String TAG = "AdminCacheImplSvc";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    HashMap<String, String> maps = new HashMap<>();

    private static AdminCacheImplSvc instance = new AdminCacheImplSvc();
    public static AdminCacheImplSvc getInstance(){
        return instance;
    }

    @Override
    public Order create(Order order) {
        return null;
    }

    @Override
    public ArrayList<Order> retrieveAll() {
        return orders;
    }
    public ArrayList<Order> clearAll() {
        orders.clear();
        return orders;
    }

    /**
     * Call get request and re-format to order obj
     */
    @Override
    public void updateList() {
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<List<getOrder>> call2 = retrofitInterface.getOrders();
        call2.enqueue(new Callback<List<getOrder>>() {
            @Override
            public void onResponse(Call<List<getOrder>> call, Response<List<getOrder>> response) {
                List<getOrder> postsList = response.body();
                for(int i = 0; i < postsList.size(); i++){
                    Order order = new Order();
                    order.setId(Integer.parseInt(postsList.get(i).getId()));
                    Login login = new Login();
                    login.setEmail(postsList.get(i).getEmail());
                    order.setLogin(login);
                    FoodTruck foodTruck = new FoodTruck();
                    foodTruck.setName(postsList.get(i).getTruck());
                    order.setFoodTruck(foodTruck);
                    order.setPickUpTime(postsList.get(i).getTime());
                    MenuItems menuItem = new MenuItems();
                    ArrayList<MenuItems> menuItems = new ArrayList<>();
                    String[] items = postsList.get(i).getItems().split(",");
                    int count1 = 0;
                    int count2 = 0;
                    int count3 = 0;
                    //String item = "";
                    for(int j = 0; j < items.length; j++){
                        String item = items[j].replaceAll("[\\p{Ps}\\p{Pe}\\s]", "");
                        if (item.equals("Burrito"))
                            ++count1;
                        if (item.equals("Pizza"))
                            ++count2;
                        if (item.equals("Lasagna"))
                            ++count3;

                    }
                    menuItem.setItem("Burrito: " + count1 + ", "+
                            " Pizza: " + count2 + ", "+
                            " Lasagna: " + count3);
                    menuItems.add(menuItem);
                    order.setMenuItems(menuItems);
                    order.setCost(postsList.get(i).getCost());
                    order.setComplete(postsList.get(i).getComplete());
                    if (!order.getComplete()) {
                        orders.add(order);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<getOrder>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }

    /**
     * Use id as DAO and update order to complete. Put request to Mongo
     * @param id
     * @return
     */
    @Override
    public Order update(String id) {
        Order order = new Order();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == Integer.parseInt(id)) {
                order = orders.get(i);
            }
        }
        order.setComplete(true);
        maps.put("id", Integer.toString(order.getId()));
        maps.put("email", order.getLogin().getEmail());
        String items = "";
        for (int i = 0; i < order.getMenuItems().size(); i++){
            items = order.getMenuItems()+",";
        }
        maps.put("items", items);
        maps.put("truck", order.getFoodTruck().getName());
        maps.put("time", order.getPickUpTime());
        maps.put("cost", order.getCost());
        maps.put("complete", order.getComplete().toString());

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Void> call3 = retrofitInterface.updateOrder(maps);
        call3.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.code() == 200) {
                    Log.i(TAG, "Successfully updated order");
                } else if (response.code() == 400) {
                    Log.i(TAG, "could not find order");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Order delete(Order order) {
        return null;
    }
}
