<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/4/13
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <tr></tr>
        </table>
    </fieldset>
</form:form>
</body>
</html>
