package org.invoice.action;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.log4j.Logger;
import org.invoice.model.Invoice;
import org.invoice.model.InvoiceDetail;
import org.invoice.model.InvoiceList;
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
import java.util.Date;
import java.util.List;

/**
 * Created by 李浩然 on 2017/4/12.
 */
@Controller
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    private Logger logger = Logger.getLogger(InvoiceController.class);

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
    public ModelAndView addInvoiceByHandInput() {
        ModelAndView modelAndView = new ModelAndView("invoice_input_hand");
        modelAndView.addObject("invoice", new Invoice());
        int detailNum = 0;
        modelAndView.addObject("detail_num", detailNum);
        return modelAndView;
    }

    @RequestMapping(value = "/test_invoice_hand", method = RequestMethod.GET)
    public ModelAndView testInvoiceByHandInput(@RequestParam("detail_num") int detailNum) {
        ModelAndView modelAndView = new ModelAndView("addInvoiceHandForm");
        modelAndView.addObject("invoice", new Invoice());
        modelAndView.addObject("detail_num", detailNum);
        return modelAndView;
    }

    @RequestMapping(value = "/add_invoice_hand", method = RequestMethod.POST)
    public ModelAndView addInvoiceByHandInput(@RequestParam("detail_num") int detailNum) {
        ModelAndView modelAndView = new ModelAndView("invoice_input_hand");
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
        return new ModelAndView("redirect:/save_result?id=" + invoice.getInvoiceId());
    }

    @RequestMapping(value = "/save_result")
    public ModelAndView invoiceSaveResult(@RequestParam("id") String id) {
        Invoice invoice = invoiceService.getInvoice(id);
        ModelAndView modelAndView = new ModelAndView("invoice_save_result");
        modelAndView.addObject("invoice", invoice);
        modelAndView.addObject("save_date", dateFormat.format(new Date()));
        return modelAndView;
    }

    @RequestMapping(value = "/list_query", method = RequestMethod.GET)
    public ModelAndView queryInvoiceForList() {
        InvoiceList invoiceList = invoiceService.getInvoiceListByUserId(0);
        ModelAndView modelAndView = new ModelAndView("invoice_list");
        modelAndView.addObject("invoice_list", invoiceList);
        modelAndView.addObject("has_result", invoiceList.size() != 0);
        return modelAndView;
    }
}
