package com.excilys.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DateUtil {
	
	@Autowired
	private ValidatorDate validator;

	public String convertToValidDate( String date){
		if ( validator.isValidDate(date)) {
			return date + " " + "00:00:00";
		}
		return date;
	}
	
	public boolean isValidDate( String date) {
		if ( validator.isValidDateHour(date) || validator.isValidDate(date)) {
			return true;
		}
		return false;
	}
}
