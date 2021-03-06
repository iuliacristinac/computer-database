package com.excilys.mapper;

import org.springframework.stereotype.Component;

import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;

@Component
public class CompanyMapperDTO implements IMapperDTO<Company, CompanyDTO>{
	
	@Override
	public CompanyDTO mapModelToDTO(Company model) {
		if (model == null) {
			throw new IllegalArgumentException("CompanyMapperDTO - Invalid Model!");
		}
		final CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(model.getId());
		companyDTO.setName(model.getName());
		return companyDTO;
	}

	@Override
	public Company mapDTOToModel(CompanyDTO dto) {
		if (dto == null){
			throw new IllegalArgumentException("CompanyMapperDTO - Invalid DTO!");
		}
		final Company company = new Company();
		company.setId(dto.getId());
		company.setName(dto.getName());
		return company;
	}

}
