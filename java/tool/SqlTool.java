package tool;

import javax.servlet.http.HttpServletRequest;

import dao.DatabaseDao;

public class SqlTool {
	public  String getSql(PageTool pageTool,String type,HttpServletRequest request) throws Exception{
		DatabaseDao databaseDao = new DatabaseDao();
		String sql="";
		String sql1="";
		if(type.equals("showUser")){	
			sql1 = "select * from `user`";	
		}
		else if(type.equals("search")){
			sql1 = "select * from `user` where " + request.getServletContext().getAttribute("sql");
		}
		else if(type.equals("check")){	
			sql1 = "select * from `user`";	
		}
		else if(type.equals("delete")){	
			sql1 = "select * from `user`";	
		}
		else if(type.equals("showNews")){
			sql1 = "select * from news";
		}
		else if(type.equals("showComment")){
			sql1 = "select * from comment where newsId= '"+request.getParameter("newsId")+"'";
		}
		else if(type.equals("manageNews")){
			sql1 = "select * from news";
		}
		databaseDao.query(sql1);
		//获取记录总数
		databaseDao.getRs().last();
		int rowCount= databaseDao.getRs().getRow();
		int totalPages=rowCount / pageTool.getPageSize();
		if(rowCount % pageTool.getPageSize() !=0){
			totalPages+=1;	
		}	
		pageTool.setTotalPages(totalPages);
		int startIndex =(pageTool.getCurrentPage()-1)*pageTool.getPageSize();
		sql = sql1 + " limit "+startIndex+","+pageTool.getPageSize();
		return sql;
	}
	
}
/*initDate();
startIndex=(currentPage-1)*pageSize;
String sql="select * from `user` limit "+startIndex+","+pageSize;*/