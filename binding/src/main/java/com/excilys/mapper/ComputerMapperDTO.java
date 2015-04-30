package com.excilys.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.util.DateUtil;

@Component
public class ComputerMapperDTO implements IMapperDTO<Computer, ComputerDTO> {

	@Autowired
	private DateUtil dateUtil;
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public ComputerDTO mapModelToDTO(Computer model) {
		if ( model == null) {
			throw new IllegalArgumentException("ComputerMapperDTO - Invalid Model!");
		}
		
		String datePattern = messageSource.getMessage("date.DatePattern", null, LocaleContextHolder.getLocale());
		
		final ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setId(model.getId());
		computerDTO.setName(model.getName());
		
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
		
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
		
		String datePattern = messageSource.getMessage("date.DatePattern", null, LocaleContextHolder.getLocale());
		
		final Computer computer = new Computer();
		computer.setId(dto.getId());
		computer.setName(dto.getName());
		
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
		
		if (dto.getIntroduced() != null) {
			dto.setIntroduced( dto.getIntroduced().trim());
			if (!dto.getIntroduced().isEmpty()) {
				computer.setIntroduced(LocalDateTime.parse(dto.getIntroduced(), formatter));
			}
		}
		if (dto.getDiscontinued() != null) {
			dto.setDiscontinued(dto.getDiscontinued().trim());
			if (!dto.getDiscontinued().isEmpty()) {
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
}
