package com.excilys.computerDatabase.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionMySQL {

	private static String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";

	private static String user = "admincdb";

	private static String password = "qwerty1234";

	private static Connection conn = null;

	public static Connection getInstance() {

			try {
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return conn;
	}

	public static void CloseConnection(ResultSet result, Statement stmt, PreparedStatement prstmt) {
		
		try {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (prstmt != null) {
				prstmt.close();
			}
		}
		catch(SQLException e) {
			
		}
		

	}

}
