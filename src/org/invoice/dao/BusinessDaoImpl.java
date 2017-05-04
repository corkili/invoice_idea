package org.invoice.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 李浩然 on 2017/5/5.
 */
@Repository
public class BusinessDaoImpl implements  BusinessDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger = Logger.getLogger(InvoiceDaoImpl.class);

    @Override
    public double getSumBetweenTwoDate(String colName, String tableName, Date start, Date end) {
        String sql = "select sum(" + colName + ") from " + tableName + " where " + COL_DATE + " between ? and ?";
        double sum = 0.0;
        try {
            sum = jdbcTemplate.queryForObject(sql, new Object[]{ start, end }, Double.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

    @Override
    public double getPropertyOnDate(Date date) {
        String sql = "select " + COL_PROPERTY + " from " + TABLE_BASIC_SITUATION + " where "
                + COL_DATE + "=?";
        double property = 0;
        try {
            property = jdbcTemplate.queryForObject(sql, new Object[]{ date }, Double.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return property;
    }
}
