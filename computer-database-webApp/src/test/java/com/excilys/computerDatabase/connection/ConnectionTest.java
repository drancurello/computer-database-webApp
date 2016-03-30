package com.excilys.computerDatabase.connection;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;
import org.junit.Test;

import com.excilys.computerDatabase.connection.ConnectionMySQL;
import com.excilys.computerDatabase.exceptions.ConnectionException;

public class ConnectionTest {

	@Test
	public void testConnection() throws SQLException, ConnectionException
	{
		Connection connection = ConnectionMySQL.getInstance().getConnection();
		
		assertNotNull(connection);
		
		ConnectionMySQL.CloseConnection(connection, null, null, null);
		
		assertTrue(connection.isClosed());
		
	} 
	 
	
}
