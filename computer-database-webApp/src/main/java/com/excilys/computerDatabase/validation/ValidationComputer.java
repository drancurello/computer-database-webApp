package com.excilys.computerDatabase.validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.page.Page;
import com.excilys.computerDatabase.service.CompanyService;

public class ValidationComputer {

	public static void nameValidation(String name) throws Exception {
		if(name.equals("")) {
			throw new Exception("Enter a name please");
		} else {
			if (name.length() < 2) {
				throw new Exception("Your name must be at least 2 characters long");
			}
			else {
				if (name.trim().length() == 0) {
					throw new Exception("Enter a valid name please");
				}
			}
		}
	}
	
	public static void introducedValidation(String introduced) throws Exception {
		if(!introduced.equals("")) {
			try {
				LocalDate.parse(introduced);			
			}
			catch(DateTimeParseException e) {
				throw new Exception("introduced is not a date");
			}
			
			if (!introduced.equals("") && (introduced.equals("1970-01-01") || LocalDate.parse(introduced).getYear() < 1970)) {
				throw new Exception("entered date is to old (can't be before 1970)");
			}
			
		}
	}
	
	public static void discontinuedValidation(String discontinued, String introduced) throws Exception {
		
		if(!discontinued.equals("")) {
			try {
				LocalDate.parse(discontinued);
			}
			catch(DateTimeParseException e) {
				throw new Exception("discontinued is not a date");
			}
		}	
		
		if (!discontinued.equals("") && (discontinued.equals("1970-01-01") || LocalDate.parse(discontinued).getYear() < 1970)) {
			throw new Exception("entered date is to old (can't be before 1970)");
		}
		
		if(!discontinued.equals("") && !introduced.equals("")) {
			if(LocalDate.parse(discontinued).isBefore(LocalDate.parse(introduced))) {
				throw new Exception("discontinued date must be after the introduced date");
			}
		}
	}
	
	public static void companyValidation(String id) throws Exception {
		
		try {
			int companyId = Integer.parseInt(id);
		} catch (Exception e) {
			throw new Exception("please enter a valid id");
		}
	}
	
	public static Page PageNumberValidation(Page page) {
		if (page.getPageNumber() > page.getNbPage()) {
			page.setPageNumber(page.getNbPage());
		} else {
			if (page.getPageNumber() < 1) {
				page.setPageNumber(1);
			}
		}
		return page;
	}
	
}
