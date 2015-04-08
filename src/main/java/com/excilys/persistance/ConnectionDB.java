package com.excilys.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.excilys.exception.ConnectionException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

@Component
public class ConnectionDB {

	private Properties properties;
	private static BoneCP connectionPool = null;
	private static final ThreadLocal<Connection> CONNECTION = new ThreadLocal<Connection>() {
		@Override
		protected Connection initialValue() {
			try {
					return connectionPool.getConnection();
				} catch (SQLException e) {
					throw new ConnectionException("Impossible to intialise the thread local", e);
				}
		}	
	};

	private ConnectionDB() {
			try {
				loadConfigFile();
			} catch (IOException  e) {
				throw new ConnectionException("Impossible to load properties file", e);
			}
	}
	
	public Connection getInstance() {
		if (connectionPool == null) {
			createConnectionPool();
		}
		return getConnection();
	}

	private void loadConfigFile() throws IOException {
		properties = new Properties();
		try (final InputStream inputStream = ConnectionDB.class.getClassLoader()
				.getResourceAsStream("config.properties")) {
			properties.load(inputStream);
		}
	}
	
	private void createConnectionPool() {
		try {
			Class.forName(properties.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			throw new ConnectionException("Impossible to load mysql driver!");
		} 
		BoneCPConfig config = new BoneCPConfig();
		config.setJdbcUrl(properties.getProperty("url"));
		config.setUsername( properties.getProperty("user") );
		config.setPassword( properties.getProperty("password") ); 
		config.setMinConnectionsPerPartition(Integer.valueOf(properties.getProperty("minConnectionsPerPartition"))); 
		config.setMaxConnectionsPerPartition(Integer.valueOf(properties.getProperty("maxConnectionsPerPartition")));
		config.setPartitionCount( 2 );        
		try {
			connectionPool = new BoneCP( config );
		} catch (SQLException e) {
			throw new ConnectionException("Impossible to create the connection pool.", e);
		} 	
	}
	
	private Connection getConnection() {
		return CONNECTION.get();
	}
	
	public void startTransaction() {
		final Connection connection = getConnection();
		if (connection != null) {
			try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				throw new ConnectionException("Impossible to start transaction", e);
			}
		}
	}

	public void endTransaction() {
		final Connection connection = getConnection();
		if (connection != null) {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new ConnectionException("Impossible to end transaction", e);
			}
		}
	}

	public void commit() {
		final Connection connection = getConnection();
		if (connection != null) {
			try {
				connection.commit();
			} catch (SQLException e) {
				throw new ConnectionException("Impossible to commit transaction", e);
			}
		}
	}

	public void rollback() {
		final Connection connection = getConnection();
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				throw new ConnectionException("Impossible to rollback transaction", e);
			}
		}
	}

	public void closeConnection() {
		final Connection connection = getConnection();
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ConnectionException("Impossible to close connection", e);
			}
			CONNECTION.remove();
		}
	}
}
