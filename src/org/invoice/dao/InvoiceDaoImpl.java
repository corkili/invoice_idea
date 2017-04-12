package org.invoice.dao;

import org.apache.log4j.Logger;
import org.invoice.model.Invoice;
import org.invoice.model.InvoiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 李浩然 on 2017/4/12.
 */
@Repository
public class InvoiceDaoImpl implements InvoiceDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger = Logger.getLogger(InvoiceDaoImpl.class);

    @Override
    public void addInvoice(Invoice invoice) {
        String invoiceSql = "insert into " + TABLE_INVOICE + " (" + COL_INVOICE_ID + "," + COL_INVOICE_CODE + ","
                + COL_INVOICE_DATE + "," + COL_BUYER_NAME + "," + COL_BUYER_ID + "," + COL_SELLER_NAME + ","
                + COL_SELLER_ID + "," + COL_TOTAL_AMOUNT + "," + COL_TOTAL_TAX + "," + COL_TOTAL + ","
                + COL_REMARK + ") values(?,?,?,?,?,?,?,?,?,?,?)";
        String detailSql = "insert into " + TABLE_DETAILES + " (" + "," + COL_INVOICE_ID + "," + COL_DETAIL_NAME + ","
                + COL_SPECIFICATION + "," + COL_UNIT_NAME + "," + COL_QUANTITY + "," + COL_UNIT_PRICE + ","
                + COL_AMOUNT + "," + COL_TAX_RATE + "," + COL_TAX_SUM + ") values(?,?,?,?,?,?,?,?,?)";
        logger.info("sql: " + invoiceSql);
        jdbcTemplate.update(invoiceSql, new Object[]{ invoice.getInvoiceId(), invoice.getInvoiceCode(),
                invoice.getInvoiceDate(), invoice.getBuyerName(), invoice.getBuyerId(), invoice.getSellerName(),
                invoice.getSellerId(), invoice.getTotalAmount(), invoice.getTotalTax(), invoice.getTotal(),
                invoice.getRemark() });
        List<InvoiceDetail> details = invoice.getDetails();
        List<Object[]> batchArgs = new ArrayList<>();
        for(InvoiceDetail detail : details) {
            batchArgs.add(new Object[]{ invoice.getInvoiceId(), detail.getDetailName(), detail.getSpecification(),
                    detail.getUnitName(), detail.getQuantity(), detail.getUnitPrice(), detail.getAmount(),
                    detail.getTaxRate(), detail.getTaxSum() });
        }
        logger.info("count: " + batchArgs.size() + " , sql: " + detailSql);
        jdbcTemplate.batchUpdate(detailSql, batchArgs);
    }

    @Override
    public List<Invoice> findInvoiceByInvoiceId(String invoiceId) {
        List<Invoice> result = new ArrayList<>();
        String sql = getSelectFromInvoiceBaseSql() + "where " + COL_INVOICE_ID + "=?";
        Invoice invoice = null;
        try {
            RowMapper<Invoice> invoiceRowMapper = new BeanPropertyRowMapper<Invoice>(Invoice.class);
            RowMapper<InvoiceDetail> detailRowMapper = new BeanPropertyRowMapper<InvoiceDetail>(InvoiceDetail.class);
            invoice = jdbcTemplate.queryForObject(sql, invoiceRowMapper, invoiceId);
            if(invoice != null) {
                sql = getSelectFromDetailBaseSql() + "where " + COL_INVOICE_ID + "=?";
                List<InvoiceDetail> details = jdbcTemplate.query(sql, detailRowMapper, invoice.getInvoiceId());
                invoice.setDetails(details);
                result.add(invoice);
            }
        } catch (Exception e) {
            // do nothing
        }
        return result;
    }

    @Override
    public List<Invoice> findInvoiceByInvoiceCode(String invoiceCode) {
        return null;
    }

    @Override
    public List<Invoice> findInvoicesByDate(Date date) {
        return null;
    }

    @Override
    public List<Invoice> findInvoicesByDate(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<Invoice> findInvoicesByBuyerName(String buyerName) {
        return null;
    }

    @Override
    public List<Invoice> findInvoicesByBuyerId(String buyerId) {
        return null;
    }

    @Override
    public List<Invoice> findInvoicesBySellerName(String sellerName) {
        return null;
    }

    @Override
    public List<Invoice> findInvoicesBySellerId(String sellerId) {
        return null;
    }

    @Override
    public List<Invoice> findInvoicesByTotalAmount(String totalAmount) {
        return null;
    }

    @Override
    public List<Invoice> findInvoicesByTotalTax(String totalTax) {
        return null;
    }

    private String getSelectFromInvoiceBaseSql() {
        return "select " + COL_INVOICE_ID + "," + COL_INVOICE_CODE + "," + COL_INVOICE_DATE + ","
                + COL_BUYER_NAME + "," + COL_BUYER_ID + "," + COL_SELLER_NAME + "," + COL_SELLER_ID + ","
                + COL_TOTAL_AMOUNT + "," + COL_TOTAL_TAX + "," + COL_TOTAL + "," + COL_REMARK
                + " from " + TABLE_INVOICE + " ";
    }

    private String getSelectFromDetailBaseSql() {
        return "select " + COL_INVOICE_ID + "," + COL_DETAIL_NAME + "," + COL_SPECIFICATION + ","
                + COL_UNIT_NAME + "," + COL_QUANTITY + "," + COL_UNIT_PRICE + "," + COL_AMOUNT + ","
                + COL_TAX_RATE + "," + COL_TAX_SUM + " from " + TABLE_DETAILES + " ";
    }
}
