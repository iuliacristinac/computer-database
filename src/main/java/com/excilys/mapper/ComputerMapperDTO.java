package com.excilys.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.util.Validator;

public enum ComputerMapperDTO implements IMapperDTO<Computer, ComputerDTO> {
	INSTANCE;
	
	private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	@Override
	public ComputerDTO mapModelToDTO(Computer model) {
		if ( model == null) {
			throw new IllegalArgumentException("ComputerMapperDTO - Invalid Model!");
		}
		final ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setId(model.getId());
		computerDTO.setName(model.getName());
		
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
		
		if (model.getIntroduced() != null) {
			computerDTO.setIntroduced(model.getIntroduced().format(formatter));
		}
		if (model.getDiscontinued() != null) {
			computerDTO.setDiscontinued(model.getDiscontinued().format(formatter));
		}
		if (model.getCompany() != null) {
			computerDTO.setCompanyId(model.getCompany().getId());
			computerDTO.setCompanyName(model.getCompany().getName());
		}
		return computerDTO;
	}

	@Override
	public Computer mapDTOToModel(ComputerDTO dto) {
		if (dto == null){
			throw new IllegalArgumentException("ComputeryMapperDTO - Invalid DTO!");
		}
		
		final Computer computer = new Computer();
		computer.setId(dto.getId());
		computer.setName(dto.getName());
		
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
		
		if (dto.getIntroduced() != null) {
			dto.setIntroduced( dto.getIntroduced().trim());
			if (!dto.getIntroduced().isEmpty()) {
				dto.setIntroduced( convertToValidDate(dto.getIntroduced()));
				computer.setIntroduced(LocalDateTime.parse(dto.getIntroduced(),formatter));
			}
		}
		if (dto.getDiscontinued() != null) {
			dto.setDiscontinued(dto.getDiscontinued().trim());
			if (!dto.getDiscontinued().isEmpty()) {
				dto.setDiscontinued( convertToValidDate(dto.getDiscontinued()));
				computer.setDiscontinued(LocalDateTime.parse(dto.getDiscontinued(), formatter));
			}
		}
		
		if (dto.getCompanyId() > 0) {
			final Company company = new Company();
			company.setId(dto.getCompanyId());
			company.setName(dto.getCompanyName());
			computer.setCompany(company);
		}
			
		return computer;
	}
	
	private String convertToValidDate( String date){
		
		if ( Validator.isValidDateHour(date)) {
			return date;
		}
		else {
				if (Validator.isValidDate(date)){
					return date + " " + "00:00:00";
			}
		}
		throw new IllegalArgumentException("ComputerMapperDTO - Invalid Date!");
	}
}
