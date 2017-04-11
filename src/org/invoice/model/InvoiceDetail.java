package org.invoice.model;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by 李浩然 on 2017/4/12.
 */
@Entity
public class InvoiceDetail implements Serializable {
    private static final long serialVersionUID = -7556430720495004066L;
    private String invoiceId;               // 发票号码
    private String detailName;              // 明细名称
    private String specification;           // 规格型号
    private String unitName;                // 单位
    private int quantity;                   // 数量
    private double unitPrice;               // 单价
    private double amount;                  // 金额
    private double taxRate;                 // 税率
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
}
