package com.excilys.console.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.excilys.model.Company;
import com.excilys.model.Computer;

@Component
public class ConsoleUtil {
	
	private final String REGEX_ID= "^\\d{1,10}$";
	private final String REGEX_DATE= "(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])-((19|20)\\d\\d)";
	
	/* Validation Methods */
	
	public boolean isValidId(String id) {
		Pattern pattern = Pattern.compile(REGEX_ID);
		Matcher matcher = pattern.matcher(id);
		return matcher.matches();
	}
	
	public boolean isValidDate(String date) {
		Pattern pattern = Pattern.compile(REGEX_DATE);
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}
	
	/* Display Methods */
	public StringBuilder displayComputers(List<Computer> list) {
		StringBuilder stringBuilder = new StringBuilder();
		for(Computer o : list){
			stringBuilder.append(o);
			stringBuilder.append("\n");
		}
		return stringBuilder;
	}
	
	public StringBuilder displayCompanies(List<Company> list) {
		StringBuilder stringBuilder = new StringBuilder();
		for(Company o : list){
			stringBuilder.append(o);
			stringBuilder.append("\n");
		}
		return stringBuilder;
	}

	/* y/n Answer Methods */
	public boolean isPositiveAnswer(String answer) {
		boolean ans = false;
		if (answer.equals("y")) {
			ans = true;
		}
		return ans;
	}
}
