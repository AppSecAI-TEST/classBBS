<%--
  Created by IntelliJ IDEA.
  User: huwendi
  Date: 2017/6/5
  Time: 上午11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
    function onOK() {
        var account = document.getElementById("printID");
        if(account.value == ""){
            swal("请输入答案!")
            account.focus();
            return;
        }
        document.questionAnswerForm.submit();
    }
</script>
<head>
    <title>注册详细信息</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/images/login.css'/>"/>
    <script src="<c:url value='/images/sweetalert-master/dist/sweetalert.min.js'/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/images/sweetalert-master/dist/sweetalert.css'/>">
    <style type="text/css">
        .errors {
            background-color:#FFCCCC;
            border:1px solid #CC0000;
            width:400px;
            margin-bottom:8px;
        }
        .errors li{
            list-style: none;
        }
    </style>
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
    var message = "${requestScope.QuestionAnswerWrong}";
    if(message != "")
        sweetAlert("Error", message, "error");
</script>
<div style="text-align:center">
    <div id="login">
        <h1>验证问题</h1>
        <s:form name="questionAnswerForm" method="post" action="QuestionAnswer">
            <h2>您的密保问题：</h2>
            <s:textfield name="user.question" disabled="true"/><br>
            <h2>您的答案</h2>
            <s:textfield name="answer" id="printID" type="text" placeholder="问题答案"/><br>
            <button class = "but" type="button" onclick="onOK()">验证</button>
        </s:form>
    </div>
</div>
</body>
</html>