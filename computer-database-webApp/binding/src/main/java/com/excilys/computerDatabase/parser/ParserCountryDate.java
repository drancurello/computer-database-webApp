package com.excilys.computerDatabase.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParserCountryDate {

	/** 
	 *convert a French date in English date 
	 *@param frenchDate
	 *@return englishDate
	 *@throws ParseException
	 */
	public static String toEnglishDate(String frenchDate) throws ParseException {
		DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
	    DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
	    
	    Date localDate = sourceFormat.parse(frenchDate);
	    String englishDate = targetFormat.format(localDate);
	    
	    return englishDate;
	}
	
	/**
	 * convert an English date in French date
	 * @param englishDate
	 * @return frenchDate
	 * @throws ParseException
	 */
	public static String toFrenchDate(String englishDate) throws ParseException {
		DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
	    DateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
	    
	    Date localDate = sourceFormat.parse(englishDate);
	    String frenchDate = targetFormat.format(localDate);
	    
	    return frenchDate;
	}
	
	
}
