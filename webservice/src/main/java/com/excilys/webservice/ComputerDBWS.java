package com.excilys.webservice;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.IMapperDTO;
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
	@Autowired
	private IMapperDTO<Computer, ComputerDTO> computerMapperDTO;
	@Autowired
	private IMapperDTO<Company, CompanyDTO> companyMapperDTO;
	
	@Override
	public long count() {
		return computerService.count();
	}
	
	@Override
	public List<ComputerDTO> getAllComputers() {
		return computerMapperDTO.mapModelsToDTO(computerService.getAll());
	}
	
	@Override
	public ComputerDTO getByIdComputer(long id) {
		return computerMapperDTO.mapModelToDTO(computerService.getById(id));
	}
	
	@Override
	public void create(ComputerDTO computerDTO) {
		computerService.create(computerMapperDTO.mapDTOToModel(computerDTO));
	}
	
	@Override
	public void update(ComputerDTO computerDTO) {
		computerService.update(computerMapperDTO.mapDTOToModel(computerDTO));
	}
	
	@Override
	public void deleteComputer(long id) {
		computerService.delete(id);
	}
	
	@Override
	public List<CompanyDTO> getAllCompanies() {
		return companyMapperDTO.mapModelsToDTO(companyService.getAll());
	}
	
	@Override
	public CompanyDTO getByIdCompany(long id) {
		return companyMapperDTO.mapModelToDTO(companyService.getById(id));
	}
	
	@Override
	public void deleteCompany(long id) {
		companyService.delete(id);
	}
}
