package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.exception.ServiceException;
import com.excilys.model.Company;
import com.excilys.persistance.dao.CompanyDAO;

@Service
public class CompanyService implements IService<Company, Long> {
	
	@Autowired
	private CompanyDAO companyDAO;

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
