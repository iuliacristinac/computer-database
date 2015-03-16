package com.excilys.dao;

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

public class ComputerDAO implements IDAO<Computer, Long>{

	@Override
	public Computer getbyId(Long id) throws SQLException {
		Connection connection = ConnectionDB.INSTANCE.getConnection();
		
		String query = "SELECT * FROM computer WHERE id = ?";
		
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

        ResultSet resultSet = statement.executeQuery( "SELECT * FROM computer" );

        while ( resultSet.next() )
        {
			Computer computer = computerMapper.mapResultSetToComputer(resultSet);
			computers.add(computer);  	
        }
        return computers;
	}

	@Override
	public Long create(Computer entity) throws SQLException {
		Connection connection = ConnectionDB.INSTANCE.getConnection();
		
		ComputerMapper computerMapper = new ComputerMapper();
		
		String query = "INSERT INTO computer (name, introduced, discontinued) VALUES (?, ?, ?, ?)";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, entity.getName());
		
		if (entity.getIntroduced() != null) {
			preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getIntroduced()));		
		}
		if (entity.getDiscontinued() != null) {
			preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getDiscontinued()));		
		}
		
		if (entity.getCompany_id() != 0) {
		preparedStatement.setLong(4, entity.getCompany_id());
		}
		
		ResultSet result = preparedStatement.executeQuery();
		
		Computer computer = null;
		if (result.first()) {
			computer = computerMapper.mapResultSetToComputer(result);
		}		
		return computer.getId();
	}

	@Override
	public void update(Computer entity) throws SQLException {
		Connection connection = ConnectionDB.INSTANCE.getConnection();
		
		String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ? ";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setLong(5, entity.getId());
		
		Computer computer = getbyId(entity.getId());
		
		if ((entity.getName() != null)  && (!computer.getName().equals(entity.getName()))) {
			preparedStatement.setString(1, entity.getName());
		}
		
		if ((entity.getIntroduced() != null) && (!computer.getIntroduced().equals(entity.getIntroduced()))) {
			preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getIntroduced()));		
		}
		if ((entity.getDiscontinued() != null) && (!computer.getDiscontinued().equals(entity.getDiscontinued()))){
			preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getDiscontinued()));		
		}
		
		if ((entity.getCompany_id() != 0) && (computer.getCompany_id() != entity.getCompany_id())) {
			preparedStatement.setLong(4, entity.getCompany_id());
			}
		
		preparedStatement.executeQuery();
	}

	@Override
	public void delete(Long id) throws SQLException {

		Connection connection = ConnectionDB.INSTANCE.getConnection();
	
		String query = "DELETE FROM computer WHERE id = ?";
	
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(1, id);

		preparedStatement.executeQuery();	
	}
}
