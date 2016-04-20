package com.excilys.computerDatabase.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import com.excilys.computerDatabase.validation.annotations.validator.DateBefore1970Validator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DateBefore1970Validator.class)
public @interface DateBefore1970 {
	String message() default "the date can't be before 1970-01-02";
	
	Class<?>[] groups() default {};
	
	Class<?>[] payload() default {};
}
