import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Test;

import dao.CommentDao;
import dao.DatabaseDao;
import tool.PageTool;

public class First {
/*	static protected CommentDao commendao;
	static protected PageTool pageTool;
	static protected DatabaseDao databaseDao;
	//static protected SqlTool sqlTool;
	@BeforeClass
	static public void beforeClass() throws Exception {
		commendao = new CommentDao();
		pageTool = new PageTool();
		databaseDao = new DatabaseDao();
		//sqlTool = new SqlTool();
	}
	@AfterClass
	static public void AfterClass() throws Exception {
		databaseDao.close();
	}*/
	@Test
	public void testGetOnePage() throws Exception {
		CommentDao commendao = new CommentDao();
		PageTool pageTool = new PageTool();
		pageTool.setPageSize(2);
		String sql = "select * from comment limit 1,"+pageTool.getPageSize();
		//commendao.getOnePage(sql);
		assertEquals(commendao.getOnePage(sql).size(),2);				
	}
	@Test
	public void testGetStairByNewsId() throws Exception{
		CommentDao commendao = new CommentDao();
		DatabaseDao databaseDao = new DatabaseDao();
		assertEquals(commendao.getStairByNewsId(2, databaseDao),(Integer)4);
	}
	@Test 
	public void testGetStairById() throws Exception{
		CommentDao commendao = new CommentDao();
		assertNotNull(commendao.getById(1));
	}
}
