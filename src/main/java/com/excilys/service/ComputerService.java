package com.excilys.service;

import java.util.List;

import com.excilys.exception.ServiceException;
import com.excilys.model.Computer;
import com.excilys.persistance.dao.ComputerDAO;

public enum ComputerService implements IService<Computer, Long>{
	INSTANCE;
	
	private ComputerDAO computerDAO;
	
	private ComputerService() {
		computerDAO = ComputerDAO.INSTANCE;
	}
	
//	@Override
//	public Computer getbyId( Long id) {
//		if (id <= 0) {
//			throw new ServiceException("ComputerService_getbyId - Invalid id!");
//		}
//		return computerDAO.getbyId(id);
//	}
	
	@Override
	public List<Computer> getAll() {
		return computerDAO.getAll();
	}
	
	@Override
	public void create( Computer entity) {
		if (entity == null) {
			throw new ServiceException("ComputerService_create -  No entity!");
		}
		computerDAO.create(entity);
	}
	
	@Override
	public void update( Computer entity) {
		if (entity == null) {
			throw new ServiceException("ComputerService_update -  No entity!");
		}
		computerDAO.update(entity);
	}
	
	@Override
	public void delete( Long id) {
		if (id <= 0) {
			throw new ServiceException("ComputerService_delete - Invalid id!");
		}
		computerDAO.delete(id);
	}

	public Computer getById(Long id) {
		if (id <= 0) {
			throw new ServiceException("ComputerService_getbyId - Invalid id!");
		}
		return computerDAO.getbyId(id);
	}
}
