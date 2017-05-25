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
import java.util.*;

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
            batchArgs.add(getInsertIntoDetailParams(detail));
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
    public void deleteInvoice(Invoice invoice) {
        String invoiceSql = getDeleteFromInvoiceSql();
        String detailSql = getDeleteFromDetailSql();
        logger.info("sql: " + invoiceSql);
        jdbcTemplate.update(invoiceSql, invoice.getInvoiceId());
        logger.info("sql: " + detailSql);
        jdbcTemplate.update(detailSql, invoice.getInvoiceId());
    }

    @Override
    public void deleteInvoice(List<Invoice> invoices) {
        for(Invoice invoice : invoices) {
            deleteInvoice(invoice);
        }
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        String invoiceSql =getUpdateInvoiceSql();
        logger.info("sql: " + invoiceSql);
        jdbcTemplate.update(invoiceSql, getUpdateInvoiceParams(invoice));
        updateDetailsOfInvoice(invoice);
    }

    @Override
    public void updateInvoice(List<Invoice> invoices) {
        for(Invoice invoice : invoices)
            updateInvoice(invoice);
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

    @Override
    public List<Invoice> findInvoicesByDateRangeAndNames(String buyerName, String sellerName, Date startDate, Date endDate) {
        return findInvoices(startDate, endDate, COL_BUYER_NAME, buyerName, COL_SELLER_NAME, sellerName);
    }

    private String getSelectFromInvoiceBaseSql() {
        return "select " + COL_INVOICE_ID + "," + COL_INVOICE_CODE + "," + COL_INVOICE_DATE + ","
                + COL_BUYER_NAME + "," + COL_BUYER_ID + "," + COL_SELLER_NAME + "," + COL_SELLER_ID + ","
                + COL_TOTAL_AMOUNT + "," + COL_TOTAL_TAX + "," + COL_TOTAL + "," + COL_REMARK
                + " from " + TABLE_INVOICE + " ";
    }

    private String getSelectFromDetailBaseSql() {
        return "select " + COL_DETAIL_ID + "," + COL_INVOICE_ID + "," + COL_DETAIL_NAME + "," + COL_SPECIFICATION + ","
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
        return "insert into " + TABLE_DETAILES + " (" + COL_INVOICE_ID + "," + COL_DETAIL_NAME + ","
                + COL_SPECIFICATION + "," + COL_UNIT_NAME + "," + COL_QUANTITY + "," + COL_UNIT_PRICE + ","
                + COL_AMOUNT + "," + COL_TAX_RATE + "," + COL_TAX_SUM + ") values(?,?,?,?,?,?,?,?,?)";
    }

    private String getDeleteFromInvoiceSql() {
        return "delete from " + TABLE_INVOICE + " where " + COL_INVOICE_ID + "=?";
    }

    private String getDeleteFromDetailSql() {
        return "delete from " + TABLE_DETAILES + " where " + COL_INVOICE_ID + "=?";
    }

    private String getUpdateInvoiceSql() {
        return "update " + TABLE_INVOICE + " set " + COL_BUYER_NAME + "=?," + COL_BUYER_ID + "=?,"
                + COL_SELLER_NAME + "=?," + COL_SELLER_ID + "=?," + COL_TOTAL_AMOUNT + "=?," + COL_TOTAL_TAX + "=?,"
                + COL_TOTAL + "=?," + COL_REMARK + "=? where " + COL_INVOICE_ID + "=?";
    }

    private String getUpdateDetailSql() {
        return "update " + TABLE_DETAILES + " set " + COL_DETAIL_NAME + "=?," + COL_SPECIFICATION + "=?,"
                + COL_UNIT_NAME + "=?," + COL_QUANTITY + "=?," + COL_UNIT_PRICE + "=?," + COL_AMOUNT + "=?,"
                + COL_TAX_RATE + "=?," + COL_TAX_SUM + "=? where " + COL_INVOICE_ID + "=? and " + COL_DETAIL_ID + "=?";
    }

    private Object[] getInsertIntoInvoiceParams(Invoice invoice) {
        return new Object[]{ invoice.getInvoiceId(), invoice.getInvoiceCode(),
                invoice.getInvoiceDate(), invoice.getBuyerName(), invoice.getBuyerId(), invoice.getSellerName(),
                invoice.getSellerId(), invoice.getTotalAmount(), invoice.getTotalTax(), invoice.getTotal(),
                invoice.getRemark() };
    }

    private Object[] getInsertIntoDetailParams(InvoiceDetail detail) {
        return new Object[]{ detail.getInvoiceId(), detail.getDetailName(), detail.getSpecification(),
                detail.getUnitName(), detail.getQuantity(), detail.getUnitPrice(), detail.getAmount(),
                detail.getTaxRate(), detail.getTaxSum() };
    }

    private Object[] getUpdateInvoiceParams(Invoice invoice) {
        return new Object[]{ invoice.getBuyerName(), invoice.getBuyerId(), invoice.getSellerName(),
                invoice.getSellerId(), invoice.getTotalAmount(), invoice.getTotalTax(), invoice.getTotal(),
                invoice.getRemark(), invoice.getInvoiceId() };
    }

    private Object[] getUpdateDetailParams(InvoiceDetail detail) {
        return new Object[]{ detail.getDetailName(), detail.getSpecification(), detail.getUnitName(),
                detail.getQuantity(), detail.getUnitPrice(), detail.getAmount(),
                detail.getTaxRate(), detail.getTaxSum(), detail.getInvoiceId(), detail.getDetailId() };
    }

    private List<Invoice> findInvoices(String colName, String colValue) {
        List<Invoice> invoices = null;
        String sql = getSelectFromInvoiceBaseSql() + "where " + colName + "=?";
        logger.info("sql: " + sql);
        try {
            invoices = findInvoicesBody(sql, new Object[]{colValue});

            if(!invoices.isEmpty()) {
                findAndSetDetails(invoices);
            }
        } catch (Exception e) {
            // do nothing
        }
        if(invoices == null) {
            invoices = new ArrayList<>();
        }
        return invoices;
    }

    private List<Invoice> findInvoices(Date start, Date end) {
        List<Invoice> invoices = null;
        String sql = getSelectFromInvoiceBaseSql() + "where " + COL_INVOICE_DATE + " between ? and ?";
        logger.info("sql: " + sql);
        try {
            invoices = findInvoicesBody(sql, new Object[]{dateFormat.format(start), dateFormat.format(end)});

            if(!invoices.isEmpty()) {
                findAndSetDetails(invoices);
            }
        } catch (Exception e) {
            // do nothing
        }
        if(invoices == null) {
            invoices = new ArrayList<>();
        }
        return invoices;
    }

    private List<Invoice> findInvoices(Date start, Date end, String colName, String colValue) {
        List<Invoice> invoices = null;
        String sql = getSelectFromInvoiceBaseSql() + "where " + colName + "=? and "
                + COL_INVOICE_DATE + " between ? and ?";
        logger.info("sql: " + sql);
        try {
            invoices = findInvoicesBody(sql, new Object[] {colValue, dateFormat.format(start), dateFormat.format(end)});

            if(!invoices.isEmpty()) {
                findAndSetDetails(invoices);
            }
        } catch (Exception e) {
            // do nothing
        }
        if(invoices == null) {
            invoices = new ArrayList<>();
        }
        return invoices;
    }

    private List<Invoice> findInvoices(Date start, Date end, String colName1, String colValue1,
                                       String colName2, String colValue2) {
        List<Invoice> invoices = null;
        String sql = getSelectFromInvoiceBaseSql() + "where " + colName1 + "=? and "
                + colName2 + "=? and " + COL_INVOICE_DATE + " between ? and ?";
        logger.info("sql: " + sql);
        try {
            invoices = findInvoicesBody(sql, new Object[] { colValue1, colValue2, dateFormat.format(start), dateFormat.format(end) });

            if(!invoices.isEmpty()) {
                findAndSetDetails(invoices);
            }
        } catch (Exception e) {
            // do nothing
        }
        if(invoices == null) {
            invoices = new ArrayList<>();
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
        if(invoices == null) {
            invoices = new ArrayList<>();
        }
        return invoices;
    }

    private void findAndSetDetails(List<Invoice> invoices) throws Exception {
        String sql = getSelectFromDetailBaseSql() + "where " + COL_INVOICE_ID + "=?";
        RowMapper<InvoiceDetail> detailRowMapper = new BeanPropertyRowMapper<>(InvoiceDetail.class);
        for(Invoice invoice : invoices) {
            List<InvoiceDetail> details = jdbcTemplate.query(sql, detailRowMapper, invoice.getInvoiceId());
            if (details == null) {
                details = new ArrayList<>();
            }
            invoice.setDetails(details);
        }
    }

    private List<Invoice> findInvoicesBody(String sql, Object[] params) throws Exception {
        RowMapper<Invoice> invoiceRowMapper = new BeanPropertyRowMapper<>(Invoice.class);
        return jdbcTemplate.query(sql, invoiceRowMapper, params);
    }

    private List<InvoiceDetail> findDetails(Invoice invoice) {
        String sql = getSelectFromDetailBaseSql() + "where " + COL_INVOICE_ID + "=?";
        RowMapper<InvoiceDetail> detailRowMapper = new BeanPropertyRowMapper<>(InvoiceDetail.class);
        List<InvoiceDetail> details = null;
        try {
            details = jdbcTemplate.query(sql, detailRowMapper, invoice.getInvoiceId());
        } catch (Exception e) {
            details = new ArrayList<>();
        }
        if (details == null) {
            details = new ArrayList<>();
        }
        return details;
    }

    private Map<Long, InvoiceDetail> getDetailsMapWithIdKeyByList(List<InvoiceDetail> details) {
        Map<Long, InvoiceDetail> detailsMap = new HashMap<>();
        for(InvoiceDetail detail : details) {
            detailsMap.put(detail.getDetailId(), detail);
        }
        return detailsMap;
    }

    private void updateDetailsOfInvoice(Invoice invoice) {
        List<InvoiceDetail> oldDetails = findDetails(invoice);
        int oldCount = oldDetails.size();
        int newCount = invoice.getDetails().size();

        Map<Long, InvoiceDetail> oldDetailsMap = getDetailsMapWithIdKeyByList(oldDetails);
        Map<Long, InvoiceDetail> newDetailsMap = getDetailsMapWithIdKeyByList(invoice.getDetails());

        List<InvoiceDetail> updateDetails = new ArrayList<>();
        List<InvoiceDetail> addDetails = new ArrayList<>();
        List<InvoiceDetail> deleteDetails = new ArrayList<>();

        for(InvoiceDetail detail : invoice.getDetails()) {
            if(detail.getDetailId() == InvoiceDetail.EMPTY_DETAIL_ID)
                addDetails.add(detail);
            else
                updateDetails.add(detail);
        }
        for(InvoiceDetail detail : oldDetails) {
            if(newDetailsMap.get(detail.getDetailId()) == null) {
                deleteDetails.add(detail);
            }
        }

        addDetails(addDetails);
        deleteDetails(deleteDetails);
        updateDetails(updateDetails);
    }

    private void addDetails(List<InvoiceDetail> details) {
        String sql = getInsertIntoDetailSql();
        List<Object[]> batchArgs = new ArrayList<>();
        for(InvoiceDetail detail : details) {
            batchArgs.add(getInsertIntoDetailParams(detail));
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    private void deleteDetails(List<InvoiceDetail> details) {
        String sql = "delete from " + TABLE_DETAILES + " where " + COL_DETAIL_ID + "=?";
        List<Object[]> batchArgs = new ArrayList<>();
        for(InvoiceDetail detail : details) {
            batchArgs.add(new Object[]{ detail.getDetailId() });
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    private void updateDetails(List<InvoiceDetail> details) {
        String sql = getUpdateDetailSql();
        List<Object[]> batchArgs = new ArrayList<>();
        for(InvoiceDetail detail : details) {
            batchArgs.add(getUpdateDetailParams(detail));
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }
}
