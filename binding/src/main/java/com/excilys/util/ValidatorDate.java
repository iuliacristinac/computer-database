package com.excilys.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ValidatorDate {
	
	@Autowired
	MessageSource messageSource;
	
	public boolean isValidDateHour(String date) {	
		
		String dateToTest = date.trim();
		String dateHourFormat = messageSource.getMessage("date.DateHourFormat", null, LocaleContextHolder.getLocale());
		if (!dateToTest.isEmpty()) {
			Pattern patternDateHour = Pattern.compile(dateHourFormat);
			Matcher matcherDateHour = patternDateHour.matcher(dateToTest);
			return (matcherDateHour.matches());
		}
		return false;
	}
	
	public boolean isValidDate(String date) {
		String dateToTest = date.trim();
		String dateFormat = messageSource.getMessage("date.DateFormat", null, LocaleContextHolder.getLocale());
		if (!dateToTest.isEmpty()) {
			Pattern patternDate = Pattern.compile(dateFormat);
			Matcher matcherDate = patternDate.matcher(dateToTest);
			return (matcherDate.matches());
		}
		return false;
	}	
}
