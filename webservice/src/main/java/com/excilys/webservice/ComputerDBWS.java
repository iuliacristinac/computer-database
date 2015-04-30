package com.excilys.webservice;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	private IMapperDTO<Computer,ComputerDTO> computerMapper;
	@Autowired
	private IMapperDTO<Company,CompanyDTO> companyMapper;

	@Override
	public long count() {
		return computerService.count();
	}
	
	@Override
	public List<ComputerDTO> getAllComputers() {
		return computerMapper.mapModelsToDTO(computerService.getAll());
	}
	
	@Override
	public ComputerDTO getByIdComputer(long id) {
		return computerMapper.mapModelToDTO(computerService.getById(id));
	}
	
	@Override
	public void create(ComputerDTO computerDTO) {
		computerService.create(computerMapper.mapDTOToModel(computerDTO));
	}
	
	@Override
	public void update(ComputerDTO computerDTO) {
		computerService.update(computerMapper.mapDTOToModel(computerDTO));
	}
	
	@Override
	public void deleteComputer(long id) {
		computerService.delete(id);
	}
	
	@Override
	public List<CompanyDTO> getAllCompanies() {
		return companyMapper.mapModelsToDTO(companyService.getAll());
	}
	
	@Override
	public CompanyDTO getByIdCompany(long id) {
		return companyMapper.mapModelToDTO(companyService.getById(id));
	}
	
	@Override
	public void deleteCompany(long id) {
		companyService.delete(id);
	}
}
