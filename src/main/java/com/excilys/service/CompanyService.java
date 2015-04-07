package com.excilys.service;

import java.util.List;

import com.excilys.exception.ServiceException;
import com.excilys.model.Company;
import com.excilys.persistance.dao.CompanyDAO;

public enum CompanyService implements IService<Company, Long> {
	INSTANCE;
	
	private CompanyDAO companyDAO;
	
	private CompanyService() {
		companyDAO = CompanyDAO.INSTANCE;
	}
	
//	@Override
//	public Company getbyId( Long id) {
//		if (id <= 0) {
//			throw new ServiceException("CompanyService_getbyId - Invalid id!");
//		}
//		return companyDAO.getbyId(id);
//	}
//	
	@Override
	public List<Company> getAll() {
		return companyDAO.getAll();
	}

	public Company getById(Long id) {
		if (id <= 0) {
			throw new ServiceException("CompanyService_getbyId - Invalid id!");
		}
		return companyDAO.getbyId(id);
	}
}
