package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Comment;
import bean.User;
import service.CommentService;
import service.NewsService;
import tool.Message;
public class CommentServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type=request.getParameter("type");
		String newsId=request.getParameter("newsId");
		CommentService commentService=new CommentService(request);
		if(type.equals("showComment")){
			try {
				commentService.getOnePage("showComment");
			} catch (Exception e) {
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/comment/showComment.jsp").include(request,response);
		}else if(type.equals("praise")){//点赞
			String url;
			if(request.getSession().getAttribute("user")!=null){
				String commentId=request.getParameter("commentId");	
				commentService.paise(Integer.parseInt(commentId));
				url="/servlet/NewsServlet?type1=showANews&newsId="+newsId
						+"&currentPage1="+request.getParameter("currentPage");
				
			}
			else{
				url="/user/login.html";
			}
			getServletContext().getRequestDispatcher(url).forward(request,response);
		}else if(type.equals("addComment")){//评论
			String url;
			if(request.getSession().getAttribute("user")==null){
				url="/user/login.html";
			}
			else{
				Comment comment=new Comment();
				comment.setContent(request.getParameter("content"));
				comment.setNewsId(Integer.parseInt(newsId));
				User user=(User)request.getSession().getAttribute("user");
				comment.setUserName(user.getName());
				String commentId=request.getParameter("commentId");
				
				if(commentId==null || commentId.isEmpty())
					commentService.addComment(comment);//对新闻的回复
				else{
					comment.setCommentId(Integer.parseInt(commentId));
					commentService.addCommentToComment(comment);//对回复的回复
				}		
				url="/servlet/NewsServlet?type1=showANews&newsId="+newsId;	
			}
			getServletContext().getRequestDispatcher(url).forward(request,response);			
		}

	}

}

