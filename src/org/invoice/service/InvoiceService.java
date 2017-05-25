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
    public Invoice getInvoice(String invoiceId);

    public InvoiceList getInvoiceListByUserId(int userId);

}
