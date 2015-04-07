package com.excilys.persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.exception.DAOException;
import com.excilys.mapper.CompanyMapper;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistance.ConnectionDB;

public enum CompanyDAO implements IDAO<Company, Long> {
	INSTANCE;
	
	private Connection connection;
	private CompanyMapper companyMapper;
	private ConnectionDB connectionDB;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);
	
	private CompanyDAO() {
		connection = ConnectionDB.INSTANCE.getInstance();
		companyMapper = CompanyMapper.INSTANCE;
		connectionDB = ConnectionDB.INSTANCE;
	}
	
	@Override
	public Company getbyId(Long id) {	
		String query = "SELECT * FROM company WHERE id = ?";
		
		Company company = null;
		try {		
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
		
			ResultSet result = preparedStatement.executeQuery();
		
			if (result.first()){
				company = companyMapper.mapResultSetToModel(result);
			}
		} catch (SQLException e) {
			throw new DAOException("CompanyDAO_getById Exception!", e);
		} finally {
			connectionDB.closeConnection();
		}
		
		return company;
	}

	@Override
	public List<Company> getAll() {		
		List<Company> companies = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			
	        ResultSet resultSet = statement.executeQuery( "SELECT * FROM company" );
			while ( resultSet.next() )
			{
				Company company = companyMapper .mapResultSetToModel(resultSet);
				companies.add(company);  	
			}
		} catch (SQLException e) {
			throw new DAOException("CompanyDAO_getAll Exception!", e);
		} finally {
			connectionDB.closeConnection();
		}
		
        return companies;
	}
	
	@Override
	public void delete(Long id) {	
		String query = "DELETE FROM company WHERE id = ?";
		String queryComputer = "SELECT * FROM computer WHERE company_id = ?";
	
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			PreparedStatement preparedStatementComputer = connection.prepareStatement(query);
			preparedStatementComputer.setLong(1, id);
			ResultSet resultSet = preparedStatementComputer.executeQuery(queryComputer);
			while ( resultSet.next() )
			{
				Computer computer = ComputerMapper.INSTANCE.mapResultSetToModel(resultSet);
				ComputerDAO.INSTANCE.delete(computer.getId());
			}			
			preparedStatement.execute();	
			connectionDB.commit();
			LOGGER.info("Company {} successfully deleted", id);	
		} catch (SQLException e) {
			throw new DAOException("ComputerDAO_delete Exception!", e);
		} finally {
			connectionDB.closeConnection();
		}
	}
}
