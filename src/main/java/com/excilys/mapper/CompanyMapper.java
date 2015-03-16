package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.model.Company;

public class CompanyMapper {

	public Company mapResultSetToCompany( ResultSet result) throws SQLException{
		Company company = new Company();
		company.setId(result.getLong("id"));
		company.setName(result.getString("name"));
		
		return company;
	}
	
}
