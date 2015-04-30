package com.excilys.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.console.ConsoleService;

public class Main {
	
	public static void main(String[] args) {
		/*
		 * Safe Solution to warning: resource leak 'context' is never closed spring
		 * - try with resources 
		 * - using ClassPathXmlApplicationContext instead of ApplicationContext because it provides a close()
		 * Why? - Since the app context is a ResourceLoader (i.e. I/O operations) it consumes resources that need 
		 * 		to be freed at some point. Hint: your ClassPathXmlApplicationContext is a DefaultResourceLoader and as such has a close method.
		 */
		
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:console-application-context.xml")) {
			ConsoleService consoleService = (ConsoleService) context.getBean("consoleService");
			consoleService.start();
		}
	}
	
}
