package com.excilys.computerDatabase.ui;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.computerDatabase.exceptions.ConnectionException;


public class Main {

	public static void main(String[] args) throws SQLException, NumberFormatException, ConnectionException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("contextUI.xml");
		
		Cli cli = (Cli) context.getBean("cli");
		
		cli.sessionCommand();
	}
}