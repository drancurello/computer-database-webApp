package com.excilys.computerDatabase.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.excilys.computerDatabase.validation.annotations.validator.DateFormatValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DateFormatValidator.class)
public @interface DateFormat {

	String message() default "the date must be in the format yyyy-mm-dd";
	
	Class<?>[] groups() default {};
	
	Class<?>[] payload() default {};
	
}
