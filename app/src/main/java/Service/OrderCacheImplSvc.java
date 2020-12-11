package Service;


import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
 * Order Cache CRUD operations.
 */
public class OrderCacheImplSvc implements IOrderSvc {
    private int nextId = 0;
    HashMap<String, String> map = new HashMap<>();
    private List<Integer> ids = new LinkedList<>();
    ArrayList<Order> orders = new ArrayList<>();
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private final String TAG = "OrderCacheImplSvc";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    private static OrderCacheImplSvc instance = new OrderCacheImplSvc();
    public static OrderCacheImplSvc getInstance(){
        return instance;
    }

    /**
     * Create order by Get request for IDs to increment nextId. Then Post request with new order
     * @param order
     * @return
     */
    public Order create(final Order order) {


        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<List<getOrder>> call2 = retrofitInterface.getOrders();
        call2.enqueue(new Callback<List<getOrder>>() {
            @Override
            public void onResponse(Call<List<getOrder>> call, Response<List<getOrder>> response) {
                List<getOrder> postsList = response.body();
                if(!postsList.isEmpty()){
                    for(int i = 0; i < postsList.size(); i++){
                        int intId = Integer.parseInt(postsList.get(i).getId());
                        ids.add(intId);
                        Log.i(TAG, postsList.get(i).getId());
                    }
                }
                for(int k = 0; k < ids.size(); k ++){
                    for(int l = 0; l < ids.size(); l++){
                        nextId = ids.get(k);
                        if(nextId < ids.get(l)){
                            nextId = ids.get(l);
                            Log.i(TAG, Integer.toString(nextId));
                        }
                    }
                }
                Log.i(TAG, "ids stored");
                nextId++;

                order.setId(nextId);
                orders.add(order);
                //send order to server
                map.put("id", Integer.toString(order.getId()));
                map.put("email", order.getLogin().getEmail());
                String items = "";
                for (int i = 0; i < order.getMenuItems().size(); i++){
                    items = order.getMenuItems()+",";
                }
                map.put("items", items);
                map.put("truck", order.getFoodTruck().getName());
                map.put("time", order.getPickUpTime());
                map.put("cost", order.getCost());
                map.put("complete", order.getComplete().toString());

                Call<Void> call3 = retrofitInterface.executeOrder(map);
                call3.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.code() == 200) {
                            Log.i(TAG, "Successfully stored order");
                        } else if (response.code() == 400) {
                            Log.i(TAG, "Order number conflict");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.i(TAG, t.getMessage());
                    }
                });
            }
            @Override
            public void onFailure(Call<List<getOrder>> call, Throwable t) {
                Log.i(TAG, "Failure message is: " +t.getMessage());
            }
        });
        return order;
    }
    public ArrayList<Order> retrieveAll() {
        return orders;
    }
    public ArrayList<Order> clearAll() {
        orders.clear();
        return orders;
    }

    /**
     * Update cache with user orders only. Get request to Node
     * @param email
     */
    public void updateList(String email) {
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<List<getOrder>> call2 = retrofitInterface.getList(email);
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
                    for(int j = 0; j < items.length; j++){
                        items[j].replaceAll("[\\p{Ps}\\p{Pe}]", "");
                        menuItem.setItem(items[j]);
                        menuItems.add(menuItem);
                    }
                    order.setMenuItems(menuItems);
                    order.setCost(postsList.get(i).getCost());
                    order.setComplete(postsList.get(i).getComplete());
                    orders.add(order);
                }

            }
            @Override
            public void onFailure(Call<List<getOrder>> call, Throwable t) {
                    Log.i(TAG, t.getMessage());
            }
        });
    }

    /**
     * Update order method not being used. Edit posted order not supported in this version
     * @param id
     * @return
     */
    public Order update(int id) {
        Order order = new Order();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == id) {
                order = orders.get(i);
            }
        }
        return order;
    }

    /**
     * Deleting an order is not supported in this version
     * @param order
     * @return
     */
    public Order delete(Order order) {
        int size = orders.size();
        for (int i = 0; i < size; i++) {
            if (orders.get(i).getId() == order.getId()) {
                orders.remove(i);
                break;
            }

        }
        return order;
    }
}
