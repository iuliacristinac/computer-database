package com.excilys.webservice;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.IService;

@Component
@WebService(endpointInterface="com.excilys.webservice.IComputerDBWS")
public class ComputerDBWS implements IComputerDBWS{
	
	@Autowired
	private IService<Computer,Long> computerService;
	@Autowired
	private IService<Company,Long>  companyService;

	@Override
	public long count() {
		return computerService.count();
	}
	
	@Override
	public List<Computer> getAllComputers() {
		return computerService.getAll();
	}
	
	@Override
	public Computer getByIdComputer(long id) {
		return computerService.getById(id);
	}
	
	@Override
	public void create(Computer computer) {
		computerService.create(computer);
	}
	
	@Override
	public void update(Computer computer) {
		computerService.update(computer);
	}
	
	@Override
	public void deleteComputer(long id) {
		computerService.delete(id);
	}
	
	@Override
	public List<Company> getAllCompanies() {
		return companyService.getAll();
	}
	
	@Override
	public Company getByIdCompany(long id) {
		return companyService.getById(id);
	}
	
	@Override
	public void deleteCompany(long id) {
		companyService.delete(id);
	}
}
