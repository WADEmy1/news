package service;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import bean.Comment;
import dao.CommentDao;
import dao.DatabaseDao;
import tool.PageTool;
import tool.SqlTool;

public class CommentService {
	HttpServletRequest request;
	int currentPage;
	PageTool pageTool = new PageTool();
	CommentDao commentDao = new CommentDao();
	SqlTool sqlTool = new SqlTool();
	public CommentService(HttpServletRequest request) {
		this.request = request;
	}
	public void getOnePage(String type) throws Exception{
		sqlTool.getSql(pageTool,type,request);
		
		pageTool.setType(type);
		String option = request.getParameter("option");
		if(option==null || option.equals("")){
			currentPage=1;
			if(!request.getParameter("currentPage1").equals("")){
				currentPage=Integer.valueOf(request.getParameter("currentPage1"));
			}
		}
		else{
			currentPage = Integer.parseInt((String)request.getServletContext().getAttribute("currentPage"));
			if(option.equals("start")){
				currentPage=1;	
			}
			else if(option.equals("last")){
				if(currentPage == 1){
				}
				else{
					currentPage-=1;	
				}
			}
			else if(option.equals("next")){
				if(currentPage == pageTool.getTotalPages()){
				}
				else{
					currentPage+=1;	
				}
			}
			else if(option.equals("end")){
				
				currentPage = pageTool.getTotalPages();	
			}
		}
		pageTool.setCurrentPage(currentPage);
		ServletContext app = request.getServletContext();
		app.setAttribute("commentList", commentDao.getOnePage(sqlTool.getSql(pageTool,type, request)));
		if(pageTool.getTotalPages() == 0)
			app.setAttribute("totalPages",1);
		else
			app.setAttribute("totalPages",pageTool.getTotalPages());
		app.setAttribute("currentPage", currentPage);
		app.setAttribute("type", pageTool.getType());
	}
	public Integer addComment(Comment comment){
		CommentDao commentDao=new CommentDao();		
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			Integer stair=commentDao.getStairByNewsId(comment.getNewsId(),databaseDao);
			comment.setStair(stair);
			return commentDao.addComment(comment,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		} catch (Exception e) {
			e.printStackTrace();
			return -3;
		}
	}
	//对回复的回复
		public Integer addCommentToComment(Comment comment){
			CommentDao commentDao=new CommentDao();		
			try {
				DatabaseDao databaseDao=new DatabaseDao();
				Comment oldComment=commentDao.getById(comment.getCommentId());
				String s="reply&nbsp&nbsp"+oldComment.getStair()+"floor&nbsp;"
							+oldComment.getUserName()+"&nbsp; the message is<br>";
				comment.setContent(s+comment.getContent());
				Integer stair=commentDao.getStairByNewsId(comment.getNewsId(),databaseDao);
				comment.setStair(stair);
				return commentDao.addComment(comment,databaseDao);
			} catch (SQLException e) {
				e.printStackTrace();
				return -2;
			} catch (Exception e) {
				e.printStackTrace();
				return -3;
			}
		}
		public Integer paise(Integer commentId){
		try {
			CommentDao commentDao=new CommentDao();
			if(commentDao.paise(commentId)>0)
				return 1;//
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;//
		} catch (Exception e) {			
			e.printStackTrace();
			return -3;//
		}	
	}
}
