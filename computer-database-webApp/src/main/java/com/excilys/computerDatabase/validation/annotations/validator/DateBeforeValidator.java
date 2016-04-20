package com.excilys.computerDatabase.validation.annotations.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.GenericValidator;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.validation.annotations.DateBefore;

public class DateBeforeValidator implements ConstraintValidator<DateBefore, ComputerDTO> {

	@Override
	public void initialize(DateBefore arg0) {}

	@Override
	public boolean isValid(ComputerDTO computerDTO, ConstraintValidatorContext arg1) {
		
		if(computerDTO.getIntroduced().equals("") || computerDTO.getIntroduced() == null || computerDTO.getDiscontinued().equals("") || computerDTO.getDiscontinued() == null ) {
			return true;
		} else {
			if (GenericValidator.isDate(computerDTO.getIntroduced(), "yyyy-MM-dd", true) && GenericValidator.isDate(computerDTO.getDiscontinued(), "yyyy-MM-dd", true)) {
				if (LocalDate.parse(computerDTO.getIntroduced()).isBefore(LocalDate.parse(computerDTO.getDiscontinued()))) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		}
	}

}
