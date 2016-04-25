package com.excilys.computerDatabase.validation.annotations.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.GenericValidator;

import com.excilys.computerDatabase.validation.annotations.DateFormat;

public class DateFormatValidator implements ConstraintValidator<DateFormat, String> {

	@Override
	public void initialize(DateFormat constraint) {}
	
	@Override
	public boolean isValid(String date, ConstraintValidatorContext context) {
		boolean bool = false;
		
		if (date == null || date.equals("") || GenericValidator.isDate(date, "yyyy-MM-dd", true) || GenericValidator.isDate(date, "dd-MM-yyyy", true)){
			bool = true;
		}
		
		return bool;
	}
	
}
