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
            swal("�������˻���!");
            loginName.focus();
            return;
        }
        var password = document.myform.password;
        if(password.value == ""){
            swal("����������!");
            password.focus();
            return;
        }
        var retype_password = document.getElementById("retype_password");
        if(password.value != retype_password.value){
            swal("������������벻��ͬ������!");
            return;
        }
        var nickName = document.myform.nickName;
        if(nickName.value == ""){
            swal("�������ǳ�!");
            nickName.focus();
            return;
        }
        var question = document.getElementById("question");
        if(question.value == ""){
            swal("�������ܱ�����!");
            question.focus();
            return;
        }
        var answer = document.myform.answer;
        if(answer.value == ""){
            swal("�������!")
            answer.focus();
            return;
        }
        var type = document.myform.type;
        if(type.value == ""){
            swal("��ѡ��ע���˺�����!")
            type.focus();
            return;
        }
        document.myform.submit();
    }
</script>
<head>
    <title>�û�ע��ҳ��</title>
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
        <h1>���û�ע��</h1>
        <s:textfield name="user.account" id="account" type="text" placeholder="�����˻�����">�˻�</s:textfield><br>
        <s:textfield name="user.password" id="password" type="text" placeholder="��������">����</s:textfield><br>
        <s:textfield name="retype_password" id="retype_password" type="text" placeholder="�ٴ���������">ȷ��</s:textfield><br>
        <s:textfield name="user.nickName" id="nickName" type="text" placeholder="�����ǳ�">�ǳ�</s:textfield><br>
        <s:textfield name="user.question" id="question" type="text" placeholder="�����ܱ�����">����</s:textfield><br>
        <s:textfield name="user.answer" id="answer" type="text" placeholder="���������">��</s:textfield><br>
        <s:select name="user.type" id="type"
                  list="#{'':'ѡ��ע���˻�����','student':'ѧ��','teacher':'��ʦ','admin':'����Ա'}"/><br>
        <center><button class="but" type="button" onclick="onOK()">ע��</button></center>
    </s:form>
    <s:form action="LogInIndex">
        <center><button class = "but" type="submit">���ص�½����</button></center>
    </s:form>
</div>
</body>
</html>