package org.invoice.service;

import org.invoice.domain.Authority;
import org.invoice.domain.User;

import java.util.List;

/**
 * Created by ran on 06/04/17.
 */
public interface UserService {
    public User findUserById(String id);

    public User findUserByUserName(String userName);

    public List<User> getAllUsers();

    public void addAuthorityOfUser(Authority authority, User user);

    public void removeAuthoriyOfUser(Authority authority, User user);
}
