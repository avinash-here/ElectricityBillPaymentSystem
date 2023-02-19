package com.masai.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {	
	static final String url = "jdbc:mysql://localhost/electricityBillPaymentSystem";
	static final String username = "root";
	static final String password = "root";
	
	static {
		try {	
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	public static Connection getAConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return con;
	}

}
