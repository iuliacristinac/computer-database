package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.excilys.model.Company;
import com.excilys.model.Computer;

@Component
public class ComputerMapper implements IMapper<Computer> {

	@Override
	public Computer mapResultSetToModel( ResultSet result) throws SQLException {
		 if (result == null) {
			 throw new IllegalArgumentException("ComputerMapper - Null query result");
		 }

		Computer computer = new Computer();
		computer.setId(result.getLong("id"));
		computer.setName(result.getString("name"));
		
		final Timestamp introduced = result.getTimestamp("introduced");
		if (introduced != null){
			computer.setIntroduced(introduced.toLocalDateTime());
		}
		
		final Timestamp discontinued = result.getTimestamp("discontinued");
		if (discontinued != null){
			computer.setDiscontinued(discontinued.toLocalDateTime());
		}
		
		final Long companyId = result.getLong("computer.company_id");
		if (companyId > 0){
			final Company company = new Company();
			company.setId(companyId);
			company.setName(result.getString("company.name"));
			computer.setCompany(company);	
		}
		return computer;
	}
}
