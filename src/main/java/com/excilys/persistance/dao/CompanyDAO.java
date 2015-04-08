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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.exception.DAOException;
import com.excilys.mapper.CompanyMapper;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistance.ConnectionDB;

@Repository
public class CompanyDAO implements IDAO<Company, Long> {
	
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private ComputerMapper computerMapper;
	@Autowired 
	private ComputerDAO computerDAO;
	@Autowired
	private ConnectionDB connectionDB;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);
	
	@Override
	public Company getbyId(Long id) {	
		
		final Connection connection = connectionDB.getInstance();
		
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
		
		final Connection connection = connectionDB.getInstance();
		
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
		
		final Connection connection = connectionDB.getInstance();
		
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
				Computer computer = computerMapper.mapResultSetToModel(resultSet);
				computerDAO.delete(computer.getId());
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
