package com.excilys.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<T, I extends Serializable> {

	T getbyId( I id) throws SQLException;
	
	List<T> getAll() throws SQLException;
	
	I create( T entity) throws SQLException;
	
	void update( T entity) throws SQLException;
	
	void delete( I id) throws SQLException;
}
