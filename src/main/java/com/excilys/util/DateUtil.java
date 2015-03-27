package com.excilys.util;

public class DateUtil {

	public static String convertToValidDate( String date){
		
		if ( Validator.isValidDateHour(date)) {
			return date;
		}
		else {
				if (Validator.isValidDate(date)){
					return date + " " + "00:00:00";
			}
		}
		throw new IllegalArgumentException("ComputerMapperDTO - Invalid Date!");
	}
}
