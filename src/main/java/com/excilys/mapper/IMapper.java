package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IMapper<T> {

	 T mapResultSetToModel(ResultSet result) throws SQLException;
	 
}
