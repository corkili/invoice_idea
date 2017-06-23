package org.invoice.model;

import org.invoice.dao.InvoiceDao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by 李浩然 on 2017/4/12.
 */
@Entity
@Table(name = InvoiceDao.TABLE_DETAILES)
public class InvoiceDetail implements Serializable {
    private static final long serialVersionUID = -7556430720495004066L;
    public static final long EMPTY_DETAIL_ID = 0;
    @Column(name = InvoiceDao.COL_DETAIL_ID)
    private long detailId;                  // 明细编号
    @Column(name = InvoiceDao.COL_INVOICE_ID)
    private String invoiceId;               // 发票号码
    @Column(name = InvoiceDao.COL_INVOICE_CODE)
    private String invoiceCode;             // 发票代码
    @Column(name = InvoiceDao.COL_DETAIL_NAME)
    private String detailName;              // 明细名称
    @Column(name = InvoiceDao.COL_SPECIFICATION)
    private String specification;           // 规格型号
    @Column(name = InvoiceDao.COL_UNIT_NAME)
    private String unitName;                // 单位
    @Column(name = InvoiceDao.COL_QUANTITY)
    private int quantity;                   // 数量
    @Column(name = InvoiceDao.COL_UNIT_PRICE)
    private double unitPrice;               // 单价
    @Column(name = InvoiceDao.COL_AMOUNT)
    private double amount;                  // 金额
    @Column(name = InvoiceDao.COL_TAX_RATE)
    private double taxRate;                 // 税率
    @Column(name = InvoiceDao.COL_TAX_SUM)
    private double taxSum;                  // 税额

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getTaxSum() {
        return taxSum;
    }

    public void setTaxSum(double taxSum) {
        this.taxSum = taxSum;
    }

    public long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }
}
