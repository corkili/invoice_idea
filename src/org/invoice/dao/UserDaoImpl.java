package org.invoice.dao;

import org.apache.log4j.Logger;
import org.invoice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by 李浩然 on 2017/4/11.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public User findUserByUserName(String username) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ").append(COL_USER_ID).append(',').append(COL_USERNAME).append(',')
                .append(COL_PASSWORD).append(',').append(COL_AUTHORITY).append(" from ").append(TABLE_USER)
                .append(" where ").append(COL_USERNAME).append("=?");
        String sql = stringBuilder.toString();
        logger.info("sql: " + sql);
        User user = null;
        RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
        user = jdbcTemplate.queryForObject(sql, new Object[]{username}, rowMapper);
        return user;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ").append(COL_USER_ID).append(',').append(COL_USERNAME).append(',')
                .append(COL_PASSWORD).append(',').append(COL_AUTHORITY).append(" from ").append(TABLE_USER)
                .append(" where ").append(COL_USERNAME).append("=?").append(" And ").append(COL_PASSWORD).append("=?");
        String sql = stringBuilder.toString();
        logger.info("sql: " + sql);
        User user = null;
        RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
        user = jdbcTemplate.queryForObject(sql, new Object[]{username, password}, rowMapper);
        return user;
    }

}
