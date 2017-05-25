package org.invoice.service;

import org.invoice.dao.InvoiceDao;
import org.invoice.model.Invoice;
import org.invoice.model.InvoiceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 李浩然 on 2017/4/12.
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceDao invoiceDao;

    private Map<String, Invoice> outputInvoices;   // 出项发票列表
    private Map<String, Invoice> incomeInvoices;    // 进项发票列表

    private Map<Integer, InvoiceList> invoiceLists;

    public InvoiceServiceImpl() {
        invoiceLists = new HashMap<>();
    }

    @Override
    public List<Invoice> test(Date invoiceId) {
        return invoiceDao.findInvoicesByDate(invoiceId);
    }

    @Override
    public void addInvoice(Invoice invoice) {
        invoiceDao.addInvoice(invoice);
    }

    @Override
    public void addInvoices(List<Invoice> invoices) {
        invoiceDao.addInvoice(invoices);
    }

    @Override
    public Invoice getInvoice(String invoiceId) {
        List<Invoice> results = invoiceDao.findInvoicesByInvoiceId(invoiceId);
        Invoice invoice = null;
        if(results != null && results.size() > 0) {
            invoice = results.get(0);
        }
        return invoice;
    }

    @Override
    public InvoiceList getInvoiceListByUserId(int userId) {
        return invoiceLists.computeIfAbsent(userId, i -> new InvoiceList(i, new ArrayList<>()));
    }
}
