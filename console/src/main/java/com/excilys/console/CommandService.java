package com.excilys.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.console.util.ConsoleUtil;
import com.excilys.dto.ComputerDTO;

@Component
public class CommandService {
	
	@Autowired
	private ComputerDBClient client;	
	@Autowired
	private ConsoleService consoleService;
	@Autowired
	private ConsoleUtil consoleUtil;
	
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
		System.out.println(consoleUtil.displayComputers(client.getWebservice().getAllComputers()));
	}
	
	private void getByIdComputer() {	
		System.out.println("Please insert computer's id : ");
		String id = consoleService.getScanner().nextLine().trim();
		if (consoleUtil.isValidId(id)) {
			System.out.println((client.getWebservice().getByIdComputer(Long.parseLong(id))));
		} else {
			System.out.println("Invalid id -> Display Action Abandoned");
		}
	}

	private void create() {
		System.out.println("Please enter a computer name: ");
		String name = consoleService.getScanner().nextLine().trim();
		String introducedDate = getIntoducedDate(null);
		String discontinuedDate = getDiscontinuedDate(null);
		long companyId = getCompanyId(0);
		String companyName = null;
		if(companyId > 0){
			companyName = getCompanyName(companyId);
		}
		client.getWebservice().create(new ComputerDTO(name,introducedDate, discontinuedDate, companyId, companyName));	
	}
	
	private void update() {
		System.out.println("Please insert computer's id : ");
		String id = consoleService.getScanner().nextLine().trim();
		ComputerDTO computerDTO;
		if (consoleUtil.isValidId(id)) {
			computerDTO = client.getWebservice().getByIdComputer(Long.parseLong(id));
			System.out.println(computerDTO);
			System.out.println("Would you like to enter a computer name? (y/n)");
			String answerName = consoleService.getScanner().nextLine().trim();
			String name = computerDTO.getName();
			if(consoleUtil.isPositiveAnswer(answerName)) {
				System.out.println("Please enter a computer name");
				name = consoleService.getScanner().nextLine().trim();	
			}
			String introducedDate = getIntoducedDate(computerDTO.getIntroduced());
			String discontinuedDate = getDiscontinuedDate(computerDTO.getDiscontinued());
			long companyId = getCompanyId(computerDTO.getCompanyId());
			String companyName = null;
			if(companyId > 0){
				companyName = getCompanyName(companyId);
			}
			client.getWebservice().update(new ComputerDTO(Long.parseLong(id),name,introducedDate, discontinuedDate, companyId, companyName));	
		} else {
			System.out.println("Invalid id -> Update Action Abandoned");
		}	
	}
	
	private void deleteComputer() {
		System.out.println("Please insert computer's id : ");
		String id = consoleService.getScanner().nextLine().trim();
		if (consoleUtil.isValidId(id)) {
			client.getWebservice().deleteComputer(Long.parseLong(id));
		} else {
			System.out.println("Invalid id -> Delete Action Abandoned");
		}
	}
	
	private void getAllCompanies() {
		System.out.println(consoleUtil.displayCompanies(client.getWebservice().getAllCompanies()));
	}
	
	private void getByIdCompany() {
		System.out.println("Please insert company's id : ");
		String id = consoleService.getScanner().nextLine().trim();
		if (consoleUtil.isValidId(id)) {
			System.out.println((client.getWebservice().getByIdCompany(Long.parseLong(id))));
		} else {
			System.out.println("Invalid id -> Display Action Abandoned");
		}
	}
	
	private void deleteCompany() {
		System.out.println("Please insert company's id : ");
		String id = consoleService.getScanner().nextLine().trim();
		if (consoleUtil.isValidId(id)) {
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
	
	/*Create and Update Helpful Methods  */ 
	private String getIntoducedDate(String defaultIDate) {
		System.out.println("Would you like to enter an introduced date? (y/n)");
		String answerIntroduced = consoleService.getScanner().nextLine().trim();
		String introducedDate = defaultIDate;
		if(consoleUtil.isPositiveAnswer(answerIntroduced)) {
			System.out.println("Please enter the introduced date (format : MM-dd-yyyy )");
			String iDate = consoleService.getScanner().nextLine().trim();
			if(consoleUtil.isValidDate(iDate)) {
				introducedDate =  iDate;
			} else {
				System.out.println("Invalid Date");
			}	
		}
		return introducedDate;
	}
	
	private String getDiscontinuedDate( String defaultDDate) {
		System.out.println("Would you like to enter an discontinued date? (y/n)");
		String answerDiscontinued = consoleService.getScanner().nextLine().trim();
		String discontinuedDate = defaultDDate;
		if(consoleUtil.isPositiveAnswer(answerDiscontinued)) {
			System.out.println("Please enter the discontinued date (format : MM-dd-yyyy )");
			String dDate = consoleService.getScanner().nextLine().trim();
			if(consoleUtil.isValidDate(dDate)) {
				discontinuedDate =  dDate;
			} else {
				System.out.println("Invalid Date");
			}	
		}
		return discontinuedDate;
	}
	
	private long getCompanyId(long defaultCompanyId) {
		System.out.println("Would you like to enter company for the computer? (y/n)");
		String answerCompany = consoleService.getScanner().nextLine().trim();
		long companyId = defaultCompanyId;
		if(consoleUtil.isPositiveAnswer(answerCompany)) {
			System.out.println("Please enter the id of the company");
			String id = consoleService.getScanner().nextLine().trim();
			if(consoleUtil.isValidId(id)) {
				companyId = Long.parseLong(id);
			} else {
				System.out.println("Invalid Id");
			}	
		}
		return companyId;
	}
	
	private String getCompanyName(long companyId) {
		return client.getWebservice().getByIdCompany(companyId).getName();
	}
}
