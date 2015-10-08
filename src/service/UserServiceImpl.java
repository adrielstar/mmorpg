package service;

/**
 * Created by Adriel on 10/8/2015.
 */
import dataAccObject.MainDataAccObject;
import models.User;
import units.EntityEnum;

import java.util.List;

public class UserServiceImpl extends MainDataAccObject implements UserService {

    @Override
    public boolean addUser(User user) {
        return add(user);
    }

    @Override
    public List<User> userList() { return getList(EntityEnum.User); }

    @Override
    public boolean deleteUser(String userName) {
        return delete(userName);
    }

    @Override
    public boolean updateUser(User user) {
        return update(user);
    }
}
