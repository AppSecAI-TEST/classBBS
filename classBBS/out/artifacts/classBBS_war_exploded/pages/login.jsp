<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: huwendi
  Date: 2017/6/3
  Time: 下午2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<script type="text/javascript">
    function onOk() {
        var account = document.loginForm.account;
        if(account.value == ""){
            sweetAlert("Error", "请输入账号!", "error");
            account.focus();
            return;
        }
        var password = document.getElementById("password");
        if(password.value == ""){
            sweetAlert("Error", "请输入密码!", "error");
            password.focus();
            return;
        }
        document.loginForm.submit();
    }
</script>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/images/login.css'/>"/>

    <script src="<c:url value='/images/sweetalert-master/dist/sweetalert.min.js'/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/images/sweetalert-master/dist/sweetalert.css'/>">

</head>
<body>
<!--
<center><s:if test="hasActionErrors()">

    <div class="errors">
        <s:actionerror/>
    </div>

</s:if></center>
<center><s:if test="hasFieldErrors()">
    <div class="errors">
        <s:fielderror/>
    </div>
</s:if></center>
-->
<script>
    var message = "${requestScope.LogInErrorInfo}";
    if(message != "")
        sweetAlert("Error", message, "error");
</script>
<div id="login">
    <h1>Login</h1>
    <s:form name="loginForm" method="post" action="LogIn">
        <s:textfield name="user.account" id="account" size="25" maxLength="64" class="textLong">账号</s:textfield>
        <s:password name="user.password" id="password" size="25" maxLength="64" class="textLong">密码</s:password>
        <button class="but" type="button" onclick="onOk()">登录</button>
    </s:form>
    <s:form name="registerIndexForm" method="POST" action="RegisterIndex">
        <button class = "but" type="submit">新用户注册</button>
    </s:form>
    <s:form name="passwordBackIndexForm" method="POST" action="PasswordBackIndex">
        <button class="but" type="submit">找回密码</button>
    </s:form>
</div>
</body>
</html>