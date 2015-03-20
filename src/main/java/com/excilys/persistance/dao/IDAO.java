package com.excilys.persistance.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<T, I extends Serializable> {
	
	default List<T> getAll() throws SQLException {
		throw new UnsupportedOperationException();
	}
	
	default T getbyId( I id) throws SQLException {
		throw new UnsupportedOperationException();
	}
	
	default void create( T entity) throws SQLException {
		throw new UnsupportedOperationException();
	}
	
	default void update( T entity) throws SQLException {
		throw new UnsupportedOperationException();
	}
	
	default void delete( I id) throws SQLException {
		throw new UnsupportedOperationException();
	}
}
