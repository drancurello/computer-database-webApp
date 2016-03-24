package com.excilys.computerDatabase.parser;

import java.time.LocalDate;

public class ParserToLocalDate {

	public static LocalDate parserLocalDate(String date) {
		
		if (date.isEmpty()) {
			return null;
		} else {
			return LocalDate.parse(date);
		}	
	}
	
}
