package com.excilys.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	public boolean validate(String date) {
		if (!date.trim().isEmpty()) {
			String regex = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])(\\s*)([01]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(date);
			return (matcher.matches());
		}
		return false;
	}
}
