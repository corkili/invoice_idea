package org.invoice.service;

import org.invoice.model.Invoice;
import org.invoice.model.InvoiceList;

import java.util.Date;
import java.util.List;

/**
 * Created by 李浩然 on 2017/4/12.
 */
public interface InvoiceService {
    public List<Invoice> test(Date invoiceId);

    // 增加
    public void addInvoice(Invoice invoice);
    public void addInvoices(List<Invoice> invoices);

    // 获取发票
    public Invoice getInvoice(String invoiceId, String invoiceCode);
    public Invoice getInvoice(int userId, int index);
    public Invoice getInvoiceFromImageFile(String fileName);

    public void removeInvoiceListByUserId(int userId);
    public InvoiceList getInvoiceListByUserId(int userId);

    public List<Invoice> getInvoicesByNamesAndDateRange(String buyerName, String sellerName, Date startDate, Date endDate);

    // 删除
    public void removeInvoice(int userId, int index);
    public void removeInvoice(String invoiceId, String invoiceCode);

}
