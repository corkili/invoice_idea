package org.invoice.dao;

import org.apache.log4j.Logger;
import org.invoice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供用户数据库操作的试题库
 * Created by 李浩然 on 2017/4/11.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public User findUserByUserId(String userId) {
        return findUser(COL_USER_ID, userId);
    }

    @Override
    public User findUserByUserName(String username) {
        return findUser(COL_USERNAME, username);
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        String sql = "select *" + " from " + TABLE_USER +
                " where " + COL_USERNAME + "=?" + " and " + COL_PASSWORD + "=?";
        logger.info("sql: " + sql);
        User user = null;
        try {
            RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
            user = jdbcTemplate.queryForObject(sql, new Object[]{username, password}, rowMapper);
        } catch (Exception e) {
            // do nothing
        }
        return user;
    }

    @Override
    public List<User> findAllUser() {
        String sql = "select * from " + TABLE_USER;
        logger.info(sql);
        List<User> userList;
        try {
            userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            userList = new ArrayList<>();
        }
        return userList;
    }

    @Override
    public void addUser(User user) {
        String sql = "insert into " + TABLE_USER + " (" + COL_USERNAME + "," + COL_PASSWORD + ","
                + COL_SALT + "," + COL_AUTHORITY + "," + COL_NAME + "," + COL_JOB_ID  + ","
                + COL_PHONE + "," + COL_EMAIL + ") values(?,?,?,?,?,?,?,?)";
        logger.info("sql: " + sql);
        try {
            jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getSalt(), user.getAuthority(),
                    user.getName(), user.getJobId(), user.getPhone(), user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        String sql = "update " + TABLE_USER + " set " + COL_USERNAME + "=?," + COL_PASSWORD + "=?,"
                + COL_SALT + "=?," + COL_AUTHORITY + "=?," + COL_NAME + "=?," + COL_JOB_ID + "=?,"
                + COL_PHONE + "=?," + COL_EMAIL + "=? where " + COL_USER_ID + "=?";
        logger.info("sql: " + sql);
        try {
            jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getSalt(), user.getAuthority(),
                    user.getName(), user.getJobId(), user.getPhone(), user.getEmail(), user.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private User findUser(String col, String value) {
        String sql = "select * from " + TABLE_USER + " where " + col + "=?";
        logger.info("sql: " + sql);
        try {
            RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
            return jdbcTemplate.queryForObject(sql, new Object[]{value}, rowMapper);
        } catch (Exception e) {
            return null;
        }
    }

}
