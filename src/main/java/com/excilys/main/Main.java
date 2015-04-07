package com.excilys.main;

import java.util.ArrayList;
import java.util.List;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

public class Main {

	public static void main(String[] args) {
		
	
//		CompanyService companyService = CompanyService.INSTANCE;
//		List<Company> companies = new ArrayList<Company>();
//		companies = companyService.getAll();
//		for (Company company : companies) {
//			System.out.println(company);
//		}
	
	
		ComputerService computerService = ComputerService.INSTANCE;		
//		List<Computer> computers = new ArrayList<Computer>();
//		computers = computerService.getAll();
//		for (Computer computer : computers) {
//			System.out.println(computer);
//		}
	
//		Computer computer = new Computer();
//		computer.setName("computerTest");
//		computerService.create(computer);

		Computer computer = computerService.getbyId(574L);
		System.out.println(computer);
		computer.setName("iPhone 4S");
		computerService.update(computer);
		System.out.println(computerService.getbyId(574L));
		
//		computerService.delete(576L);

//		Computer computer = computerService.getbyId(576L);
//		System.out.println(computer);
		
//		Computer computer = computerService.getbyId(574L);
//		System.out.println(computer);
	}
}
