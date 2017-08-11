<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: huwendi
  Date: 2017/6/5
  Time: 下午6:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<script type="text/javascript">
    function onOk() {
        var password = document.myform.password;
        if(password.value == ""){
            swal("请输入新密码!");
            password.focus();
            return;
        }
        var retype_password = document.getElementById("retype_password");
        if(password.value != retype_password.value){
            swal("两次输入的密码不相同，请检查!");
            return;
        }
        document.myform.submit();
    }
</script>
<head>
    <title>修改密码</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/images/login.css'/>"/>
    <script src="<c:url value='/images/sweetalert-master/dist/sweetalert.min.js'/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/images/sweetalert-master/dist/sweetalert.css'/>">
</head>
<body>
<div style="text-align:center">
    <div id="login">
        <h1>设置新密码</h1>
    <s:form name="myform" action="UpdatePassword">
        <h2>请输入新密码：</h2>
        <s:textfield name="user.password" id="password" class="textLong" placeholder="输入新密码"/><br>
        <h2>请再次输入新密码</h2>
        <s:textfield name="retype_password" id="retype_password" size="25" maxLength="40" class="textLong" placeholder="确认新密码"/><br>
        <button class="but" type="button" onclick="onOk()">修改</button>
    </s:form>
    </div>
</div>
</body>
</html>
