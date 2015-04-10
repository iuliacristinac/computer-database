package com.excilys.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerMapperDTO;
import com.excilys.service.ComputerService;

@Controller
@RequestMapping("/dashboard")
public class Dashboard  {

	@Autowired
	private ComputerMapperDTO computerMapperDTO;
		
	@Autowired
	private ComputerService computerService;
	
	@RequestMapping(method = RequestMethod.GET)
	public void dashboard(Model model) {
		
		/*String page = request.getParameter("page");
		String size = request.getParameter("size");
		int currentPage = 1, entitiesByPage = 10, pge = 1;
		if (page != null) {
			page = page.trim();
			if (!page.isEmpty()) {
				currentPage = Integer.valueOf(page);
				pge = currentPage;
			}
		}
		if (size != null) {
			size = size.trim();
			if (!size.isEmpty()) {
				entitiesByPage = Integer.valueOf(size);
			}
		}
		final Page p = new Page(ComputerService.INSTANCE.getAll());
		p.setNumberOfPages(numberOfPages);
		int maxPages = (totalEntities / entitiesByPage);
		if (totalEntities % entitiesByPage != 0) {
			++maxPages;
		}
		request.setAttribute("totalPages", maxPages);
		maxPages = Math.min(maxPages, pge + entitiesByPage - 1);
		request.setAttribute("page", p);
		request.setAttribute("sizePage", entitiesByPage);
		request.setAttribute("maxPages", maxPages);*/
		
		List<ComputerDTO> computers = new ArrayList<>();
		computers =  computerMapperDTO.modelsToDto(computerService.getAll());
		model.addAttribute("computers", computers);

		//return new ModelAndView("dashboard", "computers", computers);
		
		
//		request.setAttribute("currentPage", pge);
//		request.setAttribute("total", totalEntities);
//		getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}
}