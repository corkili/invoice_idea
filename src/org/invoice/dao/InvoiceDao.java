package org.invoice.dao;

import org.invoice.model.Invoice;

import java.util.Date;
import java.util.List;

/**
 * Created by 李浩然 on 2017/4/12.
 */
public interface InvoiceDao {
    public static final String TABLE_INVOICE = "table_invoice";
    public static final String TABLE_DETAILES = "table_details";

    public static final String COL_INVOICE_ID = "invoice_id";
    public static final String COL_INVOICE_CODE = "invoice_code";
    public static final String COL_INVOICE_DATE = "invoice_date";
    public static final String COL_BUYER_NAME = "buyer_name";
    public static final String COL_BUYER_ID = "buyer_id";
    public static final String COL_SELLER_NAME = "seller_name";
    public static final String COL_SELLER_ID = "seller_id";
    public static final String COL_TOTAL_AMOUNT = "total_amount";
    public static final String COL_TOTAL_TAX = "total_tax";
    public static final String COL_TOTAL = "total";
    public static final String COL_REMARK = "remark";

    public static final String COL_DETAIL_NAME = "detail_name";
    public static final String COL_SPECIFICATION = "specification";
    public static final String COL_UNIT_NAME = "unit_name";
    public static final String COL_QUANTITY = "quantity";
    public static final String COL_UNIT_PRICE = "unit_price";
    public static final String COL_AMOUNT = "amount";
    public static final String COL_TAX_RATE = "tax_rate";
    public static final String COL_TAX_SUM = "tax_sum";

    public void addInvoice(Invoice invoice);

    public List<Invoice> findInvoicesByInvoiceId(final String invoiceId);
    public List<Invoice> findInvoicesByInvoiceCode(final String invoiceCode);
    public List<Invoice> findInvoicesByDate(final Date date);
    public List<Invoice> findInvoicesByDate(final Date startDate, final Date endDate);
    public List<Invoice> findInvoicesByBuyerName(final String buyerName);
    public List<Invoice> findInvoicesByBuyerId(final String buyerId);
    public List<Invoice> findInvoicesBySellerName(final String sellerName);
    public List<Invoice> findInvoicesBySellerId(final String sellerId);
    public List<Invoice> findInvoicesByTotalAmount(final String totalAmount);
    public List<Invoice> findInvoicesByTotalTax(final String totalTax);
}
