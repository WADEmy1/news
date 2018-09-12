<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  </head>
  
  <body>
	<form action="/NewsManage/servlet/UserServlet?type1=search" method="post">
		<table width="800" height="121" border="0" align="center" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td></td>
					<td>查询条件</td>
				</tr>
				<tr>
					<td align="right">用户类型：</td>
					<td><select name="type">
							<option value="all">全部</option>
							<option value="user">用户</option>
							<option value="newsAuthor">新闻发布员</option>
							<option value="manager">管理员</option>
					</select></td>
				</tr>			
				<tr>
					<td width="116" align="right">用户名：</td>
					<td width="684" align="left"><input type="text" name="name" id="name"><span id="namespan"></span></td>
				</tr>
				<tr>
					<td align="right">帐号可用性：</td>
					<td align="left">
						<select name="enable" id="select">
							<option value="all">全部</option>
							<option value="use">可用</option>
							<option value="stop">停用</option>
						</select>					
					<span id="passwordspan"></span></td>
				</tr>
				<tr>
					<td align="right">注册日期：</td>
					<td align="left">介于<input type="date" name="lowDate">与<input type="date" name="upDate">之间</td>
				</tr>
				<tr>
					<td></td><td><input type="submit" value="submit"/></td>
				</tr>
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
