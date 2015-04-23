package com.excilys.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=DateFormatValidator.class)
@Target({ElementType.FIELD})
public @interface ValidDateFormat {

    String message() default "{date.DateErrorMessage}";

	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default { };
}