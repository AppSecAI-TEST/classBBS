<%--
  Created by IntelliJ IDEA.
  User: huwendi
  Date: 2017/6/5
  Time: ����7:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<script type="text/javascript">
    function onOK() {
        var account1 = document.getElementById("account1")
        var account2 = document.getElementById("account2");
        var nickName1 = document.getElementById("nickName1");
        var nickName2 = document.getElementById("nickName2");
        if ((account2.value == account1.innerHTML) && (nickName2.value == nickName1.innerHTML)) {
            swal("����δ�޸�, ���豣��!");
            return;
        }
        else{
            if (account2.value != account1.innerHTML)
                account2.value = account1.innerHTML;
            if (nickName2.value != nickName1.innerHTML)
                nickName2.value = nickName1.innerHTML;
            document.myform.submit();
        }
    }
    function answerQuestion() {
        var userID = document.getElementById("userID");
        var question = document.getElementById("question");
        var answer = document.getElementById("answer");
        swal({
            title : "�ܱ�������֤",
            text : "�ܱ�����: " + question.value,
            type : "input",
            showCancelButton: true,
            closeOnConfirm: false,
            animation: "slide-from-top",
            inputPlaceholder: "�������ܱ������!"
        },
            function (inputValue) {
            if(inputValue == "") {
                swal.showInputError("�������ܱ������");
            }
            else {
                if (inputValue == answer.value)
                    window.location = "updatePassword.jsp";
                else
                    swal.showInputError("�𰸴���!");
            }
        }
        );
    }
</script>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <script src="<c:url value='/images/sweetalert-master/dist/sweetalert.min.js'/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/images/sweetalert-master/dist/sweetalert.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/images/Main.css'/>"/>
    <title>Welcome!</title>
    <style type="text/css">
        body,table{
            font-size:12px;
        }
        table{
            table-layout:fixed;
            empty-cells:show;
            border-collapse: collapse;
            margin:0 auto;
        }
        td{
            height:30px;
        }
        h1,h2,h3{
            font-size:12px;
            margin:0;
            padding:0;
        }
        .table{
            border:1px solid #cad9ea;
            color:#666;
        }
        .table th {
            background-repeat:repeat-x;
            height:30px;
        }
        .table td,.table th{
            border:1px solid #cad9ea;
            padding:0 1em 0;
        }
        .table tr.alter{
            background-color:#f5fafe;
        }
    </style>
</head>
<body>
<div style="text-align:right">
    <s:a action="LogOut">�˳���¼</s:a>
</div>
<div id="navfirst">

    <s:iterator value="#session.UserAllClasses">
        <s:a action="PostInfo">
            <s:param name="classID"><s:property value="classId"/></s:param>
            <s:property value="className"/>
        </s:a>
    </s:iterator>

</div>
<div id="navsecond">
    <div id="course"><h2>�û�������ʾ</h2>
        <s:if test="%{#session.LogInUser.type != 'admin'}">
            <ul>
                <li><a href="userInfo.jsp" title="������Ϣ">������Ϣ</a></li>
                <li><a href="postList.jsp" title="����">����</a></li>
                <li class="currentLink">
                    <s:a action="UpdateUserIndex">
                        <s:param name="userID">${sessionScope.LogInUser.userId}</s:param>
                        �޸��û�����
                    </s:a>
                </li>
            </ul>
        </s:if>
        <s:else>
            <ul>
                <li><a href="userInfo.jsp" title="������Ϣ">������Ϣ</a></li>
                <li><a href="postList.jsp" title="����">����</a></li>
                <li class="currentLink">
                    <s:a action="UpdateUserIndex">
                        <s:param name="userID">${sessionScope.LogInUser.userId}</s:param>
                        �޸��û�����
                    </s:a>
                </li>
                <li>
                    <s:a action="GetAllUsers">
                        �����û�
                    </s:a>
                </li>
            </ul>
        </s:else>
    </div>
</div>
<div id="maincontent">
    <script>
        var message1 = "${requestScope.UpdateUserSuccess}";
        var message2 = "${requestScope.GetUserSuccess}";
        var message3 = "${requestScope.GetUserWrong}";
        if(message1 != "")
            sweetAlert("Success", message1, "success");
        if(message2 != "")
            sweetAlert("Success", message2, "success");
        if(message3 != "")
            sweetAlert("Error", message3, "error");
    </script>
    <h1>�û���Ϣ�޸�</h1>
    <div id="tpn"></div>
    <div id="intro">
        <s:form name="myform" action="UpdateUser">
            <table id="userInfoTable" width="90%" class="table">
                <input type="hidden" name="userID" value="${requestScope.UserBasicInfo.userId}">
                <tr>
                    <th>�˺�</th>
                    <td id="account1" contenteditable="true">${requestScope.UserBasicInfo.account}</td>
                </tr>
                <input id="account2"  type="hidden" value="${requestScope.UserBasicInfo.account}" name="account">
                <tr>
                    <th>����</th>
                    <td id="password"><input type="button" onclick="answerQuestion()" value="�޸�����"/></td>
                </tr>
                <tr>
                    <th>�ǳ�</th>
                    <td id="nickName1" contenteditable="true">${requestScope.UserBasicInfo.nickName}</td>
                </tr>
                <input id="nickName2" type="hidden" value="${requestScope.UserBasicInfo.nickName}" name="nickName">
                <tr>
                    <th>�ܱ�����</th>
                    <td>${requestScope.UserBasicInfo.question}</td>
                </tr>
                <input id="question" type="hidden" value="${requestScope.UserBasicInfo.question}" name="question">
                <input id="answer" type="hidden" value="${requestScope.UserBasicInfo.answer}" name="answer">
                <tr>
                    <th>����</th>
                    <td>${requestScope.UserBasicInfo.type}</td>
                </tr>
            </table>
            <button type="button" onclick="onOK()">����</button>
        </s:form>
    </div>
</div>
<div id="footer">
    <p id="p1">
        <span>˽����̳��������ţ�</span>
        <br />����ҳ���κη������⼰���ղ��е��κ����Ρ�
    </p>
    <p id="p2">
        ��ʹ�ñ�վʱ���������ѽ����˱�վ��ʹ���������˽�����Ȩ���У�����һ��Ȩ����
        �����ˣ����ĵѣ����ڡ�
    </p>
</div>
</body>
</html>