<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  </head>
  
  <body>
	<table width="600" border="1" align="center" cellspacing="0" cellpadding="0">
		<tbody>
			<tr><td colspan="2">个人信息：</td></tr>
			<tr><td>用户类型：</td>
				<td>${ sessionScope.user.type}</td>
			</tr>			
			<tr><td>用户名：</td>
				<td>${ sessionScope.user.name}</td>
			</tr>			
			<tr><td>头像：</td>	
				<td><img src="${ sessionScope.user.headIconUrl}" height="100"/></td></tr>
			<tr><td>注册日期：</td>
				<td>${ sessionScope.user.registerDate}</td>
			</tr> 
			<c:if test="${sessionScope.user.type=='user'}" >
				<tr><td>性别：</td>	
					<td>${ requestScope.userinformation.sex}</td></tr>
				<tr><td>爱好：</td>
					<td>${ requestScope.userinformation.hobby}</td>
				</tr> 
			</c:if>					
		</tbody>
	</table> 	
	<br>
 	<br>
 	<br>
	<table align="center" bgcolor='#FF69B4'>
		<tr> <td><a href="/NewsManage/servlet/UserServlet?type1=home">回个人主页</a> </td> </tr>
	</table>			   
  </body>
</html>
