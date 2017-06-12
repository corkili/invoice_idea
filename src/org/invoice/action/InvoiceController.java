package org.invoice.action;

import org.apache.log4j.Logger;
import org.invoice.model.*;
import org.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        logger.info("detail_num: " +  detailNum);
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
        List<Invoice> IncomeInvoices = invoiceService.getInvoicesByNamesAndDateRange(buyerName, sellerName, startDate, endDate);
        List<Invoice> OutputInvoices = invoiceService.getInvoicesByNamesAndDateRange(sellerName, buyerName, startDate, endDate);
        invoiceList.clear();
        invoiceList.addAll(IncomeInvoices);
        invoiceList.addAll(OutputInvoices);
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
        modelAndView.addObject("has_file", false);
        modelAndView.addObject("has_error", false);
        modelAndView.addObject("invoice", null);
        return modelAndView;
    }

    @RequestMapping(value = "add_invoice_image", method = RequestMethod.POST)
    public ModelAndView addInvoiceImage(HttpServletRequest request,
                                        @RequestParam(value = "invoice_image", required = false) MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView("invoice_input_image");

        String path = request.getSession().getServletContext().getRealPath("invoiceImage");
        String fileName = file.getOriginalFilename();
        logger.info(path + "\\" + fileName);
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        // save
        try {
            file.transferTo(targetFile);
            Invoice invoice = new Invoice();
            List<InvoiceDetail> details = new ArrayList<>();
            // id
            // code
            // quantity
            // unitPrice
            // tax
            // many set
            modelAndView.addObject("invoice", invoice);
            modelAndView.addObject("has_file", true);
            modelAndView.addObject("has_error", false);
        } catch (Exception e) {
            logger.info("save failed");
            modelAndView.addObject("has_file", false);
            modelAndView.addObject("has_error", true);
        }
        return modelAndView;
    }

    @RequestMapping(value = "chart_query", method = RequestMethod.GET)
    public ModelAndView queryInvoiceForChart() {
        InvoiceList invoiceList = invoiceService.getInvoiceListByUserId(0);
        invoiceList.clear();
        ModelAndView modelAndView = new ModelAndView("invoice_query_chart");

        // 模拟数据
        List<String> dates = new ArrayList<>();
        List<Double> incomes = new ArrayList<>();
        List<Double> outcomes = new ArrayList<>();
        Random random = new Random();
        int min  = 200;
        int max = 2000;
        for (int i = 1; i < 13; i++) {
            dates.add(String.valueOf(i));
            incomes.add((double)random.nextInt(max) % (max - min + 1) + min);
            outcomes.add((double)random.nextInt(max) % (max - min + 1) + min);
        }

        modelAndView.addObject("dates", dates);
        modelAndView.addObject("incomes", incomes);
        modelAndView.addObject("outcomes", outcomes);
        modelAndView.addObject("has_result", false);
        return modelAndView;
    }

    @RequestMapping(value = "chart_query", method = RequestMethod.POST)
    public ModelAndView queryInvoiceForChart(
            @RequestParam("buyer_name") String buyerName,
            @RequestParam("seller_name") String sellerName,
            @RequestParam("start_time") Date startDate,
            @RequestParam("end_time") Date endDate) {
        logger.info(buyerName + "\n" + sellerName + "\n" + startDate + "\n" + endDate);
        InvoiceList invoiceList = invoiceService.getInvoiceListByUserId(0);
        List<Invoice> incomeInvoices = invoiceService.getInvoicesByNamesAndDateRange(buyerName, sellerName, startDate, endDate);
        List<Invoice> outcomeInvoices = invoiceService.getInvoicesByNamesAndDateRange(sellerName, buyerName, startDate, endDate);
        logger.info("incomeInvoices.size: " + incomeInvoices.size());
        logger.info("outcomeInvoices.size: " + outcomeInvoices.size());
        invoiceList.clear();
        invoiceList.addAll(incomeInvoices);
        invoiceList.addAll(outcomeInvoices);
        InvoiceMaps invoiceMaps = new InvoiceMaps(incomeInvoices, outcomeInvoices);
        List<TotalCome> comeList = invoiceMaps.getTotalComes();
        List<String> dates = new ArrayList<>();
        List<Double> incomes = new ArrayList<>();
        List<Double> outcomes = new ArrayList<>();
        if (invoiceList.size() != 0) {
            for (TotalCome come : comeList) {
                dates.add(come.getDate());
                incomes.add(new BigDecimal(come.getIncomes()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                outcomes.add(new BigDecimal(come.getOutcomes()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                logger.info(come.getDate());
                logger.info(come.getIncomes());
                logger.info(come.getOutcomes());
            }
        }
        ModelAndView modelAndView = new ModelAndView("invoice_query_chart");
        logger.info("size: " + invoiceList.size());
        modelAndView.addObject("dates", dates);
        modelAndView.addObject("incomes", incomes);
        modelAndView.addObject("outcomes", outcomes);
        modelAndView.addObject("has_result", invoiceList.size() != 0);
        return modelAndView;
    }

    @RequestMapping(value = "report", method = RequestMethod.GET)
    public ModelAndView queryReportForm() {
        InvoiceList invoiceList = invoiceService.getInvoiceListByUserId(0);
        invoiceList.clear();
        ModelAndView modelAndView = new ModelAndView("invoice_report");
        modelAndView.addObject("income_names", null);   // List
        modelAndView.addObject("outcome_names", null);  // List
        modelAndView.addObject("income_amounts", null); // List<List>
        modelAndView.addObject("outcome_amounts",null); // List<List>
        modelAndView.addObject("dates", null);  // List
        modelAndView.addObject("incomes", null);    // List
        modelAndView.addObject("outcomes", null);   // List
        modelAndView.addObject("has_result", false);
        return modelAndView;
    }

    @RequestMapping(value = "report", method = RequestMethod.POST)
    public ModelAndView queryReportForm(
            @RequestParam("buyer_name") String buyerName,
            @RequestParam("seller_name") String sellerName,
            @RequestParam("start_time") Date startDate,
            @RequestParam("end_time") Date endDate) {
        InvoiceList invoiceList = invoiceService.getInvoiceListByUserId(0);
        List<Invoice> incomeInvoices = invoiceService.getInvoicesByNamesAndDateRange(buyerName, sellerName, startDate, endDate);
        List<Invoice> outcomeInvoices = invoiceService.getInvoicesByNamesAndDateRange(sellerName, buyerName, startDate, endDate);
        logger.info(buyerName + "\n" + sellerName + "\n" + startDate + "\n" + endDate);
        logger.info("incomeInvoices.size: " + incomeInvoices.size());
        logger.info("outcomeInvoices.size: " + outcomeInvoices.size());
        invoiceList.clear();
        invoiceList.addAll(incomeInvoices);
        invoiceList.addAll(outcomeInvoices);
        InvoiceMaps invoiceMaps = new InvoiceMaps(incomeInvoices, outcomeInvoices);
        List<TotalCome> comeList = invoiceMaps.getTotalComes();
        List<String> dates = new ArrayList<>();
        List<Double> incomes = new ArrayList<>();
        List<Double> outcomes = new ArrayList<>();
        List<List<ProductCome>> result = invoiceMaps.getProductComes();
        List<ProductCome> incomeProductComes = result.get(0);
        List<ProductCome> outcomeProductComes = result.get(1);
        List<String> incomeNames = new ArrayList<>();
        List<String> outcomeNames = new ArrayList<>();
        List<List<Double>> incomeAmounts = new ArrayList<>();
        List<List<Double>> outcomeAmounts = new ArrayList<>();
        if (invoiceList.size() != 0) {
            for (TotalCome come : comeList) {
                logger.info(come.getDate());
                logger.info(come.getIncomes());
                logger.info(come.getOutcomes());
                dates.add(come.getDate());
                incomes.add(new BigDecimal(come.getIncomes()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                outcomes.add(new BigDecimal(come.getOutcomes()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }

            incomeNames.addAll(incomeProductComes.get(0).getNames());
            outcomeNames.addAll(outcomeProductComes.get(0).getNames());

            // 进项与销项数据装载
            for (ProductCome income : incomeProductComes) {
                incomeAmounts.add(income.getAmounts());
            }
            for (ProductCome outcome : outcomeProductComes) {
                outcomeAmounts.add(outcome.getAmounts());
            }
        }

        ModelAndView modelAndView = new ModelAndView("invoice_report");
        logger.info("size: " + invoiceList.size());
        modelAndView.addObject("income_names", incomeNames);   // List
        modelAndView.addObject("outcome_names", outcomeNames);  // List
        modelAndView.addObject("income_amounts", incomeAmounts); // List<List>
        modelAndView.addObject("outcome_amounts",outcomeAmounts); // List<List>
        modelAndView.addObject("dates", dates);
        modelAndView.addObject("incomes", incomes);
        modelAndView.addObject("outcomes", outcomes);
        modelAndView.addObject("has_result", invoiceList.size() != 0);
        return modelAndView;
    }
}
