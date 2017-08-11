<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<script type="text/javascript">
    function onOK() {
        var account = document.getElementById("account");
        if(account.value == ""){
            swal("请输入账户!")
            account.focus();
            return;
        }
        document.passwordBackForm.submit();
    }
</script>
<head>
    <meta charset="UTF-8">
    <title>找回密码</title>
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
    var message = "${requestScope.PasswordBackAccountWrong}";
    if(message != "")
        sweetAlert("Error", message, "error");
</script>
<div id="login">
    <h1>找回密码</h1>
    <s:form name="passwordBackForm" method="post" action="PasswordBack">
        <h3>输入您的账号：</h3>
        <s:textfield name="user.account" id="account"/><br>
        <button class="but" type="button" onclick="onOK()">确认</button>
    </s:form>
    <s:form action="LogInIndex">
        <center><button class = "but" type="submit">返回登陆界面</button></center>
    </s:form>
</div>

</body>
</html>