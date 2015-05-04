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
import com.excilys.util.Field;
import com.excilys.util.Page;
import com.excilys.util.Sort;

@Controller
@RequestMapping("/dashboard")
public class Dashboard  {

	@Autowired
	private IMapperDTO<Computer, ComputerDTO> computerMapperDTO;
		
	@Autowired
	private IService<Computer, Long> computerService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String dashboard( @RequestParam("page") Optional<Integer> page,
							 @RequestParam("size") Optional<Integer> size,
							 @RequestParam("search") Optional<String> search,
							 @RequestParam("sort") Optional<String> sort,
							 @RequestParam("col") Optional<String> column,
							 Model model) {
		Page p ;
		int pg = 1;
		int sz =10;
		List <Computer> computersList;
		
		if (search.isPresent()) {
			String text = search.get().trim();
			computersList = computerService.search(text);
			p = new Page(computersList);
			List<ComputerDTO> computers = new ArrayList<>();
			computers =  computerMapperDTO.mapModelsToDTO(p.getElements(computersList, pg));
			model.addAttribute("totalEntities", computersList.size());
			model.addAttribute("page", p);
			model.addAttribute("computers", computers);
			model.addAttribute("lastSearch", text);
			return "dashboard";
		} else {
			computersList = computerService.getAll();
		}
		
		if(size.isPresent()) {
			sz = size.get();
			p = new Page(computersList, sz);
		}
		else {
			p = new Page(computersList);
		}
		
		if (sort.isPresent()) {
			String srt = sort.get().trim();
			if (!srt.isEmpty()) {
				if (Sort.isValid(srt)) {
					p.setSort(Sort.valueOf(srt));
				} else {
					return "404";
				}
			}
		}
		
		if (column.isPresent()) {
			final String col = column.get().trim();
			if (!Field.isValid(col)) {
				return "404";
			}
			if (!col.isEmpty()) {
				p.setProperties(col);
			}
		}
		
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