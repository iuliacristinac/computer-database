package com.excilys.controller;

import java.util.Locale;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.IMapperDTO;
import com.excilys.model.Computer;
import com.excilys.service.IService;
import com.excilys.util.Page;

@Controller
@RequestMapping("/dashboard")
public class Dashboard  {

	private static final Logger LOGGER = LoggerFactory.getLogger(Dashboard.class);
	
	@Autowired
	private IMapperDTO<Computer, ComputerDTO> computerMapperDTO;
		
	@Autowired
	private IService<Computer, Long> computerService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String dashboard( @RequestParam("page") Optional<Integer> page,
							 Locale locale,Model model) {
		
	
//	public String dashboard( Locale locale,Model model) {
	
		List <Computer> computersList = computerService.getAll();
		Page p = new Page(computersList);
		int pg = 1;
		if(page.isPresent()) {
			pg = page.get();
			p.setCurrentPage(pg);
			
		}
		
		if(p.hasPrevious()){
			p.setPrevious(true);
		}
		
		if(p.hasNext()){
			p.setNext(true);
		}
		
		LOGGER.info("Welcome! The client locale is {}.", locale);
		
		List<ComputerDTO> computers = new ArrayList<>();
		computers =  computerMapperDTO.modelsToDto(p.getElements(computersList, pg));
		model.addAttribute("totalEntities", computerService.count());
		model.addAttribute("page", p);
		model.addAttribute("computers", computers);
		return "dashboard";
	}
}