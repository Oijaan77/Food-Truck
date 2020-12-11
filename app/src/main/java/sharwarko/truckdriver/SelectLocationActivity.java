package sharwarko.truckdriver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import Domain.FoodTruck;
import Domain.Order;
import Lists.FoodTruckList;
import Lists.TruckListAdapter;

/**
 * Select location activity
 */
public class SelectLocationActivity extends AppCompatActivity {
    FoodTruck foodTruck = new FoodTruck();
    CheckBox locate;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);

        FoodTruckList foodTruckList = new FoodTruckList();
        listView = (ListView) findViewById(R.id.lister);

        TruckListAdapter adapter = new TruckListAdapter(this, R.layout.adapter_view3, foodTruckList.getTruckList());
        listView.setAdapter(adapter);

        locate = (CheckBox) findViewById(R.id.chkBox);
        Button selBtn = (Button) findViewById(R.id.selectLBtn);
        selBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoc();
            }
        });
    }

    /**
     * Get user selection for foood truck and pass partially created obj to select time activity
     */
    private void setLoc() {
        int cbCount = 0;
        ArrayList<FoodTruck> trucks = new ArrayList<>();
        for (int i = 0; i < listView.getAdapter().getCount(); i++) {
            TextView tv = (TextView) listView.getChildAt(i).findViewById(R.id.textView2);
            TextView tv2 = (TextView) listView.getChildAt(i).findViewById(R.id.tvTime);
            locate = (CheckBox) listView.getChildAt(i).findViewById(R.id.chkBox);


            if (locate.isChecked()){
                FoodTruck foodTruck = new FoodTruck();
                foodTruck.setName(tv.getText().toString());
                foodTruck.setLocation(tv2.getText().toString());
                trucks.add(foodTruck);
                Order order = (Order) getIntent().getSerializableExtra("Order");
                order.setFoodTruck(foodTruck);
                Intent intent = new Intent(this, SelectTimeActivity.class);
                intent.putExtra("Order", order);
                startActivity(intent);
            }
        }




        }
    }
