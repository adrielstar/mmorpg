package service;

import dataAccObject.MainDataAccObject;
import models.User;
import init.IdentetyUnit;

import java.util.List;

/**
 * Created by Adriel on 10/8/2015.
 */


public class UserServiceImpl extends MainDataAccObject implements UserService {

    @Override
    public boolean addUser(User user) {
        return add(user);
    }

    @Override
    public List<User> userList() { return getList(IdentetyUnit.User); }

    @Override
    public boolean deleteUser(String userName) {
        return delete(userName);
    }

    @Override
    public boolean updateUser(User user) {
        return update(user);
    }
}
