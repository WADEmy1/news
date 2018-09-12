<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="tool.WebProperties" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!doctype html>
<html>
  <head>
	<script src='<%=WebProperties.propertiesMap.get("ueditConfigJs")%>'	type="text/javascript"></script>
	<script src='<%=WebProperties.propertiesMap.get("ueditJs")%>'	type="text/javascript"></script>
	<script src='<%=WebProperties.propertiesMap.get("ueditLang")%>'	type="text/javascript"></script>
  </head>
  
  <body>
<form action="/NewsManage/servlet/NewsServlet?type1=add" name="form1" method="post" onsubmit="return submit1()">
	<table width="600" align="center" border="1">
		<tbody>
			<tr><td>标题：</td><td>
					<input type="text" name="caption" id="caption"></td>
			</tr>
			<tr><td>类型：</td><td>
					<select name="newsType" id="newsType">
					<c:forEach items="${applicationScope.newsTypes}"  var="newsType">      
				   		<option value="${newsType.name}">${newsType.name}</option>
					</c:forEach>								
					</select>
				</td>
			</tr>
			<tr>
				<td>作者：</td>
				<td><input type="text" name="author" id="author"></td>
			</tr>
			<tr>
				<td>日期：</td>
				<td><input type="datetime-local" name="newsTime" id="newsTime"></td>
			</tr>
			<tr>
				<td colspan="2">    
					<div>
    					<script id="content" type="text/plain" style="width:800px;height:500px;"></script>
					</div>
	</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="添加新闻"></td>
			</tr>
		</tbody>
	</table>
	<br>
 	<br>
 	<br>
 	<table align="center" bgcolor='#FF69B4'>
		<tr> <td><a href="/NewsManage/servlet/UserServlet?type1=home">回个人主页</a> </td> </tr>
	</table>
</form>
	<script type="text/javascript">
	
	    //实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	    var ue = UE.getEditor('content');
	</script>


  </body>
</html>
