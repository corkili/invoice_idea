<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/4/8
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Successful</p><hr/>
<p>
    id: ${user.userId} <br/>
    username: ${user.username} <br/>
    password: ${user.password} <br/>
    authority: ${user.authority} <br/>
</p>
</body>
</html>
