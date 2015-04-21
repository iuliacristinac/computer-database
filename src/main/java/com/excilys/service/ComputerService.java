package com.excilys.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.exception.ServiceException;
import com.excilys.model.Computer;
import com.excilys.persistance.dao.IDAO;

@Service
public class ComputerService implements IService<Computer, Long>{
	
	@Autowired
	private IDAO<Computer, Long> computerDAO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerService.class);
	
	
	@Override
	@Transactional(readOnly=true)
	public Long count() {
		return computerDAO.count();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Computer> getAll() {
		return computerDAO.getAll();
	}
	
	@Override
	@Transactional
	public void create( Computer entity) {
		if (entity == null) {
			throw new ServiceException("ComputerService_create -  No entity!");
		}
		computerDAO.create(entity);
		LOGGER.info("Computer {} successfully created", entity.getId());	
	}
	
	@Override
	@Transactional
	public void update( Computer entity) {
		if (entity == null) {
			throw new ServiceException("ComputerService_update -  No entity!");
		}
		computerDAO.update(entity);
		LOGGER.info("Computer {} successfully updated", entity.getId());	
	}
	
	@Override
	@Transactional
	public void delete( Long id) {
		if (id <= 0) {
			throw new ServiceException("ComputerService_delete - Invalid id!");
		}
		computerDAO.delete(id);
		LOGGER.info("Computer {} successfully deleted", id);	
	}

	@Override
	@Transactional(readOnly=true)
	public Computer getById(Long id) {
		if (id <= 0) {
			throw new ServiceException("ComputerService_getbyId - Invalid id!");
		}
		return computerDAO.getbyId(id);
	}
}
