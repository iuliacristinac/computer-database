package com.excilys.console;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.model.Company;
import com.excilys.model.Computer;

@Component
public class CommandService {
	
	private final String REGEX_LONG= "^\\d{1,10}$";
	
	@Autowired
	private ComputerDBClient client;	
	
	@Autowired
	private ConsoleService consoleService;
	
	public void execute(String command) {
		
		switch(command) {
			case "help": 
					showMenu();
					break;
			case "getAllComputers":
					getAllComputers();
					break;
			case "getByIdComputer":
					getByIdComputer();
					break;
			case "create":
					create();
					break;
			case "update":
					update();
					break;
			case "deleteComputer":
					deleteComputer();
					break;		
			case "getAllCompanies":
					getAllCompanies();
					break;
			case "getByIdCompany":
					getByIdCompany();
					break;
			case "deleteCompany":
					deleteCompany();
					break;
			case "exit":
					exit();
					break;
			default:
					defaultMethod();
					break;
		}
	}
	
	private void showMenu() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\nCommands : \n");
		stringBuilder.append("\t- getAllComputers \t -> display all the computers \n");
		stringBuilder.append("\t- getByIdComputer \t -> display the computer corresponding to the id \n");
		stringBuilder.append("\t- create \t\t -> create a computer \n");
		stringBuilder.append("\t- update \t\t -> update a computer corresponding to an id \n");
		stringBuilder.append("\t- deleteComputer \t -> delete a computer \n");
		stringBuilder.append("\t- getAllCompanies \t -> display all the companies \n");
		stringBuilder.append("\t- getByIdCompany \t -> display the company corresponding to the id \n");
		stringBuilder.append("\t- deleteCompany \t -> delete a company and the computers associated to the company \n");
		stringBuilder.append("\t- help  \t\t -> show available commands \n");
		stringBuilder.append("\t- exit  \t\t -> exit console \n");
		System.out.println(stringBuilder);
	}
	
	private void getAllComputers() {
		System.out.println(displayComputers(client.getWebservice().getAllComputers()));
	}
	
	private void getByIdComputer() {	
		System.out.println("Please insert computer's id : ");
		String id = consoleService.getScanner().nextLine().trim();
		if (validateId(id)) {
			System.out.println((client.getWebservice().getByIdComputer(Long.parseLong(id))));
		} else {
			System.out.println("Invalid id -> Display Action Abandoned");
		}
	}

	private void create() {
//		System.out.println("Please enter a computer name: ");
//		String name = consoleService.getScanner().nextLine().trim();
//		System.out.println("Would you like to enter an introduced date? (y/n)");
//		String answerIntroduced = consoleService.getScanner().nextLine().trim();
//		if(isPositiveAnswer(answerIntroduced)) {
//			System.out.println("Please entre the introduced date (format )");
//		}
		
	}
	
	private void update() {
			
	}
	
	private void deleteComputer() {
		System.out.println("Please insert computer's id : ");
		String id = consoleService.getScanner().nextLine().trim();
		if (validateId(id)) {
			client.getWebservice().deleteComputer(Long.parseLong(id));
		} else {
			System.out.println("Invalid id -> Delete Action Abandoned");
		}
	}
	
	private void getAllCompanies() {
		System.out.println(displayCompanies(client.getWebservice().getAllCompanies()));
	}
	
	private void getByIdCompany() {
		System.out.println("Please insert company's id : ");
		String id = consoleService.getScanner().nextLine().trim();
		if (validateId(id)) {
			System.out.println((client.getWebservice().getByIdCompany(Long.parseLong(id))));
		} else {
			System.out.println("Invalid id -> Display Action Abandoned");
		}
	}
	
	private void deleteCompany() {
		System.out.println("Please insert company's id : ");
		String id = consoleService.getScanner().nextLine().trim();
		if (validateId(id)) {
			client.getWebservice().deleteCompany(Long.parseLong(id));
		} else {
			System.out.println("Invalid id -> Delete Action Abandoned");
		}
	}
	
	private void exit() {
		System.out.println("Sad to see you go...:(");
		consoleService.getScanner().close();
	}
	
	private void defaultMethod() {
		System.out.println("The command is invalid.");
	}
	
	/* y/n Answer Methods */
	private boolean isPositiveAnswer(String answer) {
		boolean ans = false;
		switch(answer) {
			case "y":
				ans = true;
				break;
			default:
				break;
		}
		return ans;
	}
	
	/* Validation Methods */
	
	private boolean validateId(String id) {
		Pattern pattern = Pattern.compile(REGEX_LONG);
		Matcher matcher = pattern.matcher(id);
		return matcher.matches();
	}
	
	/* Display Methods */
	private StringBuilder displayComputers(List<Computer> list) {
		StringBuilder stringBuilder = new StringBuilder();
		for(Computer o : list){
			stringBuilder.append(o);
			stringBuilder.append("\n");
		}
		return stringBuilder;
	}
	
	private StringBuilder displayCompanies(List<Company> list) {
		StringBuilder stringBuilder = new StringBuilder();
		for(Company o : list){
			stringBuilder.append(o);
			stringBuilder.append("\n");
		}
		return stringBuilder;
	}
}
