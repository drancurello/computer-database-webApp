package com.excilys.computerDatabase.parser;

public class ParserInteger {

	public static int parserInt(String val) {
		int i = -1;
		if (val != null && val.matches("\\d+")) {
			i = Integer.parseInt(val);
		}
		return i;
	}
	
}
