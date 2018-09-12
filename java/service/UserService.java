package service;

import java.io.File;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.User;
import bean.Userinformation;
import dao.DatabaseDao;
import dao.UserDao;
import dao.UserinformationDao;
import tool.PageTool;
import tool.SqlTool;
import tool.FileTool;
import tool.WebProperties;

public class UserService {
	HttpServletRequest request;
	int currentPage;
	PageTool pageTool = new PageTool();
	UserDao userDao = new UserDao();
	SqlTool sqlTool = new SqlTool();
	public UserService(HttpServletRequest request) {
		this.request = request;
	}
	public void getOnePage(String type) throws Exception{
		sqlTool.getSql(pageTool,type,request);
		
		pageTool.setType(type);
		if(request.getParameter("option")==null){
			currentPage=1;
			if(request.getParameter("currentPage") != null){
				currentPage=Integer.valueOf(request.getParameter("currentPage"));
			}
		}
		else{
			currentPage = Integer.parseInt((String) request.getServletContext().getAttribute("currentPage"));
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
		app.setAttribute("userList", userDao.getOnePage(sqlTool.getSql(pageTool,type, request)));
		app.setAttribute("totalPages",pageTool.getTotalPages());
		app.setAttribute("currentPage", currentPage);
		app.setAttribute("type", pageTool.getType());
	}
	public int login() throws SQLException, Exception{
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		int result = userDao.login(user);
		if(result == 1){
			//user.setPassword(null);//
			request.getSession().setAttribute("user", user);
		}
		return result;
	}
	public int register() throws Exception{
		User user = new User();	
		user.setType(request.getParameter("type"));
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		if(user.getType().equals("user"))
			user.setEnable("use");
		else
			user.setEnable("stop");		
		if(userDao.hasUser(user)){
			return 0;//用户名重复
		}else{//
			if(userDao.register(user)>0)
				return 1;	//注册成功
			else
				return -1;  //失败
		}
	}
	public int changePassword(){
		int result = 0;
		User user = (User) request.getSession().getAttribute("user");
		String password=user.getPassword();
		String oldPassword = request.getParameter("oldPassword");
		if(!password.equals(oldPassword))//
			result= -1;
		else{
			try {
				if(userDao.changePassword(user, request.getParameter("newPassword")) >0)
					result = 1;
					user.setPassword(request.getParameter("newPassword"));
					request.getSession().setAttribute("user", user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public void changeEnable(String id) throws Exception{
		userDao.changeEnable(id);
	}
	public void delete(String ids) throws Exception{
		userDao.deletes(ids);
	}
	
	public Userinformation getByUserId(Integer userId){
		Userinformation userinformation=null;
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			UserinformationDao userinformationDao=new UserinformationDao();
			userinformation=userinformationDao.getByUserId(userId,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userinformation;
	}
	public Integer updatePrivate(User user,HttpServletRequest request){	
		Integer result;
		try {
			Userinformation userinformation=new Userinformation();
			if("user".equals(user.getType())){
					userinformation.setUserId(user.getUserId());
			}
			String oldHeadIconUrl=user.getHeadIconUrl();
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// Configure a repository (to ensure a secure temp location is used)
			String fullPath=request.getServletContext()
					.getRealPath(WebProperties.propertiesMap.get("tempDir"));//获取相对路径的绝对路径
			File repository = new File(fullPath);
			factory.setRepository(repository);//设置临时文件存放的文件夹
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解析request，将其中各表单元素和上传文件提取出来
		
			List<FileItem> items = upload.parseRequest(request);//items存放各表单元素
			
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {//遍历表单元素
			    FileItem item = iter.next();

			    if (item.isFormField()) {//非上传文件表单元素			    	
			        if("sex".equals(item.getFieldName()))//获取表单元素的name属性
			        	userinformation.setSex(item.getString("UTF-8"));//获取表单元素的值（一般是value属性）
			        if("hobby".equals(item.getFieldName()))//获取表单元素的name属性
			        	userinformation.setHobby(item.getString("UTF-8"));//获取表单元素的值（一般是value属性）
			    } else {//上传文件		
			    	File uploadedFile ;
			    	String randomFileName;
			    	do{
			    		randomFileName=FileTool.getRandomFileNameByCurrentTime(item.getName());
			    		String full=request.getServletContext()
				    			.getRealPath(WebProperties.propertiesMap.get("headIconDirDefault")+"\\"
				    					+randomFileName);
			    		uploadedFile=new File(full);
			    	}while(uploadedFile.exists());//确保文件未存在
			    	
			        item.write(uploadedFile);//将临时文件转存为新文件保存
			        result=1;//表示上传文件成功
			        item.delete();//删除临时文件
			        result=2;//表示上传文件成功，且临时文件删除			        
			        user.setHeadIconUrl("\\"+WebProperties.propertiesMap.get("projectName")
							+WebProperties.propertiesMap.get("headIconDirDefault")
							+"\\"+randomFileName);
			    }
			}
			DatabaseDao databaseDao=new DatabaseDao();
			UserDao userDao=new UserDao();
			UserinformationDao userinformationDao=new UserinformationDao();
			
			//开始事务处理
			databaseDao.setAutoCommit(false);
			userDao.updateHeadIcon(user, databaseDao);//更新用户表的头像
			if("user".equals(user.getType())){
				userinformation.setUserId(user.getUserId());
				//普通用户有userinformation信息
				if(userinformationDao.hasUserId(user.getUserId(), databaseDao))
					userinformationDao.update(userinformation, databaseDao);//更新用户详细信息
				else
					userinformationDao.insert(userinformation, databaseDao);//插入新的用户详细信息
			}
			databaseDao.commit();
			databaseDao.setAutoCommit(true);
			result=3;//表示上传文件成功，临时文件删除，且路径保存到数据库成功
			
			if(oldHeadIconUrl.contains(FileTool.getFileName(
					WebProperties.propertiesMap.get("headIconFileDefault"))))
				result=5;////表示上传文件成功，临时文件删除，且路径保存到数据库成功，老的图片使用系统默认图片，不需要删除
			else//老的图片没有使用系统默认图片，需要删除
				if(FileTool.deleteFile(new File(FileTool.root.replace(
						"\\"+WebProperties.propertiesMap.get("projectName"), "")+oldHeadIconUrl)))
					result=5;////表示上传文件成功，临时文件删除，且路径保存到数据库成功，老的图片被删除
				else
					result=4;////表示上传文件成功，临时文件删除，且路径保存到数据库成功，老的图片无法被删除		
		}catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;
		}
		return  result;
	}	
}