package Service;

import java.util.List;

import Domain.User;

/**
 * Interface for user CRUD. Currently not being used. Save for future implementation.
 */
public interface IUserSvc {
    public User create(User user);
    public List<User> retrieveAll();
    public User update(User user);
    public User delete(User order);
}
