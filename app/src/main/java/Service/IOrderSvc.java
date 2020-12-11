package Service;

import java.util.ArrayList;
import java.util.List;

import Domain.Order;

/**
 * Interface for Order CRUD
 */
public interface IOrderSvc {
    public Order create(Order order);
    public ArrayList<Order> retrieveAll();
    public void updateList(String email);
    public Order update(int id);
    public Order delete(Order order);
}
