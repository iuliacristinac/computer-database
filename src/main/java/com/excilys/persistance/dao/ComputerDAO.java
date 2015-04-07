package com.excilys.persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.exception.DAOException;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.persistance.ConnectionDB;

public enum ComputerDAO implements IDAO<Computer, Long>{
	INSTANCE;
 
	private ComputerMapper computerMapper;
	private ConnectionDB connectionDB;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);
	
	private ComputerDAO(){
		
		computerMapper = ComputerMapper.INSTANCE;
		connectionDB = ConnectionDB.INSTANCE;
	}
	
	@Override
	public Computer getbyId(Long id) {	
		
		final Connection connection = connectionDB.getInstance();
		
		String query = "SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id WHERE computer.id = ?";
		
		Computer computer = null;
		try {		
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
		
			ResultSet result = preparedStatement.executeQuery();
		
			if (result.first()){
				computer = computerMapper.mapResultSetToModel(result);
			}
			
		} catch (SQLException e) {
			throw new DAOException("ComputerDAO_getById Exception!", e);
		} finally {
			connectionDB.closeConnection();
		}
		
		return computer;
	}

	@Override
	public List<Computer> getAll() {
		
		final Connection connection = connectionDB.getInstance();
		
		List<Computer> computers = new ArrayList<>();
		
		String query  = "SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id";
		
		try {
			Statement statement = connection.createStatement();		
	
	        ResultSet resultSet = statement.executeQuery(query);
	
	        while ( resultSet.next() )
	        {
				Computer computer = computerMapper.mapResultSetToModel(resultSet);
				computers.add(computer);  	
	        }
		} catch (SQLException e) {
			throw new DAOException("ComputerDAO_getAll Exception!", e);
		} finally {
			connectionDB.closeConnection();
		}
		
        return computers;
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
		} finally {
			connectionDB.closeConnection();
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
		} finally {
			connectionDB.closeConnection();
		}
	}
	
	@Override
	public void delete(Long id) {	
		
		final Connection connection = connectionDB.getInstance();
		
		String query = "DELETE FROM computer WHERE id = ?";
	
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			preparedStatement.execute();	
			connectionDB.commit();
			LOGGER.info("Computer {} successfully deleted", id);	
		} catch (SQLException e) {
			throw new DAOException("ComputerDAO_delete Exception!", e);
		} finally {
			connectionDB.closeConnection();
		}
	}
}
