package com.excilys.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	public boolean validate(String date) {
		if (!date.trim().isEmpty()) {
			String regex = "(\\d{4}-\\d{2}-\\d{2})(\\s*)(\\d{2}:\\d{2}:\\d{2})";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(date);
			if (matcher.matches()) {
				return true;
			}
		}
		return false;
	}
}
