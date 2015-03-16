package com.excilys.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.dao.ComputerDAO;
import com.excilys.model.Computer;

public class ComputerService {
	
	private ComputerDAO computerDAO;
	
	public ComputerService() {
		computerDAO = new ComputerDAO();
	}
	
	public Computer getbyId( Long id) throws SQLException {
		return computerDAO.getbyId(id);
	}
	
	public List<Computer> getAll() throws SQLException {
		return computerDAO.getAll();
	}
	
	public Long create( Computer entity) throws SQLException {
		return computerDAO.create(entity);
	}
	
	public void update( Computer entity) throws SQLException {
		computerDAO.update(entity);
	}
	
	public void delete( Long id) throws SQLException {
		computerDAO.delete(id);
	}
}
