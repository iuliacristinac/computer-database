package com.excilys.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

public class Main {

	public static void main(String[] args) {
		
/*		
		CompanyService companyService = new CompanyService();
		List<Company> companies = new ArrayList<Company>();

		try {
			companies = companyService.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Company company : companies) {
			System.out.println(company);
		}
	*/
		ComputerService computerService = new ComputerService();		
		List<Computer> computers = new ArrayList<Computer>();

		try {
			computers = computerService.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Computer computer : computers) {
			System.out.println(computer);
		}
	
	}

}
