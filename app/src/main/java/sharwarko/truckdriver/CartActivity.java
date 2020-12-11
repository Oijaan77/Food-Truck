package sharwarko.truckdriver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Domain.Order;
import Lists.MenuItemsList;
import Lists.OrdersListAdapter;
import Lists.SelectFoodListAdapter;
import Service.IOrderSvc;
import Service.OrderCacheImplSvc;
import Service.Session;

/**
 * Cart activity
 */
public class CartActivity extends AppCompatActivity {
    Session session;
    private ArrayList<Order> orders = new ArrayList<>();
    private ListView orderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //get list of user orders by session login
        OrderCacheImplSvc orderCacheImplSvc = OrderCacheImplSvc.getInstance();
        session = new Session(this);
        orders = orderCacheImplSvc.retrieveAll();
        orderList = (ListView) findViewById(R.id.orderList);

        OrdersListAdapter adapter = new OrdersListAdapter(this, R.layout.adapter_view4, orders);
        orderList.setAdapter(adapter);

    }
}