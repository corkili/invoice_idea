<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/4/12
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Invoice</title>
</head>
<body>
<c:choose>
    <c:when test="${empty invoices}">
        <p>empty invoices!</p>
    </c:when>
    <c:otherwise>
        <c:forEach var="invoice" items="${invoices}" varStatus="invoiceStatus">
            <p>${invoiceStatus.count}>...<br/></p>
            <c:choose>
                <c:when test="${not empty invoice}">
                    <p>发票号码: ${invoice.invoiceId}</p>
                    <p>发票编码: ${invoice.invoiceCode}</p>
                    <p>开票日期: ${invoice.invoiceDate}</p>
                    <p>买方名称: ${invoice.buyerName}</p>
                    <p>买方纳税人识别号: ${invoice.buyerId}</p>
                    <p>卖方名称: ${invoice.sellerName}</p>
                    <p>卖方纳税人识别号: ${invoice.sellerName}</p>
                    <p>合计金额: ${invoice.totalAmount}</p>
                    <p>合计税额: ${invoice.totalTax}</p>
                    <p>税价合计: ${invoice.total}</p>
                    <p>备注: ${invoice.remark}</p>
                </c:when>
                <c:otherwise>
                    <p>empty invoice!</p>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${not empty invoice and not empty invoice.details}">
                    <c:forEach var="detail" items="${invoice.details}" varStatus="detailStatus">
                        <p>${detailStatus.count}> <br/></p>
                        <p>明细名称: ${detail.detailName}</p>
                        <p>规格型号: ${detail.specification}</p>
                        <p>单位: ${detail.unitName}</p>
                        <p>数量: ${detail.quantity}</p>
                        <p>单价: ${detail.unitPrice}</p>
                        <p>金额: ${detail.amount}</p>
                        <p>税率: ${detail.taxRate}</p>
                        <p>税额: ${detail.taxSum}</p>
                    </c:forEach>
                </c:when>
                <c:when test="${not empty invoice and empty invoice.details}">
                    <p>empty details!</p>
                </c:when>
            </c:choose>
        </c:forEach>
    </c:otherwise>
</c:choose>

<p></p>
</body>
</html>
