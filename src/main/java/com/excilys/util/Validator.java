package com.excilys.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	private static final String DATE_HOUR_REGEX = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])(\\s*)([01]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])";
	private static final String DATE_REGEX = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
	
	
	public static boolean isValidDateHour(String date) {
		String dateToTest = date.trim();
		if (!dateToTest.isEmpty()) {
			Pattern patternDateHour = Pattern.compile(DATE_HOUR_REGEX);
			Matcher matcherDateHour = patternDateHour.matcher(dateToTest);
			return (matcherDateHour.matches());
		}
		return false;
	}
	
	public static boolean isValidDate(String date) {
		String dateToTest = date.trim();
		if (!dateToTest.isEmpty()) {
			Pattern patternDate = Pattern.compile(DATE_REGEX);
			Matcher matcherDate = patternDate.matcher(dateToTest);
			return (matcherDate.matches());
		}
		return false;
	}
	
}
