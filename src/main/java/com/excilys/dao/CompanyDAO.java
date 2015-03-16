package com.excilys.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;
import com.excilys.persistance.ConnectionDB;

public class CompanyDAO implements IDAO<Company, Long> {

	public Company getbyId( Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Company> getAll() {
		Connection connection = ConnectionDB.INSTANCE.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Company> companies = new ArrayList<>();

		CompanyMapper companyMapper = new CompanyMapper();
		
        ResultSet resultSet =  null;
		try {
			resultSet = statement.executeQuery( "SELECT * FROM company" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			while ( resultSet.next() )
			{
				Company company = companyMapper.mapResultSetToCompany(resultSet);
				companies.add(company);  	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return companies;
	}

	public Long create( Company entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
		/*Connection connection = ConnectionDB.INSTANCE.getConnection();
		String query = "INSERT INTO Company (NAME) VALUES ( ? )";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.set
		
		String name = entity.getName();
		
		ResultSet result = statement.executeQuery(query);
	*/		
	}


	public void update( Company entity) throws SQLException {
		// TODO Auto-generated method stub
	}

	public void delete( Long id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
