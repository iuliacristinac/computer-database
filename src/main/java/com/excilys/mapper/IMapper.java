package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public interface IMapper<T> extends RowMapper<T> {

	public T mapRow(ResultSet result, int arg1) throws SQLException;
}
