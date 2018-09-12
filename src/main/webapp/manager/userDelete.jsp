<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>delete users</title>
	<script type="text/javascript">
	
	  function checkAll1(obj){
	  	var checkboxs= document.getElementsByName("checkbox1");
	  	for (var i = 0; i < checkboxs.length; i++) 
	  		checkboxs[i].checked =obj.checked;	  
	  }
	
	  function deleteUsers(){
	  	var checkboxs= document.getElementsByName("checkbox1"); 
	  	var ids="";
	  	//拼接id为 ：1,2,
	   	for(i=0;i<checkboxs.length;i++){
        	if(checkboxs[i].checked == true)
            	ids+=checkboxs[i].value+",";            	
        }
		if(ids.length<1){
			alert("请选择至少一个需删除的元素！");
			return false;//阻止提交
		}
		ids=ids.substring(0,ids.length-1);//删除最后的逗号
		ids1=document.getElementById("ids"); 
	  	ids1.value=ids;
	  	//提交
    	document.getElementById('myform').submit();
	  }
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

  </head>
  
  <body>
  	<form action="/NewsManage/servlet/UserServlet?type1=delete" id="myform" method="post">
  	 <table bgcolor='#FF69B4'  border='4' >
	    <tr bgcolor='#FFACAC'>
	      <td><input id="checkboxAll" type='checkbox' onchange="checkAll1(this);"></td>
	      <td>Id</td>
	      <td>用户类型</td><td>用户名</td><td>注册日期</td><td>帐号可用性</td>
	    </tr>	    
	    <c:forEach items="${applicationScope.userList}"  var="user">      
	   		<tr bgcolor='#FFFACD'>
	   			<td><input name="checkbox1"  type="checkbox" value="${user.userId}"></td>
		   		<td><c:out value="${user.userId}" /></td>     
		   		<td><c:out value="${user.type}" /> </td>	
		   		<td><c:out value="${user.name}" /></td>     
		   		<td><c:out value="${user.registerDate}" /> </td>	
		   		<td><c:out value="${user.enable}" /></td>  
		   	</tr>
		</c:forEach> 	    
	</table>
	 <table>     
	   	<tr>	
	   		<td> <input type="button"  value="删除所选" onclick="deleteUsers();"></td>	
	   	</tr>	
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
	 <input type="hidden" name="ids" id="ids" value="">
	 
	 <br>
 	<br>
 	<br>
 	<table align="center" bgcolor='#FF69B4'>
		<tr> <td><a href="/NewsManage/servlet/UserServlet?type1=home">回个人主页</a> </td> </tr>
	</table>  
  </body>
</html>
