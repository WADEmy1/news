<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="tool.WebProperties" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!doctype html>
<html>
  <head></head>
  <body>
	<table width="600" align="center" border="1">
		<tbody>
			<tr><td>标题：${requestScope.news.caption}</td>
			<tr><td>作者：${requestScope.news.author}&nbsp;	&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
					发表时间：${requestScope.news.newsTime}</td>
			</tr>
			<tr>
				<td>${requestScope.news.content}</td>
			</tr>
		</tbody>
	</table>
	<jsp:include page='<%="/servlet/CommentServlet?type=showComment"%>' flush="true" >
		<jsp:param name="newsId" value="${requestScope.news.newsId}" />
		<jsp:param name="currentPage" value="${currentPage}" />
		<jsp:param name="option" value="${param.option}" />
		<jsp:param name="currentPage1" value="${param.currentPage1}" />
	</jsp:include>
	<jsp:include page='/comment/addComment.jsp' flush="true" >
		<jsp:param name="newsId" value="${requestScope.news.newsId}" />
		<jsp:param name="currentPage" value="1" />
	</jsp:include>	
	<br>
 	<br>
 	<br>
 	<table align="center" bgcolor='#FF69B4'>
		<tr> <td><a href="/NewsManage/servlet/UserServlet?type1=home">回个人主页</a> </td> </tr>
	</table>
  </body>
</html>
