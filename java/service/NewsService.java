package service;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import bean.News;
import dao.DatabaseDao;
import dao.NewsDao;
import tool.PageTool;
import tool.SqlTool;

public class NewsService {
	HttpServletRequest request;
	int currentPage;
	PageTool pageTool = new PageTool();
	NewsDao newsDao = new NewsDao();
	SqlTool sqlTool = new SqlTool();
	public NewsService(HttpServletRequest request) {
		this.request = request;
	}
	public void getOnePage(String type) throws Exception{
		sqlTool.getSql(pageTool,type,request);
		
		pageTool.setType(type);
		if(request.getParameter("option")==null){
			currentPage=1;
			/*if(request.getParameter("currentPage") != null){
				currentPage=Integer.valueOf(request.getParameter("currentPage"));
			}*/
		}
		else{
			currentPage = Integer.parseInt((String)request.getServletContext().getAttribute("currentPage"));
			String option= request.getParameter("option");
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
		app.setAttribute("newsList", newsDao.getOnePage(sqlTool.getSql(pageTool,type, request)));
		app.setAttribute("totalPages",pageTool.getTotalPages());
		app.setAttribute("currentPage", currentPage);
		app.setAttribute("type", pageTool.getType());
	}
	public News getNewsById() {
		Integer newsId=Integer.parseInt(request.getParameter("newsId"));
		try {
			return newsDao.getById(newsId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Integer add(News news){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			NewsDao newsDao=new NewsDao();
			return newsDao.add( news, databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
	}
	public void delete(){
		try {
			newsDao.deletes(request.getParameter("ids"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
