package utils;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connect {

	
	public static Connection getConnection() throws Exception
	{
//		
		DriverManager.setLogWriter(new PrintWriter(System.out));
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url = null;
		String user = null;
		String passwd = null;
		try{
			  Properties properties = new Properties();
		
			  FileInputStream tmpFile = new FileInputStream("src/main/resources/connect.properties");
			  properties.load(tmpFile);
			  url = properties.getProperty("url");
			  user = properties.getProperty("user");
			  passwd = properties.getProperty("passwd");
			}catch( Exception e){
			 System.out.println("FIchier non trouvé");
			}
	

		return DriverManager.getConnection(url, user, passwd);
	}
}
