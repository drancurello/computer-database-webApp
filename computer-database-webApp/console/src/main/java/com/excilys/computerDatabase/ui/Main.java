package com.excilys.computerDatabase.ui;

import java.sql.SQLException;

import com.excilys.computerDatabase.exceptions.ConnectionException;

public class Main {

	public static void main(String[] args) throws SQLException, NumberFormatException, ConnectionException {
		
		Cli.sessionCommand();
		
	}
}