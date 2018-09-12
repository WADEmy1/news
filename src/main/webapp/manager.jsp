<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>adminiatrator</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <br>
   <br>
   <br>
   <table align="center" border='5'  bgcolor='#FF69B4'>
     <tr> <td colspan='4' ><a href="/NewsManage/servlet/NewsServlet?type1=showNews" >浏览新闻</a> </td> </tr>
     <tr> <td colspan='4' ><a href="/NewsManage/servlet/UserServlet?type1=showUser">浏览用户</a></td> </tr>
     <tr> <td colspan='4' ><a href="/NewsManage/servlet/UserServlet?type1=check">审查用户</a> </td> </tr>
     <tr> <td colspan='4' ><a href="/NewsManage/manager/userSearch.jsp">查询用户</a></td> </tr>
     <tr> <td colspan='4' > <a href="/NewsManage/servlet/UserServlet?type1=delete">删除用户</a></td> </tr>
     <tr> <td colspan='4' > <a href="/NewsManage/servlet/NewsServlet?type1=manageNews">管理新闻</a></td> </tr>
     <tr> <td colspan='4' ><a href="/NewsManage/servlet/UserServlet?type1=logout">注销</a></td> </tr>
  </table>
  </body>
</html>

