package com.excilys.persistance;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.exception.ConnectionException;
import com.jolbox.bonecp.BoneCPDataSource;

@Component("ConnectionDB")
public class ConnectionDB {

	@Autowired
	private BoneCPDataSource dataSource;

	public Connection getInstance() {
		return getConnection();
	}

	private Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new ConnectionException("Impossible to get a connection from the connection pool", e);
		}
	}
}
