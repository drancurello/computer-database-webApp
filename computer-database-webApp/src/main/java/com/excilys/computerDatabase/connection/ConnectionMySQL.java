package com.excilys.computerDatabase.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionMySQL {

	private static Connection conn = null;

	public static Connection getInstance() {
		
			Properties properties = new Properties();

			try {
				Class.forName("com.mysql.jdbc.Driver");
				InputStream input = ConnectionMySQL.class.getClassLoader().getResourceAsStream("db_config.properties");
				
				properties.load(input);
				conn = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
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
