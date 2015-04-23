package com.excilys.service;

import java.io.Serializable;
import java.util.List;

public interface IService<T, I extends Serializable> {
	
	default I count() {
		throw new UnsupportedOperationException();
	}
	
	default List<T> getAll() {
		throw new UnsupportedOperationException();
	}
	
	default T getById(I id) {
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
}