package org.invoice.action;

import org.apache.log4j.Logger;
import org.invoice.model.Invoice;
import org.invoice.model.InvoiceDetail;
import org.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 李浩然 on 2017/4/12.
 */
@Controller
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    private Logger logger = Logger.getLogger(InvoiceController.class);

    @RequestMapping(value = "/test")
    public ModelAndView test(@RequestParam("condition") String condition) {
        List<Invoice> invoice = null;
        try {
            invoice = invoiceService.test(new SimpleDateFormat("yyyy-MM-dd").parse(condition));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView("test");
        modelAndView.addObject("invoices", invoice);
        return modelAndView;
    }

    @RequestMapping(value = "/add_invoice_hand", method = RequestMethod.GET)
    public ModelAndView addInvoiceByHandInput(@RequestParam("detail_num") int detailNum) {
        ModelAndView modelAndView = new ModelAndView("addInvoiceHandForm");
        modelAndView.addObject("invoice", new Invoice());
        modelAndView.addObject("detail_num", detailNum);
        return modelAndView;
    }

    @RequestMapping(value = "/save_invoice", method = RequestMethod.POST)
    public ModelAndView saveInvoice(@ModelAttribute Invoice invoice) {
        logger.info("save invoice");
        for(InvoiceDetail detail : invoice.getDetails())
            detail.setInvoiceId(invoice.getInvoiceId());
        invoiceService.addInvoice(invoice);
        return new ModelAndView("redirect:/test?condition=" + new SimpleDateFormat("yyyy-MM-dd").format(invoice.getInvoiceDate()));
    }
}
