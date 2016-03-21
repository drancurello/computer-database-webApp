package com.excilys.computerDatabase.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.excilys.computerDatabase.exceptions.DAOConfigurationException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * The Class ConnectionMySQL.
 */
public class ConnectionMySQL {

    private static final String PROPERTIES_FILE = "db_config.properties";
    private static final String PROPERTY_URL       = "url";
    private static final String PROPERTY_USER 	   = "user";
    private static final String PROPERTY_PASSWORD  = "password";
    
    private BoneCP connectionPool;
    
    private static ConnectionMySQL connectionMySQL;

	private ConnectionMySQL() throws DAOConfigurationException{
		
			Properties properties = new Properties();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				properties.load(ConnectionMySQL.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));
				BoneCPConfig config = new BoneCPConfig();

	            config.setJdbcUrl(properties.getProperty(PROPERTY_URL));
	            config.setUsername(properties.getProperty(PROPERTY_USER));
	            config.setPassword(properties.getProperty(PROPERTY_PASSWORD));

	            config.setMinConnectionsPerPartition(2);
	            config.setMaxConnectionsPerPartition(10);
	            config.setPartitionCount(2);

	            connectionPool = new BoneCP(config);		
			
			} catch (SQLException e) {
				throw new DAOConfigurationException("Pool configuration failed", e);
			} catch (IOException e) {
				throw new DAOConfigurationException("fail to load the properties file", e);
			} catch (ClassNotFoundException e) {
				throw new DAOConfigurationException("jdbc driver not found", e);
			}
	}
	
    public static ConnectionMySQL getInstance() throws DAOConfigurationException{
        if (connectionMySQL == null) {
            connectionMySQL = new ConnectionMySQL();
            return connectionMySQL;
        } else {
            return connectionMySQL;
        }
    }
	
    public Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

	/**
	 * Close connection.
	 *
	 * @param result a ResultSet
	 * @param stmt a Statement
	 * @param prstmt a preparedStatement
	 */
	public static void CloseConnection(Connection connection, ResultSet result, Statement stmt, PreparedStatement prstmt) {
		
		try {
			if (connection != null) {
				connection.close();
			}
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
