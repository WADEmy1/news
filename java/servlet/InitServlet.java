package servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import bean.NewsType;
import tool.FileTool;
import tool.WebProperties;
import dao.DatabaseDao;
import service.NewsTypeService;

public class InitServlet extends HttpServlet {
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		ServletContext servletContext=conf.getServletContext();
		FileTool.root=servletContext.getRealPath("\\");		
		
		String fileDir=servletContext.getRealPath("\\WEB-INF\\web.properties");		
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
		    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		    .configure(params.properties().setFileName(fileDir));
		try
		{
			
		    Configuration config = builder.getConfiguration();

		    WebProperties.propertiesMap.put("projectRoot", 
		    		servletContext.getRealPath(config.getString("projectName")));
		    WebProperties.propertiesMap.put("projectName",config.getString("projectName"));		    
		    WebProperties.propertiesMap.put("tempDir",config.getString("tempDir"));
		    
		    WebProperties.propertiesMap.put("headIconFileDefault",config.getString("headIconFileDefault"));
		    WebProperties.propertiesMap.put("headIconDir",config.getString("headIconDir"));		    
		    WebProperties.propertiesMap.put("headIconDirDefault",config.getString("headIconDirDefault"));
		    WebProperties.propertiesMap.put("redirectTime",config.getString("redirectTime"));
  
		    WebProperties.propertiesMap.put("ueditConfigJs",config.getString("ueditConfigJs"));
		    WebProperties.propertiesMap.put("ueditJs",config.getString("ueditJs"));
		    WebProperties.propertiesMap.put("ueditLang",config.getString("ueditLang"));
		    
		    String dd=WebProperties.propertiesMap.get("ueditLang");

		    
		    NewsTypeService newsTypeService=new NewsTypeService();
		    List<NewsType> newsTypes=new ArrayList<NewsType>(); 
		    newsTypes=newsTypeService.getAll();
		    this.getServletContext().setAttribute("newsTypes", newsTypes);
		    
		}
		catch(ConfigurationException cex)
		{
			cex.printStackTrace();
		}		
	}

}

