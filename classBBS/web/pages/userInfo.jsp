<%--
  Created by IntelliJ IDEA.
  User: huwendi
  Date: 2017/6/12
  Time: ����2:32
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
                <li class="currentLink"><a href="userInfo.jsp" title="������Ϣ">������Ϣ</a></li>
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
                <li class="currentLink"><a href="userInfo.jsp" title="������Ϣ">������Ϣ</a></li>
                <li><a href="" title="����">����</a></li>
                <li>
                    <s:a action="UpdateUserIndex">
                        <s:param name="userID">${sessionScope.LogInUser.userId}</s:param>
                        �޸��û�����
                    </s:a>
                </li>
                <!--li><button class="currentLink">�޸��û�����</button></li>-->
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
    <h1>�û���Ϣ�޸�</h1>
    <div id="tpn"></div>
    <div id="intro">
        <table id="userInfoTable" width="90%" class="table">
                <tr>
                    <th>��ʵ����</th>
                    <td>${sessionScope.LogInUserInfo.userName}</td>
                </tr>
                <tr>
                    <th>�༶</th>
                    <td>${sessionScope.LogInUserInfo.userClass}</td>
                </tr>
                <tr>
                    <th>�ֻ���</th>
                    <td>${sessionScope.LogInUserInfo.userPhone}</td>
                </tr>
                <tr>
                    <th>����</th>
                    <td>${sessionScope.LogInUserInfo.userEmail}</td>
                </tr>
        </table>
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