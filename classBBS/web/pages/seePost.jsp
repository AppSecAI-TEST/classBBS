<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: huwendi
  Date: 2017/6/11
  Time: 下午6:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <link rel="stylesheet" type="text/css" href="<c:url value='/images/Main.css'/>"/>
    <script src="<c:url value='/images/sweetalert-master/dist/sweetalert.min.js'/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/images/sweetalert-master/dist/sweetalert.css'/>">
    <title>Welcome!</title>
    <script>
        function onOK() {
            var content1 = document.getElementById("content1");
            var content2 = document.getElementById("content2");
            var postID = document.getElementById("postID");
            content2.value = content1.innerHTML;
            if(content2.value == "")
                swal("请输入回复帖子内容!");
            else
                document.contentForm.submit();
        }
    </script>
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
                <li><a href="userInfo.jsp" title="个人信息">个人信息</a></li>
                <li class="currentLink"><a href="postList.jsp" title="发帖">发帖</a></li>
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
                <li><a href="userInfo.jsp" title="个人信息">个人信息</a></li>
                <li class="currentLink"><a href="" title="发帖">发帖</a></li>
                <li>
                    <!--a href="updateUser.jsp" title="修改用户资料">修改用户资料</a>-->
                    <s:a action="UpdateUserIndex">
                        <s:param name="userID">${sessionScope.LogInUser.userId}</s:param>
                        修改用户资料
                    </s:a>
                </li>
                <li>
                    <s:a action="GetAllUsers">
                        管理用户
                    </s:a>
                </li>
            </ul>
        </s:else>
    </div>
</div>

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

<div id="maincontent">

    <h1>帖子信息</h1>

    <script>
        var message1 = "${requestScope.ReplyPostSuccess}";
        var message2 = "${requestScope.SeePostWrong}";
        var message3 = "${requestScope.ReplyPostWrong}";
        var message4 = "${requestScope.DeleteContentSuccess}";
        var message5 = "${requestScope.DeleteContentWrong}";
        if(message1 != "")
            sweetAlert("Success", message1, "success");
        if(message2 != "")
            sweetAlert("Error", message2, "error");
        if(message3 != "")
            sweetAlert("Error", message3, "error");
        if(message4 != "")
            sweetAlert("Success", message4, "success");
        if(message5 != "")
            sweetAlert("Error", message5, "error");
    </script>

    <div id="tpn">
    </div>

    <div id="intro">
        <table width="90%" class="table" id = "table">
            <tr>
                <th>帖子编号</th>
                <th width = "70%">${requestScope.PostInfo.postId}</th>
            </tr>
            <tr>
                <th>发送者昵称</th>
                <th width = "70%">${requestScope.SendUserNickName}</th>
            </tr>
            <tr>
                <th>标题</th>
                <td height="60px" width = "70%">${requestScope.PostInfo.title}</td>
            </tr>
        </table>
<br><br><br>
<table width="90%" class="table">
<s:iterator value="#request.PostContent">
    <tr>
        <th>回复人</th>
        <th width = "70%"><s:property value="replyUserId"/></th>
    </tr>
    <tr>
        <th>楼层</th>
        <th width = "70%"><s:property value="floor"/> </th>
    </tr>
    <tr>
        <th>内容</th>
        <td height="100px" width = "70%"><s:property value="content"/></td>
    </tr>
    <tr>
        <th>回复时间</th>
        <td height="70%"><s:property value="replyDate"/></td>
    </tr>
    <s:if test="%{#session.LogInUser.type != 'student'}">
        <tr>
            <th>删除回复</th>
            <th>
                <s:a action="DeleteContent">
                    <s:param name="deleteReplyID"><s:property value="replyId"/></s:param>
                    <s:param name="postID"><s:property value="postId"/></s:param>
                    删除
                </s:a>
            </th>
        </tr>
    </s:if>

</s:iterator>
</table>
    </div>
    <br>
    <s:form name="contentForm" action="ReplyPost" method="POST">
        <table width="90%" class="table">
            <tr>
                <th>内容</th>
                <td id="content1" width = "70%" height = "300px" contentEditable="true"></td>
                <input id="content2" type="hidden" name="content"/>
                <input id="postID" type="hidden" name="postID" value="${requestScope.PostInfo.postId}"/>
            </tr>
        </table>
        <button type="button" onclick = "onOK()">回复帖子</button>
    </s:form>
</div>
<div id = ""></div>

<div id="footer">
    <p id="p1">
        <span>私人论坛，请勿打扰！</span>
        <br />此网页对任何法律问题及风险不承担任何责任。
    </p>
    <p id="p2">
        当使用本站时，代表您已接受了本站的使用条款和隐私条款。版权所有，保留一切权利。
        制作人：胡文笛，方磊。
    </p>
    <a id ="Bottom"></a>
</div>

</body>
</html>
