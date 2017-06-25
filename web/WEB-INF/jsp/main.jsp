<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/4/8
  Time: 18:35
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

    <title>企业增值税发票分析系统</title>

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
                        <h3><spring:message code="title.app_name" /></h3>
                    </div>
                </div>

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
                                <h3 style="text-align: center"><spring:message code="text.welcome"/></h3>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="clearfix"></div>

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
                                <div class="col-md-4 col-sm-4 profile_details">
                                    <div class="well profile_view">
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
                                                            <c:when test="${auth.get('auth_add_invoice')}">
                                                                <span class="fa fa-star"></span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="fa fa-star-o"></span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </li>
                                                    <li>
                                                        查询发票
                                                        <c:choose>
                                                            <c:when test="${auth.get('auth_query_invoice')}">
                                                                <span class="fa fa-star"></span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="fa fa-star-o"></span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </li>
                                                    <li>
                                                        修改发票
                                                        <c:choose>
                                                            <c:when test="${auth.get('auth_edit_invoice')}">
                                                                <span class="fa fa-star"></span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="fa fa-star-o"></span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </li>
                                                    <li>
                                                        删除发票
                                                        <c:choose>
                                                            <c:when test="${auth.get('auth_del_invoice')}">
                                                                <span class="fa fa-star"></span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="fa fa-star-o"></span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </li>
                                                    <li>
                                                        查询报表
                                                        <c:choose>
                                                            <c:when test="${auth.get('auth_query_report')}">
                                                                <span class="fa fa-star"></span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="fa fa-star-o"></span>
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
                                                <form action="main" method="post">
                                                    <input type="submit" class="btn btn-primary btn-xs"
                                                           value="修改密码">
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${edit_password}">
                                    <div class="col-md-8 col-sm-8" >
                                        <form action="modify_password" method="post" class="form-horizontal form-label-left">
                                            <div class="form-group">
                                                <label class="control-label col-md-2" for="old_password">
                                                    原始密码
                                                    <span class="required">*</span>
                                                </label>
                                                <div class="col-md-6">
                                                    <input class="form-control has-feedback-left"  type="password" id="old_password"
                                                           name="old_password" placeholder="Old Password" required="required"/>
                                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-2" for="new_password">
                                                    新密码
                                                    <span class="required">*</span>
                                                </label>
                                                <div class="col-md-6">
                                                    <input class="form-control has-feedback-left" type="password" id="new_password"
                                                           name="new_password" placeholder="Password" required="required"/>
                                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-2" for="confirm_password">
                                                    确认密码
                                                    <span class="required">*</span>
                                                </label>
                                                <div class="col-md-6">
                                                    <input class="form-control has-feedback-left" type="password" id="confirm_password"
                                                           name="confirm_password" placeholder="Confirm Password" required="required"/>
                                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-2 col-sm-2 col-xs-2" for="captcha">
                                                    验证码
                                                    <span class="required">*</span>
                                                </label>
                                                <div class="col-md-3 col-sm-3 col-xs-3">
                                                    <input type="text" id="captcha" name="captcha" class="form-control has-feedback-left"
                                                           maxlength="6" placeholder="Verification code" required="required"/>
                                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                                </div>
                                                <div class="col-md-3 col-sm-3 col-xs-3">
                                                    <img id="captchaImage" src="captcha" onclick="reImg()"/>
                                                </div>
                                                <script type="text/javascript">
                                                    function reImg(){
                                                        var img = document.getElementById("captchaImage");
                                                        img.src = "captcha?timestamp=" + (new Date()).valueOf();
                                                    }
                                                </script>
                                            </div>
                                            <div class="ln_solid"></div>
                                            <c:if test="${has_error}">
                                                <div class="form-group">
                                                    <div class="col-md-8 text-center">
                                                        <p style="color: red">${error_message}</p>
                                                    </div>
                                                </div>
                                                <div class="ln_solid"></div>
                                            </c:if>
                                            <div class="form-group">
                                                <div class="col-md-5 col-md-offset-3">
                                                    <script>
                                                        function cancel() {
                                                            window.location.href="/main";
                                                        }
                                                    </script>
                                                    <input type="submit" class="btn btn-success" value="<spring:message code="button.ok" /> ">
                                                    <input type="button" class="btn btn-primary" value="<spring:message code="button.cancel" />"
                                                           onclick="cancel()">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>

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

