package org.amdocs.com;

import java.sql.Connection;
import java.sql.DriverManager;



public class ConnectionBank {                                   
	static Connection con;
	public static Connection connect()  {                                   //function for creating connection with oracle database
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/orcl.iiht.tech";
			String username = "scott";
			String password = "tiger";
			con = DriverManager.getConnection(url, username, password);
		}
		catch(Exception e) {
			System.out.println("No Connection");
		}
		return con;
	}
	
}
