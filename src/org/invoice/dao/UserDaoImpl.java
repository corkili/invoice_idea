package org.invoice.dao;

import org.apache.log4j.Logger;
import org.invoice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
    public User findUserByUserName(String username) {
        String sql = "select " + COL_USER_ID + ',' + COL_USERNAME + ',' +
                COL_PASSWORD + ',' + COL_AUTHORITY + " from " + TABLE_USER +
                " where " + COL_USERNAME + "=?";
        logger.info("sql: " + sql);
        User user = null;
        try {
            RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
            user = jdbcTemplate.queryForObject(sql, new Object[]{username}, rowMapper);
        } catch (Exception e) {
            // do nothing
        }
        return user;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        String sql = "select " + COL_USER_ID + ',' + COL_USERNAME + ',' +
                COL_PASSWORD + ',' + COL_AUTHORITY + " from " + TABLE_USER +
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
    public void addUser(User user) {
        String sql = "insert into " + TABLE_USER + " (" + COL_USERNAME + "," + COL_PASSWORD + ","
                + COL_AUTHORITY + ") values(?,?,?)";
        logger.info("sql: " + sql);
        try {
            jdbcTemplate.update(sql, new Object[]{user.getUsername(), user.getPassword(), user.getAuthority()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        String sql = "update " + TABLE_USER + " set " + COL_USERNAME + "=?," + COL_PASSWORD + "=?,"
                + COL_AUTHORITY + "=? where " + COL_USER_ID + "=?";
        logger.info("sql: " + sql);
        try {
            jdbcTemplate.update(sql, new Object[]{user.getUsername(), user.getPassword(), user.getAuthority(),user.getUserId()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
