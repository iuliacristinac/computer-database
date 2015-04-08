package com.excilys.persistance.dao;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.util.DBUtil;

public class ComputerDAOTest {

	private static DBUtil dbUtil;
	
	@Autowired
	private ComputerDAO computerDAO;
	
	@BeforeClass
	public static void setUpDB() {
		dbUtil = new DBUtil();
	}
	
	public void tearDown() throws Exception {
		dbUtil.tearDown();
	}
	
	@Test
	public void getAllReturnsEntitiesOK() throws Exception {
		//GIVEN
		dbUtil.importDataSet("src/test/java/datasets/computerDAO/getAll.xml");
		
		final int expectedSize = 3;
		final Company company1 = new Company(1L, "Apple INC.");
		final Company company2 = new Company(2L, "Thinking Machines");
		final Computer expectedComputer1 = new Computer(1L, "MacBook Pro 15.4 inch", company1);
		final Computer expectedComputer2 = new Computer(2L, "CM-2a", company2);
		final Computer expectedComputer3 = new Computer(7L, "Apple IIe");
		// WHEN
		final List<Computer> computers = computerDAO.getAll();
		// THEN
		Assertions.assertThat(computers).isNotNull();
		Assertions.assertThat(computers.size()).isEqualTo(expectedSize);
		Assertions.assertThat(computers).contains(expectedComputer1, expectedComputer2, expectedComputer3);
	}
	
	@Test
	public void getAllNoEntities() throws Exception {
		//GIVEN
		dbUtil.importDataSet("src/test/java/datasets/computerDAO/getAllEmpty.xml");
		
		// WHEN
		final List<Computer> computers = computerDAO.getAll();
		// THEN
		Assertions.assertThat(computers).isNotNull();
		Assertions.assertThat(computers).isEmpty();
	}

}
