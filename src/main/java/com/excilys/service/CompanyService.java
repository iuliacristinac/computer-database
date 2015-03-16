package com.excilys.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.dao.CompanyDAO;
import com.excilys.model.Company;

public class CompanyService {
	
	private CompanyDAO companyDAO;
	
	public CompanyService() {
		companyDAO = new CompanyDAO();
	}
	
	public Company getbyId( Long id) throws SQLException {
		return companyDAO.getbyId(id);
	}
	
	public List<Company> getAll() throws SQLException {
		return companyDAO.getAll();
	}
	
	public Long create( Company entity) throws SQLException {
		return companyDAO.create(entity);
	}
	
	public void update( Company entity) throws SQLException {
		companyDAO.update(entity);
	}
	
	public void delete( Long id) throws SQLException {
		companyDAO.delete(id);
	}
	
}
