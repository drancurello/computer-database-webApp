package com.excilys.computerDatabase.validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.service.CompanyService;

public class ValidationComputer {

	public static void nameValidation(String name) throws Exception {
		if(name.equals("")) {
			throw new Exception("Enter a name please");
		} else {
			if (name.length() < 2) {
				throw new Exception("Enter a name of at least 2 characters");
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
		
		Company company = CompanyService.findCompany(Integer.parseInt(id));
		
		if(company.getName() == null) {
			throw new Exception("id entered did not match any company ");
		}
	}
	
}
