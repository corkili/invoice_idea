package org.invoice.utils;

/**
 * Created by 李浩然 on 2017/6/24.
 */
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.invoice.model.Invoice;
import org.invoice.model.InvoiceDetail;

/**
 * 读取Excel
 *
 * @author zengwendong
 */
public class ExcelUtil {
    private Logger logger = Logger.getLogger(ExcelUtil.class);
    private Workbook wb;
    private Sheet sheet;
    private Row row;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public ExcelUtil(String filepath) {
        if(filepath==null){
            return;
        }
        String ext = filepath.substring(filepath.lastIndexOf("."));
        try {
            InputStream is = new FileInputStream(filepath);
            if(".xls".equals(ext)){
                wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(ext)){
                wb = new XSSFWorkbook(is);
            }else{
                wb=null;
            }
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
    }

    /**
     * 读取Excel表格表头的内容
     *
     * @return String 表头内容的数组
     * @author zengwendong
     */
    public String[] readExcelTitle() throws Exception{
        if(wb==null){
            throw new Exception("Workbook对象为空！");
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("colNum:" + colNum);
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            // title[i] = getStringCellValue(row.getCell((short) i));
            title[i] = row.getCell(i).getCellFormula();
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     *
     * @return Map 包含单元格数据内容的Map对象
     * @author zengwendong
     */
    public List<Invoice> getInvoicesFromExcel(){
        if(wb == null){
            return null;
        }
        List<Invoice> invoices = new ArrayList<>();  //

        Map<String, Invoice> invoiceMap = new HashMap<>();

        // 解析发票数据
        sheet = wb.getSheetAt(0);
        if (sheet == null) {
            return null;
        }
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        if (colNum != 11) {
            return null;
        }
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            Invoice invoice = new Invoice();
            Object object;
            int j = 0;
            // 0:发票ID
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof String) {
                invoice.setInvoiceId((String)object);
            } else {
                continue;
            }
            // 1:发票Code
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof String) {
                invoice.setInvoiceCode((String)object);
            } else {
                continue;
            }
            // 2:发票日期
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof Date) {
                invoice.setInvoiceDate((Date)object);
            } else if(object instanceof String) {
                try {
                    invoice.setInvoiceDate(dateFormat.parse((String)object));
                } catch (ParseException e) {
                    continue;
                }
            } else {
                continue;
            }
            // 3:购贷方名称
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof String) {
                invoice.setBuyerName((String)object);
            } else {
                continue;
            }
            // 4:购贷方ID
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof String) {
                invoice.setBuyerId((String)object);
            } else {
                continue;
            }
            // 5:销贷方名称
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof String) {
                invoice.setSellerName((String)object);
            } else {
                continue;
            }
            // 6:销贷方ID
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof String) {
                invoice.setSellerId((String)object);
            } else {
                continue;
            }
            // 7:总金额
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof Double) {
                invoice.setTotalAmount((double)object);
            } else if (object instanceof String){
                try {
                    invoice.setTotalAmount(Double.parseDouble((String)object));
                } catch (NumberFormatException e) {
                    continue;
                }
            } else {
                continue;
            }
            // 8:总税额
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof Double) {
                invoice.setTotalTax((double)object);
            } else if (object instanceof String){
                try {
                    invoice.setTotalTax(Double.parseDouble((String)object));
                } catch (NumberFormatException e) {
                    continue;
                }
            } else {
                continue;
            }
            // 9:税价合计
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof Double) {
                invoice.setTotal((double)object);
            } else if (object instanceof String){
                try {
                    invoice.setTotal(Double.parseDouble((String)object));
                } catch (NumberFormatException e) {
                    continue;
                }
            } else {
                continue;
            }
            // 10:备注
            if ((object = getCellFormatValue(row.getCell(j))) instanceof String) {
                invoice.setRemark((String)object);
            }
            invoice.setDetails(new ArrayList<>());
            invoiceMap.put(invoice.getInvoiceId() + "_" + invoice.getInvoiceCode(), invoice);
        }

        // 解析发票明细数据
        sheet = wb.getSheetAt(1);
        if (sheet == null) {
            return null;
        }
        // 得到总行数
        rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        colNum = row.getPhysicalNumberOfCells();
        if (colNum != 10) {
            return null;
        }
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            InvoiceDetail detail = new InvoiceDetail();
            Object object;
            int j = 0;
            // 0:发票ID
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof String) {
                detail.setInvoiceId((String) object);
            } else {
                continue;
            }
            // 1:发票代码
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof String) {
                detail.setInvoiceCode((String) object);
            } else {
                continue;
            }
            // 2:产品名称
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof String) {
                detail.setDetailName((String) object);
            } else {
                continue;
            }
            // 3:规格型号
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof String) {
                detail.setSpecification((String) object);
            } else {
                continue;
            }
            // 4:单位
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof String) {
                detail.setUnitName((String) object);
            } else {
                continue;
            }
            // 5:数量
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof Double) {
                detail.setQuantity((int)((double)object));
            } else if (object instanceof String){
                try {
                    detail.setQuantity((int)Double.parseDouble((String)object));
                } catch (NumberFormatException e) {
                    continue;
                }
            } else {
                continue;
            }
            // 6:单价
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof Double) {
                detail.setUnitPrice((double) object);
            } else if (object instanceof String){
                try {
                    detail.setUnitPrice(Double.parseDouble((String)object));
                } catch (NumberFormatException e) {
                    continue;
                }
            } else {
                continue;
            }
            // 7:金额
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof Double) {
                detail.setAmount((double) object);
            } else if (object instanceof String){
                try {
                    detail.setAmount(Double.parseDouble((String)object));
                } catch (NumberFormatException e) {
                    continue;
                }
            } else {
                continue;
            }
            // 8:税率
            if ((object = getCellFormatValue(row.getCell(j++))) instanceof Double) {
                detail.setTaxRate((double) object);
            } else if (object instanceof String){
                try {
                    detail.setTaxRate(Double.parseDouble((String)object));
                } catch (NumberFormatException e) {
                    continue;
                }
            } else {
                continue;
            }
            // 9:税额
            if ((object = getCellFormatValue(row.getCell(j))) instanceof Double) {
                detail.setTaxSum((double) object);
            } else if (object instanceof String){
                try {
                    detail.setTaxSum(Double.parseDouble((String)object));
                } catch (NumberFormatException e) {
                    continue;
                }
            } else {
                continue;
            }
            try {
                invoiceMap.get(detail.getInvoiceId() + "_" + detail.getInvoiceCode()).getDetails().add(detail);
            } catch (NullPointerException e) {
                logger.info(detail.getInvoiceId() + "_" + detail.getInvoiceCode());
            }
        }
        invoices.addAll(invoiceMap.values());
        return invoices;
    }

    /**
     *
     * 根据Cell类型设置数据
     *
     * @param cell
     * @return
     * @author zengwendong
     */
    private Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
                case Cell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Date格式
                        // date格式是带时分秒的：2013-7-10 0:00:00
                        // cellvalue = cell.getDateCellValue().toLocaleString();
                        // date格式是不带带时分秒的：2013-7-10
                        Date date = cell.getDateCellValue();
                        cellvalue = date;
                    } else {// 如果是纯数字
                        // 取得当前Cell的数值
                        cellvalue = cell.getNumericCellValue();
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:// 默认的Cell值
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

    public static void main(String[] args) {
        try {
            String filepath = "C:\\Users\\李浩然\\Desktop\\　\\project\\中软杯\\test data\\发票数据.xlsx";
            ExcelUtil excelReader = new ExcelUtil(filepath);

            // 对读取Excel表格内容测试
            List<Invoice> invoices = excelReader.getInvoicesFromExcel();
            System.out.println("获得Excel表格的内容:");
            System.out.println(invoices.size());
            int sum = 0;
            for (Invoice invoice : invoices) {
                System.out.println(invoice);
                for (InvoiceDetail detail : invoice.getDetails()) {
                    System.out.println(detail);
                }
                sum += invoice.getDetails().size();
            }
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
