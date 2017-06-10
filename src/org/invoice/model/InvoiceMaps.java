package org.invoice.model;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 李浩然 on 2017/6/10.
 */
public class InvoiceMaps {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM");

    private Map<String, List<Invoice>> incomeInvoiceMap;    // 日期->发票
    private Map<String, List<Invoice>> outcomeInvoiceMap;    // 日期->发票

    public InvoiceMaps() {
        incomeInvoiceMap = new HashMap<>();
        outcomeInvoiceMap = new HashMap<>();
    }

    public InvoiceMaps(Collection<Invoice> incomeInvoices, Collection<Invoice> outcomeInvoices) {
        this();
        addAllIncome(incomeInvoices);
        addAllOutcome(outcomeInvoices);
    }

    public boolean addIncome(Invoice invoice) {
        String date = dateFormat.format(invoice.getInvoiceDate());
        if (incomeInvoiceMap.get(date) == null) {
            incomeInvoiceMap.put(date, new ArrayList<>());
        }
        if (outcomeInvoiceMap.get(date) == null) {
            outcomeInvoiceMap.put(date, new ArrayList<>());
        }
        return incomeInvoiceMap.get(date).add(invoice);
    }

    public void addAllIncome(Collection<Invoice> invoices) {
        for (Invoice invoice : invoices) {
            addIncome(invoice);
        }
    }

    public Invoice removeIncome(Date date, int index) {
        List<Invoice> invoices = incomeInvoiceMap.get(dateFormat.format(date));
        if (invoices == null) {
            return null;
        }
        return invoices.remove(index);
    }

    public boolean removeIncome(Invoice invoice) {
        List<Invoice> invoices = incomeInvoiceMap.get(dateFormat.format(invoice.getInvoiceDate()));
        if (invoices == null) {
            return true;
        }
        return invoices.remove(invoice);
    }

    public boolean addOutcome(Invoice invoice) {
        String date = dateFormat.format(invoice.getInvoiceDate());
        if (outcomeInvoiceMap.get(date) == null) {
            outcomeInvoiceMap.put(date, new ArrayList<>());
        }
        if (incomeInvoiceMap.get(date) == null) {
            incomeInvoiceMap.put(date, new ArrayList<>());
        }
        return outcomeInvoiceMap.get(date).add(invoice);
    }

    public void addAllOutcome(Collection<Invoice> invoices) {
        for (Invoice invoice : invoices) {
            addOutcome(invoice);
        }
    }

    public Invoice removeOutcome(Date date, int index) {
        List<Invoice> invoices = outcomeInvoiceMap.get(dateFormat.format(date));
        if (invoices == null) {
            return null;
        }
        return invoices.remove(index);
    }

    public boolean removeOutcome(Invoice invoice) {
        List<Invoice> invoices = outcomeInvoiceMap.get(dateFormat.format(invoice.getInvoiceDate()));
        if (invoices == null) {
            return true;
        }
        return invoices.remove(invoice);
    }

    public List<Invoice> remove(Date date) {
        List<Invoice> result = outcomeInvoiceMap.remove(dateFormat.format(date));
        result.addAll(incomeInvoiceMap.remove(dateFormat.format(date)));
        return result;
    }

    public List<Come> getComeMap() {
        List<Come> comes = new ArrayList<>();

        for (Map.Entry<String, List<Invoice>> entry : incomeInvoiceMap.entrySet()) {
            comes.add(new Come(entry.getKey(),
                    computeAmountOfInvoices(entry.getValue()),
                    computeAmountOfInvoices(outcomeInvoiceMap.get(entry.getKey()))));
        }
        Collections.sort(comes);
        return comes;
    }

    private double computeAmountOfInvoices(Collection<Invoice> invoices) {
        double sum = 0.0;
        for (Invoice invoice : invoices) {
            sum += invoice.getTotal();
        }
        return sum;
    }
}
