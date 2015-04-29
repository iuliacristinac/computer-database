package com.excilys.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.excilys.model.Company;
import com.excilys.model.Computer;

@WebService
@SOAPBinding(style=Style.DOCUMENT)
public interface IComputerDBWS {

	@WebMethod
	long count();
	
	@WebMethod
	List<Computer> getAllComputers();
	
	@WebMethod
	Computer getByIdComputer(long id);
	
	@WebMethod
	void create(Computer computer);
	
	@WebMethod
	void update(Computer computer);
	
	@WebMethod
	void deleteComputer(long id);
	
	@WebMethod
	List<Company> getAllCompanies();
	
	@WebMethod
	Company getByIdCompany(long id);
	
	@WebMethod
	void deleteCompany(long id);	
}
