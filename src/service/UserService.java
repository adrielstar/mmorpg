package service;

/**
 * Created by Adriel on 10/8/2015.
 */
import models.User;

import java.util.List;

public interface UserService {
    boolean addUser(User user);
    List<User> userList();
    boolean deleteUser(String userName);
    boolean updateUser(User user);
}
