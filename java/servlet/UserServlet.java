package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import bean.Userinformation;
import service.UserService;
import tool.Message;
import tool.SearchTool;

public class UserServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type1");
		if(type.equals("showUser")){
			UserService userService = new UserService(request);
			try {
				userService.getOnePage("showUser");
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("/NewsManage/manager/userShow.jsp");
		}
		else if(type.equals("home")){
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				if(user.getType().equals("user")){;
					response.sendRedirect("/NewsManage/user.jsp");
				}
				else if(user.getType().equals("manager")){
					response.sendRedirect("/NewsManage/manager.jsp");
				}
				else if(user.getType().equals("newsAuthor")){
					response.sendRedirect("/NewsManage/newsAuthor.jsp");
				}
			}
			else{
				response.sendRedirect("/NewsManage/user/login.html");
			}
		}
		else if(type.equals("search")){
			if(request.getParameter("option") == null){
				String sql = SearchTool.userSearch(request);
				request.getServletContext().setAttribute("sql",sql);
			}
			UserService userService = new UserService(request);
			try {
				userService.getOnePage("search");
			} catch (Exception e) {
				e.printStackTrace();
			}
			ArrayList<User> userList = new ArrayList<User>();
			userList = (ArrayList<User>) request.getServletContext().getAttribute("userList");
			if(userList.size() == 0){
				response.sendRedirect("/NewsManage/manager/blank.jsp");
			}
			else{
				response.sendRedirect("/NewsManage/manager/userShow.jsp");
			}
		}
		else if(type.equals("check")){
			UserService userService = new UserService(request);
			if(request.getParameter("id") != null){
				String id =request.getParameter("id");
					try {
						userService.changeEnable(id);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
			try {
				userService.getOnePage("check");
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("/NewsManage/manager/userCheck.jsp");		
		}
		else if(type.equals("delete")){
			UserService userService = new UserService(request);
			if(request.getParameter("ids") != null){
				String ids =request.getParameter("ids");
					try {
						userService.delete(ids);;
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
			try {
				userService.getOnePage("delete");
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("/NewsManage/manager/userDelete.jsp");
		}
		else if(type.equals("login")){
			UserService userService = new UserService(request);
			try {
				int result=userService.login();
				Message message = new Message();
				message.setResult(result);
				if(result==1){
					//if(request.getSession().getAttribute("originalUrl") !=null){
						//String originalUrl=(String)request.getSession().getAttribute("originalUrl");
						//request.getSession().removeAttribute("originalUrl");
						//message.setMessage("请先登录");
						//message.setRedirectUrl(originalUrl);
					//}
					//else{
						User user = (User) request.getSession().getAttribute("user");
						if(user.getType().equals("user")){
							message.setMessage("欢迎您，普通用户");
							message.setRedirectUrl("/NewsManage/user.jsp");
						}
						else if(user.getType().equals("manager")){
							message.setMessage("欢迎您，管理员");
							message.setRedirectUrl("/NewsManage/manager.jsp");
						}
						else if(user.getType().equals("newsAuthor")){
							message.setMessage("欢迎您，新闻发布者");
							message.setRedirectUrl("/NewsManage/newsAuthor.jsp");
						}
					//}
				}else if(result==0){
					message.setMessage("用户存在，但被停用");
					message.setRedirectUrl("/NewsManage/user/login.html");
				}else if(result==-1){
					message.setMessage("用户不存在或密码错误！ ");
					message.setRedirectUrl("/NewsManage/user/login.html");
				}
				request.setAttribute("message", message);
				getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(type.equals("register")){
			UserService userService = new UserService(request);
			int result = -1;
			try {
				result = userService.register();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Message message = new Message();
			message.setResult(result);
			if(result==1){
				message.setMessage("注册成功！");
				message.setRedirectUrl("/NewsManage/user/login.html");
			}else if(result==0){
				message.setMessage("ͬ该用户已存在！");
				message.setRedirectUrl("/NewsManage/user/register.html");
			}else{
				message.setMessage("注册失败,请来联系相关人员");
				message.setRedirectUrl("/NewsManage/user/register.html");
			}
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
		}
		else if(type.equals("changePassword")){
			UserService userService = new UserService(request);
			Message message = new Message();
			int result = userService.changePassword();
			if(result == 1){
				message.setMessage("修改成功！请登录");
				request.removeAttribute("user");
				message.setRedirectUrl("/NewsManage/user/login.html");
			}
			else{
				message.setMessage("原密码输入不正确");
				message.setRedirectUrl("/NewsManage/user/manage/changePassword.jsp");
			}
			
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);			
		}	
		else if(type.equals("showPrivate")){
			UserService userService = new UserService(request);
			User user=(User)request.getSession().getAttribute("user");
			if("user".equals(user.getType())){
				Userinformation userinformation=userService.getByUserId(user.getUserId());
				request.setAttribute("userinformation", userinformation);
			}			
			getServletContext().getRequestDispatcher("/user/manage/showPrivate.jsp").forward(request,response);		
		}
		else if(type.equals("changePrivate1")){
			UserService userService = new UserService(request);
			User user=(User)request.getSession().getAttribute("user");
			if("user".equals(user.getType())){
				Userinformation userinformation=userService.getByUserId(user.getUserId());
				request.setAttribute("userinformation", userinformation);
			}			
			getServletContext().getRequestDispatcher("/user/manage/changePrivate.jsp").forward(request,response);		
		}
		else if(type.equals("changePrivate2")){
			UserService userService = new UserService(request);
			Message message = new Message();
			User user=(User)request.getSession().getAttribute("user");
			if("user".equals(user.getType())){
				Userinformation userinformation=new Userinformation();
				userinformation.setUserId(user.getUserId());
				userinformation.setSex(request.getParameter("sex"));
				userinformation.setHobby(request.getParameter("hobby"));
			}
			Integer result=userService.updatePrivate(user, request);
			message.setResult(result);
			if(result==5){
				message.setMessage("修改个人信息成功！");	
				message.setRedirectUrl("/NewsManage/servlet/UserServlet?type1=showPrivate");
			}else if(result==0){
				message.setMessage("修改个人信息失败，请联系管理员");
				message.setRedirectUrl("/NewsManage/servlet/UserServlet?type1=showPrivate");
			}
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);			
		}		
		else if(type.equals("logout")){
			request.getSession().removeAttribute("user");
			getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);		
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
