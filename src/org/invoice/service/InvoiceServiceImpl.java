package org.invoice.service;

import org.invoice.model.Invoice;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by 李浩然 on 2017/4/12.
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {
    private Map<String, Invoice> outcomeInvoices;   // 出项发票列表
    private Map<String, Invoice> incomeInvoices;    // 进项发票列表
}
