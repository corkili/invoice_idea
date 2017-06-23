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

    private Map<Integer, InvoiceList> invoiceLists; // userId->invoiceList

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
    public Invoice getInvoice(String invoiceId, String invoiceCode) {
        List<Invoice> results = invoiceDao.findInvoicesByInvoiceIdAndCode(invoiceId, invoiceCode);
        Invoice invoice = null;
        if(results != null && results.size() > 0) {
            invoice = results.get(0);
        }
        return invoice;
    }

    @Override
    public Invoice getInvoice(int userId, int index) {
        return getInvoiceListByUserId(userId).get(index);
    }

    @Override
    public Invoice getInvoiceFromImageFile(String fileName) {
        Invoice invoice = new Invoice();

        return null;
    }

    @Override
    public void removeInvoiceListByUserId(int userId) {
        invoiceLists.remove(userId);
    }

    @Override
    public InvoiceList getInvoiceListByUserId(int userId) {
        return invoiceLists.computeIfAbsent(userId, i -> new InvoiceList(i, new ArrayList<>()));
    }

    @Override
    public List<Invoice> getInvoicesByNamesAndDateRange(String buyerName, String sellerName, Date startDate, Date endDate) {
        if (buyerName == null || "".equals(buyerName)) {
            return invoiceDao.findInvoicesByDateRangeAndSellerName(startDate, endDate, sellerName);
        } else if (sellerName == null || "".equals(sellerName)) {
            return invoiceDao.findInvoicesByDateRangeAndBuyerName(startDate, endDate, buyerName);
        } else {
            return invoiceDao.findInvoicesByDateRangeAndNames(buyerName, sellerName, startDate, endDate);
        }
    }

    @Override
    public void removeInvoice(int userId, int index) {
        removeInvoice(getInvoiceListByUserId(userId).remove(index));
    }

    @Override
    public void removeInvoice(String invoiceId, String invoiceCode) {
        removeInvoice(getInvoice(invoiceId, invoiceCode));
    }

    private void removeInvoice(Invoice invoice) {
        invoiceDao.deleteInvoice(invoice);
    }
}
