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
    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    private SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

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
        List<Double> incomeProductTotals = new ArrayList<>();
        List<Double> outcomeProductTotals = new ArrayList<>();
        List<Double> balances = new ArrayList<>();
        String incomeComment = "";
        String outcomeComment = "";
        StringBuilder compareComment = new StringBuilder();

        if (invoiceList.size() != 0) {
            for (TotalCome come : comeList) {
                logger.info(come.getDate());
                logger.info(come.getIncomes());
                logger.info(come.getOutcomes());
                dates.add(come.getDate());
                incomes.add(new BigDecimal(come.getIncomes()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                outcomes.add(new BigDecimal(come.getOutcomes()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                balances.add(new BigDecimal(come.getOutcomes() - come.getIncomes()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
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
            double sum = 0.0;
            // 进项数据，年月总和
            for (int i = 0; i < incomeNames.size(); i++) {
                sum = 0.0;
                for (int j = 0; j < incomeAmounts.size(); j++) {
                    sum += incomeAmounts.get(j).get(i);
                }
                incomeProductTotals.add(new BigDecimal(sum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            sum = 0.0;
            for (Double income : incomes) {
                sum += income;
            }
            incomeProductTotals.add(new BigDecimal(sum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            // 销项数据, 年月总和
            for (int i = 0; i < outcomeNames.size(); i++) {
                sum = 0.0;
                for (List<Double> amounts : outcomeAmounts) {
                    sum += amounts.get(i);
                }
                outcomeProductTotals.add(new BigDecimal(sum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            sum = 0.0;
            for (Double outcome : outcomes) {
                sum += outcome;
            }
            outcomeProductTotals.add(new BigDecimal(sum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            balances.add(new BigDecimal(outcomeProductTotals.get(outcomeProductTotals.size() - 1)
                    - incomeProductTotals.get(incomeProductTotals.size() - 1))
                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            // 日期
            String dateString = yearFormat.format(startDate) + "年" +
                    monthFormat.format(startDate) + "月至" +
                    yearFormat.format(endDate) + "年" +
                    monthFormat.format(endDate) + "月";

            // 进、销项数据分析
            incomeComment = compareIncomeOrOutcome(incomeAmounts, incomeProductTotals, dateString, "进项", incomeNames);
            outcomeComment = compareIncomeOrOutcome(outcomeAmounts, outcomeProductTotals, dateString, "销项", outcomeNames);
            logger.info(incomeComment);
            logger.info(outcomeComment);

            compareComment.append("进销项对比分析：由上述的数据可知，在")
                    .append(dateString).append("，企业的总进项额为")
                    .append(incomeProductTotals.get(incomeProductTotals.size() - 1))
                    .append("元，").append("总销项额为")
                    .append(outcomeProductTotals.get(outcomeProductTotals.size() - 1))
                    .append("元，").append("进、销项差值为：")
                    .append(Math.abs(balances.get(balances.size() - 1)))
                    .append("元。");
            compareComment.append("在").append(dateString);
            if (balances.get(balances.size() - 1) > 0) {
                compareComment.append("，企业总体运营情况良好！");
            } else if (balances.get(balances.size() - 1) < 0) {
                compareComment.append("，企业总体运营情况不好！");
            } else {
                compareComment.append("，企业总体运营情况稳定！");
            }
        }

        ModelAndView modelAndView = new ModelAndView("invoice_report");
        logger.info("size: " + invoiceList.size());
        modelAndView.addObject("balances", balances); // List
        modelAndView.addObject("income_product_totals", incomeProductTotals); // List
        modelAndView.addObject("outcome_product_totals", outcomeProductTotals); // List
        modelAndView.addObject("income_names", incomeNames);   // List
        modelAndView.addObject("outcome_names", outcomeNames);  // List
        modelAndView.addObject("income_amounts", incomeAmounts); // List<List>
        modelAndView.addObject("outcome_amounts",outcomeAmounts); // List<List>
        modelAndView.addObject("dates", dates); // List
        modelAndView.addObject("incomes", incomes); // List
        modelAndView.addObject("outcomes", outcomes); // List
        modelAndView.addObject("income_comments", incomeComment);   // String
        modelAndView.addObject("outcome_comments", outcomeComment); // String
        modelAndView.addObject("compare_comments", compareComment.toString());  // String
        modelAndView.addObject("has_result", invoiceList.size() != 0);
        return modelAndView;
    }

    private String compareIncomeOrOutcome(List<List<Double>> amounts, List<Double> productTotals,
                                         String dateString, String type, List<String> names) {
        StringBuilder comments = new StringBuilder();
        List<String> Ups = new ArrayList<>();
        List<String> Downs = new ArrayList<>();
        List<String> Holds = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            double diff = amounts.get(amounts.size() - 1).get(i) - amounts.get(0).get(i);
            if (diff > 0) {
                Ups.add(names.get(i));
            } else if (diff < 0) {
                Downs.add(names.get(i));
            } else {
                Holds.add(names.get(i));
            }
        }
        comments.append("由以上数据可以看出，");
        if (Ups.size() > 0) {
            comments.append(Ups.get(0));
            for (int i = 1; i < Ups.size(); i++) {
                comments.append("、").append(Ups.get(i));
            }
            if (Ups.size() > 1) {
                comments.append("等");
            }
            comments.append("产品，在").append(dateString).append("时间段内总体呈现上升趋势");
        }
        if (Downs.size() > 0) {
            comments.append("；").append(Downs.get(0));
            for (int i = 1; i < Downs.size(); i++) {
                comments.append("、").append(Downs.get(i));
            }
            if (Downs.size() > 1) {
                comments.append("等");
            }
            comments.append("产品，在").append(dateString).append("时间段内总体呈现下降趋势");
        }
        if (Holds.size() > 0) {
            comments.append("；").append(Holds.get(0));
            for (int i = 1; i < Holds.size(); i++) {
                comments.append("、").append(Holds.get(i));
            }
            if (Holds.size() > 1) {
                comments.append("等");
            }
            comments.append("产品，在").append(dateString).append("时间段内总体呈现平滑趋势");
        }
        comments.append("。\n");
        comments.append("企业在").append(dateString).append("时间段内，总计").append(type)
                .append(productTotals.get(productTotals.size() - 1)).append("元，总体在")
                .append(dateString).append("时间段内呈现");
        if (productTotals.get(productTotals.size() - 1) > productTotals.get(0)) {
            comments.append("上升");
        } else if (productTotals.get(productTotals.size() - 1) < productTotals.get(0)) {
            comments.append("下降");
        } else {
            comments.append("平滑");
        }
        comments.append("趋势。");
        return comments.toString();
    }
}
