package org.invoice.service;

import org.invoice.model.Invoice;

import java.util.Date;
import java.util.List;

/**
 * Created by 李浩然 on 2017/4/12.
 */
public interface InvoiceService {
    public List<Invoice> test(Date invoiceId);
}
