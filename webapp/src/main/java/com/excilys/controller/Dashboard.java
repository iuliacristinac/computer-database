package com.excilys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Autowired
	private IMapperDTO<Computer, ComputerDTO> computerMapperDTO;
		
	@Autowired
	private IService<Computer, Long> computerService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String dashboard( @RequestParam("page") Optional<Integer> page, Model model) {
			
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
		
		List<ComputerDTO> computers = new ArrayList<>();
		computers =  computerMapperDTO.mapModelsToDTO(p.getElements(computersList, pg));
		model.addAttribute("totalEntities", computerService.count());
		model.addAttribute("page", p);
		model.addAttribute("computers", computers);
		return "dashboard";
	}
}