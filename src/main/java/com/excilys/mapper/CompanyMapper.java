package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.excilys.model.Company;

@Component
public class CompanyMapper implements IMapper<Company>{
	
	@Override
	public Company mapResultSetToModel( ResultSet result) throws SQLException {	
		 if (result == null) {
			 throw new IllegalArgumentException("ComputerMapper - Null query result");
		 }

		Company company = new Company();
		company.setId(result.getLong("id"));
		company.setName(result.getString("name"));
		return company;
	}
	
}
