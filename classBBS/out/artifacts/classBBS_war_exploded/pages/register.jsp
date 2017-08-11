<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<html>
<script type="text/javascript">
    if (parent.frames.length > 0){
        top.location.replace(document.location);
    }
    function onOK() {
        var loginName = document.getElementById("account");
        if(loginName.value == "") {
            swal("请输入账户名!");
            loginName.focus();
            return;
        }
        var password = document.myform.password;
        if(password.value == ""){
            swal("请输入密码!");
            password.focus();
            return;
        }
        var retype_password = document.getElementById("retype_password");
        if(password.value != retype_password.value){
            swal("两次输入的密码不相同，请检查!");
            return;
        }
        var nickName = document.myform.nickName;
        if(nickName.value == ""){
            swal("请输入昵称!");
            nickName.focus();
            return;
        }
        var question = document.getElementById("question");
        if(question.value == ""){
            swal("请输入密保问题!");
            question.focus();
            return;
        }
        var answer = document.myform.answer;
        if(answer.value == ""){
            swal("请输入答案!")
            answer.focus();
            return;
        }
        var type = document.myform.type;
        if(type.value == ""){
            swal("请选择注册账号类型!")
            type.focus();
            return;
        }
        document.myform.submit();
    }
</script>
<head>
    <title>用户注册页面</title>
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
<div style="text-align:center">

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

    <script>
        var message1 = "${requestScope.RetypePasswordWrong}";
        var message2 = "${requestScope.HasAccountWrong}";
        var message3 = "${requestScope.HasNickNameWrong}";
        if(message1 != "")
            sweetAlert("Error", message1, "error");
        if(message2 != "")
            sweetAlert("Error", message2, "error");
        if(message3 != "")
            sweetAlert("Error", message3, "error");
    </script>
    <s:form name="myform" method="post" action="Register">
        <h1>新用户注册</h1>
        <s:textfield name="user.account" id="account" type="text" placeholder="输入账户名称">账户</s:textfield><br>
        <s:textfield name="user.password" id="password" type="text" placeholder="输入密码">密码</s:textfield><br>
        <s:textfield name="retype_password" id="retype_password" type="text" placeholder="再次输入密码">确认</s:textfield><br>
        <s:textfield name="user.nickName" id="nickName" type="text" placeholder="输入昵称">昵称</s:textfield><br>
        <s:textfield name="user.question" id="question" type="text" placeholder="设置密保问题">问题</s:textfield><br>
        <s:textfield name="user.answer" id="answer" type="text" placeholder="设置问题答案">答案</s:textfield><br>
        <s:select name="user.type" id="type"
                  list="#{'':'选择注册账户类型','student':'学生','teacher':'教师','admin':'管理员'}"/><br>
        <center><button class="but" type="button" onclick="onOK()">注册</button></center>
    </s:form>
    <s:form action="LogInIndex">
        <center><button class = "but" type="submit">返回登陆界面</button></center>
    </s:form>
</div>
</body>
</html>