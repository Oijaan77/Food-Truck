package Service;

import java.util.LinkedList;
import java.util.List;

import Domain.Login;
import Domain.User;

/**
 * Class not in use in this version. Save for later implementation.
 */
public class UserCacheImplSvc {
    private int nextId = 0;
    private List<User> users = new LinkedList<User>();


    private static UserCacheImplSvc instance = new UserCacheImplSvc();
    public static UserCacheImplSvc getInstance(){
        return instance;
    }


    public User create(User user) {
        user.setId(++nextId);
        users.add(user);
        return user;
    }

    public List<User> retrieveAll() {
        return users;
    }

    public User update(User user) {

        for (int i = 0; i < users.size(); i++) {
            User o = users.get(i);
            if (o.getId() == user.getId()) {
                users.set(i, user);
                break;
            }
        }
        return user;
    }

    public User delete(User user) {
        int size = users.size();
        for (int i = 0; i < size; i++) {
            if (users.get(i).getId() == user.getId()) {
                users.remove(i);
                break;
            }
        }
        return user;
    }
}
