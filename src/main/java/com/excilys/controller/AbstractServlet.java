package com.excilys.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class AbstractServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
}
//	

//public class AbstractServlet implements ServletContextListener {
//
//	@Override
//    public void contextInitialized(ServletContextEvent sce) {
//		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, sce.getServletContext());
//		}
//
//	@Override
//	public void contextDestroyed(ServletContextEvent arg0) {}
//}
