<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/5/4
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>登录</title>

    <!-- Bootstrap -->
    <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="../vendors/animate.css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../build/css/custom.min.css" rel="stylesheet">
</head>

<body class="login">
<div>
    <div class="login_wrapper">
        <div class="animate form login_form">
            <section class="login_content">
                <form:form commandName="user" action="login" method="post" id="login_form" name="login_form">
                    <h1>
                        <spring:message code="login.form.title"/><br/><br/>
                        <small><spring:message code="title.app_name"/></small>
                    </h1>
                    <form:hidden path="userId" value="0" userId="userId" name="userId"/>
                    <form:hidden path="authority" value="0" userId="authority" name="authority" />
                    <div>
                        <form:input path="username" id="username" name="username" cssErrorClass="error"
                                    type="text" cssClass="form-control" placeholder="Username" required="required"/>
                        <form:errors path="username" />
                    </div>
                    <div>
                        <form:input path="password" id="password" name="password" cssErrorClass="error"
                                    type="password" cssClass="form-control" placeholder="Password" required="required"/>
                        <form:errors path="password" />
                    </div>
                    <%--<div>--%>
                        <%--<input type="text" id="captcha" name="captcha" class="text" maxlength="10" />--%>
                        <%--<img id="captchaImage" src="captcha"/>--%>
                    <%--</div>--%>
                    <%--<script>--%>
                        <%--$('#captchaImage').click(function()--%>
                        <%--{--%>
                            <%--$('#captchaImage').attr("src", "captcha?timestamp=" + (new Date()).valueOf());--%>
                        <%--});--%>
                    <%--</script>--%>
                    <div>
                        <input id="submit" type="submit" class="btn btn-default submit"
                               value="<spring:message code="button.login"/>" tabindex="3">
                        <a id="to_register" href="register" style="display: none"></a>
                        <input type="button" class="btn btn-default submit" value="<spring:message code="button.register"/>"
                               onclick="document.getElementById('to_register').click();" tabindex="4">
                    </div>
                    <div class="clearfix"></div>
                    <div class="separator">
                        <div class="clearfix"></div>
                        <br />
                        <div>
                            <h1><i class="fa fa-paw"></i> SCU-H.L.D.</h1>
                            <p>©2017 All Rights Reserved. SCU-H.L.D.!</p>
                        </div>
                    </div>
                </form:form>
            </section>
        </div>
    </div>
</div>
</body>
</html>
