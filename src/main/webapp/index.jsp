<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>

  </head>
  
  <body>
  <table border='5'  align="center">
	  <tr bgcolor='#FF69B4'> <td colspan='4' ><a href="/NewsManage/user/register.html">注册</a></td> </tr>
	  <tr bgcolor='#FF69B4'> <td colspan='4' ><a href="/NewsManage/user/login.html">登录</a> </td> </tr>
	  <tr bgcolor='#FF69B4'> <td colspan='4' >  <a href="/NewsManage/servlet/NewsServlet?type1=showNews">游客浏览新闻</a> </td> </tr>
  </table>
 
  </body>
</html>
