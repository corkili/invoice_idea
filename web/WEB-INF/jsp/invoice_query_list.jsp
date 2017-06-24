<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/5/25
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <title><spring:message code="title.invoice_list" /></title>

    <!-- Bootstrap -->
    <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="../vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- bootstrap-wysiwyg -->
    <link href="../vendors/google-code-prettify/bin/prettify.min.css" rel="stylesheet">
    <!-- Select2 -->
    <link href="../vendors/select2/dist/css/select2.min.css" rel="stylesheet">
    <!-- Switchery -->
    <link href="../vendors/switchery/dist/switchery.min.css" rel="stylesheet">
    <!-- starrr -->
    <link href="../vendors/starrr/dist/starrr.css" rel="stylesheet">
    <!-- bootstrap-daterangepicker -->
    <link href="../vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../build/css/custom.min.css" rel="stylesheet">

    <script>
        function viewInvoice(index, invoice_id){
            var form = document.createElement("form");
            form.action = "view_invoice";
            form.method = "post";
            form.style.display = "none";
            var opt1 = document.createElement("input");
            opt1.name = "index";
            opt1.value = index;
            var opt2 = document.createElement("input");
            opt2.name = "invoice_id";
            opt2.value =  invoice_id;
            form.appendChild(opt1);
            form.appendChild(opt2);
            document.body.appendChild(form);
            form.submit();
            return form;
        }

        function delInvoice(index, invoice_id){
            var form = document.createElement("form");
            form.action = "del_invoice";
            form.method = "post";
            form.style.display = "none";
            var opt1 = document.createElement("input");
            opt1.name = "index";
            opt1.value = index;
            var opt2 = document.createElement("input");
            opt2.name = "invoice_id";
            opt2.value =  invoice_id;
            form.appendChild(opt1);
            form.appendChild(opt2);
            document.body.appendChild(form);
            form.submit();
            return form;
        }
    </script>

