package org.invoice.dao;

import org.invoice.model.User;

import java.util.List;

/**
 * Created by 李浩然 on 2017/4/11.
 */
public interface UserDao {
    public static final String TABLE_USER = "table_user";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_SALT = "salt";
    public static final String COL_AUTHORITY = "authority";
    public static final String COL_NAME = "name";
    public static final String COL_JOB_ID = "job_id";
    public static final String COL_PHONE = "phone";
    public static final String COL_EMAIL = "email";

    public User findUserByUserId(final String userId);
    public User findUserByUserName(final String username);
    public User findUserByUsernameAndPassword(final String username, final String password);
    public List<User> findAllUser();
    public void addUser(User user);
    public void updateUser(User user);
}

