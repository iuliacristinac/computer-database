package com.excilys.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum ConnectionDB {
	INSTANCE;

	private Properties properties;
	private String url;

	/*
	 * private Connection createConnection() throws ClassNotFoundException,
	 * SQLException{ Class.forName( "com.mysql.jdbc.Driver" ); Connection
	 * connection = DriverManager.getConnection(
	 * "jdbc:mysql://localhost:3306/computer-database-db/?" +
	 * "user=root&password=iulia" ); return connection; }
	 */
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			loadConfigFile();
			connection = DriverManager.getConnection(url, properties);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	private void loadConfigFile() throws IOException {
		if (properties == null) {
			properties = new Properties();
			try (final InputStream is = ConnectionDB.class.getClassLoader()
					.getResourceAsStream("config.properties")) {
				properties.load(is);
				url = properties.getProperty("url");
			}
		}
	}

}
