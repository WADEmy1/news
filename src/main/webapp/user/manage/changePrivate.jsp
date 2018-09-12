<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head></head>
  
  <body>
  <form action="/NewsManage/servlet/UserServlet?type1=changePrivate2" method="post"  enctype="multipart/form-data">
	<table width="600" border="1" align="center" cellspacing="0" cellpadding="0">
		<tbody>
			<tr><td colspan="2">修改个人信息：</td></tr>		
			<tr><td>头像：</td>	
				<td><img src="${ sessionScope.user.headIconUrl}" height="100"/><input id="myFile" name="myFile" type="file" onchange="preview()"><br>
					<br>预览：<img id="myImage" height="100"/></td></tr>
			<c:if test="${sessionScope.user.type=='user'}" >
				<tr><td>性别：</td>	
					<td><select name="sex" id="sex">
							<option value="man">man</option>
							<option value="woman">woman</option>
						</select>
					</td></tr>
				<tr><td>爱好：</td>
					<td><input type="text" name="hobby" value="${requestScope.userinformation.hobby}"/></td>
				</tr> 
			</c:if>	
			<tr><td colspan="2"><input type="submit" value="提交"/></td></tr>					
		</tbody>
	</table> 
  </form>
  <br>
 	<br>
 	<br>
  <table align="center" bgcolor='#FF69B4'>
		<tr> <td><a href="/NewsManage/servlet/UserServlet?type1=home">回个人主页</a> </td> </tr>
	</table>				   
  </body>
</html>

	 <script language=javascript>
	 	preview();
		function preview() {
		 	var preview = document.getElementById("myImage");
		 	var file  = document.getElementById("myFile").files[0];
		 	var reader = new FileReader();
		 	reader.onloadend = function () {
		  		preview.src = reader.result;
		 	}
		 	
			if (file) 
			  	reader.readAsDataURL(file);
			else 
			  	preview.src = "";
			
			var sex = document.getElementById("sex");
			for (var i = 0; i < sex.options.length; i++) {
	            if (sex.options[i].value == "${requestScope.userinformation.sex}") {
	                sex.options[i].selected = true;
	                break;
	            }
	        }			
		}
		
	</script> 
