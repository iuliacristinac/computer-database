package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.model.Computer;

public class ComputerMapper {

	public Computer mapResultSetToComputer( ResultSet result) throws SQLException{
		Computer computer = new Computer();
		computer.setId(result.getLong("id"));
		computer.setName(result.getString("name"));
		if (result.getTimestamp("introduced") != null){
			computer.setIntroduced(result.getTimestamp("introduced").toLocalDateTime());
		}
		if (result.getTimestamp("discontinued") != null){
			computer.setDiscontinued(result.getTimestamp("discontinued").toLocalDateTime());
		}
		computer.setCompany_id(result.getLong("company_id"));
		
		return computer;
	}
}
