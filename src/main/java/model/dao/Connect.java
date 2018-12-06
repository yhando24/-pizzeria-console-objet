package model.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class Connect {
			
	
	public static Connection getConnection() throws Exception
	{
		
		DriverManager.setLogWriter(new PrintWriter(System.out));
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		ResourceBundle rb = ResourceBundle.getBundle("properties.connect.properties");
		String url = rb.getString("url");;
				
		
		String passwd = rb.getString("passwd");
		String user = rb.getString("user");
		return DriverManager.getConnection(url, user, passwd);
	}
	
	
}
