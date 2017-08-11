<%--
  Created by IntelliJ IDEA.
  User: huwendi
  Date: 2017/6/12
  Time: ����3:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
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
                <li>
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
                <li>
                    <s:a action="UpdateUserIndex">
                        <s:param name="userID">${sessionScope.LogInUser.userId}</s:param>
                        �޸��û�����
                    </s:a>
                </li>
                <li class="currentLink">
                    <s:a action="GetAllUsers">
                        �����û�
                    </s:a>
                </li>
            </ul>
        </s:else>
    </div>
</div>
<div id="maincontent">
    <h1>�����û�</h1>
    <script>
        var message1 = "${requestScope.GetAllUsersSuccess}";
        var message2 = "${requestScope.GetAllUsersWrong}";
        if(message1 != "")
            sweetAlert("Success", message1, "success");
        if(message2 != "")
            sweetAlert("Error", message2, "error");
    </script>

    <div id="tpn"></div>
    <div id="intro">
        <s:form >
            <table id="userInfoTable" width="90%" class="table">
                <tr>
                    <th>�û�ID</th>
                    <th>�û��˺�</th>
                    <th>�û�����</th>
                    <th>�û��ǳ�</th>
                    <th>�ܱ�����</th>
                    <th>�����</th>
                    <th>�û�����</th>
                    <th>ɾ��</th>
                </tr>
                <s:iterator value="#request.AllUsers">
                    <!--
                    <tr>
                        <td id="userID1" contenteditable="true"><s:property value="userId"/></td>
                        <input id="userID2"  type="hidden" value="<s:property value="userId"/>" name="userID">
                        <td id="account1" contenteditable="true"><s:property value="account"/></td>
                        <input id="account2"  type="hidden" value="<s:property value="account"/>" name="account">
                        <td id="password1" contenteditable="true"><s:property value="password"/></td>
                        <input id="password2"  type="hidden" value="<s:property value="password"/>" name="password">
                        <td id="nickName1" contenteditable="true"><s:property value="nickName"/></td>
                        <input id="nickName2"  type="hidden" value="<s:property value="nickName"/>" name="nickName">
                        <td id="question1" contenteditable="true"><s:property value="question"/></td>
                        <input id="question2"  type="hidden" value="<s:property value="question"/>" name="question">
                        <td id="answer1" contenteditable="true"><s:property value="answer"/></td>
                        <input id="answer2"  type="hidden" value="<s:property value="answer"/>" name="answer">
                        <td id="type1" contenteditable="true"><s:property value="type"/></td>
                        <input id="type2"  type="hidden" value="<s:property value="type"/>" name="type">
                    </tr>
                    -->
                    <tr>
                        <!--<td><s:property value="userId"/></td>-->

                        <td>
                            <s:a action="UpdateUserIndex">
                                <s:param name="userID">
                                    <s:property value="userId"/>
                                </s:param>
                                <s:property value="userId"/>
                            </s:a>
                        </td>
                        <!--�鿴���޸��û���Ϣ-->
                        <td><s:property value="account"/></td>
                        <td><s:property value="password"/></td>
                        <td><s:property value="nickName"/></td>
                        <td><s:property value="question"/></td>
                        <td><s:property value="answer"/></td>
                        <td><s:property value="type"/></td>
                        <td>
                            <s:a action="DeleteUser">
                                <s:param name="userID">
                                    <s:property value="userId"/>
                                </s:param>
                                ɾ��
                            </s:a>
                        </td>
                        <!--ɾ���û�-->
                    </tr>
                </s:iterator>
            </table>
            <button type="button" onclick="onOK()">����</button>
        </s:form>
    </div>
<!--
    <script>
        function onOK() {
            var userID1 = document.getElementById("userID1");
            var userID2 = document.getElementById("userID2");
            var account1 = document.getElementById("account1");
            var account2 = document.getElementById("account2");
            var password1 = document.getElementById("password1");
            var password2 = document.getElementById("password2");
            var nickName1 = document.getElementById("nickName1");
            var nickName2 = document.getElementById("nickName2");
            var question1 = document.getElementById("question1");
            var question2 = document.getElementById("question2");
            var answer1 = document.getElementById("answer1");
            var answer2 = document.getElementById("answer2");
            var type1 = document.getElementById("type1");
            var type2 = document.getElementById("type2");

            swal(userID2.value + account2.value + password2.value + nickName2.value + question2.value + answer2.value + type2.value);
            /*
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
            */
        }
    </script>
-->
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
