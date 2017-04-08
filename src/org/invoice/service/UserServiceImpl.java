package org.invoice.service;

import org.invoice.domain.Authority;
import org.invoice.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 李浩然 on 2017/4/8.
 */
@Service
public class UserServiceImpl implements UserService {

    private List<User> allUsers;

    public UserServiceImpl() {
        allUsers = new ArrayList<>();
    }

    @Override
    public User findUserById(String id) {
        for(int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getId().equals(id))
                return allUsers.get(i);
        }
        return null;
    }

    @Override
    public User findUserByUserName(String userName) {
        for(int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getUsername().equals(userName))
                return allUsers.get(i);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return allUsers;
    }

    @Override
    public void addAuthorityOfUser(Authority authority, User user) {
        user.setAuthority(authority);
    }

    @Override
    public void addAuthorityOfUser(int authority, User user) {
        user.setAuthority(authority);
    }

    @Override
    public void removeAuthorityOfUser(Authority authority, User user) {
        user.removeAuthority(authority);
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
            Authority authority = new Authority(Authority.AUTHORITY_QUERY_INVOICE_RECORD |
                    Authority.AUTHORITY_MODIFY_INVOICE_RECORD |
                    Authority.AUTHORITY_ADD_INVOICE_RECORD |
                    Authority.AUTHORITY_REMOVE_INVOICE_RECORE |
                    Authority.AUTHORITY_QUERY_INVOICE_ANALYSIS_RESULT |
                    Authority.AUTHORITY_MANAGE_USER |
                    Authority.AUTHORITY_QUERY_SYSTEM_LOG);
            user.setAuthority(authority);
            user.setId("0000");
            return true;
        }
        return false;
    }

    private boolean isLoginInformationCorrect(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}
