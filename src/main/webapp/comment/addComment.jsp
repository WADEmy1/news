<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta charset="utf-8"></head>
  <body>
  	<form action="/NewsManage/servlet/CommentServlet?type=addComment" method="post">
		<table border="1" align="center" cellpadding="0" cellspacing="0">
			<tbody><tr><td>
					<textarea name="content" cols="60" rows="8" id="textarea" required></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" value="提交"></td>
				</tr></tbody>
		</table>
		<input type="hidden" name="newsId" id="newsId" value="${param.newsId}">
	 	<input type="hidden" name="currentPage" id="currentPage" value="${param.currentPage}">

	</form> 
	 	
  </body>
</html>
