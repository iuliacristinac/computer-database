package com.excilys.persistence.dao;


import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.exception.DAOException;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.QComputer;
import com.excilys.model.QCompany;
import com.mysema.query.jpa.hibernate.HibernateDeleteClause;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.jpa.hibernate.HibernateUpdateClause;

@Repository
public class ComputerDAO implements IDAO<Computer, Long> {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);
	
	@Override
	public Long count() {
		
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QComputer computer = QComputer.computer;
		Long nbComputers = query
				.from(computer)
				.count();
		
		return nbComputers;
	}
	
	@Override
	public Computer getbyId(Long id) {	
		
		if (id == null || id < 0) {
			throw new DAOException("ComputerDAO_getById - Invalid computer id!");
		}
		
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		Computer result = query
							.from(computer)
							.leftJoin(computer.company, company)
							.where(computer.id.eq(id))
							.uniqueResult(computer);
		return result;
	}

	@Override
	public List<Computer> getAll() {
		
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		List<Computer> computers = query
									.from(computer)
									.leftJoin(computer.company, company)
									.list(computer);
		return computers;
	}

	public List<Computer> getAllByCompany(Long id) {
		
		if (id == null || id < 0) {
			throw new DAOException("ComputerDAO_getAllByCompany - Invalid company id!");
		}
		
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QComputer computer = QComputer.computer;
		List<Computer> computers = query
									.from(computer)
									.where(computer.company.id.eq(id))
									.list(computer);
		return computers;
	}

	@Override
	public void create(Computer entity) {	
		
		if (entity == null) {
			throw new DAOException("ComputerDAO_create - No entity!");
		}
		
		sessionFactory.getCurrentSession().save(entity);
	    LOGGER.info("Computer {} successfully created", entity.getId());	
	}

	@Override
	public void update(Computer entity) {		
		
		if (entity == null) {
			throw new DAOException("ComputerDAO_update - No entity!");
		}
		
		QComputer computer = QComputer.computer;
		LocalDateTime introduced = null;
		LocalDateTime discontinued = null ;
		Company company = null;

		if (entity.getIntroduced() != null) {
			introduced = entity.getIntroduced();		
		}
		
		if (entity.getDiscontinued() != null) {
			discontinued = entity.getDiscontinued();		
		}
		
		if (entity.getCompany() != null) {
			company = entity.getCompany();
		}		
		
		new HibernateUpdateClause(sessionFactory.getCurrentSession(), computer)
			.where(computer.id.eq(entity.getId()))
			.set(computer.name, entity.getName())
			.set(computer.introduced, introduced)
			.set(computer.discontinued, discontinued)
			.set(computer.company, company)
			.execute();

		LOGGER.info("Computer {} successfully updated", entity.getId());	
	}
	
	@Override
	public void delete(Long id) {	
		
		if (id == null || id < 0) {
			throw new DAOException("ComputerDAO_delete - Invalid computer id!");
		}
		
		QComputer computer = QComputer.computer;

		new HibernateDeleteClause(sessionFactory.getCurrentSession(), computer)
			.where(computer.id.eq(id)).execute();
		LOGGER.info("Computer {} successfully deleted", id);	
	}
}
