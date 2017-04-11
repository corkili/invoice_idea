package org.invoice.service;

import org.invoice.model.User;

import java.util.List;
/**
 * Created by ran on 06/04/17.
 */
public interface UserService {
    public User findUserById(int id);

    public User findUserByUserName(String username);

    public User findUserByUserNameFromDB(String username);

    public User findUserByUserNameAndPasswordFromDB(String username, String password);

    public List<User> getAllUsers();

    public void addAuthorityOfUser(int authority, User user);

    public void removeAuthorityOfUser(int authority, User user);

    public boolean validateUserLoginInformation(User user);
}
