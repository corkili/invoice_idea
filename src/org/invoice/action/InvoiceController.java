package org.invoice.action;

import org.apache.log4j.Logger;
import org.invoice.model.Invoice;
import org.invoice.model.InvoiceDetail;
import org.invoice.model.InvoiceList;
import org.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        invoiceList.clear();
        ModelAndView modelAndView = new ModelAndView("invoice_query_list");
        modelAndView.addObject("invoice_list", invoiceList);
        modelAndView.addObject("has_result", invoiceList.size() != 0);
        modelAndView.addObject("view_invoice", false);
        modelAndView.addObject("invoice", null);
        return modelAndView;
    }

    @RequestMapping(value = "/list_query", method = RequestMethod.POST)
    public ModelAndView queryInvoiceForList(
            @RequestParam("buyer_name") String buyerName,
            @RequestParam("seller_name") String sellerName,
            @RequestParam("start_time") Date startDate,
            @RequestParam("end_time") Date endDate) {
        System.out.println(buyerName + "\n" + sellerName + "\n" + startDate + "\n" + endDate);
        InvoiceList invoiceList = invoiceService.getInvoiceListByUserId(0);
        List<Invoice> invoices = invoiceService.getInvoicesByNamesAndDateRange(buyerName, sellerName, startDate, endDate);
        invoiceList.clear();
        invoiceList.addAll(invoices);
        ModelAndView modelAndView = new ModelAndView("invoice_query_list");
        System.err.println("size: " + invoiceList.size());
        modelAndView.addObject("invoice_list", invoiceList);
        modelAndView.addObject("has_result", invoiceList.size() != 0);
        modelAndView.addObject("view_invoice", false);
        modelAndView.addObject("invoice", null);
        modelAndView.addObject("index", -1);
        return modelAndView;
    }

    @RequestMapping(value = "/view_invoice", method = RequestMethod.POST)
    public ModelAndView viewInvoice(@RequestParam("index") int index, @RequestParam("invoice_id") String invoiceId) {
        InvoiceList invoiceList = invoiceService.getInvoiceListByUserId(0);
        Invoice invoice = invoiceService.getInvoice(0, index);
        if (!invoice.getInvoiceId().equals(invoiceId)) {
            invoice = invoiceService.getInvoice(invoiceId);
        }
        ModelAndView modelAndView = new ModelAndView("invoice_query_list");
        modelAndView.addObject("invoice_list", invoiceList);
        modelAndView.addObject("has_result", invoiceList.size() != 0);
        modelAndView.addObject("view_invoice", true);
        modelAndView.addObject("invoice", invoice);
        modelAndView.addObject("index", index);
        return modelAndView;
    }

    @RequestMapping(value = "/del_invoice", method = RequestMethod.POST)
    public ModelAndView delInvoice(@RequestParam("index") int index, @RequestParam("invoice_id") String invoiceId) {
        if (invoiceService.getInvoice(0, index).getInvoiceId().equals(invoiceId)) {
            invoiceService.removeInvoice(0, index);
        }
        InvoiceList invoiceList = invoiceService.getInvoiceListByUserId(0);
        ModelAndView modelAndView = new ModelAndView("invoice_query_list");
        modelAndView.addObject("invoice_list", invoiceList);
        modelAndView.addObject("has_result", invoiceList.size() != 0);
        modelAndView.addObject("view_invoice", false);
        modelAndView.addObject("invoice", null);
        modelAndView.addObject("index", -1);
        return modelAndView;
    }

    @RequestMapping(value = "add_invoice_image", method = RequestMethod.GET)
    public ModelAndView addInvoiceByImage() {
        ModelAndView modelAndView = new ModelAndView("invoice_input_image");
        modelAndView.addObject("success", false);
        return modelAndView;
    }
}
