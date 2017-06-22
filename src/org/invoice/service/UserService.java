package org.invoice.service;

import org.invoice.model.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpSession;
import java.util.List;
/**
 * Created by ran on 06/04/17.
 */
public interface UserService {
    public boolean register(User user, Errors errors);

    public boolean login(User user, Errors errors, HttpSession session);

    public void logout(int userId, HttpSession session);

    public boolean modifyUserInfo(User user, Errors errors);

    public boolean modifyUserPassword(int userId, String newPassword, Errors errors);

    public List<User> getAllUserList(int userId);

    public User findUserByUserId(int userId);

    public User findUserByUserName(String username);

    public User findUserByUserNameFromDB(String username);

    public User findUserByUserNameAndPasswordFromDB(String username, String password);

    public void addAuthorityOfUser(int authority, User user);

    public void removeAuthorityOfUser(int authority, User user);

    public boolean validateUserLoginInformation(User user);

}
