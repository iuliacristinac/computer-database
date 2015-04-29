package com.excilys.console;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("consoleService")
public class ConsoleService {
	
	@Autowired
	private CommandService commandService;
	
	private Scanner scanner;
	
	public void start() {
		scanner = new Scanner(System.in);
		System.out.println(commandService);
		commandService.execute("help");
		String command = scanner.nextLine().trim();
		while(!command.equals("exit")) {
			commandService.execute(command);
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Another Command ? \n");
			stringBuilder.append("\t - help to display commands list \n");
			stringBuilder.append("\t - exit to quit the command prompt");
			System.out.println( stringBuilder);
			command = scanner.nextLine().trim();
		}
		System.out.println("Sad to see you go...:(");
		scanner.close();
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
}
