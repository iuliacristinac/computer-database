package com.excilys.console.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;

@Component
public class ConsoleUtil {
	
	@Autowired
	private MessageSource messageSource;
	
	private final String REGEX_ID= "^\\d{1,10}$";
	
	/* Validation Methods */
	public boolean isValidId(String id) {
		Pattern pattern = Pattern.compile(REGEX_ID);
		Matcher matcher = pattern.matcher(id);
		return matcher.matches();
	}
	public boolean isValidDate(String date) {
		String dateRegex = messageSource.getMessage("date.DateFormat", null, LocaleContextHolder.getLocale());
		Pattern pattern = Pattern.compile(dateRegex);
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}
	
	/* Display Methods */
	public StringBuilder displayComputers(List<ComputerDTO> list) {
		StringBuilder stringBuilder = new StringBuilder();
		for(ComputerDTO o : list){
			stringBuilder.append(o);
			stringBuilder.append("\n");
		}
		return stringBuilder;
	}
	public StringBuilder displayCompanies(List<CompanyDTO> list) {
		StringBuilder stringBuilder = new StringBuilder();
		for(CompanyDTO o : list){
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
