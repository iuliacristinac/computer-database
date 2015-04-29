package com.excilys.console;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class ConsoleService {
	
	@Autowired
	private CommandService commandService;
	private Scanner scanner;
	
	public void console() {
		scanner = new Scanner(System.in);
		commandService.execute("help");
		String command = scanner.nextLine().trim();
		while(!command.equals("exit")) {
			commandService.execute(command);
		}
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	

}
