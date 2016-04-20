package com.excilys.computerDatabase.validation.annotations.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.GenericValidator;

import com.excilys.computerDatabase.validation.annotations.DateBefore1970;

public class DateBefore1970Validator implements ConstraintValidator<DateBefore1970, String> {

	@Override
	public void initialize(DateBefore1970 arg0) {}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext arg1) {
		if (date.equals("") || date == null) {
			return true;
		} else {
			if (GenericValidator.isDate(date, "yyyy-MM-dd", true)) {
				if (LocalDate.parse(date).getYear() < 1970 || date.equals("1970-01-01")) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		}
	}

	
	
}
