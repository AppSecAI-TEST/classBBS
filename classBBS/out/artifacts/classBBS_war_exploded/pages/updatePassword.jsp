<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: huwendi
  Date: 2017/6/5
  Time: ����6:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<script type="text/javascript">
    function onOk() {
        var password = document.myform.password;
        if(password.value == ""){
            swal("������������!");
            password.focus();
            return;
        }
        var retype_password = document.getElementById("retype_password");
        if(password.value != retype_password.value){
            swal("������������벻��ͬ������!");
            return;
        }
        document.myform.submit();
    }
</script>
<head>
    <title>�޸�����</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/images/login.css'/>"/>
    <script src="<c:url value='/images/sweetalert-master/dist/sweetalert.min.js'/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/images/sweetalert-master/dist/sweetalert.css'/>">
</head>
<body>
<div style="text-align:center">
    <div id="login">
        <h1>����������</h1>
    <s:form name="myform" action="UpdatePassword">
        <h2>�����������룺</h2>
        <s:textfield name="user.password" id="password" class="textLong" placeholder="����������"/><br>
        <h2>���ٴ�����������</h2>
        <s:textfield name="retype_password" id="retype_password" size="25" maxLength="40" class="textLong" placeholder="ȷ��������"/><br>
        <button class="but" type="button" onclick="onOk()">�޸�</button>
    </s:form>
    </div>
</div>
</body>
</html>
