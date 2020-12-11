package sharwarko.truckdriver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;

import Domain.FoodTruck;
import Domain.Login;
import Domain.MenuItems;
import Domain.Order;
import Lists.MenuItemsList;
import Lists.MenuListAdapter;
import Lists.SelectFoodListAdapter;
import Service.OrderCacheImplSvc;
import Service.Session;

/**
 * Select food activity
 */
public class SelectFoodActivity extends AppCompatActivity {
    NumberPicker numberPicker;
    private ListView selectList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_food);

        MenuItemsList menuItemsList = new MenuItemsList();
        selectList = (ListView) findViewById(R.id.selectList);

        SelectFoodListAdapter adapter = new SelectFoodListAdapter(this, R.layout.adapter_view2, menuItemsList.getMenuList());
        selectList.setAdapter(adapter);

        Button cart = (Button) findViewById(R.id.cartBtn);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCart();
            }
        });

    }

    /**
     * Get user selection and pass partially created obj to food truck activity
     */
    private void setCart() {
        Order order = new Order();
        ArrayList<MenuItems> menu = new ArrayList<>();

        for (int i = 0; i < selectList.getAdapter().getCount(); i++) {
            TextView tv = (TextView) selectList.getChildAt(i).findViewById(R.id.textView2);
            numberPicker = (NumberPicker)selectList.getChildAt(i).findViewById(R.id.chkBox);

            if (numberPicker.getValue() > 0){
                for(int j = 0; j < numberPicker.getValue(); j++){
                    MenuItems menuItems = new MenuItems();
                    menuItems.setItem(tv.getText().toString());
                    menuItems.getPrice(tv.getText().toString());
                    menu.add(menuItems);
                }
            }
        }

        order.setMenuItems(menu);
        Intent intent = new Intent(this, SelectLocationActivity.class);
        intent.putExtra("Order", order);
        startActivity(intent);
    }
}
