package org.invoice.model;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 李浩然 on 2017/6/10.
 */
public class InvoiceMap {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");

    private Map<String, List<Invoice>> invoiceMap;    // 日期->发票

    public InvoiceMap() {
        invoiceMap = new HashMap<>();
    }

    public InvoiceMap(Collection<Invoice> invoices) {
        invoiceMap = new HashMap<>();
        addAll(invoices);
    }

    public boolean add(Invoice invoice) {
        String date = dateFormat.format(invoice.getInvoiceDate());
        if (invoiceMap.get(date) == null) {
            invoiceMap.put(date, new ArrayList<>());
        }
        return invoiceMap.get(date).add(invoice);
    }

    public void addAll(Collection<Invoice> invoices) {
        for (Invoice invoice : invoices) {
            add(invoice);
        }
    }

    public Invoice remove(Date date, int index) {
        List<Invoice> invoices = invoiceMap.get(dateFormat.format(date));
        if (invoices == null) {
            return null;
        }
        return invoices.remove(index);
    }

    public boolean remove(Invoice invoice) {
        List<Invoice> invoices = invoiceMap.get(dateFormat.format(invoice.getInvoiceDate()));
        if (invoices == null) {
            return true;
        }
        return invoices.remove(invoice);
    }

    public List<Invoice> remove(Date date) {
        return invoiceMap.remove(dateFormat.format(date));
    }

    public Map<String, Double> getDate2AmountMap() {
        Map<String, Double> result = new HashMap<>();

        for (Map.Entry<String, List<Invoice>> entry : invoiceMap.entrySet()) {
            result.put(entry.getKey(), computeAmountOfInvoices(entry.getValue()));
        }

        return result;
    }

    private double computeAmountOfInvoices(Collection<Invoice> invoices) {
        double sum = 0.0;
        for (Invoice invoice : invoices) {
            sum += invoice.getTotal();
        }
        return sum;
    }

}
