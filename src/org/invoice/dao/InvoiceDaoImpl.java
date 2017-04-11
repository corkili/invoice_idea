package org.invoice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by 李浩然 on 2017/4/12.
 */
@Repository
public class InvoiceDaoImpl implements InvoiceDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

}
