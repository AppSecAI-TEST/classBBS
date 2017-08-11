<%--
  Created by IntelliJ IDEA.
  User: huwendi
  Date: 2017/6/9
  Time: ����2:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <link rel="stylesheet" type="text/css" href="<c:url value='/images/Main.css'/>" />
    <script src="<c:url value='/images/sweetalert-master/dist/sweetalert.min.js'/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/images/sweetalert-master/dist/sweetalert.css'/>">
    <title>Welcome!</title>
    <script type="text/javascript" src="<c:url value='/images/jquery-1.4.4.min.js'/>"></script>
    <script>
        var pageSize = 4;//ÿҳ��ʾ�ļ�¼����
        var curPage=0;
        var lastPage;
        var direct=0;
        var len;
        var page;
        $(document).ready(function(){
            len =$("#table tr").length;
            page=len % pageSize==0 ? len/pageSize : Math.floor(len/pageSize)+1;//���ݼ�¼����������ҳ��
            //  alert("page==="+page);
            curPage=1;
            displayPage(1);//��ʾ��һҳ
            $("#btn1").click(function(){
                curPage=1;
                displayPage();
            });
            $("#btn2").click(function(){
                direct=-1;
                displayPage();
            });
            $("#btn3").click(function(){
                direct=1;
                displayPage();
            });
            $("#btn4").click(function(){
                curPage=page;
                displayPage();
            });
        });
        //function nextPage(){
        //	var str = '[{ "firstName":"Bill" , "lastName":"Gates" },{ "firstName":"George" , "lastName":"Bush" },{ "firstName":"Thomas" , "lastName": "Carter" }]';
        //	var user = JSON.parse(str);
        //	console.log(user[0],user);
        //	var result = '<tr><td>'+user[0].firstName +'</td>'+
        //	'<td>'+user[1].firstName +'</td></tr>';
        //	$("#table").html("");
        //$("#table").append(result);
        //}
        function displayPage(){
            if((curPage <=1 && direct==-1) || (curPage >= page && direct==1)){
                direct=0;
                alert("�Ѿ��ǵ�һҳ�������һҳ��");
                return;
            }
            lastPage = curPage;
            curPage = (curPage + direct + len) % len;
            var begin=(curPage-1)*pageSize;//��ʼ��¼��
            var end = begin + pageSize;
            if(end > len ) end=len;
            $("#table tr").hide();
            $("#table tr").each(function(i){
                if(i>=begin && i<end)//��ʾ��pageҳ�ļ�¼
                    $(this).show();
            });
        }
        function switchClass() {
            var classId = document.getElementById();
        }
        function onOK() {
            var title1 = document.getElementById("title1")
            var title2 = document.getElementById("title2");
            var content1 = document.getElementById("content1");
            var content2 = document.getElementById("content2");
            title2.value = title1.innerHTML;
            content2.value = content1.innerHTML;
            if(title2.value == "" || content2.value == "")
                swal("���������ӱ������������!");
            else
                document.postForm.submit();
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
    <s:a action="LogOut">�˳���¼</s:a>
</div>

<div id="navfirst">
    <s:iterator value="#session.UserAllClasses">
        <s:a action="PostInfo">
            <s:param name="classID"><s:property value="classId"/></s:param>
            <s:property value="className"/>
        </s:a>
    </s:iterator>
    <!--�����Ӵ��ݿγ�ID�л���̳-->
</div>
<div id="navsecond">
    <div id="course"><h2>�û�������ʾ</h2>
        <s:if test="%{#session.LogInUser.type != 'admin'}">
            <ul>
                <li><a href="userInfo.jsp" title="������Ϣ">������Ϣ</a></li>
                <li class="currentLink"><a href="postList.jsp" title="����">����</a></li>
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
                <li class="currentLink"><a href="" title="����">����</a></li>
                <li>
                    <!--a href="updateUser.jsp" title="�޸��û�����">�޸��û�����</a>-->
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
        <h1>��ӭ����${requestScope.ClassName}��̳</h1>
    <script>
        var message1 = "${requestScope.SendPostSuccess}";
        var message2 = "${requestScope.SendPostWrong}";
        var message3 = "${requestScope.GetClassPostInfoWrong}";
        var message4 = "${requestScope.TopPostSuccess}";
        var message5 = "${requestScope.CancelTopSuccess}";
        var message6 = "${requestScope.CancelTopWrong}";
        var message7 = "${requestScope.DeletePostSuccess}";
        var message8 = "${requestScope.DeletePostWrong}";
        var message9 = "${requestScope.UpdateUserSuccess}";
        var message10 = "${requestScope.UpdateUserWrong}";
        var message11 = "${requestScope.DeleteUserSuccess}";
        var message12 = "${requestScope.DeleteUserWrong}";
        if(message1 != "")
            sweetAlert("Success", message1, "success");
        if(message2 != "")
            sweetAlert("Error", message2, "error");
        if(message3 != "")
            sweetAlert("Error", message3, "error");
        if(message4 != "")
            sweetAlert("Success", message4, "success");
        if(message5 != "")
            sweetAlert("Success", message5, "success");
        if(message6 != "")
            sweetAlert("Error", message6, "error");
        if(message7 != "")
            sweetAlert("Success", message7, "success");
        if(message8 != "")
            sweetAlert("Error", message8, "error");
        if(message9 != "")
            sweetAlert("Success", message9, "success");
        if(message10 != "")
            sweetAlert("Error", message10, "error");
        if(message11 != "")
            sweetAlert("Success", message11, "success");
        if(message12 != "")
            sweetAlert("Error", message12, "error");
    </script>

    <div id="tpn">
    </div>
    <div id="intro">
            <table width="90%" class="table" id = "table">
                <tr>
                    <s:if test="%{#session.LogInUser.type != 'student'}">
                        <th>ɾ��</th>
                    </s:if>
                    <th >���ӱ��</th>
                    <th >������ID</th>
                    <th width="30%">��������</th>
                    <th >��������</th>
                    <th >��������</th>
                    <th >�Ƿ��ö�</th>
                    <s:if test="%{#session.LogInUser.type != 'student'}">
                        <th>�ö�</th>
                        <th>ȡ���ö�</th>
                    </s:if>
                </tr>
                <s:iterator value="#request.UserClassPostInfo">
                    <tr>
                        <s:if test="%{#session.LogInUser.type != 'student'}">
                            <td>
                                <s:a action="DeletePost">
                                    <s:param name="deletePostID"><s:property value="postId"/></s:param>
                                    <s:param name="classID"><s:property value="classId"/></s:param>
                                    ɾ��
                                </s:a>
                            </td>
                        </s:if>
                        <!--��ѧ���û�����ɾ������-->
                        <td ><s:property value="postId"/></td>
                        <td><s:property value="userId"/></td>
                        <td>
                            <s:a action="SeePostIndex">
                            <s:param name="postID"><s:property value="postId"/></s:param>
                            <s:property value="title"/>
                            </s:a>
                        </td>
                        <td><s:property value="sendDate"/></td>
                        <td><s:property value="updateDate"/> </td>
                        <td><s:property value="isTop"/></td>

                        <s:if test="%{#session.LogInUser.type != 'student'}">
                            <td>
                                <s:a action="TopPost">
                                    <s:param name="topPostID"><s:property value="postId"/></s:param>
                                    <s:param name="classID"><s:property value="classId"/></s:param>
                                    �ö�
                                </s:a>
                            </td>
                            <td>
                                <s:a action="CancelTop">
                                    <s:param name="cancelTopPostID"><s:property value="postId"/></s:param>
                                    <s:param name="classID"><s:property value="classId"/></s:param>
                                    ȡ���ö�
                                </s:a>
                            </td>
                        </s:if>
                        <!--��ѧ���û������ö���ȡ������-->
                    </tr>
                </s:iterator>
            </table>
        <button type="submit" id="btn1">��ҳ</button>
        <button type="submit" id ="btn2">��һҳ</button>
        <button type="submit" id="btn3">��һҳ</button>
        <button type="submit" id="btn4">βҳ</button>
        <p>----------------------------------------------------------------------------------------------------------------------------------------------------</p>
        <br><table width = "90%" class = "table" >
        <tr>
            <td height="50px">��Ҫ����</td>
        </tr>
    </table>
        <s:form name="postForm" action="SendPost" method="POST">
            <table width="90%" class="table">
                <tr>
                    <td height="50px">����</td>
                    <td id="title1" height="50px" width = "80%" contentEditable="true" ></td>
                    <input id="title2" type="hidden" name="title">                                                   <!--���ӱ���-->
                </tr>
                <tr>
                    <td height="30px">������</td>
                    <td height="30px">${sessionScope.LogInUser.nickName}</td>
                    <input name="userID" type="hidden" value="${sessionScope.LogInUser.userId}"/>
                    <input name="classID" type="hidden" value="<s:property value="classID"/>"/>
                </tr>
                <tr>
                    <td height="150px">����</td>
                    <td id="content1" contentEditable="true"></td>
                    <input id="content2" type="hidden" name="content"/>                                              <!--��������-->
                </tr>
            </table>
            <button type="button" onclick = "onOK()">��������</button>
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