package com.excilys.console;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.webservice.IComputerDBWS;

@Component
public class CommandService {
	
	@Autowired
	private ComputerDBClient client;	
	
	@Autowired
	private ConsoleService consoleService;
	
	public void execute(String command) {
	
		IComputerDBWS ws = client.getWebservice();
		
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
		}
	}
	
	private void showMenu() {
		StringBuilder stringBuilder = new StringBuilder()
		.append("Commands : \n\n")
		.append("\t- getAllComputers : displays all the computers \n")
		.append("\t- getByIdComputer : displays the computer corresponding to the id \n")
		.append("\t- create : affiche l'ordinateur correspondant à l'id\n")
		.append("\t- update : affiche la compagnie correspondante à l'id\n")
		.append("\t- deleteComputer : affiche une page de computer\n")
		.append("\t- getAllCompanies : affiche une page de compagnie\n")
		.append("\t- getByIdCompany : supprime l'ordinateur correspondant à l'id\n")
		.append("\t- deleteCompany : cré un nouvel ordinateur\n")
		.append("\t- exit : arrêt du programme\n");
		
		System.out.println(stringBuilder);
	}
	
	private void getAllComputers() {
		System.out.println(client.getWebservice().getAllComputers());
	}
	
	private void getByIdComputer() {
		
		System.out.println("Please insert computer's id : ");
		String id = consoleService.getScanner().nextLine().trim();
		if (Pattern.matches( "\\d{6}", id)) {
			System.out.println((client.getWebservice().getByIdComputer(Long.parseLong(id))));
		} else {
			System.out.println("Invalid id");
		}
	}
	
	private void create() {
		
	}
}
