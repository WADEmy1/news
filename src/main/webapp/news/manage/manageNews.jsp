<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
	  function deleteANews(id){
		ids1=document.getElementById("ids"); 
	  	ids1.value=id;
	  	//提交
	  	document.getElementById('myform').action="/NewsManage/servlet/NewsServlet?type1=deleteANews";
		document.getElementById('myform').submit();
	  }
	  	
	  function editsANews(id){
		ids1=document.getElementById("newsId"); 
	  	ids1.value=id;
	  	//提交
	  	document.getElementById('myform').action="/NewsManage/servlet/NewsServlet?type1=editANews";
		document.getElementById('myform').submit();
	  }	
	  
      
	</script>  
  </head>
  
  <body>
  	<form action="/NewsManage/servlet/NewsServlet?type1=newsManage" id="myform" method="post">
  	 <table align="center" border='1' >
	    <tr bgcolor='#FFACAC'>
	      <td>Id</td>
	      <td>标题</td><td>作者</td><td>日期</td>
	      <td>删除</td><td>编辑</td>
	    </tr>	    
	    <c:forEach items="${applicationScope.newsList}"  var="news">      
	   		<tr>
		   		<td>${news.newsId}</td>     
		   		<td>${news.caption}</td>	
		   		<td>${news.author}</td>     
		   		<td>${news.newsTime}</td>
		   		<td><a href="javascript:void(0);" onclick="deleteANews(${news.newsId});">删除</a></td>
		   		<td><a href="javascript:void(0);" onclick="editsANews(${news.newsId});">编辑</a></td>	
		   	</tr>
		</c:forEach> 	    
	</table>
	 
	<table align="center" border='1'>
		<tr>
     		<th colspan="4">当前为第${currentPage}页，共${totalPages}页</th> 	
     	</tr>		
 		<tr>
    		<th><a href="/NewsManage/servlet/NewsServlet?type1=${type}&option=start">首页</a></th>
		    <th><a href="/NewsManage/servlet/NewsServlet?type1=${type}&option=last" onclick="checkLast(${currentPage})">上一页</a></th>
		    <th><a href="/NewsManage/servlet/NewsServlet?type1=${type}&option=next" onclick="checkNext(${currentPage},${totalPages})">下一页</a></th>
		    <th><a href="/NewsManage/servlet/NewsServlet?type1=${type}&option=end">尾页</a></th>
  		</tr>
 	</table>
 	<input type="hidden" name="ids" id="ids" value="">
 	<input type="hidden" name="newsId" id="newsId" value="">
 	<br>
 	<br>
 	<br>
 	<table align="center" bgcolor='#FF69B4'>
		<tr> <td><a href="/NewsManage/servlet/UserServlet?type1=home">回个人主页</a> </td> </tr>
	</table>
  </form>
  </body>
</html>
