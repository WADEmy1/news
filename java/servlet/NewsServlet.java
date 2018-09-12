package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.News;
import service.NewsService;
import tool.Message;
import tool.ServletTool;

public class NewsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type1");
		NewsService newsService=new NewsService(request);
		Message message=new Message();
		if(type.equals("showNews")){
			try {
				newsService.getOnePage("showNews");
			} catch (Exception e) {
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/news/newsShow.jsp").forward(request,response);
		}
		else if(type.equals("showANews")){
			News news=newsService.getNewsById();
			request.setAttribute("news", news);
			getServletContext().getRequestDispatcher("/news/aNewsShow.jsp").forward(request,response);
		}
		else if(type.equals("add")){
			News news=ServletTool.news(request);
			int result=newsService.add(news);
			message.setResult(result);
			if(result==1){
				message.setMessage("添加新闻成功！请添加新的新闻！");
				message.setRedirectUrl("/NewsManage/news/manage/addNews.jsp");
			}else if(result==0){
				message.setMessage("添加新闻失败！请联系管理员！");
				message.setRedirectUrl("/NewsManage/index.jsp");
			}
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
		}
		else if(type.equals("manageNews")){
			try {
				newsService.getOnePage("manageNews");
			} catch (Exception e) {
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/news/manage/manageNews.jsp").forward(request,response);
		}
		else if(type.equals("deleteANews")){
			try {
				newsService.delete();
				newsService.getOnePage("manageNews");
			} catch (Exception e) {
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/news/manage/manageNews.jsp").forward(request,response);
		}
		else if(type.equals("editANews")){
			News news=newsService.getNewsById();
			request.setAttribute("news", news);
			getServletContext().getRequestDispatcher("/news/manage/editANews.jsp").forward(request,response);
		}
		else if(type.equals("edit")){//修改新闻
			News news=ServletTool.news(request);
			newsService.delete();
			newsService.add(news);
			request.setAttribute("news", news);
			message.setMessage("修改新闻成功！");
			message.setRedirectUrl("/NewsManage/manager.jsp");
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
