package org.invoice.dao;

import org.apache.log4j.Logger;
import org.invoice.model.Invoice;
import org.invoice.model.InvoiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
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

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void addInvoice(Invoice invoice) {
        String invoiceSql = getInsertIntoInvoiceSql();
        String detailSql = getInsertIntoDetailSql();
        logger.info("sql: " + invoiceSql);
        jdbcTemplate.update(invoiceSql, getInsertIntoInvoiceParams(invoice));
        List<InvoiceDetail> details = invoice.getDetails();
        List<Object[]> batchArgs = new ArrayList<>();
        for(InvoiceDetail detail : details) {
            batchArgs.add(getInsertIntoDetailParams(invoice, detail));
        }
        logger.info("count: " + batchArgs.size() + " , sql: " + detailSql);
        jdbcTemplate.batchUpdate(detailSql, batchArgs);
    }

    @Override
    public void addInvoice(List<Invoice> invoices) {
        for(Invoice invoice : invoices) {
            addInvoice(invoice);
        }
    }

    @Override
    public List<Invoice> findInvoicesByInvoiceId(String invoiceId) {
        return findInvoices(COL_INVOICE_ID, invoiceId);
    }

    @Override
    public List<Invoice> findInvoicesByInvoiceCode(String invoiceCode) {
        return findInvoices(COL_INVOICE_CODE, invoiceCode);
    }

    @Override
    public List<Invoice> findInvoicesByDate(Date date) {
        return findInvoices(COL_INVOICE_DATE, dateFormat.format(date));
    }

    @Override
    public List<Invoice> findInvoicesByDateRange(Date startDate, Date endDate) {
        return findInvoices(startDate, endDate);
    }

    @Override
    public List<Invoice> findInvoicesByBuyerName(String buyerName) {
        return findInvoices(COL_BUYER_NAME, buyerName);
    }

    @Override
    public List<Invoice> findInvoicesByBuyerId(String buyerId) {
        return findInvoices(COL_BUYER_ID, buyerId);
    }

    @Override
    public List<Invoice> findInvoicesBySellerName(String sellerName) {
        return findInvoices(COL_SELLER_NAME, sellerName);
    }

    @Override
    public List<Invoice> findInvoicesBySellerId(String sellerId) {
        return findInvoices(COL_SELLER_ID, sellerId);
    }

    @Override
    public List<Invoice> findInvoicesByTotalAmount(String totalAmount) {
        return findInvoices(COL_TOTAL_AMOUNT, totalAmount);
    }

    @Override
    public List<Invoice> findInvoicesByTotalTax(String totalTax) {
        return findInvoices(COL_TOTAL_TAX, totalTax);
    }

    @Override
    public List<Invoice> findInvoicesByDateAndBuyerName(Date date, String buyerName) {
        return findInvoices(COL_INVOICE_DATE, dateFormat.format(date), COL_BUYER_NAME, buyerName);
    }

    @Override
    public List<Invoice> findInvoicesByDateAndBuyerId(Date date, String buyerId) {
        return findInvoices(COL_INVOICE_DATE, dateFormat.format(date), COL_BUYER_ID, buyerId);
    }

    @Override
    public List<Invoice> findInvoicesByDateRangeAndBuyerName(Date startDate, Date endDate, String buyerName) {
        return findInvoices(startDate, endDate, COL_BUYER_NAME, buyerName);
    }

    @Override
    public List<Invoice> findInvoicesByDateRangeAndBuyerId(Date startDate, Date endDate, String buyerId) {
        return findInvoices(startDate, endDate, COL_BUYER_ID, buyerId);
    }

    @Override
    public List<Invoice> findInvoicesByDateAndSellerName(Date date, String sellerName) {
        return findInvoices(COL_INVOICE_DATE, dateFormat.format(date), COL_SELLER_NAME, sellerName);
    }

    @Override
    public List<Invoice> findInvoicesByDateAndSellerId(Date date, String sellerId) {
        return findInvoices(COL_INVOICE_DATE, dateFormat.format(date), COL_SELLER_ID, sellerId);
    }

    @Override
    public List<Invoice> findInvoicesByDateRangeAndSellerName(Date startDate, Date endDate, String sellerName) {
        return findInvoices(startDate, endDate, COL_SELLER_NAME, sellerName);
    }

    @Override
    public List<Invoice> findInvoicesByDateRangeAndSellerId(Date startDate, Date endDate, String sellerId) {
        return findInvoices(startDate, endDate, COL_SELLER_ID, sellerId);
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

    private String getInsertIntoInvoiceSql(){
        return "insert into " + TABLE_INVOICE + " (" + COL_INVOICE_ID + "," + COL_INVOICE_CODE + ","
                + COL_INVOICE_DATE + "," + COL_BUYER_NAME + "," + COL_BUYER_ID + "," + COL_SELLER_NAME + ","
                + COL_SELLER_ID + "," + COL_TOTAL_AMOUNT + "," + COL_TOTAL_TAX + "," + COL_TOTAL + ","
                + COL_REMARK + ") values(?,?,?,?,?,?,?,?,?,?,?)";
    }

    private String getInsertIntoDetailSql() {
        return "insert into " + TABLE_DETAILES + " (" + "," + COL_INVOICE_ID + "," + COL_DETAIL_NAME + ","
                + COL_SPECIFICATION + "," + COL_UNIT_NAME + "," + COL_QUANTITY + "," + COL_UNIT_PRICE + ","
                + COL_AMOUNT + "," + COL_TAX_RATE + "," + COL_TAX_SUM + ") values(?,?,?,?,?,?,?,?,?)";
    }

    private Object[] getInsertIntoInvoiceParams(Invoice invoice) {
        return new Object[]{ invoice.getInvoiceId(), invoice.getInvoiceCode(),
                invoice.getInvoiceDate(), invoice.getBuyerName(), invoice.getBuyerId(), invoice.getSellerName(),
                invoice.getSellerId(), invoice.getTotalAmount(), invoice.getTotalTax(), invoice.getTotal(),
                invoice.getRemark() };
    }

    private Object[] getInsertIntoDetailParams(Invoice invoice, InvoiceDetail detail) {
        return new Object[]{ invoice.getInvoiceId(), detail.getDetailName(), detail.getSpecification(),
                detail.getUnitName(), detail.getQuantity(), detail.getUnitPrice(), detail.getAmount(),
                detail.getTaxRate(), detail.getTaxSum() };
    }

    private List<Invoice> findInvoices(String colName, String colValue) {
        List<Invoice> invoices = null;
        String sql = getSelectFromInvoiceBaseSql() + "where " + colName + "=?";
        try {
            invoices = findInvoicesBody(sql, new Object[]{colValue});

            if(!invoices.isEmpty()) {
                findAndSetDetails(invoices);
            }
        } catch (Exception e) {
            // do nothing
        }
        return invoices;
    }

    private List<Invoice> findInvoices(Date start, Date end) {
        List<Invoice> invoices = null;
        String sql = getSelectFromInvoiceBaseSql() + "where " + COL_INVOICE_DATE + " between ? and ?";
        try {
            invoices = findInvoicesBody(sql, new Object[]{start, end});

            if(!invoices.isEmpty()) {
                findAndSetDetails(invoices);
            }
        } catch (Exception e) {
            // do nothing
        }
        return invoices;
    }

    private List<Invoice> findInvoices(Date start, Date end, String colName, String colValue) {
        List<Invoice> invoices = null;
        String sql = getSelectFromInvoiceBaseSql() + "where " + colName + "=? and "
                + COL_INVOICE_DATE + "between ? and ?";
        try {
            invoices = findInvoicesBody(sql, new Object[] {colValue, start, end});

            if(!invoices.isEmpty()) {
                findAndSetDetails(invoices);
            }
        } catch (Exception e) {
            // do nothing
        }
        return invoices;
    }

    private List<Invoice> findInvoices(String colName1, String colValue1, String colName2, String colValue2) {
        List<Invoice> invoices = null;
        String sql = getSelectFromInvoiceBaseSql() + "where " + colName1 + "=? and "
                + colName2 + "=?";
        try {
            invoices = findInvoicesBody(sql, new Object[] {colValue1, colValue2});

            if(!invoices.isEmpty()) {
                findAndSetDetails(invoices);
            }
        } catch (Exception e) {
            // do nothing
        }
        return invoices;
    }

    private void findAndSetDetails(List<Invoice> invoices) throws Exception {
        String sql = getSelectFromDetailBaseSql() + "where " + COL_INVOICE_ID + "=?";
        RowMapper<InvoiceDetail> detailRowMapper = new BeanPropertyRowMapper<>(InvoiceDetail.class);
        for(Invoice invoice : invoices) {
            List<InvoiceDetail> details = jdbcTemplate.query(sql, detailRowMapper, invoice.getInvoiceId());
            invoice.setDetails(details);
        }
    }

    private List<Invoice> findInvoicesBody(String sql, Object[] params) throws Exception {
        RowMapper<Invoice> invoiceRowMapper = new BeanPropertyRowMapper<>(Invoice.class);
        return jdbcTemplate.query(sql, invoiceRowMapper, params);
    }
}
