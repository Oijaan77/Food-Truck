package sharwarko.truckdriver.domain;

import org.junit.Test;

import Domain.MenuItems;

import static org.junit.Assert.*;


public class MenuItemsTest {

    public MenuItemsTest(){}

    @Test
    public void testMenuItems() {
        MenuItems menuItems = new MenuItems();
        boolean result = menuItems.Validate();
        assertFalse(result);
        menuItems.setItem("food");
        result = menuItems.Validate();
        assertFalse(result);
        menuItems.setAdds("forks");
        result = menuItems.Validate();
        assertFalse(result);
        menuItems.setSubs("spoons");
        result = menuItems.Validate();
        assertFalse(result);
        menuItems.setNotes("notes notes notes");
        result = menuItems.Validate();
        assertTrue(result);

    }

}