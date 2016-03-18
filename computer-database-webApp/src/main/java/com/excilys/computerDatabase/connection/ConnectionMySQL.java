package com.excilys.computerDatabase.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * The Class ConnectionMySQL.
 */
public class ConnectionMySQL {

	/** The connection */
	private static Connection conn = null;

	/**
	 * Gets the single instance of ConnectionMySQL.
	 *
	 * @return single instance of ConnectionMySQL
	 */
	public static Connection getInstance() {
		
			Properties properties = new Properties();

			try {
				Class.forName("com.mysql.jdbc.Driver");
				InputStream input = ConnectionMySQL.class.getClassLoader().getResourceAsStream("db_config.properties");
				
				properties.load(input);
				conn = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		return conn;
	}

	/**
	 * Close connection.
	 *
	 * @param result a ResultSet
	 * @param stmt a Statement
	 * @param prstmt a preparedStatement
	 */
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
