package com.excilys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.mapper.ComputerMapperDTO;
import com.excilys.service.ComputerService;

@WebServlet(urlPatterns = "/dashboard")
public class Dashboard extends AbstractServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ComputerMapperDTO computerMapperDTO;
		
	@Autowired
	private ComputerService computerService;
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
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
		
		
		request.setAttribute("computers", computerMapperDTO.modelsToDto(computerService.getAll()));
		
		
//		request.setAttribute("currentPage", pge);
//		request.setAttribute("total", totalEntities);
		getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}
}