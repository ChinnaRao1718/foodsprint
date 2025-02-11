package com.foodsprint.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12761963";
	private static final String USERNAME = "sql12761963";
	private static final String PASSWORD = "nGrqDItvnl";
	
	public final static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		}
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;
		
	}

}
