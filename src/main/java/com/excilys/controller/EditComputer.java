package com.excilys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.IMapperDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.IService;

@Controller
@RequestMapping("/editComputer")
public class EditComputer {

	static final Logger LOGGER = LoggerFactory
			.getLogger(EditComputer.class);

	@Autowired
	private IMapperDTO<Company, CompanyDTO> companyMapperDTO;
	
	@Autowired
	private IMapperDTO<Computer, ComputerDTO> computerMapperDTO;
	
	@Autowired
	private IService<Company, Long> companyService;
	
	@Autowired
	private IService<Computer, Long> computerService;
	
	@RequestMapping(method=RequestMethod.GET)
	
	protected void editComputerGET(@RequestParam ("id") long id, Model model) {
		
		model.addAttribute("computer",
				computerMapperDTO.mapModelToDTO(computerService.getById(id)));
		model.addAttribute("companies", 
				companyMapperDTO.modelsToDto(companyService.getAll()));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected String editComputerPOST(@ModelAttribute("computer") ComputerDTO newComputer, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
				return "editComputer";
		}
		
		long id = newComputer.getId();
		String name = newComputer.getName();
		String introduced = newComputer.getIntroduced();
		String discontinued = newComputer.getDiscontinued();
		long companyId = newComputer.getCompanyId();
		if (name != null) {
			name = name.trim();
			if (name.isEmpty()) {
				LOGGER.error("Adding computer failed because of empty name");
				model.addAttribute("companies", companyMapperDTO
						.modelsToDto(companyService.getAll()));
				model.addAttribute("message", "Name is mandatory");
				return "editComputer";
			}
		} else {
			LOGGER.error("Adding computer failed because of null name");
			model.addAttribute("companies", companyMapperDTO
					.modelsToDto(companyService.getAll()));
			model.addAttribute("message", "Name is mandatory");
			return "editComputer";
		}
		final ComputerDTO dto = new ComputerDTO();
		dto.setName(name);
		if (companyId > 0) {
			Company company = companyService.getById(companyId);
			dto.setCompanyId(companyId);
			dto.setCompanyName(company.getName());
		}
		dto.setId(id);
		dto.setName(name);
		dto.setIntroduced(introduced);
		dto.setDiscontinued(discontinued);
		final Computer computer = computerMapperDTO.mapDTOToModel(dto);
		computerService.update(computer);
		LOGGER.info("Computer {} successfully updated ",
				computer.getId());
		
		return "redirect:/dashboard";
	}
}