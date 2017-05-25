<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/5/10
  Time: 14:56
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


    <title><spring:message code="title.add_invoice" /></title>

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
        function determineDetailNumber(){
            var num = document.getElementById("detail_num").value;
            if(num > 0) {
                document.getElementById("btn_determine").disabled = true;
                var tmp = document.createElement("form");
                tmp.action = "add_invoice_hand";
                tmp.method = "post";
                tmp.style.display = "none";
                var opt = document.createElement("input");
                opt.name = "detail_num";
                opt.value = num;
                tmp.appendChild(opt);
                document.body.appendChild(tmp);
                tmp.submit();
                return tmp;
            } else {
                alert("<spring:message code="detail_number.range" />");
            }
        }
    </script>
</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>Gentelella Alela!</span></a>
                </div>

                <div class="clearfix"></div>

                <!-- menu profile quick info -->
                <div class="profile clearfix">
                    <div class="profile_pic">
                        <img src="images/img.jpg" alt="..." class="img-circle profile_img">
                    </div>
                    <div class="profile_info">
                        <span>Welcome,</span>
                        <h2>Full GPA Late</h2>
                    </div>
                </div>
                <!-- /menu profile quick info -->

                <br />

                <!-- sidebar menu -->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                    <div class="menu_section">
                        <h3>General</h3>
                        <ul class="nav side-menu">
                            <li><a><i class="fa fa-home"></i> 首页 <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="/main">首页</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-edit"></i> 发票数据导入 <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="/add_invoice_hand">手动导入</a></li>
                                    <li><a href="invoice_input_image.html">图像导入</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-table"></i> 数据查询 <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="/list_query">列表查询</a></li>
                                    <li><a href="chart_query.html">图表查询</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-bar-chart-o"></i> 分析报表 <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="report_form.html">生成报表</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-clone"></i> 系统管理 <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="users_manage.html">用户管理</a></li>
                                    <li><a href="logs_maintain.html">日志维护</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
            <div class="nav_menu">
                <nav>
                    <div class="nav toggle">
                        <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                    </div>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="">
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <img src="../images/img.jpg" alt="">Full GPA Late
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu pull-right">
                                <li><a href="javascript:;"> Profile</a></li>
                                <li>
                                    <a href="javascript:;">
                                        <span class="badge bg-red pull-right">50%</span>
                                        <span>Settings</span>
                                    </a>
                                </li>
                                <li><a href="javascript:;">Help</a></li>
                                <li><a href="login.html"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                            </ul>
                        </li>

                        <li role="presentation" class="dropdown">
                            <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                                <i class="fa fa-envelope-o"></i>
                                <span class="badge bg-green">6</span>
                            </a>
                            <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                                <li>
                                    <a>
                                        <span class="image"><img src="../images/img.jpg" alt="Profile Image" /></span>
                                        <span>
                          <span>Full GPA Late</span>
                          <span class="time">3 mins ago</span>
                        </span>
                                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                                    </a>
                                </li>
                                <li>
                                    <a>
                                        <span class="image"><img src="../images/img.jpg" alt="Profile Image" /></span>
                                        <span>
                          <span>John Smith</span>
                          <span class="time">3 mins ago</span>
                        </span>
                                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                                    </a>
                                </li>
                                <li>
                                    <a>
                                        <span class="image"><img src="../images/img.jpg" alt="Profile Image" /></span>
                                        <span>
                          <span>Full GPA Late</span>
                          <span class="time">3 mins ago</span>
                        </span>
                                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                                    </a>
                                </li>
                                <li>
                                    <a>
                                        <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                                        <span>
                          <span>John Smith</span>
                          <span class="time">3 mins ago</span>
                        </span>
                                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                                    </a>
                                </li>
                                <li>
                                    <div class="text-center">
                                        <a>
                                            <strong>See All Alerts</strong>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3><spring:message code="title.add_invoice" /></h3>
                    </div>

                    <div class="title_right">
                        <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                            <div class="input-group">
                                <input type="text" class="form-control" id="detail_num"
                                       placeholder="<spring:message code="input.detail_number" />">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button" id="btn_determine"
                                            onclick="determineDetailNumber()">
                                        <spring:message code="button.ok"/>
                                    </button>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>
                <c:if test="${detail_num > 0}">
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2><spring:message code="form.title.invoice" /></h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <br />
                                    <form:form commandName="invoice" action="save_invoice" method="post" cssClass="form-horizontal form-label-left">
                                    <div class="row">
                                        <div class="form-group">
                                            <label class="control-label col-md-2" for="invoiceCode">
                                                <spring:message code="invoice.code" />
                                                <span class="required">*</span>
                                            </label>
                                            <div class="col-md-2">
                                                <form:input path="invoiceCode" id="invoiceCode" name="invoiceCode"
                                                            cssClass="form-control col-md-2" required="required"/>
                                            </div>
                                            <label class="control-label col-md-2" for="invoiceId">
                                                <spring:message code="invoice.id" />
                                                <span class="required">*</span>
                                            </label>
                                            <div class="col-md-2">
                                                <form:input path="invoiceId" id="invoiceId" name="invoiceId"
                                                            cssClass="form-control col-md-2" required="required"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-6" for="invoiceDate">
                                                <spring:message code="invoice.date" />
                                                <span class="required">*</span>
                                            </label>
                                            <div class="col-md-2">
                                                <form:input path="invoiceDate" id="invoiceDate" name="invoiceDate"
                                                            cssClass="form-control col-md-2" required="required"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-2" for="buyerName">
                                                （购贷单位）名称
                                                <span class="required">*</span>
                                            </label>
                                            <div class="col-md-6">
                                                <form:input path="buyerName" id="buyerName" name="buyerName"
                                                            cssClass="form-control col-md-6" required="required" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-2" for="buyerId">
                                                （购贷单位）纳税人识别号
                                            </label>
                                            <div class="col-md-6">
                                                <form:input path="buyerId" id="buyerId" name="buyerId"
                                                            cssClass="form-control col-md-6"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-1">产品名称<span class="required">*</span></label>
                                            <label class="control-label col-md-1">规格型号</label>
                                            <label class="control-label col-md-1">单位</label>
                                            <label class="control-label col-md-1">数量<span class="required">*</span></label>
                                            <label class="control-label col-md-1">单价<span class="required">*</span></label>
                                            <label class="control-label col-md-1">金额<span class="required">*</span></label>
                                            <label class="control-label col-md-1">税率（小数）<span class="required">*</span></label>
                                            <label class="control-label col-md-1">税额<span class="required">*</span></label>
                                        </div>

                                        <c:forEach var="i" begin="0" end="${detail_num-1}" step="1">
                                            <div class="form-group">
                                                <div class="col-md-1">
                                                    <form:input path="details[${i}].detailName"
                                                                cssClass="form-control col-md-1" required="required"/>
                                                </div>
                                                <div class="col-md-1">
                                                    <form:input path="details[${i}].specification"
                                                                cssClass="form-control col-md-1"/>
                                                </div>
                                                <div class="col-md-1">
                                                    <form:input path="details[${i}].unitName"
                                                                cssClass="form-control col-md-1"/>
                                                </div>
                                                <div class="col-md-1">
                                                    <form:input path="details[${i}].quantity"
                                                                cssClass="form-control col-md-1" required="required"/>
                                                </div>
                                                <div class="col-md-1">
                                                    <form:input path="details[${i}].unitPrice"
                                                                cssClass="form-control col-md-1" required="required"/>
                                                </div>
                                                <div class="col-md-1">
                                                    <form:input path="details[${i}].amount"
                                                                cssClass="form-control col-md-1" required="required"/>
                                                </div>
                                                <div class="col-md-1">
                                                    <form:input path="details[${i}].taxRate"
                                                                cssClass="form-control col-md-1" required="required"/>
                                                </div>
                                                <div class="col-md-1">
                                                    <form:input path="details[${i}].taxSum"
                                                                cssClass="form-control col-md-1" required="required"/>
                                                </div>
                                            </div>
                                        </c:forEach>

                                        <div class="form-group">
                                            <label class="control-label col-md-5" for="totalAmount">
                                                合计金额<span class="required">*</span>
                                            </label>
                                            <div class="col-md-1">
                                                <form:input path="totalAmount" id="totalAmount" name="totalAmount"
                                                            cssClass="form-control col-md-1" required="required"/>
                                            </div>
                                            <label class="control-label col-md-1" for="totalTax">
                                                合计税额<span class="required">*</span>
                                            </label>
                                            <div class="col-md-1">
                                                <form:input path="totalTax" id="totalTax" name="totalTax"
                                                            cssClass="form-control col-md-1" required="required"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-6" for="total">
                                                税价合计<span class="required">*</span>
                                            </label>
                                            <div class="col-md-2">
                                                <form:input path="total" id="total" name="total"
                                                            cssClass="form-control col-md-2" required="required"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-2" for="sellerName">
                                                （销贷单位）名称
                                                <span class="required">*</span>
                                            </label>
                                            <div class="col-md-6">
                                                <form:input path="sellerName" id="sellerName" name="sellerName"
                                                            cssClass="form-control col-md-6" required="required" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-2" for="sellerId">
                                                （销贷单位）纳税人识别号
                                            </label>
                                            <div class="col-md-6">
                                                <form:input path="sellerId" id="sellerId" name="sellerId"
                                                            cssClass="form-control col-md-6"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-2" for="remark">备注</label>
                                            <div class="col-md-6">
                                                <form:input path="remark" id="remark" name="remark"
                                                            cssClass="form-control col-md-6"/>
                                            </div>
                                        </div>
                                        <div class="ln_solid"></div>
                                        <div class="form-group">
                                            <div class="col-md-5 col-md-offset-3">
                                                <input type="reset" class="btn btn-primary" value="<spring:message code="button.reset" />">
                                                <input type="submit" class="btn btn-success" value="<spring:message code="button.submit" /> ">
                                            </div>
                                        </div>
                                    </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
            <div class="pull-right">

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
<!-- bootstrap-progressbar -->
<script src="../vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
<!-- iCheck -->
<script src="../vendors/iCheck/icheck.min.js"></script>
<!-- bootstrap-daterangepicker -->
<script src="../vendors/moment/min/moment.min.js"></script>
<script src="../vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- bootstrap-wysiwyg -->
<script src="../vendors/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
<script src="../vendors/jquery.hotkeys/jquery.hotkeys.js"></script>
<script src="../vendors/google-code-prettify/src/prettify.js"></script>
<!-- jQuery Tags Input -->
<script src="../vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>
<!-- Switchery -->
<script src="../vendors/switchery/dist/switchery.min.js"></script>
<!-- Select2 -->
<script src="../vendors/select2/dist/js/select2.full.min.js"></script>
<!-- Parsley -->
<script src="../vendors/parsleyjs/dist/parsley.min.js"></script>
<!-- Autosize -->
<script src="../vendors/autosize/dist/autosize.min.js"></script>
<!-- jQuery autocomplete -->
<script src="../vendors/devbridge-autocomplete/dist/jquery.autocomplete.min.js"></script>
<!-- starrr -->
<script src="../vendors/starrr/dist/starrr.js"></script>
<!-- Custom Theme Scripts -->
<script src="../build/js/custom.min.js"></script>

</body>
</html>

