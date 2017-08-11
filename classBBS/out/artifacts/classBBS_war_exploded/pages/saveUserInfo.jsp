<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: huwendi
  Date: 2017/6/2
  Time: ����3:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=GBK" language="java" %>
<html>
<script type="text/javascript">
    if (parent.frames.length > 0){
        top.location.replace(document.location);
    }
    function onOK() {
        var userClass = document.userInfoForm.userClass;
        if(userClass.value == ""){
            alert("��ѡ�����ڵİ༶!")
            userClass.focus();
            return;
        }
        var userName = document.getElementById("userName");
        if(userClass.value == ""){
            alert("�������û�����!");
            userName.focus();
            return;
        }
        var userPhone = document.getElementById("userPhone");
        if(userPhone.value == ""){
            alert("�������û��ֻ���!");
            userPhone.focus();
            return;
        }
        var userEmail = document.userInfoForm.userEmail;
        if(userEmail.value == ""){
            alert("�������û�Email!");
            userEmail.focus();
            return;
        }
        document.userInfoForm.submit();
    }
</script>
<head>
    <title>�û���Ϣ</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/images/login.css'/>"/>
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
    <center><s:form name="userInfoForm" method="post" action="SaveUserInfo">
        <s:select name="userInfo.userClass" id="userClass"
                  list="#{'':'ѡ��༶', 'XinJi141':'�ż�141��', 'XinJi142':'�ż�142��'}"/><br>
        <s:textfield name="userInfo.userName" id="userName" size="25" maxLength="40" class="textLong">��ʵ����</s:textfield><br>
        <s:textfield name="userInfo.userPhone" id="userPhone" size="25" maxLength="40" class="textLong">�ֻ���</s:textfield><br>
        <s:textfield name="userInfo.userEmail" id="userEmail" size="25" maxLength="40" class="textLong">��������</s:textfield><br>
        <button class="but" type="button" name="regBtn" value="ע��" onclick="onOK()"/>
        <!--<input type="submit" name="submit" value="ע��">-->
    </s:form></center>
</div>
</body>
</html>
