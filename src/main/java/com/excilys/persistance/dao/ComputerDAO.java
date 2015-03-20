package com.excilys.persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.persistance.ConnectionDB;

public enum ComputerDAO implements IDAO<Computer, Long>{
	INSTANCE;
	
	@Override
	public Computer getbyId(Long id) throws SQLException {
		Connection connection = ConnectionDB.INSTANCE.getConnection();
		
		String query = "SELECT * FROM computer, company WHERE computer.company_id = company.id AND computer.id = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(1, id);
		
		ComputerMapper computerMapper = new ComputerMapper();
		
		ResultSet result = preparedStatement.executeQuery();
		
		Computer computer = null;
		if (result.first()){
			computer = computerMapper.mapResultSetToComputer(result);
		}		
		return computer;
	}

	@Override
	public List<Computer> getAll() throws SQLException {
		Connection connection = ConnectionDB.INSTANCE.getConnection();
		Statement statement = connection.createStatement();
		List<Computer> computers = new ArrayList<>();
		
		ComputerMapper computerMapper = new ComputerMapper();

        ResultSet resultSet = statement.executeQuery( "SELECT * FROM computer, company WHERE computer.company_id = company.id" );

        while ( resultSet.next() )
        {
			Computer computer = computerMapper.mapResultSetToComputer(resultSet);
			computers.add(computer);  	
        }
        return computers;
	}

	@Override
	public void create(Computer entity) throws SQLException {
		Connection connection = ConnectionDB.INSTANCE.getConnection();
		
		String query = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES ( ?, ?, ?, ?)";
		
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
		
		try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
            }
            else {
                throw new SQLException("Creating computer failed.");
            }
        }	
	}

	@Override
	public void update(Computer entity) throws SQLException {
		Connection connection = ConnectionDB.INSTANCE.getConnection();
		
		String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ? ";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setLong(5, entity.getId());
		
//		Computer computer = getbyId(entity.getId());
		
		if (entity.getName() != null ) {
			preparedStatement.setString(1, entity.getName());
		}
//		else {
//			preparedStatement.setString(1, computer.getName());
//		}
		
		if (entity.getIntroduced() != null) {
			preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getIntroduced()));		
		}
//		else if (computer.getIntroduced() != null) {
//			preparedStatement.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced()));	
//		}
		else {
			preparedStatement.setTimestamp(2, null);
		}
		
		
		if (entity.getDiscontinued() != null) {
			preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getDiscontinued()));		
		}
//		else if (computer.getDiscontinued() != null) {
//			preparedStatement.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()));
//		}
		else{
			preparedStatement.setTimestamp(3, null);
		}
		
		if (entity.getCompany() != null) {
			preparedStatement.setLong(4, entity.getCompany().getId());
		}
//		else if (computer.getCompany() != null) {
//			preparedStatement.setLong(4, computer.getCompany().getId());
//		} 
		else {
			preparedStatement.setObject(4, null);
		}
		
		preparedStatement.execute();
	}

	@Override
	public void delete(Long id) throws SQLException {

		Connection connection = ConnectionDB.INSTANCE.getConnection();
	
		String query = "DELETE FROM computer WHERE id = ?";
	
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(1, id);

		preparedStatement.execute();	
	}
}
