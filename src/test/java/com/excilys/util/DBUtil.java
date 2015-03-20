package com.excilys.util;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;

import com.excilys.persistance.ConnectionDB;


public class DBUtil {
	
	private Connection testConnection;
	private IDatabaseTester databaseTester;
	private String url;
	private String user;
	private String password;
	private String driver ;
	
	public DBUtil() {
		System.setProperty("env", "TEST");
		
		testConnection = ConnectionDB.INSTANCE.getConnection();
		url = ConnectionDB.INSTANCE.getUrl();
		user = ConnectionDB.INSTANCE.getUser();
		password = ConnectionDB.INSTANCE.getPassword();
		driver = ConnectionDB.INSTANCE.getDriver();
		
		try {
			RunScript.execute(url, user, password, "testDB.sql", Charset.forName("UTF8"), false);
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
