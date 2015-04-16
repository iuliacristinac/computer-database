package com.excilys.controller;

import java.util.Locale;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.IMapperDTO;
import com.excilys.model.Computer;
import com.excilys.service.IService;

@Controller
@RequestMapping("/dashboard")
public class Dashboard  {

	private static final Logger LOGGER = LoggerFactory.getLogger(Dashboard.class);
	
	@Autowired
	private IMapperDTO<Computer, ComputerDTO> computerMapperDTO;
		
	@Autowired
	private IService<Computer, Long> computerService;
	
	@RequestMapping(method = RequestMethod.GET)
	public void dashboard(Locale locale,Model model) {
		
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
		
		LOGGER.info("Welcome! The client locale is {}.", locale);
		
		List<ComputerDTO> computers = new ArrayList<>();
		computers =  computerMapperDTO.modelsToDto(computerService.getAll());
		model.addAttribute("computers", computers);

		//return new ModelAndView("dashboard", "computers", computers);
		
		
//		request.setAttribute("currentPage", pge);
//		request.setAttribute("total", totalEntities);
//		getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}
}