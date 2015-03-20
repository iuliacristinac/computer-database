package com.excilys.persistance.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;
import com.excilys.persistance.ConnectionDB;

public enum CompanyDAO implements IDAO<Company, Long> {
	INSTANCE;

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
}
