package org.invoice.action;

import org.invoice.model.Invoice;
import org.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 李浩然 on 2017/4/12.
 */
@Controller
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value = "/test")
    public ModelAndView test() {
        List<Invoice> invoice = invoiceService.test("44264749");
        ModelAndView modelAndView = new ModelAndView("test");
        modelAndView.addObject("invoices", invoice);
        return modelAndView;
    }
}
