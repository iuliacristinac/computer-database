package com.excilys.controller;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.IMapperDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.IService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/addComputer")
@SessionAttributes("companies")
public class AddComputer {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AddComputer.class);
	
	@Autowired
	private IMapperDTO<Company, CompanyDTO> companyMapperDTO;
	
	@Autowired
	private IMapperDTO<Computer, ComputerDTO> computerMapperDTO;
	
	@Autowired
	private IService<Company, Long> companyService;
	
	@Autowired
	private IService<Computer, Long> computerService;
	
	@ModelAttribute("companies")
	private List<CompanyDTO> getCompanies() {
		return companyMapperDTO.modelsToDto(companyService.getAll());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String  addComputerGET(@ModelAttribute("newComputer") ComputerDTO newComputer, Model model) {
		return "addComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addComputerPOST(@Valid @ModelAttribute("newComputer") ComputerDTO newComputer, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
				return "addComputer";
		}
		
		String name = newComputer.getName();
		String introduced = newComputer.getIntroduced();
		String discontinued = newComputer.getDiscontinued();
		long companyId = newComputer.getCompanyId();
		if (name != null) {
			name = name.trim();
			if (name.isEmpty()) {
				LOGGER.error("Adding computer failed because of empty name");
				model.addAttribute("message", "Name is mandatory");
				return "addComputer";
			}
		} else {
			LOGGER.error("Adding computer failed because of null name");
			model.addAttribute("message", "Name is mandatory");
			return "addComputer";
		}
		final ComputerDTO dto = new ComputerDTO();
		if (companyId > 0 ) {
			final Company company = companyService.getById(companyId);
			dto.setCompanyId(companyId);
			dto.setCompanyName(company.getName());
		}
		dto.setName(name);
		dto.setIntroduced(introduced);
		dto.setDiscontinued(discontinued);
		final Computer computer = computerMapperDTO.mapDTOToModel(dto);
		computerService.create(computer);
		LOGGER.info("Successfully created computer with id {}",
				computer.getId());
		
		return "redirect:/dashboard";
	}
}