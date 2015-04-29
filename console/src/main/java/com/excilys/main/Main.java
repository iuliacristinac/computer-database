package com.excilys.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.console.ConsoleService;

public class Main {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:console-application-context.xml");
		ConsoleService consoleService = (ConsoleService) context.getBean("consoleService");
		consoleService.start();
	}
	
}
