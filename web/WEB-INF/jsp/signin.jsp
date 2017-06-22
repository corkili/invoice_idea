<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/6/22
  Time: 14:42
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

    <title>注册</title>

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
                <form:form commandName="user" action="register" method="post" id="register_form" name="register_form">
                    <h1>
                        <spring:message code="register.form.title"/><br/><br/>
                        <small><spring:message code="title.app_name"/></small>
                    </h1>
                    <form:hidden path="userId" value="0" userId="userId" name="userId"/>
                    <form:hidden path="authority" value="0" userId="authority" name="authority" />
                    <div>
                        <label class="control-label left" for="username">
                            用户名<span class="required">*</span>
                        </label>
                        <form:input path="username" id="username" name="username" cssErrorClass="error"
                                    type="text" cssClass="form-control" placeholder="Username" required="required"/>
                        <form:errors path="username" />
                    </div>
                    <div>
                        <label class="control-label left" for="password">
                            密码<span class="required">*</span>
                        </label>
                        <form:input path="password" id="password" name="password" cssErrorClass="error"
                                    type="password" cssClass="form-control" placeholder="Password" required="required"/>
                        <form:errors path="password" />
                    </div>
                    <div>
                        <label class="control-label left" for="confirmPassword">
                            重复密码<span class="required">*</span>
                        </label>
                        <form:input path="confirmPassword" id="confirmPassword" name="confirmPassword" cssErrorClass="error"
                                    type="password" cssClass="form-control" placeholder="Confirm Password" required="required"/>
                        <form:errors path="confirmPassword" />
                    </div>

                    <div>
                        <label class="control-label left" for="name">
                            真实姓名<span class="required">*</span>
                        </label>
                        <form:input path="name" id="name" name="name" cssErrorClass="error"
                                    type="text" cssClass="form-control" placeholder="Real name" required="required"/>
                        <form:errors path="name"/>
                    </div>

                    <div>
                        <label class="control-label left" for="jobId">
                            工号<span class="required">*</span>
                        </label>
                        <form:input path="jobId" id="jobId" name="jobId" cssErrorClass="error"
                                    type="text" cssClass="form-control" placeholder="Job ID" required="required"/>
                        <form:errors path="jobId"/>
                    </div>

                    <div>
                        <label class="control-label left" for="phone">
                            手机号<span class="required">*</span>
                        </label>
                        <form:input path="phone" id="phone" name="phone" cssErrorClass="error"
                                    type="text" cssClass="form-control" placeholder="Phone" required="required"/>
                        <form:errors path="phone"/>
                    </div>

                    <div>
                        <label class="control-label left" for="email">
                            邮箱<span class="required">*</span>
                        </label>
                        <form:input path="email" id="email" name="email" cssErrorClass="error"
                                    type="text" cssClass="form-control" placeholder="Email" required="required"/>
                        <form:errors path="email"/>
                    </div>
                    <div>
                        <input id="submit" type="submit" class="btn btn-default submit"
                               value="<spring:message code="button.register"/>">
                        <input id="reset" type="reset" class="btn btn-default submit"
                               value="<spring:message code="button.reset"/>">
                    </div>

                    <div class="clearfix"></div>

                    <div class="separator">
                        <p class="change_link">Already a member ?
                            <a href="login" class="to_register"> Log in </a>
                        </p>
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

