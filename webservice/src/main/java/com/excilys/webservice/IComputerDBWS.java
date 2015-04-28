package com.excilys.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;

@WebService
@SOAPBinding(style=Style.DOCUMENT)
public interface IComputerDBWS {

	@WebMethod
	long count();
	
	@WebMethod
	List<ComputerDTO> getAllComputers();
	
	@WebMethod
	ComputerDTO getByIdComputer(long id);
	
	@WebMethod
	void create(ComputerDTO computer);
	
	@WebMethod
	void update(ComputerDTO computer);
	
	@WebMethod
	void deleteComputer(long id);
	
	@WebMethod
	List<CompanyDTO> getAllCompanies();
	
	@WebMethod
	CompanyDTO getByIdCompany(long id);
	
	@WebMethod
	void deleteCompany(long id);	
}
