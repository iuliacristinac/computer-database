package com.excilys.persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.annotations.SQLInsert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.exception.DAOException;
import com.excilys.mapper.IMapper;
import com.excilys.model.Computer;
import com.excilys.model.QComputer;
import com.excilys.model.QCompany;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class ComputerDAO implements IDAO<Computer, Long>{
 
	@Autowired
	private IMapper<Computer> computerMapper;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);

	private static final String GET_BY_ID_SQL = "SELECT * FROM computer LEFT OUTER JOIN company "
			+ "ON computer.company_id = company.id WHERE computer.id = ?";
	
	private static final String INSERT_SQL = "INSERT INTO computer (name, introduced, discontinued, company_id)"
			+ " VALUES ( ?, ?, ?, ?)";
	
	private static final String UPDATE_SQL = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?,"
			+ " company_id = ? WHERE id = ? ";

	
	@Override
	public Computer getbyId(Long id) {	
		
		return jdbcTemplate.queryForObject(GET_BY_ID_SQL, new Object[] { id }, computerMapper);
	}

	@Override
	public List<Computer> getAll() {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(entityManager);
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		List<Computer> computers = query
									.from(computer)
									.leftJoin(computer.company, company)
									.list(computer);
		return computers;
	}

	public List<Computer> getAllByCompany(Long id) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(entityManager);
		QComputer computer = QComputer.computer;
		List<Computer> computers = query
									.from(computer)
									.where(computer.company.id.eq(id))
									.list(computer);
		return computers;

	}

	@Override
	public void create(Computer entity) {	
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.persist(entity);
		entityManager.flush();
		entityManager.close();
	        	
	        	

//			preparedStatement.setString(1, entity.getName());
//			if (entity.getIntroduced() != null) {
//			preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getIntroduced()));
//			}
//			else {
//			preparedStatement.setTimestamp(2, null);
//			}
//			if (entity.getDiscontinued() != null) {
//			preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getDiscontinued()));
//			}
//			else {
//			preparedStatement.setTimestamp(3, null);
//			}
//			if (entity.getCompany() != null) {
//			preparedStatement.setLong(4, entity.getCompany().getId());
//			}
//			else {
//			preparedStatement.setObject(4, null);
//			} 
	    LOGGER.info("Computer {} successfully created", entity.getId());	
	}

	@Override
	public void update(Computer entity) {		
			
		List<Object> param = new LinkedList<>();
		param.add(entity.getName());

		if (entity.getIntroduced() != null) {
			param.add(Timestamp.valueOf(entity.getIntroduced()));		
		}
		else {
			param.add(null);
		}
		
		if (entity.getDiscontinued() != null) {
			param.add(Timestamp.valueOf(entity.getDiscontinued()));		
		}
		else{
			param.add(null);
		}
		
		if (entity.getCompany() != null) {
			param.add(entity.getCompany().getId());
		}
		else {
			param.add(null);
		}
		param.add(entity.getId());
		jdbcTemplate.update(UPDATE_SQL, param.toArray()); 
		LOGGER.info("Computer {} successfully updated", entity.getId());	
	}
	
	@Override
	public void delete(Long id) {	
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		QComputer computer = QComputer.computer;

		new JPADeleteClause(entityManager, computer)
			.where(computer.id.eq(id)).execute();
		LOGGER.info("Computer {} successfully deleted", id);	
	}
}
