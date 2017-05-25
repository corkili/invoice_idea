package org.invoice.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 李浩然 on 2017/5/25.
 */
public class InvoiceList {
    private int userId;
    private List<Invoice> invoiceList;

    public InvoiceList(int userId, List<Invoice> invoiceList) {
        this.userId = userId;
        this.invoiceList = new ArrayList<>();
        addAll(invoiceList);
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int size() {
        return invoiceList.size();
    }

    public boolean add(Invoice invoice) {
        return invoiceList.add(invoice);
    }

    public Invoice remove(int index) {
        return invoiceList.remove(index);
    }

    public boolean remove(Invoice invoice) {
        return invoiceList.remove(invoice);
    }

    public boolean addAll(Collection<Invoice> invoices) {
        return invoiceList.addAll(invoices);
    }

    public boolean removeAll(Collection<Invoice> invoices) {
        return invoiceList.removeAll(invoices);
    }

    public void clear() {
        invoiceList.clear();
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public Invoice get(int index) {
        return invoiceList.get(index);
    }
}
