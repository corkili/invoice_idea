package org.invoice.model;

import javax.naming.Name;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 李浩然 on 2017/6/10.
 */
public class InvoiceMaps {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM");

    private Map<String, List<Invoice>> incomeInvoiceMap;    // 日期->发票
    private Map<String, List<Invoice>> outcomeInvoiceMap;    // 日期->发票

    private Map<String, List<InvoiceDetail>> incomeDetailMap;   // 日期->明细
    private Map<String, List<InvoiceDetail>> outcomeDetailMap;  // 日期->明细

    private Map<String, Boolean> incomeNameMap;     // 产品名
    private Map<String, Boolean> outcomeNameMap;    // 产品名


    public InvoiceMaps() {
        incomeInvoiceMap = new HashMap<>();
        outcomeInvoiceMap = new HashMap<>();
        incomeDetailMap = new HashMap<>();
        outcomeDetailMap = new HashMap<>();
        incomeNameMap = new HashMap<>();
        outcomeNameMap = new HashMap<>();
    }

    public InvoiceMaps(Collection<Invoice> incomeInvoices, Collection<Invoice> outcomeInvoices) {
        this();
        addAllIncome(incomeInvoices);
        addAllOutcome(outcomeInvoices);
    }

    public boolean addIncome(Invoice invoice) {
        return add(invoice, incomeInvoiceMap, incomeDetailMap, incomeNameMap);
    }

    public void addAllIncome(Collection<Invoice> invoices) {
        for (Invoice invoice : invoices) {
            addIncome(invoice);
        }
    }

    private Invoice removeIncome(Date date, int index) {
        List<Invoice> invoices = incomeInvoiceMap.get(dateFormat.format(date));
        if (invoices == null) {
            return null;
        }
        return invoices.remove(index);
    }

    private boolean removeIncome(Invoice invoice) {
        List<Invoice> invoices = incomeInvoiceMap.get(dateFormat.format(invoice.getInvoiceDate()));
        if (invoices == null) {
            return true;
        }
        return invoices.remove(invoice);
    }

    public boolean addOutcome(Invoice invoice) {
        return add(invoice, outcomeInvoiceMap, outcomeDetailMap, outcomeNameMap);
    }

    public void addAllOutcome(Collection<Invoice> invoices) {
        for (Invoice invoice : invoices) {
            addOutcome(invoice);
        }
    }

    private Invoice removeOutcome(Date date, int index) {
        List<Invoice> invoices = outcomeInvoiceMap.get(dateFormat.format(date));
        if (invoices == null) {
            return null;
        }
        return invoices.remove(index);
    }

    private boolean removeOutcome(Invoice invoice) {
        List<Invoice> invoices = outcomeInvoiceMap.get(dateFormat.format(invoice.getInvoiceDate()));
        if (invoices == null) {
            return true;
        }
        return invoices.remove(invoice);
    }

    private List<Invoice> remove(Date date) {
        List<Invoice> result = outcomeInvoiceMap.remove(dateFormat.format(date));
        result.addAll(incomeInvoiceMap.remove(dateFormat.format(date)));
        return result;
    }

    public List<TotalCome> getTotalComes() {
        List<TotalCome> totalComes = new ArrayList<>();

        for (Map.Entry<String, List<Invoice>> entry : incomeInvoiceMap.entrySet()) {
            totalComes.add(new TotalCome(entry.getKey(),
                    computeAmountOfInvoices(entry.getValue()),
                    computeAmountOfInvoices(outcomeInvoiceMap.get(entry.getKey()))));
        }
        Collections.sort(totalComes);
        return totalComes;
    }

    public List<List<ProductCome>> getProductComes() {
        List<ProductCome> incomeProductComes = new ArrayList<>();
        List<ProductCome> outcomeProductComes = new ArrayList<>();
        Map<String, Double> map = new HashMap<>();
        for (Map.Entry<String, List<InvoiceDetail>> entry : incomeDetailMap.entrySet()) {
            List<String> names = new ArrayList<>();
            List<Double> amounts = new ArrayList<>();
            for (String d : incomeNameMap.keySet()) {
                map.put(d, 0.0);
            }
            for (InvoiceDetail detail : entry.getValue()) {
                map.put(detail.getDetailName(), map.get(detail.getDetailName()) + detail.getTaxSum() + detail.getAmount());
            }
            for (Map.Entry<String, Double> i : map.entrySet()) {
                names.add(i.getKey());
                amounts.add(i.getValue());
            }
            incomeProductComes.add(new ProductCome(entry.getKey(), names, amounts));
        }
        Collections.sort(incomeProductComes);
        map.clear();
        for (Iterator<Map.Entry<String, List<InvoiceDetail>>> iterator = outcomeDetailMap.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, List<InvoiceDetail>> entry = iterator.next();
            List<String> names = new ArrayList<>();
            List<Double> amounts = new ArrayList<>();
            for (String d : outcomeNameMap.keySet()) {
                map.put(d, 0.0);
            }
            for (InvoiceDetail detail : entry.getValue()) {
                map.put(detail.getDetailName(), map.get(detail.getDetailName()) + detail.getTaxSum() + detail.getAmount());
            }
            for (Map.Entry<String, Double> i : map.entrySet()) {
                names.add(i.getKey());
                amounts.add(i.getValue());
            }
            outcomeProductComes.add(new ProductCome(entry.getKey(), names, amounts));
        }
        Collections.sort(outcomeProductComes);
        List<List<ProductCome>> result = new ArrayList<>();
        result.add(incomeProductComes);
        result.add(outcomeProductComes);
        return result;
    }

    private double computeAmountOfInvoices(Collection<Invoice> invoices) {
        double sum = 0.0;
        for (Invoice invoice : invoices) {
            sum += invoice.getTotal();
        }
        return sum;
    }

    private boolean add(Invoice invoice, Map<String, List<Invoice>> invoiceMap,
                        Map<String, List<InvoiceDetail>> detailMap, Map<String, Boolean> nameMap) {
        String date = dateFormat.format(invoice.getInvoiceDate());
        if (!outcomeInvoiceMap.containsKey(date)) {
            outcomeInvoiceMap.put(date, new ArrayList<>());
        }
        if (!outcomeDetailMap.containsKey(date)) {
            outcomeDetailMap.put(date, new ArrayList<>());
        }
        if (!incomeInvoiceMap.containsKey(date)) {
            incomeInvoiceMap.put(date, new ArrayList<>());
        }
        if (!incomeDetailMap.containsKey(date)) {
            incomeDetailMap.put(date, new ArrayList<>());
        }

        for (InvoiceDetail detail : invoice.getDetails()) {
            detailMap.get(date).add(detail);
            nameMap.put(detail.getDetailName(), true);
        }

        return invoiceMap.get(date).add(invoice);
    }
}
