package com.excilys.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Properties;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;

import com.excilys.persistance.ConnectionDB;


public class DBUtil {
	
	private IDatabaseTester databaseTester;
	private String url;
	private String user;
	private String password;
	private String driver ;
	
	public DBUtil() {
		final Properties properties = new Properties();
		try (final InputStream is = ConnectionDB.class
			.getClassLoader().getResourceAsStream("config-test.properties")) {
			properties.load(is);
			System.out.println("-------------------" + properties + "--------------");
			driver = properties.getProperty("test-driver");
			url = properties.getProperty("test-url");
			user = properties.getProperty("test-username");
			password = properties.getProperty("test-password");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		try {
			RunScript.execute(url, user, password, "src/test/resources/testDB.sql", Charset.forName("UTF8"), false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void importDataSet(String xmlFile) throws Exception {
		IDataSet dataSet = readDataSet(xmlFile);
		cleanlyInsert(dataSet);
	}

	public void tearDown() throws Exception {
		databaseTester.onTearDown();
	}
	
	
	private void cleanlyInsert(IDataSet dataSet) throws Exception {
		databaseTester = new JdbcDatabaseTester(driver, url, user, password);
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}
	
	private IDataSet readDataSet(String xmlFile) throws Exception {
		return new FlatXmlDataSetBuilder().build(new File(xmlFile));
	}
	

}
