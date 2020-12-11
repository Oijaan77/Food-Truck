package Service;

import java.util.ArrayList;

import Domain.Order;

/**
 * Interface for Admin CRUD
 */
public interface IAdminSvc {
    public Order create(Order order);
    public ArrayList<Order> retrieveAll();
    public void updateList();
    public Order update(String id);
    public Order delete(Order order);
}
