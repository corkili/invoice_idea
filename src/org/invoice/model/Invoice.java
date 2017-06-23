package org.invoice.model;

import org.invoice.dao.InvoiceDao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 李浩然 on 2017/4/11.
 */
@Entity
@Table(name = InvoiceDao.TABLE_INVOICE)
public class Invoice implements Serializable {
    private static final long serialVersionUID = 710397989174849013L;
    @Column(name = InvoiceDao.COL_IDENTIFY_ID)
    private long identifyId;                // 发票的唯一标识
    @Column(name = InvoiceDao.COL_INVOICE_ID)
    private String invoiceId;               // 发票号码
    @Column(name = InvoiceDao.COL_INVOICE_CODE)
    private String invoiceCode;             // 发票代码
    @Column(name = InvoiceDao.COL_INVOICE_DATE)
    private Date invoiceDate;               // 开票日期
    @Column(name = InvoiceDao.COL_BUYER_NAME)
    private String buyerName;               // 买方名称
    @Column(name = InvoiceDao.COL_BUYER_ID)
    private String buyerId;                 // 买方纳税人识别号
    @Column(name = InvoiceDao.COL_SELLER_NAME)
    private String sellerName;              // 卖方名称
    @Column(name = InvoiceDao.COL_SELLER_ID)
    private String sellerId;                // 卖方纳税人识别号
    @Column(name = InvoiceDao.COL_TOTAL_AMOUNT)
    private double totalAmount;             // 合计金额
    @Column(name = InvoiceDao.COL_TOTAL_TAX)
    private double totalTax;                // 合计税额
    @Column(name = InvoiceDao.COL_TOTAL)
    private double total;                   // 税价合计
    @Column(name = InvoiceDao.COL_REMARK)
    private String remark;                  // 备注
    private List<InvoiceDetail> details;    // 发票明细

    public Invoice(){
        details = new ArrayList<>();
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<InvoiceDetail> getDetails() {
        return details;
    }

    public void setDetails(List<InvoiceDetail> details) {
        this.details = details;
    }

    public long getIdentifyId() {
        return identifyId;
    }

    public void setIdentifyId(long identifyId) {
        this.identifyId = identifyId;
    }
}
