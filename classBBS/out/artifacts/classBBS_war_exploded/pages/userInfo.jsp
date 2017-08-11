<%--
  Created by IntelliJ IDEA.
  User: huwendi
  Date: 2017/6/12
  Time: 下午2:32
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
    <s:a action="LogOut">退出登录</s:a>
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
    <div id="course"><h2>用户功能提示</h2>
        <s:if test="%{#session.LogInUser.type != 'admin'}">
            <ul>
                <li class="currentLink"><a href="userInfo.jsp" title="个人信息">个人信息</a></li>
                <li><a href="postList.jsp" title="发帖">发帖</a></li>
                <li>
                    <s:a action="UpdateUserIndex">
                        <s:param name="userID">${sessionScope.LogInUser.userId}</s:param>
                        修改用户资料
                    </s:a>
                </li>
            </ul>
        </s:if>
        <s:else>
            <ul>
                <li class="currentLink"><a href="userInfo.jsp" title="个人信息">个人信息</a></li>
                <li><a href="" title="发帖">发帖</a></li>
                <li>
                    <s:a action="UpdateUserIndex">
                        <s:param name="userID">${sessionScope.LogInUser.userId}</s:param>
                        修改用户资料
                    </s:a>
                </li>
                <!--li><button class="currentLink">修改用户资料</button></li>-->
                <li>
                    <s:a action="GetAllUsers">
                        管理用户
                    </s:a>
                </li>
            </ul>
        </s:else>
    </div>
</div>
<div id="maincontent">
    <h1>用户信息修改</h1>
    <div id="tpn"></div>
    <div id="intro">
        <table id="userInfoTable" width="90%" class="table">
                <tr>
                    <th>真实姓名</th>
                    <td>${sessionScope.LogInUserInfo.userName}</td>
                </tr>
                <tr>
                    <th>班级</th>
                    <td>${sessionScope.LogInUserInfo.userClass}</td>
                </tr>
                <tr>
                    <th>手机号</th>
                    <td>${sessionScope.LogInUserInfo.userPhone}</td>
                </tr>
                <tr>
                    <th>邮箱</th>
                    <td>${sessionScope.LogInUserInfo.userEmail}</td>
                </tr>
        </table>
    </div>
</div>
<div id="footer">
    <p id="p1">
        <span>私人论坛，请勿打扰！</span>
        <br />此网页对任何法律问题及风险不承担任何责任。
    </p>
    <p id="p2">
        当使用本站时，代表您已接受了本站的使用条款和隐私条款。版权所有，保留一切权利。
        制作人：胡文笛，方磊。
    </p>
</div>
</body>
</html>