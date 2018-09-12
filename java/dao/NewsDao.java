package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.News;

public class NewsDao {
	public Integer add(News news,DatabaseDao databaseDao) throws SQLException{
		if(news.getNewsId()!=null){
			String sql="insert into news(newsId,caption,author,newsType,content,newsTime) values("
					+news.getNewsId()+","
					+"'"+news.getCaption()+"','"
					+news.getAuthor()+"','"
					+news.getNewsType()+"','"
					+news.getContent()+"','"
					+news.getNewsTime()+"')";
			return databaseDao.update(sql);
		}
		else{
			String sql="insert into news(caption,author,newsType,content,newsTime) values("
					+"'"+news.getCaption()+"','"
					+news.getAuthor()+"','"
					+news.getNewsType()+"','"
					+news.getContent()+"','"
					+news.getNewsTime()+"')";
			return databaseDao.update(sql);
		}
	}
	
	public List<News> getOnePage(String sql) throws Exception{
		List<News> newses=new ArrayList<News>(); 
		DatabaseDao databaseDao=new DatabaseDao();
		databaseDao.query(sql);
		while (databaseDao.next()) {
			News news=new News();
			news.setNewsId(databaseDao.getInt("newsId"));
			news.setCaption(databaseDao.getString("caption"));
			news.setAuthor(databaseDao.getString("author"));
			news.setNewsType(databaseDao.getString("newsType"));
			news.setNewsTime(databaseDao.getLocalDateTime("newsTime"));
			news.setPublishTime(databaseDao.getTimestamp("publishTime"));
			newses.add(news);	
		}		
		return newses;
	}	
	
	public News getById(Integer newsId) throws SQLException,Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		News news=new News();
		String sql="select * from news where newsId="+newsId;
		databaseDao.query(sql);
		while (databaseDao.next()) {			
			news.setNewsId(databaseDao.getInt("newsId"));
			news.setCaption(databaseDao.getString("caption"));
			news.setAuthor(databaseDao.getString("author"));
			news.setNewsType(databaseDao.getString("newsType"));
			news.setContent(databaseDao.getString("content"));
			news.setNewsTime(databaseDao.getLocalDateTime("newsTime"));
			news.setPublishTime(databaseDao.getTimestamp("publishTime"));
		}	
		return news;
	}
	
	public Integer deletes(String ids)throws SQLException,Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		if(ids!=null && ids.length()>0){
			String sql1 = "delete from comment where newsId in ("+ids+")";//首先删除参照表关联的信息，有外键约束。
			databaseDao.update(sql1);
			String sql2 = "delete from news where newsId in ("+ids+")";
			return databaseDao.update(sql2);
		}else
			return -1;
	}
}
