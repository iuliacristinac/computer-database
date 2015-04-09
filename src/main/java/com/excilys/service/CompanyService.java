package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.exception.ServiceException;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistance.dao.CompanyDAO;
import com.excilys.persistance.dao.ComputerDAO;

@Service
public class CompanyService implements IService<Company, Long> {
	
	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private ComputerDAO computerDAO;

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

	@Override
	@Transactional
	public void delete(Long id) {
		List<Computer> computers = computerDAO.getAllByCompany(id);
		for (Computer computer : computers) {
			computerDAO.delete(computer.getId());
		}
		companyDAO.delete(id);
	}
}
