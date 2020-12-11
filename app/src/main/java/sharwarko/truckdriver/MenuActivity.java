package sharwarko.truckdriver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import Lists.MenuItemsList;
import Lists.MenuListAdapter;

/**
 * Menu activity to display the menu
 */
public class MenuActivity extends AppCompatActivity {
    private ListView listmenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MenuItemsList menuItemsList = new MenuItemsList();
        listmenu = (ListView) findViewById(R.id.menuList);

        MenuListAdapter adapter = new MenuListAdapter(this, R.layout.adapter_view, menuItemsList.getMenuList());
        listmenu.setAdapter(adapter);

    }

}
