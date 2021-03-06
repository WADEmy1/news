package dao;

import java.sql.SQLException;
import java.util.ArrayList;
//import tools.PageInformation;
//import tools.Tool;
import bean.User;
import tool.WebProperties;

public class UserDao {
	public ArrayList<User> getOnePage(String sql) throws Exception {
		
		ArrayList<User> userList = new ArrayList<User>();
		DatabaseDao databaseDao = new DatabaseDao();
		databaseDao.query(sql);
		while (databaseDao.next()) {
			User user=new User();
			user.setEnable(databaseDao.getString("enable"));
			user.setName(databaseDao.getString("name"));
			user.setRegisterDate(databaseDao.getTimestamp("registerDate"));
			user.setType(databaseDao.getString("type"));
			user.setUserId(databaseDao.getInt("userId"));
			userList.add(user);	
		}		
		return userList;
	}

	
	public boolean hasUser(User user) throws Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		String sql="select * from user where name='"+user.getName()+"'";
		databaseDao.query(sql);
		while(databaseDao.next()){
			return true;
		}
		return false;
	}
	
	public int register(User user) throws Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		user.setHeadIconUrl("\\"+WebProperties.propertiesMap.get("projectName")
			+WebProperties.propertiesMap.get("headIconFileDefault"));//默认头像
		String sql="insert into user(type,name,password,enable,headIconUrl) values('"+
		user.getType()+"','"+user.getName()+"','"+
		user.getPassword()+"','"+user.getEnable()+"','"+
		user.getHeadIconUrl().replace("\\", "\\\\")+"')";
	return databaseDao.update(sql);	
	}
	
	public Integer login(User user) throws SQLException, Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		String sql="select * from user where name='" + user.getName()+
				"' and password='"+ user.getPassword()+"'";
		databaseDao.query(sql);
		while(databaseDao.next()){
			String enable=databaseDao.getString("enable");
			if( ("use").equals(enable)  ){
				user.setType(databaseDao.getString("type"));
				user.setUserId(databaseDao.getInt("userId"));
				user.setHeadIconUrl(databaseDao.getString("headIconUrl"));
				user.setRegisterDate(databaseDao.getTimestamp("registerDate"));
				return 1;//可以登录
			}			
			return 0;//用户存在，但被停用
		}
		return -1;//该用户不存在或密码错误
	}	
	
	public int changePassword(User user,String newPassword) throws Exception{//修改密码
		DatabaseDao databaseDao=new DatabaseDao();
		String sql="update user set password ='"+newPassword+"' where name = '"+user.getName()+"'";
		return databaseDao.update(sql);
	}

	//切换用户的可用性
	public Integer changeEnable(String id)throws Exception{//查询失败返回-1
		DatabaseDao databaseDao=new DatabaseDao();
		String sql = "select * from user where userId in ("+id+")";
		databaseDao.query(sql);
		while (databaseDao.next()) {
			String enable=databaseDao.getString("enable");
			if("use".equals(enable))
				enable="stop";
			else
				enable="use";
			sql = "update user set enable='"+enable+"' where userId in ("+id+")";
			databaseDao.update(sql);
			return 1;
		}		
		return 0;
	}
	
	//删除多个用户
	public Integer deletes(String ids)throws Exception{//查询失败返回-1
		DatabaseDao databaseDao=new DatabaseDao();
		if(ids!=null && ids.length()>0){
			String sql = "delete from user where userId in ("+ids+")";
			return databaseDao.update(sql);
		}else
			return -1;
	}	
	public Integer updateHeadIcon(User user,DatabaseDao databaseDao)throws SQLException{//
		String sql = "update user set headIconUrl='"+user.getHeadIconUrl()+
				"' where userId ="+user.getUserId().toString();
		return databaseDao.update(sql.replace("\\", "\\\\"));

	}	
}
