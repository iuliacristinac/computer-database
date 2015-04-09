package com.excilys.persistance.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.CompanyMapper;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Company;
import com.excilys.persistance.ConnectionDB;

@Repository
public class CompanyDAO implements IDAO<Company, Long> {
	
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private ComputerMapper computerMapper;
	@Autowired 
	private ComputerDAO computerDAO;
	@Autowired
	private ConnectionDB connectionDB;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);
	
	@Override
	public Company getbyId(Long id) {	

	return jdbcTemplate.queryForObject("SELECT * FROM company WHERE id = ?", new Object[] { id }, companyMapper);
	}

	@Override
	public List<Company> getAll() {		
		

	return jdbcTemplate.query("SELECT * FROM company", companyMapper);
	}
	
	@Override
	public void delete(Long id) {	
		
		jdbcTemplate.update("DELETE FROM company WHERE id = ?", id);
		LOGGER.info("Company {} successfully deleted", id);	
	}
}
