package com.excilys.persistance.dao;

import java.io.Serializable;
import java.util.List;

import com.excilys.model.Computer;

public interface IDAO<T, I extends Serializable> {
	
	default List<T> getAll() {
		throw new UnsupportedOperationException();
	}
	
	default T getbyId( I id) {
		throw new UnsupportedOperationException();
	}
	
	default void create( T entity) {
		throw new UnsupportedOperationException();
	}
	
	default void update( T entity) {
		throw new UnsupportedOperationException();
	}
	
	default void delete( I id) {
		throw new UnsupportedOperationException();
	}

	default List<Computer> getAllByCompany(Long id){
		throw new UnsupportedOperationException();
	}
}
