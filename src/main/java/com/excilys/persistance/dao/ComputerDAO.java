package com.excilys.persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.exception.DAOException;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.persistance.ConnectionDB;

@Repository
public class ComputerDAO implements IDAO<Computer, Long>{
 
	@Autowired
	private ComputerMapper computerMapper;
	@Autowired
	private ConnectionDB connectionDB;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);

	private static final String GET_BY_ID_SQL = "SELECT * FROM computer LEFT OUTER JOIN company "
			+ "ON computer.company_id = company.id WHERE computer.id = ?";
	
	private static final String GET_ALL_SQL = "SELECT * FROM computer LEFT OUTER JOIN company "
			+ "ON computer.company_id = company.id";
	
	private static final String GET_ALL_BY_COMPANY_SQL = "SELECT * FROM computer WHERE company_id = ?";
	
	private static final String INSERT_SQL = "INSERT INTO computer (name, introduced, discontinued, company_id)"
			+ " VALUES ( ?, ?, ?, ?)";
	
	private static final String UPDATE_SQL = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?,"
			+ " company_id = ? WHERE id = ? ";
	
	private static final String DELETE_SQL = "DELETE FROM computer WHERE id = ?";
	
	@Override
	public Computer getbyId(Long id) {	
		
		return jdbcTemplate.queryForObject(GET_BY_ID_SQL, new Object[] { id }, computerMapper);
	}

	@Override
	public List<Computer> getAll() {
		
		return jdbcTemplate.query(GET_ALL_SQL, computerMapper);
	}

	public List<Computer> getAllByCompany(Long id) {
		
		 return jdbcTemplate.query(GET_ALL_BY_COMPANY_SQL, new Object[] { id }, computerMapper);
	}

	@Override
	public void create(Computer entity) {		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator  preparedStatementCreator = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) {
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL,
							new String[] { "id" });
					preparedStatement.setString(1, entity.getName());
					
					if (entity.getIntroduced() != null) {
						preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getIntroduced()));		
					}
					else {
						preparedStatement.setTimestamp(2, null);		
					}
					
					if (entity.getDiscontinued() != null) {
						preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getDiscontinued()));		
					}
					else {
						preparedStatement.setTimestamp(3, null);
					}
					
					
					if (entity.getCompany() != null) {
						preparedStatement.setLong(4, entity.getCompany().getId());
					}
					else {
						preparedStatement.setObject(4, null);
					} 
					return preparedStatement;
				} catch (SQLException e) {
						throw new DAOException("ComputerDAO_create Exception!", e);
				}
			}
		};
		jdbcTemplate.update(preparedStatementCreator, keyHolder);
		entity.setId((Long)keyHolder.getKey());
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
		
		jdbcTemplate.update(DELETE_SQL, id );
		LOGGER.info("Computer {} successfully deleted", id);	
	}
}
