package sharwarko.truckdriver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Domain.FoodTruck;
import Domain.Order;
import Lists.AdminListAdapter;
import Service.AdminCacheImplSvc;

/**
 * Admin activity
 */
public class AdminActivity extends AppCompatActivity {
    private ListView orderList;
    CheckBox complete;
    AdminCacheImplSvc adminCacheImplSvc = AdminCacheImplSvc.getInstance();
    private final String TAG = "AdminActivity";
    private ArrayList<Order> orders = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //get all orders that are not complete
        orders = adminCacheImplSvc.retrieveAll();
        orderList = (ListView) findViewById(R.id.adminList);

        AdminListAdapter adapter = new AdminListAdapter(this, R.layout.adapter_view5, orders);
        orderList.setAdapter(adapter);

        complete = (CheckBox) findViewById(R.id.chkBox);
        Button selBtn = (Button) findViewById(R.id.updateBtn);
        selBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpdate();
            }
        });
    }
    //refresh list
    @Override
    protected void onStart() {
        super.onStart();
        adminCacheImplSvc.clearAll();
        adminCacheImplSvc.updateList();
        supportInvalidateOptionsMenu();
    }
    //btn func to update checked items (complete order)
    private void setUpdate() {
        for (int i = 0; i < orderList.getAdapter().getCount(); i++) {
            TextView tv = (TextView) orderList.getChildAt(i).findViewById(R.id.tvID);
            complete = (CheckBox) orderList.getChildAt(i).findViewById(R.id.chkBox);

            if (complete.isChecked()) {
                String id = tv.getText().toString();
                adminCacheImplSvc.update(id);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        }
    }
}