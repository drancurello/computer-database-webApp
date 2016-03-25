package com.excilys.computerDatabase.parser;

import java.sql.Date;
import java.time.LocalDate;

public class ParserToLocalDate {

	public static LocalDate parserLocalDate(String date) {
		
		if (date.isEmpty()) {
			return null;
		} else {
			return LocalDate.parse(date);
		}	
	}
	
	public static LocalDate parserLocalDate(Date date) {
		
		if (date == null) {
			return null;
		}
			
		String dateSQL = date.toString();
		LocalDate localDate = LocalDate.parse(dateSQL);
		
		return localDate;	
	}
	
}
