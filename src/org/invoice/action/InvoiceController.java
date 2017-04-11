package org.invoice.action;

import org.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by 李浩然 on 2017/4/12.
 */
@Controller
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
}
