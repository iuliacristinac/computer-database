package com.excilys.persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

	@Override
	public Computer getbyId(Long id) {	
		
		return jdbcTemplate.queryForObject("SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id WHERE computer.id = ?", new Object[] { id }, computerMapper);
	}

	@Override
	public List<Computer> getAll() {
		
		return jdbcTemplate.query("SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id", computerMapper);
	}

	public List<Computer> getAllByCompany(Long id) {
		
		 return jdbcTemplate.query("SELECT * FROM computer WHERE company_id = ?", new Object[] { id }, computerMapper);
	}

	@Override
	public void create(Computer entity) {		
		
		final Connection connection = connectionDB.getInstance();
		
		String query = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES ( ?, ?, ?, ?)";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
			
			preparedStatement.execute();
			
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
            }
            else {
                throw new SQLException("Creating computer failed.");
            }
            LOGGER.info("Computer {} successfully created", entity.getId());	
		} catch (SQLException e) {
			throw new DAOException("ComputerDAO_create Exception!", e);
		} 
	}

	@Override
	public void update(Computer entity) {		
		
		final Connection connection = connectionDB.getInstance();
		
		String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ? ";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
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
			else{
				preparedStatement.setTimestamp(3, null);
			}
			
			if (entity.getCompany() != null) {
				preparedStatement.setLong(4, entity.getCompany().getId());
			}
			else {
				preparedStatement.setObject(4, null);
			}
			preparedStatement.setLong(5, entity.getId());
			preparedStatement.execute();
			 LOGGER.info("Computer {} successfully updated", entity.getId());	
		} catch (SQLException e) {
			throw new DAOException("ComputerDAO_update Exception!", e);
		}
	}
	
	@Override
	public void delete(Long id) {	
		
		jdbcTemplate.update("DELETE FROM computer WHERE id = ?", id );
		LOGGER.info("Computer {} successfully deleted", id);	
	}
}
