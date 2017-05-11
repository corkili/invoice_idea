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

    <title>Login</title>

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
    <a class="hiddenanchor" id="signup"></a>
    <a class="hiddenanchor" id="signin"></a>

    <div class="login_wrapper">
        <div class="animate form login_form">
            <section class="login_content">
                <form:form commandName="user" action="login" method="post">
                    <h1><spring:message code="login.form.title" /></h1>
                    <form:hidden path="userId" value="0" userId="userId" name="userId"/>
                    <form:hidden path="authority" value="0" userId="authority" name="authority" />
                    <div>
                        <form:input path="username" id="username" name="username" cssErrorClass="error"
                                    type="text" cssClass="form-control" placeholder="Username" required=""/>
                        <br/><form:errors path="username" />
                    </div>
                    <div>
                        <form:input path="password" id="password" name="password" cssErrorClass="error"
                                    type="password" cssClass="form-control" placeholder="Password" required=""/>
                        <br/><form:errors path="password" />
                    </div>
                    <div>
                        <input id="submit" type="submit" class="btn btn-default submit"
                               value="<spring:message code="button.login" /> " tabindex="3">
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
