<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

	<script type="text/javascript">
      	
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
       	function praise(commentId,newsId){
      		document.getElementById('myform').action="/NewsManage/servlet/CommentServlet?type=praise&"
      			+"&commentId="+commentId+"&newsId="+newsId;
      		document.getElementById('myform').submit();
      	}
      	
      	function model(commentId){
      		document.getElementById('myModel').innerHTML=
      			"<form action='/NewsManage/servlet/CommentServlet?type=addComment' method='post'> \
      				<table  bgcolor='#FF69B4' border='1' align='center' cellpadding='0' cellspacing='0'>\
						<tbody><tr><td> \
								<textarea name='content' cols='60' rows='8' id='textarea' required></textarea></td>\
							</tr>\
							<tr>\
							<td><input type='submit' name='submit' id='submit' value='提交'>\
								<input type='submit' onclick='cancel();' value='取消'></td>\
						</tr></tbody>\
					</table>\
					<input type='hidden' name='newsId' id='newsId' value='${param.newsId}'>\
					<input type='hidden' name='currentPage' id='page' value='${param.currentPage}'>\
					<input type='hidden' name='commentId' id='commentId'>\
				</form>";
			document.getElementById('commentId').value=commentId;
      		document.getElementById('myModel').style.display="block";      		
      	}
      	
      	function cancel(){
      		document.getElementById('myModel').style.display="none"; 
      	}
      	
	</script>  
  </head>
  
  <body>
  	<form action="/NewsManage/servlet/NewsServlet?type1=showANews&newsId=${param.newsId}" id="myform" method="post">
  	 <table align="center" border='1' >
	    <tr bgcolor='#FFACAC'>
	      <td>评论：</td>
	    </tr>	    
	    <c:forEach items="${applicationScope.commentList}"  var="comment">      
	   		<tr><td>${comment.userName}于
	   			<fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${comment.time}" />发表：&nbsp;&nbsp;第${comment.stair}楼层<br>
	   				${comment.content}<br>
	   				赞（<a href="javascript:void(0);" onclick="praise(${comment.commentId},${param.newsId});">${comment.praise}</a>）
	   				<a href="javascript:void(0);" onclick="model(${comment.commentId});">回复</a>
	   		</td></tr>
		</c:forEach> 	    
	</table>
	<table align="center">
		<tr>
     		<th colspan="4">当前为第${currentPage}页，共${totalPages}页</th> 	
     	</tr>		
 		<tr>
    		<th><a href="/NewsManage/servlet/NewsServlet?type1=showANews&newsId=${param.newsId}&option=start">首页</a></th>
		    <th><a href="/NewsManage/servlet/NewsServlet?type1=showANews&newsId=${param.newsId}&option=last" onclick="checkLast(${currentPage})">上一页</a></th>
		    <th><a href="/NewsManage/servlet/NewsServlet?type1=showANews&newsId=${param.newsId}&option=next" onclick="checkNext(${currentPage},${totalPages})">下一页</a></th>
		    <th><a href="/NewsManage/servlet/NewsServlet?type1=showANews&newsId=${param.newsId}&option=end">尾页</a></th>
  		</tr>
  		
 	</table>
 	<input type='hidden' name='currentPage' id='page' value='${currentPage}'>
  </form>
  	<div id="myModel" class="model"></div>
  	
  	
  </body>

  
</html>
