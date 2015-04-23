package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.exception.ServiceException;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.dao.IDAO;

@Service
public class CompanyService implements IService<Company, Long> {
	
	@Autowired
	private IDAO<Company, Long> companyDAO;
	@Autowired
	private IDAO<Computer, Long> computerDAO;

	@Override
	@Transactional(readOnly=true)
	public List<Company> getAll() {
		return companyDAO.getAll();
	}

	@Override
	@Transactional(readOnly=true)
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
