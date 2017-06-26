<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/6/13
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><spring:message code="title.user_manage"/></title>

    <!-- Bootstrap -->
    <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="../vendors/iCheck/skins/flat/green.css" rel="stylesheet">

    <!-- bootstrap-progressbar -->
    <link href="../vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="../vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>
    <!-- bootstrap-daterangepicker -->
    <link href="../vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../build/css/custom.min.css" rel="stylesheet">
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
                        <h3><spring:message code="title.user_manage" /></h3>
                    </div>
                </div>

                <c:if test="${edit_user}">
                    <div class="row">
                        <div class="col-md-12">
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
                                    <div class="col-md-4 col-sm-4 col-xs-12 profile_details">
                                        <div class="well profile_view">
                                            <form action="modify_authority" method="post">
                                                <div class="col-sm-12">
                                                    <h4 class="brief"><i>个人信息</i></h4>
                                                    <div class="left col-xs-7">
                                                        <h2>${user.username}</h2>
                                                        <p><strong><i class="fa fa-user"></i> 姓名: </strong>${user.name}</p>
                                                        <p><strong><i class="fa fa-credit-card"></i> 工号：</strong>${user.jobId}</p>
                                                        <p><strong><i class="fa fa-phone"></i>  手机：</strong>${user.phone}</p>
                                                        <p><strong><i class="fa fa-envelope"></i> 邮箱：</strong>${user.email}</p>
                                                        <p>
                                                            <strong>权限：</strong><br/>
                                                        </p>
                                                        <ul class="list-unstyled">
                                                            <li>
                                                                添加发票
                                                                <c:choose>
                                                                    <c:when test="${user.authorityMap.get('auth_add_invoice')}">
                                                                        <input type="checkbox" name="authority" class="flat"
                                                                               value="auth_add_invoice" checked="checked">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <input type="checkbox" name="authority"
                                                                               value="auth_add_invoice" class="flat">
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </li>
                                                            <li>
                                                                查询发票
                                                                <c:choose>
                                                                    <c:when test="${user.authorityMap.get('auth_query_invoice')}">
                                                                        <input type="checkbox" name="authority" class="flat"
                                                                               value="auth_query_invoice" checked="checked">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <input type="checkbox" name="authority"
                                                                               value="auth_query_invoice" class="flat">
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </li>
                                                            <li>
                                                                修改发票
                                                                <c:choose>
                                                                    <c:when test="${user.authorityMap.get('auth_edit_invoice')}">
                                                                        <input type="checkbox" name="authority" class="flat"
                                                                               value="auth_edit_invoice" checked="checked">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <input type="checkbox" name="authority"
                                                                               value="auth_edit_invoice" class="flat">
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </li>
                                                            <li>
                                                                删除发票
                                                                <c:choose>
                                                                    <c:when test="${user.authorityMap.get('auth_del_invoice')}">
                                                                        <input type="checkbox" name="authority" class="flat"
                                                                               value="auth_del_invoice" checked="checked">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <input type="checkbox" name="authority"
                                                                               value="auth_del_invoice" class="flat">
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </li>
                                                            <li>
                                                                查询报表
                                                                <c:choose>
                                                                    <c:when test="${user.authorityMap.get('auth_query_report')}">
                                                                        <input type="checkbox" name="authority" class="flat"
                                                                               value="auth_query_report" checked="checked">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <input type="checkbox" name="authority"
                                                                               value="auth_query_report" class="flat">
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div class="right col-xs-5 text-center">
                                                        <img src="images/se.png" alt="" class="img-circle img-responsive">
                                                    </div>
                                                </div>
                                                <div class="col-xs-12 bottom text-center">
                                                    <div class="col-xs-12 col-sm-6 emphasis right">
                                                        <input type="hidden" name="index" value="${index}">
                                                        <input type="hidden" name="user_id" value="${user.userId}">
                                                        <input type="checkbox" name="authority" value="auth_none"
                                                               style="display: none" checked="checked">
                                                        <input type="submit" value="<spring:message code="button.ok" />"
                                                               class="btn btn-round btn-danger pull-right" onclick="return confirm('确定保存修改？');">
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:choose>
                    <c:when test="${has_authority}">
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2></h2>
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <h3 style="text-align: center">用户列表</h3>
                                        <div class="table-responsive">
                                            <table id="datatable" class="table table-striped jambo_table" style="white-space: nowrap;">
                                                <thead>
                                                <tr class="headings">
                                                    <th class="column-title">用户名</th>
                                                    <th class="column-title">姓名</th>
                                                    <th class="column-title">工号</th>
                                                    <th class="column-title">手机</th>
                                                    <th class="column-title">邮箱</th>
                                                    <th class="column-title">添加发票</th>
                                                    <th class="column-title">查询发票</th>
                                                    <th class="column-title">修改发票</th>
                                                    <th class="column-title">删除发票</th>
                                                    <th class="column-title">查询报表</th>
                                                    <th class="column-title no-link last"><span class="nobr">操作</span></th>
                                                </tr>
                                                </thead>

                                                <tbody>
                                                <c:forEach var="user" items="${user_list}" varStatus="status">
                                                    <c:choose>
                                                        <c:when test="${status.index % 2 == 0}">
                                                            <tr class="even pointer">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <tr class="odd pointer">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td class=" ">${user.username}</td>
                                                    <td class=" ">${user.name}</td>
                                                    <td class=" ">${user.jobId}</td>
                                                    <td class=" ">${user.phone}</td>
                                                    <td class=" ">${user.email}</td>
                                                    <td class=" ">
                                                        <c:choose>
                                                            <c:when test="${user.authorityMap.get('auth_add_invoice')}">
                                                                <span class="fa fa-star"></span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="fa fa-star-o"></span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td class=" ">
                                                        <c:choose>
                                                            <c:when test="${user.authorityMap.get('auth_query_invoice')}">
                                                                <span class="fa fa-star"></span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="fa fa-star-o"></span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td class=" ">
                                                        <c:choose>
                                                            <c:when test="${user.authorityMap.get('auth_edit_invoice')}">
                                                                <span class="fa fa-star"></span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="fa fa-star-o"></span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td class=" ">
                                                        <c:choose>
                                                            <c:when test="${user.authorityMap.get('auth_del_invoice')}">
                                                                <span class="fa fa-star"></span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="fa fa-star-o"></span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td class=" ">
                                                        <c:choose>
                                                            <c:when test="${user.authorityMap.get('auth_query_report')}">
                                                                <span class="fa fa-star"></span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="fa fa-star-o"></span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td class=" last">
                                                        <form action="user_manage" method="post">
                                                            <input type="hidden" name="index" value="${status.index}">
                                                            <input type="hidden" name="user_id" value="${user.userId}">
                                                            <input type="submit" value="<spring:message code="button.edit_authority" />"
                                                                   class="btn btn-round btn-success">
                                                        </form>
                                                    </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="clearfix"></div>

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
<!-- Chart.js -->
<script src="../vendors/Chart.js/dist/Chart.min.js"></script>
<!-- gauge.js -->
<script src="../vendors/gauge.js/dist/gauge.min.js"></script>
<!-- bootstrap-progressbar -->
<script src="../vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
<!-- iCheck -->
<script src="../vendors/iCheck/icheck.min.js"></script>
<!-- Skycons -->
<script src="../vendors/skycons/skycons.js"></script>
<!-- Flot -->
<script src="../vendors/Flot/jquery.flot.js"></script>
<script src="../vendors/Flot/jquery.flot.pie.js"></script>
<script src="../vendors/Flot/jquery.flot.time.js"></script>
<script src="../vendors/Flot/jquery.flot.stack.js"></script>
<script src="../vendors/Flot/jquery.flot.resize.js"></script>
<!-- Flot plugins -->
<script src="../vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
<script src="../vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
<script src="../vendors/flot.curvedlines/curvedLines.js"></script>
<!-- DateJS -->
<script src="../vendors/DateJS/build/date.js"></script>
<!-- JQVMap -->
<script src="../vendors/jqvmap/dist/jquery.vmap.js"></script>
<script src="../vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
<script src="../vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
<!-- bootstrap-daterangepicker -->
<script src="../vendors/moment/min/moment.min.js"></script>
<script src="../vendors/bootstrap-daterangepicker/daterangepicker.js"></script>

<!-- Custom Theme Scripts -->
<script src="../build/js/custom.min.js"></script>

</body>
</html>


