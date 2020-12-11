package sharwarko.truckdriver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Domain.Order;

public class CreateOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        Button sFood = (Button) findViewById(R.id.selectFoodBtn);
        sFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFoodLink();
            }
        });

        Button sLoc = (Button) findViewById(R.id.selectLocBtn);
        sLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocLink();
            }
        });

        Button sTime = (Button) findViewById(R.id.selectTimeBtn);
        sTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeLink();
            }
        });

        Button checkOut = (Button) findViewById(R.id.checkOutBtn);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLCheckLink();
            }
        });
    }
    private void setFoodLink() {
        Intent intent = new Intent(this, SelectFoodActivity.class);
        startActivity(intent);
    }
    private void setLocLink() {
        Order order = (Order) getIntent().getSerializableExtra("Order");
        Intent intent = new Intent(this, SelectLocationActivity.class);
        intent.putExtra("Order", order);
        startActivity(intent);
    }
    private void setTimeLink() {
        Order order = (Order) getIntent().getSerializableExtra("Order");
        Intent intent = new Intent(this, SelectTimeActivity.class);
        intent.putExtra("Order", order);
        startActivity(intent);
    }
    private void setLCheckLink() {
        Order order = (Order) getIntent().getSerializableExtra("Order");
        Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putExtra("Order", order);
        startActivity(intent);
    }
}
