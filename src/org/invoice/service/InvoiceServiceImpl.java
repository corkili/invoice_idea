package org.invoice.service;

import org.invoice.dao.InvoiceDao;
import org.invoice.model.Invoice;
import org.invoice.model.InvoiceDetail;
import org.invoice.model.InvoiceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

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

    @Override
    public Map<String, Object> checkInvoice(Invoice invoice) {
        final double e = 0.01;
        Map<String, Object> map = new HashMap<>();
        boolean correct = true;
        List<String> errorMessages = new ArrayList<>();
        String checkNum = "[0-9]*";
        int count = 1;
        if (invoiceDao.findInvoicesByInvoiceIdAndCode(invoice.getInvoiceId(), invoice.getInvoiceCode()).size() != 0) {
            errorMessages.add("*" + count + ": 发票已经存在");
            map.put("correct", false);
            map.put("errorMessages", errorMessages);
            return map;
        }
        // 验证发票代码是否为10位
        if (invoice.getInvoiceCode().length() != 10) {
            errorMessages.add("*" + count + ": 发票代码必须为10位!");
            count++;
            correct = false;
        }
        // 验证发票代码是否只包含数字
        if (!invoice.getInvoiceCode().matches(checkNum)) {
            errorMessages.add("*" + count + ": 发票代码只能包含数字!");
            count++;
            correct = false;
        }
        // 验证发票ID是否为8位
        if (invoice.getInvoiceId().length() != 8) {
            errorMessages.add("*" + count + ": 发票号码必须为8位!");
            count++;
            correct = false;
        }
        // 验证发票ID是否只包含数字
        if (!invoice.getInvoiceId().matches(checkNum)) {
            errorMessages.add("*" + count + ": 发票号码只能包含数字!");
            count++;
            correct = false;
        }
        double totalAmount = 0.0;
        double totalTax = 0.0;
        // 验证金额和税额
        for (int i = 0; i < invoice.getDetails().size(); i++) {
            InvoiceDetail detail = invoice.getDetails().get(i);
            // 验证数量乘单价是否等于金额(误差为0.01)
            if (Math.abs(detail.getAmount() - detail.getQuantity() * detail.getUnitPrice()) > e) {
                errorMessages.add("*" + count + ": 第" + (i + 1) + "条明细的金额与（数量*单价）的值不符【精度误差为" + e + "】");
                count++;
                correct = false;
            }
            if (Math.abs(detail.getAmount() * detail.getTaxRate() - detail.getTaxSum()) > e) {
                errorMessages.add("*" + count + ": 第" + (i + 1) + "条明细的税额与（金额*税率）的值不符【精度误差为" + e + "】");
                count++;
                correct = false;
            }
            totalAmount += detail.getAmount();
            totalTax += detail.getTaxSum();
        }
        if (Math.abs(totalAmount - invoice.getTotalAmount()) > e) {
            errorMessages.add("*" + count + ": 发票的合计金额与所有明细的金额之和不符【精度误差为" + e + "】");
            count++;
            correct = false;
        }
        if (Math.abs(totalTax - invoice.getTotalTax()) > e) {
            errorMessages.add("*" + count + ": 发票的合计税额与所有明细的税额之和不符【精度误差为" + e + "】");
            count++;
            correct = false;
        }
        if (Math.abs(totalAmount + totalTax - invoice.getTotal()) > e) {
            errorMessages.add("*" + count + ": 发票的税价合计与（合计金额+合计税额）的值不符【精度误差为" + e + "】");
            count++;
            correct = false;
        }
        map.put("correct", correct);
        map.put("errorMessages", errorMessages);
        return map;
    }

    private void removeInvoice(Invoice invoice) {
        invoiceDao.deleteInvoice(invoice);
    }
}
