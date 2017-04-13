<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/4/13
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Invoice</title>
</head>
<body>
<form:form commandName="invoice" action="save_invoice" method="post">
    <fieldset>
        <legend>Invoice</legend>
        <table border="1">
            <tr>
                <td colspan="1">
                    <input type="reset" value="重置"/>
                </td>
                <td colspan="6" style="text-align: center">增值税专用发票</td>
                <td colspan="1">
                    <input type="submit" value="提交"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center">
                    <form:input path="invoiceCode" id="invoiceCode" name="invoiceCode" tabindex="1" />
                </td>
                <td colspan="3"></td>
                <td colspan="1" style="text-align: right">No.</td>
                <td colspan="2" style="text-align: left">
                    <form:input path="invoiceId" id="invoiceId" name="invoiceId" tabindex="2" />
                </td>
            </tr>
            <tr>
                <td colspan="5"></td>
                <td colspan="1" style="text-align: right">开票日期：</td>
                <td colspan="2" style="text-align: left">
                    <form:input path="invoiceDate" id="invoiceDate" name="invoiceDate" tabindex="3" />
                </td>
            </tr>
            <tr>
                <td colspan="1" rowspan="2" style="text-align: center">购贷单位</td>
                <td colspan="1" rowspan="1" style="text-align: right">名称：</td>
                <td colspan="2" rowspan="1" style="text-align: left">
                    <form:input path="buyerName" id="buyerName" name="buyerName" tabindex="4" />
                </td>
                <td colspan="1" rowspan="2" style="text-align: center">密文区</td>
                <td colspan="3" rowspan="2"></td>
            </tr>
            <tr>
                <td colspan="1" rowspan="1" style="text-align: right">纳税人识别号：</td>
                <td colspan="2" rowspan="1" style="text-align: left">
                    <form:input path="buyerId" id="buyerId" name="buyerId" tabindex="5"/>
                </td>
            </tr>
            <tr>
                <td style="text-align: center">货物或应税劳务名称</td>
                <td style="text-align: center">规格型号</td>
                <td style="text-align: center">单位</td>
                <td style="text-align: center">数量</td>
                <td style="text-align: center">单价</td>
                <td style="text-align: center">金额</td>
                <td style="text-align: center">税率</td>
                <td style="text-align: center">税额</td>
            </tr>
            <c:forEach var="i" begin="0" end="${detail_num-1}" step="1">
                <tr>
                    <td style="text-align: center">
                        <form:input path="details[${i}].detailName" size="12" />
                    </td>
                    <td style="text-align: center">
                        <form:input path="details[${i}].specification" size="10" />
                    </td>
                    <td style="text-align: center">
                        <form:input path="details[${i}].unitName" size="8" />
                    </td>
                    <td style="text-align: center">
                        <form:input path="details[${i}].quantity" size="5" />
                    </td>
                    <td style="text-align: center">
                        <form:input path="details[${i}].unitPrice" size="8" />
                    </td>
                    <td style="text-align: center">
                        <form:input path="details[${i}].amount" size="8" />
                    </td>
                    <td style="text-align: center">
                        <form:input path="details[${i}].taxRate" size="3" />
                    </td>
                    <td style="text-align: center">
                        <form:input path="details[${i}].taxSum" size="5" />
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="1" style="text-align: center">合计</td>
                <td colspan="4"></td>
                <td colspan="1" style="text-align: center">
                    <form:input path="totalAmount" id="totalAmount" name="totalAmount" size="8" />
                </td>
                <td colspan="1"></td>
                <td colspan="1" style="text-align: center">
                    <form:input path="totalTax" id="totalTax" name="totalTax" size="5" />
                </td>
            </tr>
            <tr>
                <td colspan="5"></td>
                <td colspan="1" style="text-align: right">税价合计</td>
                <td colspan="2" style="text-align: center">
                    <form:input path="total" id="total" name="total" size="8" />
                </td>
            </tr>
            <tr>
                <td colspan="1" rowspan="2" style="text-align: center">销贷单位</td>
                <td colspan="1" rowspan="1" style="text-align: right">名称：</td>
                <td colspan="2" rowspan="1" style="text-align: left">
                    <form:input path="sellerName" id="sellerName" name="sellerName" />
                </td>
                <td colspan="1" rowspan="2" style="text-align: center">备注</td>
                <td colspan="3" rowspan="2">
                    <form:input path="remark" id="remark" name="remark" />
                </td>
            </tr>
            <tr>
                <td colspan="1" rowspan="1" style="text-align: right">纳税人识别号：</td>
                <td colspan="2" rowspan="1" style="text-align: left">
                    <form:input path="sellerId" id="sellerId" name="sellerId"/>
                </td>
            </tr>
        </table>
    </fieldset>
</form:form>
</body>
</html>