</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <%@ include file="left_top.jspf"%>

        <!-- top navigation -->
        <%@ include file="right_top.jspf"%>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3><spring:message code="title.invoice_list" /></h3>
                    </div>
                </div>

                <c:choose>
                    <c:when test="${has_authority}">
                        <div class="clearfix"></div>

                        <!-- query form -->

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2>查询条件</h2>
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>

                                    <div class="x_content">
                                        <form action="list_query" method="post" class="form-horizontal form-label-left">
                                            <%@ include file="invoice_query_form.jspf"%>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <c:if test="${edit_invoice}">
                            <div class="row">
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="x_panel">
                                        <div class="x_title">
                                            <h2><spring:message code="form.title.invoice" /></h2>
                                            <ul class="nav navbar-right panel_toolbox">
                                                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                                                <li><a class="close-link"><i class="fa fa-close"></i></a></li>
                                            </ul>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="x_content">
                                            <form:form commandName="invoice" action="save_edit_invoice" method="post" cssClass="form-horizontal form-label-left">
                                                <input type="hidden" name="save_action_source" value="invoice_query_list">
                                                <input type="hidden" name="index" value="${index}">
                                                <%@ include file="invoice_edit_form.jspf"%>
                                            </form:form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>

                        <c:if test="${view_invoice}">
                            <div class="row">
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="x_panel">
                                        <div class="x_title">
                                            <h2><spring:message code="form.title.invoice" /></h2>
                                            <ul class="nav navbar-right panel_toolbox">
                                                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                                </li>
                                                <li><a class="close-link"><i class="fa fa-close"></i></a>
                                                </li>
                                            </ul>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="x_content">

                                            <section class="content invoice">
                                                <!-- title row -->

                                                <%@include file="invoice_element.jspf"%>

                                                <div class="row no-print">
                                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                                        <div class="col-md-offset-10 col-sm-offset-10 col-xs-offset-10 col-md-1 col-sm-1 col-xs-1">
                                                            <form action="edit_invoice" method="post">
                                                                <input type="hidden" name="index" value="${index}">
                                                                <input type="hidden" name="invoice_id" value="${invoice.invoiceId}">
                                                                <input type="hidden" name="invoice_code" value="${invoice.invoiceCode}">
                                                                <input type="submit" value="<spring:message code="button.edit"/>"
                                                                       class="btn btn-round btn-primary pull-right">
                                                            </form>

                                                        </div>
                                                        <div class="col-md-1 col-sm-1 col-xs-1">
                                                            <form action="del_invoice" method="post">
                                                                <input type="hidden" name="index" value="${index}">
                                                                <input type="hidden" name="invoice_id" value="${invoice.invoiceId}">
                                                                <input type="hidden" name="invoice_code" value="${invoice.invoiceCode}">
                                                                <input type="submit" value="<spring:message code="button.del" />"
                                                                       class="btn btn-round btn-danger pull-right" onclick="return confirm('确定删除发票？');">
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>

                        <!-- result list -->
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2>查询结果</h2>
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>

                                    <div class="x_content">

                                        <c:choose>
                                            <c:when test="${has_result}">
                                                <div class="table-responsive">
                                                    <table id="datatable" class="table table-striped jambo_table" style="white-space: nowrap;">
                                                        <thead>
                                                        <tr class="headings">
                                                            <th class="column-title">发票代码</th>
                                                            <th class="column-title">发票号码</th>
                                                            <th class="column-title">开票日期</th>
                                                            <th class="column-title">购贷方</th>
                                                            <th class="column-title">销贷方</th>
                                                            <th class="column-title">总金额</th>
                                                            <th class="column-title">总税额</th>
                                                            <th class="column-title no-link last"><span class="nobr">操作</span></th>
                                                        </tr>
                                                        </thead>

                                                        <tbody>
                                                        <c:forEach var="invoice" items="${invoice_list.invoiceList}" varStatus="status">
                                                            <c:choose>
                                                                <c:when test="${status.index % 2 == 0}">
                                                                    <tr class="even pointer">
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <tr class="odd pointer">
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td class=" ">${invoice.invoiceCode}</td>
                                                            <td class=" ">${invoice.invoiceId}</td>
                                                            <td class=" ">${invoice.displayDate}</td>
                                                            <td class=" ">${invoice.buyerName}</td>
                                                            <td class=" ">${invoice.sellerName}</td>
                                                            <td class=" ">￥${invoice.totalAmount}</td>
                                                            <td class=" ">￥${invoice.totalTax}</td>
                                                            <td class=" last">
                                                                <form action="view_invoice" method="post">
                                                                    <input type="hidden" name="index" value="${status.index}">
                                                                    <input type="hidden" name="invoice_id" value="${invoice.invoiceId}">
                                                                    <input type="hidden" name="invoice_code" value="${invoice.invoiceCode}">
                                                                    <input type="submit" value="<spring:message code="button.view" />"
                                                                           class="btn btn-round btn-success">
                                                                </form>
                                                            </td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <h3 style="text-align: center"><small><spring:message code="tip.no_result"/></small></h3>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <%@ include file="no_authority.jspf"%>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
            <div class="pull-right">
                <spring:message code="text.footer"/>
            </div>
            <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
    </div>
</div>
<!-- jQuery -->
<script src="../vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="../vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="../vendors/nprogress/nprogress.js"></script>
<!-- iCheck -->
<script src="../vendors/iCheck/icheck.min.js"></script>
<!-- Datatables -->
<script src="../vendors/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="../vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="../vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="../vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
<script src="../vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
<script src="../vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="../vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="../vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
<script src="../vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
<script src="../vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
<script src="../vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
<script src="../vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
<script src="../vendors/jszip/dist/jszip.min.js"></script>
<script src="../vendors/pdfmake/build/pdfmake.min.js"></script>
<script src="../vendors/pdfmake/build/vfs_fonts.js"></script>

<!-- Custom Theme Scripts -->
<script src="../build/js/custom.min.js"></script>
</body>
</html>
