package com.excilys.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DateFormatValidator implements ConstraintValidator<ValidDateFormat, String>{

	@Autowired
	private DateUtil dateUtil;
	
	@Override
	public void initialize(ValidDateFormat arg0) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) {
			return true;
		}
		
		if (dateUtil.isValidDate(value)) {
			value = dateUtil.convertToValidDate(value);
			return true;
		}
		
		return false;
	}
}
