<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>check users</title>

  </head>
  
  <body>
	<table action="/NewsManage/servlet/UserServlet?type1=check" id="myform" method="post" height='90' border='5'>
    	<tr bgcolor='#FF69B4'>
    		 <td>ID</td><td>用户类型</td><td>用户名</td><td>注册日期</td><td>帐号可用性</td> <td>切换帐号可用性</td>
    	</tr>
    	
    	<c:forEach items="${applicationScope.userList}" var="user">
  			<tr bgcolor='#FFFACD'>
  				<td><c:out value="${user.userId}" /></td>     
		   		<td><c:out value="${user.type}" /> </td>	
		   		<td><c:out value="${user.name}" /></td>     
		   		<td><c:out value="${user.registerDate}" /> </td>	
		   		<td><c:out value="${user.enable}" /></td>  
		   		<td>
					<c:choose>
					    <c:when test="${user.enable == 'use'}">
					        <a href="/NewsManage/servlet/UserServlet?type1=check&id=${user.userId}&currentPage=${currentPage}">停用</a> 
					    </c:when>
					    <c:when test="${user.enable == 'stop'}">
					        <a href="/NewsManage/servlet/UserServlet?type1=check&id=${user.userId}&currentPage=${currentPage}">启用</a>
					    </c:when>
					</c:choose>
		   		 </td>     
  			</tr>
  		</c:forEach>
    </table>
	
	<table>
		<tr>
     		<th colspan="4">当前为第${currentPage}页，共${totalPages}页</th> 	
     	</tr>		
 		<tr>
    		<th><a href="/NewsManage/servlet/UserServlet?type1=${type}&option=start">首页</a></th>
		    <th><a href="/NewsManage/servlet/UserServlet?type1=${type}&option=last" onclick="checkLast(${currentPage})">上一页</a></th>
		    <th><a href="/NewsManage/servlet/UserServlet?type1=${type}&option=next" onclick="checkNext(${currentPage},${totalPages})">下一页</a></th>
		    <th><a href="/NewsManage/servlet/UserServlet?type1=${type}&option=end">尾页</a></th>
  		</tr>
 	</table>
 	
 	<br>
 	<br>
 	<br>
 	<table align="center" bgcolor='#FF69B4'>
		<tr> <td><a href="/NewsManage/servlet/UserServlet?type1=home">回个人主页</a> </td> </tr>
	</table>  
  </body>
  <script type='text/javascript'>
  		

  		
		function checkLast(currentPage){
			if(currentPage == 1){
				alert("已经是第一页！");
				return false;
			}
			return true;
		}
		function checkNext(currentPage,totalPages){
			if(currentPage == totalPages){
				alert("已经是尾页！");
				return false;
			}
			return true;
		}
  </script>
</html>