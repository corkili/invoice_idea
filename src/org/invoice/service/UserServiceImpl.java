package org.invoice.service;

import org.invoice.dao.UserDao;
import org.invoice.model.Authority;
import org.invoice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李浩然 on 2017/4/8.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private List<User> allUsers;

    public UserServiceImpl() {
        allUsers = new ArrayList<>();
    }

    @Override
    public User findUserById(int userId) {
        for (User allUser : allUsers) {
            if (allUser.getUserId() == userId)
                return allUser;
        }
        return null;
    }

    @Override
    public User findUserByUserName(String username) {
        for (User allUser : allUsers) {
            if (allUser.getUsername().equals(username))
                return allUser;
        }
        return null;
    }

    @Override
    public User findUserByUserNameFromDB(String username) {
        return userDao.findUserByUserName(username);
    }

    @Override
    public User findUserByUserNameAndPasswordFromDB(String username, String password) {
        return userDao.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public List<User> getAllUsers() {
        return allUsers;
    }

    @Override
    public void addAuthorityOfUser(int authority, User user) {
        user.setAuthority(authority);
    }

    @Override
    public void removeAuthorityOfUser(int authority, User user) {
        user.removeAuthority(authority);
    }

    @Override
    public boolean validateUserLoginInformation(User user) {
        return saveUserIfLoginInfoCorrect(user);
    }

    private boolean saveUserIfLoginInfoCorrect(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if(isLoginInformationCorrect(username, password)) {
            user.setAuthority(Authority.getAllAuthority());
            user.setUserId(0);
            return true;
        }
        return false;
    }

    private boolean isLoginInformationCorrect(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}
