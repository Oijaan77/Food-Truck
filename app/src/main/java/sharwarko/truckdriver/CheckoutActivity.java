package sharwarko.truckdriver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import Domain.Login;
import Domain.MenuItems;
import Domain.Order;
import Service.IOrderSvc;
import Service.OrderCacheImplSvc;
import Service.Session;

/**
 * Checkout Activity
 */
public class CheckoutActivity extends AppCompatActivity {
    TextView foodList;
    ArrayList<MenuItems> menuItems;
    Session session;
    Button checkOut;
    Button editOrder;
    private IOrderSvc orderSvc;
    private final String TAG = "CheckoutActivity";
    //Paypal will be primary payment method, but need to deploy application to complete implementation
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId("YOUR CLIENT ID");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //get passed in data
        session = new Session(this);
        Order order = (Order) getIntent().getSerializableExtra("Order");
        //update order
        Login login = new Login();
        String email = session.getEmail();
        login.setEmail(email);

        orderSvc = OrderCacheImplSvc.getInstance();
        order.setLogin(login);

        //format food items
        menuItems = (ArrayList<MenuItems>) order.getMenuItems();
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        for(int j = 0; j < menuItems.size(); j++){
            if (menuItems.get(j).getItem().equals("Burrito"))
                ++count1;
            if (menuItems.get(j).getItem().equals("Pizza"))
                ++count2;
            if (menuItems.get(j).getItem().equals("Lasagna"))
                ++count3;
        }
        String burrito = "";
        String pizza = "";
        String lasagna = "";
        if (count1 != 0){
            burrito = "Burrito X" + count1;
        }
        if (count2 != 0){
            pizza = "Pizza X" + count2;
        }
        if (count3 != 0){
            lasagna = "Lasagna X" + count3;
        }
        String foods = (burrito + "\n"+
                pizza +"\n"+ lasagna);

        //paypal btn
        final Button paypal = (Button) findViewById(R.id.paypal_button);
        //get total cost
        double total = 0;
        double foodItem;
        for (int i = 0; i < order.getMenuItems().size(); i++) {
            foodItem = Double.parseDouble(order.getMenuItems().get(i).getCost());
            total += foodItem;
        }
        order.setCost(Double.toString(total));

        foodList = (TextView) findViewById(R.id.fList);
        foodList.setText(foods + "\n\n" + order.getFoodTruck() + "\n\n" + order.getPickUpTime() + "\n\n Amount Due:  $" + total);

        checkOut = (Button) findViewById(R.id.chxOutBtn);
        checkOut.setText("Pay at Truck Total: $" +total);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckOut();
            }
        });

        editOrder = (Button) findViewById(R.id.editBtn);
        editOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEdit();
            }
        });


    }

    /**
     * Edit order functionality
     */
    private void setEdit() {
        Order order = (Order) getIntent().getSerializableExtra("Order");
        Intent intent = new Intent(this, CreateOrderActivity.class);
        intent.putExtra("Order", order);
        startActivity(intent);
    }

    /**
     * Checkout functionality--> create order
     */
    private void setCheckOut() {
        Order order = (Order) getIntent().getSerializableExtra("Order");
        orderSvc.create(order);
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
    /**public void beginPayment(View view) {
        Intent serviceConfig = new Intent(this, PayPalService.class);
        serviceConfig.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(serviceConfig);
        PayPalPayment payment = new PayPalPayment((new BigDecimal("5.65")),
                "USD", "My Awesome Item", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent paymentConfig = new Intent(this, PaymentActivity.class);
        paymentConfig.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        paymentConfig.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(paymentConfig, 0);
    }**/
}
