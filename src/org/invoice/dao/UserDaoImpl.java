package org.invoice.dao;

import org.invoice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by 李浩然 on 2017/4/11.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return null;
    }

}
