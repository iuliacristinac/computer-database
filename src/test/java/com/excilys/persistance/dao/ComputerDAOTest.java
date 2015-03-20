package com.excilys.persistance.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.util.DBUtil;

public class ComputerDAOTest {

	private static DBUtil dbUtil;
	
	@BeforeClass
	public static void setUpDB() {
		dbUtil = new DBUtil();
	}
	
	@After
	public void tearDown() throws Exception {
		dbUtil.tearDown();
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
