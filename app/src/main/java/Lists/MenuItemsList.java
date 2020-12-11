package Lists;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import Domain.MenuItems;
import sharwarko.truckdriver.R;

/**
 * List for food items for menu display. Add new menu items here
 */
public class MenuItemsList{

    public ArrayList<MenuItems> getMenuList(){
        ArrayList<MenuItems> menuList = new ArrayList<>();
        MenuItems menuItems1 = new MenuItems(R.drawable.lasagna,"Lasagna", "Vegan cheese, red sauce, vegetables", "$13" );
        MenuItems menuItems2 = new MenuItems( R.drawable.pizza,"Pizza", "Vegan cheese, red sauce, vegetables", "$10");
        MenuItems menuItems3 = new MenuItems( R.drawable.burrito,"Burrito", "Vegan cheese, pinto beans, guacamole", "$10");

        menuList.add(menuItems1);
        menuList.add(menuItems2);
        menuList.add(menuItems3);
        return menuList;
    }


}
