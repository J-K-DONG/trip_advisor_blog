<%@ page language="java" import="java.util.*" 
	pageEncoding="utf-8"%>
	
	

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5 Transitional//EN">

<html>
<head>
<title>My JSP 'success.jsp' starting page</title>
</head>
<body>
	${xiaoxi}<br>
	<a href="SearchallServlet">查看所有用户</a>
</body>
</html>