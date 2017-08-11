<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: huwendi
  Date: 2017/6/5
  Time: 下午6:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>跳转页面</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/images/login.css'/>"/>
    <script>
        function jump(){
            location='login.jsp';
        }
        setTimeout('jump()',3000);
    </script>
</head>
<body>
<div align="center">
    <h1>注册用户成功，3秒后返回登录页面！</h1>
    <p>Loading...</p>
</div>
</body>
</html>
