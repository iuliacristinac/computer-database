package com.excilys.persistance.dao;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.model.Company;
import com.excilys.util.DBUtil;

public class CompanyDAOTest {

	private static DBUtil dbUtil;
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@BeforeClass
	public static void setUpDB() {
		dbUtil = new DBUtil();
	}
	
	@After
	public void tearDown() throws Exception {
		dbUtil.tearDown();
	}
	
	@Test
	public void getAllReturnsEntitiesOK() throws Exception {
		//GIVEN
		dbUtil.importDataSet("src/test/java/datasets/companyDAO/getAll.xml");
		
		final int expectedSize = 3;
		final Company expectedCompany1 = new Company(1L, "Apple INC.");
		final Company expectedCompany2 = new Company(2L, "Thinking Machines");
		final Company expectedCompany3 = new Company(3L, "RCA");
		// WHEN
		final List<Company> companies = companyDAO.getAll();
		// THEN
		Assertions.assertThat(companies).isNotNull();
		Assertions.assertThat(companies.size()).isEqualTo(expectedSize);
		Assertions.assertThat(companies).contains(expectedCompany1, expectedCompany2, expectedCompany3);
	}

	@Test
	public void getAllNoEntities() throws Exception {
		//GIVEN
		dbUtil.importDataSet("src/test/java/datasets/companyDAO/getAllEmpty.xml");
		
		// WHEN
		final List<Company> companies = companyDAO.getAll();
		// THEN
		Assertions.assertThat(companies).isNotNull();
		Assertions.assertThat(companies).isEmpty();
	}

	
}
